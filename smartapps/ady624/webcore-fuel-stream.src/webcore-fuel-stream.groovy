/*
 * Last update April 28, 2022 for Hubitat
 */

private static String handle() { return "webCoRE" }
@Field static final String sVER='v0.3.114.20220203'
@Field static final String sHVER='v0.3.114.20220428_HE'
public static String version(){ return sVER }
public static String HEversion(){ return sHVER }

definition(
	namespace:"ady624",
	name:"${handle()} Fuel Stream",
	description: "Local container for fuel streams",
	author:"jp0550",
	category:"My Apps",
	iconUrl: "https://cdn.rawgit.com/ady624/${handle()}/master/resources/icons/app-CoRE.png",
	iconX2Url: "https://cdn.rawgit.com/ady624/${handle()}/master/resources/icons/app-CoRE@2x.png",
	iconX3Url: "https://cdn.rawgit.com/ady624/${handle()}/master/resources/icons/app-CoRE@3x.png",
	parent: "ady624:webCoRE"
)


import groovy.json.*
import java.text.DecimalFormat
import groovy.transform.Field
import groovy.transform.CompileStatic
import java.util.concurrent.Semaphore

preferences {
	page(name: "settingsPage")
}

def settingsPage(){
 	dynamicPage(name: "settingsPage", title: "Settings", uninstall: true, install: true){
		section('Use HE files for data storage'){
			input( type: "bool", name: "useFiles", title: "Use HE files for fuelstream storage?",
					required: false, multiple: false, submitOnChange: true, defaultValue: false)

		}
		if((Boolean)settings.useFiles){
			section('Security'){
				if (settings["hpmSecurity"]==null){
					settings["hpmSecurity"] = true
					app.updateSetting("hpmSecurity", [type: "bool", value: "true"])
				}
				input( type: "bool", name: "hpmSecurity", title: "Use Hubitat Security",
						required: false, multiple: false, submitOnChange: true, defaultValue: true)

				if ((Boolean)settings["hpmSecurity"]){
					input "username", "string", title: "Hub Security username", required: false, submitOnChange: true
					input "password", "password", title: "Hub Security password", required: false, submitOnChange: true
				}
			}
			if ((Boolean)settings["hpmSecurity"] && settings.password && !login()){
				section('Login Error'){
					paragraph("""<b>CANNOT LOGIN</b><br>If you have Hub Security Enabled, please put in correct login credentials<br> If not, please deselect <b>Use Hubitat Security</b>""" )
				}
			}
		}

		section('Storage Limit'){
			input "maxSize", "number", title: "Max size of this fuelStream data in KB", defaultValue: 95
		}

		List<Map> a=getFuelStreamDBData(false)
		state.useFiles= (Boolean)settings.useFiles && !(a)
		section('Storage'){

			if((Boolean)settings.useFiles){
				String attribute=fuelNattr()
				def sensor=app
				Boolean fexists= fileExists(sensor,attribute)

				if(a){
					paragraph("Found DB Storage in use, with use files selected")
					input( type: "bool", name: "convertToFile", title: "Convert to File storage",
							required: false, multiple: false, submitOnChange: true, defaultValue: false)

					if((Boolean)settings.convertToFile){
						if(!fexists){
							if(writeFile(sensor, attribute, JsonOutput.toJson(a))){
								state.remove('fuelStreamData')
								info "Converted to file",null
								fexists=true
								a=null
								state.useFiles= (Boolean)settings.useFiles && !(a)

							} else{
								error "conversion to file failed",null
							}

						} else {
							paragraph("Found file exists with DB storage in use")
						}
						app.updateSetting("convertToFile", [type: "bool", value: "false"])
					}
				}
				//a=getFuelStreamFData()
			}

			Map storage = getCurrentDailyStorage(null,null)
			if(!(Boolean)settings.useFiles || !(Boolean)state.useFiles){
				paragraph("Using HE DB as storage")
			}
			if((Boolean)settings.useFiles && (Boolean)state.useFiles){
				paragraph("Using HE Files as storage")
			}
			paragraph("Storage Limit: ${settings.maxSize}KB")
			paragraph("Current storage usage is ${convertStorageSize(storage.size)}")
			Integer storageSize = state.toString().size()
			paragraph("Current state usage is ${convertStorageSize(storageSize)}")
			paragraph("Details: ${storage}")
		}
	}
}

String fuelName(){
	return getFileName(app.id,fuelNattr())
}

@CompileStatic
String fuelNattr(){
	Map fs=(Map)gtSt('fuelStream')
//state.fuelStream = [i: settings.id, c: (settings.canister ?: sBLK), n: settings.name, w: 1, t: getFormattedDate(new Date())]
	String c = fs.c ?: sBLK
	String n = fs.n
	Integer i = (Integer)fs.i
	String d='_'
	String attribute = c+d+n+d+i.toString()
	return attribute.replaceAll(sSPC, d)
}

def installed(){
	log.debug "Installed with settings $settings"
	initialize()
}

def updated(){
	log.debug "Updated with settings $settings"
 	initialize()
}

def initialize(){
	unsubscribe()
	unschedule()

	if(app.id){ // if someone changed settings
		List<Map> a=getFuelStreamData(false)
		if(a){
			a = cleanFuelStreams(a)
			storeUpdate(a)
		}
	}
}

public void createStream(settings){
	state.fuelStream = [i: settings.id, c: (settings.canister ?: sBLK), n: settings.name, w: 1, t: getFormattedDate(new Date())]
}

public Map getFuelStream(){
	(Map)state.fuelStream
}

public List<Map> listFuelStreamData(){
	getFuelStreamData().collect{ it + [t: getFormattedDate(new Date((Long)it.i))]}
}

public List<Map> getFuelStreamData(Boolean init=true){
	if(!(Boolean)state.useFiles){
		return getFuelStreamDBData(init)
	} else return getFuelStreamFData()
}

public List<Map> readFuelStream(Map req) {
	if(!req)return
	return listFuelStreamData()
}

public void writeFuelStream(Map req){ // overwrite
	if(!req)return
	if(req.d instanceof List)
		storeUpdate((List)req.d,true)
}

public void clearFuelStream(Map req){
	if(!req)return
	storeUpdate([],true)
}

public void updateFuelStream(Map req){ // append
//	def canister = req.c ?: sBLK
//	def name = req.n
//	def instance = req.i
//	def data = req.d
//	def source = req.s

	if(!req)return
	List<Map> stream= getFuelStreamData()
	Boolean a=stream.add([d: req.d, i: wnow()])
	storeUpdate(stream)
}

@Field volatile static Map<String,String> storTmpFLD=[:]

List<Map> getFuelStreamDBData(Boolean init=true){
	if(!state.fuelStreamData){
		if(init) state.fuelStreamData = []
	}

	return (List)state.fuelStreamData
}

List<Map> getFuelStreamFData(){
	if((Boolean)state.useFiles){
		String attribute=fuelNattr()
		def sensor=app
		List<Map> stream= getFileData(sensor, attribute)
		List<Map> tstor=(List)state[attribute] ?: []
		return stream+tstor
	}

	return null
}

@CompileStatic
List<Map> cleanFuelStreams(List<Map> stream){
	//ensure max size is obeyed

	if(!stream) return []
	Boolean a
	stream.each { a=it.keySet().remove('t') }

	Double storageSize = (Integer)(stream.toString().size() / 1024.0)
	Integer max = (gtSetting('maxSize') ?: 95) as Integer

	if(storageSize > max){
		debug "Trim down fuel stream",null
		Integer points = stream.size()
		Double averageSize = points > 0 ? (storageSize/points).toDouble() : 0.0D

		Integer pointsToRemove = averageSize > 0 ? (Integer)((storageSize - max) / averageSize) : 0
		pointsToRemove = pointsToRemove > 0 ? pointsToRemove : 0

		debug "Size ${storageSize}KB Points ${points} Avg $averageSize Remove $pointsToRemove".toString(),null
		List<Map> toBeRemoved = stream.sort { it.i }.take(pointsToRemove)
		a=stream.removeAll(toBeRemoved)
	}
	return stream
}

void storeUpdate(List<Map>istream,Boolean frc=false){
	Boolean res
	List<Map>stream = cleanFuelStreams(istream)
	if(!(Boolean)state.useFiles) {
		res = storeDBData(stream)
	}else res=storeFData(stream,frc)
	if(!res) warn "storeUpdate failed",null
}

Boolean storeDBData(List<Map>stream){
	if(!(Boolean)state.useFiles) {
		state.fuelStreamData=stream
		return true
	}
	return false
}

Boolean storeFData(List<Map>istream,Boolean frc){
	if((Boolean)gtSt('useFiles')){
		String attribute=fuelNattr()
		def sensor=app

		Integer osz=istream.size()
		List<Map>stream = cleanFuelStreams(istream)
		Integer nsz=stream.size()

		if(!frc && nsz>0 && osz==nsz){
			Long lst = nsz>1 ? (Long)stream[nsz-1].i : 0L
			Long lst2 = nsz> 1 ? (Long)stream[nsz-2].i : 0L
			if((lst-lst2) < 1800000L){ // 30 mins
				List<Map> tstor=(List)state[attribute] ?: []
				if(tstor.toString().size()<2000 && tstor.size()<20){
					Map item=stream.pop()
					Boolean a= tstor.add(item)
					state[attribute]=tstor
					return true
				}
			}
		}
		state[attribute]= []
		return writeFile(sensor, attribute, JsonOutput.toJson(stream))

	}
	return false
}

@CompileStatic
static String getFormattedDate(Date date = new Date()){
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	format.setTimeZone(TimeZone.getTimeZone("UTC"))
	format.format(date)
}

def uninstalled(){
	parent.resetFuelStreamList()
}

Boolean login() {
	if ((Boolean)settings["hpmSecurity"]){
		Boolean result = false
		try {
			httpPost( [
				uri: "http://127.0.0.1:8080",
				path: "/login",
				query: [ loginRedirect: "/" ],
				body: [
						username: username,
						password: password,
						submit: "Login"
				],
				textParser: true,
				ignoreSSLIssues: true
				]
			) { resp ->
				if (resp.data?.text?.contains("The login information you supplied was incorrect."))
					result = false
				else {
					state.cookie = ((List) ((String)resp?.headers?.'Set-Cookie')?.split(';'))?.getAt(0)
					result = true
				}
			}
		} catch (e) {
			error "Error logging in: ",null,iN2,e
			result = false
		}
		return result
	}
	return true
}

Boolean fileExists(sensor, String attribute){

	String filename_ = getFileName(sensor, attribute)

	String uri = "http://${location.hub.localIP}:8080/local/${filename_}"

	Map params = [
		uri: uri,
		textParser: true,
	]

	Boolean res=false
	try {
		httpGet(params) { resp ->
			if(resp.status==200) res=true
		}
	} catch (e){
		if (e.message.contains("Not Found")){
			debug "File DOES NOT Exists for ${sensor.name} (${attribute})",null,iN2
		} else {
			error"Find file ${sensor.name} (${attribute}) ($filename_} :: Exception: ",null,iN2,e
		}
	}
	return res
}

@Field volatile static Map<String,String> readTmpFLD=[:]

Map readFile(sensor, String attribute){

	String filename_ = getFileName(sensor, attribute)
	String pNm=filename_
	if(readTmpFLD[pNm]==sNL) { readTmpFLD[pNm]=sBLK; readTmpFLD= readTmpFLD }
	try {
		Integer sz=readTmpFLD[pNm].size()
		if(sz> 4) {
			JsonSlurper jsonSlurper = new JsonSlurper()
			List parse = convertToList((List<Map>) jsonSlurper.parseText(readTmpFLD[pNm]))
			if(eric()) trace "readFile cache hit",null
			return [size: sz, data: parse ]
		}
	} catch (ignored) {}

	String uri = "http://${location.hub.localIP}:8080/local/${filename_}"
	Map params = [
		uri: uri,
		contentType: "text/plain; charset=UTF-8",
		textParser: true,
		headers: [ "Cookie": state.cookie, "Accept": 'application/octet-stream' ]
	]

	try {
		httpGet(params) { resp ->
			if(resp.status==200 && resp.data){
				Integer i
				char c
				i=resp.data.read()
				while(i!=-1){
					c=(char)i
					readTmpFLD[pNm]+=c
					i=resp.data.read()
				}
				//log.warn "pNm: ${pNm} data: ${data} file: ${readDataFLD[pNm]}"
			}else{
				error "Read Response status $resp.status",null
			}
		}
		readTmpFLD= readTmpFLD
		JsonSlurper jsonSlurper = new JsonSlurper()
		List parse = convertToList((List<Map>)jsonSlurper.parseText(readTmpFLD[pNm]))
		return [size: readTmpFLD[pNm].size(), data: parse ]
	} catch (e) {
		error"Read file ${sensor.name} (${attribute}) ($filename_} :: Exception: ",null,iN2,e
	}
	readTmpFLD[pNm]=sNL
	readTmpFLD= readTmpFLD
	return null
}

static String getFileName(sensor, String attribute){
	String attr = attribute.replaceAll(sSPC, "_")
	return "WebCoRE_Fuel_${sensor.id}_${attr}.json"
}

List<Map> pruneData(List<Map> input_data, Integer time){

	if(input_data==null) return null
	List return_data = []+input_data
	if(input_data.size() > 0){
		Integer days = time
		if (days == 0) return input_data

		Date then = new Date()
		use (groovy.time.TimeCategory) {
			then -= days.days
		}

		Long startDate = then.getTime()

		//Long date = ((Date)input_data[0].date).getTime()
		Long date = (Long)input_data[0].i

		while (date < startDate){
			return_data.remove(0)
			//date = ((Date)return_data[0].date).getTime()
			date = (Long)return_data[0].i
		}
	}
	return return_data
}

static List addData(List main, List append){

	List return_data = main
	append.each{data->
		return_data << data
	}
	return return_data
}

static List convertToList(List<Map>json){

	return json
/*
	//String dateFormat = "yyyy-MM-dd'T'HH:mm:ssX"
	String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

	List return_data = []

	json.each{data->
		//Date date = Date.parse(dateFormat, (String)data.date)
		Date date = new Date((Long)data.t)
		return_data << [date: date, value: data.value, t:(Long)data.t]
	}
	return return_data */
}

List getFileData(sensor, String attribute){
	String filename_ = getFileName(sensor, attribute)

	String pNm=filename_
	if(readTmpFLD[pNm]==sNL) { readTmpFLD[pNm]=sBLK; readTmpFLD= readTmpFLD }

	List parse_data =[]
	try {
		Integer sz=readTmpFLD[pNm].size()
		if(sz> 4) {
			JsonSlurper jsonSlurper = new JsonSlurper()
			parse_data = convertToList((List<Map>)jsonSlurper.parseText(readTmpFLD[pNm]))
			if(eric()) trace "getFileData cache hit",null
			return parse_data
		}
	} catch (ignored) {}

	String uri = "http://${location.hub.localIP}:8080/local/${filename_}"

	Map params = [
			uri: uri,
			contentType: "text/plain; charset=UTF-8",
			textParser: true,
			headers: [ "Cookie": state.cookie, "Accept": 'application/octet-stream' ]
	]

	try {
		httpGet(params) { resp ->
			if(resp.status==200 && resp.data){
				Integer i
				char c
				i=resp.data.read()
				while(i!=-1){
					c=(char)i
					readTmpFLD[pNm]+=c
					i=resp.data.read()
				}
				//log.warn "pNm: ${pNm} data: ${data} file: ${readDataFLD[pNm]}"
			}else{
				error "Read Response status $resp.status",null
			}
		}
		readTmpFLD= readTmpFLD
		JsonSlurper jsonSlurper = new JsonSlurper()
		parse_data = convertToList((List<Map>)jsonSlurper.parseText(readTmpFLD[pNm]))
		return parse_data
	} catch (e) {
		error"Get File Data ${sensor.name} (${attribute}) ($filename_} :: Exception: ",null,iN2,e
	}
	readTmpFLD[pNm]=sNL
	readTmpFLD= readTmpFLD
	return parse_data
}

void appendFile(sensor, String attribute){
	myDetail null,"append file",i1

	String filename_ = getFileName(sensor, attribute)
	String attr = attribute.replaceAll(sSPC, "_")

//	String quantization_minutes = (String)settings["${sensor.id}_${attr}_quantization"]
//	String quantization_function = (String)settings["${sensor.id}_${attr}_quantization_function"]
//	Integer quantization_decimals = (Integer)settings["${sensor.id}_${attr}_quantization_decimals"]
//	Boolean quantization_boundary = settings["${sensor.id}_${attr}_boundary"] ?
//			(Boolean)settings["${sensor.id}_${attr}_boundary"] : false

	//Integer storage = (String)settings["${sensor.id}_${attr}_storage"] as Integer

	List<Map> parse_data = getFileData(sensor, attribute)

	try {

		//parse_data = pruneData(parse_data, storage)

		//Get the most Current Data
		//Date then = (Date)parse_data[parse_data.size()-1].date

		//List<Map> respEvents = getEvents(sensor: sensor, attribute: attribute, start_time: then)

		List<Map> write_data
		/*
		if (respEvents){
			write_data = addData(parse_data, respEvents)

		} else {
		}*/
		write_data = parse_data

		//write_data = quantizeData(write_data, quantization_minutes, quantization_function, quantization_decimals, quantization_boundary)

		writeFile(sensor, attribute, JsonOutput.toJson(write_data))

	} catch (e){

		readTmpFLD[pNm]=sNL
		readTmpFLD= readTmpFLD

		if (e.message.contains("Not Found")){
/*
			Date then = new Date()
			use (groovy.time.TimeCategory) {
				then -= storage.days
			}

			List<Map> respEvents = getEvents(sensor: sensor, attribute: attribute, start_time: then)
			respEvents = quantizeData(respEvents, quantization_minutes, quantization_function, quantization_decimals, quantization_boundary)

			String write_data = respEvents == null ? sBLK : JsonOutput.toJson(respEvents)

			writeFile(sensor, attribute, write_data)
*/
		} else {
			error "Append File ${sensor.name} (${attribute}) ($filename_} :: Exception: ",null,iN2,e
		}
	}
	myDetail null,"append file"
}

Boolean writeFile(sensor, String attribute, String contents) {

	String filename_ = getFileName(sensor, attribute)
	String pNm=filename_

	if (!login()) return false
	Integer sz=readTmpFLD[pNm].size()
	if(sz> 4 && sz==contents?.size() && contents==readTmpFLD[pNm]) {
		if(eric()) trace "writeFile no changes",null
		return true
	}

	Date d=new Date()
	String encodedString = "thebearmay$d".bytes.encodeBase64().toString()
	try {
		Map params = [
			uri: "http://127.0.0.1:8080",
			path: "/hub/fileManager/upload",
			query: [ "folder": "/" ],
			headers: [
					"Cookie": state.cookie,
					"Content-Type": "multipart/form-data; boundary=$encodedString"
			],
			body: """--${encodedString}
Content-Disposition: form-data; name="uploadFile"; filename="${filename_}"
Content-Type: "text/plain; charset=UTF-8"

${contents}

--${encodedString}
Content-Disposition: form-data; name="folder"


--${encodedString}--""",
			timeout: 300,
			ignoreSSLIssues: true
		]
		httpPost(params) { resp ->
			if(resp.status!=200){
				error "Write Response status $resp.status",null
			}
		}
		readTmpFLD[pNm]=contents
		readTmpFLD= readTmpFLD
		return true
	}
	catch (e) {
		error "Write File ${sensor.name} (${attribute}) ($filename_} :: Exception: ",null,iN2,e
	}
	readTmpFLD[pNm]=sNL
	readTmpFLD= readTmpFLD
	return false
}

Map getCurrentDailyStorage(sensor, String attribute){

	List<Map> a=getFuelStreamData()
	Integer sz=a.toString().size()
	//Map json = fileExists(sensor,attribute) ? readFile(sensor, attribute) : null
	Map json = [size: sz, data: a ]
	if (json?.data){

		List<Map> data = (List<Map>)json.data
		Integer size = (Integer)json.size

		//String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
		//String dateFormat = "yyyy-MM-dd'T'HH:mm:ssX"
		//Date first = Date.parse(dateFormat, (String)data[0].date)
		//Date then = Date.parse(dateFormat, (String)data[data.size()-1].date)
		Date first = new Date((Long)data[0].i)
		Date then = new Date((Long)data[data.size()-1].i)

//              List<Map> respEvents = getEvents(sensor: sensor, attribute: attribute, start_time: then)
//              String file_string = respEvents == null ? sBLK : JsonOutput.toJson(respEvents)

		return [num_events: data.size(), first: first, last: then, size: size]

	} else {
/*
		try{

			Integer storage = (String)settings["${sensor.id}_${attribute}_storage"] as Integer
			storage = storage ?: 30
			List<Map> respEvents = getEvents(sensor: sensor, attribute: attribute, days: storage)

			String file_string = respEvents == null ? sBLK : JsonOutput.toJson(respEvents)

			writeFile(sensor, attribute, file_string)

			return [num_events: respEvents.size(), first: respEvents[0].date, last: respEvents[respEvents.size()-1].date, size: file_string.size()]

		} catch (e) {
			error "Error: ",null,iN2,e
		}
*/
	}
	return null
}

static String convertStorageSize(Integer num){
	def df = new DecimalFormat("#0.0")

	if (num < 1024){
		return df.format(num)+" bytes"
	} else if (num < 1048576){
		return df.format(num/1024.0)+" KB"
	} else {
		return df.format(num/1048576.0)+" MB"
	}

}

static String round(num){
	def df = new DecimalFormat("#0.0")
	return df.format(num.toString().toDouble())
}





@Field static final String sNL=(String)null
@Field static final String sSNULL='null'
@Field static final String sBOOLN='boolean'
@Field static final String sBLK=''
@Field static final String sCOMMA=','
@Field static final String sSPC=' '

@Field static final String sNM='name'
@Field static final String sREQ='required'
@Field static final String sTYPE='type'
@Field static final String sTIT='title'
@Field static final String sVAL='value'
@Field static final String sERROR='error'
@Field static final String sWARN='warn'
@Field static final String sTRC='trace'
@Field static final String sDBG='debug'
@Field static final String sON='on'
@Field static final String sOFF='off'
@Field static final String sSWITCH='switch'


@Field static final Integer iZ=0
@Field static final Integer i1=1
@Field static final Integer i2=2
@Field static final Integer i3=3
@Field static final Integer i4=4
@Field static final Integer i5=5
@Field static final Integer i6=6
@Field static final Integer i7=7
@Field static final Integer i8=8
@Field static final Integer i9=9
@Field static final Integer i10=10
@Field static final Integer i12=12
@Field static final Integer i20=20

@Field static final Long lZ=0L

@Field static final Integer iN1=-1
@Field static final Integer iN2=-2

private static TimeZone mTZ(){ return TimeZone.getDefault() } // (TimeZone)location.timeZone

import java.text.SimpleDateFormat

@CompileStatic
static String formatTime(Date t){
	String format=  "yyyy-MM-dd HH:mm:ss.SSS"
	SimpleDateFormat formatter=new SimpleDateFormat(format)
	formatter.setTimeZone(mTZ())
	return formatter.format(t)
}

/**							**/
/** DEBUG FUNCTIONS			**/
/**							**/

private void myDetail(Map r9,String msg,Integer shift=iN1){ Map a=log(msg,r9,shift,null,sWARN,true,false) }

@Field static final String sTMSTMP='timestamp'
@Field static final String sDBGLVL='debugLevel'
@Field static final String sLOGS='logs'
@Field static final String sTIMER='timer'

@Field static final String sA='a'
@Field static final String sB='b'
@Field static final String sC='c'
@Field static final String sE='e'
@Field static final String sM='m'
@Field static final String sN='n'
@Field static final String sO='o'
@Field static final String sP='p'
@Field static final String sS='s'
@Field static final String sT='t'

@Field static final Double d1=1.0D

private Map log(message,Map r9,Integer shift=iN2,Exception err=null,String cmd=sNL,Boolean force=false,Boolean svLog=true){
	if(cmd==sTIMER){
		return [(sM):message.toString(),(sT):wnow(),(sS):shift,(sE):err]
	}
	String myMsg
	Exception merr=err
	Integer mshift=shift
	if(message instanceof Map){
		mshift=(Integer)message.s
		merr=(Exception)message.e
		myMsg=(String)message.m+" (${elapseT(lMt(message))}ms)".toString()
	}else myMsg=message.toString()
	String mcmd=cmd!=sNL ? cmd:sDBG

	Integer level = state[sDBGLVL] ? (Integer)state[sDBGLVL]:iZ
	//shift is
	// 0 initialize level,level set to 1
	// 1 start of routine,level up
	// -1 end of routine,level down
	// anything else: nothing happens
//	Integer maxLevel=4
	//level=r9[sDBGLVL] ? (Integer)r9[sDBGLVL]:iZ
	String ss='╔'
	String sb='║'
	String se='╚'
	String prefix=sb
	String prefix2=sb
//	String pad=sBLK //"░"
	switch(mshift){
		case iZ:
			level=iZ
		case i1:
			level+=i1
			prefix=se
			prefix2=ss
//			pad="═"
			break
		case iN1:
			level-=i1
//			pad='═'
			prefix=ss
			prefix2=se
			break
	}
	if(level>iZ){
		prefix=prefix.padLeft(level+(mshift==iN1 ? i1:iZ),sb)
		prefix2=prefix2.padLeft(level+(mshift==iN1 ? i1:iZ),sb)
	}
	//r9[sDBGLVL]=level
	state[sDBGLVL]=level

	Boolean hasErr=(merr!=null && !!merr)
	myMsg=myMsg.replaceAll(/(\r\n|\r|\n|\\r\\n|\\r|\\n)+/,"\r")
	if(myMsg.size()>1024){
		myMsg=myMsg[iZ..1023]+'...[TRUNCATED]'
	}
	List<String> msgs=!hasErr ? myMsg.tokenize("\r"):[myMsg]
	if(r9 && r9[sTMSTMP]){
		if(svLog && r9[sLOGS] instanceof List){
			for(String msg in msgs){
				Boolean a=((List)r9[sLOGS]).push([(sO):elapseT(lMs(r9,sTMSTMP)),(sP):prefix2,(sM):msg+(hasErr ? " $merr".toString():sBLK),(sC):mcmd])
			}
		}
	}
	String myPad=sSPC
	if(hasErr) myMsg+="$merr".toString()
	if((mcmd in [sERROR,sWARN]) || hasErr || force || !svLog || !r9 || r9Is(r9,'logsToHE') || isEric(r9))log."$mcmd" myPad+prefix+sSPC+myMsg
	//}else log."$mcmd" myMsg
	return [:]
}

private void info(message,Map r9,Integer shift=iN2,Exception err=null){ Map a=log(message,r9,shift,err,'info')}
private void trace(message,Map r9,Integer shift=iN2,Exception err=null){ Map a=log(message,r9,shift,err,sTRC)}
private void debug(message,Map r9,Integer shift=iN2,Exception err=null){ Map a=log(message,r9,shift,err,sDBG)}
private void warn(message,Map r9,Integer shift=iN2,Exception err=null){ Map a=log(message,r9,shift,err,sWARN)}
private void error(message,Map r9,Integer shift=iN2,Exception err=null){
	String aa=sNL
	String bb=sNL
	try{
		if(err){
			aa=getExceptionMessageWithLine(err)
			bb=getStackTrace(err)
		}
		Map a=log(message,r9,shift,err,sERROR)
	}catch(ignored){}
	if(aa||bb)log.error "LTS exception: "+aa+" \n"+bb
}
//error "object: ${describeObject(e)}",r9

@CompileStatic
private static Long lMs(Map m,String v){ (Long)m[v] }
@CompileStatic
private static Long lMt(Map m){ (Long)m[sT] }

private Map timer(String message,Map r9,Integer shift=iN2,err=null){ log(message,r9,shift,err,sTIMER)}

@Field static final String sCLR4D9	= '#2784D9'
@Field static final String sCLRRED	= 'red'
@Field static final String sCLRRED2	= '#cc2d3b'
@Field static final String sCLRGRY	= 'gray'
@Field static final String sCLRGRN	= 'green'
@Field static final String sCLRGRN2	= '#43d843'
@Field static final String sCLRORG	= 'orange'
@Field static final String sLINEBR	= '<br>'
static String span(String str,String clr=sNL,String sz=sNL,Boolean bld=false,Boolean br=false){
	return str ? "<span ${(clr || sz || bld) ? "style='${clr ? "color: ${clr};":sBLK}${sz ? "font-size: ${sz};":sBLK}${bld ? "font-weight: bold;":sBLK}'":sBLK}>${str}</span>${br ? sLINEBR:sBLK}": sBLK
}

@CompileStatic
private Long elapseT(Long t,Long n=wnow()){ return Math.round(d1*n-t) }

@Field static final String sSP='<span>'
@Field static final String sSSP='</span>'
@Field static final String sSPCSB7='      │'
@Field static final String sSPCSB6='     │'
@Field static final String sSPCS6 ='      '
@Field static final String sSPCS5 ='     '
@Field static final String sSPCST='┌─ '
@Field static final String sSPCSM='├─ '
@Field static final String sSPCSE='└─ '
@Field static final String sNWL='\n'
@Field static final String sDBNL='\n\n • '
@Field static final String sSPORNG="<span style='color:orange'>"

@CompileStatic
static String doLineStrt(Integer level,List<Boolean>newLevel){
	String lineStrt=sNWL
	Boolean dB=false
	for(Integer i=iZ;i<level;i++){
		if(i+i1<level){
			if(!newLevel[i]) {
				if(!dB){ lineStrt+=sSPCSB7; dB=true }
				else lineStrt+=sSPCSB6
			}else lineStrt+= !dB ? sSPCS6:sSPCS5
		}else lineStrt+= !dB ? sSPCS6:sSPCS5
	}
	return lineStrt
}

@CompileStatic
static String dumpListDesc(List data,final Integer level,List<Boolean> lastLevel,final String listLabel,Boolean html=false,Boolean reorder=true){
	String str=sBLK
	Integer cnt=i1
	List<Boolean> newLevel=lastLevel

	final List list1=data?.collect{it}
	final Integer sz=list1.size()
	for(Object par in list1){
		final String lbl=listLabel+"[${cnt-i1}]".toString()
		if(par instanceof Map){
			Map newmap=[:]
			newmap[lbl]=(Map)par
			Boolean t1=cnt==sz
			newLevel[level]=t1
			str+=dumpMapDesc(newmap,level,newLevel,cnt,sz,!t1,html,reorder)
		}else if(par instanceof List || par instanceof ArrayList){
			Map newmap=[:]
			newmap[lbl]=par
			Boolean t1=cnt==sz
			newLevel[level]=t1
			str+=dumpMapDesc(newmap,level,newLevel,cnt,sz,!t1,html,reorder)
		}else{
			String lineStrt=doLineStrt(level,lastLevel)
			lineStrt+=cnt==i1 && sz>i1 ? sSPCST:(cnt<sz ? sSPCSM:sSPCSE)
			if(html)str+=sSP
			str+=lineStrt+lbl+": ${par} (${objType(par)})".toString()
			if(html)str+=sSSP
		}
		cnt+=i1
	}
	return str
}

@CompileStatic
static String dumpMapDesc(Map data,final Integer level,List<Boolean> lastLevel,Integer listCnt=null,Integer listSz=null,Boolean listCall=false,Boolean html=false,Boolean reorder=true){
	String str=sBLK
	Integer cnt=i1
	final Integer sz=data?.size()
	Map svMap=[:]
	Map svLMap=[:]
	Map newMap=[:]
	for(par in data){
		final String k=(String)par.key
		final def v=par.value
		if(reorder && v instanceof Map){
			svMap+=[(k): v]
		}else if(reorder && (v instanceof List || v instanceof ArrayList)){
			svLMap+=[(k): v]
		}else newMap+=[(k):v]
	}
	newMap+=svMap+svLMap
	final Integer lvlpls=level+i1
	for(par in newMap){
		String lineStrt
		List<Boolean> newLevel=lastLevel
		final Boolean thisIsLast=cnt==sz && !listCall
		if(level>iZ)newLevel[(level-i1)]=thisIsLast
		Boolean theLast=thisIsLast
		if(level==iZ)lineStrt=sDBNL
		else{
			theLast=theLast && thisIsLast
			lineStrt=doLineStrt(level,newLevel)
			if(listSz && listCnt && listCall)lineStrt+=listCnt==i1 && listSz>i1 ? sSPCST:(listCnt<listSz ? sSPCSM:sSPCSE)
			else lineStrt+=((cnt<sz || listCall) && !thisIsLast) ? sSPCSM:sSPCSE
		}
		final String k=(String)par.key
		final def v=par.value
		String objType=objType(v)
		if(v instanceof Map){
			if(html)str+=sSP
			str+=lineStrt+"${k}: (${objType})".toString()
			if(html)str+=sSSP
			newLevel[lvlpls]=theLast
			str+=dumpMapDesc((Map)v,lvlpls,newLevel,null,null,false,html,reorder)
		}
		else if(v instanceof List || v instanceof ArrayList){
			if(html)str+=sSP
			str+=lineStrt+"${k}: [${objType}]".toString()
			if(html)str+=sSSP
			newLevel[lvlpls]=theLast
			str+=dumpListDesc((List)v,lvlpls,newLevel,sBLK,html,reorder)
		}
		else{
			if(html)str+=sSP
			str+=lineStrt+"${k}: (${v}) (${objType})".toString()
			if(html)str+=sSSP
		}
		cnt+=i1
	}
	return str
}

@CompileStatic
static String objType(obj){ return sSPORNG+myObj(obj)+sSSP }

@CompileStatic
static String getMapDescStr(Map data,Boolean reorder=true){
	List<Boolean> lastLevel=[true]
	String str=dumpMapDesc(data,iZ,lastLevel,null,null,false,true,reorder)
	return str!=sBLK ? str:'No Data was returned'
}


private static String sectionTitleStr(String title)	{ return '<h3>'+title+'</h3>' }
private static String inputTitleStr(String title)	{ return '<u>'+title+'</u>' }
//private static String pageTitleStr(String title)	{ return '<h1>'+title+'</h1>' }
//private static String paraTitleStr(String title)	{ return '<b>'+title+'</b>' }

@Field static final String sGITP='https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/'
private static String gimg(String imgSrc){ return sGITP+imgSrc }

@CompileStatic
private static String imgTitle(String imgSrc,String titleStr,String color=sNL,Integer imgWidth=30,Integer imgHeight=iZ){
	String imgStyle=sBLK
	String myImgSrc=gimg(imgSrc)
	imgStyle+=imgWidth>iZ ? 'width: '+imgWidth.toString()+'px !important;':sBLK
	imgStyle+=imgHeight>iZ ? imgWidth!=iZ ? sSPC:sBLK+'height:'+imgHeight.toString()+'px !important;':sBLK
	if(color!=sNL) return """<div style="color: ${color}; font-weight:bold;"><img style="${imgStyle}" src="${myImgSrc}"> ${titleStr}</img></div>""".toString()
	else return """<img style="${imgStyle}" src="${myImgSrc}"> ${titleStr}</img>""".toString()
}

static String myObj(obj){
	if(obj instanceof String)return 'String'
	else if(obj instanceof Map)return 'Map'
	else if(obj instanceof List)return 'List'
	else if(obj instanceof ArrayList)return 'ArrayList'
	else if(obj instanceof BigInteger)return 'BigInt'
	else if(obj instanceof Long)return 'Long'
	else if(obj instanceof Integer)return 'Int'
	else if(obj instanceof Boolean)return 'Bool'
	else if(obj instanceof BigDecimal)return 'BigDec'
	else if(obj instanceof Double)return 'Double'
	else if(obj instanceof Float)return 'Float'
	else if(obj instanceof Byte)return 'Byte'
	else if(obj instanceof com.hubitat.app.DeviceWrapper)return 'Device'
	else return 'unknown'
}


@Field volatile static Map<String,Long> lockTimesVFLD=[:]
@Field volatile static Map<String,String> lockHolderVFLD=[:]

@CompileStatic
void getTheLock(String qname,String meth=sNL,Boolean longWait=false){
	Boolean a=getTheLockW(qname,meth,longWait)
}

@Field static final Long lTHOUS=1000L

@CompileStatic
Boolean getTheLockW(String qname,String meth=sNL,Boolean longWait=false){
	Long waitT=longWait? lTHOUS:10L
	Boolean wait=false
	Integer semaNum=semaNum(qname)
	String semaSNum=semaNum.toString()
	Semaphore sema=sema(semaNum)
	while(!(sema.tryAcquire())){
		// did not get lock
		Long t=lockTimesVFLD[semaSNum]
		if(t==null){
			t=wnow()
			lockTimesVFLD[semaSNum]=t
			lockTimesVFLD=lockTimesVFLD
		}
		if(eric())warn "waiting for ${qname} ${semaSNum} lock access, $meth, long: $longWait, holder: ${(String)lockHolderVFLD[semaSNum]}",null
		wpauseExecution(waitT)
		wait=true
		if(elapseT(t)>30000L){
			releaseTheLock(qname)
			if(eric())warn "overriding lock $meth",null
		}
	}
	lockTimesVFLD[semaSNum]=wnow()
	lockTimesVFLD=lockTimesVFLD
	lockHolderVFLD[semaSNum]=sAppId()+sSPC+meth
	lockHolderVFLD=lockHolderVFLD
	return wait
}

@CompileStatic
static void releaseTheLock(String qname){
	Integer semaNum=semaNum(qname)
	String semaSNum=semaNum.toString()
	Semaphore sema=sema(semaNum)
	lockTimesVFLD[semaSNum]=(Long)null
	lockTimesVFLD=lockTimesVFLD
//	lockHolderVFLD[semaSNum]=sNL
//	lockHolderVFLD=lockHolderVFLD
	sema.release()
}

void clearSema(){
	String pNm=sAppId()
	getTheLock(pNm,'updated')
	theSemaphoresVFLD[pNm]=lZ
	theSemaphoresVFLD=theSemaphoresVFLD
	theQueuesVFLD[pNm]=[]
	theQueuesVFLD=theQueuesVFLD // forces volatile cache flush
	releaseTheLock(pNm)
}

@Field static Semaphore theLock0FLD=new Semaphore(1)

@Field static final Integer iStripes=1
@CompileStatic
static Integer semaNum(String name){
	if(name.isNumber())return name.toInteger()%iStripes
	Integer hash=smear(name.hashCode())
	return Math.abs(hash)%iStripes
}

@CompileStatic
static Semaphore sema(Integer snum){
	switch(snum) {
		case 0: return theLock0FLD
		default: //log.error "bad hash result $snum"
			return null
	}
}

private static Integer smear(Integer hashC){
	Integer hashCode=hashC
	hashCode ^= (hashCode >>> i20) ^ (hashCode >>> i12)
	return hashCode ^ (hashCode >>> i7) ^ (hashCode >>> i4)
}


@Field volatile static Map<String,List<Map>> theQueuesVFLD=[:]
@Field volatile static Map<String,Long> theSemaphoresVFLD=[:]

// This can queue event
@CompileStatic
private Map queueSemaphore(Map event){
	Long tt1=wnow()
	Long startTime=tt1
	Long r_semaphore=lZ
	Long semaphoreDelay=lZ
	String semaphoreName=sNL
	Boolean didQ=false
	Boolean waited

	String mSmaNm=sAppId()
	waited=getTheLockW(mSmaNm,'queue')
	tt1=wnow()

	Long lastSemaphore
	Boolean clrC=false
	Integer qsize=iZ
	while(true){
		Long t0=theSemaphoresVFLD[mSmaNm]
		Long tt0=t0!=null ? t0:lZ
		lastSemaphore=tt0
		if(lastSemaphore==lZ || tt1-lastSemaphore>100000L){
			theSemaphoresVFLD[mSmaNm]=tt1
			theSemaphoresVFLD=theSemaphoresVFLD
			semaphoreName=mSmaNm
			semaphoreDelay=waited ? tt1-startTime:lZ
			r_semaphore=tt1
			break
		}

		if(event!=null){
			Map mEvt=event
			List<Map> evtQ=theQueuesVFLD[mSmaNm]
			evtQ=evtQ!=null ? evtQ:(List<Map>)[]
			qsize=evtQ.size()
			if(qsize>i12)clrC=true
			else{
				Boolean a=evtQ.push(mEvt)
				theQueuesVFLD[mSmaNm]=evtQ
				theQueuesVFLD=theQueuesVFLD
				didQ=true
			}
		}
		break
	}
	releaseTheLock(mSmaNm)
	if(clrC){
		error "large queue size ${qsize} clearing",null
		//clear1(true,true,true,true)
	}
	return [
		semaphore:r_semaphore,
		semaphoreName:semaphoreName,
		semaphoreDelay:semaphoreDelay,
		waited:waited,
		exitOut:didQ
	]

}

private Long wnow(){ return (Long)now() }
private String sAppId(){ return ((Long)app.id).toString() }
private void wpauseExecution(Long t){ pauseExecution(t) }

private gtSetting(String nm){ return settings."${nm}" }
private gtSt(String nm){ return state."${nm}" }
private gtAS(String nm){ return atomicState."${nm}" }

/** assign to state  */
private void assignSt(String nm,v){ state."${nm}"=v }

/** assign to atomicState  */
private void assignAS(String nm,v){ atomicState."${nm}"=v }
private Map gtState(){ return state }

private gtLocation(){ return location }

@CompileStatic
static Boolean eric(){ return false }
