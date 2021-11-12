/*
 *  webCoRE - Community's own Rule Engine - Web Edition for HE
 *
 *  Copyright 2016 Adrian Caramaliu <ady624("at" sign goes here)gmail.com>
 *
 *  webCoRE Piston
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not see <http://www.gnu.org/licenses/>.
 *
 * Last update November 12, 2021 for Hubitat
*/

//file:noinspection GroovySillyAssignment
//file:noinspection GrDeprecatedAPIUsage
//file:noinspection GroovyDoubleNegation
//file:noinspection GroovyUnusedAssignment
//file:noinspection unused

@Field static final String sVER='v0.3.113.20210203'
@Field static final String sHVER='v0.3.113.20211005_HE'

static String version(){ return sVER }
static String HEversion(){ return sHVER }

/** webCoRE DEFINITION	**/

static String handle(){ return 'webCoRE' }

import groovy.json.*
import hubitat.helper.RMUtils
import groovy.transform.Field

import java.security.MessageDigest
import java.text.SimpleDateFormat
import java.util.concurrent.Semaphore

definition(
	name:handle()+' Piston',
	namespace:'ady624',
	author:'Adrian Caramaliu',
	description:'Do not install this directly, use webCoRE instead',
	category:'Convenience',
	parent:'ady624:'+handle(),
	iconUrl:'https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/app-CoRE.png',
	iconX2Url:'https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/app-CoRE@2x.png',
	iconX3Url:'https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/app-CoRE@3x.png',
	importUrl:'https://raw.githubusercontent.com/imnotbob/webCoRE/hubitat-patches/smartapps/ady624/webcore-piston.src/webcore-piston.groovy'
)

preferences{
	page((sNM):'pageMain')
	page((sNM):'pageRun')
	page((sNM):'pageClear')
	page((sNM):'pageClearAll')
	page((sNM):'pageDumpPiston')
	page((sNM):'pageDumpPiston1')
}

static Boolean eric(){ return false }
static Boolean eric1(){ return false }

//#include ady624.webCoRElib1

@Field static final String sNULL=(String)null
@Field static final String sSNULL='null'
@Field static final String sBOOLN='boolean'
@Field static final String sLONG='long'
@Field static final String sSTR='string'
@Field static final String sINT='integer'
@Field static final String sDEC='decimal'
@Field static final String sDYN='dynamic'
@Field static final String sDTIME='datetime'
@Field static final String sTRUE='true'
@Field static final String sFALSE='false'
@Field static final String sTIME='time'
@Field static final String sDATE='date'
@Field static final String sDEV='device'
@Field static final String sDBL='double'
@Field static final String sNUMBER='number'
@Field static final String sFLOAT='float'
@Field static final String sVARIABLE='variable'
@Field static final String sMODE='mode'
@Field static final String sNM='name'
@Field static final String sTYPE='type'
@Field static final String sTIT='title'
@Field static final String sVAL='value'
@Field static final String sERROR='error'
@Field static final String sWARN='warn'
@Field static final String sDBG='debug'
@Field static final String sON='on'
@Field static final String sOFF='off'
@Field static final String sSWITCH='switch'
@Field static final String sTRIG='trigger'
@Field static final String sCONDITION='condition'
@Field static final String sDURATION='duration'
@Field static final String sDLLRINDX='$index'
@Field static final String sDLLRDEVS='$devices'
@Field static final String sDLLRDEVICE='$device'
@Field static final String sTEXT='text'
@Field static final String sENUM='enum'
@Field static final String sTHREAX='threeAxis'
@Field static final String sBLK=''
@Field static final String sCOMMA=','
@Field static final String sSPC=' '
@Field static final String sA='a'
@Field static final String sV='v'
@Field static final String sVT='vt'
@Field static final String sP='p'
@Field static final String sL='l'
@Field static final String sO='o'
@Field static final String sM='m'
@Field static final String sS='s'
@Field static final String sC='c'
@Field static final String sH='h'
@Field static final String sR='r'
@Field static final String sB='b'
@Field static final String sI='i'
@Field static final String sT='t'
@Field static final String sE='e'
@Field static final String sD='d'
@Field static final String sN='n'
@Field static final String sX='x'
@Field static final String sLB='['
@Field static final String sRB=']'
@Field static final String sAT='@'
@Field static final String sDLR='$'
@Field static final String sHSMSTS='hsmStatus'
@Field static final String sPEVDATE='$previousEventDate'
@Field static final String sPEVDELAY='$previousEventDelay'
@Field static final String sPEVDEV='$previousEventDevice'
@Field static final String sPEVDEVINDX='$previousEventDeviceIndex'
@Field static final String sPEVATTR='$previousEventAttribute'
@Field static final String sPEVDESC='$previousEventDescription'
@Field static final String sPEVVALUE='$previousEventValue'
@Field static final String sPEVUNIT='$previousEventUnit'
@Field static final String sPEVPHYS='$previousEventDevicePhysical'
@Field static final String sCURDATE='$currentEventDate'
@Field static final String sCURDELAY='$currentEventDelay'
@Field static final String sCURDEV='$currentEventDevice'
@Field static final String sCURDEVINDX='$currentEventDeviceIndex'
@Field static final String sCURATTR='$currentEventAttribute'
@Field static final String sCURDESC='$currentEventDescription'
@Field static final String sCURVALUE='$currentEventValue'
@Field static final String sCURUNIT='$currentEventUnit'
@Field static final String sCURPHYS='$currentEventDevicePhysical'
@Field static final String sAPPJSON='application/json'
@Field static final String sAPPFORM='application/x-www-form-urlencoded'
@Field static final String sASYNCREP='wc_async_reply'
@Field static final String sGET='GET'
@Field static final String sDELETE='DELETE'
@Field static final String sHEAD='HEAD'
@Field static final String sLVL='level'
@Field static final String sSTLVL='setLevel'
@Field static final String sIFLVL='infraredLevel'
@Field static final String sSTIFLVL='setInfraredLevel'
@Field static final String sSATUR='saturation'
@Field static final String sSSATUR='setSaturation'
@Field static final String sHUE='hue'
@Field static final String sSHUE='setHue'
@Field static final String sSCLR='setColor'
@Field static final String sCLRTEMP='colorTemperature'
@Field static final String sSCLRTEMP='setColorTemperature'
@Field static final String sZEROS='000000'
@Field static final String sHTTPR='httpRequest'
@Field static final String sSENDE='sendEmail'
@Field static final String sANY='any'
@Field static final String sALL='all'
@Field static final String sAND='and'
@Field static final String sOR='or'
@Field static final String sIF='if'
@Field static final String sWHILE='while'
@Field static final String sREPEAT='repeat'
@Field static final String sFOR='for'
@Field static final String sEACH='each'
@Field static final String sACTION='action'
@Field static final String sEVERY='every'
@Field static final String sRESTRIC='restriction'
@Field static final String sGROUP='group'
@Field static final String sDO='do'
@Field static final String sEVENT='event'
@Field static final String sEXIT='exit'
@Field static final String sBREAK='break'
@Field static final String sEXPR='expression'
@Field static final String sOPER='operator'
@Field static final String sOPERAND='operand'
@Field static final String sFUNC='function'
@Field static final String sONE='1'
@Field static final String sPLUS='+'
@Field static final String sMINUS='-'
@Field static final String sDOT='.'
@Field static final String sORIENT='orientation'
@Field static final String sAXISX='axisX'
@Field static final String sAXISY='axisY'
@Field static final String sAXISZ='axisZ'
@Field static final String sEXPECTING='Expecting '
@Field static final String sINT32='int32'
@Field static final String sINT64='int64'
@Field static final String sBOOL='bool'
@Field static final String sPHONE='phone'
@Field static final String sURI='uri'
@Field static final String sSTOREM='storeMedia'
@Field static final String sIFTTM='iftttMaker'
@Field static final String sDOLARGS='$args'
@Field static final String sDOLJSON='$json'
@Field static final String sDOLRESP='$response'
@Field static final String sEND='end'
@Field static final String sHTTPCONTENT='$httpContentType'
@Field static final String sHTTPSTSCODE='$httpStatusCode'
@Field static final String sHTTPSTSOK='$httpStatusOk'
@Field static final String sIFTTTSTSCODE='$iftttStatusCode'
@Field static final String sIFTTTSTSOK='$iftttStatusOk'
@Field static final String sTSLF='theSerialLockFLD'
@Field static final String sTCCC='theCCC'
@Field static final String sTCL='cacheLock'
@Field static final String sTGBL='theGlobal'
@Field static final String sLCK1='lockOrQueue1'
@Field static final String sLCK2='lockOrQueue2'
@Field static final String sGETTRTD='getTempRtd'
@Field static final String sHNDLEVT='handleEvents'
@Field static final String sVALUEN='(value1, value2,..., valueN)'
@Field static final String sDATTRH='([device:attribute])'
@Field static final String sDATTRHT='([device:attribute] [,.., [device:attribute]],threshold)'
@Field static final String sMULP='*'
@Field static final String sQM='?'
@Field static final String sCOLON=':'
@Field static final String sPWR='**'
@Field static final String sAMP='&'
@Field static final String sBOR='|'
@Field static final String sBXOR='^'
@Field static final String sBNOT='~'
@Field static final String sBNAND='~&'
@Field static final String sBNOR='~|'
@Field static final String sBNXOR='~^'
@Field static final String sLTH='<'
@Field static final String sGTH='>'
@Field static final String sLTHE='<='
@Field static final String sGTHE='>='
@Field static final String sEQ='=='
@Field static final String sNEQ='!='
@Field static final String sNEQA='<>'
@Field static final String sMOD='%'
@Field static final String sMOD1='\\'
@Field static final String sSBL='<<'
@Field static final String sSBR='>>'
@Field static final String sNEG='!'
@Field static final String sDNEG='!!'
@Field static final String sDIV='/'
@Field static final String sLAND='&&'
@Field static final String sLNAND='!&'
@Field static final String sLOR='||'
@Field static final String sLNOR='!|'
@Field static final String sLXOR='^^'
@Field static final String sLNXOR='!^'

@Field static final Long lZERO=0L
@Field static final Long lTHOUS=1000L
@Field static final Long lMSDAY=86400000L
@Field static final Double dZERO=0.0D
@Field static final Double dONE=1.0D
@Field static final Double dTHOUS=1000.0D
@Field static final Double dSIXTY=60.0D
@Field static final Double dSECHR=3600.0D
@Field static final Double dMSECHR=3600000.0D
@Field static final Double dMSMINT=60000.0D
@Field static final Double dMSDAY=86400000.0D

/** CONFIGURATION PAGES	**/

def pageMain(){
	return dynamicPage((sNM):'pageMain',(sTIT):sBLK,install:true,uninstall:(Integer)state.build!=null){
		if(parent==null || !(Boolean)parent.isInstalled()){
			section(){
				paragraph 'Sorry you cannot install a piston directly from the HE console; please use the webCoRE dashboard (dashboard.webcore.co) instead.'
			}
			section(sectionTitleStr('Installing webCoRE')){
				paragraph 'If you are trying to install webCoRE please go back one step and choose webCoRE, not webCoRE Piston. You can also visit wiki.webcore.co for more information on how to install and use webCoRE'
				if(parent!=null){
					String t0=(String)parent.getWikiUrl()
					href sBLK,(sTIT):imgTitle('app-CoRE.png',inputTitleStr('More information')),description:t0,style:'external',url:t0,required:false
				}
			}
		}else{
			section(sectionTitleStr('General')){
				label([(sNM):sNM,(sTIT):'Name',required:true,state:(name ? 'complete':sNULL),defaultValue:(String)parent.generatePistonName(),submitOnChange:true])
			}

			section(sectionTitleStr('Dashboard')){
				String dashboardUrl=(String)parent.getDashboardUrl()
				if(dashboardUrl!=sNULL){
					dashboardUrl=dashboardUrl+'piston/'+hashId(app.id)
					href sBLK,(sTIT):imgTitle('dashboard.png',inputTitleStr('View piston in dashboard')),style:'external',url:dashboardUrl,required:false
				}else paragraph 'Sorry your webCoRE dashboard does not seem to be enabled; please go to the parent app and enable the dashboard if needed.'
			}

			section(sectionTitleStr('Application Info')){
				LinkedHashMap<String,Object> rtD=getTemporaryRunTimeData()
				if(!(Boolean)rtD.enabled)paragraph 'Piston is disabled by webCoRE'
				if(!(Boolean)rtD.active)paragraph 'Piston is paused'
				if((String)rtD.bin!=sNULL){
					paragraph 'Automatic backup bin code: '+(String)rtD.bin
				}
				paragraph 'Version: '+sVER
				paragraph 'VersionH: '+sHVER
				paragraph 'Memory Usage: '+mem()
				paragraph 'RunTime History: '+runTimeHis(rtD)
				rtD=null
			}

			section(sectionTitleStr('Recovery')){
				href 'pageRun',(sTIT):'Test run this piston'
				href 'pageClear',(sTIT):'Clear logs',description:'This will remove logs but no variables'
				href 'pageClearAll',(sTIT):'Clear all data',description:'This will reset all data stored in local variables'
			}

			section(){
				input 'dev',"capability.*",(sTIT):'Devices',description:'Piston devices',multiple:true
				input 'logging',sENUM,(sTIT):'Logging Level',options:["0":"None","1":"Minimal","2":"Medium","3":"Full"],description:'Piston logging',defaultValue:state.logging? state.logging.toString() : "0"
				input 'logsToHE',sBOOL,(sTIT):'Piston logs are also displayed in HE console logs?',description:"Logs are available in webCoRE console; also display in HE console 'Logs'?",defaultValue:false
				input 'maxStats',sNUMBER,(sTIT):'Max number of timing history stats',description:'Max number of stats',range:'2..300',defaultValue:50
				input 'maxLogs',sNUMBER,(sTIT):'Max number of history logs',description:'Max number of logs',range:'0..300',defaultValue:50
			}
			if(eric() || settings.logging?.toInteger()>2){
				section('Debug'){
					href 'pageDumpPiston',(sTIT):'Dump piston structure',description:sBLK
					href 'pageDumpPiston1',(sTIT):'Dump cached piston structure',description:sBLK
				}
			}
		}
	}
}

def pageRun(){
	test()
	return dynamicPage((sNM):'pageRun',(sTIT):sBLK,uninstall:false){
		section('Run'){
			paragraph 'Piston tested'
			Map t0=(Map)parent.getWCendpoints()
			String t1="/execute/${hashId(app.id)}?access_token=${t0.at}".toString()
			paragraph "Cloud Execute endpoint ${t0.ep}${t1}".toString()
			paragraph "Local Execute endpoint ${t0.epl}${t1}".toString()
		}
	}
}

def pageClear(){
	clear1(false,true,true,false)
	return dynamicPage((sNM):'pageClear',(sTIT):sBLK,uninstall:false){
		section('Clear'){
			paragraph 'All non-essential data has been cleared.'
		}
	}
}

void clear1(Boolean ccache=false,Boolean some=true,Boolean most=false,Boolean all=false,Boolean reset=false){
	String meth='clear1'
	if(some)state.logs=[]
	if(most){ state.trace=[:];state.stats=[:] }
	if(reset){app.clearSetting('maxLogs'); app.clearSetting('maxStats')}
	if(all){
		meth +=' all'
		LinkedHashMap<String,Object> tRtData=getTemporaryRunTimeData()
		Boolean act=(Boolean)tRtData.active
		Boolean dis=!(Boolean)tRtData.enabled
		tRtData=null
		state.cache=[:]
		state.vars=[:]
		state.store=[:]
		state.pauses=lZERO
		clearMyCache(meth)

		String semaName=app.id.toString()
		getTheLock(semaName,meth)
		theSemaphoresVFLD[semaName]=lZERO
		theSemaphoresVFLD=theSemaphoresVFLD
		theQueuesVFLD[semaName]=[]
		theQueuesVFLD=theQueuesVFLD // this forces volatile cache flush
		releaseTheLock(semaName)

		if(act && !dis){
			tRtData=getTemporaryRunTimeData()
			LinkedHashMap rtD=getRunTimeData(tRtData,null,true,true) //reinitializes cache variables; caches piston
			rtD=null
			tRtData=null
		}
	}
	clearMyCache(meth)
	if(ccache){
		clearMyPiston(meth)
	}
}

def pageClearAll(){
	clear1(true,true,true,true)
	return dynamicPage((sNM):'pageClearAll',(sTIT):sBLK,uninstall:false){
		section('Clear All'){
			paragraph 'All local data has been cleared.'
		}
	}
}

static String dumpListDesc(data,Integer level,List<Boolean> lastLevel,String listLabel,Boolean html=false){
	String str=sBLK
	Integer cnt=1
	List<Boolean> newLevel=lastLevel

	List list1=data?.collect{it}
	Integer sz=list1.size()
	list1?.each{ par ->
		Integer t0=cnt-1
		String myStr="${listLabel}[${t0}]".toString()
		if(par instanceof Map){
			Map newmap=[:]
			newmap[myStr]=(Map)par
			Boolean t1= cnt==sz
			newLevel[level]=t1
			str += dumpMapDesc(newmap,level,newLevel,!t1,html)
		}else if(par instanceof List || par instanceof ArrayList){
			Map newmap=[:]
			newmap[myStr]=par
			Boolean t1= cnt==sz
			newLevel[level]=t1
			str += dumpMapDesc(newmap,level,newLevel,!t1,html)
		}else{
			String lineStrt='\n'
			for(Integer i=0; i<level; i++){
				lineStrt += (i+1<level)? (!lastLevel[i] ? '     │' : '      '):'      '
			}
			lineStrt += (cnt==1 && sz>1)? '┌─ ':(cnt<sz ? '├─ ' : '└─ ')
			if(html)str += '<span>'
			str += "${lineStrt}${listLabel}[${t0}]: ${par} (${getObjType(par)})".toString()
			if(html)str += '</span>'
		}
		cnt=cnt+1
	}
	return str
}

static String dumpMapDesc(data,Integer level,List<Boolean> lastLevel,Boolean listCall=false,Boolean html=false){
	String str=sBLK
	Integer cnt=1
	Integer sz=data?.size()
	data?.each{ par ->
		String lineStrt
		List<Boolean> newLevel=lastLevel
		Boolean thisIsLast= cnt==sz && !listCall
		if(level>0){
			newLevel[(level-1)]=thisIsLast
		}
		Boolean theLast=thisIsLast
		if(level==0){
			lineStrt='\n\n • '
		}else{
			theLast= theLast && thisIsLast
			lineStrt='\n'
			for(Integer i=0; i<level; i++){
				lineStrt += (i+1<level)? (!newLevel[i] ? '     │' : '      '):'      '
			}
			lineStrt += ((cnt<sz || listCall) && !thisIsLast) ? '├─ ' : '└─ '
		}
		String objType=getObjType(par.value)
		if(par.value instanceof Map){
			if(html)str += '<span>'
			str += "${lineStrt}${(String)par.key}: (${objType})".toString()
			if(html)str += '</span>'
			newLevel[(level+1)]=theLast
			str += dumpMapDesc((Map)par.value,level+1,newLevel,false,html)
		}
		else if(par.value instanceof List || par.value instanceof ArrayList){
			if(html)str += '<span>'
			str += "${lineStrt}${(String)par.key}: [${objType}]".toString()
			if(html)str += '</span>'
			newLevel[(level+1)]=theLast
			str += dumpListDesc(par.value,level+1,newLevel,sBLK,html)
		}
		else{
			if(html)str += '<span>'
			str += "${lineStrt}${(String)par.key}: (${par.value}) (${objType})".toString()
			if(html)str += '</span>'
		}
		cnt=cnt+1
	}
	return str
}

static String getObjType(obj){
	return "<span style='color:orange'>"+myObj(obj)+"</span>"
}

static String getMapDescStr(data){
	String str
	List<Boolean> lastLevel=[true]
	str=dumpMapDesc(data,0,lastLevel,false,true)
	return str!=sBLK ? str:'No Data was returned'
}

def pageDumpPiston1(){
	LinkedHashMap rtD=getRunTimeData()
	LinkedHashMap pis=recreatePiston(true,true)
	rtD.piston=pis
	subscribeAll(rtD,false)
	String message=getMapDescStr(rtD.piston)
	rtD=null
	pis=null
	return dynamicPage((sNM):'pageDumpPiston1',(sTIT):sBLK,uninstall:false){
		section('Cached Piston dump'){
			paragraph message
		}
	}
}

def pageDumpPiston(){
	LinkedHashMap rtD=getRunTimeData()
//	LinkedHashMap pis=recreatePiston(false,true)
	String message=getMapDescStr(rtD.piston)
	rtD=null
	return dynamicPage((sNM):'pageDumpPiston',(sTIT):sBLK,uninstall:false){
		section('Full Piston dump'){
			paragraph message
		}
	}
}

void installed(){
	if(app.id==null)return
	Long t=now()
	state.created=t
	state.modified=t
	state.build=0
	state.vars=(Map)state.vars ?: [:]
	state.subscriptions=(Map)state.subscriptions ?: [:]
	state.logging=0
	initialize()
}

void updated(){
	unsubscribe()
	initialize()
}

void uninstalled(){
	if(eric())log.debug 'uninstalled'
	if(!atomicState.pistonDeleted) Map a=deletePiston()
}

void initialize(){
	svSunTFLD=null
	String tt1=(String)settings.logging
	Integer tt2=(Integer)state.logging
	String tt3=tt2.toString()
	if(tt1==sNULL)Map a=setLoggingLevel(tt2 ? tt3:"0",false)
	else if(tt1!=tt3)Map a=setLoggingLevel(tt1,false)
	if((Boolean)state.active)Map b=resume()
	else{
		cleanState()
		clearMyCache('initialize')
	}
}

@Field static final List<String> clST=['hash','piston','cVersion','hVersion','disabled','logPExec','settings','svSunT','temp','debugLevel']

void cleanState(){
//cleanups between releases
	for(sph in ((Map<String,Object>)state).findAll{ (Boolean)((String)it.key).startsWith('sph')})state.remove(sph.key.toString())
	for(String foo in clST)state.remove(foo)
}

/** PUBLIC METHODS					**/

Boolean isInstalled(){
	return (Long)state.created!=null
}

Map get(Boolean minimal=false){ // minimal is backup
	LinkedHashMap rtD=getRunTimeData()
	Map rVal=[
		meta: [
			id: (String)rtD.id,
			author: (String)rtD.author,
			(sNM): (String)rtD.name,
			created: (Long)rtD.created,
			modified: (Long)rtD.modified,
			build: (Integer)rtD.build,
			bin: (String)rtD.bin,
			active: (Boolean)rtD.active,
			category: rtD.category
		],
		piston: (LinkedHashMap)rtD.piston
	]+(minimal ? [:]:[ // use state as getRunTimeData re-initializes these
		systemVars: getSystemVariablesAndValues(rtD),
		subscriptions: (Map)state.subscriptions,
		state: (Map)state.state,
		logging: state.logging!=null ? (Integer)state.logging:0,
		stats: (Map)state.stats,
		logs: (List)state.logs,
		trace: (Map)state.trace,
		localVars: (Map)state.vars,
		memory: mem(),
		lastExecuted: (Long)state.lastExecuted,
		nextSchedule: (Long)state.nextSchedule,
		schedules: (List)state.schedules
	])
	rtD=null
	return rVal
}

Map activity(lastLogTimestamp){
	Map t0=getCachedMaps('activity')
	if(t0==null)return [:]
	List logs=[]+(List)t0.logs
	Integer lsz=logs.size()
	Long llt=lastLogTimestamp!=null && lastLogTimestamp instanceof String && ((String)lastLogTimestamp).isLong()? (Long)((String)lastLogTimestamp).toLong():lZERO
	Integer index=(llt!=lZERO && lsz>0)? logs.findIndexOf{ it?.t==llt }:0
	index=index>0 ? index:(llt!=lZERO ? 0:lsz)
	Map rVal=[
		(sNM): (String)t0.name,
		state: (Map)t0.state,
		logs: index>0 ? logs[0..index-1]:[],
		trace: (Map)t0.trace,
		localVars: (Map)t0.vars,// not reporting global or system variable changes
		memory: (String)t0.mem,
		lastExecuted: (Long)t0.lastExecuted,
		nextSchedule: (Long)t0.nextSchedule,
		schedules: (List)t0.schedules,
		systemVars: (Map)t0.cachePersist
	]
	t0=null
	return rVal
}

Map curPState(){
	Map t0=getCachedMaps('curPState',true,false)
	if(t0==null)return null
	Map st=[:] + (Map)t0.state
	def a=st.remove('old')
	Map rVal=[
		(sA):(Boolean)t0.active,
		(sC):t0.category,
		(sT):(Long)t0.lastExecuted,
		(sN):(Long)t0.nextSchedule,
		z:(String)t0.pistonZ,
		(sS):st,
		heCached:(Boolean)t0.Cached ?: false
	]
	t0=null
	return rVal
}

Map clearLogs(){
	clear1()
	return [:]
}

static String decodeEmoji(String value){
	return value.replaceAll(/(\:%[0-9A-F]{2}%[0-9A-F]{2}%[0-9A-F]{2}%[0-9A-F]{2}\:)/,{ m -> URLDecoder.decode(m[0].substring(1,13),'UTF-8')})
}

@Field static Map<String,Map> thePistonCacheFLD=[:]

private void clearMyPiston(String meth=sNULL){
	String pisName=app.id.toString()
	if(pisName.length()==0)return
	Boolean cleared=false
	Map pData=(Map)thePistonCacheFLD[pisName]
	if(pData!=null){
		LinkedHashMap<String,Object> t0=(LinkedHashMap<String,Object>)pData.pis
		if(t0){
			List<String> data=t0.collect{ (String)it.key }
			def a
			for(item in data)a=t0.remove((String)item)
			thePistonCacheFLD[pisName].pis=null
			mb()
			cleared=true
		}
		pData=null
	}
	if(cleared && eric()){
		log.debug 'clearing piston-code-cache '+meth
		dumpPCsize()
	}
}

private LinkedHashMap recreatePiston(Boolean shorten=false,Boolean useCache=true){
	if(shorten && useCache){
		String pisName=app.id.toString()
		Map pData=(Map)thePistonCacheFLD[pisName]
		if(pData==null || pData.cnt==null){
			pData=[cnt:0,pis:null]
			thePistonCacheFLD[pisName]=pData
			mb()
		}
		//pData.cnt+=1
		if(pData.pis!=null)return (LinkedHashMap)(pData.pis+[cached:true])
	}

	if(eric())log.debug "recreating piston $shorten $useCache"
	String sdata=sBLK
	Integer i=0
	while(true){
		String s=(String)settings."chunk:$i"
		if(s!=null)sdata += s
		else break
		i++
	}
	if(sdata!=sBLK){
		def data=(LinkedHashMap)new JsonSlurper().parseText(decodeEmoji(new String(sdata.decodeBase64(),'UTF-8')))
		LinkedHashMap<String,Object> piston=[
			(sO): data.o ?: [:],
			(sR): data.r ?: [],
			rn: !!data.rn,
			rop: data.rop ?: sAND,
			(sS): data.s ?: [],
			(sV): data.v ?: [],
			z: data.z ?: sBLK
		]
		state.pistonZ=(String)piston.z
		clearMsetIds(piston)
		Integer a=msetIds(shorten,piston)
		return piston
	}
	return [:]
}

Map setup(LinkedHashMap data,Map<String,String>chunks){
	if(data==null){
		log.error 'setup: no data'
		return [:]
	}
	clearMyCache('setup')

	String semaName=app.id.toString()
	getTheLock(semaName,'setup')

	state.modified=now()
	state.build=(Integer)state.build!=null ? (Integer)state.build+1:1
	LinkedHashMap<String,Object> piston=[
		o: data.o ?: [:],
		r: data.r ?: [],
		rn: !!data.rn,
		rop: data.rop ?: sAND,
		s: data.s ?: [],
		(sV): data.v ?: [],
		z: data.z ?: sBLK
	]
	String meth='setup'
	clearMyPiston(meth)
	clearMsetIds(piston)
	Integer a=msetIds(false,piston)

	for(chunk in ((Map<String,Object>)settings).findAll{ (Boolean)((String)it.key).startsWith('chunk:') && !chunks[(String)it.key] }){
		app.clearSetting((String)chunk.key)
	}
	for(chunk in chunks)app.updateSetting((String)chunk.key,[(sTYPE):sTEXT,(sVAL):chunk.value])
	app.updateSetting('bin',[(sTYPE):sTEXT,(sVAL):(String)state.bin ?: sBLK])
	app.updateSetting('author',[(sTYPE):sTEXT,(sVAL):(String)state.author ?: sBLK])

	state.pep=piston.o?.pep ? true:false

	String lbl=(String)data.n
	if(lbl){
		state.svLabel=lbl
		atomicState.svLabel=lbl
		app.updateLabel(lbl)
	}
	state.schedules=[]
	state.vars=(Map)state.vars ?: [:]
	state.modifiedVersion=sVER

	state.cache=[:]
	state.logs=[]
	state.trace=[:]

	Map rtD=[:]
	rtD.piston=piston
	releaseTheLock(semaName)
	if((Integer)state.build==1 || (Boolean)state.active)rtD=resume(piston)
	else clearMyCache('setup')
	return [active:(Boolean)state.active,build:(Integer)state.build,modified:(Long)state.modified,state:(Map)state.state,rtData:rtD]
}

private void clearMsetIds(node){
	if(node==null)return
	for(list in node.findAll{ it.value instanceof List }){
		for(item in ((List)list.value).findAll{ it instanceof Map })clearMsetIds(item)
	}
	if(node instanceof Map && node[sDLR]!=null) node[sDLR]=null

	for(item in node.findAll{ it.value instanceof Map })clearMsetIds(item.value)
}

@Field static List<String> ListCmd=[]

private Integer msetIds(Boolean shorten,node,Integer mId=0,Map<String,Integer> existingIds=[:],List<Map> requiringIds=[],Integer level=0){
	List<Map> nodeE=node?.ei
	String nodeT=node?.t
	Integer maxId=mId
	//Boolean lg= eric() && settings.logging?.toInteger()>2
	if(!ListCmd) ListCmd=[sIF,sACTION,sCONDITION,sWHILE,sREPEAT,sFOR,sEACH,sSWITCH,sEVERY,sRESTRIC,sGROUP,sDO,sON,sEVENT,sEXIT,sBREAK]
	if(nodeT in ListCmd){
		Integer id=node[sDLR]!=null ? (Integer)node[sDLR] :0
		if(id==0 || existingIds[id.toString()]!=null){
			Boolean a=requiringIds.push(node)
			//if(lg) log.warn "adding id for node $nodeT ${existingIds}"
		}else{
			maxId=maxId<id ? id:maxId
			existingIds[id.toString()]=id
		}
		if(nodeT==sIF && nodeE){
			Boolean a=((List<Map>)node.ei).removeAll{ !it.c && !it.s }
			for(Map elseIf in (List<Map>)node.ei){
				id=elseIf[sDLR]!=null ? (Integer)elseIf[sDLR]:0
				if(id==0 || existingIds[id.toString()]!=null) Boolean aa=requiringIds.push(elseIf)
				else{
					maxId=maxId<id ? id:maxId
					existingIds[id.toString()]=id
				}
			}
		}
		if(nodeT==sSWITCH && node.cs){
			for(Map _case in (List<Map>)node.cs){
				id=_case[sDLR]!=null ? (Integer)_case[sDLR]:0
				if(id==0 || existingIds[id.toString()]!=null) Boolean a=requiringIds.push(_case)
				else{
					maxId=maxId<id ? id:maxId
					existingIds[id.toString()]=id
				}
			}
		}
		if(nodeT==sACTION && node.k){
			for(Map task in (List<Map>)node.k){
				id=task[sDLR]!=null ? (Integer)task[sDLR]:0
				if(id==0 || existingIds[id.toString()]!=null) Boolean a=requiringIds.push(task)
				else{
					maxId=maxId<id ? id:maxId
					existingIds[id.toString()]=id
				}
			}
		}
	}
	for(list in node.findAll{ it.value instanceof List }){
		for(item in ((List)list.value).findAll{ it instanceof Map })maxId=msetIds(shorten,item,maxId,existingIds,requiringIds,level+1)
	}
	if(level==0){
		//if(lg) log.warn "start id $maxId"
		for(item in requiringIds){
			maxId += 1
			item[sDLR]=maxId
			/*if(eric() && settings.logging?.toInteger()>2){
				String ty=item?.t
				if(!ty) ty=item?.c
				if(!ty) ty="case / elseif"
				if(lg) log.warn "adding id $maxId for node ${ty}"
			}*/
		}
		if(shorten)cleanCode(node)
	}
	return maxId
}

@Field static List<String> ListC1=[]
@Field static List<String> ListC2=[]
@Field static List<String> ListC3=[]

private void cleanCode(item){
	if(item==null || !(item instanceof Map))return

	if(!ListC1){
		ListC1=[sC,sS,sV,sE]
		ListC2=[sC,sS,sX,sV,sE]
		ListC3=[sS,sX,sV,sE]
		if(!LT1) { LT1=fill_TIM() }
	}
	def a
	if((String)item.t in ListC1){ // operand values that don't need g,a
		if((String)item.g=='avg')a=item.remove('g')
		if(item.a instanceof String)a=item.remove(sA)
	}
	if((String)item.t in ListC2){ // operand values that don't need d
		if(item.d instanceof List)a=item.remove(sD)
	}
	if((String)item.t in ListC3 || ((String)item.t==sC && !((String)item.vt in LT1)) ){ // operand values that don't need c
		a=item.remove(sC)
	}
	if(item.t==null && item.size()==4 && item.d instanceof List && !item.d && (String)item.g=='avg' && item.f==sL && item.vt){
		a=item.remove(sD); a=item.remove('g'); a=item.remove('f')
	}
	if(item.fs instanceof List && !item.fs)a=item.remove('fs')
	if(item.ts instanceof List && !item.ts)a=item.remove('ts')
	if(item.e instanceof List && !item.e)a=item.remove(sE)
	if(item.ei instanceof List && !item.ei)a=item.remove('ei')
	if(item.s instanceof List && !item.s)a=item.remove(sS)
	if(item.r instanceof List && !item.r)a=item.remove(sR)
	if(item.d instanceof List && !item.d)a=item.remove(sD)

	if(item.str!=null)a=item.remove('str')
	if(item.ok!=null)a=item.remove('ok')
	if(item.z!=null)a=item.remove('z')
	if(item.zc!=null)a=item.remove('zc')
	if(item.e!=null && item.e instanceof String)a=item.remove(sE)
	if(item.l!=null && item.l instanceof String)a=item.remove(sL)

	if(item.v!=null)cleanCode(item.v)
	if(item.exp!=null)cleanCode(item.exp)
	if(item.lo!=null)cleanCode(item.lo)
	if(item.lo2!=null)cleanCode(item.lo2)
	if(item.lo3!=null)cleanCode(item.lo3)
	if(item.ro!=null){
		if(item.ro instanceof String || fndEmptyOper((Map)item.ro))a=item.remove('ro')
		else cleanCode(item.ro)
	}
	if(item.ro2!=null){
		if(fndEmptyOper((Map)item.ro2))a=item.remove('ro2')
		else cleanCode(item.ro2)
	}
	if(item.to!=null){
		if(fndEmptyOper((Map)item.to))a=item.remove('to')
		else cleanCode(item.to)
	}
	if(item.to2!=null){
		if(fndEmptyOper((Map)item.to2))a=item.remove('to2')
		else cleanCode(item.to2)
	}
	for(list in item.findAll{ it.value instanceof List }){
		for(itemA in ((List)list.value).findAll{ it instanceof Map })cleanCode(itemA)
	}
}

static Boolean fndEmptyOper(Map oper){
	if(oper.size()==3 && (String)oper.t==sC && !oper.d && (String)oper.g==sANY)return true
	return false
}

Map deletePiston(){
	String meth='deletePiston'
	if(eric())log.debug meth
	atomicState.pistonDeleted=true
	state.active=false
	clear1(true,true,true,true)	// calls clearMyCache(meth) && clearMyPiston
	return [:]
}

private void checkLabel(Map rtD=null){
	Boolean act=(Boolean)rtD.active
	Boolean dis=!(Boolean)rtD.enabled
	String savedLabel=(String)rtD.svLabel
	if(savedLabel==sNULL){
		log.error "null label"
		return
	}
	String appLbl=savedLabel
	if(savedLabel!=sNULL){
		if(act && !dis){
			app.updateLabel(savedLabel)
		}
		if(!act || dis){
			String tstr='(Paused)'
			if(act && dis) tstr='(Disabled) Kill switch is active'
			String res=appLbl+" <span style='color:orange'>"+tstr+"</span>"
			app.updateLabel(res)
		}
	}
}

void config(Map data){ // creates a new piston
	if(data==null) return
	if((String)data.bin!=sNULL){
		state.bin=(String)data.bin
		app.updateSetting('bin',[(sTYPE):sTEXT,(sVAL):(String)state.bin])
	}
	if((String)data.author!=null){
		state.author=(String)data.author
		app.updateSetting('author',[(sTYPE):sTEXT,(sVAL):(String)state.author])
	}
	if((String)data.initialVersion!=null) state.initialVersion=(String)data.initialVersion
	clearMyCache('config')
}

Map setBin(String bin){
	if(!bin || !!state.bin){
		log.error 'setBin: bad bin'
		return [:]
	}
	state.bin=bin
	app.updateSetting('bin',[(sTYPE):sTEXT,(sVAL):bin])
	String typ='setBin'
	clearMyCache(typ)
	return [:]
}

Map pausePiston(){
	state.active=false
	clearMyCache('pauseP')

	LinkedHashMap rtD=getRunTimeData()
	Map msg=timer 'Piston successfully stopped',rtD,-1
	if((Integer)rtD.logging>0)info 'Stopping piston...',rtD,0
	state.schedules=[]
	rtD.stats.nextSchedule=lZERO
	rtD.nextSchedule=lZERO
	state.nextSchedule=lZERO
	unsubscribe()
	unschedule()
//	state.trace=[:]
	state.subscriptions=[:]
	if((Integer)rtD.logging>0)info msg,rtD
	updateLogs(rtD)
	state.active=false
	state.state=[:]+(Map)rtD.state
	def a=state.remove('lastEvent')
	clear1(true,false,false,false)	// calls clearMyCache(meth) && clearMyPiston
	Map nRtd=shortRtd(rtD)
	rtD=null
	return nRtd
}

Map resume(LinkedHashMap piston=null){
	state.active=true
	state.subscriptions=[:]
	state.schedules=[]

	String semName=app.id.toString()
	getTheLock(semName,'resume')
	theSemaphoresVFLD[semName]=lZERO
	theSemaphoresVFLD=theSemaphoresVFLD
	theQueuesVFLD[semName]=[]
	theQueuesVFLD=theQueuesVFLD
	releaseTheLock(semName)

	clearMyCache('resumeP')

	LinkedHashMap<String,Object> tmpRtD=getTemporaryRunTimeData()
	Map msg=timer 'Piston successfully started',tmpRtD,-1
	if(piston!=null)tmpRtD.piston=piston
	LinkedHashMap rtD=getRunTimeData(tmpRtD,null,true,false) //performs subscribeAll(rtD); reinitializes cache variables
	if((Integer)rtD.logging>0)info 'Starting piston... ('+sHVER+')',rtD,0
	checkVersion(rtD)
	if((Integer)rtD.logging>0)info msg,rtD
	updateLogs(rtD)
	state.state=[:]+(Map)rtD.state
	Map nRtd=shortRtd(rtD)
	nRtd.result=[active:true,subscriptions:(Map)state.subscriptions]
	tmpRtD=null
	rtD=null
	return nRtd
}

static Map shortRtd(Map rtD){
	Map st=[:]+(Map)rtD.state
	def a=st.remove('old')
	Map myRt=[
		id:(String)rtD.id,
		active:(Boolean)rtD.active,
		category:rtD.category,
		stats:[
			nextSchedule:(Long)rtD.nextSchedule
		],
		piston:[
			z:(String)rtD.pistonZ
		],
		state:st,
		Cached:(Boolean)rtD.Cached ?: false
	]
	return myRt
}

Map setLoggingLevel(String level,Boolean clearC=true){
	Integer mlogging=level.isInteger()? level.toInteger():0
	mlogging=Math.min(Math.max(0,mlogging),3)
	app.updateSetting('logging',[(sTYPE):sENUM,(sVAL):mlogging.toString()])
	state.logging=mlogging
	if(mlogging==0)state.logs=[]
	if(clearC) clearMyCache('setLoggingLevel')
	return [logging:mlogging]
}

Map setCategory(String category){
	state.category=category
	clearMyCache('setCategory')
	return [category:category]
}

Map test(){
	handleEvents([(sDATE):new Date(),(sDEV):location,(sNM):'test',(sVAL):now()])
	return [:]
}

Map execute(data,source){
	handleEvents([(sDATE):new Date(),(sDEV):location,(sNM):'execute',(sVAL): source!=null ? source:now(),jsonData:data],false)
	return [:]
}

Map clickTile(index){
	handleEvents([(sDATE):new Date(),(sDEV):location,(sNM):'tile',(sVAL):index])
	return (Map)state.state ?: [:]
}

Map clearCache(){
	handleEvents([(sDATE):new Date(),(sDEV):location,(sNM):'clearc',(sVAL):now()])
	return [:]
}

Map clearLogsQ(){
	handleEvents([(sDATE):new Date(),(sDEV):location,(sNM):'clearl',(sVAL):now()])
	return [:]
}

private Map getCachedAtomicState(){
	Long atomStart=now()
	def atomState
	atomicState.loadState()
	atomState=atomicState.@backingMap
	if(settings.logging?.toInteger()>2)log.debug "AtomicState generated in ${elapseT(atomStart)}ms"
	return atomState
}

@Field volatile static Map<String,Long> lockTimesVFLD=[:]
@Field volatile static Map<String,String> lockHolderVFLD=[:]

void getTheLock(String qname,String meth=sNULL,Boolean longWait=false){
	Boolean a=getTheLockW(qname,meth,longWait)
}

Boolean getTheLockW(String qname,String meth=sNULL,Boolean longWait=false){
	Long waitT=longWait? lTHOUS:60L
	Boolean wait=false
	Integer semaNum=getSemaNum(qname)
	String semaSNum=semaNum.toString()
	Semaphore sema=getSema(semaNum)
	while(!((Boolean)sema.tryAcquire())){
		// did not get the lock
		Long t=lockTimesVFLD[semaSNum]
		if(t==null){
			t=now()
			lockTimesVFLD[semaSNum]=t
			lockTimesVFLD=lockTimesVFLD
		}
		if(eric())log.warn "waiting for ${qname} ${semaSNum} lock access, $meth, long: $longWait, holder: ${(String)lockHolderVFLD[semaSNum]}"
		pauseExecution(waitT)
		wait=true
		if(elapseT(t) > 30000L){
			releaseTheLock(qname)
			if(eric())log.warn "overriding lock $meth"
		}
	}
	lockTimesVFLD[semaSNum]=(Long)now()
	lockTimesVFLD=lockTimesVFLD
	lockHolderVFLD[semaSNum]=app.id.toString()+sSPC+meth
	lockHolderVFLD=lockHolderVFLD
	return wait
}

void releaseTheLock(String qname){
	Integer semaNum=getSemaNum(qname)
	String semaSNum=semaNum.toString()
	Semaphore sema=getSema(semaNum)
	lockTimesVFLD[semaSNum]=null
	lockTimesVFLD=lockTimesVFLD
//	lockHolderVFLD[semaSNum]=sNULL
//	lockHolderVFLD=lockHolderVFLD
	sema.release()
}

@Field static Semaphore theLock0FLD=new Semaphore(1)
@Field static Semaphore theLock1FLD=new Semaphore(1)
@Field static Semaphore theLock2FLD=new Semaphore(1)
@Field static Semaphore theLock3FLD=new Semaphore(1)
@Field static Semaphore theLock4FLD=new Semaphore(1)
@Field static Semaphore theLock5FLD=new Semaphore(1)
@Field static Semaphore theLock6FLD=new Semaphore(1)
@Field static Semaphore theLock7FLD=new Semaphore(1)
@Field static Semaphore theLock8FLD=new Semaphore(1)
@Field static Semaphore theLock9FLD=new Semaphore(1)
@Field static Semaphore theLock10FLD=new Semaphore(1)
@Field static Semaphore theLock11FLD=new Semaphore(1)
@Field static Semaphore theLock12FLD=new Semaphore(1)
@Field static Semaphore theLock13FLD=new Semaphore(1)
@Field static Semaphore theLock14FLD=new Semaphore(1)
@Field static Semaphore theLock15FLD=new Semaphore(1)
@Field static Semaphore theLock16FLD=new Semaphore(1)
@Field static Semaphore theLock17FLD=new Semaphore(1)
@Field static Semaphore theLock18FLD=new Semaphore(1)
@Field static Semaphore theLock19FLD=new Semaphore(1)
@Field static Semaphore theLock20FLD=new Semaphore(1)
@Field static Semaphore theLock21FLD=new Semaphore(1)
@Field static Semaphore theLock22FLD=new Semaphore(1)
@Field static Semaphore theLock23FLD=new Semaphore(1)
@Field static Semaphore theLock24FLD=new Semaphore(1)

static Integer getSemaNum(String name){
	if(name==sTCCC)return 22
	if(name==sTSLF)return 23
	if(name==sTGBL)return 24
	Integer stripes=22
	if(name.isNumber()) return name.toInteger()%stripes
	Integer hash=smear(name.hashCode())
	return Math.abs(hash)%stripes
//	if(eric())log.info "sema $name # $sema"
}

Semaphore getSema(Integer snum){
	switch(snum){
		case 0: return theLock0FLD
		case 1: return theLock1FLD
		case 2: return theLock2FLD
		case 3: return theLock3FLD
		case 4: return theLock4FLD
		case 5: return theLock5FLD
		case 6: return theLock6FLD
		case 7: return theLock7FLD
		case 8: return theLock8FLD
		case 9: return theLock9FLD
		case 10: return theLock10FLD
		case 11: return theLock11FLD
		case 12: return theLock12FLD
		case 13: return theLock13FLD
		case 14: return theLock14FLD
		case 15: return theLock15FLD
		case 16: return theLock16FLD
		case 17: return theLock17FLD
		case 18: return theLock18FLD
		case 19: return theLock19FLD
		case 20: return theLock20FLD
		case 21: return theLock21FLD
		case 22: return theLock22FLD
		case 23: return theLock23FLD
		case 24: return theLock24FLD
		default:log.error "bad hash result $snum"
			return null
	}
}

private static Integer smear(Integer hashC){
	Integer hashCode=hashC
	hashCode ^= (hashCode >>> 20) ^ (hashCode >>> 12)
	return hashCode ^ (hashCode >>> 7) ^ (hashCode >>> 4)
}

void getCacheLock(String meth=sNULL){
	getTheLock(sTCCC,meth+sSPC+sTCL)
}

void releaseCacheLock(){
	releaseTheLock(sTCCC)
}

@Field volatile static Map<String,List<Map>> theQueuesVFLD=[:]
@Field volatile static Map<String,Long> theSemaphoresVFLD=[:]

// This can a)lock semaphore,b)wait for semaphore,c)queue event, d)just fall through (no locking, waiting)
private Map lockOrQueueSemaphore(Boolean synchr,event,Boolean queue,Map rtD){
	Long tt1=now()
	Long startTime=tt1
	Long r_semaphore=lZERO
	Long semaphoreDelay=lZERO
	String semaphoreName=sNULL
	Boolean didQ=false
	Boolean waited=false

	if(synchr){
		String semaName=app.id.toString()
		waited=getTheLockW(semaName,sLCK1)
		tt1=now()

		Long lastSemaphore
		Boolean clearC=false
		Integer qsize=0
		while(true){
			Long t0=theSemaphoresVFLD[semaName]
			Long tt0=t0!=null ? t0:lZERO
			lastSemaphore=tt0
			if(lastSemaphore==lZERO || tt1-lastSemaphore>100000L){
				theSemaphoresVFLD[semaName]=tt1
				theSemaphoresVFLD=theSemaphoresVFLD
				semaphoreName=semaName
				semaphoreDelay=waited ? tt1-startTime:lZERO
				r_semaphore=tt1
				break
			}
			if(queue){
				if(event!=null){
					Map myEvent=[
						(sT):(Long)((Date)event.date).getTime(),
						(sNM):(String)event.name,
						(sVAL):event.value,
						descriptionText:(String)event.descriptionText,
						unit:event?.unit,
						physical:!!event.physical,
						jsonData:event?.jsonData,
					]+(event instanceof com.hubitat.hub.domain.Event ? [:]:[
						index:event.index,
						recovery:event.recovery,
						schedule:event.schedule,
						contentType:(String)event.contentType,
						responseData:event.responseData,
						responseCode:event.responseCode,
						setRtData:event.setRtData
					])
					if(event.device!=null){
						myEvent.device=[id:event.device?.id,(sNM):event.device?.name,label:event.device?.label]
						if(event.device?.hubs!=null){
							myEvent.device.hubs=[(sT):'tt']
						}
					}
					List<Map> evtQ=theQueuesVFLD[semaName]
					evtQ=evtQ!=null ? evtQ:[]
					qsize=evtQ.size()
					if(qsize>12){
						clearC=true
					}else{
						Boolean a=evtQ.push(myEvent)
						theQueuesVFLD[semaName]=evtQ
						theQueuesVFLD=theQueuesVFLD
						didQ=true
					}
				}
				break
			}else{
				releaseTheLock(semaName)
				waited=true
				pauseExecution(100L)
				getTheLock(semaName,sLCK2)
				tt1=now()
			}
		}
		releaseTheLock(semaName)
		if(clearC){
			error "large queue size ${qsize} clearing",rtD
			clear1(true,true,true,true)
		}
	}
	return [
		semaphore:r_semaphore,
		semaphoreName:semaphoreName,
		semaphoreDelay:semaphoreDelay,
		waited:waited,
		exitOut:didQ
	]
}

private LinkedHashMap<String,Object> getTemporaryRunTimeData(Long startTime=now()){
	if(thePhysCommandsFLD==null){ //do one time load once
		String semName=sTSLF
		getTheLock(semName,sGETTRTD,true)
		if(thePhysCommandsFLD==null){ // load caches
			Map comparison=Comparisons()
			Map vcmd=VirtualCommands()
			Map attr=Attributes()
			List col=getColors()
			Map cmd=PhysicalCommands()
		}
		releaseTheLock(semName)
	}
	LinkedHashMap<String,Object> rtD=getDSCache(sGETTRTD)
	rtD.temporary=true
	rtD.timestamp=startTime
	rtD.logs=[[(sT):startTime]]
	rtD.debugLevel=0
	rtD.eric=eric1() && (Integer)rtD.logging>2
	return rtD
}

@Field volatile static LinkedHashMap<String,LinkedHashMap<String,Object>> theCacheVFLD=[:] // each piston has a map

private void clearMyCache(String meth=sNULL){
	Boolean clrd=false
	String appStr=app.id.toString()
	String myId=hashId(appStr)
	if(!myId)return
	String semaName=appStr
	String str='clearMyCache'
	getTheLock(semaName,str)
	getCacheLock(str)
	Map t0=theCacheVFLD[myId]
	if(t0){
		theCacheVFLD[myId]=null
		theCacheVFLD=theCacheVFLD
		clrd=true
		t0=null
	}
	releaseCacheLock()
	releaseTheLock(semaName)
	if(clrd && eric())log.debug 'clearing piston data cache '+meth
}

private LinkedHashMap<String,Object> getCachedMaps(String meth=sNULL,Boolean retry=true,Boolean Upd=true){
	String myId=hashId(app.id)
	LinkedHashMap<String,Object> result=theCacheVFLD[myId]
	if(result!=null){
		if(result.cache instanceof Map && result.build instanceof Integer){
			return result
		}
		String semaName=app.id.toString()
		getTheLock(semaName,sI)
		theCacheVFLD[myId]=null
		theCacheVFLD=theCacheVFLD
		releaseTheLock(semaName)
	}
	if(retry){
		LinkedHashMap<String,Object> a=getDSCache(meth,Upd)
		if(!Upd)return a
		return getCachedMaps(meth,false,Upd)
	}
	if(eric())log.warn 'cached map nf'
	return null
}

private LinkedHashMap<String,Object> getDSCache(String meth,Boolean Upd=true){
	String appStr=app.id.toString()
	String appId=hashId(appStr)
	String myId=appId
	LinkedHashMap<String,Object> pC=getParentCache()
	LinkedHashMap<String,Object> result=theCacheVFLD[myId]

	if(result!=null) result.stateAccess=null
	Boolean sendM=false
	if(result==null){
		String lockTyp='getDSCache'
		String semaName=appStr
		getTheLock(semaName,lockTyp)
		result=theCacheVFLD[myId]
		if(result==null){
			Long stateStart=now()
			if(state.pep==null){ // upgrades of older pistons
				LinkedHashMap piston=recreatePiston()
				state.pep=piston.o?.pep ? true:false
			}
			Integer bld=(Integer)state.build
			String ttt=(String)state.svLabel
			if(ttt==sNULL){
				ttt=(String)app.label
				if(bld>0){
					state.svLabel=ttt
					atomicState.svLabel=ttt
				}
			}
			Map t1=[
				id: appId,
				logging: (Integer)state.logging!=null ? (Integer)state.logging:0,
				svLabel: ttt,
				(sNM): ttt,
				active: (Boolean)state.active,
				category: state.category ?: 0,
				pep: (Boolean)state.pep,
				created: (Long)state.created,
				modified: (Long)state.modified,
				build: bld,
				author: (String)state.author,
				bin: (String)state.bin,
				logsToHE: (Boolean)settings.logsToHE
			] as Map
			Long stateEnd=now()
			t1.stateAccess=stateEnd-stateStart
			t1.runTimeHis=[]
			def atomState=((Boolean)t1.pep)? getCachedAtomicState():state

			def t0=(Map)atomState.cache
			t1.cache=t0 ? (Map)t0:[:]
			t0=(Map)atomState.store
			t1.store=t0 ? (Map)t0:[:]

			t0=(Map)atomState.state
			t1.state=t0 ? (Map)t0:[:]

			t0=(String)atomState.pistonZ
			t1.pistonZ=t0

			t0=(Map)atomState.trace
			t1.trace=t0 ? (Map)t0:[:]
			t0=(List)atomState.schedules
			t1.schedules=t0 ? (List)t0:[]
			t1.nextSchedule=(Long)atomState.nextSchedule
			t1.lastExecuted=(Long)atomState.lastExecuted
			t1.mem=mem()
			t0=(List)atomState.logs
			t1.logs=t0 ? (List)t0:[]
			t0=(Map)atomState.vars
			t1.vars=t0 ? [:]+(Map)t0:[:]
			t1.cachePersist=[:]
			resetRandomValues(t1)
			t1.devices= settings.dev && settings.dev instanceof List ? settings.dev.collectEntries{[(hashId(it.id)): it]} : [:]

			sendM=true
			if(Upd){
				t1.Cached=true
				theCacheVFLD[myId]= t1 as LinkedHashMap
				theCacheVFLD=theCacheVFLD
			}
			result= t1 as LinkedHashMap<String,Object>
			t1=null
			t0=null
			atomState=null
		}
		releaseTheLock(semaName)
		if(sendM && eric()){
			String st=sBLK
			if(Upd)st='/cached'
			log.debug 'creating'+st+' my piston cache '+meth
		}
	}
	LinkedHashMap<String,Object> rtD= (LinkedHashMap)(pC+result)
	pC=null
	result=null
	if(sendM && rtD.build!=0)checkLabel(rtD)
	return rtD
}

@Field volatile static LinkedHashMap<String,LinkedHashMap<String,Object>> theParentCacheVFLD=[:]

void clearParentCache(String meth=sNULL){
	String lockTyp='clearParentCache'
	String semName=sTSLF
	String wName=parent.id.toString()
	getTheLock(semName,lockTyp)

	theParentCacheVFLD[wName]=null
	theParentCacheVFLD=theParentCacheVFLD

	getCacheLock(lockTyp)
	theCacheVFLD=[:] // all pistons reset their cache
	clearHashMap(wName)
	theVirtDevicesFLD=null
	releaseCacheLock()

	releaseTheLock(semName)

	if(eric())log.debug "clearing parent cache and all piston caches $meth"
}

private LinkedHashMap<String,Object> getParentCache(){
	String lockTyp='getParentCache'
	String wName=parent.id.toString()
	LinkedHashMap<String,Object> result=theParentCacheVFLD[wName]
	if(result==null){
		String semName=sTSLF
		getTheLock(semName,lockTyp)
		result=theParentCacheVFLD[wName]
		Boolean sendM=false
		if(result==null){
			Map t0=(Map)parent.getChildPstate()
			Map t1=[
				coreVersion: (String)t0.sCv,
				hcoreVersion: (String)t0.sHv,
				powerSource: (String)t0.powerSource,
				region: (String)t0.region,
				instanceId: (String)t0.instanceId,
				settings: (Map)t0.stsettings,
				enabled: (Boolean)t0.enabled,
				//disabled: !(Boolean)t0.enabled,
				lifx: (Map)t0.lifx,
				logPExec: (Boolean)t0.logPExec,
				locationId: (String)t0.locationId,
				oldLocationId: hashId(location.id.toString()+'L'),//backwards compatibility
				incidents: (List)t0.incidents,
				useLocalFuelStreams: (Boolean)t0.useLocalFuelStreams
			]
			result=t1
			theParentCacheVFLD[wName]=t1
			theParentCacheVFLD=theParentCacheVFLD
			t1=null
			sendM=true
		}
		releaseTheLock(semName)
		if(sendM && eric()){
			String mStr='gathering parent cache'
			log.debug mStr
		}
	}
	return result
}

private LinkedHashMap<String,Object> getRunTimeData(LinkedHashMap<String,Object> rtD=null,Map retSt=null,Boolean fetchWrappers=false,Boolean shorten=false){
	Long started=now()
	List logs=[]
	Long lstarted=lZERO
	Long lended=lZERO
	LinkedHashMap piston
	Integer dbgLevel=0
	if(rtD!=null){
		logs=rtD.logs!=null ? (List)rtD.logs:[]
		lstarted=rtD.lstarted!=null ? (Long)rtD.lstarted:lZERO
		lended=rtD.lended!=null ? (Long)rtD.lended:lZERO
		piston=rtD.piston!=null ? (LinkedHashMap)rtD.piston:null
		dbgLevel=rtD.debugLevel!=null ? (Integer)rtD.debugLevel:0
	}else rtD=getTemporaryRunTimeData(started)
	Long timestamp=(Long)rtD.timestamp

	if(rtD.temporary!=null) def a=rtD.remove('temporary')

	LinkedHashMap<String,Object> m1=[semaphore:lZERO,semaphoreName:sNULL,semaphoreDelay:lZERO] as LinkedHashMap<String,Object>
	if(retSt!=null){
		m1.semaphore=(Long)retSt.semaphore
		m1.semaphoreName=(String)retSt.semaphoreName
		m1.semaphoreDelay=(Long)retSt.semaphoreDelay
	}
	rtD=(LinkedHashMap)(rtD+m1)

	rtD.timestamp=timestamp
	rtD.lstarted=lstarted
	rtD.lended=lended
	//rtD.logs=[]
	if(logs!=[] && logs.size()>0) rtD.logs=logs
	else rtD.logs=[[(sT):timestamp]]
	rtD.debugLevel=dbgLevel

	rtD.trace=[(sT):timestamp,points:[:]]
	rtD.stats=[nextSchedule:lZERO]
	rtD.newCache=[:]
	rtD.schedules=[]
	rtD.cancelations=[statements:[],conditions:[],all:false]
	rtD.updateDevices=false
	rtD.systemVars=getSystemVariables()

	Map atomState=getCachedMaps('getRTD')
	atomState=atomState!=null?atomState:[:]
	Map st=(Map)atomState.state
	rtD.state=st!=null && st instanceof Map ? [:]+st : [old:sBLK,new:sBLK]
	rtD.state.old=(String)rtD.state.new

	rtD.pStart=now()

	if(piston==null) piston=recreatePiston(shorten)
	Boolean doSubScribe=!(Boolean)piston.cached

	rtD.piston=piston

	getLocalVariables(rtD,(List)piston.v,atomState)
	piston=null

	if(doSubScribe || fetchWrappers){
		subscribeAll(rtD,fetchWrappers)
		String pisName=app.id.toString()
		Map pData=(Map)thePistonCacheFLD[pisName]
		if(shorten && pisName!=sBLK && pData!=null && pData.pis==null){
			pData.pis=[:]+(LinkedHashMap)rtD.piston
			thePistonCacheFLD[pisName]=[:]+pData
			pData=null
			mb()
			if(eric()){
				log.debug 'creating piston-code-cache'
				dumpPCsize()
			}
		}
	}
	Long t0=now()
	rtD.pEnd=t0
	rtD.ended=t0
	rtD.generatedIn=t0-started
	return rtD
}

private void dumpPCsize(){
	Map pL
	Integer t0=0
	Integer t1=0
	try{
		pL=[:]+thePistonCacheFLD
		t0=pL.size()
		t1="${pL}".size()
	}catch(ignored){}
	pL=null
	String mStr="piston plist is ${t0} elements, and ${t1} bytes".toString()
	log.debug mStr
	if(t1>40000000){
		thePistonCacheFLD=[:]
		mb()
		log.warn "clearing entire "+mStr
	}
}

private void checkVersion(Map rtD){
	String ver=sHVER
	String t0=(String)rtD.hcoreVersion
	if(ver!=t0){
		String tt0="child app's version($ver)".toString()
		String tt1="parent app's version($t0)".toString()
		String tt2=' is newer than the '
		String msg
		if(ver>t0) msg=tt0+tt2+tt1
		else msg=tt1+tt2+tt0
		warn "WARNING: Results may be unreliable because the "+msg+". Please update both apps to the same version.",rtD
	}
	if(location.timeZone==null){
		error 'Your location is not setup correctly - timezone information is missing. Please select your location by placing the pin and radius on the map, then tap Save, and then tap Done. You may encounter error or incorrect timing until this is fixed.',rtD
	}
}

/** EVENT HANDLING								**/

void deviceHandler(event){
	handleEvents(event)
}

void timeHandler(event){
	timeHelper(event,false)
}

void timeHelper(event,Boolean recovery){
	handleEvents([(sDATE):new Date((Long)event.t),(sDEV):location,(sNM):sTIME,(sVAL):(Long)event.t,schedule:event,recovery:recovery],!recovery)
}

void executeHandler(event){
	handleEvents([(sDATE):event.date,(sDEV):location,(sNM):'execute',(sVAL):event.value,jsonData:event.jsonData])
}

@Field static final Map getPistonLimits=[
	scheduleRemain: 35000L, // need this or longer remaining executionTime to process additional schedules
	scheduleVariance: 270L,
	executionTime: 40000L, // time we stop this execution
	slTime: 6300L, // time before we start inserting pauses
	useBigDelay: 20000L, // transition from short delay to Long delay
	taskShortDelay: 150L,
	taskLongDelay: 500L,
	taskMaxDelay: 250L,
	deviceMaxDelay: 1000L,
	maxStats: 50,
	maxLogs: 50,
]

void handleEvents(evt,Boolean queue=true,Boolean callMySelf=false){
	def event=evt
	Long startTime=now()
	LinkedHashMap<String,Object> tmpRtD=getTemporaryRunTimeData(startTime)
	Map msg=timer 'Event processed successfully',tmpRtD,-1
	String evntName=(String)event.name
	String evntVal="${event.value}".toString()
	Long eventDelay=Math.round(dONE*startTime-(Long)((Date)event.date).getTime())
	if((Integer)tmpRtD.logging!=0){
		String devStr="${event.device?.label ?: event.device?.name ?: location}".toString()
		String recStr=evntName==sTIME && (Boolean)event.recovery ? '/recovery':sBLK
		String valStr=evntVal+(evntName=='hsmAlert' && evntVal=='rule' ? ','+(String)event.descriptionText:sBLK)
		String mymsg='Received event ['+devStr+'].'+evntName+recStr+' = '+valStr+" with a delay of ${eventDelay}ms, canQueue: ${queue}, calledMyself: ${callMySelf}".toString()
		info mymsg,tmpRtD,0
	}

	Boolean clearC=evntName=='clearc'
	Boolean clearL=evntName=='clearl'

	Boolean act=(Boolean)tmpRtD.active
	Boolean dis=!(Boolean)tmpRtD.enabled
	if(!act || dis){
		if((Integer)tmpRtD.logging!=0){
			String tstr=' active,aborting piston execution.'
			if(!act) msg.m='Piston is not'+tstr+' (Paused)' // this is pause/resume piston
			if(dis) msg.m='Kill switch is'+tstr
			info msg,tmpRtD
		}
		updateLogs(tmpRtD)
		if(clearL) clear1(true,true,true,false,true)
		else if(clearC) clear1(true,false,false,false)
		return
	}

	Boolean myPep=(Boolean)tmpRtD.pep
	String appId=(String)tmpRtD.id
	Boolean serializationOn=true // on / off switch
	Boolean strictSync=true // this could be a setting
	serializationOn=!myPep && serializationOn
	Boolean doSerialization=serializationOn && !callMySelf

	tmpRtD.lstarted=now()
	Map retSt=[semaphore:lZERO,semaphoreName:sNULL,semaphoreDelay:lZERO]
	if(doSerialization){
		retSt=lockOrQueueSemaphore(doSerialization,event,queue,tmpRtD)
		if((Boolean)retSt.exitOut){
			if((Integer)tmpRtD.logging!=0){
				msg.m='Event queued'
				info msg,tmpRtD
			}
			updateLogs(tmpRtD)
			event=null
			tmpRtD=null
			return
		}
		if((Long)retSt.semaphoreDelay>lZERO)warn 'Piston waited for semaphore '+(Long)retSt.semaphoreDelay+'ms',tmpRtD
	}
	tmpRtD.lended=now()

//measure how Long first state access takes
	Long stAccess=lZERO
	if((Integer)tmpRtD.logging>0 && !myPep){
		if(tmpRtD.stateAccess==null){
			Long stStart=now()
			Long b=(Long)state.nextSchedule
			List a=(List)state.schedules
			Map pEvt=(Map)state.lastEvent
			Long stEnd=now()
			stAccess=stEnd-stStart
		}else stAccess=(Long)tmpRtD.stateAccess
	}

	tmpRtD.cachePersist=[:]
	LinkedHashMap<String,Object> rtD=getRunTimeData(tmpRtD,retSt,false,true)
	tmpRtD=null
	checkVersion(rtD)

	Long theend=now()
	Long t0=theend-startTime
	Long t1=(Long)rtD.lended-(Long)rtD.lstarted
	Long t2=(Long)rtD.generatedIn
	Long t3=(Long)rtD.pEnd-(Long)rtD.pStart
	Long missing=t0-t1-t2
	Long t4=(Long)rtD.lended-startTime
	Long t5=theend-(Long)rtD.lended
	rtD.curStat=[(sI):t0,(sL):t1,(sR):t2,(sP):t3,(sS):stAccess]
	String myId=(String)rtD.id
	Integer lg=(Integer)rtD.logging
	if(lg>1){
		if(lg>2)debug "RunTime initialize > ${t0} LockT > ${t1}ms > rtDT > ${t2}ms > pistonT > ${t3}ms (first state access ${missing} $t4 $t5)".toString(),rtD
		String adMsg=sBLK
		if(eric())adMsg=" (Init:$t0, Lock: $t1, pistonT $t3 first state access $missing ($t4 $t5) $stAccess".toString()
		trace "Runtime (${"$rtD".size()} bytes) successfully initialized in ${t2}ms (${sHVER})".toString()+adMsg,rtD
	}

	resetRandomValues(rtD)
	rtD.tPause=lZERO
	rtD.stats.timing=[(sT):startTime,(sD):eventDelay>lZERO ? eventDelay:lZERO,(sL):elapseT(startTime)]

	String semName=(String)rtD.semaphoreName
	Long lS=(Long)rtD.semaphore

	if(clearC||clearL){
		if(clearL) clear1(true,true,true,false,true)
		else if(rtD.lastExecuted==null || elapseT((Long)rtD.lastExecuted) > 3660000L) clear1(true,false,false,false)
	}else{
		startTime=now()
		Map msg2=null
		if(lg>0)msg2=timer "Execution stage complete.",rtD,-1
		Boolean success=true
		Boolean firstTime=true
		if(!(evntName in [sTIME,sASYNCREP])){
			if(lg>0)info "Execution stage started",rtD,1
			success=executeEvent(rtD,event)
			firstTime=false
		}
		if(evntName==sTIME && !(Boolean)event.recovery){
			rtD.stats.nextSchedule=lZERO
			rtD.nextSchedule=lZERO
			state.nextSchedule=lZERO
		}

		if(!ListAsync) ListAsync=[sHTTPR,sSTOREM,'lifx',sSENDE,sIFTTM]
		Boolean syncTime=true
		Boolean sv_syncTime=syncTime

		List<Map> schedules
		Map tt0
		Boolean a
		String semaName=app.id.toString()
		Map sch
		while(success && (Long)getPistonLimits.executionTime+(Long)rtD.timestamp-(Long)now() >(Long)getPistonLimits.scheduleRemain){
			// as long as no queued events
			if(!firstTime && serializationOn){
				Boolean inq=false
				getTheLock(semaName,sHNDLEVT)
				List<Map> evtQ=theQueuesVFLD[semaName]
				if(evtQ){ inq=true }
				releaseTheLock(semaName)
				if(inq){
					if(eric()) log.warn "found pending queued events"
					break
				}
			}

			tt0=getCachedMaps(sHNDLEVT)
			if(tt0!=null)schedules=(List<Map>)[]+(List<Map>)tt0.schedules
			else schedules=myPep ? (List<Map>)atomicState.schedules:(List<Map>)state.schedules

			if(schedules==null || schedules==(List<Map>)[] || schedules.size()==0)break

			if(evntName==sASYNCREP){
				event.schedule=schedules.sort{ Map it -> (Long)it.t }.find{ Map it -> (String)it.d==evntVal }
			}else{
				Long t=now()
				//anything less than scheduleVariance (270ms) in the future is considered due, we'll do some pause to sync with it
				//we're doing this because many times,the scheduler will run a job early
				sch=schedules.sort{ Map it -> (Long)it.t }.find{ Map it -> (Long)it.t<t+(Long)getPistonLimits.scheduleVariance }
				if(!sch) break

				evntName=sTIME
				evntVal=t.toString()
				event=[(sDATE):(Date)event.date,(sDEV):location,(sNM):evntName,(sVAL):t,schedule:sch]
			}

			if(event.schedule==null) break

			tt0=getCachedMaps(sHNDLEVT+sONE)
			if(tt0!=null)schedules=(List<Map>)[]+(List<Map>)tt0.schedules
			else schedules=myPep ? (List<Map>)atomicState.schedules:(List<Map>)state.schedules

			a=schedules.remove(event.schedule)
			if(tt0!=null){
				getTheLock(semaName,sX)
				theCacheVFLD[myId].schedules=schedules
				theCacheVFLD=theCacheVFLD
				releaseTheLock(semaName)
			}
			if(myPep)atomicState.schedules=schedules
			else state.schedules=schedules
			tt0=null

			if(!firstTime){
				rtD.cachePersist=[:]
				Map<String,Map>sysV=(Map<String,Map>)rtD.systemVars
				sysV[sDLLRINDX].v=null
				sysV[sDLLRDEVICE].v=null
				sysV[sDLLRDEVS].v=null
				sysV[sHTTPCONTENT].v=null
				sysV[sHTTPSTSCODE].v=null
				sysV[sHTTPSTSOK].v=null
				sysV[sIFTTTSTSCODE].v=null
				sysV[sIFTTTSTSOK].v=null
				rtD.systemVars=sysV

				event.date=new Date((Long)sch.t)
			}

			if(evntName==sASYNCREP){
				syncTime=false
				if((Boolean)rtD.eric) myDetail rtD,"async event $event"
				Integer rCode=(Integer)event.responseCode
				Boolean sOk=rCode>=200 && rCode<=299
				//noinspection GroovyFallthrough
				switch(evntVal){
					case sHTTPR:
						if(event.schedule.stack!=null){
							event.schedule.stack.response=event.responseData
							event.schedule.stack.json=event.jsonData
						}
						setSystemVariableValue(rtD,sHTTPCONTENT,(String)event.contentType)
					case sSTOREM:
						if((Map)event.setRtData){
							for(item in (Map<String,Object>)event.setRtData){
								rtD[(String)item.key]=item.value
							}
						}
						setSystemVariableValue(rtD,sHTTPSTSCODE,rCode)
						setSystemVariableValue(rtD,sHTTPSTSOK,sOk)
						break
					case 'lifx':
					case sSENDE:
						break
					case sIFTTM:
						setSystemVariableValue(rtD,sIFTTTSTSCODE,rCode)
						setSystemVariableValue(rtD,sIFTTTSTSOK,sOk)
						break
					default:
						error "unknown async event "+evntVal,rtD
				}
				evntName=sTIME
				event.name=evntName
				event.value=t
				evntVal=t.toString()
			}else{
				String ttyp=(String)event.schedule.d
				if(ttyp in ListAsync){
					error "Timeout Error "+ttyp,rtD
					syncTime=false
					Integer rCode=408
					Boolean sOk=false
					//noinspection GroovyFallthrough
					switch(ttyp){
						case sHTTPR:
							setSystemVariableValue(rtD,sHTTPCONTENT,sBLK)
							if(event.schedule.stack!=null) event.schedule.stack.response=null
						case sSTOREM:
							setSystemVariableValue(rtD,sHTTPSTSCODE,rCode)
							setSystemVariableValue(rtD,sHTTPSTSOK,sOk)
							break
						case 'lifx':
						case sSENDE:
							break
						case sIFTTM:
							setSystemVariableValue(rtD,sIFTTTSTSCODE,rCode)
							setSystemVariableValue(rtD,sIFTTTSTSOK,sOk)
							break
					}
				}
			}

			if(syncTime && strictSync){
				Long delay=Math.round((Long)event.schedule.t-dONE*(Long)now())
				if(delay>lZERO){
					Long ty0=(Long)getPistonLimits.scheduleVariance
					delay=delay<ty0 ? delay : ty0
					doPause("Synchronizing scheduled event, waiting for ${delay}ms".toString(),delay,rtD,true)
				}
			}
			if(lg>0 && firstTime){
				msg2=timer "Execution stage complete.",rtD,-1
				info "Execution stage started",rtD,1
			}
			success=executeEvent(rtD,event)
			firstTime=false
			syncTime=sv_syncTime
		} // end while

		rtD.stats.timing.e=elapseT(startTime)
		if(lg>0)info msg2,rtD
		if(!success)msg.m='Event processing failed'
		if(eric()){
			msg.m=(String)msg.m+' Total Pauses ms: '+((Long)rtD.tPause).toString()
			if(firstTime) msg.m=(String)msg.m+' found nothing to do'
		}
		finalizeEvent(rtD,msg,success)

		if((Boolean)rtD.logPExec){
			Map rtCE=(Map)rtD.currentEvent
			if(rtCE!=null){
				String desc='webCore piston \''+(String)app.label+'\' was executed'
				sendLocationEvent((sNM):'webCoRE',(sVAL):'pistonExecuted',isStateChange:true,displayed:false,linkText:desc,descriptionText:desc,
					data:[
						id:appId,
						(sNM):(String)app.label,
						event:[(sDATE):new Date((Long)rtCE.date),delay:(Long)rtCE.delay,duration:elapseT((Long)rtCE.date),(sDEV):"${rtD.event.device}".toString(),(sNM):(String)rtCE.name,(sVAL):rtCE.value,physical:(Boolean)rtCE.physical,index:(Integer)rtCE.index],
						state:[old:(String)rtD.state.old,new:(String)rtD.state.new]
					]
				)
			}
		}
	}

	if((Boolean)rtD.updateDevices) clearMyCache('updateDeviceList')

	List<String>data=rtD.collect{ (String)it.key }
	for(String item in data)a=rtD.remove(item)
	event=null
	rtD=null

// any queued events?
	String msgt=sNULL
	if(lg>2)msgt='Exiting'
	Boolean lckd=false
	while(doSerialization && semName!=sNULL){
		if(!lckd){ getTheLock(semName,sHNDLEVT+'2'); lckd=true }
		List<Map> evtQ=theQueuesVFLD[semName]
		if(!evtQ){
			if(theSemaphoresVFLD[semName] <= lS){
				if(lg>2)msgt='Released Lock and exiting'
				theSemaphoresVFLD[semName]=lZERO
				theSemaphoresVFLD=theSemaphoresVFLD
			}
			if(lckd){ releaseTheLock(semName); lckd=false }
			break
		}else{
			Map theEvent
			if(!lckd){ getTheLock(semName,sHNDLEVT+'3'); lckd=true }
			evtQ=theQueuesVFLD[semName]
			List<Map>evtList=evtQ.sort{(Long)it.t }
			theEvent=evtList.remove(0)
			Integer qsize=evtList.size()
			theQueuesVFLD[semName]=evtList
			theQueuesVFLD=theQueuesVFLD
			if(lckd){ releaseTheLock(semName); lckd=false }

			if(qsize>8) log.error "large queue size ${qsize}".toString()
			theEvent.date=new Date((Long)theEvent.t)
			handleEvents(theEvent,false,true)
		}
	}
	if(lg>2) log.debug msgt
}

@Field static List<String> ListAsync=[]

private Boolean executeEvent(Map rtD,event){
	String myS=sNULL
	if((Boolean)rtD.eric){
		myS='executeEvent'
		myDetail rtD,myS,1
	}
	Boolean ended=false
	try{
/*		if(event instanceof com.hubitat.hub.domain.Event){
			Map myEvent=[
				(sDATE):(Date)event.date,
				(sNM):(String)event.name,
				(sVAL):event.value,
				descriptionText:(String)event.descriptionText,
				unit:event.unit,
				physical:event.physical,
				jsonData:event.jsonData,
			]
			if(event.device!=null){
				myEvent.device=[id:event.device?.id,(sNM):event.device?.name,label:event.device?.label]
				if(event.device?.hubs!=null){
					myEvent.device.hubs=[(sT):'tt']
				}
			}
			rtD.event=myEvent
		}else*/ rtD.event=event
		Map pEvt=(Map)state.lastEvent
		if(pEvt==null)pEvt=[:]
		rtD.previousEvent=pEvt
		String evntName=(String)event.name
		Integer index=0 //event?.index ?: 0
		if(event.jsonData!=null){
			Map attribute=Attributes()[evntName]
			String attrI=attribute!=null ? (String)attribute.i:sNULL
			if(attrI!=sNULL && event.jsonData[attrI]){ // .i is the attribute to lookup
				index=((String)((Map)event.jsonData)[attrI]).toInteger()
			}
			if(!index)index=1
		}

		Map srcEvent=null
		rtD.args=[:]
		rtD.json=[:]
		rtD.response=[:]

		Map<String,Map>sysV=(Map<String,Map>)rtD.systemVars

		if(event!=null){
			rtD.args= evntName==sTIME && event.schedule!=null && event.schedule.args!=null && event.schedule.args instanceof Map ? (Map)event.schedule.args:(event.jsonData!=null ? event.jsonData:[:])
			if(evntName==sTIME && event.schedule!=null){
				srcEvent=(Map)event.schedule.evt
				Map tMap=(Map)event.schedule.stack
				if(tMap!=null){
					sysV[sDLLRINDX].v=(Double)tMap.index
					sysV[sDLLRDEVICE].v=(List)tMap.device
					sysV[sDLLRDEVS].v=(List)tMap.devices
					rtD.json=tMap.json ?: [:]
					rtD.response=tMap.response ?: [:]
					index=(Integer)srcEvent?.index ?: 0
// more to restore here?
					rtD.systemVars=sysV
				}
			}
		}
		setSystemVariableValue(rtD,sDOLARGS,rtD.args)
		sysV=(Map<String,Map>)rtD.systemVars

		String theDevice=srcEvent!=null ? (String)srcEvent.device:sNULL
		def theDevice1=theDevice==sNULL && event.device ? event.device.id:null
		String theFinalDevice=theDevice!=sNULL ? theDevice : (theDevice1!=null ? (!isDeviceLocation(event.device) ? hashId(theDevice1.toString()) : (String)rtD.locationId) : (String)rtD.locationId)
		Map myEvt=[
			(sDATE):(Long)((Date)event.date).getTime(),
			delay:rtD.stats?.timing?.d ? (Long)rtD.stats.timing.d : lZERO,
			(sDEV):theFinalDevice,
			index:index
		]
		if(srcEvent!=null){
			myEvt=myEvt + [
				(sNM):(String)srcEvent.name,
				(sVAL):srcEvent.value,
				descriptionText:(String)srcEvent.descriptionText,
				unit:srcEvent.unit,
				physical:(Boolean)srcEvent.physical,
			]
		}else{
			myEvt=myEvt + [
				(sNM):evntName,
				(sVAL):event.value,
				descriptionText:(String)event.descriptionText,
				unit:event.unit,
				physical:!!event.physical,
			]
		}
		rtD.currentEvent=myEvt
		state.lastEvent=myEvt

		rtD.conditionStateChanged=false
		rtD.pistonStateChanged=false
		rtD.ffTo=0
		rtD.statementLevel=0
		rtD.break=false
		rtD.resumed=false
		rtD.terminated=false
		if(evntName==sTIME){
			rtD.ffTo=(Integer)event.schedule.i
		}
		sysV[sPEVDATE].v=pEvt.date ?: now()
		sysV[sPEVDELAY].v=pEvt.delay ?: lZERO
		sysV[sPEVDEV].v=[pEvt.device]
		sysV[sPEVDEVINDX].v=pEvt.index ?: 0
		sysV[sPEVATTR].v=pEvt.name ?: sBLK
		sysV[sPEVDESC].v=pEvt.descriptionText ?: sBLK
		sysV[sPEVVALUE].v=pEvt.value ?: sBLK
		sysV[sPEVUNIT].v=pEvt.unit ?: sBLK
		sysV[sPEVPHYS].v=!!pEvt.physical

		sysV[sCURDATE].v=(Long)myEvt.date
		sysV[sCURDELAY].v=(Long)myEvt.delay
		sysV[sCURDEV].v=[myEvt.device]
		sysV[sCURDEVINDX].v=myEvt.index!=sBLK && myEvt.index!=null? (Integer)myEvt.index:0
		sysV[sCURATTR].v=(String)myEvt.name
		sysV[sCURDESC].v=(String)myEvt.descriptionText
		sysV[sCURVALUE].v=myEvt.value
		sysV[sCURUNIT].v=myEvt.unit
		sysV[sCURPHYS].v=(Boolean)myEvt.physical
		rtD.systemVars=sysV

		rtD.stack=[(sC):0,(sS):0,cs:[],ss:[]]
		try{
			Boolean allowed=!rtD.piston.r || (Integer)rtD.piston.r.size()==0 || evaluateConditions(rtD,(Map)rtD.piston,sR,true)
			rtD.restricted=!rtD.piston.o?.aps && !allowed //allowPreScheduled tasks to execute during restrictions
			if(allowed || (Integer)rtD.ffTo!=0){
				if((Integer)rtD.ffTo==-3){
					//device related time schedules
					if(!(Boolean)rtD.restricted){
						def data=event.schedule.d
						if(data!=null && (String)data.d && (String)data.c){
							//we have a device schedule, execute it
							def device=getDevice(rtD,(String)data.d)
							if(device!=null){
								//executing scheduled physical command
								//used by fades,flashes,etc.
								executePhysicalCommand(rtD,device,(String)data.c,data.p,lZERO,sNULL,true)
							}
						}
					}
				}else{
					if(executeStatements(rtD,(List)rtD.piston.s)){
						ended=true
						tracePoint(rtD,sEND,lZERO,0)
					}
					processSchedules rtD
				}
			}else{
				if((Integer)rtD.logging>2)debug 'Piston execution aborted due to restrictions in effect',rtD
				//we need to run through all to update stuff
				rtD.ffTo=-9
				Boolean a=executeStatements(rtD,(List)rtD.piston.s)
				ended=true
				tracePoint(rtD,sEND,lZERO,0)
				processSchedules rtD
			}
			if(!ended){ ended=true; tracePoint(rtD,sBREAK,lZERO,0) }
		}catch(all){
			error 'An error occurred while executing the event:',rtD,-2,all
		}
		if((Boolean)rtD.eric) myDetail rtD,myS+' Result:TRUE',-1
		return true
	}catch(all){
		error 'An error occurred within executeEvent:',rtD,-2,all
	}
	if(!ended){ ended=true; tracePoint(rtD,sBREAK,lZERO,0) }
	processSchedules rtD
	if((Boolean)rtD.eric) myDetail rtD,myS+' Result:FALSE',-1
	return false
}

@Field static final List<String> cleanData=['allDevices','cachePersist','mem','break','powerSource','oldLocationId','incidents','lstarted','lended','pStart','pEnd','generatedIn','ended','semaphoreDelay','vars','stateAccess','author','bin','build','newCache','mediaData','weather','logs','trace','systemVars','localVars','currentAction','previousEvent','json','response','cache','store','settings','locationModeId','locationId','coreVersion','hcoreVersion','cancelations','conditionStateChanged','pistonStateChanged','ffTo','resumed','terminated','instanceId','wakingUp','statementLevel','args','nfl','temp']

private void finalizeEvent(Map rtD,Map initialMsg,Boolean success=true){
	Long startTime=now()
	Boolean myPep=(Boolean)rtD.pep

	processSchedules(rtD,true)

	if(success){
		if((Integer)rtD.logging>0)info initialMsg,rtD
	}else error initialMsg,rtD

	updateLogs(rtD,(Long)rtD.timestamp)

	String myId=(String)rtD.id

	rtD.trace.d=elapseT((Long)rtD.trace.t)

	//flush the new cache value
	for(item in (Map)rtD.newCache) ((Map)rtD.cache)[(String)item.key]=item.value

	//overwrite state,might have changed meanwhile
	String str='finalize '
	Map t0=getCachedMaps(str)
	String semaName=app.id.toString()
	if(t0!=null){
		getTheLock(semaName,str)
		theCacheVFLD[myId].cache=[:]+(Map)rtD.cache
		theCacheVFLD[myId].store=[:]+(Map)rtD.store
		theCacheVFLD[myId].state=[:]+(Map)rtD.state
		theCacheVFLD[myId].trace=[:]+(Map)rtD.trace
		theCacheVFLD=theCacheVFLD
		releaseTheLock(semaName)
	}
	if(myPep){
		atomicState.cache=(Map)rtD.cache
		atomicState.store=(Map)rtD.store
		atomicState.state=[:]+(Map)rtD.state
		atomicState.trace=(Map)rtD.trace
	}else{
		state.cache=(Map)rtD.cache
		state.store=(Map)rtD.store
		state.state=[:]+(Map)rtD.state
		state.trace=(Map)rtD.trace
	}

//remove large stuff
	def aa
	for(String foo in cleanData) aa=rtD.remove(foo)
	if(!(rtD.event instanceof com.hubitat.hub.domain.Event)){
		if(rtD.event?.responseData)rtD.event.responseData=[:]
		if(rtD.event?.jsonData)rtD.event.jsonData=[:]
		if(rtD.event?.setRtData)rtD.event.setRtData=[:]
		if(rtD.event?.schedule?.stack)rtD.event.schedule.stack=[:]
	}

	if((Boolean)rtD.updateDevices) updateDeviceList(rtD,((List)((Map)rtD.devices)*.value.id))
	aa=rtD.remove('devices')

	Boolean a
	if(rtD.gvCache!=null || rtD.gvStoreCache!=null){
		LinkedHashMap tpiston=(LinkedHashMap)rtD.piston
		rtD.piston=[:]
		rtD.piston.z=(String)tpiston.z
		tpiston=null
		if(rtD.gvCache!=null){
			String lockTyp='finalize'
			String semName=sTGBL
			String wName=parent.id.toString()
			getTheLock(semName,lockTyp)
			for(var in (Map<String,Map>)rtD.gvCache){
				Map vars=globalVarsVFLD[wName]
				String varName=(String)var.key
				if(varName && (Boolean)varName.startsWith(sAT) && vars[varName] && var.value.v!=vars[varName].v){
					globalVarsVFLD[wName][varName].v=var.value.v
					globalVarsVFLD=globalVarsVFLD
				}
			}
			releaseTheLock(semName)
		}
		parent.pCallupdateRunTimeData(rtD)
		aa=rtD.remove('gvCache')
		aa=rtD.remove('gvStoreCache')
		rtD.initGStore=false
	}else{
		Map myRt=shortRtd(rtD)
		myRt.t=now()
		parent.pCallupdateRunTimeData(myRt)
	}
	rtD.piston=null

	rtD.stats.timing.u=elapseT(startTime)
//update graph data
	Map stats
	if(myPep)stats=(Map)atomicState.stats
	else stats=(Map)state.stats
	stats=stats ?: [:]

	List<Map> tlist=(List<Map>)stats.timing ?: []
	Map lastST= tlist.size() ? [:]+tlist.last() : null
	Map newMap=[:]+(Map)rtD.stats.timing
	if(lastST && newMap){
		lastST.t=(Long)newMap.t-10L
		a=tlist.push(lastST)
	}
	a=tlist.push(newMap)
	Integer t1=settings.maxStats!=null ? (Integer)settings.maxStats: (Integer)getPistonLimits.maxStats
	if(t1<=0)t1=(Integer)getPistonLimits.maxStats
	if(t1<2)t1=2
	Integer t2=tlist.size()
	if(t2>t1)tlist=tlist[t2-t1..t2-1]

	stats.timing=tlist
	if(myPep)atomicState.stats=stats
	else state.stats=stats
	rtD.stats.timing=null

	t0=getCachedMaps(str+sONE)
	if(t0!=null){
		Long totTime=elapseT((Long)rtD.timestamp)
		t1=20
		String t4=mem()
		getTheLock(semaName,str+sONE)
		theCacheVFLD[myId].mem=t4
		theCacheVFLD[myId].runStats=[:]+(Map)rtD.curStat
		List hisList=(List)theCacheVFLD[myId].runTimeHis
		Boolean b=hisList.push(totTime)
		t2=hisList.size()
		if(t2>t1) hisList=hisList[t2-t1..t2-1]
		theCacheVFLD[myId].runTimeHis=hisList
		theCacheVFLD=theCacheVFLD
		releaseTheLock(semaName)
	}
}

private void processSchedules(Map rtD,Boolean scheduleJob=false){
	Boolean myPep=(Boolean)rtD.pep
	String str='processSchedules'

	List<Map> schedules
	Map t0=getCachedMaps(str)
	if(t0!=null)schedules=(List<Map>)[]+(List<Map>)t0.schedules
	else schedules=myPep ? (List<Map>)atomicState.schedules:(List<Map>)state.schedules

	//if automatic piston states,we set it based on the autoNew - if any
	if(rtD.piston.o?.mps==null || !rtD.piston.o.mps) rtD.state.new=(String)rtD.state.autoNew ?: sTRUE
	rtD.state.old=(String)rtD.state.new

	Boolean a
	if((Boolean)rtD.cancelations.all){
		//a=schedules.removeAll{ (Integer)it.i>0 }
		//if we have any other pending -3 events (device schedules),we cancel them all
		a=schedules.removeAll{ Map it -> (Integer)it.i>0 || (Integer)it.i==-3 }
	}

	//cancel statements
	a=schedules.removeAll{ Map schedule -> !!((List<Map>)rtD.cancelations.statements).find{ Map cancelation -> (Integer)cancelation.id==(Integer)schedule.s && (!cancelation.data || ((String)cancelation.data==(String)schedule.d))}}

	//cancel on conditions
	for(Integer cid in (List<Integer>)rtD.cancelations.conditions){
		a=schedules.removeAll{ Map it -> cid in (List)it.cs }
	}

	//cancel on piston state change
	if((Boolean)rtD.pistonStateChanged){
		a=schedules.removeAll{ Map it -> (Integer)it.ps!=0 }
	}

	rtD.cancelations=[statements:[],conditions:[],all:false]
	schedules=(schedules+(List<Map>)rtD.schedules)//.sort{ (Long)it.t }

	if(myPep)atomicState.schedules=schedules
	else state.schedules=(List<Map>)[]+schedules
	String myId=(String)rtD.id
	String semaName=app.id.toString()
	t0=getCachedMaps(str+sONE)
	if(t0!=null){
		getTheLock(semaName,sT)
		theCacheVFLD[myId].schedules=(List<Map>)[]+schedules
		theCacheVFLD=theCacheVFLD
		releaseTheLock(semaName)
	}

	if(scheduleJob){
		Long nextT=lZERO
		Integer ssz=schedules.size()
		if(ssz>0){
			Map tnext=((List<Map>)schedules).sort{ Map it -> (Long)it.t }[0]
			nextT=(Long)tnext.t
			Long t=(nextT-now())+30L
			t=(t<250L ? 250L:t)
			runInMillis(t,timeHandler,[data: tnext])

			if((Integer)rtD.logging>0) info 'Setting up scheduled job for '+formatLocalTime(nextT)+' (in '+t.toString()+'ms)' + ((ssz)>1 ? ',with ' + (ssz-1).toString() + ' more job' + (ssz>2 ? sS : sBLK) + ' pending' : sBLK),rtD
		}
		if(nextT==lZERO && (Long)rtD.nextSchedule!=lZERO){
			unschedule(timeHandler)
		}

		rtD.nextSchedule=nextT
		rtD.stats.nextSchedule=nextT
		state.nextSchedule=nextT
		t0=getCachedMaps(str+'2')
		if(t0!=null){
			getTheLock(semaName,sT+sONE)
			theCacheVFLD[myId].nextSchedule=nextT
			theCacheVFLD=theCacheVFLD
			releaseTheLock(semaName)
		}
	}
	rtD.schedules=[]
}

private void updateLogs(Map rtD,Long lastExecute=null){
	if(!rtD || !rtD.logs)return

	String str='updateLogs'
	String myId=(String)rtD.id
	Map cacheMap
	String semaName=app.id.toString()
	if(lastExecute!=null){
		state.lastExecuted=lastExecute
		cacheMap=getCachedMaps(str)
		if(cacheMap!=null){
			getTheLock(semaName,sE)
			theCacheVFLD[myId].lastExecuted=lastExecute
			theCacheVFLD[myId].temp=[:]+(Map)rtD.temp
			theCacheVFLD[myId].cachePersist=[:]+(Map)rtD.cachePersist
			theCacheVFLD=theCacheVFLD
			releaseTheLock(semaName)
		}
	}

	if(((List)rtD.logs).size()>1){
		Boolean myPep=(Boolean)rtD.pep
		Integer lim=settings.maxLogs!=null ? (Integer)settings.maxLogs:(Integer)getPistonLimits.maxLogs
		if(lim<0)lim=(Integer)getPistonLimits.maxLogs

		List t0
		cacheMap=getCachedMaps(str+sONE)
		if(cacheMap!=null)t0=[]+(List)cacheMap.logs
		else t0=myPep ? (List)atomicState.logs:(List)state.logs
		List logs=[]+(List)rtD.logs+t0
		if(lim>=0){
			Integer lsz=logs.size()
			if(lim==0 || lsz==0) logs=[]
			else{
				if(lim< lsz-1){
					logs=logs[0..lim]
					lsz=logs.size()
				}
				if(lsz>50){
					state.logs=logs //this mixes state and AS
					if(state.toString().size()>75000){
						lim -= Math.min(50L,Math.round(lim/2.0D)).toInteger()
						logs=logs[0..lim]
					}
				}
			}
		}
		cacheMap=getCachedMaps(str+'2')
		if(cacheMap!=null){
			getTheLock(semaName,sE+sONE)
			theCacheVFLD[myId].logs=logs
			theCacheVFLD=theCacheVFLD
			releaseTheLock(semaName)
		}
		if(myPep)atomicState.logs=logs
		else state.logs=logs
	}
	rtD.logs=[]
}

private Boolean executeStatements(Map rtD,List<Map> statements,Boolean async=false){
	rtD.statementLevel=(Integer)rtD.statementLevel+1
	for(Map statement in statements){
		//only execute statements that are enabled
		Boolean disab=statement.di!=null && (Boolean)statement.di
		if(!disab && !executeStatement(rtD,statement,async)){
			//stop processing
			rtD.statementLevel=(Integer)rtD.statementLevel-1
			return false
		}
	}
	//continue processing
	rtD.statementLevel=(Integer)rtD.statementLevel-1
	return true
}

@Field static List<String> s0=[]
@Field static List<String> s1=[]

private Boolean executeStatement(Map rtD,Map statement,Boolean asynch=false){
	String str='executeStatement'
	//if rtD.ffTo is a positive, non-zero number, we need to fast forward through all branches
	//until we find the task with an id equal to that number, then we play nicely after that
	if(statement==null)return false
	Integer lg=(Integer)rtD.logging
	Integer stateNum=statement.$!=null ? (Integer)statement.$:0
	if((Integer)rtD.ffTo==0){
		String sMsg="Skipping execution for statement #${stateNum} because "
		switch((String)statement.tep){ // Task Execution Policy
			case sC:
				if(!(Boolean)rtD.conditionStateChanged){
					if(lg>2)debug sMsg+'condition state did not change',rtD
					return true
				}
				break
			case sP:
				if(!(Boolean)rtD.pistonStateChanged){
					if(lg>2)debug sMsg+'piston state did not change',rtD
					return true
				}
				break
			case sB:
				if( !(Boolean)rtD.conditionStateChanged && !(Boolean)rtD.pistonStateChanged){
					if(lg>2)debug sMsg+'neither condition state nor piston state changed',rtD
					return true
				}
				break
		}
	}
	String stateType=(String)statement.t
	String mySt=sNULL
	if((Boolean)rtD.eric){
		mySt=str+sSPC+stateType
		myDetail rtD,mySt,1
	}
	Boolean a=((List<Integer>)rtD.stack.ss).push((Integer)rtD.stack.s)
	rtD.stack.s=stateNum
	Long t=now()
	Boolean value=true
	Integer c=(Integer)rtD.stack.c
	Boolean stacked=true /* cancelable on condition change */
	if(stacked)a=((List<Integer>)rtD.stack.cs).push(c)
	Boolean svCSC=(Boolean)rtD.conditionStateChanged
	//def parentAsync=asynch
	Double svIndex=(Double)rtD.systemVars[sDLLRINDX].v
	List svDevice=(List)rtD.systemVars[sDLLRDEVICE].v

	if(!s0) s0=[sEVERY,sON]
	if(!s1) s1=[sWHILE,sREPEAT,sFOR,sEACH]

	Boolean selfAsync= (String)statement.a==sONE || (stateType in s0) // execution method
	Boolean async=asynch || selfAsync
	Boolean myPep=(Boolean)rtD.pep
	Boolean perform=false
	Boolean repeat=true
	Double index=null
	Boolean allowed=!(List)statement.r || ((List)statement.r).size()==0 || evaluateConditions(rtD,statement,sR,async)
	if(allowed || (Integer)rtD.ffTo!=0){
		while(repeat){
			switch(stateType){
				case sACTION:
					value=executeAction(rtD,statement,async)
					break
				case sIF:
				case sWHILE:
					//check conditions for if and while
					perform=evaluateConditions(rtD,statement,sC,async)
					//override current condition so child statements can cancel on it
					rtD.stack.c=stateNum
					if((Integer)rtD.ffTo==0 && perform && !rtD.piston.o?.mps && stateType==sIF && (Integer)rtD.statementLevel==1){
						//automatic piston state
						rtD.state.autoNew=sTRUE
					}
					if(perform || (Integer)rtD.ffTo!=0){
						if(!executeStatements(rtD,(List)statement.s,async)){
							//stop processing
							value=false
							if((Integer)rtD.ffTo==0)break
						}
						value=true
						if((Integer)rtD.ffTo==0)break
					}
					if(!perform || (Integer)rtD.ffTo!=0){
						if(stateType==sIF){
							//look for else-ifs
							for(Map elseIf in (List<Map>)statement.ei){
								perform=evaluateConditions(rtD,elseIf,sC,async)
								if(perform || (Integer)rtD.ffTo!=0){
									if(!executeStatements(rtD,(List)elseIf.s,async)){
										//stop processing
										value=false
										if((Integer)rtD.ffTo==0)break
									}
									value=true
									if((Integer)rtD.ffTo==0)break
								}
							}
							if((Integer)rtD.ffTo==0 && !rtD.piston.o?.mps && (Integer)rtD.statementLevel==1){
								//automatic piston state
								rtD.state.autoNew=sFALSE
							}
							if((!perform || (Integer)rtD.ffTo!=0) && !executeStatements(rtD,(List)statement.e,async)){
								//stop processing
								value=false
								if((Integer)rtD.ffTo==0)break
							}
						}
					}
					break
				case sEVERY:
					Boolean ownEvent= rtD.event!=null && (String)rtD.event.name==sTIME && rtD.event.schedule!=null && (Integer)rtD.event.schedule.s==stateNum && (Integer)rtD.event.schedule.i<0

					List<Map> schedules
					Map t0=getCachedMaps(str)
					if(t0!=null)schedules=[]+(List<Map>)t0.schedules
					else schedules=myPep ? (List<Map>)atomicState.schedules:(List<Map>)state.schedules
					if(ownEvent || !schedules.find{ Map it -> (Integer)it.s==stateNum }){
						//if the time has come for our timer, schedule the next timer
						// NOT VALID: if no next time is found quick enough, a new schedule with i=-2 will be setup so that a new attempt can be made at a later time
						if(ownEvent)rtD.ffTo=0
						scheduleTimer(rtD,statement, ownEvent ? (Long)rtD.event.schedule.t:lZERO)
					}
					//override current condition so child statements can cancel on it
					rtD.stack.c=stateNum
					if(ownEvent)rtD.ffTo=0
					if((Integer)rtD.ffTo!=0 || (ownEvent && allowed && !(Boolean)rtD.restricted)){
						//don't want to run this if there are piston restrictions in effect
						//only execute the every if i=-1 (for rapid timers with large restrictions i.e. every second, but only on Mondays)
						// NOT VALID: need to make sure we don't block execution while trying to find the next execution scheduled time, so we give up after too many attempts and schedule a rerun with i=-2 to give us the chance to try again at that later time
						if((Integer)rtD.ffTo!=0 || (Integer)rtD.event.schedule.i==-1)a=executeStatements(rtD,(List)statement.s,true)
						//we always exit a timer, this only runs on its own schedule, nothing else is executed
						if(ownEvent)rtD.terminated=true
						value=false
						break
					}
					value=true
					break
				case sREPEAT:
					//override current condition so child statements can cancel on it
					rtD.stack.c=stateNum
					if(!executeStatements(rtD,(List)statement.s,async)){
						//stop processing
						value=false
						if((Integer)rtD.ffTo==0)break
					}
					value=true
					perform= !evaluateConditions(rtD,statement,sC,async)
					break
				case sON:
					perform=false
					if((Integer)rtD.ffTo==0){
						//look to see if any of the event matches
						String deviceId= rtD.event.device!=null ? hashId(rtD.event.device.id):sNULL
						for(event in (List<Map>)statement.c){
							def operand=event.lo
							if(operand!=null && (String)operand.t){
								switch((String)operand.t){
									case sP:
										if(deviceId!=sNULL && (String)rtD.event.name==(String)operand.a && (List)operand.d!=[] && deviceId in expandDeviceList(rtD,(List)operand.d,true)) perform=true
										break
									case sV:
										if((String)rtD.event.name==(String)operand.v) perform=true
										break
									case sX:
										String operX=(String)operand.x
										if(rtD.event.value==operX && (String)rtD.event.name==(String)rtD.instanceId+sDOT+operX) perform=true
										break
								}
							}
							if(perform)break
						}
					}
					value= (Integer)rtD.ffTo!=0 || perform ? executeStatements(rtD,(List)statement.s,async):true
					break
				case sFOR:
				case sEACH:
					List devices=[]
					Double startValue=dZERO
					Double endValue
					Double stepValue=dONE
					Integer dsiz=devices.size()
					if(stateType==sEACH){
						List t0=(List)((Map)evaluateOperand(rtD,null,(Map)statement.lo)).v
						devices=t0 ?: []
						dsiz=devices.size()
						endValue=dsiz-dONE
					}else{
						startValue=(Double)evaluateScalarOperand(rtD,statement,(Map)statement.lo,null,sDEC).v
						endValue=(Double)evaluateScalarOperand(rtD,statement,(Map)statement.lo2,null,sDEC).v
						Double t0=(Double)evaluateScalarOperand(rtD,statement,(Map)statement.lo3,null,sDEC).v
						stepValue=t0 ?: dONE
					}
					String counterVariable=(String)getVariable(rtD,(String)statement.x).t!=sERROR ? (String)statement.x:sNULL
					String sidx='f:'+stateNum.toString()
					if( (startValue<=endValue && stepValue>dZERO) || (startValue>=endValue && stepValue<dZERO) || (Integer)rtD.ffTo!=0){
						//initialize the for loop
						if((Integer)rtD.ffTo!=0)index=(Double)cast(rtD,((Map)rtD.cache)[sidx],sDEC)
						if(index==null){
							index=(Double)cast(rtD,startValue,sDEC)
							//index=startValue
							rtD.cache[sidx]=index
						}
						rtD.systemVars[sDLLRINDX].v=index
						if(stateType==sEACH && ((Integer)rtD.ffTo==0||(Integer)rtD.ffTo==-9))setSystemVariableValue(rtD,sDLLRDEVICE,index<dsiz ? [devices[index.toInteger()]]:[])
						if(counterVariable!=sNULL && (Integer)rtD.ffTo==0)def m=setVariable(rtD,counterVariable,stateType==sEACH ? (index<dsiz ? [devices[index.toInteger()]]:[]):index)
						//do the loop
						perform=executeStatements(rtD,(List)statement.s,async)
						if(!perform){
							//stop processing
							value=false
							if((Boolean)rtD.break){
								//reached a break, want to continue execution outside of the for
								value=true
								rtD.break=false
								//perform=false
							}
							break
						}
						//don't do the rest if we're fast forwarding
						if((Integer)rtD.ffTo!=0)break
						index=index+stepValue
						rtD.systemVars[sDLLRINDX].v=index
						if(stateType==sEACH && (Integer)rtD.ffTo==0)setSystemVariableValue(rtD,sDLLRDEVICE,index<dsiz ? [devices[index.toInteger()]]:[])
						if(counterVariable!=sNULL && (Integer)rtD.ffTo==0)def n=setVariable(rtD,counterVariable,stateType==sEACH ? (index<dsiz ? [devices[index.toInteger()]]:[]):index)
						rtD.cache[sidx]=index
						if((stepValue>dZERO && index>endValue) || (stepValue<dZERO && index<endValue)){
							perform=false
							break
						}
					}
					break
				case sSWITCH:
					Map lo=[operand: (Map)statement.lo,values: (List)evaluateOperand(rtD,statement,(Map)statement.lo)]
					//go through all cases
					Boolean found=false
					Boolean implicitBreaks= (String)statement.ctp==sI // case traversal policy
					Boolean fallThrough=!implicitBreaks
					perform=false
					if(lg>2)debug "Evaluating switch with values $lo.values",rtD
					for(Map _case in (List<Map>)statement.cs){
						Map ro=[operand: (Map)_case.ro,values: (List)evaluateOperand(rtD,_case,(Map)_case.ro)]
						Map ro2= (String)_case.t==sR ? [operand: (Map)_case.ro2,values: (List)evaluateOperand(rtD,_case,(Map)_case.ro2,null,false,true)]:null
						perform=perform || evaluateComparison(rtD,((String)_case.t==sR ? 'is_inside_of_range' : 'is'),lo,ro,ro2)
						found=found || perform
						if(perform || (found && fallThrough)|| (Integer)rtD.ffTo!=0){
							Integer ffTo=(Integer)rtD.ffTo
							if(!executeStatements(rtD,(List)_case.s,async)){
								//stop processing
								value=false
								if((Boolean)rtD.break){
									//reached a break, want to continue execution outside of the switch
									value=true
									found=true
									fallThrough=false
									rtD.break=false
								}
								if((Integer)rtD.ffTo==0){
									break
								}
							}
							//if the fast forwarding ended during this execution, assume found is true
							found=found || (ffTo!=(Integer)rtD.ffTo)
							value=true
							//if implicit breaks
							if(implicitBreaks && (Integer)rtD.ffTo==0){
								fallThrough=false
								break
							}
						}
					}
					if(statement.e && ((List)statement.e).size() && (value || (Integer)rtD.ffTo!=0) && (!found || fallThrough || (Integer)rtD.ffTo!=0)){
						//no case found, let's do the default
						if(!executeStatements(rtD,(List)statement.e,async)){
							//stop processing
							value=false
							if((Boolean)rtD.break){
								//reached a break, want to continue execution outside of the switch
								value=true
								rtD.break=false
							}
							if((Integer)rtD.ffTo==0)break
						}
					}
					break
				case sDO:
					value=executeStatements(rtD,(List)statement.s,async)
					break
				case sBREAK:
					if((Integer)rtD.ffTo==0){
						rtD.break=true
					}
					value=false
					break
				case sEXIT:
					if((Integer)rtD.ffTo==0){
						vcmd_setState(rtD,null,[(String)cast(rtD,((Map)evaluateOperand(rtD,null,(Map)statement.lo)).v,sSTR)])
						rtD.terminated=true
					}
					value=false
					break
			}
			//break the loop
			if((Integer)rtD.ffTo!=0 || stateType==sIF)perform=false

			//is this statement a loop
			Boolean loop=(stateType in s1)
			if(loop && !value && (Boolean)rtD.break){
				//someone requested a break from the loop, we're doing it
				rtD.break=false
				//but we're allowing the rest to continue
				value=true
				perform=false
			}
			//repeat the loop?
			repeat=perform && value && loop && (Integer)rtD.ffTo==0

			Long overBy=checkForSlowdown(rtD)
			if(overBy>lZERO){
				Long delay=(Long)getPistonLimits.taskShortDelay
				if(overBy>(Long)getPistonLimits.useBigDelay){
					delay=(Long)getPistonLimits.taskLongDelay
				}
				String mstr=str+":Execution time exceeded by ${overBy}ms,".toString()
				if(repeat && overBy>(Long)getPistonLimits.executionTime){
					error mstr+'Terminating',rtD
					rtD.terminated=true
					repeat=false
				}else{
					doPause(mstr+'Waiting for '+delay+'ms',delay,rtD)
				}
			}
		} // end while
	}
	if((Integer)rtD.ffTo==0){
		Map schedule
		if(stateType==sEVERY){
			Map t0=((List<Map>)rtD.schedules).find{ (Integer)it.s==stateNum}
			if(t0==null){
				List<Map> schedules
				Map t1=getCachedMaps(str+sONE)
				if(t1!=null)schedules=[]+(List<Map>)t1.schedules
				else schedules=myPep ? (List<Map>)atomicState.schedules:(List<Map>)state.schedules
				schedule=schedules.find{ Map it -> (Integer)it.s==stateNum }
			}else schedule=t0
		}
		String myS="s:${stateNum}".toString()
		Long myL=elapseT(t)
		if(schedule!=null){
			//timers need to show the remaining time
			tracePoint(rtD,myS,myL,elapseT((Long)schedule.t))
		}else{
			tracePoint(rtD,myS,myL,value)
		}
	}
	//if(statement.a==sONE){
	//when an async action requests the thread termination, we continue to execute the parent
		//when an async action terminates as a result of a time event, we exit completely
		//value=(rtD.event.name!=sTIME)
	//}
	if(selfAsync){
		//if running in async mode, return true (to continue execution)
		value=!(Boolean)rtD.resumed
		rtD.resumed=false
	}
	if((Boolean)rtD.terminated){
		value=false
	}
	//restore current condition
	rtD.stack.c=c
	if(stacked){ Integer tc=((List<Integer>)rtD.stack.cs).pop() }
	rtD.stack.s=(Integer)((List<Integer>)rtD.stack.ss).pop()
	rtD.systemVars[sDLLRINDX].v=svIndex
	rtD.systemVars[sDLLRDEVICE].v=svDevice
	rtD.conditionStateChanged=svCSC
	Boolean ret=value || (Integer)rtD.ffTo!=0
	if((Boolean)rtD.eric) myDetail rtD,mySt+" result:"+ret.toString(),-1
	return ret
}

private Long checkForSlowdown(Map rtD){
	//return how long over the time limit
	Long t2=(Long)rtD.tPause
	t2=t2!=null ? t2: lZERO
	Long curRunTime=elapseT((Long)rtD.timestamp)-t2-(Long)getPistonLimits.slTime
	Long overBy= curRunTime>lZERO ? curRunTime : lZERO
	return overBy
}

private void doPause(String mstr,Long delay,Map rtD,Boolean ign=false){
	Long actDelay=lZERO
	Long t0=now()
	if((Long)rtD.lastPause==null || ign || (t0-(Long)rtD.lastPause)>(Long)getPistonLimits.slTime){
		if((Integer)rtD.logging>1)trace mstr+'; lastPause: '+rtD.lastPause,rtD
		rtD.lastPause=t0
		pauseExecution(delay)
		Long t1=now()
		actDelay=t1-t0
		Long t2=(Long)rtD.tPause
		t2=t2!=null ? t2: lZERO
		rtD.tPause=t2+actDelay
		rtD.lastPause=t1
		t2=(Long)state.pauses
		t2=t2!=null ? t2 : lZERO
		state.pauses=t2+1L
	}
	//return actDelay
}

private Boolean executeAction(Map rtD,Map statement,Boolean async){
	String mySt=sNULL
	if((Boolean)rtD.eric){
		mySt='executeAction'
		myDetail rtD,mySt,1
	}
	List svDevices=(List)rtD.systemVars[sDLLRDEVS].v
	//if override
	if((Integer)rtD.ffTo==0 && (String)statement.tsp!=sA){ // Task scheduling policy
		cancelStatementSchedules(rtD,(Integer)statement.$)
	}
	Boolean result=true
	List<String> deviceIds=expandDeviceList(rtD,(List)statement.d)
	List devices=deviceIds.collect{ String it -> getDevice(rtD,it)}
	rtD.currentAction=statement
	for(Map task in (List<Map>)statement.k){
		if(task.$!=null && (Integer)task.$==(Integer)rtD.ffTo){
			//resuming a waiting task, need to bring back the devices
			if(rtD.event && (Map)rtD.event.schedule && (Map)rtD.event.schedule.stack){
				rtD.systemVars[sDLLRINDX].v=(Double)rtD.event.schedule.stack.index
				rtD.systemVars[sDLLRDEVICE].v=(List)rtD.event.schedule.stack.device
				if(rtD.event.schedule.stack.devices instanceof List){
					deviceIds=(List)rtD.event.schedule.stack.devices
					rtD.systemVars[sDLLRDEVS].v=deviceIds
					devices=deviceIds.collect{ getDevice(rtD,(String)it)}
				}
			}
		}
		rtD.systemVars[sDLLRDEVS].v=deviceIds
		result=executeTask(rtD,devices,statement,task,async)
		if(!result && (Integer)rtD.ffTo==0){
			break
		}
	}
	rtD.remove('currentAction')
	rtD.systemVars[sDLLRDEVS].v=svDevices
	if((Boolean)rtD.eric) myDetail rtD,mySt+" result:$result".toString(),-1
	return result
}

private Boolean executeTask(Map rtD,List devices,Map statement,Map task,Boolean async){
	Long t=now()
	String myS='t:'+(Integer)task.$
	if((Integer)rtD.ffTo!=0){
		if((Integer)task.$==(Integer)rtD.ffTo){
			//finally found the resuming point, play nicely from hereon
			tracePoint(rtD,myS,elapseT(t),null)
			rtD.ffTo=0
			//restore $device and $devices
			rtD.resumed=true
		}
		//we're not doing anything, we're fast forwarding
		return true
	}
	if(task.m!=null && task.m instanceof List && ((List)task.m).size()>0){
		if(rtD.locationModeId==null){
			def mode=location.getCurrentMode()
			rtD.locationModeId=mode!=null ? hashId(mode.getId()):null
		}
		if(!((String)rtD.locationModeId in (List)task.m)){
			if((Integer)rtD.logging>2)debug "Skipping task ${(Integer)task.$} because of mode restrictions",rtD
			return true
		}
	}
	String mySt=sNULL
	if((Boolean)rtD.eric){
		mySt='executeTask '+(String)task.c+" async:${async} devices: ${devices.size()} ".toString()
		myDetail rtD,mySt,1
	}
	//parse parameters
	List params=[]
	for(Map param in (List<Map>)task.p){
		def p=null
//		if(eric())log.debug "executeTask ${param}"
		switch((String)param.vt){
			case sVARIABLE: // vcmd_setVariable command, first argument is the variable name
				if((String)param.t==sX) p=param.x instanceof List ? (List)param.x : (String)param.x + ((String)param.xi!=sNULL ? sLB+(String)param.xi+sRB:sBLK)
				break
			default:
				Map v=(Map)evaluateOperand(rtD,null,param)
				String tt1=(String)param.vt //?: (String)v.vt
				def t0=v.v
				//if not selected, return the null to fill in parameter
				// (tt1==(String)v.t)
				// (tt1==sDEC && t0 instanceof BigDecimal)
				p= t0==null || matchCast(rtD,t0,tt1) ? t0 : evaluateExpression(rtD,v,tt1).v
		}
		//ensure value type is successfuly passed through
		Boolean a=params.push(p)
	}

	//handle duplicate command "push" which was replaced with fake command "pushMomentary"
	def override=CommandsOverrides.find{ (String)it.value.r==(String)task.c }
	String command=override ? (String)override.value.c:(String)task.c

	def virtualDevice=devices.size()!=0 ? null:location
// If the VirtualCommand exists and has o: true, use that virtual command; otherwise try the physical command
	Map vcmd=VirtualCommands()[command]
	Long delay=lZERO
	for(device in (virtualDevice!=null ? [virtualDevice]:devices)){
		if(virtualDevice==null && device?.hasCommand(command) && !(vcmd && vcmd.o /* virtual command does not override physical command */)){
			Map msg=timer "Executed [$device].${command}",rtD
			try{
				delay="cmd_${command}"(rtD,device,params)
			}catch(ignored){
				executePhysicalCommand(rtD,device,command,params)
			}
			if((Integer)rtD.logging>1)trace msg,rtD
		}else{
			if(vcmd!=null){
				delay=executeVirtualCommand(rtD,vcmd.a ? devices:device,command,params)
				//aggregate commands only run once, for all devices at the same time
				if(vcmd.a)break
			}
		}
	}
	//negative delays force us to reschedule, no sleeping on this one
	Boolean reschedule= delay<lZERO
	delay=reschedule ? -delay:delay

	//if we don't have to wait, we're home free
	String pStr= "executeTask: Waiting for "
	if(delay!=lZERO){
		//get remaining piston time
		if(reschedule || async || delay>(Long)getPistonLimits.taskMaxDelay){
			//schedule a wake up
			Long msec=delay
			if((Integer)rtD.logging>1)trace "Requesting a wake up for ${formatLocalTime(Math.round((Long)now()*dONE+delay))} (in ${msec}ms)",rtD
			tracePoint(rtD,myS,elapseT(t),-delay)
			requestWakeUp(rtD,statement,task,delay,(String)task.c)
			if((Boolean)rtD.eric) myDetail rtD,mySt+"result:FALSE",-1
			return false
		}else{
			doPause(pStr+"${delay}ms",delay,rtD,true)
		}
	}
	tracePoint(rtD,myS,elapseT(t),delay)

	//get remaining piston time
	Long overBy=checkForSlowdown(rtD)
	if(overBy>lZERO){
		Long mdelay=(Long)getPistonLimits.taskShortDelay
		if(overBy>(Long)getPistonLimits.useBigDelay){
			mdelay=(Long)getPistonLimits.taskLongDelay
		}
		doPause(pStr+"${mdelay}ms, Execution time exceeded by ${overBy}ms",mdelay,rtD)
	}
	if((Boolean)rtD.eric) myDetail rtD,mySt+"result:TRUE",-1
	return true
}

private Long executeVirtualCommand(Map rtD,devices,String command,List params){
	Map msg=timer "Executed virtual command ${devices ? (devices instanceof List ? "$devices.":"[$devices]."):sBLK}${command}",rtD
	Long delay=lZERO
	try{
		delay="vcmd_${command}"(rtD,devices,params)
		if((Integer)rtD.logging>1)trace msg,rtD
	}catch(all){
		msg.m="Error executing virtual command ${devices instanceof List ? "$devices":"[$devices]"}.${command}:"
		msg.e="$all"
		error msg,rtD,-2,all
	}
	return delay
}

private void executePhysicalCommand(Map rtD,device,String command,params=[],Long delay=lZERO,String scheduleDevice=sNULL,Boolean disableCommandOptimization=false){
	if(delay!=lZERO && scheduleDevice!=sNULL){
		//delay without schedules is not supported in hubitat
		//scheduleDevice=hashId(device.id)
		//we're using schedules instead
		Map statement=(Map)rtD.currentAction
		List<Integer> cs=[]+ ((String)statement.tcp==sB || (String)statement.tcp==sC ? (rtD.stack?.cs!=null ? (List<Integer>)rtD.stack.cs:[]):[]) // task cancelation policy
		Integer ps= (String)statement.tcp==sB || (String)statement.tcp==sP ? 1:0
		Boolean a=cs.removeAll{ Integer it -> it==0 }
		Long ttt=Math.round((Long)now()*dONE+delay)
		Map schedule=[
			(sT):ttt,
			(sS):(Integer)statement.$,
			(sI):-3,
			cs:cs,
			ps:ps,
			(sD):[
				(sD):scheduleDevice,
				(sC):command,
				(sP):params
			]
		]
		if((Boolean)rtD.eric)trace "Requesting a physical command wake up for ${formatLocalTime(ttt)}",rtD
		a=((List<Map>)rtD.schedules).push(schedule)
	}else{
		List nparams=(params instanceof List)? (List)params:(params!=null ? [params]:[])
		try{
			//cleanup the params so that SONOS works
			while (nparams.size()>0 && nparams[nparams.size()-1]==null)def a=nparams.pop()
			Map msg=null
			Boolean doL=(Integer)rtD.logging>2
			if(doL) msg=timer sBLK,rtD
			Boolean skip=false
			if(!rtD.piston.o?.dco && !disableCommandOptimization && !(command in [sSCLRTEMP,sSCLR,sSHUE,sSSATUR])){
				Map cmd=PhysicalCommands()[command]
				if(cmd!=null && (String)cmd.a!=sNULL){
					if(cmd.v!=null && nparams.size()==0){
						//commands with no parameter that set an attribute to a preset value
						if((String)getDeviceAttributeValue(rtD,device,(String)cmd.a)==(String)cmd.v){
							skip=true
						}
					}else if(nparams.size()==1){
						if(getDeviceAttributeValue(rtD,device,(String)cmd.a)==nparams[0]){
							skip=(command in [sSTLVL,sSTIFLVL] ? (String)getDeviceAttributeValue(rtD,device,sSWITCH)==sON:true)
						}
					}
				}
			}
			//if we're skipping, we already have a message
			String tstr=sNULL
			if(doL) tstr=' physical command ['+"${(String)device.label ?: (String)device.name}".toString()+'].'+command+'('
			if(skip){
				if(doL) msg.m='Skipped execution of'+tstr+"$nparams".toString()+') because it would make no change to the device.'
			}else{
				String tailStr=sNULL
//				if(doL) tailStr=')'
				Long t1=(Long)getPistonLimits.deviceMaxDelay
				delay=delay>t1 ? t1 : delay
				if(delay>lZERO){
					doPause("wait before device command: Waiting for ${delay}ms",delay,rtD,true)
					if(doL) tailStr="[delay: $delay])".toString()
				}
				if(doL) tstr='Executed'+tstr
				if(nparams.size()>0){
					if(doL) msg.m=tstr+nparams.join(',')+"${tailStr ? ','+tailStr : ')'}"
					device."$command"(nparams as Object[])
				}else{
					if(doL) msg.m=tstr+"${tailStr ?: ')'}"
					device."$command"()
				}
			}
			if(doL)debug msg,rtD
		}catch(all){
			error "Error while executing physical command $device.$command($nparams):",rtD,-2,all
		}
		Long t0=rtD.piston.o?.ced ? (Integer)rtD.piston.o.ced:lZERO
		if(t0!=lZERO){
			Long t1=(Long)getPistonLimits.deviceMaxDelay
			t0=t0>t1 ? t1 : t0
			doPause("Injected command execution delay ${t0}ms after [$device].$command(${nparams ? "$nparams":sBLK})",t0,rtD,true)
		}
	}
}

private void scheduleTimer(Map rtD,Map timer,Long lastRun=lZERO){
	//if already scheduled once during this run, don't do it again
	if(((List<Map>)rtD.schedules).find{ Map it -> (Integer)it.s==(Integer)timer.$ })return
	String mySt=sNULL
	if((Boolean)rtD.eric){
		mySt="scheduleTimer $timer     $lastRun"
		myDetail rtD,mySt,1
	}
	//complicated stuff follows
	String tinterval="${((Map)evaluateOperand(rtD,null,(Map)timer.lo)).v}".toString()
	Boolean exitOut=false
	Integer interval=0
	if(tinterval.isInteger()){
		interval=tinterval.toInteger()
		if(interval<=0)exitOut=true
	}else{ exitOut=true }
	if(exitOut){
		if ((Boolean)rtD.eric) myDetail rtD,mySt,-1
		return
	}
	String intervalUnit=(String)timer.lo.vt
	Integer level=0
	Long delta=lZERO
	switch(intervalUnit){
		case 'ms': level=1; delta=1L; break
		case sS: level=2; delta=lTHOUS; break
		case 'm': level=3; delta=dMSMINT.toLong(); break
		case sH: level=4; delta=dMSECHR.toLong(); break
		case sD: level=5; break
		case 'w': level=6; break
		case sN: level=7; break
		case 'y': level=8; break
	}

	Long time=lZERO
	if(delta==lZERO){
		//let's get the offset
		time=(Long)evaluateExpression(rtD,(Map)evaluateOperand(rtD,null,(Map)timer.lo2),sDTIME).v
		if((String)timer.lo2.t!=sC){
			Map offset=(Map)evaluateOperand(rtD,null,(Map)timer.lo3)
			time += (Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):offset.v,(sVT):(String)offset.vt],sLONG).v
		}
		//resulting time is in UTC
		if(lastRun==lZERO){
			//first run, just adjust the time so we're in the future
			time=pushTimeAhead(time,(Long)now())
		}
	}
	delta=Math.round(delta*interval*dONE)
	Boolean priorActivity=lastRun!=lZERO

	Long rightNow=now()
	Long lastR=lastRun!=lZERO ? lastRun:rightNow
	Long nextSchedule=lastR

	if(lastR>rightNow){
		//sometimes timers run early, so make sure at least in the near future
		rightNow=Math.round(lastR+dONE)
	}

	if(intervalUnit==sH){
		Long min=(Long)cast(rtD,timer.lo.om,sLONG)
		nextSchedule=Math.round(dMSECHR*Math.floor(nextSchedule/dMSECHR)+(min*dMSMINT))
	}

	//next date
	Integer cycles=100
	while(cycles!=0){
		if(delta!=lZERO){
			if(nextSchedule<(rightNow-delta)){
				//we're behind,let's fast forward to where the next occurrence happens in the future
				Long count=Math.floor((rightNow-nextSchedule)/delta*dONE).toLong()
				//if((Integer)rtD.logging>2)debug "Timer fell behind by $count interval${count>1 ? sS:sBLK}, catching up",rtD
				nextSchedule=Math.round(nextSchedule+delta*count*dONE)
			}
			nextSchedule=nextSchedule+delta
		}else{
			//advance one day if we're in the past
			time=pushTimeAhead(time,rightNow)
			Long lastDay=Math.floor(nextSchedule/dMSDAY).toLong()
			Long thisDay=Math.floor(time/dMSDAY).toLong()

			Date adate=new Date(time)
			Integer dyYear=adate.year
			Integer dyMon=adate.month
			Integer dyDay=adate.day

			//the repeating interval is not necessarily constant
			switch(intervalUnit){
				case sD:
					if(priorActivity){
						//add the required number of days
						nextSchedule=time+Math.round(dMSDAY*(interval-(thisDay-lastDay)))
					}else{
						nextSchedule=time
					}
					break
				case 'w':
					//figure out the first day of the week matching the requirement
					Long currentDay=dyDay //(new Date(time)).day
					Long requiredDay=(Long)cast(rtD,timer.lo.odw,sLONG)
					if(currentDay>requiredDay)requiredDay += 7
					//move to first matching day
					nextSchedule=time+Math.round(dMSDAY*(requiredDay-currentDay))
					if(nextSchedule<rightNow){
						nextSchedule=Math.round(nextSchedule+604800000.0D*interval)
					}
					break
				case sN:
				case 'y':
					//figure out the first day of the week matching the requirement
					Integer odm=timer.lo.odm.toInteger()
					def odw=timer.lo.odw
					Integer omy=intervalUnit=='y' ? timer.lo.omy.toInteger():0
					Integer day
					Date date= adate // new Date(time)
					Integer year=dyYear //date.year
					Integer month=Math.round((intervalUnit==sN ? dyMon /*date.month*/:omy)+(priorActivity ? interval:((nextSchedule<rightNow)? dONE:dZERO))*(intervalUnit==sN ? dONE:12.0D)).toInteger()
					if(month>=12){
						year += Math.floor(month/12.0D).toInteger()
						month= month % 12
					}
					date.setDate(1)
					date.setMonth(month)
					date.setYear(year)

					Integer lastDayOfMonth= (new Date(date.year,date.month+1,0)).date
					if(odw==sD){
						if(odm>0){
							day=(odm<=lastDayOfMonth)? odm:0
						}else{
							day=lastDayOfMonth+1+odm
							day=(day>=1)? day:0
						}
					}else{
						Integer iodw=odw.toInteger()
						//find the nth week day of the month
						if(odm>0){
							//going forward
							Integer firstDayOfMonthDOW=(new Date(date.year,date.month,1)).day
							//find the first matching day
							Integer firstMatch=Math.round(1+iodw-firstDayOfMonthDOW+(iodw<firstDayOfMonthDOW ? 7.0D:dZERO)).toInteger()
							day=Math.round(firstMatch+7.0D*(odm-dONE)).toInteger()
							day=(day<=lastDayOfMonth)? day:0
						}else{
							//going backwards
							Integer lastDayOfMonthDOW=(new Date(date.year,date.month+1,0)).day
							//find the first matching day
							Integer firstMatch=lastDayOfMonth+iodw-lastDayOfMonthDOW-(iodw>lastDayOfMonthDOW ? 7:0)
							day=Math.round(firstMatch+7.0D*(odm+1)).toInteger()
							day=(day>=1)? day:0
						}
					}
					if(day){
						date.setDate(day)
						nextSchedule=(Long)date.getTime()
					}
					break
			}
		}
		//check to see if it fits the restrictions
		if(nextSchedule>=rightNow){
			Long offset=checkTimeRestrictions(rtD,(Map)timer.lo,nextSchedule,level,interval)
			if(offset==lZERO)break
			if(offset>lZERO)nextSchedule += offset
		}
		time=nextSchedule
		priorActivity=true
		cycles -= 1
	}

	if(nextSchedule>lastR){
		Boolean a=((List<Map>)rtD.schedules).removeAll{ (Integer)it.s==(Integer)timer.$ }
		requestWakeUp(rtD,timer,[(sDLR): -1],nextSchedule)
	}
	if((Boolean)rtD.eric) myDetail rtD,mySt,-1
}

private Long pushTimeAhead(Long pastTime,Long curTime){
	Long retTime=pastTime
	while(retTime<curTime){
		Long t0=Math.round(retTime+dMSDAY)
		Long t1=Math.round(t0+(((TimeZone)location.timeZone).getOffset(retTime)-((TimeZone)location.timeZone).getOffset(t0))*dONE)
		retTime=t1
	}
	return retTime
}

private void scheduleTimeCondition(Map rtD,Map condition){
	if((Boolean)rtD.eric) myDetail rtD,"scheduleTimeCondition",1
	Integer conditionNum=(Integer)condition.$
	//if already scheduled once during this run, don't do it again
	if(((List<Map>)rtD.schedules).find{ (Integer)it.s==conditionNum && (Integer)it.i==0 })return
	Map comparison=Comparisons().conditions[(String)condition.co]
	Boolean trigger=false
	if(comparison==null){
		comparison=Comparisons().triggers[(String)condition.co]
		if(comparison==null)return
		trigger=true
	}
	cancelStatementSchedules(rtD,conditionNum)
	if(!comparison.p)return

	Long lnow=now()
	Map tv1=condition.ro!=null && (String)condition.ro.t!=sC ? (Map)evaluateOperand(rtD,null,(Map)condition.to):null
	Long v1=(Long)evaluateExpression(rtD,(Map)evaluateOperand(rtD,null,(Map)condition.ro),sDTIME).v + (tv1!=null ? (Long)evaluateExpression(rtD,[(sT):sDURATION,(sV): tv1.v,(sVT):(String)tv1.vt],sLONG).v : lZERO)
	Map tv2=condition.ro2!=null && (String)condition.ro2.t!=sC && (Integer)comparison.p>1 ? (Map)evaluateOperand(rtD,null,(Map)condition.to2):null
	Long v2=trigger ? v1 : ((Integer)comparison.p>1 ? ((Long)evaluateExpression(rtD,(Map)evaluateOperand(rtD,null,(Map)condition.ro2,null,false,true),sDTIME).v + (tv2!=null ? (Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):tv2.v,(sVT):(String)tv2.vt]).v : lZERO)) : (String)condition.lo.v==sTIME ? getMidnightTime():v1 )
	Long n=Math.round(dONE*lnow+2000L)
	if((String)condition.lo.v==sTIME){
		v1=pushTimeAhead(v1,n)
		v2=pushTimeAhead(v2,n)
	}
	//figure out the next time
	v1=(v1<n)? v2:v1
	v2=(v2<n)? v1:v2
	n=v1<v2 ? v1:v2
	lnow=now()
	if(n>lnow){
		if((Integer)rtD.logging>2)debug "Requesting time schedule wake up at ${formatLocalTime(n)}",rtD
		requestWakeUp(rtD,condition,[(sDLR):0],n)
	}
	if((Boolean)rtD.eric) myDetail rtD,"scheduleTimeCondition",-1
}

private static Long checkTimeRestrictions(Map rtD,Map operand,Long time,Integer level,Integer interval){
	//returns 0 if restrictions are passed
	//returns a positive number as millisecond offset to apply to nextSchedule for fast forwarding
	//returns a negative number as a failed restriction with no fast forwarding offset suggestion

	List<Integer> om=level<=2 && operand.om instanceof List && ((List)operand.om).size()>0 ? (List)operand.om:null
	List<Integer> oh=level<=3 && operand.oh instanceof List && ((List)operand.oh).size()>0 ? (List)operand.oh:null
	List<Integer> odw=level<=5 && operand.odw instanceof List && ((List)operand.odw).size()>0 ? (List)operand.odw:null
	List<Integer> odm=level<=6 && operand.odm instanceof List && ((List)operand.odm).size()>0 ? (List)operand.odm:null
	List<Integer> owm=level<=6 && odm==null && operand.owm instanceof List && ((List)operand.owm).size()>0 ? (List)operand.owm:null
	List<Integer> omy=level<=7 && operand.omy instanceof List && ((List)operand.omy).size()>0 ? (List)operand.omy:null

	if(om==null && oh==null && odw==null && odm==null && owm==null && omy==null)return lZERO
	Date date=new Date(time)
	Integer dyYear=date.year
	Integer dyMon=date.month
	Integer dyDate=date.date
	Integer dyDay=date.day
	Integer dyHr=date.hours
	Integer dyMins=date.minutes

	Double dminDay=1440.0D
	Double dsecDay=86400.0D

	Long result=-1L
	//month restrictions
	if(omy!=null && (omy.indexOf(dyMon+1)<0)){
		List<Integer> tI=omy.sort{ it } as List<Integer>
		Integer month=(tI.find{ Integer it -> it>dyMon+1 } ?: 12+tI[0])- 1
		Integer year=dyYear+(month>=12 ? 1:0)
		month=(month>=12 ? month-12:month)
		Long ms=(Long)(new Date(year,month,1)).time-time
		switch(level){
			case 2: //by second
				result=Math.round(interval*(Math.floor(ms/dTHOUS/interval)-2.0D)*dTHOUS)
				break
			case 3: //by minute
				result=Math.round(interval*(Math.floor(ms/dMSMINT/interval)-2.0D)*dMSMINT)
				break
		}
		return (result>lZERO)? result:-1L
	}

	//week of month restrictions
	if(owm!=null){
		if(!((owm.indexOf(getWeekOfMonth(date))>=0) || (owm.indexOf(getWeekOfMonth(date,true))>=0))){
			switch(level){
				case 2: //by second
					result=Math.round(interval*(Math.floor(((7.0D-dyDay)*dsecDay-dyHr*dSECHR-dyMins*dSIXTY)/interval)-2.0D)*dTHOUS)
					break
				case 3: //by minute
					result=Math.round(interval*(Math.floor(((7.0D-dyDay)*dminDay-dyHr*dSIXTY-dyMins)/interval)-2.0D)*dMSMINT)
					break
			}
			return (result>lZERO)? result:-1L
		}
	}

	//day of month restrictions
	if(odm!=null){
		if(odm.indexOf(dyDate)<0){
			Integer lastDayOfMonth=new Date(dyYear,dyMon+1,0).date
			if(odm.find{ it<1 }){
				//we need to add the last days
				odm= []+odm as List<Integer> //copy the array
				if(odm.indexOf(-1)>=0)Boolean a=odm.push(lastDayOfMonth)
				if(odm.indexOf(-2)>=0)Boolean a=odm.push(lastDayOfMonth-1)
				if(odm.indexOf(-3)>=0)Boolean a=odm.push(lastDayOfMonth-2)
				Boolean a=odm.removeAll{ Integer it -> it<1 }
			}
			List<Integer> tI=odm.sort{ it } as List<Integer>
			switch(level){
				case 2: //by second
					result=Math.round(interval*(Math.floor((((tI.find{ Integer it -> it>dyDate } ?: lastDayOfMonth+tI[0])-dyDate)*dsecDay-dyHr*dSECHR-dyMins*dSIXTY)/interval)- 2.0D)*dTHOUS)
					break
				case 3: //by minute
					result=Math.round(interval*(Math.floor((((tI.find{ Integer it -> it>dyDate } ?: lastDayOfMonth+tI[0])-dyDate)*dminDay-dyHr*dSIXTY-dyMins)/interval)-2.0D)*dMSMINT)
					break
			}
			return (result>lZERO)? result:-1L
		}
	}

	//day of week restrictions
	if(odw!=null && odw.indexOf(dyDay)<0 ){
		List<Integer> tI=odw.sort{ it } as List<Integer>
		switch(level){
			case 2: //by second
				result=Math.round(interval*(Math.floor((((tI.find{ Integer it -> it>dyDay } ?: 7.0D+tI[0])-dyDay)*dsecDay-dyHr*dSECHR-dyMins*dSIXTY)/interval)-2.0D)*dTHOUS)
				break
			case 3: //by minute
				result=Math.round(interval*(Math.floor((((tI.find{ Integer it -> it>dyDay } ?: 7.0D+tI[0])-dyDay)*dminDay-dyHr*dSIXTY-dyMins)/interval)-2.0D)*dMSMINT)
				break
		}
		return (result>lZERO)? result:-1L
	}

	//hour restrictions
	if(oh!=null && oh.indexOf(dyHr)<0 ){
		List<Integer> tI=oh.sort{ it } as List<Integer>
		switch(level){
			case 2: //by second
				result=Math.round(interval*(Math.floor((((tI.find{ Integer it -> it>dyHr } ?: 24.0D+tI[0])-dyHr)*dSECHR-dyMins*dSIXTY)/interval)-2.0D)*dTHOUS)
				break
			case 3: //by minute
				result=Math.round(interval*(Math.floor((((tI.find{ Integer it -> it>dyHr } ?: 24.0D+tI[0])-dyHr)*dSIXTY-dyMins)/interval)-2.0D)*dMSMINT)
				break
		}
		return result>lZERO ? result:-1L
	}

	//minute restrictions
	if(om!=null && om.indexOf(dyMins)<0 ){
		//get the next highest minute
	//suggest an offset to reach the next minute
		List<Integer> tI=om.sort{ it } as List<Integer>
		result=Math.round(interval*(Math.floor(((tI.find{ Integer it -> it>dyMins } ?: dSIXTY+tI[0])-dyMins-dONE)*dSIXTY/interval)-2.0D)*dTHOUS)
		return result>lZERO ? result:-1L
	}
	return lZERO
}

//return the number of occurrences of same day of week up until the date or from the end of the month if backwards,i.e. last Sunday is -1, second-last Sunday is -2
private static Integer getWeekOfMonth(Date date,Boolean backwards=false){
	Integer day=date.date
	if(backwards){
		Integer month=date.month
		Integer year=date.year
		Integer lastDayOfMonth=(new Date(year,month+1,0)).date
		return -(1+Math.floor((lastDayOfMonth-day)/7))
	}else{
		return 1+Math.floor((day-1)/7) //1 based
	}
}

private void requestWakeUp(Map rtD,Map statement,Map task,Long timeOrDelay,String data=sNULL){
	Long time=timeOrDelay>9999999999L ? timeOrDelay:now()+timeOrDelay
	List<Integer> cs=[]+ ((String)statement.tcp==sB || (String)statement.tcp==sC ? (rtD.stack?.cs!=null ? (List<Integer>)rtD.stack.cs:[]):[]) // task cancelation policy
	Integer ps= (String)statement.tcp==sB || (String)statement.tcp==sP ? 1:0
	Boolean a=cs.removeAll{ Integer it -> it==0 }
// state to save across a sleep
	Boolean fnd=false
	def myResp=rtD.response
	if(myResp.toString().size() > 10000){ myResp=[:]; fnd=true } // state can only be total 100KB
	def myJson=rtD.json
	if(myJson.toString().size() > 10000){ myJson=[:]; fnd=true }
	if(fnd) debug 'trimming saved $response and/or $json for scheduled wakeup due to large size',rtD
	Map mmschedule=[
		(sT):time,
		(sS):(Integer)statement.$,
		(sI):task?.$!=null ? (Integer)task.$:0,
		cs:cs,
		ps:ps,
		(sD):data,
		evt:(Map)rtD.currentEvent,
		args:rtD.args,
		stack:[
			index:(Double)rtD.systemVars[sDLLRINDX].v,
			(sDEV):(List)rtD.systemVars[sDLLRDEVICE].v,
			devices:(List)rtD.systemVars[sDLLRDEVS].v,
			json:myJson ?: [:],
			response:myResp ?: [:]
// what about previousEvent httpContentType httpStatusCode httpStatusOk iftttStatusCode iftttStatusOk "\$mediaId" "\$mediaUrl" "\$mediaType" mediaData (big)
// currentEvent in case of httpRequest
		]
	]
	a=((List<Map>)rtD.schedules).push(mmschedule)
}

private Long do_setLevel(Map rtD,device,List params,String attr,Integer val=null){
	Integer arg=val!=null ? val:(Integer)params[0]
	Integer psz=params.size()
	String mstate=psz>1 ? (String)params[1]:sNULL
	if(mstate!=sNULL && (String)getDeviceAttributeValue(rtD,device,sSWITCH)!=mstate){
		return lZERO
	}
	if(attr==sSCLRTEMP && psz>2){ // setColorTemp takes level and seconds duration arguments (optional)
		Integer lvl=(Integer)params[2]
		Long delay=psz>3 ? (Long)params[3]:lZERO
		List larg=[arg]
		if(lvl||delay)larg.push(lvl)
		if(delay)larg.push(delay.toInteger())
		executePhysicalCommand(rtD,device,attr,larg)
	}else{
		Long delay=psz>2 ? (Long)params[2]:lZERO
		if(attr==sSTLVL && delay>0){ // setLevel takes seconds duration argument (optional)
			List larg=[arg,delay.toInteger()]
			executePhysicalCommand(rtD,device,attr,larg)
		}else executePhysicalCommand(rtD,device,attr,arg,delay)
	}
	return lZERO
}

private Long cmd_setLevel(Map rtD,device,List params){
	return do_setLevel(rtD,device,params,sSTLVL)
}

private Long cmd_setInfraredLevel(Map rtD,device,List params){
	return do_setLevel(rtD,device,params,sSTIFLVL)
}

private Long cmd_setHue(Map rtD,device,List params){
	Integer hue=(Integer)cast(rtD,Math.round((Integer)params[0]/3.6D),sINT)
	return do_setLevel(rtD,device,params,sSHUE,hue)
}

private Long cmd_setSaturation(Map rtD,device,List params){
	return do_setLevel(rtD,device,params,sSSATUR)
}

private Long cmd_setColorTemperature(Map rtD,device,List params){
	return do_setLevel(rtD,device,params,sSCLRTEMP)
}

private Map getColor(Map rtD,String colorValue){
	Map color=(colorValue=='Random')? getRandomColor():getColorByName(colorValue)
	if(color!=null){
		color=[
			hex:(String)color.rgb,
			hue:Math.round((Integer)color.h/3.6D),
			saturation:(Integer)color.s,
			level:(Integer)color.l
		]
	}else{
		color=hexToColor(colorValue)
		if(color!=null){
			color=[
				hex:(String)color.hex,
				hue:Math.round((Integer)color.hue/3.6D),
				saturation:(Integer)color.saturation,
				level:(Integer)color.level
			]
		}
	}
	return color
}

private Long cmd_setColor(Map rtD,device,List params){
	Map color=getColor(rtD,(String)params[0])
	if(!color){
		error "ERROR: Invalid color $params",rtD
		return lZERO
	}
	Integer psz=params.size()
	String mstate=psz>1 ? (String)params[1]:sNULL
	if(mstate!=sNULL && (String)getDeviceAttributeValue(rtD,device,sSWITCH)!=mstate){
		return lZERO
	}
	Long delay=psz>2 ? (Long)params[2]:lZERO
	executePhysicalCommand(rtD,device,sSCLR,color,delay)
	return lZERO
}

private Long cmd_setAdjustedColor(Map rtD,device,List params){
	Map color=getColor(rtD,(String)params[0])
	if(!color){
		error "ERROR: Invalid color $params",rtD
		return lZERO
	}
	Long duration=(Long)cast(rtD,params[1],sLONG)
	Integer psz=params.size()
	String mstate=psz>2 ? (String)params[2]:sNULL
	if(mstate!=sNULL && (String)getDeviceAttributeValue(rtD,device,sSWITCH)!=mstate){
		return lZERO
	}
	Long delay=psz>3 ? (Long)params[3]:lZERO
	executePhysicalCommand(rtD,device,'setAdjustedColor',[color,duration],delay)
	return lZERO
}

private Long cmd_setAdjustedHSLColor(Map rtD,device,List params){
	Integer hue=(Integer)cast(rtD,Math.round((Integer)params[0]/3.6D),sINT)
	Integer saturation=(Integer)params[1]
	Integer level=(Integer)params[2]
	def color=[
		hue: hue,
		saturation: saturation,
		level: level
	]
	Long duration=(Long)cast(rtD,params[3],sLONG)
	Integer psz=params.size()
	String mstate=psz>4 ? (String)params[4]:sNULL
	Long delay=psz>5 ? (Long)params[5]:lZERO
	if(mstate!=sNULL && (String)getDeviceAttributeValue(rtD,device,sSWITCH)!=mstate){
		return lZERO
	}
	executePhysicalCommand(rtD,device,'setAdjustedColor',[color,duration],delay)
	return lZERO
}

private Long cmd_setLoopDuration(Map rtD,device,List params){
	Integer duration=(Integer)Math.round((Long)cast(rtD,params[0],sLONG)/1000)
	executePhysicalCommand(rtD,device,'setLoopDuration',duration)
	return lZERO
}

private Long cmd_setVideoLength(Map rtD,device,List params){
	Integer duration=(Integer)Math.round((Long)cast(rtD,params[0],sLONG)/1000)
	executePhysicalCommand(rtD,device,'setVideoLength',duration)
	return lZERO
}

private Long cmd_setVariable(Map rtD,device,List params){
	def var=params[1]
	executePhysicalCommand(rtD,device,'setVariable',var)
	return lZERO
}

private Long vcmd_log(Map rtD,device,List params){
	String command=params[0] ? (String)params[0]:sBLK
	String message=(String)params[1]
	Map a=log(message,rtD,-2,null,command.toLowerCase().trim(),true)
	return lZERO
}

private Long vcmd_setState(Map rtD,device,List params){
	String value=params[0]
	if(rtD.piston.o?.mps){
		rtD.state.new=value
		rtD.pistonStateChanged=(Boolean)rtD.pistonStateChanged || ((String)rtD.state.old!=(String)rtD.state.new)
	}else{
		error "Cannot set the piston state while in automatic mode. Please edit the piston settings to disable the automatic piston state if you want to manually control the state.",rtD
	}
	return lZERO
}

private Long vcmd_setTileColor(Map rtD,device,List params){
	Integer index=(Integer)cast(rtD,params[0],sINT)
	if(index<1 || index>16)return lZERO
	String sIdx=index.toString()
	rtD.state[sC+sIdx]=(String)getColor(rtD,(String)params[1])?.hex
	rtD.state[sB+sIdx]=(String)getColor(rtD,(String)params[2])?.hex
	rtD.state['f'+sIdx]=!!params[3]
	return lZERO
}

private Long vcmd_setTileTitle(Map rtD,device,List params){
	return helper_setTile(rtD,sI,params)
}

private Long vcmd_setTileText(Map rtD,device,List params){
	return helper_setTile(rtD,sT,params)
}

private Long vcmd_setTileFooter(Map rtD,device,List params){
	return helper_setTile(rtD,sO,params)
}

private Long vcmd_setTileOTitle(Map rtD,device,List params){
	return helper_setTile(rtD,sP,params)
}

private Long helper_setTile(Map rtD,String typ,List params){
	Integer index=(Integer)cast(rtD,params[0],sINT)
	if(index<1 || index>16)return lZERO
	rtD.state["${typ}$index".toString()]=(String)params[1]
	return lZERO
}

private Long vcmd_setTile(Map rtD,device,List params){
	Integer index=(Integer)cast(rtD,params[0],sINT)
	if(index<1 || index>16)return lZERO
	String sIdx=index.toString()
	rtD.state[sI+sIdx]=(String)params[1]
	rtD.state[sT+sIdx]=(String)params[2]
	rtD.state[sO+sIdx]=(String)params[3]
	rtD.state[sC+sIdx]=(String)getColor(rtD,(String)params[4])?.hex
	rtD.state[sB+sIdx]=(String)getColor(rtD,(String)params[5])?.hex
	rtD.state['f'+sIdx]=!!params[6]
	return lZERO
}

private Long vcmd_clearTile(Map rtD,device,List params){
	Integer index=(Integer)cast(rtD,params[0],sINT)
	if(index<1 || index>16)return lZERO
	String sIdx=index.toString()
	Map t0=(Map)rtD.state
	t0.remove(sI+sIdx)
	t0.remove(sT+sIdx)
	t0.remove(sC+sIdx)
	t0.remove(sO+sIdx)
	t0.remove(sB+sIdx)
	t0.remove('f'+sIdx)
	t0.remove(sP+sIdx)
	rtD.state=t0
	return lZERO
}

private Long vcmd_setLocationMode(Map rtD,device,List params){
	String modeIdOrName=(String)params[0]
	def mode=location.getModes()?.find{ (hashId(it.id)==modeIdOrName)|| ((String)it.name==modeIdOrName)}
	if(mode) location.setMode((String)mode.name)
	else error "Error setting location mode. Mode '$modeIdOrName' does not exist.",rtD
	return lZERO
}

private Long vcmd_setAlarmSystemStatus(Map rtD,device,List params){
	String statusIdOrName=(String)params[0]
	def dev=VirtualDevices()['alarmSystemStatus']
	Map options=(Map)dev?.ac
	List status=options?.find{ (String)it.key==statusIdOrName || (String)it.value==statusIdOrName }?.collect{ [id:(String)it.key,(sNM):it.value] }

	if(status && status.size()!=0){
		sendLocationEvent((sNM):'hsmSetArm',(sVAL): status[0].id)
	}else{
		error "Error setting HSM status. Status '$statusIdOrName' does not exist.",rtD
	}
	return lZERO
}

private Long vcmd_sendEmail(Map rtD,device,List params){
	def data=[
		i: (String)rtD.id,
		n: (String)app.label,
		t:(String)params[0],
		s: (String)params[1],
		m: (String)params[2]
	]

	Map requestParams=[
		uri: 'https://api.webcore.co/email/send/'+(String)rtD.locationId,
		query: null,
		headers: [:],//(auth ? [Authorization: auth]:[:]),
		requestContentType: sAPPJSON,
		body: data,
		timeout:20
	]
	String msg='Unknown error'

	try{
		asynchttpPost('ahttpRequestHandler',requestParams,[command:sSENDE,em:data])
		return 24000L
	}catch(all){
		error "Error sending email to ${data.t}: $msg",rtD,-2,all
	}
	return lZERO
}

private static Long vcmd_noop(Map rtD,device,List params){
	return lZERO
}

private Long vcmd_wait(Map rtD,device,List params){
	return (Long)cast(rtD,params[0],sLONG)
}

private Long vcmd_waitRandom(Map rtD,device,List params){
	Long min=(Long)cast(rtD,params[0],sLONG)
	Long max=(Long)cast(rtD,params[1],sLONG)
	if(max<min){
		Long v=max
		max=min
		min=v
	}
	return min+(Integer)Math.round(dONE*(max-min)*Math.random())
}

private Long vcmd_waitForTime(Map rtD,device,List params){
	Long time
	time=(Long)cast(rtD,(Long)cast(rtD,params[0],sTIME),sDTIME,sTIME)
	Long rightNow=now()
	time=pushTimeAhead(time,rightNow)
	return time-rightNow
}

private Long vcmd_waitForDateTime(Map rtD,device,List params){
	Long time=(Long)cast(rtD,params[0],sDTIME)
	Long rightNow=now()
	return (time>rightNow)? time-rightNow:lZERO
}

private Long vcmd_setSwitch(Map rtD,device,List params){
	//noinspection GroovyAssignabilityCheck
	executePhysicalCommand(rtD,device,(Boolean)cast(rtD,params[0],sBOOLN) ? sON : sOFF)
	return lZERO
}

private Long vcmd_toggle(Map rtD,device,List params){
	executePhysicalCommand(rtD,device,(String)getDeviceAttributeValue(rtD,device,sSWITCH)==sOFF ? sON : sOFF)
	return lZERO
}

private Long vcmd_toggleRandom(Map rtD,device,List params){
	Integer probability=(Integer)cast(rtD,params.size()==1 ? params[0]:50,sINT)
	if(probability<=0)probability=50
	executePhysicalCommand(rtD,device,Math.round(100.0D*Math.random())<=probability ? sON : sOFF)
	return lZERO
}

private Long vcmd_toggleLevel(Map rtD,device,List params){
	Integer level=(Integer)params[0]
	executePhysicalCommand(rtD,device,sSTLVL,(Integer)getDeviceAttributeValue(rtD,device,sLVL)==level ? 0 : level)
	return lZERO
}

private Long do_adjustLevel(Map rtD,device,List params,String attr,String attr1,Integer val=null,Boolean big=false){
	Integer arg=val!=null ? val : (Integer)cast(rtD,params[0],sINT)
	Integer psz=params.size()
	String mstate=psz>1 ? (String)params[1]:sNULL
	Long delay=psz>2 ? (Long)params[2]:lZERO
	if(mstate!=sNULL && (String)getDeviceAttributeValue(rtD,device,sSWITCH)!=mstate){
		return lZERO
	}
	arg=arg+(Integer)cast(rtD,getDeviceAttributeValue(rtD,device,attr),sINT)
	Integer low=big ? 1000:0
	Integer hi=big ? 30000:100
	arg=(arg<low)? low:((arg>hi)? hi:arg)
	executePhysicalCommand(rtD,device,attr1,arg,delay)
	return lZERO
}

private Long vcmd_adjustLevel(Map rtD,device,List params){
	return do_adjustLevel(rtD,device,params,sLVL,sSTLVL)
}

private Long vcmd_adjustInfraredLevel(Map rtD,device,List params){
	return do_adjustLevel(rtD,device,params,sIFLVL,sSTIFLVL)
}

private Long vcmd_adjustSaturation(Map rtD,device,List params){
	return do_adjustLevel(rtD,device,params,sSATUR,sSSATUR)
}

private Long vcmd_adjustHue(Map rtD,device,List params){
	Integer hue=(Integer)cast(rtD,Math.round((Integer)params[0]/3.6D),sINT)
	return do_adjustLevel(rtD,device,params,sHUE,sSHUE,hue)
}

private Long vcmd_adjustColorTemperature(Map rtD,device,List params){
	return do_adjustLevel(rtD,device,params,sCLRTEMP,sSCLRTEMP,null,true)
}

private Long do_fadeLevel(Map rtD,device,List params,String attr,String attr1,Integer val=null,Integer val1=null,Boolean big=false){
	Integer startLevel
	Integer endLevel
	if(val==null){
		startLevel=(params[0]!=null)? (Integer)cast(rtD,params[0],sINT):(Integer)cast(rtD,getDeviceAttributeValue(rtD,device,attr),sINT)
		endLevel=(Integer)cast(rtD,params[1],sINT)
	}else{
		startLevel=val
		endLevel=val1
	}
	Long duration=(Long)cast(rtD,params[2],sLONG)
	String mstate=params.size()>3 ? (String)params[3]:sNULL
	if(mstate!=sNULL && (String)getDeviceAttributeValue(rtD,device,sSWITCH)!=mstate){
		return lZERO
	}
	Integer low=big ? 1000:0
	Integer hi=big ? 30000:100
	startLevel=(startLevel<low)? low:((startLevel>hi)? hi:startLevel)
	endLevel=(endLevel<low)? low:((endLevel>hi)? hi:endLevel)
	return vcmd_internal_fade(rtD,device,attr1,startLevel,endLevel,duration)
}

private Long vcmd_fadeLevel(Map rtD,device,List params){
	return do_fadeLevel(rtD,device,params,sLVL,sSTLVL)
}

private Long vcmd_fadeInfraredLevel(Map rtD,device,List params){
	return do_fadeLevel(rtD,device,params,sIFLVL,sSTIFLVL)
}

private Long vcmd_fadeSaturation(Map rtD,device,List params){
	return do_fadeLevel(rtD,device,params,sSATUR,sSSATUR)
}

private Long vcmd_fadeHue(Map rtD,device,List params){
	Integer startLevel=(params[0]!=null)? (Integer)cast(rtD,Math.round((Integer)params[0]/3.6D),sINT):(Integer)cast(rtD,getDeviceAttributeValue(rtD,device,sHUE),sINT)
	Integer endLevel=(Integer)cast(rtD,Math.round((Integer)params[1]/3.6D),sINT)
	return do_fadeLevel(rtD,device,params,sHUE,sSHUE,startLevel,endLevel)
}

private Long vcmd_fadeColorTemperature(Map rtD,device,List params){
	return do_fadeLevel(rtD,device,params,sCLRTEMP,sSCLRTEMP,null,null,true)
}

private Long vcmd_internal_fade(Map rtD,device,String command,Integer startLevel,Integer endLevel,Long duration){
	Long minInterval
	if(duration<=5000L){
		minInterval=500L
	}else if(duration<=10000L){
		minInterval=lTHOUS
	}else if(duration<=30000L){
		minInterval=3000L
	}else{
		minInterval=5000L
	}
	if((startLevel==endLevel)|| (duration<=500L)){
		//if the fade is too fast, or not changing anything, go to the end level directly
		executePhysicalCommand(rtD,device,command,endLevel)
		return lZERO
	}
	Integer delta=endLevel-startLevel
	//the max number of steps we can do
	Integer steps=delta>0 ? delta:-delta
	//figure out the interval
	Long interval=Math.round(duration/steps)
	if(interval<minInterval){
		//intervals too small,adjust to do one change per 500ms
		steps=Math.floor(dONE*duration/minInterval).toInteger()
		interval=Math.round(dONE*duration/steps)
	}
	String scheduleDevice=hashId(device.id)
	Integer oldLevel=startLevel
	executePhysicalCommand(rtD,device,command,startLevel)
	for(Integer i=1; i<=steps; i++){
		Integer newLevel=Math.round(startLevel+delta*i/steps*dONE).toInteger()
		if(oldLevel!=newLevel){
			executePhysicalCommand(rtD,device,command,newLevel,i*interval,scheduleDevice,true)
		}
		oldLevel=newLevel
	}
	//for good measure,send a last command 100ms after the end of the interval
	executePhysicalCommand(rtD,device,command,endLevel,duration+99L,scheduleDevice,true)
	return duration+105L
}

private Long vcmd_emulatedFlash(Map rtD,device,List params){
	vcmd_flash(rtD,device,params)
}

private Long vcmd_flash(Map rtD,device,List params){
	Long onDuration=(Long)cast(rtD,params[0],sLONG)
	Long offDuration=(Long)cast(rtD,params[1],sLONG)
	Integer cycles=(Integer)cast(rtD,params[2],sINT)
	String mstate=params.size()>3 ? (String)params[3]:sNULL
	String currentState=(String)getDeviceAttributeValue(rtD,device,sSWITCH)
	if(mstate!=sNULL && (currentState!=mstate)){
		return lZERO
	}
	Long duration=Math.round((onDuration+offDuration)*cycles*dONE)
	if(duration<=500L){
		//if the flash is too fast, ignore it
		return lZERO
	}
	//initialize parameters
	String firstCommand=currentState==sON ? sOFF:sON
	Long firstDuration=firstCommand==sON ? onDuration:offDuration
	String secondCommand=firstCommand==sON ? sOFF:sON
	Long secondDuration=firstCommand==sON ? offDuration:onDuration
	String scheduleDevice=hashId(device.id)
	Long dur=lZERO
	for(Integer i=1; i<=cycles; i++){
		executePhysicalCommand(rtD,device,firstCommand,[],dur,scheduleDevice,true)
		dur += firstDuration
		executePhysicalCommand(rtD,device,secondCommand,[],dur,scheduleDevice,true)
		dur += secondDuration
	}
	//for good measure,send a last command 100ms after the end of the interval
	executePhysicalCommand(rtD,device,currentState,[],dur+100L,scheduleDevice,true)
	return dur+105L
}

private Long vcmd_flashLevel(Map rtD,device,List params){
	Integer level1=(Integer)cast(rtD,params[0],sINT)
	Long duration1=(Long)cast(rtD,params[1],sLONG)
	Integer level2=(Integer)cast(rtD,params[2],sINT)
	Long duration2=(Long)cast(rtD,params[3],sLONG)
	Integer cycles=(Integer)cast(rtD,params[4],sINT)
	String mstate=params.size()>5 ? (String)params[5]:sNULL
	String currentState=(String)getDeviceAttributeValue(rtD,device,sSWITCH)
	if(mstate!=sNULL && (currentState!=mstate)){
		return lZERO
	}
	Integer currentLevel=(Integer)getDeviceAttributeValue(rtD,device,sLVL)
	Long duration=Math.round((duration1+duration2)*cycles*dONE)
	if(duration<=500L){
		//if the flash is too fast, ignore it
		return lZERO
	}
	String scheduleDevice=hashId(device.id)
	Long dur=lZERO
	for(Integer i=1; i<=cycles; i++){
		executePhysicalCommand(rtD,device,sSTLVL,[level1],dur,scheduleDevice,true)
		dur += duration1
		executePhysicalCommand(rtD,device,sSTLVL,[level2],dur,scheduleDevice,true)
		dur += duration2
	}
	//for good measure,send a last command 100ms after the end of the interval
	executePhysicalCommand(rtD,device,sSTLVL,[currentLevel],dur+100L,scheduleDevice,true)
	executePhysicalCommand(rtD,device,currentState,[],dur+101L,scheduleDevice,true)
	return dur+105L
}

private Long vcmd_flashColor(Map rtD,device,List params){
	Map color1=getColor(rtD,(String)params[0])
	Long duration1=(Long)cast(rtD,params[1],sLONG)
	Map color2=getColor(rtD,(String)params[2])
	Long duration2=(Long)cast(rtD,params[3],sLONG)
	Integer cycles=(Integer)cast(rtD,params[4],sINT)
	String mstate=params.size()>5 ? (String)params[5]:sNULL
	String currentState=(String)getDeviceAttributeValue(rtD,device,sSWITCH)
	if(mstate!=sNULL && (currentState!=mstate)){
		return lZERO
	}
	Long duration=Math.round((duration1+duration2)*cycles*dONE)
	if(duration<=500L){
		//if the flash is too fast, ignore it
		return lZERO
	}
	String scheduleDevice=hashId(device.id)
	Long dur=lZERO
	for(Integer i=1; i<=cycles; i++){
		executePhysicalCommand(rtD,device,sSCLR,[color1],dur,scheduleDevice,true)
		dur += duration1
		executePhysicalCommand(rtD,device,sSCLR,[color2],dur,scheduleDevice,true)
		dur += duration2
	}
	//for good measure,send a last command 100ms after the end of the interval
	executePhysicalCommand(rtD,device,currentState,[],dur+99L,scheduleDevice,true)
	return dur+105L
}

private Long vcmd_sendNotification(Map rtD,device,List params){
	def message="Hubitat does not support sendNotification "+params[0]
	Map a=log(message,rtD,-2,"Err",sWARN,true)
	//sendNotificationEvent(message)
	return lZERO
}

private Long vcmd_sendPushNotification(Map rtD,device,List params){
	String message=(String)params[0]
	if(rtD.initPush==null){
		rtD.pushDev=(List)parent.getPushDev()
		rtD.initPush=true
	}
	List t0=(List)rtD.pushDev
	try{
		t0*.deviceNotification(message)
	}catch(ignored){
		message="Default push device not set properly in webCoRE "+(String)params[0]
		error message,rtD
	}
	return lZERO
}

private Long vcmd_sendSMSNotification(Map rtD,device,List params){
	String message=(String)params[0]
	String msg="HE SMS notifications are being removed,please convert to a notification device "+message
	warn msg,rtD
	return lZERO
}

private Long vcmd_sendNotificationToContacts(Map rtD,device,List params){
	// Contact Book has been disabled and we're falling back onto PUSH notifications,if the option is on
	String message=(String)params[0]
	Boolean save=!!params[2]
	return vcmd_sendPushNotification(rtD,device,[message,save])
}

private static Map parseVariableName(String name){
	Map result=[
		(sNM): name,
		index: sNULL
	]
	if(name!=sNULL && !(Boolean)name.startsWith(sDLR) && (Boolean)name.endsWith(sRB)){
		List<String> parts=name.replace(sRB,sBLK).tokenize(sLB)
		if(parts.size()==2){
			result=[
				(sNM): parts[0],
				index: parts[1]
			]
		}
	}
	return result
}

private Long vcmd_setVariable(Map rtD,device,List params){
	String name=(String)params[0]
	def value=params[1]
	if((Boolean)rtD.eric) myDetail rtD,"setVariable $name  $value"
	Map t0=setVariable(rtD,name,value)
	if((String)t0.t==sERROR){
		String message=(String)t0.v+sSPC+name
		error message,rtD
	}
	return lZERO
}

private Long vcmd_executePiston(Map rtD,device,List params){
	String selfId=(String)rtD.id
	String pistonId=(String)params[0]
	List<String> arguments=(params[1] instanceof List ? (List<String>)params[1]:params[1].toString().tokenize(sCOMMA)).unique()
	//noinspection GroovyAssignabilityCheck
	Boolean wait=(params.size()>2)? (Boolean)cast(rtD,params[2],sBOOLN):false
	String description="webCoRE: Piston ${(String)app.label} requested execution of piston $pistonId".toString()
	Map data=[:]
	for(String argument in arguments){
		if(argument)data[argument]=getVariable(rtD,argument).v
	}
	if(wait){
		wait=(Boolean)parent.executePiston(pistonId,data,selfId)
	}
	if(!wait){
		sendLocationEvent((sNM):pistonId,(sVAL):selfId,isStateChange:true,displayed:false,linkText:description,descriptionText:description,data:data)
	}
	return lZERO
}

private Long vcmd_pausePiston(Map rtD,device,List params){
//	String selfId=(String)rtD.id
	String pistonId=(String)params[0]
	if(!(Boolean)parent.pausePiston(pistonId)){
		String message="Piston not found "+pistonId
		error message,rtD
	}
	return lZERO
}

private Long vcmd_resumePiston(Map rtD,device,List params){
//	String selfId=(String)rtD.id
	String pistonId=(String)params[0]
	if(!(Boolean)parent.resumePiston(pistonId)){
		String message="Piston not found "+pistonId
		error message,rtD
	}
	return lZERO
}

private Long vcmd_executeRule(Map rtD,device,List params){
	String ruleId=(String)params[0]
	String action=(String)params[1]
	//Boolean wait=(params.size()>2)? (Boolean)cast(rtD,params[2],sBOOLN):false
	String ruleAction=sNULL
	if(action=="Run")ruleAction="runRuleAct"
	if(action=="Stop")ruleAction="stopRuleAct"
	if(action=="Pause")ruleAction="pauseRule"
	if(action=="Resume")ruleAction="resumeRule"
	if(action=="Evaluate")ruleAction="runRule"
	if(action=="Set Boolean True")ruleAction="setRuleBooleanTrue"
	if(action=="Set Boolean False")ruleAction="setRuleBooleanFalse"
	if(!ruleAction){
		String message="No Rule action found "+action
		error message,rtD
	}else{
		Boolean sent=false
		['4.1', '5.0'].each { String ver->
			List<Map> rules=RMUtils.getRuleList(ver ?: sNULL)
			List myRule=[]
			rules.each{rule->
				List t0=rule.find{ hashId((String)it.key)==ruleId }.collect{(String)it.key}
				myRule += t0
			}
			if(myRule){
				RMUtils.sendAction(myRule,ruleAction,(String)app.label, ver ?: sNULL)
				sent=true
			}
		}
		if(!sent){
			String message="Rule not found "+ruleId
			error message,rtD
		}
	}
	return lZERO
}

private Long vcmd_setHSLColor(Map rtD,device,List params){
	Integer hue=(Integer)cast(rtD,Math.round((Integer)params[0]/3.6D),sINT)
	Integer saturation=(Integer)params[1]
	Integer level=(Integer)params[2]
	def color=[
		hue: hue,
		saturation: saturation,
		level: level
	]
	String mstate=params.size()>3 ? (String)params[3]:sNULL
	Long delay=params.size()>4 ? (Long)params[4]:lZERO
	if(mstate!=sNULL && (String)getDeviceAttributeValue(rtD,device,sSWITCH)!=mstate){
		return lZERO
	}
	executePhysicalCommand(rtD,device,sSCLR,color,delay)
	return lZERO
}

private Long vcmd_wolRequest(Map rtD,device,List params){
	String mac=(String)params[0]
	String secureCode=(String)params[1]
	mac=mac.replace(sCOLON,sBLK).replace(sMINUS,sBLK).replace(sDOT,sBLK).replace(sSPC,sBLK).toLowerCase()

	sendHubCommand(HubActionClass().newInstance(
		"wake on lan $mac".toString(),
		HubProtocolClass().LAN,
		null,
		secureCode ? [secureCode: secureCode]:[:]
	))
	return lZERO
}

private Long vcmd_iftttMaker(Map rtD,device,List params){
	String key=sNULL
	if(rtD.settings==null){
		error "no settings",rtD
	}else{
		key=((String)rtD.settings.ifttt_url ?: sBLK).trim().replace('https://',sBLK).replace('http://',sBLK).replace('maker.ifttt.com/use/',sBLK)
	}
	if(!key){
		error "Failed to send IFTTT event, because the IFTTT integration is not properly set up. Please visit Settings in your webCoRE dashboard and configure the IFTTT integration.",rtD
		return lZERO
	}
	String event=params[0]
	def value1=params.size()>1 ? params[1]:sBLK
	def value2=params.size()>2 ? params[2]:sBLK
	def value3=params.size()>3 ? params[3]:sBLK
	def body=[:]
	if(value1)body.value1=value1
	if(value2)body.value2=value2
	if(value3)body.value3=value3
	Map data=[
		t:event,
		p1:value1,
		p2:value2,
		p3:value3
	]
	Map requestParams=[
		uri: "https://maker.ifttt.com/trigger/${java.net.URLEncoder.encode(event,"UTF-8")}/with/key/".toString()+key,
		requestContentType: sAPPJSON,
		body: body,
		timeout:20
	]
	try{
		asynchttpPost('ahttpRequestHandler',requestParams,[command:sIFTTM,em: data])
		return 24000L
	}catch(all){
		error "Error iftttMaker to ${requestParams.uri}  ${data.t}: ${data.p1}, ${data.p2}, ${data.p3}",rtD,-2,all
	}
	return lZERO
}

private Long do_lifx(Map rtD,String cmd,String path,Map body,duration,String c){
	String token=rtD.settings?.lifx_token
	if(!token){
		error "Sorry, enable the LIFX integration in the dashboard's Settings section before trying to execute a LIFX operation.",rtD
		return lZERO
	}
	Map requestParams=[
		uri: "https://api.lifx.com",
		path: path,
		headers: [ "Authorization": "Bearer $token" ],
		requestContentType: sAPPJSON,
		timeout:10,
		body: body
	]
	try{
		if((Integer)rtD.logging>2)debug "Sending lifx ${c} web request to: $path",rtD
		"asynchttp${cmd}"('ahttpRequestHandler',requestParams,[command:'lifx',em: [(sT):c]])
		Long ldur=duration ? Math.round( duration * dTHOUS) : lZERO
		return ldur > 11000L ? ldur : 11000L
	}catch(all){
		error "Error while activating LIFX $c:",rtD,-2,all
	}
	return lZERO
}

private Long lifxErr(Map rtD){
	error "Sorry, could not find the specified LIFX selector.",rtD
	return lZERO
}

private Long vcmd_lifxScene(Map rtD,device,List params){
	String sceneId=(String)params[0]
	Long duration=params.size() > 1 ? Math.round( ((Long)cast(rtD,params[1],sLONG) / dTHOUS)) : lZERO
	Map scn=(Map)rtD.lifx?.scenes
	if(!scn){
		error "Sorry, there seems to be no available LIFX scenes, please ensure the LIFX integration is working.",rtD
		return lZERO
	}
	sceneId=scn.find{ ((String)it.key==sceneId) || ((String)it.value==sceneId) }?.key
	if(!sceneId){
		error "Sorry, could not find the specified LIFX scene.",rtD
		return lZERO
	}
	String path="/v1/scenes/scene_id:${sceneId}/activate"
	Map body= duration ? [duration: duration] : null
	return do_lifx(rtD,'Put',path,body,duration,'scene')
}

private static String getLifxSelector(Map rtD,String selector){
	String selectorId=sBLK
	if(selector=='all')return selector
	Integer i=0
	List<String> a=['scene_',sBLK,'group_','location_']
	for(String m in ['scenes','lights','groups','locations']){
		String obj=((Map)rtD.lifx."${m}")?.find{ (it.key==selector) || (it.value==selector) }?.key
		if(obj) return "${a[i]}id:${obj}".toString()
		i+=1
	}
	return selectorId
}

private Long vcmd_lifxState(Map rtD,device,List params){
	String selector=getLifxSelector(rtD,(String)params[0])
	if (!selector) return lifxErr(rtD)
	String power=(String)params[1]
	Map color=getColor(rtD,(String)params[2])
	Integer level=(Integer)params[3]
	Integer infraredLevel=(Integer)params[4]
	Long duration=Math.round( ((Long)cast(rtD,params[5],sLONG) / dTHOUS) )
	String path= "/v1/lights/${selector}/state"
	Map body= [:] + (power ? ([power: power]) : [:]) + (color ? ([color: color.hex]) : [:]) + (level != null ? ([brightness: level / 100.0]) : [:]) + (infrared != null ? [infrared: infraredLevel] : [:]) + (duration ? [duration: duration] : [:])
	return do_lifx(rtD,'Put',path,body,duration,'state')
}

private Long vcmd_lifxToggle(Map rtD,device,List params){
	String selector=getLifxSelector(rtD,(String)params[0])
	if (!selector) return lifxErr(rtD)
	Long duration=Math.round( ((Long)cast(rtD,params[1],sLONG) / dTHOUS) )
	String path= "/v1/lights/${selector}/toggle"
	Map body= [:] + (duration ? [duration: duration] : [:])
	return do_lifx(rtD,'Post',path,body,duration,'toggle')
}

private Long vcmd_lifxBreathe(Map rtD,device,List params){
	String selector=getLifxSelector(rtD,(String)params[0])
	if (!selector) return lifxErr(rtD)
	Map color=getColor(rtD,(String)params[1])
	Map fromColor= (params[2]==null) ? null : getColor(rtD,(String)params[2])
	Long period= (params[3]==null) ? null : Math.round( ((Long)cast(rtD,params[3],sLONG) / dTHOUS))
	Integer cycles=(Integer)params[4]
	Integer peak=(Integer)params[5]
	Boolean powerOn=(params[6]==null) ? null : cast(rtD,params[6],sBOOLN)
	Boolean persist=(params[7]==null) ? null : cast(rtD,params[7],sBOOLN)
	String path= "/v1/lights/${selector}/effects/breathe"
	Map body= [color: color.hex] + (fromColor ? ([from_color: fromColor.hex]) : [:]) + (period != null ? ([period: period]) : [:]) + (cycles ? ([cycles: cycles]) : [:]) + (powerOn != null ? ([power_on: powerOn]) : [:]) + (persist != null ? ([persist:persist]) : [:]) + (peak != null ? ([peak: peak / 100]) : [:])
	Long ldur=Math.round( (period ? period : 1) * (cycles ? cycles : dONE) )
	return do_lifx(rtD,'Post',path,body,ldur,'breathe')
}

private Long vcmd_lifxPulse(Map rtD,device,List params){
	String selector=getLifxSelector(rtD,(String)params[0])
	if (!selector) return lifxErr(rtD)
	Map color=getColor(rtD,(String)params[1])
	Map fromColor=(params[2]==null) ? null : getColor(rtD,(String)params[2])
	Long period=(params[3]==null) ? null : Math.round( ((Long)cast(rtD,params[3],sLONG) / dTHOUS))
	Integer cycles=(Integer)params[4]
	Boolean powerOn=(params[5]==null)? null : cast(rtD,params[5],sBOOLN)
	Boolean persist=(params[6]==null) ? null : cast(rtD,params[6],sBOOLN)
	String path= "/v1/lights/${selector}/effects/pulse"
	Map body= [color: color.hex] + (fromColor ? ([from_color: fromColor.hex]) : [:]) + (period != null ? ([period: period]) : [:]) + (cycles ? ([cycles: cycles]) : [:]) + (powerOn != null ? ([power_on: powerOn]) : [:]) + (persist != null ? ([persist:persist]) : [:])
	Long ldur=Math.round( (period ? period : 1) * (cycles ? cycles : dONE) )
	return do_lifx(rtD,'Post',path,body,ldur,'pulse')
}

private Long vcmd_httpRequest(Map rtD,device,List params){
	String uri=((String)params[0]).replace(sSPC,"%20")
	if(!uri){
		error "Error executing external web request:no URI",rtD
		return lZERO
	}
	String method=(String)params[1]
	Boolean useQueryString= (method in [sGET,sDELETE,sHEAD])
	String requestBodyType=(String)params[2]
	def variables=params[3]
	String auth=sNULL
	def requestBody=null
	String contentType=sNULL
	if(params.size()==5){
		auth=(String)params[4]
	}else if(params.size()==7){
		requestBody=(String)params[4]
		contentType=(String)params[5] ?: 'text/plain'
		auth=(String)params[6]
	}
	String protocol="https"
	String requestContentType=(method==sGET || requestBodyType=='FORM')? sAPPFORM : (requestBodyType=="JSON")? sAPPJSON:contentType
	String userPart=sBLK
	//List uriParts=uri.split("://").toList()
	String[] uriParts=uri.split("://")
	if(uriParts.size()>2){
		warn "Invalid URI for web request:$uri",rtD
		return lZERO
	}
	if(uriParts.size()==2){
		//remove the httpX:// from the uri
		protocol=(String)uriParts[0].toLowerCase()
		uri=(String)uriParts[1]
	}
	//support for user:pass@IP
	if((Boolean)uri.contains(sAT)){
		//List uriSubParts= uri.split(sAT).toList()
		String[] uriSubParts= uri.split(sAT as String)
		userPart=uriSubParts[0]+sAT
		uri=uriSubParts[1]
	}
	def data=null
	if(requestBodyType=='CUSTOM' && !useQueryString){
		data=requestBody
	}else if(variables instanceof List){
		for(String variable in ((List)variables).findAll{ !!it }){
			data=data ?: [:]
			data[variable]=getVariable(rtD,variable).v
		}
	}
	if(!useQueryString && requestContentType==sAPPFORM && data instanceof Map){
		data=data.collect{ String k,v -> encodeURIComponent(k)+'='+encodeURIComponent(v) }.join(sAMP)
	}
	try{
		Map requestParams=[
			uri: protocol+'://'+userPart+uri,
			query: useQueryString ? data:null,
			headers: (auth ? (((Boolean)auth.startsWith('{') && (Boolean)auth.endsWith('}'))? (new JsonSlurper().parseText(auth)):[Authorization: auth]):[:]),
			contentType: '*/*',
			requestContentType: requestContentType,
			body: !useQueryString ? data:null,
			timeout:20
		]
		String func=sBLK
		switch(method){
			case sGET:
				func='asynchttpGet'
				break
			case 'POST':
				func='asynchttpPost'
				break
			case 'PUT':
				func='asynchttpPut'
				break
			case sDELETE:
				func='asynchttpDelete'
				break
			case sHEAD:
				func='asynchttpHead'
				break
		}
		if((Integer)rtD.logging>2)debug "Sending ${func} web request to: $uri",rtD
		if(func!=sBLK){
			"$func"('ahttpRequestHandler',requestParams,[command:sHTTPR])
			return 24000L
		}
	}catch(all){
		error "Error executing external web request:",rtD,-2,all
	}
	return lZERO
}

void ahttpRequestHandler(resp,Map callbackData){
	Boolean binary=false
	def t0=resp.getHeaders()
	String t1=t0!=null ? (String)t0."Content-Type" : sNULL
	String mediaType=t1 ? (String)(t1.toLowerCase()?.tokenize(';')[0]):sNULL
	switch(mediaType){
		case 'image/jpeg':
		case 'image/png':
		case 'image/gif':
			binary=true
	}
	def data=null
	def json=null
	Map setRtData=[:]
	String callBackC=(String)callbackData?.command
	Integer responseCode=resp.status
	//if(eric1() && (Integer)state.logging>2) log.debug "http status ${responseCode}\nmediaType ${mediaType}\nheaders $t0"

	Boolean success=false
	String erMsg=sNULL
	if(resp.hasError()){
		erMsg=" Response Status: ${resp.status} error Message: ${resp.getErrorMessage()}".toString()
		if(!responseCode) responseCode=500
	}
	Boolean respOk=(responseCode>=200 && responseCode<300)

	switch(callBackC){
		case sHTTPR:
			if(responseCode==204){ // no content
				mediaType=sBLK
			}else{
				if(respOk && resp.data){
					if(!binary){
						data=resp.data
						//if(eric1() && (Integer)state.logging>2) log.debug "http mediaType $mediaType RESP ${data}"
						if(data!=null && !(data instanceof Map) && !(data instanceof List)){
							def ndata=parseMyResp(data,mediaType)
							if(ndata) data=ndata
						}
					}else{
						if(resp.data!=null && resp.data instanceof java.io.ByteArrayInputStream){
							setRtData.mediaType=mediaType
							setRtData.mediaData=resp.data.decodeBase64() // HE binary data is b64encoded resp.data.getBytes()
						}
					}
				}else{
					erMsg='http'+erMsg
				}
			}
			break
		case 'lifx':
			def em=callbackData?.em
			if(!respOk)
				erMsg="lifx Error lifx sending ${em?.t}".toString()+erMsg
			break
		case sSENDE:
			String msg='Unknown error'
			def em=callbackData?.em
			if(respOk){
				data=resp.getJson()
				if(data!=null){
					if((String)data.result=='OK'){
						success=true
					}else{
						msg=((String)data.result).replace('ERROR ',sBLK)
					}
				}
			}
			if(!success){
				erMsg="Error sending email to ${em?.t}: ${msg}".toString()
			}
			break
		case sIFTTM:
			def em=callbackData?.em
			if(!respOk)
				erMsg="ifttt Error iftttMaker to ${em?.t}: ${em?.p1},${em?.p2},${em?.p3} ".toString()+erMsg
			break
		case sSTOREM:
			def mediaId=sNULL
			def mediaUrl=sNULL
			if(respOk){
				data=resp.getJson()
				if((String)data.result=='OK' && data.url){
					mediaId=data.id
					mediaUrl=data.url
				}else{
					if(data.message){
						erMsg="storeMedia Error storing media item: $data.message"+erMsg
					}
				}
				data=null
			}else erMsg='ifttt'+erMsg
			setRtData=[mediaId:mediaId,mediaUrl:mediaUrl]
	}
	if(erMsg!=sNULL) error erMsg,[:]

	handleEvents([(sDATE):new Date(),(sDEV):location,(sNM):sASYNCREP,(sVAL):callBackC,contentType:mediaType,responseData:data,jsonData:json,responseCode:responseCode,setRtData:setRtData])
}

private parseMyResp(a,String mediaType=sNULL){
	def ret=null
	if(a instanceof String || a instanceof GString){
		a=a.toString()
		Boolean expectJson= mediaType ? (Boolean)mediaType.contains('json') : false
		try{
			if((Boolean)a.startsWith('{') && (Boolean)a.endsWith('}')){
				ret=(LinkedHashMap)new JsonSlurper().parseText(a)
			}else if((Boolean)a.startsWith(sLB) && (Boolean)a.endsWith(sRB)){
				ret=(List)new JsonSlurper().parseText(a)
			}else if(expectJson || (mediaType in ['application/octet-stream'] && a.size() % 4 == 0) ){ // HE can return data Base64
				String dec=new String(a.decodeBase64())
				if(dec!=sNULL){
					def t0=parseMyResp(dec,sBLK)
					ret=t0==null ? dec:t0
				}
			}
		}catch(ignored){}
	}
	return ret
}

private Long vcmd_writeToFuelStream(Map rtD,device,List params){
	String canister=(String)params[0]
	String name=(String)params[1]
	def data=params[2]
	def source=params[3]

	Map req=[
		c: canister,
		n: name,
		s: source,
		d: data,
		i: (String)rtD.instanceId
	]
	if((Boolean)rtD.useLocalFuelStreams && name!=sNULL){
		parent.writeToFuelStream(req)
	}else{
		Map requestParams=[
			uri: "https://api-"+(String)rtD.region+'-'+((String)rtD.instanceId)[32]+".webcore.co:9247",
			path: "/fuelStream/write",
			headers: [ 'ST': (String)rtD.instanceId ],
			body: req,
			contentType: sAPPJSON,
			requestContentType: sAPPJSON,
			timeout:20
		]
		asynchttpPut('asyncFuel',requestParams,[bbb:0])
	}
	return lZERO
}

void asyncFuel(response,data){
	if(response.status==200){
		return
	}
	error "Error storing fuel stream: $response?.data?.message",[:]
}

private Long vcmd_storeMedia(Map rtD,device,List params){
	if(!rtD.mediaData || !rtD.mediaType || !(rtD.mediaData)|| ((Integer)rtD.mediaData.size()<=0)){
		error 'No media is available to store; operation aborted.',rtD
		return lZERO
	}
	String data=new String(rtD.mediaData as byte[],'ISO_8859_1')
	Map requestParams=[
		uri: "https://api-"+(String)rtD.region+'-'+((String)rtD.instanceId)[32]+".webcore.co:9247",
		path: "/media/store",
		headers: [
			'ST':(String)rtD.instanceId,
			'media-type':rtD.mediaType
		],
		body: data,
		requestContentType: rtD.mediaType,
		timeout:20
	]
	asynchttpPut('asyncRequestHandler',requestParams,[command:sSTOREM])
	return 24000L
}

private Long vcmd_saveStateLocally(Map rtD,device,List params,Boolean global=false){
	List<String> attributes=((String)cast(rtD,params[0],sSTR)).tokenize(sCOMMA)
	String canister=(params.size()>1 ? (String)cast(rtD,params[1],sSTR)+sCOLON : sBLK)+hashId(device.id)+sCOLON
	//noinspection GroovyAssignabilityCheck
	Boolean overwrite=!(params.size()>2 ? (Boolean)cast(rtD,params[2],sBOOLN):false)
	for(String attr in attributes){
		String n=canister+attr
		if(global && !(Boolean)rtD.initGStore){
			rtD.globalStore=(Map)parent.getGStore()
			rtD.initGStore=true
		}
		if(overwrite || (global ? (rtD.globalStore[n]==null):(rtD.store[n]==null))){
			def value=getDeviceAttributeValue(rtD,device,attr)
			if(attr==sHUE)value=value*3.6D
			if(global){
				rtD.globalStore[n]=value
				LinkedHashMap<String,Object> cache= (LinkedHashMap)rtD.gvStoreCache ?: [:] as LinkedHashMap<String,Object>
				cache[n]=value
				rtD.gvStoreCache=cache
			}else{
				rtD.store[n]=value
			}
		}
	}
	return lZERO
}

private Long vcmd_saveStateGlobally(Map rtD,device,List params){
	return vcmd_saveStateLocally(rtD,device,params,true)
}

private Long vcmd_loadStateLocally(Map rtD,device,List params,Boolean global=false){
	List<String> attributes=((String)cast(rtD,params[0],sSTR)).tokenize(sCOMMA)
	String canister=(params.size()>1 ? (String)cast(rtD,params[1],sSTR)+sCOLON : sBLK)+hashId(device.id)+sCOLON
	//noinspection GroovyAssignabilityCheck
	Boolean empty=params.size()>2 ? (Boolean)cast(rtD,params[2],sBOOLN):false
	for(String attr in attributes){
		String n=canister+attr
		if(global && !(Boolean)rtD.initGStore){
			rtD.globalStore=(Map)parent.getGStore()
			rtD.initGStore=true
		}
		def value=global ? rtD.globalStore[n]:rtD.store[n]
		if(attr==sHUE)value=(Double)cast(rtD,value,sDEC)/3.6D
		def a
		if(empty){
			if(global){
				a=((Map)rtD.globalStore).remove(n)
				Map cache=(Map)rtD.gvStoreCache ?: [:]
				cache[n]=null
				rtD.gvStoreCache=cache
			}else a=((Map)rtD.store).remove(n)
		}
		if(value==null)continue
		String exactCommand=sNULL
		String fuzzyCommand=sNULL
		for(command in PhysicalCommands()){
			if((String)command.value.a==attr){
				if(command.value.v==null){
					fuzzyCommand=(String)command.key
				}else{
					if((String)command.value.v==value){
						exactCommand=(String)command.key
						break
					}
				}
			}
		}
		String t0="Restoring attribute '$attr' to value '$value' using command".toString()
		if(exactCommand!=sNULL){
			if((Integer)rtD.logging>2)debug "${t0} $exactCommand()",rtD
			executePhysicalCommand(rtD,device,exactCommand)
			continue
		}
		if(fuzzyCommand!=sNULL){
			if((Integer)rtD.logging>2)debug "${t0} $fuzzyCommand($value)",rtD
			executePhysicalCommand(rtD,device,fuzzyCommand,value)
			continue
		}
		warn "Could not find a command to set attribute '$attr' to value '$value'",rtD
	}
	return lZERO
}

private Long vcmd_loadStateGlobally(Map rtD,device,List params){
	return vcmd_loadStateLocally(rtD,device,params,true)
}

private Long vcmd_parseJson(Map rtD,device,List params){
	String data=params[0]
	try{
		if((Boolean)data.startsWith('{') && (Boolean)data.endsWith('}')){
			rtD.json=(LinkedHashMap)new JsonSlurper().parseText(data)
		}else if((Boolean)data.startsWith(sLB) && (Boolean)data.endsWith(sRB)){
			rtD.json=(List)new JsonSlurper().parseText(data)
		}else{
			rtD.json=[:]
		}
	}catch(all){
		error "Error parsing JSON data $data",rtD,-2,all
	}
	return lZERO
}

private static Long vcmd_cancelTasks(Map rtD,device,List params){
	rtD.cancelations.all=true
	return lZERO
}

private Boolean evaluateConditions(Map rtD,Map conditions,String collection,Boolean async){
	String myS=sBLK
	Boolean ntf=(Boolean)rtD.eric
	if(ntf){
		myS="evaluateConditions "
		myDetail rtD,myS,1
	}
	Long t=now()
	Map msg=null
	Integer lg=(Integer)rtD.logging
	if(lg>2)msg=timer sBLK,rtD
	//override condition id
	Integer c=(Integer)rtD.stack.c
	Integer myC=conditions.$!=null ? (Integer)conditions.$:0
	rtD.stack.c=myC
	Boolean not= collection==sC ? !!conditions.n:!!conditions.rn
	String grouping= collection==sC ? (String)conditions.o:(String)conditions.rop
	Boolean value= grouping!=sOR

	if(grouping=='followed by' && collection==sC){
		if((Integer)rtD.ffTo==0 || (Integer)rtD.ffTo==myC){
			//we're dealing with a followed by condition
			String sidx='c:fbi:'+myC.toString()
			Integer ladderIndex=(Integer)cast(rtD,((Map)rtD.cache)[sidx],sINT)
			String sldt='c:fbt:'+myC.toString()
			Long ladderUpdated=(Long)cast(rtD,((Map)rtD.cache)[sldt],sDTIME)
			Integer steps=conditions[collection] ? ((List)conditions[collection]).size():0
			if(ladderIndex>=steps){
				value=false
			}else{
				Map condition=((List<Map>)conditions[collection])[ladderIndex]
				Long duration=lZERO
				if(ladderIndex){
					Map tv=(Map)evaluateOperand(rtD,null,(Map)condition.wd)
					duration=(Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):tv.v,(sVT):(String)tv.vt],sLONG).v
				}
				if(ladderUpdated && duration!=lZERO && (ladderUpdated+duration<now())){
					//time has expired
					value=((String)condition.wt==sN)
					if(!value){
						if(lg>2)debug "Conditional ladder step failed due to a timeout",rtD
					}
				}else{
					value=evaluateCondition(rtD,condition,collection,async)
					if((String)condition.wt==sN){
						if(value){
							value=false
						}else{
							value=null
						}
					}
					//we allow loose matches to work even if other events happen
					if((String)condition.wt==sL && !value)value=null
				}
				if(value){
					//successful step,move on
					ladderIndex += 1
					ladderUpdated=now()
					cancelStatementSchedules(rtD,myC)
					if(lg>2)debug "Condition group #${myC} made progress up the ladder; currently at step $ladderIndex of $steps",rtD
					if(ladderIndex<steps){
						//delay decision, there are more steps to go through
						value=null
						condition=((List<Map>)conditions[collection])[ladderIndex]
						Map tv=(Map)evaluateOperand(rtD,null,(Map)condition.wd)
						duration=(Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):tv.v,(sVT):(String)tv.vt],sLONG).v
						requestWakeUp(rtD,conditions,conditions,duration)
					}
				}
			}

			switch(value){
				case null:
					//we need to exit time events set to work out the timeouts...
					if((Integer)rtD.ffTo==myC)rtD.terminated=true
					break
				case true:
				case false:
					//ladder either collapsed or finished,reset data
					ladderIndex=0
					ladderUpdated=lZERO
					cancelStatementSchedules(rtD,myC)
					break
			}
			if((Integer)rtD.ffTo==myC)rtD.ffTo=0
			rtD.cache[sidx]=ladderIndex
			rtD.cache[sldt]=ladderUpdated
		}
	}else{
		Boolean res
		for(Map condition in (List<Map>)conditions[collection]){
			res=evaluateCondition(rtD,condition,collection,async)
			value= grouping==sOR ? value||res : value&&res
			//cto == disable condition traversal optimizations
			if((Integer)rtD.ffTo==0 && !rtD.piston.o?.cto && ((value && grouping==sOR) || (!value && grouping==sAND)))break
		}
	}
	Boolean result=false
	if(value!=null){
		result=not ? !value:value
	}
	if(value!=null && myC!=0){
		String mC="c:${myC}".toString()
		if((Integer)rtD.ffTo==0)tracePoint(rtD,mC,elapseT(t),result)
		Boolean oldResult=!!(Boolean)((Map)rtD.cache)[mC]
		rtD.conditionStateChanged=(oldResult!=result)
		if((Boolean)rtD.conditionStateChanged){
			//condition change,perform Task Cancellation Policy TCP
			cancelConditionSchedules(rtD,myC)
		}
		rtD.cache[mC]=result
		//true/false actions
		if(collection==sC){
			if((result || (Integer)rtD.ffTo!=0) && conditions.ts!=null && ((List)conditions.ts).size())Boolean a=executeStatements(rtD,(List)conditions.ts,async)
			if((!result || (Integer)rtD.ffTo!=0) && conditions.fs!=null && ((List)conditions.fs).size())Boolean a=executeStatements(rtD,(List)conditions.fs,async)
		}
		if((Integer)rtD.ffTo==0 && lg>2){
			msg.m="Condition group #${myC} evaluated $result (state ${(Boolean)rtD.conditionStateChanged ? 'changed' : 'did not change'})".toString()
			debug msg,rtD
		}
	}
	//restore condition id
	rtD.stack.c=c
	if(ntf) myDetail rtD,myS+"result:$result",-1
	return result
}

@SuppressWarnings('GroovyFallthrough')
private evaluateOperand(Map rtD,Map node,Map oper,index=null,Boolean trigger=false,Boolean nextMidnight=false){
	String myS=sBLK
	Boolean ntf=(Boolean)rtD.eric
	if(ntf){
		myS="evaluateOperand $oper "
		myDetail rtD,myS,1
	}
	List<Map> values=[]
	Map operand=oper
	//older pistons don't have the 'to' operand (time offset),we're simulating an empty one
	if(!operand)operand=[(sT):sC]
	String ovt=(String)operand.vt
	String nodeI="${node?.$}:$index:0".toString()
	switch((String)operand.t){
		case sBLK: //optional,nothing selected
			values=[[(sI):nodeI,(sV):[(sT):ovt,(sV):null]]]
			break
		case sP: //physical device
			String operA=(String)operand.a
			Map attribute=operA ? Attributes()[operA] : [:]
			Boolean a
			for(String deviceId in expandDeviceList(rtD,(List)operand.d)){
				Map value=[(sI): deviceId+sCOLON+operA,(sV):getDeviceAttribute(rtD,deviceId,operA,operand.i,trigger)+(ovt ? [(sVT):ovt]:[:])+(attribute && attribute.p ? [p:operand.p]:[:])]
				updateCache(rtD,value)
				a=values.push(value)
			}
			if(values.size()>1 && !((String)operand.g in [sANY,sALL])){
				//if we have multiple values and a grouping other than any or all we need to apply that function
				try{
					values=[[(sI):nodeI,(sV):(Map)"func_${(String)operand.g}"(rtD,values*.v)+(ovt ? [(sVT):ovt]:[:])]]
				}catch(ignored){
					error "Error applying grouping method ${(String)operand.g}",rtD
				}
			}
			break
		case sD: //devices
			List deviceIds=[]
			Boolean a
			for(String d in expandDeviceList(rtD,(List)operand.d)){
				if(getDevice(rtD,d))a=deviceIds.push(d)
			}
			values=[[(sI):"${node?.$}:d".toString(),(sV):[(sT):sDEV,(sV):deviceIds.unique()]]]
			break
		case sV: //virtual devices
			String rEN=(String)rtD.event.name
			String evntVal="${rtD.event.value}".toString()
			String nodeV="${node?.$}:v".toString()
			String oV=(String)operand.v
			switch(oV){
				case sTIME:
				case sDATE:
				case sDTIME:
					values=[[(sI):nodeV,(sV):[(sT):oV,(sV):(Long)cast(rtD,now(),oV,sLONG)]]]
					break
				case sMODE:
				case sHSMSTS:
				case 'alarmSystemStatus':
					values=[[(sI):nodeV,(sV):getDeviceAttribute(rtD,(String)rtD.locationId,oV)]]
					break
				case 'hsmAlert':
				case 'alarmSystemAlert':
					String valStr=evntVal+(rEN=='hsmAlert' && evntVal=='rule' ? ",${(String)rtD.event.descriptionText}".toString():sBLK)
					values=[[(sI):nodeV,(sV):[(sT):sSTR,(sV):(rEN=='hsmAlert' ? valStr:sNULL)]]]
					break
				case "hsmSetArm":
				case 'alarmSystemEvent':
					values=[[(sI):nodeV,(sV):[(sT):sSTR,(sV):(rEN=='hsmSetArm' ? evntVal:sNULL)]]]
					break
				case 'alarmSystemRule':
					values=[[(sI):nodeV,(sV):[(sT):sSTR,(sV):(rEN=='hsmRules' ? evntVal:sNULL)]]]
					break
				case 'powerSource':
					values=[[(sI):nodeV,(sV):[(sT):sENUM,(sV):rtD.powerSource]]]
					break
				case 'routine':
					values=[[(sI):nodeV,(sV):[(sT):sSTR,(sV):(rEN=='routineExecuted' ? hashId(evntVal):sNULL)]]]
					break
				case 'systemStart':
					values=[[(sI):nodeV,(sV):[(sT):sSTR,(sV):(rEN=='systemStart' ? evntVal:sNULL)]]]
					break
				case 'tile':
					values=[[(sI):nodeV,(sV):[(sT):sSTR,(sV):(rEN==oV ? evntVal:sNULL)]]]
					break
				case 'ifttt':
					values=[[(sI):nodeV,(sV):[(sT):sSTR,(sV):(rEN==('ifttt.'+evntVal)? evntVal:sNULL)]]]
					break
				case 'email':
					values=[[(sI):nodeV,(sV):[(sT):'email',(sV):(rEN==('email.'+evntVal)? evntVal:sNULL)]]]
					break
			}
			break
		case sS: //preset
			Boolean time=false
			//noinspection GroovyFallthrough
			switch(ovt){
				case sTIME:
					time=true
				case sDTIME:
					Long v=lZERO
					switch((String)operand.s){
						case 'sunset': v=getSunsetTime(rtD); break
						case 'sunrise': v=getSunriseTime(rtD); break
						case 'midnight': v=nextMidnight ? getNextMidnightTime():getMidnightTime(); break
						case 'noon': v=getNoonTime(); break
					}
					if(time)v=(Long)cast(rtD,v,ovt,sDTIME)
					values=[[(sI):nodeI,(sV):[(sT):ovt,(sV):v]]]
					break
				default:
					values=[[(sI):nodeI,(sV):[(sT):ovt,(sV):operand.s]]]
					break
			}
			break
		case sX: //variable
			if(ovt==sDEV && operand.x instanceof List){
				//we could have multiple devices selected
				List asum=[]
				Map avar
				for(String x in (List)operand.x){
					avar=getVariable(rtD,x)
					if(avar.v instanceof List){
						asum+=(List)avar.v
					}else{
						Boolean a=asum.push(avar.v)
					}
				}
				values=[[(sI):nodeI,(sV):[(sT):sDEV,(sV):asum]+(ovt ? [(sVT):ovt]:[:])]]
			}else{
				values=[[(sI):nodeI,(sV):getVariable(rtD,(String)operand.x+((String)operand.xi!=sNULL ? sLB+(String)operand.xi+sRB:sBLK))+(ovt ? [(sVT):ovt]:[:])]]
			}
			break
		case sC: //constant
			switch(ovt){
				case sTIME:
					Long offset=(operand.c instanceof Integer)? operand.c.toLong():(Long)cast(rtD,operand.c,sLONG)
					values=[[(sI):nodeI,(sV):[(sT):sTIME,(sV):(offset%1440L)*60000L]]]
					break
				case sDATE:
				case sDTIME:
					values=[[(sI):nodeI,(sV):[(sT):ovt,(sV):operand.c]]]
					break
			}
			if(values.size()!=0)break
		case sE: //expression
			values=[[(sI):nodeI,(sV): [:]+evaluateExpression(rtD,(Map)operand.exp) + (ovt ? [(sVT):ovt]:[:])]]
			break
		case 'u': //expression
			values=[[(sI):nodeI,(sV):getArgument(rtD,(String)operand.u)]]
			break
	}

	if(node==null){ // return a Map instead of a List
		Map ret
		if(values.size())ret=(Map)values[0].v
		else ret=[(sT):sDYN,(sV):null]
		if(ntf) myDetail rtD,myS+"result:$ret",-1
		return ret
	}
	if(ntf) myDetail rtD,myS+"result:$values",-1
	return values
}

private Map evaluateScalarOperand(Map rtD,Map node,Map operand,index=null,String dataType=sSTR){
	Map value=(Map)evaluateOperand(rtD,null,operand,index)
	return [(sT):dataType,(sV):cast(rtD,(value ? value.v:sBLK),dataType)]
}

private Boolean evaluateCondition(Map rtD,Map condition,String collection,Boolean async){
	String myS=sBLK
	String str='evaluateCondition'
	if((Boolean)rtD.eric){
		myS=str+" $condition".toString()
		myDetail rtD,myS,1
	}

	if((String)condition.t==sGROUP){
		Boolean tt1=evaluateConditions(rtD,condition,collection,async)
		if((Boolean)rtD.eric) myDetail rtD,myS+" result:$tt1",-1
		return tt1
	}

	Long t=now()
	Map msg=[:]
	if((Integer)rtD.logging>2)msg=timer sBLK,rtD
	//override condition id
	Integer c=(Integer)rtD.stack.c
	Integer conditionNum=condition.$!=null ? (Integer)condition.$:0
	rtD.stack.c=conditionNum
	String sIndx="c:${conditionNum}".toString()
	Boolean oldResult=!!(Boolean)((Map)rtD.cache)[sIndx]
	Boolean result=false

	Boolean not=!!condition.n
	Map comparison=Comparisons().triggers[(String)condition.co]
	Boolean trigger=comparison!=null
	if(!trigger)comparison=Comparisons().conditions[(String)condition.co]
	rtD.wakingUp=(String)rtD.event.name==sTIME && rtD.event.schedule!=null && (Integer)rtD.event.schedule.s==conditionNum
	if((Integer)rtD.ffTo!=0 || comparison!=null){
		Boolean isStays=((String)condition.co).startsWith('stays')
		if((Integer)rtD.ffTo==0 || ((Integer)rtD.ffTo==-9 /*initial run*/)){
			Integer paramCount=comparison.p!=null ? (Integer)comparison.p:0
			Map lo=null
			Map ro=null
			Map ro2=null
			for(Integer i=0; i<=paramCount; i++){
				Map operand=(i==0 ? (Map)condition.lo:(i==1 ? (Map)condition.ro : (Map)condition.ro2))
				//parse the operand
				List values=(List)evaluateOperand(rtD,condition,operand,i,trigger)
				switch(i){
					case 0:
						lo=[operand:operand,values:values]
						break
					case 1:
						ro=[operand:operand,values:values]
						break
					case 2:
						ro2=[operand:operand,values:values]
						break
				}
			}

			//we now have all the operands,their values, and the comparison, let's get to work
			Boolean t_and_compt=(trigger && comparison.t!=null)
			Map options=[
					//we ask for matching/non-matching devices if the user requested it or if the trigger is timed
					//setting matches to true will force the condition group to evaluate all members (disables evaluation optimizations)
					matches: lo.operand.dm!=null || lo.operand.dn!=null || t_and_compt,
					forceAll: t_and_compt
			]
			Map to=(comparison.t!=null || (ro!=null && (String)lo.operand.t==sV && (String)lo.operand.v==sTIME && (String)ro.operand.t!=sC)) && condition.to!=null ? [operand: (Map)condition.to,values: (Map)evaluateOperand(rtD,null,(Map)condition.to)]:null
			Map to2=ro2!=null && (String)lo.operand.t==sV && (String)lo.operand.v==sTIME && (String)ro2.operand.t!=sC && condition.to2!=null ? [operand: (Map)condition.to2,values: (Map)evaluateOperand(rtD,null,(Map)condition.to2)]:null

			if((Boolean)rtD.eric && trigger && (Integer)rtD.ffTo==0 && (Integer)rtD.statementLevel!=1){
				myDetail rtD,"trigger comparison ${condition.co} at level > 1  level: ${rtD.statementLevel}"
			}

			result=evaluateComparison(rtD,(String)condition.co,lo,ro,ro2,to,to2,options)

			//save new values to cache
			if(lo)for(Map value in (List<Map>)lo.values)updateCache(rtD,value)
			if(ro)for(Map value in (List<Map>)ro.values)updateCache(rtD,value)
			if(ro2)for(Map value in (List<Map>)ro2.values)updateCache(rtD,value)
			if(lo.operand.dm!=null && options.devices!=null)def m=setVariable(rtD,(String)lo.operand.dm,options.devices.matched!=null ? (List)options.devices.matched:[])
			if(lo.operand.dn!=null && options.devices!=null)def n=setVariable(rtD,(String)lo.operand.dn,options.devices.unmatched!=null ? (List)options.devices.unmatched:[])

			//do the stays logic here
			if(t_and_compt && (Integer)rtD.ffTo==0){
				//trigger on device:attribute and timed trigger
				if(eric())log.debug "stays check ${condition.co} isStays: $isStays result: $result"
				if(to!=null){
					Map tvalue=(Map)to.operand && (Map)to.values ? (Map)to.values+[f: to.operand.f]:null
					if(tvalue!=null){
						Boolean isStaysUnchg=((String)condition.co=='stays_unchanged')
						Long delay=(Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):tvalue.v,(sVT):(String)tvalue.vt],sLONG).v

						List<Map> schedules
						Map t0=getCachedMaps(str)
						if(t0!=null)schedules=[]+(List<Map>)t0.schedules
						else schedules=(Boolean)rtD.pep ? (List<Map>)atomicState.schedules:(List<Map>)state.schedules

						if((String)lo.operand.t==sP && (String)lo.operand.g==sANY && ((List)lo.values).size()>1){
							List<String> chkList=(List)options.devices.matched
							if(eric())log.debug "stays check device options: $options"
							//if(!isStays) chkList=(List)options.devices.unmatched
							for(value in (List<Map>)lo.values){
								String dev=(String)value.v?.d
								doStaysProcess(rtD,schedules,isStays,isStaysUnchg,condition,conditionNum,delay,(dev in chkList),dev)
							}
						}else{
							if(eric())log.debug "stays check"
							doStaysProcess(rtD,schedules,isStays,isStaysUnchg,condition,conditionNum,delay,result,sNULL)
						}
					}else{ log.error "expecting time for stay and value not found $to  $tvalue" }  //; result=false }
				}else{ log.error "expecting time for stay and operand not found $to" } //;  result=false }
				if(isStays)result=false
			}
			result=not ? !result:result
		}else if((String)rtD.event.name==sTIME && (Integer)rtD.ffTo==conditionNum){ // we are ffwding - stays timer fired,pickup at result of if statement
			rtD.ffTo=0
			rtD.resumed=true
			if(isStays) result=!not
		}else{ // continue ffwding
			result=oldResult
		}
	}
	if((Integer)rtD.ffTo==0)tracePoint(rtD,sIndx,elapseT(t),result)

	rtD.wakingUp=false
	rtD.conditionStateChanged=oldResult!=result
	if((Boolean)rtD.conditionStateChanged){
		//condition change,perform Task Cancellation Policy TCP
		cancelConditionSchedules(rtD,conditionNum)
	}
	((Map)rtD.cache)[sIndx]=result
	//true/false actions
	if((result || (Integer)rtD.ffTo!=0) && condition.ts!=null && ((List)condition.ts).size()!=0)Boolean a=executeStatements(rtD,(List)condition.ts,async)
	if((!result || (Integer)rtD.ffTo!=0) && condition.fs!=null && ((List)condition.fs).size()!=0)Boolean a=executeStatements(rtD,(List)condition.fs,async)
	//restore condition id
	rtD.stack.c=c
	if((Integer)rtD.ffTo==0 && (Integer)rtD.logging>2){
		msg.m="Condition #${conditionNum} evaluated $result"
		debug msg,rtD
	}
	if((Integer)rtD.ffTo<=0 && (Boolean)condition.s && (String)condition.t==sCONDITION && condition.lo!=null && (String)condition.lo.t==sV){
		if(!LT1) { LT1=fill_TIM() }
		if((String)condition.lo.v in LT1){ scheduleTimeCondition(rtD,condition) }
	}
	if((Boolean)rtD.eric) myDetail rtD,myS+" result:$result",-1
	return result
}

void doStaysProcess(Map rtD,List<Map>schedules,Boolean isStays,Boolean isStaysUnchg,Map condition,Integer conditionNum,Long delay,Boolean result,String dev){
	Boolean canc=false
	Boolean schd=false
	String msgS
	if(isStays && result){
		//if we find the comparison true (ie reason to time stays has begun),set a timer if we haven't already
		msgS= dev ? " stays match in list" : " stays result true"
		if(!schedules.find{ Map it -> (Integer)it.s==conditionNum && (!dev || (String)it.d==dev) }){
			//schedule a wake up if there's none,otherwise just move on
			msgS += " scheduling timer "
			schd=true
		}else msgS += " found timer "
	}else{ // the comparison failed, normally cancel except for stays_unchanged
		msgS= dev ? " stays device did not match" : " stays result false"
		if(isStaysUnchg){
			msgS += " stays unchanged result false (it changed)"
			if (!schedules.find { Map it -> (Integer)it.s==conditionNum && (!dev || (String)it.d==dev) }) {
				msgS += " no timer creating timer "
				schd=true
			}else{
				msgS += " with timer active cancel timer "
				canc=true
			}
		}else{
			//cancel that one device schedule
			msgS += " cancel timers "
			canc=true
		}
	}
	String devM= dev ? "for device $dev " : ""
	msgS="timed trigger schedule${msgS}${devM}for condition ${conditionNum}"
	Integer lg=(Integer)rtD.logging
	if(canc){
		if(lg>2) debug "Cancelling any $msgS",rtD
		cancelStatementSchedules(rtD,conditionNum,dev)
	}
	if(schd){
		if(lg>2) debug "Adding a $msgS",rtD
		requestWakeUp(rtD,condition,condition,delay,dev)
	}
	if(!schd && !canc){
		if(lg>2) debug "Doing nothing found $msgS",rtD
	}
}

private void updateCache(Map rtD,Map value){
	Map oldValue=(Map)((Map)rtD.cache)[(String)value.i]
	if(oldValue==null || ((String)oldValue.t!=(String)value.v.t) || (oldValue.v!=value.v.v)){
		((Map)rtD.newCache)[(String)value.i]=(Map)value.v+[s: now()]
	}
}

private Boolean evaluateComparison(Map rtD,String comparison,Map lo,Map ro=null,Map ro2=null,Map to=null,Map to2=null,Map options=[:]){
	String mySt=sBLK
	if((Boolean)rtD.eric){
		mySt="evaluateComparison $comparison"
		myDetail rtD,mySt,1
	}
	Integer lg=(Integer)rtD.logging
	String fn="comp_"+comparison
	String loG= (String)lo.operand.g
	Boolean result= loG!=sANY
	Boolean oM=(Boolean)options.matches
	if(oM){ options.devices=[matched: [],unmatched: []] }
	//if multiple left values,go through each
	Map tvalue=to && to.operand && to.values ? (Map)to.values+[f: to.operand.f]:null
	Map tvalue2=to2 && to2.operand && to2.values ? (Map)to2.values:null
	if(!LT1) { LT1=fill_TIM() }
	for(Map<String,Map> value in (List<Map>)lo.values){
		Boolean res=false
		//x=eXclude - if a momentary attribute is looked for and the device does not match the current device, then we must ignore this during comparisons
		if(value && value.v && (!value.v.x || (Boolean)options.forceAll)){
			try{
				//physical support
				//value.p=lo.operand.p
				if(value && ((String)value.v.t==sDEV))value.v=evaluateExpression(rtD,(Map)value.v,sDYN)
				if(!ro){
					Map msg=[:]
					if(lg>2)msg=timer sBLK,rtD
					if(comparison=='event_occurs'){
						String compS=(String)lo.operand.v
						if(compS=='alarmSystemStatus') compS=sHSMSTS
						if(compS=='alarmSystemAlert') compS='hsmAlert'
						if(compS=='alarmSystemEvent') compS='hsmSetArm'
						if((String)lo.operand.t==sV && (String)rtD.event.name==compS){
							res=true
						}else if((String)value.v.d==hashId(rtD.event.device?.id) && (String)value.v.a==(String)rtD.event.name){
							res=true
							compS=(String)value.v.a
						}
						if(res && lg>2) msg.m="Comparison (string) ${compS} $comparison = $res"
					}else{
						res=(Boolean)"$fn"(rtD,value,null,null,tvalue,tvalue2)
						if(lg>2)msg.m="Comparison (${value.v.t}) ${value.v.v} $comparison = $res"
					}
					if(lg>2) debug msg,rtD
				}else{
					Boolean rres
					String roG= (String)ro.operand.g
					res= roG!=sANY
					//if multiple right values,go through each
					for(Map<String,Map> rvalue in (List<Map>)ro.values){
						if(rvalue && ((String)rvalue.v.t==sDEV))rvalue.v=evaluateExpression(rtD,(Map)rvalue.v,sDYN)
						if(!ro2){
							Map msg=[:]
							if(lg>2)msg=timer sBLK,rtD
							rres=(Boolean)"$fn"(rtD,value,rvalue,null,tvalue,tvalue2)
							if(lg>2){
								msg.m="Comparison (${value.v.t}) ${value.v.v} $comparison  (${rvalue?.v?.t}) ${rvalue?.v?.v} = $rres"
								debug msg,rtD
							}
						}else{
							String ro2G= (String)ro2.operand.g
							rres=ro2G!=sANY
							//if multiple right2 values,go through each
							for(Map<String,Map> r2value in (List<Map>)ro2.values){
								if(r2value && ((String)r2value.v.t==sDEV))r2value.v=evaluateExpression(rtD,(Map)r2value.v,sDYN)
								Map msg=[:]
								if(lg>2)msg=timer sBLK,rtD
//if((Boolean)rtD.eric) myDetail rtD,"$fn $value   $rvalue    $r2value    $tvalue   $tvalue2",1
								Boolean r2res=(Boolean)"$fn"(rtD,value,rvalue,r2value,tvalue,tvalue2)
//if((Boolean)rtD.eric) myDetail rtD,"$r2res  ${myObj(value?.v?.v)}    ${myObj(rvalue?.v?.v)}  $fn $value   $rvalue    $r2value    $tvalue   $tvalue2",-1
								if(lg>2){
									msg.m="Comparison (${value.v.t}) ${value.v.v} $comparison  (${rvalue?.v?.t}) ${rvalue?.v?.v} .. (${r2value?.v?.t}) ${r2value?.v?.v} = $r2res"
									debug msg,rtD
								}
								rres= ro2G==sANY ? rres||r2res : rres&&r2res
								if((ro2G==sANY && rres) || (ro2G!=sANY && !rres))break
							}
						}
						res=(roG==sANY ? res||rres : res&&rres)
						if((roG==sANY && res) || (roG!=sANY && !res))break
					}
				}
			}catch(all){
				error "Error calling comparison $fn:",rtD,-2,all
				res=false
			}

			if(res && (String)lo.operand.t==sV && (String)lo.operand.v in LT1){
				Boolean pass=(checkTimeRestrictions(rtD,(Map)lo.operand,(Long)now(),5,1)==lZERO)
				if(lg>2)debug "Time restriction check ${pass ? 'passed' : 'failed'}",rtD
				if(!pass)res=false
			}
		}
		result= loG==sANY ? result||res : result&&res
		if(oM){
			String vVD=(String)value.v.d
			if(vVD){
				Boolean a
				if(res) a=((List)options.devices.matched).push(vVD)
				else a=((List)options.devices.unmatched).push(vVD)
			}
		}else{
			// if not matching, see if we are done
			//logical OR if we're using the ANY keyword
			if(loG==sANY && res) break
			//logical AND if we're using the ALL keyword
			if(loG==sALL && !result) break
		}
	}
	if((Boolean)rtD.eric) myDetail rtD,mySt+" result:$result",-1
	return result
}

private void cancelStatementSchedules(Map rtD,Integer statementId,String data=sNULL){
	//cancel all schedules that are pending for statement statementId
	Boolean found=false
	for(Map item in (List<Map>)rtD.cancelations.statements){
		found=(statementId==(Integer)item.id && (!data || data==(String)item.data))
		if(found)break
	}
	if((Integer)rtD.logging>2)debug "Cancelling statement #${statementId}'s schedules...",rtD
	if(!found)Boolean a=((List<Map>)rtD.cancelations.statements).push([id: statementId,data: data])
}

private void cancelConditionSchedules(Map rtD,Integer conditionId){
	//cancel all schedules that are pending for condition conditionId
	if((Integer)rtD.logging>2)debug "Cancelling condition #${conditionId}'s schedules...",rtD
	if(!(conditionId in (List<Integer>)rtD.cancelations.conditions)){
		Boolean a=((List<Integer>)rtD.cancelations.conditions).push(conditionId)
	}
}

private static Boolean matchDeviceSubIndex(list,deviceSubIndex){
	//if (!list || !(list instanceof List) || list.size()==0) return true
	//return list.collect{ "$it".toString() }.indexOf("$deviceSubIndex".toString()) >= 0
	return true
}

private static Boolean matchDeviceInteraction(String option,Map rtD){
	Boolean isPhysical=(Boolean)rtD.currentEvent.physical
	return !((option==sP && !isPhysical) || (option==sS && isPhysical))
}

private List<Map> listPreviousStates(device,String attribute,Long threshold,Boolean excludeLast){
	List<Map> result=[]
	List<Map> events=(List<Map>)device.events([all: true,max: 100]).findAll{(String)it.name==attribute}
	//if we got any events,let's go through them
	//if we need to exclude last event, we start at the second event, as the first one is the event that triggered this function. The attribute's value has to be different from the current one to qualify for quiet
	Integer sz=events.size()
	if(sz!=0){
		Long thresholdTime=elapseT(threshold)
		Long endTime=now()
		for(Integer i=0; i<sz; i++){
			Long startTime=(Long)((Date)events[i].date).getTime()
			Long duration=endTime-startTime
			if(duration>=1L && (i>0 || !excludeLast)){
				Boolean a=result.push([(sVAL): events[i].value,startTime: startTime,duration: duration])
			}
			if(startTime<thresholdTime) break
			endTime=startTime
		}
	}else{
		def currentState=device.currentState(attribute,true)
		if(currentState){
			Long startTime=(Long)((Date)currentState.getDate()).getTime()
			Boolean a=result.push([(sVAL): currentState.value,startTime: startTime,duration: elapseT(startTime)])
		}
	}
	return result
}

private static Map valueCacheChanged(Map rtD,Map comparisonValue){
	def oV=((Map)rtD.cache)[(String)comparisonValue.i]
	Map newValue=(Map)comparisonValue.v
	Map oldValue= oV instanceof Map ? oV : null
	return (oldValue!=null && ((String)oldValue.t!=(String)newValue.t || "${oldValue.v}"!="${newValue.v}")) ? [(sI): (String)comparisonValue.i,(sV): oldValue] : null
}

private Boolean valueWas(Map rtD,Map comparisonValue,Map rightValue,Map rightValue2,Map timeValue,String func){
	if(comparisonValue==null || comparisonValue.v==null || !(String)comparisonValue.v.d || !(String)comparisonValue.v.a || timeValue==null || !timeValue.v || !(String)timeValue.vt){
		return false
	}
	def device=getDevice(rtD,(String)comparisonValue.v.d)
	if(device==null)return false
	String attribute=(String)comparisonValue.v.a
	Long threshold=(Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):timeValue.v,(sVT):(String)timeValue.vt],sLONG).v

	Boolean thisEventWokeUs=(rtD.event.device?.id==device.id && (String)rtD.event.name==attribute)
	List<Map> states=listPreviousStates(device,attribute,threshold,false) // thisEventWokeUs)
	Boolean result
	Long duration=lZERO
	Integer i=1
	for(Map stte in states){
		if(!(i==1 && thisEventWokeUs)){
			if(!("comp_$func"(rtD,[(sI): (String)comparisonValue.i,(sV): [(sT):(String)comparisonValue.v.t,(sV): cast(rtD,stte.value,(String)comparisonValue.v.t)]],rightValue,rightValue2,timeValue)))break
			duration += (Long)stte.duration
		}
		i+=1
	}
	if(duration==lZERO)return false
	result=((String)timeValue.f==sL)? duration<threshold:duration>=threshold
	if((Integer)rtD.logging>2)debug "Duration ${duration}ms for ${func.replace('is_','was_')} ${(String)timeValue.f==sL ? sLTH : sGTHE} ${threshold}ms threshold = ${result}",rtD
	return result
}

private Boolean valueChanged(Map rtD,Map comparisonValue,Map timeValue){
	if(comparisonValue==null || comparisonValue.v==null || !(String)comparisonValue.v.d || !(String)comparisonValue.v.a || timeValue==null || !timeValue.v || !(String)timeValue.vt){
		return false
	}
	def device=getDevice(rtD,(String)comparisonValue.v.d)
	if(device==null)return false
	String attribute=(String)comparisonValue.v.a
	Long threshold=(Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):timeValue.v,(sVT):(String)timeValue.vt],sLONG).v

	List<Map> states=listPreviousStates(device,attribute,threshold,false)
	if(states.size()==0)return false
	def value=states[0].value
	for(Map tstate in states){
		if(tstate.value!=value)return true
	}
	return false
}

private static Boolean match(String str,String pattern){
	Integer sz=pattern.size()
	if(sz>2 && (Boolean)pattern.startsWith(sDIV) && (Boolean)pattern.endsWith(sDIV)){
		def ppattern = ~pattern.substring(1,sz-1)
		return !!(str =~ ppattern)
	}
	return (Boolean)str.contains(pattern)
}

//comparison low level functions
private Boolean comp_is					(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return ((String)evaluateExpression(rtD,(Map)lv.v,sSTR).v==(String)evaluateExpression(rtD,(Map)rv.v,sSTR).v)|| (lv.v.n && ((String)cast(rtD,lv.v.n,sSTR)==(String)cast(rtD,rv.v.v,sSTR)))}
private Boolean comp_is_not				(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_equal_to		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ String dt=(((String)lv?.v?.t==sDEC)|| ((String)rv?.v?.t==sDEC)? sDEC:(((String)lv?.v?.t==sINT)|| ((String)rv?.v?.t==sINT)? sINT:sDYN)); return evaluateExpression(rtD,(Map)lv.v,dt).v==evaluateExpression(rtD,(Map)rv.v,dt).v }
private Boolean comp_is_not_equal_to	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_equal_to(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_different_than	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_not_equal_to(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_less_than		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (Double)evaluateExpression(rtD,(Map)lv.v,sDEC).v<(Double)evaluateExpression(rtD,(Map)rv.v,sDEC).v }
private Boolean comp_is_less_than_or_equal_to	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (Double)evaluateExpression(rtD,(Map)lv.v,sDEC).v<=(Double)evaluateExpression(rtD,(Map)rv.v,sDEC).v }
private Boolean comp_is_greater_than	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (Double)evaluateExpression(rtD,(Map)lv.v,sDEC).v>(Double)evaluateExpression(rtD,(Map)rv.v,sDEC).v }
private Boolean comp_is_greater_than_or_equal_to	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (Double)evaluateExpression(rtD,(Map)lv.v,sDEC).v>=(Double)evaluateExpression(rtD,(Map)rv.v,sDEC).v }
private Boolean comp_is_even			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return ((Integer) evaluateExpression(rtD,(Map)lv.v,sINT).v) % 2 ==0 }
private Boolean comp_is_odd				(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return ((Integer) evaluateExpression(rtD,(Map)lv.v,sINT).v) % 2 !=0 }
private Boolean comp_is_true			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (Boolean)evaluateExpression(rtD,(Map)lv.v,sBOOLN).v }
private Boolean comp_is_false			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !(Boolean)evaluateExpression(rtD,(Map)lv.v,sBOOLN).v }
private Boolean comp_is_inside_of_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Double v=(Double)evaluateExpression(rtD,(Map)lv.v,sDEC).v; Double v1=(Double)evaluateExpression(rtD,(Map)rv.v,sDEC).v; Double v2=(Double)evaluateExpression(rtD,(Map)rv2.v,sDEC).v; return (v1<v2) ? (v>=v1 && v<=v2):(v>=v2 && v<=v1)}
private Boolean comp_is_outside_of_range	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_inside_of_range(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_is_any_of			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ String v=(String)evaluateExpression(rtD,(Map)lv.v,sSTR).v; for(String vi in ((String)rv.v.v).tokenize(sCOMMA)){ if(v==(String)evaluateExpression(rtD,[(sT):(String)rv.v.t,(sV): "$vi".toString().trim(),i: rv.v.i,a: rv.v.a,(sVT):(String)rv.v.vt],sSTR).v)return true }; return false}
private Boolean comp_is_not_any_of		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_any_of(rtD,lv,rv,rv2,tv,tv2)}

private Boolean comp_was				(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is')}
private Boolean comp_was_not			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_not')}
private Boolean comp_was_equal_to		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_equal_to')}
private Boolean comp_was_not_equal_to	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_not_equal_to')}
private Boolean comp_was_different_than		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_different_than')}
private Boolean comp_was_less_than		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_less_than')}
private Boolean comp_was_less_than_or_equal_to		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_less_than_or_equal_to')}
private Boolean comp_was_greater_than	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_greater_than')}
private Boolean comp_was_greater_than_or_equal_to	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_greater_than_or_equal_to')}
private Boolean comp_was_even			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_even')}
private Boolean comp_was_odd			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_odd')}
private Boolean comp_was_true			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_true')}
private Boolean comp_was_false			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_false')}
private Boolean comp_was_inside_of_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_inside_of_range')}
private Boolean comp_was_outside_of_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_outside_of_range')}
private Boolean comp_was_any_of			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_any_of')}
private Boolean comp_was_not_any_of		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueWas(rtD,lv,rv,rv2,tv,'is_not_any_of')}

private Boolean comp_changed			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,tv2=null){ return valueChanged(rtD,lv,tv)}
private Boolean comp_did_not_change		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !valueChanged(rtD,lv,tv)}

private static Boolean comp_is_any		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return true }
private Boolean comp_is_before			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Long offset1=tv ? (Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):tv.v,(sVT):(String)tv.vt],sLONG).v:lZERO; return (Long)cast(rtD,(Long)evaluateExpression(rtD,(Map)lv.v,sDTIME).v+2000L,(String)lv.v.t)< (Long)cast(rtD,(Long)evaluateExpression(rtD,(Map)rv.v,sDTIME).v+offset1,(String)lv.v.t)}
private Boolean comp_is_after			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Long offset1=tv ? (Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):tv.v,(sVT):(String)tv.vt],sLONG).v:lZERO; return (Long)cast(rtD,(Long)evaluateExpression(rtD,(Map)lv.v,sDTIME).v+2000L,(String)lv.v.t)>= (Long)cast(rtD,(Long)evaluateExpression(rtD,(Map)rv.v,sDTIME).v+offset1,(String)lv.v.t)}
private Boolean comp_is_between			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Long offset1=tv ? (Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):tv.v,(sVT):(String)tv.vt],sLONG).v:lZERO; Long offset2=tv2 ? (Long)evaluateExpression(rtD,[(sT):sDURATION,(sV):tv2.v,(sVT):(String)tv2.vt],sLONG).v:lZERO; Long v=(Long)cast(rtD,(Long)evaluateExpression(rtD,(Map)lv.v,sDTIME).v+2000L,(String)lv.v.t); Long v1=(Long)cast(rtD,(Long)evaluateExpression(rtD,(Map)rv.v,sDTIME).v+offset1,(String)lv.v.t); Long v2=(Long)cast(rtD,(Long)evaluateExpression(rtD,(Map)rv2.v,sDTIME).v+offset2,(String)lv.v.t); return v1<v2 ? v>=v1 && v<v2 : v<v2 || v>=v1}
private Boolean comp_is_not_between		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_is_between(rtD,lv,rv,rv2,tv,tv2)}

/*triggers*/
private Boolean comp_gets				(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (String)cast(rtD,lv.v.v,sSTR)==(String)cast(rtD,rv.v.v,sSTR) && matchDeviceSubIndex(lv.v.i,(Integer)rtD.currentEvent.index)}
private Boolean comp_executes			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_arrives			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (String)rtD.event.name=='email' && match(rtD.event?.jsonData?.from ?: sBLK,(String)evaluateExpression(rtD,(Map)rv.v,sSTR).v) && match(rtD.event?.jsonData?.message ?: sBLK,(String)evaluateExpression(rtD,(Map)rv2.v,sSTR).v)}
private static Boolean comp_event_occurs		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return false }
private static Boolean comp_happens_daily_at		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return (Boolean)rtD.wakingUp }
private static Boolean comp_changes		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueCacheChanged(rtD,lv)!=null && matchDeviceInteraction((String)lv.v.p,rtD)}
private static Boolean comp_changes_to	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueCacheChanged(rtD,lv)!=null && ("${lv.v.v}"=="${rv.v.v}") && matchDeviceInteraction((String)lv.v.p,rtD)}
private static Boolean comp_receives	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return ("${lv.v.v}"=="${rv.v.v}") && matchDeviceInteraction((String)lv.v.p,rtD)}
private static Boolean comp_changes_away_from		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ("${oldValue.v.v}"=="${rv.v.v}") && matchDeviceInteraction((String)lv.v.p,rtD)}
private Boolean comp_drops				(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)>(Double)cast(rtD,lv.v.v,sDEC))}
private Boolean comp_does_not_drop		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_drops(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_drops_below		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)>=(Double)cast(rtD,rv.v.v,sDEC)) && ((Double)cast(rtD,lv.v.v,sDEC)<(Double)cast(rtD,rv.v.v,sDEC))}
private Boolean comp_drops_to_or_below	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)>(Double)cast(rtD,rv.v.v,sDEC)) && ((Double)cast(rtD,lv.v.v,sDEC)<=(Double)cast(rtD,rv.v.v,sDEC))}
private Boolean comp_rises				(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)<(Double)cast(rtD,lv.v.v,sDEC))}
private Boolean comp_does_not_rise		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_rises(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_rises_above		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)<=(Double)cast(rtD,rv.v.v,sDEC)) && ((Double)cast(rtD,lv.v.v,sDEC)>(Double)cast(rtD,rv.v.v,sDEC))}
private Boolean comp_rises_to_or_above	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)<(Double)cast(rtD,rv.v.v,sDEC)) && ((Double)cast(rtD,lv.v.v,sDEC)>=(Double)cast(rtD,rv.v.v,sDEC))}
private Boolean comp_remains_below		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)<(Double)cast(rtD,rv.v.v,sDEC)) && ((Double)cast(rtD,lv.v.v,sDEC)<(Double)cast(rtD,rv.v.v,sDEC))}
private Boolean comp_remains_below_or_equal_to		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)<=(Double)cast(rtD,rv.v.v,sDEC)) && ((Double)cast(rtD,lv.v.v,sDEC)<=(Double)cast(rtD,rv.v.v,sDEC))}
private Boolean comp_remains_above		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)>(Double)cast(rtD,rv.v.v,sDEC)) && ((Double)cast(rtD,lv.v.v,sDEC)>(Double)cast(rtD,rv.v.v,sDEC))}
private Boolean comp_remains_above_or_equal_to		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && ((Double)cast(rtD,oldValue.v.v,sDEC)>=(Double)cast(rtD,rv.v.v,sDEC)) && ((Double)cast(rtD,lv.v.v,sDEC)>=(Double)cast(rtD,rv.v.v,sDEC))}
private Boolean comp_enters_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); if(oldValue==null)return false; Double ov=(Double)cast(rtD,oldValue.v.v,sDEC); Double v=(Double)cast(rtD,lv.v.v,sDEC); Double v1=(Double)cast(rtD,rv.v.v,sDEC); Double v2=(Double)cast(rtD,rv2.v.v,sDEC); if(v1>v2){ Double vv=v1; v1=v2; v2=vv }; return ((ov<v1)|| (ov>v2)) && ((v>=v1) && (v<=v2))}
private Boolean comp_exits_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); if(oldValue==null)return false; Double ov=(Double)cast(rtD,oldValue.v.v,sDEC); Double v=(Double)cast(rtD,lv.v.v,sDEC); Double v1=(Double)cast(rtD,rv.v.v,sDEC); Double v2=(Double)cast(rtD,rv2.v.v,sDEC); if(v1>v2){ Double vv=v1; v1=v2; v2=vv }; return ((ov>=v1) && (ov<=v2)) && ((v<v1)|| (v>v2))}
private Boolean comp_remains_inside_of_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); if(oldValue==null)return false; Double ov=(Double)cast(rtD,oldValue.v.v,sDEC); Double v=(Double)cast(rtD,lv.v.v,sDEC); Double v1=(Double)cast(rtD,rv.v.v,sDEC); Double v2=(Double)cast(rtD,rv2.v.v,sDEC); if(v1>v2){ Double vv=v1; v1=v2; v2=vv }; return (ov>=v1) && (ov<=v2) && (v>=v1) && (v<=v2)}
private Boolean comp_remains_outside_of_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); if(oldValue==null)return false; Double ov=(Double)cast(rtD,oldValue.v.v,sDEC); Double v=(Double)cast(rtD,lv.v.v,sDEC); Double v1=(Double)cast(rtD,rv.v.v,sDEC); Double v2=(Double)cast(rtD,rv2.v.v,sDEC); if(v1>v2){ Double vv=v1; v1=v2; v2=vv }; return ((ov<v1)|| (ov>v2)) && ((v<v1) || (v>v2))}
private Boolean comp_becomes_even		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv);return oldValue!=null&&(((Integer)cast(rtD,oldValue.v.v,sINT))%2!=0) && (((Integer)cast(rtD,lv.v.v,sINT))%2==0)}
private Boolean comp_becomes_odd		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv);return oldValue!=null&&(((Integer)cast(rtD,oldValue.v.v,sINT))%2==0) && (((Integer)cast(rtD,lv.v.v,sINT))%2!=0)}
private Boolean comp_remains_even		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv);return oldValue!=null&&(((Integer)cast(rtD,oldValue.v.v,sINT))%2==0) && (((Integer)cast(rtD,lv.v.v,sINT))%2==0)}
private Boolean comp_remains_odd		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv);return oldValue!=null&&(((Integer)cast(rtD,oldValue.v.v,sINT))%2!=0) && (((Integer)cast(rtD,lv.v.v,sINT))%2!=0)}

private Boolean comp_changes_to_any_of			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return valueCacheChanged(rtD,lv)!=null && comp_is_any_of(rtD,lv,rv,rv2,tv,tv2) && matchDeviceInteraction((String)lv.v.p,rtD)}
private Boolean comp_changes_away_from_any_of		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ Map oldValue=valueCacheChanged(rtD,lv); return oldValue!=null && comp_is_any_of(rtD,oldValue,rv,rv2) && matchDeviceInteraction((String)lv.v.p,rtD)}

private Boolean comp_stays				(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is(rtD,lv,rv,rv2,tv,tv2)}
//private Boolean comp_stays_unchanged			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return true }
private static Boolean comp_stays_unchanged			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return !comp_changes(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_not				(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_not(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_equal_to			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_equal_to(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_different_than		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_different_than(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_less_than			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_less_than(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_less_than_or_equal_to	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_less_than_or_equal_to(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_greater_than			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_greater_than(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_greater_than_or_equal_to	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_greater_than_or_equal_to(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_even			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_even(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_odd			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_odd(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_true			(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_true(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_false		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_false(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_inside_of_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_inside_of_range(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_outside_of_range		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_outside_of_range(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_any_of		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_any_of(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_away_from	(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_not_equal_to(rtD,lv,rv,rv2,tv,tv2)}
private Boolean comp_stays_away_from_any_of		(Map rtD,Map lv,Map rv=null,Map rv2=null,Map tv=null,Map tv2=null){ return comp_is_not_any_of(rtD,lv,rv,rv2,tv,tv2)}

private void traverseStatements(node,Closure closure,parentNode=null,Map data=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List<Map>)node){
			if(!item.di){
				Boolean lastTimer=(data!=null && (Boolean)data.timer)
				if(data!=null && ((String)item.t==sEVERY)){
					data.timer=true
				}
				traverseStatements(item,closure,parentNode,data)
				if(data!=null){
					data.timer=lastTimer
				}
			}
		}
		return
	}

	//got a statement
	if(closure instanceof Closure){
		closure(node,parentNode,data)
	}

	//if the statements has substatements,go through them
	if(node.s instanceof List){
		traverseStatements((List)node.s,closure,node,data)
	}
	if(node.e instanceof List){
		traverseStatements((List)node.e,closure,node,data)
	}
}

private void traverseEvents(node,Closure closure,parentNode=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List)node){
			traverseEvents(item,closure,parentNode)
		}
		return
	}
	//got a condition
	if(closure instanceof Closure){
		closure(node,parentNode)
	}
}

private void traverseConditions(node,Closure closure,parentNode=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List)node){
			traverseConditions(item,closure,parentNode)
		}
		return
	}
	//got a condition
	if(node.t==sCONDITION && (closure instanceof Closure)){
		closure(node,parentNode)
	}
	//if the statements has substatements,go through them
	if(node.c instanceof List){
		if(closure instanceof Closure)closure(node,parentNode)
		traverseConditions((List)node.c,closure,node)
	}
}

private void traverseRestrictions(node,Closure closure,parentNode=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List)node){
			traverseRestrictions(item,closure,parentNode)
		}
		return
	}
	//got a restriction
	if(node.t==sRESTRIC && (closure instanceof Closure)){
		closure(node,parentNode)
	}
	//if the statements has substatements,go through them
	if(node.r instanceof List){
		if(closure instanceof Closure)closure(node,parentNode)
		traverseRestrictions((List)node.r,closure,node)
	}
}

private void traverseExpressions(node,Closure closure,param,parentNode=null){
	if(!node)return
	//if a statements element, go through each item
	if(node instanceof List){
		for(item in (List)node){
			traverseExpressions(item,closure,param,parentNode)
		}
		return
	}
	//got a statement
	if(closure instanceof Closure){
		closure(node,parentNode,param)
	}
	//if the statements has substatements,go through them
	if(node.i instanceof List){
		traverseExpressions((List)node.i,closure,param,node)
	}
}

private void updateDeviceList(Map rtD,List deviceIdList){
	app.updateSetting('dev',[(sTYPE): 'capability',(sVAL): deviceIdList.unique()])// settings update do not happen till next execution
}

@SuppressWarnings('GroovyFallthrough')
private void subscribeAll(Map rtD,Boolean doit=true){
	if(eric())log.debug "subscribeAll $doit"
	try{
		if(!rtD){ log.error "no rtD subscribeAll"; return }
		Map<String,Integer> ss=[
			events: 0,
			controls: 0,
			devices: 0,
		]
		Integer lg=(Integer)rtD.logging
		Map statementData=[timer:false]
		Map msg=timer "Finished subscribing",rtD,-1
		if(doit){
			removeAllInUseGlobalVar()
			unsubscribe()
			rtD.devices=[:]
			if(lg>1)trace "Subscribing to devices...",rtD,1
		}
		Map<String,Map> devices=[:]
		Map<String,Object> rawDevices=[:]
		Map<String,Map> subscriptions=[:]
		Boolean hasTriggers=false
		Boolean downgradeTriggers=false
		String never='never'
		//traverse all statements
		Closure expressionTraverser
		Closure operandTraverser
		Closure eventTraverser
		Closure conditionTraverser
		Closure restrictionTraverser
		Closure statementTraverser
		expressionTraverser={ Map expression,parentExpression,String comparisonType ->
			String subsId=sNULL
			String deviceId=sNULL
			String attribute=sNULL
			String exprID=(String)expression.id
			if((String)expression.t==sDEV && exprID){
				if(exprID==(String)rtD.oldLocationId)exprID=(String)rtD.locationId
				devices[exprID]=[(sC): (comparisonType ? 1:0)+(devices[exprID]?.c ? (Integer)devices[exprID].c:0)]
				deviceId=exprID
				attribute=(String)expression.a
				subsId=deviceId+attribute
			}
			String exprX=(String)expression.x
			if((String)expression.t==sVARIABLE && exprX && (Boolean)exprX.startsWith(sAT)){
				deviceId=(String)rtD.locationId
				if((Boolean)exprX.startsWith('@@')){
					String vn=exprX.substring(2)
					def hg=getGlobalVar(vn) // check if it exists
					if(hg){
						subsId=vn
						attribute=sVARIABLE+sCOLON+vn
					} else warn "hub varible not found while subscribing: $vn",rtD
				}else{
					subsId=exprX
					attribute=(String)rtD.instanceId+sDOT+exprX
				}
			}
			if(subsId!=sNULL && deviceId!=sNULL){
				String ct=(String)subscriptions[subsId]?.t ?: sNULL
				if(ct==sTRIG || comparisonType==sTRIG){
					ct=sTRIG
				}else{
					ct=ct ?: comparisonType
				}
				subscriptions[subsId]=[(sD):deviceId, (sA):attribute, (sT):ct, (sC):(subscriptions[subsId] ? (List)subscriptions[subsId].c:[])+(comparisonType? [expression]:[])]
				if(deviceId!=(String)rtD.locationId && (Boolean)deviceId.startsWith(sCOLON)){
					if(doit && !rawDevices[deviceId])rawDevices[deviceId]=getDevice(rtD,deviceId)
					devices[deviceId]=[(sC): (comparisonType ? 1:0)+(devices[deviceId]?.c ? (Integer)devices[deviceId].c:0)]
				}
			}
		}
		operandTraverser={ Map node,Map operand,value,String comparisonType ->
			if(!operand)return
			switch((String)operand.t){
				case sP: //physical device
					for(String mdeviceId in expandDeviceList(rtD,(List)operand.d,true)){
						String deviceId=mdeviceId
						if(deviceId==(String)rtD.oldLocationId)deviceId=(String)rtD.locationId
						devices[deviceId]=[(sC): (comparisonType ? 1:0)+(devices[deviceId]?.c ? (Integer)devices[deviceId].c:0)]
						String attribute=(String)operand.a
						String subsId=deviceId+attribute
						//if we have any trigger,it takes precedence over anything else
						String ct=(String)subscriptions[subsId]?.t ?: sNULL
						Boolean allowAval=(Boolean)null
						List<String> avals=[]
						if(ct==sTRIG || comparisonType==sTRIG){
							ct=sTRIG
							String msgVal
							hasTriggers=true

							allowAval= subscriptions[subsId]?.allowA==null ? true : (Boolean)subscriptions[subsId].allowA
							String attrVal=sNULL
							if(allowAval && ((String)node.co=='receives' || (String)node.co=='gets') && value && (String)value.t==sC && value.c){
								attrVal=(String)value.c
								//msgVal='Attempting Attribute value'
								avals=(List)subscriptions[subsId]?.avals ?: []
							}else allowAval=false
							if(allowAval && attrVal!=sNULL){
								if(! (attrVal in avals)) avals << attrVal
								msgVal='Attempting Attribute value '+avals
							}else{
								allowAval=false
								msgVal='Using Attribute'
								avals=[]
							}
							if(doit && lg>2)debug msgVal+' subscription',rtD
						}else{
							ct=ct ?: comparisonType
						}
						subscriptions[subsId]=[(sD): deviceId,(sA): attribute,(sT):ct,(sC): (subscriptions[subsId] ? (List)subscriptions[subsId].c:[])+(comparisonType?[node]:[]),allowA: allowAval,avals: avals]
						if(doit && deviceId!=(String)rtD.locationId && (Boolean)deviceId.startsWith(sCOLON) && !rawDevices[deviceId]){
							rawDevices[deviceId]=getDevice(rtD,deviceId)
						}
					}
					break
				case sV: //virtual device
					String deviceId=(String)rtD.locationId
					//if we have any trigger, it takes precedence over anything else
					devices[deviceId]=[(sC): (comparisonType ? 1:0)+(devices[deviceId]?.c ? (Integer)devices[deviceId].c:0)]
					String subsId=sNULL
					String attribute=sNULL
					String operV=(String)operand.v
					String tsubId=deviceId+operV
					switch(operV){
						case sTIME:
						case sDATE:
						case sDTIME:
						case sMODE:
						case 'powerSource':
						case 'systemStart':
							subsId=tsubId
							attribute=operV
							break
						case sHSMSTS:
						case 'alarmSystemStatus':
							subsId=tsubId
							attribute=sHSMSTS
							break
						case 'hsmAlert':
						case 'alarmSystemAlert':
							subsId=tsubId
							attribute="hsmAlert"
							break
						case "hsmSetArm":
						case 'alarmSystemEvent':
							subsId=tsubId
							attribute="hsmSetArm"
							break
						case 'alarmSystemRule':
							subsId=tsubId
							attribute="hsmRules"
							break
						case 'email':
							subsId="$deviceId${operV}${(String)rtD.id}".toString()
							attribute="email.${(String)rtD.id}".toString()// receive email does not work
							break
						case 'ifttt':
							if(value && (String)value.t==sC && value.c){
								Map<String,String> options=(Map)VirtualDevices()[operV]?.o
								String item=options ? (String)options[(String)value.c]:(String)value.c
								if(item){
									subsId="$deviceId${operV}${item}".toString()
									String attrVal=".${item}".toString()
									attribute="${operV}${attrVal}".toString()
								}
							}
							break
					}
					if(subsId!=sNULL){
						String ct=(String)subscriptions[subsId]?.t ?: sNULL
						if(ct==sTRIG || comparisonType==sTRIG){
							ct=sTRIG
							hasTriggers=true
						}else{
							ct=ct ?: comparisonType
						}
						subscriptions[subsId]=[(sD): deviceId,(sA): attribute,(sT):ct,(sC): (subscriptions[subsId] ? (List)subscriptions[subsId].c:[])+(comparisonType?[node]:[])]
						break
					}
					break
				case sX:
					String operX=(String)operand.x
					if(operX && (Boolean)operX.startsWith(sAT)){
						String subsId=operX
						String attribute="${(String)rtD.instanceId}.${operX}".toString()
						if((Boolean)operX.startsWith('@@')){
							String vn=operX.substring(2)
							def hg=getGlobalVar(vn) // check if it exists
							if(hg){
								subsId=vn
								attribute=sVARIABLE+sCOLON+vn
							} else warn "hub varible not found while subscribing: $vn",rtD
						}
						String ct=(String)subscriptions[subsId]?.t ?: sNULL
						if(ct==sTRIG || comparisonType==sTRIG){
							ct=sTRIG
							hasTriggers=true
						}else{
							ct=ct ?: comparisonType
						}
						subscriptions[subsId]=[(sD): (String)rtD.locationId,(sA): attribute,(sT):ct,(sC): (subscriptions[subsId] ? (List)subscriptions[subsId].c:[])+(comparisonType?[node]:[])]
					}
					break
				case sC: //constant
				case sE: //expression
					traverseExpressions(operand.exp?.i,expressionTraverser,comparisonType)
					break
			}
		}
		eventTraverser={ Map event,parentEvent ->
			if(event.lo){
				String comparisonType=sTRIG
				operandTraverser(event,(Map)event.lo,null,comparisonType)
			}
		}
		conditionTraverser={ Map condition,parentCondition ->
			if((String)condition.co){
				Map comparison=Comparisons().conditions[(String)condition.co]
				String comparisonType=sCONDITION
				if(comparison==null){
					hasTriggers=true
					comparisonType=downgradeTriggers || ((String)condition.sm==never)? sCONDITION:sTRIG //subscription method
					comparison=Comparisons().triggers[(String)condition.co]
				}
				if(comparison!=null){
					condition.ct=(String)comparisonType.take(1) // modifies the code
					Integer paramCount=comparison.p!=null ? (Integer)comparison.p: 0
					for(Integer i=0; i<=paramCount; i++){
						//get the operand to parse
						Map operand=(i==0 ? (Map)condition.lo:(i==1 ? (Map)condition.ro:(Map)condition.ro2))
						operandTraverser(condition,operand,condition.ro,comparisonType)
					}
				}
			}
			if(condition.ts instanceof List)traverseStatements((List)condition.ts,statementTraverser,condition,statementData)
			if(condition.fs instanceof List)traverseStatements((List)condition.fs,statementTraverser,condition,statementData)
		}
		restrictionTraverser={ Map restriction,parentRestriction ->
			if((String)restriction.co){
				Map comparison=Comparisons().conditions[(String)restriction.co]
				//String comparisonType=sCONDITION
				if(comparison==null){
					comparison=Comparisons().triggers[(String)restriction.co]
				}
				if(comparison!=null){
					Integer paramCount=comparison.p!=null ? (Integer)comparison.p: 0
					for(Integer i=0; i<=paramCount; i++){
						//get the operand to parse
						Map operand=(i==0 ? (Map)restriction.lo:(i==1 ? (Map)restriction.ro:(Map)restriction.ro2))
						operandTraverser(restriction,operand,null,sNULL)
					}
				}
			}
		}
		statementTraverser={ Map node,parentNode,Map data ->
			downgradeTriggers=data!=null && (Boolean)data.timer
			if(node.r)traverseRestrictions(node.r,restrictionTraverser)
			for(String mdeviceId in node.d){
				String deviceId=mdeviceId
				if(deviceId==(String)rtD.oldLocationId)deviceId=(String)rtD.locationId
				devices[deviceId]=devices[deviceId] ?: [(sC): 0]
				if(doit && deviceId!=(String)rtD.locationId && (Boolean)deviceId.startsWith(sCOLON) && !rawDevices[deviceId]){
					rawDevices[deviceId]=getDevice(rtD,deviceId)
				}
			}
			switch((String)node.t){
				case sACTION:
					if(node.k){
						for(Map k in (List<Map>)node.k){
							traverseStatements(k.p?:[],statementTraverser,k,data)
						}
					}
					break
				case sIF:
					if(node.ei){
						for(Map ei in (List<Map>)node.ei){
							traverseConditions(ei.c?:[],conditionTraverser)
							traverseStatements(ei.s?:[],statementTraverser,ei,data)
						}
					}
				case sWHILE:
				case sREPEAT:
					traverseConditions(node.c,conditionTraverser)
					break
				case sON:
					traverseEvents(node.c?:[],eventTraverser)
					break
				case sSWITCH:
					operandTraverser(node,(Map)node.lo,null,sCONDITION)
					for(Map c in (List<Map>)node.cs){
						operandTraverser(c,(Map)c.ro,null,sNULL)
						//if case is a range,traverse the second operand too
						if((String)c.t==sR)operandTraverser(c,(Map)c.ro2,null,sNULL)
						if(c.s instanceof List) traverseStatements((List)c.s,statementTraverser,node,data)
					}
					break
				case sEVERY:
					hasTriggers=true
					break
			}
		}
		if(rtD.piston.r)traverseRestrictions((List)rtD.piston.r,restrictionTraverser)
		if(rtD.piston.s)traverseStatements((List)rtD.piston.s,statementTraverser,null,statementData)
		//device variables
		for(Map variable in ((List<Map>)rtD.piston.v).findAll{ Map it -> (String)it.t==sDEV && it.v!=null && it.v.d!=null && it.v.d instanceof List}){
			for(String mdeviceId in (List)variable.v.d){
				String deviceId=mdeviceId
				if(deviceId==(String)rtD.oldLocationId)deviceId=(String)rtD.locationId
				devices[deviceId]=[(sC): 0+(devices[deviceId]?.c ? (Integer)devices[deviceId].c:0)]
				if(doit && deviceId!=(String)rtD.locationId && !rawDevices[deviceId]){
					rawDevices[deviceId]=getDevice(rtD,deviceId)
				}
			}
		}
		if(!LT1) { LT1=fill_TIM() }
		if(!LTHR) { LTHR=fill_THR() }
		Map<String,Integer> dds=[:]
		for(subscription in subscriptions){
			String devStr=(String)subscription.value.d
			String altSub=never
			String always='always'
			for(condition in (List<Map>)subscription.value.c)if(condition){
				condition.s=false // this modifies the code
				String tt0=(String)condition.sm
				altSub= tt0==always ? tt0 : (altSub!=always && tt0!=never ? tt0 : altSub)
			}
			// check for disabled event subscriptions
			if(!rtD.piston.o?.des && (String)subscription.value.t && !!subscription.value.c && altSub!=never && ((String)subscription.value.t==sTRIG || altSub==always || !hasTriggers)){
				def device=(Boolean)devStr.startsWith(sCOLON)? getDevice(rtD,devStr):null
				Boolean allowA=subscription.value.allowA!=null?(Boolean)subscription.value.allowA:false
				String a=(String)subscription.value.a
				if(a in LTHR){
					a=sTHREAX
					allowA=false
				}
				if(device!=null){
					for(condition in (List<Map>)subscription.value.c) if(condition){
						String t1=(String)condition.sm
						condition.s= t1!=never && ((String)condition.ct==sT || t1==always || !hasTriggers) // modifies the code
					}
					if(a in LT1) break
					else {
						Integer cnt=ss.events
						List avals=(List)subscription.value.avals
						if(allowA && avals.size()<9){
							for(String aval in avals){
								String myattr=a+sDOT+aval
								if(doit){
									if(lg>0)info "Subscribing to $device.${myattr}...",rtD
									subscribe(device,myattr,deviceHandler)
								}
								cnt+=1
							}
						}else{
							if(doit){
								if(lg>0)info "Subscribing to $device.${a}...",rtD
								subscribe(device,a,deviceHandler)
							}
							cnt+=1
						}
						ss.events=cnt
						String didS=device.id.toString()
						if(!dds[didS]){
							ss.devices=ss.devices+1
							dds[didS]=1
						}
					}
				}else{
					error "Failed subscribing to $devStr.${a}, device not found",rtD
				}
			}else{
				for(condition in (List<Map>)subscription.value.c)if(condition){ condition.s=false } // modifies the code
				if(devices[devStr]){
					devices[devStr].c=(Integer)devices[devStr].c-1
				}
			}
		}
		//not using fake subscriptions for controlled devices - piston has device in settings
		for(d in devices.findAll{ ((Integer)it.value.c<=0 || rtD.piston.o?.des) && (String)it.key!=(String)rtD.locationId }){
			def device=(Boolean)((String)d.key).startsWith(sCOLON)? getDevice(rtD,(String)d.key):null
			if(device!=null && !isDeviceLocation(device)){
				String didS=device.id.toString()
				if(lg>1 && doit)trace "Piston controls $device...",rtD
				ss.controls=ss.controls+1
				if(!dds[didS]){
					ss.devices=ss.devices+1
					dds[didS]=1
				}
			}
		}
		if(doit){
			//save devices
			List deviceIdList=rawDevices.collect{ it && it.value ? it.value.id:null }
			rawDevices=null
			Boolean a=deviceIdList.removeAll{ it==null }
			updateDeviceList(rtD,deviceIdList)

			state.subscriptions=ss
			if(lg>1)trace msg,rtD

			//subscribe(app,appHandler)
			subscribe(location,(String)rtD.id,executeHandler)
			Map event=[(sDATE):new Date(),(sDEV):location,(sNM):sTIME,(sVAL):now(),schedule:[(sT):lZERO,(sS):0,(sI):-9]]
			a=executeEvent(rtD,event)
			processSchedules rtD,true
			//save cache collected through dummy run
			for(item in (Map)rtD.newCache)((Map)rtD.cache)[(String)item.key]=item.value

			String str='subAll'
			Map t0=getCachedMaps(str)
			String myId=(String)rtD.id
			if(t0!=null){
				getCacheLock(str)
				theCacheVFLD[myId].cache=[:]+(Map)rtD.cache
				theCacheVFLD=theCacheVFLD
				releaseCacheLock()
			}
			state.cache=(Map)rtD.cache
		}

	}catch(all){
		error "An error has occurred while subscribing: ",rtD,-2,all
	}
}

private List<String> expandDeviceList(Map rtD,List devs,Boolean localVarsOnly=false){
	Boolean mlocalVars=false	//allowing global vars
	List<String>devices=devs
	List<String> result=[]
	for(String deviceId in devices){
		if(deviceId){
			if(isWcDev(deviceId)){
				Boolean a=result.push(deviceId)
			}else{
				if(mlocalVars){
					//during subscriptions we can use local vars only to make sure we don't subscribe to "variable" lists of devices
					Map var=(Map)rtD.localVars[deviceId]
					if(var && (String)var.t==sDEV && var.v instanceof Map && (String)var.v.t==sD && var.v.d instanceof List)result += (List)var.v.d
				}else{
					Map var=getVariable(rtD,deviceId)
					if((String)var.t==sDEV)result += (var.v instanceof List) ? (List)var.v : []
					else{
						def device=getDevice(rtD,(String)cast(rtD,var.v,sSTR))
						if(device!=null)result += [hashId(device.id)]
					}
				}
			}
		}
	}
	return result.unique()
}

//def appHandler(evt){
//}

private static String sanitizeVariableName(String name){
	String rname=name!=sNULL ? name.trim().replace(sSPC,'_'):sNULL
	return rname
}

private getDevice(Map rtD,String idOrName){
	if((String)rtD.locationId==idOrName || (String)rtD.oldLocationId==idOrName)return location
	def t0=((Map)rtD.devices)[idOrName]
	def device=t0!=null ? t0 : ((Map)rtD.devices).find{ (String)it.value.getDisplayName()==idOrName }?.value
	if(device==null){
		if(rtD.allDevices==null){
			Map msg=timer "Device missing from piston. Loading all from parent",rtD
			rtD.allDevices=(Map)parent.listAvailableDevices(true)
			if(eric()||(Integer)rtD.logging>2)debug msg,rtD
		}
		if(rtD.allDevices!=null){
			def deviceMap=((Map)rtD.allDevices).find{ (idOrName==(String)it.key)|| (idOrName==(String)it.value.getDisplayName())}
			if(deviceMap!=null){
				device=deviceMap.value
				rtD.updateDevices=true
				rtD.devices[(String)deviceMap.key]=device
			}
		}else{
			error "Device ${idOrName} was not found. Please review your piston.",rtD
		}
	}
	return device
}

@Field static List<String> LDAV
@Field static final String sSTS='$status'

private getDeviceAttributeValue(Map rtD,device,String attributeName){
	String rtDEvN=rtD.event!=null ? (String)rtD.event.name:sBLK
	Boolean rtDEdID=rtD.event!=null ? rtD.event.device?.id==device.id:false
	if(rtDEvN==attributeName && rtDEdID){
		return rtD.event.value
	}else{
		def result
		String msg="Error reading current value for ${device}.".toString()
		if(!LDAV) {
			if(!LTHR) { LTHR=fill_THR() }
			LDAV=[sSTS]+LTHR
		}
		if(attributeName in LDAV){
			switch(attributeName){
				case sSTS:
					return device.getStatus()
				default:
					Map xyz
					try{ xyz= rtDEvN==sTHREAX && rtDEdID && rtD.event.value ? rtD.event.value : null }catch(ignored){}
					if(xyz==null){
						try{
							xyz=device.currentValue(sTHREAX,true)
						}catch(al){
							error msg+sTHREAX+sCOLON,rtD,-2,al
							break
						}
					}
					switch(attributeName){
						case sORIENT:
							return getThreeAxisOrientation(xyz)
						case sAXISX:
							return xyz.x
						case sAXISY:
							return xyz.y
						case sAXISZ:
							return xyz.z
					}
			}
		}else{
			try{
				result=device.currentValue(attributeName,true)
			}catch(all){
				error msg+attributeName+sCOLON,rtD,-2,all
			}
		}
		return result!=null ? result:sBLK
	}
}

@Field static List<String> LTHR

private static List<String> fill_THR(){ return [sORIENT,sAXISX,sAXISY,sAXISZ] }

private Map getDeviceAttribute(Map rtD,String deviceId,String attributeName,subDeviceIndex=null,Boolean trigger=false){
	if(deviceId==(String)rtD.locationId || deviceId==(String)rtD.oldLocationId){ //backwards compatibility
		//we have the location here
		switch(attributeName){
			case sMODE:
				def mode=location.getCurrentMode()
				return [(sT):sSTR,(sV):hashId(mode.getId()),(sN):(String)mode.getName()]
			case sHSMSTS:
			case 'alarmSystemStatus':
				String v=location.hsmStatus
				String n=VirtualDevices()['alarmSystemStatus']?.o[v]
				return [(sT):sSTR,(sV):v,(sN):n]
		}
		return [(sT):sSTR,(sV):(String)location.getName()]
	}
	def device=getDevice(rtD,deviceId)
	if(device!=null){
		Map attribute=attributeName!=sNULL ? Attributes()[attributeName]:null
		if(attribute==null){
			attribute=[(sT):sSTR, /* m:false */ ]
		}
		def t0=(attributeName!=sNULL ? getDeviceAttributeValue(rtD,device,attributeName):null)
		String tt1=(String)attribute.t
//	String tt2=myObj(t0)
//	if(attributeName)log.warn "attributeName: $attributeName  t0:  ($tt2) $t0    tt1: $tt1"
		def value= attributeName!=sNULL ? (matchCast(rtD,t0,tt1) ? t0 : cast(rtD,t0,tt1)) :"$device"
		if(attributeName==sHUE){
			value=cast(rtD,(Double)cast(rtD,value,sDEC)*3.6D,(String)attribute.t)
		}
		//have to compare ids and type for hubitat since the locationid can be the same as the deviceid
		def tt0=rtD.event?.device!=null ? rtD.event.device:location
		Boolean deviceMatch=device?.id==tt0.id && isDeviceLocation(device)==isDeviceLocation(tt0)
		//x=eXclude - if a momentary attribute is looked for and the device does not match the current device, then we must ignore this during comparisons
		if(!LTHR) { LTHR=fill_THR() }
		return [
			(sT):(String)attribute.t,
			(sV):value,
			(sD):deviceId,
			(sA):attributeName,
			(sI):subDeviceIndex,
			(sX):(attribute.m!=null || trigger) && (!deviceMatch || (( attributeName in LTHR ? sTHREAX:attributeName)!=(String)rtD.event.name))
		]
	}
	return [(sT):sERROR,(sV):"Device '${deviceId}' not found"]
}

private Map getJsonData(Map rtD,data,String name,String feature=sNULL){
	if(data!=null){
	try{
		List<String> parts=name.replace('][','].[').tokenize(sDOT)
		def args=(data instanceof Map ? [:]+(Map)data : (data instanceof List ? []+(List)data : new JsonSlurper().parseText((String)data)))
		Integer partIndex=-1
		for(String part in parts){
			partIndex=partIndex+1
			if(args instanceof String || args instanceof GString){
				def narg=parseMyResp(args.toString())
				if(narg)args=narg
			}
			if(args instanceof List){
				List largs=(List)args
				Integer sz=largs.size()
				switch(part){
					case 'length':
						return [(sT):sINT,(sV):sz]
					case 'first':
						args=sz>0 ? largs[0]:sBLK
						continue
					case 'second':
						args=sz>1 ? largs[1]:sBLK
						continue
					case 'third':
						args=sz>2 ? largs[2]:sBLK
						continue
					case 'fourth':
						args=sz>3 ? largs[3]:sBLK
						continue
					case 'fifth':
						args=sz>4 ? largs[4]:sBLK
						continue
					case 'sixth':
						args=sz ? largs[5]:sBLK
						continue
					case 'seventh':
						args=sz>6 ? largs[6]:sBLK
						continue
					case 'eighth':
						args=sz>7 ? largs[7]:sBLK
						continue
					case 'ninth':
						args=sz>8 ? largs[8]:sBLK
						continue
					case 'tenth':
						args=sz>9 ? largs[9]:sBLK
						continue
					case 'last':
						args=sz>0 ? largs[sz- 1]:sBLK
						continue
				}
			}
			if(!(args instanceof Map) && !(args instanceof List))return [(sT):sDYN,(sV):sBLK]
			//nfl overrides
			Boolean overrideArgs=false
			if(feature=='NFL' && partIndex==1 && !!args && !!args.games){
				def offset=null
				def start=null
				def end=null
				Date date=localDate()
				Integer dow=date.day
				switch(((String)part.tokenize(sLB)[0]).toLowerCase()){
					case 'yesterday':
						offset=-1
						break
					case 'today':
						offset=0
						break
					case 'tomorrow':
						offset=1
						break
					case 'mon':
					case 'monday':
						offset=dow<=2 ? 1 - dow:8 - dow
						break
					case 'tue':
					case 'tuesday':
						offset=dow<=2 ? 2-dow:9-dow
						break
					case 'wed':
					case 'wednesday':
						offset=dow<=2 ? -4 - dow:3-dow
						break
					case 'thu':
					case 'thursday':
						offset=dow<=2 ? -3 - dow:4-dow
						break
					case 'fri':
					case 'friday':
						offset=dow<=2 ? -2 - dow:5-dow
						break
					case 'sat':
					case 'saturday':
						offset=dow<=2 ? -1 - dow:6-dow
						break
					case 'sun':
					case 'sunday':
						offset=dow<=2 ? 0 - dow:7-dow
						break
					case 'lastweek':
						start=(dow<=2 ? -4 - dow:3-dow)-7
						end=(dow<=2 ? 2 - dow:9-dow)-7
						break
					case 'thisweek':
						start=dow<=2 ? -4 - dow:3-dow
						end=dow<=2 ? 2 - dow:9-dow
						break
					case 'nextweek':
						start=(dow<=2 ? -4 - dow:3-dow)+7
						end=(dow<=2 ? 2 - dow:9-dow)+7
						break
				}
				if(offset!=null){
					date.setTime(Math.round((Long)date.getTime()+offset*dMSDAY))
					def game=args.games.find{ it.year==date.year+1900 && it.month==date.month+1 && it.day==date.date}
					args=game
					continue
				}
				if(start!=null){
					Date startDate=localDate()
					startDate.setTime(Math.round((Long)date.getTime()+start*dMSDAY))
					Date endDate=localDate()
					endDate.setTime(Math.round((Long)date.getTime()+end*dMSDAY))
					start=(startDate.year+1900)*372+(startDate.month*31)+(startDate.date-1)
					end=(endDate.year+1900)*372+(endDate.month*31)+(endDate.date-1)
					if((Integer)parts[0].size()>3){
						def games=args.games.findAll{ (it.year*372+(it.month-1)*31+(it.day-1)>=start) && (it.year*372+(it.month-1)*31+(it.day-1)<=end)}
						args=games
						overrideArgs=true
					}else{
						def game=args.games.find{ (it.year*372+(it.month-1)*31+(it.day-1)>=start) && (it.year*372+(it.month-1)*31+(it.day-1)<=end)}
						args=game
						continue
					}
				}
			}
			def idx=0
			String newPart=part
			if((Boolean)part.endsWith(sRB)){
				//array index
				Integer start=part.indexOf(sLB)
				if(start>=0){
					idx=part.substring(start+1,(Integer)part.size()-1)
					newPart=part.substring(0,start)
					if(idx.isInteger()){
						idx=idx.toInteger()
					}else{
						Map var=getVariable(rtD,"$idx".toString())
						idx=(String)var.t!=sERROR ? var.v:idx
					}
				}
				if(!overrideArgs && !!newPart)args=args[newPart]
				if(args instanceof List){
					idx=cast(rtD,idx,sINT)
					args=args[(Integer)idx]
				} else args=args[(String)idx]
				continue
			}
			if(!overrideArgs)args=args[newPart]
		}
		return [(sT):sDYN,(sV):"$args".toString()]
	}catch(all){
		error "Error retrieving JSON data part $part",rtD,-2,all
		//return [(sT):sDYN,(sV):sBLK]
	}
	}
	return [(sT):sDYN,(sV):sBLK]
}

private Map getArgument(Map rtD,String name){
	return getJsonData(rtD,rtD.args,name)
}

private Map getJson(Map rtD,String name){
	return getJsonData(rtD,rtD.json,name)
}

private Map getPlaces(Map rtD,String name){
	return getJsonData(rtD,rtD.settings?.places,name)
}

private Map getResponse(Map rtD,String name){
	return getJsonData(rtD,rtD.response,name)
}

private Map getWeather(Map rtD,String name){
	if(rtD.weather==null){
		Map t0=parent.getWData()
		rtD.weather=t0!=null ? t0:[:]
	}
	return getJsonData(rtD,rtD.weather,name)
}

private Map getNFLDataFeature(String dataFeature){
	Map requestParams=[
		uri: "https://api.webcore.co/nfl/$dataFeature".toString(),
		//query: method==sGET ? data:null,
		query: null,
		timeout:20
	]
	httpGet(requestParams){ response ->
		if(response.status==200 && response.data){
			try{
				return response.data instanceof Map ? response.data : (LinkedHashMap)new JsonSlurper().parseText((String)response.data)
			}catch(ignored){}
		}
		return null
	}
}

private Map getNFL(Map rtD,String name){
	List parts=name.tokenize(sDOT)
	rtD.nfl=rtD.nfl!=null?rtD.nfl: [:]
	if((Integer)parts.size()>0){
		String dataFeature=(String)(((String)parts[0]).tokenize(sLB)[0])
		if(rtD.nfl[dataFeature]==null){
			rtD.nfl[dataFeature]=getNFLDataFeature(dataFeature)
		}
	}
	return getJsonData(rtD,rtD.nfl,name,'NFL')
}

private Map getIncidents(Map rtD,String name){
	return getJsonData(rtD,rtD.incidents,name)
}

@Field volatile static Map<String,Boolean> initGlobalVFLD=[:]
@Field volatile static Map<String,Map<String,Map>> globalVarsVFLD=[:]

void clearGlobalCache(String meth=sNULL){
	String lockTyp='clearGlobalCache '+meth
	String semaName=sTGBL
	String wName=parent.id.toString()
	getTheLock(semaName,lockTyp)
	globalVarsVFLD[wName]=null
	globalVarsVFLD=globalVarsVFLD
	initGlobalVFLD[wName]=false
	initGlobalVFLD=initGlobalVFLD
	releaseTheLock(semaName)
	if(eric())log.debug lockTyp
}

private void loadGlobalCache(){
	String wName=parent.id.toString()
	if(!initGlobalVFLD[wName]){
		String lockTyp='loadGlobalCache'
		String semaName=sTGBL
		getTheLock(semaName,lockTyp)
		globalVarsVFLD[wName]=(Map)parent.listAvailableVariables()
		globalVarsVFLD=globalVarsVFLD
		initGlobalVFLD[wName]=true
		initGlobalVFLD=initGlobalVFLD
		releaseTheLock(semaName)
		if(eric())log.debug lockTyp
	}
}

private Map getVariable(Map rtD,String name){
	Map var=parseVariableName(name)
	String tname=sanitizeVariableName((String)var.name)
//	if(eric())log.debug "getVariable ${name}  ${tname} ${var}"
	if(tname==sNULL)return [(sT):sERROR,(sV):'Invalid empty variable name']
	Map result
	Map err=[(sT):sERROR,(sV):"Variable '$tname' not found".toString()]
	if((Boolean)tname.startsWith(sAT)){
		if((Boolean)tname.startsWith('@@')){
			String vn=tname.substring(2)
			//get a variable
			def hg=getGlobalVar(vn)
			if(hg){
				addInUseGlobalVar(vn)
				String typ=sNULL
				def vl=null
				Map ta=fixHeGType(false,(String)hg.type,hg.value,sNULL)
				ta.each {
					typ=(String)it.key
					vl=it.value
				}
				result=[(sT):typ,(sV):vl]
			} else result=err
			if(eric())log.debug "getVariable hub variable ${vn} returning ${result} to webcore"
		}else{
			loadGlobalCache()
			String wName=parent.id.toString()
			def tresult=globalVarsVFLD[wName][tname]
			if(!(tresult instanceof Map))result=err
			else{
				result=(Map)tresult
				result.v=cast(rtD,result.v,(String)result.t)
			}
		}
	}else{
		if((Boolean)tname.startsWith(sDLR)){
			Integer t0=tname.size()
			if((Boolean)tname.startsWith(sDOLARGS+sDOT) && (t0>6)){ // '$args.'
				result=getArgument(rtD,tname.substring(6))
			}else if((Boolean)tname.startsWith(sDOLARGS+sLB) && (t0>6)){ //'$args['
				result=getArgument(rtD,tname.substring(5))
			}else if((Boolean)tname.startsWith(sDOLRESP+sDOT) && (t0>10)){
				result=getResponse(rtD,tname.substring(10))
			}else if((Boolean)tname.startsWith(sDOLRESP+sLB) && (t0>10)){
				result=getResponse(rtD,tname.substring(9))
			}else if((Boolean)tname.startsWith('$weather.') && (t0>9)){
				result=getWeather(rtD,tname.substring(9))
			}else if((Boolean)tname.startsWith(sDOLJSON+sDOT) && (t0>6)){
				result=getJson(rtD,tname.substring(6))
			}else if((Boolean)tname.startsWith(sDOLJSON+sLB) && (t0>6)){
				result=getJson(rtD,tname.substring(5))
			}else if((Boolean)tname.startsWith('$incidents.') && (t0>11)){
				result=getIncidents(rtD,tname.substring(11))
			}else if((Boolean)tname.startsWith('$incidents[') && (t0>11)){
				result=getIncidents(rtD,tname.substring(10))
			}else if((Boolean)tname.startsWith('$nfl.') && (t0>5)){
				result=getNFL(rtD,tname.substring(5))
			}else if((Boolean)tname.startsWith('$places.') && (t0>8)){
				result=getPlaces(rtD,tname.substring(8))
			}else if((Boolean)tname.startsWith('$places[') && (t0>8)){
				result=getPlaces(rtD,tname.substring(7))
			}else{
				def tresult=rtD.systemVars[tname]
				if(!(tresult instanceof Map))result=err
				else result=(Map)tresult
				if(result!=null && result.d){
					result=[(sT):(String)result.t,(sV): getSystemVariableValue(rtD,tname)]
				}
			}
		}else{
//			if(eric())log.debug "getVariable ${rtD.localVars}"
			def tlocalVar=rtD.localVars[tname]
			if(!(tlocalVar instanceof Map)){
				result=err
			}else{
				result=[(sT):(String)tlocalVar.t,(sV): tlocalVar.v]
				//make a local copy of the list
				if(result.v instanceof List)result.v=[]+(List)result.v
				//make a local copy of the map
				if(result.v instanceof Map)result.v=[:]+(Map)result.v
			}
		}
	}
	if(result!=null && (Boolean)((String)result.t).endsWith(sRB)){
		result.t=((String)result.t).replace('[]',sBLK)
		if(result.v instanceof Map && (String)var.index!=sNULL && (String)var.index!=sBLK){
			Map indirectVar=getVariable(rtD,(String)var.index)
			//indirect variable addressing
			if((String)indirectVar.t!=sERROR){
				def value=(String)indirectVar.t==sDEC ? (Integer)cast(rtD,indirectVar.v,sINT,(String)indirectVar.t):indirectVar.v
				String dataType=(String)indirectVar.t==sDEC ? sINT:(String)indirectVar.t
				var.index=(String)cast(rtD,value,sSTR,dataType)
			}
			result.v=result.v[(String)var.index]
		}
	}else{
		if(result.v instanceof Map){
			String tt0=(String)result.t
			result=(Map)evaluateOperand(rtD,null,(Map)result.v)
			result=(tt0!=null && tt0==(String)result.t) ? result : evaluateExpression(rtD,result,tt0)
		}
	}
	return [(sT):(String)result.t,(sV):result.v]
}

private Map setVariable(Map rtD,String name,value){
	Map var=parseVariableName(name)
	String tname=sanitizeVariableName((String)var.name)
	if(tname==sNULL)return [(sT):sERROR,(sV):'Invalid empty variable name']
	Map err=[(sT):sERROR,(sV):'Invalid variable']
	if((Boolean)tname.startsWith(sAT)){
		if((Boolean)tname.startsWith('@@')){
			String vn=tname.substring(2)
			def hg=getGlobalVar(vn)
			if(hg){ // we know it exists and if it has a value we can know its type (overloaded String, datetime)
				addInUseGlobalVar(vn)
				String typ=sNULL
				String wctyp=sNULL
				def vl=null
				Map tb=fixHeGType(false,(String)hg.type,hg.value,sNULL)
				tb.each {
					wctyp=(String)it.key
				}
				if(wctyp){ // if we know current type
					Map ta=fixHeGType(true,wctyp,value,sNULL)
					Map result=null
					ta.each {
						typ=(String)it.key
						vl=it.value
						if(eric())log.debug "setVariable setting Hub $vn to $vl with type ${typ} wc original type ${wctyp}"
						Boolean a=false
						try {
							a=setGlobalVar(vn,vl)
						} catch(all){
							error 'An error occurred while executing set hub variable',rtD,-2,all
						}
						if(a){
							result=[(sT):wctyp,(sV):value]
							if((Boolean)rtD.eric) myDetail rtD,"setVariable returning ${result} to webcore",-1
						} else err.v='setGlobal failed'
					}
					if(result) return result
				} else err.v='setGlobal unknown wctyp'
			}
		} else{
			loadGlobalCache()
			String lockTyp='setGlobalvar'
			String semaName=sTGBL
			String wName=parent.id.toString()
			getTheLock(semaName,lockTyp)
			def tvariable=globalVarsVFLD[wName][tname]
			if(tvariable instanceof Map){
				Map variable=(Map)globalVarsVFLD[wName][tname]
				variable.v=cast(rtD,value,(String)variable.t)
				globalVarsVFLD=globalVarsVFLD
				Map<String,Map> cache=rtD.gvCache!=null ? (Map<String,Map>)rtD.gvCache:[:]
				cache[tname]=variable
				rtD.gvCache=cache
				releaseTheLock(semaName)
				return variable
			}
			releaseTheLock(semaName)
		}
	}else{
// global vars are removed by setting them to null via webcore dashboard
// local vars are removed by 'clear all data' via HE console
//		if(eric())log.debug "setVariable ${rtD.localVars}"
		def tvariable=rtD.localVars[tname]
//		if(eric())log.debug "setVariable tvariable ${tvariable}"
		if(tvariable instanceof Map){
			Map variable=(Map)rtD.localVars[tname]
//			if(eric())log.debug "setVariable found variable ${variable}"
			if((Boolean)((String)variable.t).endsWith(sRB)){
				//we're dealing with a list
				variable.v=(variable.v instanceof Map)? variable.v:[:]
				if((String)var.index=='*CLEAR'){
					((Map)variable.v).clear()
				}else{
					Map indirectVar=getVariable(rtD,(String)var.index)
					//indirect variable addressing
					if((String)indirectVar.t!=sERROR){
						var.index=(String)cast(rtD,indirectVar.v,sSTR,(String)indirectVar.t)
					}
					variable.v[(String)var.index]=cast(rtD,value,((String)variable.t).replace('[]',sBLK))
				}
			}else{
				def v=(value instanceof GString)? "$value".toString():value
				String t=(String)variable.t
				variable.v=matchCast(rtD,v,t) ? v:cast(rtD,v,t)
			}
			if(!variable.f){
				Map<String,Object> vars
				Map t0=getCachedMaps('setVariable')
				if(t0!=null)vars=(Map<String,Object>)t0.vars
				else{ vars=(Boolean)rtD.pep ? (Map<String,Object>)atomicState.vars:(Map<String,Object>)state.vars }

				rtD.localVars[tname]=variable
				vars[tname]=variable.v

				String myId=(String)rtD.id
				if(t0!=null){
					String semaName=app.id.toString()
					getTheLock(semaName,sV)
					theCacheVFLD[myId].vars=vars
					theCacheVFLD=theCacheVFLD
					releaseTheLock(semaName)
				}
				if((Boolean)rtD.pep)atomicState.vars=vars
				else state.vars=vars
			}
			return variable
		}
	}
	return err
}

@Field static List<String> mL=[]
@Field static List<String> mL1=[]

private static Boolean matchCast(Map rtD, v, String t) {
	if(!mL) {
		mL=[sSTR,sENUM,sTEXT,sLONG,sBOOLN,sINT,sDEC]
		mL1=[sSTR,sENUM,sTEXT]
	}
	Boolean match= v!=null && t in mL && (
			(t in mL1 && v instanceof String)||
			(t==sLONG && v instanceof Long)||
			(t==sINT && v instanceof Integer)||
			(t==sBOOLN && v instanceof Boolean)||
			(t==sDEC && v instanceof Double) )
	return match
}

Map setLocalVariable(String name,value){ // called by parent (IDE) to set a variable
	String tname=sanitizeVariableName(name)
	if(tname==sNULL || (Boolean)tname.startsWith(sAT))return [:]
	Map<String,Object> vars=(Map<String,Object>)atomicState.vars
	vars=vars!=null ? vars:[:]
	vars[tname]=value
	atomicState.vars=vars
	clearMyCache('setLocalVariable')
	return vars
}

/** EXPRESSION FUNCTIONS							**/

Map proxyEvaluateExpression(LinkedHashMap mrtD,Map expression,String dataType=sNULL){
	LinkedHashMap rtD=getRunTimeData(mrtD)
	resetRandomValues(rtD)
	try{
		Map result=evaluateExpression(rtD,expression,dataType)
		if((String)result.t==sDEV && result.a!=null){
			Map attr=Attributes()[(String)result.a]
			result=evaluateExpression(rtD,result,attr!=null && attr.t!=null ? (String)attr.t:sSTR)
		}
		rtD=null
		return result
	}catch(all){
		error 'An error occurred while executing the expression',rtD,-2,all
	}
	return [(sT):sERROR,(sV):'expression error']
}

private static Map simplifyExpression(Map express){
	Map expression=express
	while ((String)expression.t==sEXPR && expression.i && (Integer)((List)expression.i).size()==1) expression=(Map)((List)expression.i)[0]
	return expression
}

@Field static List<String> LT0=[]
@Field static List<String> LS=[]
@Field static List<String> L1opt=[]
@Field static List<String> lPLSMIN=[]
@Field static List<String> LT1=[]
@Field static List<String> LN=[]
@Field static List<String> LD=[]
@Field static List<String> LT2=[]
@Field static List<String> tL2=[]
@Field static List<String> tL4=[]
@Field static List<String> tL6=[]
@Field static List<String> tL7=[]
@Field static List<String> tL8=[]
@Field static List<String> tL9=[]
@Field static List<String> tL10=[]
@Field static List<String> tL11=[]
@Field static List<String> tL12=[]
@Field static List<String> tL13=[]
@Field static List<String> tL14=[]
@Field static List<String> pn1=[]
@Field static List<String> pn2=[]
@Field static List<String> pn3=[]
@Field static List<String> pn4=[]

private static List<String> fill_TIM(){ return [sDTIME,sTIME,sDATE] }

private Map evaluateExpression(Map rtD,Map express,String dataType=sNULL){
	//if dealing with an expression that has multiple items, let's evaluate each item one by one
	//let's evaluate this expression
	if(!LT0){
		LT0=[sSTR,sTEXT]
		LS=[sSTR,sENUM]
		L1opt=[sPLUS,sMINUS,sPWR,sAMP,sBOR,sBXOR,sBNOT,sBNAND,sBNOR,sBNXOR,sLTH,sGTH,sLTHE,sGTHE,sEQ,sNEQ,sNEQA,sSBL,sSBR,sNEG,sDNEG,sQM]
		lPLSMIN=[sPLUS,sMINUS]
		if(!LT1) { LT1=fill_TIM() }
		LN=[sNUMBER,sINT,sLONG]
		LD=[sDEC,sFLOAT]
		LT2=[sDEV,sVARIABLE]
		tL2=[sNEG,sDNEG,sBNOT]
		tL4=[sMULP,sDIV,sMOD1,sMOD]
		tL6=[sSBL,sSBR]
		tL7=[sGTH,sLTH,sGTHE,sLTHE]
		tL8=[sEQ,sNEQ,sNEQA]
		tL9=[sAMP,sBNAND]
		tL10=[sBXOR,sBNXOR]
		tL11=[sBOR,sBNOR]
		tL12=[sLAND,sLNAND]
		tL13=[sLXOR,sLNXOR]
		tL14=[sLOR,sLNOR]
		pn1=[sMULP,sDIV,sMINUS,sPWR] // number fixes
		pn2=[sMOD1,sMOD,sAMP,sBOR,sBXOR,sBNAND,sBNOR,sBNXOR,sSBL,sSBR] // int fixes
		pn3=[sLAND,sLOR,sLXOR,sLNAND,sLNOR,sLNXOR,sNEG,sDNEG] // bool fixes
		pn4=[sEQ,sNEQ,sLTH,sGTH,sLTHE,sGTHE,sNEQA]
	}
	if(!express)return [(sT):sERROR,(sV):'Null expression']
	//not sure what it was needed for - need to comment more
	//if(express && express.v instanceof Map)return evaluateExpression(rtD,express.v,express.t)
	Long time=now()
	Map expression=simplifyExpression(express)
	String mySt=sNULL
	if((Boolean)rtD.eric){
		mySt="evaluateExpression $expression  dataType: $dataType".toString()
		myDetail rtD,mySt,1
	}
	Map result=expression
	String exprType=(String)expression.t
	def exprV=expression.v
	//noinspection GroovyFallthrough
	switch(exprType){
		case sINT:
		case sLONG:
		case sDEC:
			result=[(sT):exprType,(sV):exprV]
			break
		case sTIME:
		case sDTIME:
			String st0="$exprV"
			if(st0.isNumber()){
				Long l1=st0.toLong()
				if( (l1>=lMSDAY && exprType==sDTIME) || (l1<lMSDAY && exprType==sTIME) ){
					result=[(sT):exprType,(sV):l1]
					break
				}
			}
		case sINT32:
		case sINT64:
		case sDATE:
			result=[(sT):exprType,(sV):cast(rtD,exprV,exprType,dataType)]
			break
		case sBOOL:
		case sBOOLN:
			if(exprV instanceof Boolean){
				result=[(sT):sBOOLN,(sV):(Boolean)exprV]
				break
			}
			Boolean t1=cast(rtD,exprV,sBOOLN,dataType)
			result=[(sT):sBOOLN,(sV):t1]
			break
		case sSTR:
		case sENUM:
		case sERROR:
		case sPHONE:
		case sURI:
		case sTEXT:
			if(exprV instanceof String){
				result=[(sT):sSTR,(sV):(String)exprV]
				break
			}
			result=[(sT):sSTR,(sV):(String)cast(rtD,exprV,sSTR,dataType)]
			break
		case sNUMBER:
		case sFLOAT:
		case sDBL:
			if(exprV instanceof Double){
				result=[(sT):sDEC,(sV):(Double)exprV]
				break
			}
			result=[(sT):sDEC,(sV):(Double)cast(rtD,exprV,sDEC,dataType)]
			break
		case sDURATION:
			String t0=(String)expression.vt
			if(t0==null && exprV instanceof Long){ result=[(sT):sLONG,(sV):(Long)exprV] }
			else result=[(sT):sLONG,(sV):(Long)cast(rtD,exprV,t0!=sNULL ? t0:sLONG)]
			break
		case sVARIABLE:
			//get variable {n:name,t:type,v:value}
			result=getVariable(rtD,(String)expression.x+((String)expression.xi!=sNULL ? sLB+(String)expression.xi+sRB:sBLK))
			break
		case sDEV:
			if(exprV instanceof List){
				//already parsed
				result=expression
			}else{
				List deviceIds=(expression.id instanceof List)? (List)expression.id:(expression.id ? [expression.id]:[])
				Boolean err=false
				if((Integer)deviceIds.size()==0){
					//get variable {n:name,t:type,v:value}
					Map var=getVariable(rtD,(String)expression.x)
					if((String)var.t!=sERROR){
						if((String)var.t==sDEV){
							deviceIds=(List)var.v
						}else{
							def device=getDevice(rtD,(String)var.v)
							if(device!=null)deviceIds=[hashId(device.id)]
						}
					} else {
						// [(sT):sERROR,v:'Invalid variable']
						err=true
						result=var
					}
				}
				if(!err) result=[(sT):sDEV,(sV):deviceIds,(sA):(String)expression.a]
			}
			break
		case sOPERAND:
			result=[(sT):sSTR,(sV):(String)cast(rtD,exprV,sSTR)]
			break
		case sFUNC:
			String fn='func_'+(String)expression.n
			//in a function, we look for device parameters,they may be lists - we need to reformat all parameters to send them to the function properly
			String myStr=sNULL
			try{
				List params=[]
				List<Map> t0=(List<Map>)expression.i
				if(t0 && t0.size()!=0){
					Map param
					Boolean a
					for(Map i in t0){
						param=simplifyExpression(i)
						if((String)param.t in LT2){ // sDEV or sVARIABLE
							param=evaluateExpression(rtD,param)
							//if multiple devices, spread into multiple params
							Integer sz=param.v instanceof List ? (Integer)((List)param.v).size():1
							switch(sz){
								case 0: break
								case 1: a=params.push(param); break
								default:
									for(v in (List)param.v){
										a=params.push([(sT):(String)param.t,(sA):(String)param.a,(sV):[v]])
									}
							}
						}else a=params.push(param)
					}
				}
				if((Boolean)rtD.eric){
					myStr='calling function '+fn
					myDetail rtD,myStr,1
				}
				result=(Map)"$fn"(rtD,params)
			}catch(all){
				error "Error executing $fn: ",rtD,-2,all
				result=[(sT):sERROR,(sV):"${all}"]
			}
			if((Boolean)rtD.eric) myDetail rtD,myStr+sSPC+"${result}".toString(),-1
			break
		case sEXPR:
			//if we have a single item, we simply traverse the expression
			List<Map> items=[]
			Integer operand=-1
			Integer lastOperand=-1
			Boolean a
			for(Map item in (List<Map>)expression.i){
				if((String)item.t==sOPER){
					String ito=(String)item.o
					if(operand<0){
						if(ito in L1opt){
							a=items.push([(sT):sINT,(sV):0,(sO):ito])
						} else switch(ito){
							case sCOLON:
								if(lastOperand>=0){
									//groovy-style support for(object ?: value)
									a=items.push(items[lastOperand]+[(sO):ito])
								}else{
									a=items.push([(sT):sINT,(sV):0,(sO):ito])
								}
								break
							case sMULP:
							case sDIV:
								a=items.push([(sT):sINT,(sV):1,(sO):ito])
								break
							case sLAND:
							case sLNAND:
								a=items.push([(sT):sBOOLN,(sV):true,(sO):ito])
								break
							case sLOR:
							case sLNOR:
							case sLXOR:
							case sLNXOR:
								a=items.push([(sT):sBOOLN,(sV):false,(sO):ito])
								break
						}
					}else{
						items[operand].o=ito
						operand=-1
					}
				}else{
					Map tmap= [:]+evaluateExpression(rtD,item)
					a=items.push(tmap)
					operand=(Integer)items.size()-1
					lastOperand=operand
				}
			} // end for
			//clean up operators, ensure there's one for each
			Integer idx=0
			Integer itmSz=(Integer)items.size()-1
			for(Map item in items){
				if(!item.o){
					switch((String)item.t){
						case sINT:
						case sFLOAT:
						case sDBL:
						case sDEC:
						case sNUMBER:
							String nextType=sSTR
							if(idx<itmSz)nextType=(String)items[idx+1].t
							item.o= nextType in LT0 ? sPLUS:sMULP // Strings
							break
						default:
							item.o=sPLUS
							break
					}
				}
				idx++
			}
			//do the job
			idx=0
			itmSz=(Integer)items.size()
			def aa
			while (itmSz>1){
				//ternary
				if(itmSz==3 && (String)items[0].o==sQM && (String)items[1].o==sCOLON){
					//we have a ternary operator
					if((Boolean)evaluateExpression(rtD,(Map)items[0],sBOOLN).v){
						items=[items[1]]
					}else{
						items=[items[2]]
					}
					items[0].o=sNULL
					break
				}
				//order of operations
				idx=0
				//#2	!   !!   ~   -	Logical negation, logical double-negation, bitwise NOT, and numeric negation unary operators
				for(Map item in items){
					String t0=(String)item.o
					if(t0 in tL2 || (item.t==null && t0==sMINUS))break
					idx++
				}
				//#3	**	Exponent operator
				if(idx>=itmSz){
					//we then look for power **
					idx=0
					for(Map item in items){
						if((String)item.o==sPWR)break
						idx++
					}
				}
				//#4	*   /   \   % MOD	Multiplication, division, modulo
				if(idx>=itmSz){
					//we then look for * or /
					idx=0
					for(Map item in items){
						if((String)item.o in tL4)break
						idx++
					}
				}
				//#5	+   -	Addition and subtraction
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in lPLSMIN)break
						idx++
					}
				}
				//#6	<<   >>	Shift left and shift right operators
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL6)break
						idx++
					}
				}
				//#7	<  <= >  >=	Comparisons: less than, less than or equal to,greater than, greater than or equal to
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL7)break
						idx++
					}
				}
				//#8	==   !=	Comparisons: equal and not equal
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL8)break
						idx++
					}
				}
				//#9	&	Bitwise AND
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL9)break
						idx++
					}
				}
				//#10	^	Bitwise exclusive OR (XOR)
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL10)break
						idx++
					}
				}
				//#11	|	Bitwise inclusive (normal)OR
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL11)break
						idx++
					}
				}
				//#12	&&	Logical AND
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL12)break
						idx++
					}
				}
				//#13	^^	Logical XOR
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL13)break
						idx++
					}
				}
				//#14	||	Logical OR
				if(idx>=itmSz){
					idx=0
					for(Map item in items){
						if((String)item.o in tL14)break
						idx++
					}
				}
				//if none selected get the first one
				if(idx>=itmSz-1)idx=0

				String o=(String)items[idx].o

				String a1=(String)items[idx].a
				String t1=(String)items[idx].t
				def v1=items[idx].v

				Integer idxPlus=idx+1
				String a2=(String)items[idxPlus].a
				String t2=(String)items[idxPlus].t
				def v2=items[idxPlus].v

				def v=null
				String t=t1

				//fix-ups
				if(t1==sDEV && a1!=sNULL && a1.length()>0){
					Map attr=Attributes()[a1]
					t1=attr!=null ? (String)attr.t:sSTR
				}
				if(t2==sDEV && a2!=sNULL && a2.length()>0){
					Map attr=Attributes()[a2]
					t2=attr!=null ? (String)attr.t:sSTR
				}
				if(t1==sDEV && t2==sDEV && (o in lPLSMIN)){
					List lv1=(v1 instanceof List)? v1:[v1]
					List lv2=(v2 instanceof List)? v2:[v2]
					v= o==sPLUS ? lv1+lv2 : lv1-lv2
					//set the results
					items[idxPlus].t=sDEV
					items[idxPlus].v=v
				}else{
					Boolean t1d= (t1 in LT1)
					Boolean t2d= (t2 in LT1)
					Boolean t1i= (t1 in LN)
					Boolean t2i= (t2 in LN)
					Boolean t1f= (t1 in LD)
					Boolean t2f= (t2 in LD)
					Boolean t1n=t1i || t1f
					Boolean t2n=t2i || t2f
					//warn "Precalc ($t1) $v1 $o ($t2) $v2 >>> t1d=$t1d,t2d=$t2d,t1n=$t1n, t2n=$t2n",rtD
					if((o in lPLSMIN) && (t1d || t2d) && (t1d || t1n) && (t2n || t2d)){
						//if dealing with date +/- date/numeric then
						if(t1n){
							t=t2
						}else if(t2n){
							t=t1
						}else{
							t= t1==sDATE && t2==sDATE ? sDATE:(t1==sTIME && t2==sTIME ? sTIME:sDTIME)
						}
					}else{
						if(o in lPLSMIN){
							//devices and others play nice
							if(t1==sDEV){
								t=t2
								t1=t2
							}else if(t2==sDEV){
								t=t1
								t2=t1
							}
						}
						//integer with decimal gives decimal, also *,/ require decimals
						if(o in pn1){
							t= t1i && t2i ? (t1==sLONG || t2==sLONG ? sLONG:sINT) : sDEC
							t1=t
							t2=t
						}
						if(o in pn2){
							t= t1==sLONG || t2==sLONG ? sLONG:sINT
							t1=t
							t2=t
						}
						if(o in pn3){
							t=sBOOLN
							t1=t
							t2=t
						}
						if(o==sPLUS && (t1 in LT0 || t2 in LT0)){
							t=sSTR
							t1=t
							t2=t
						}
						if(t1n && t2n){
							t= t1i && t2i ? ( t1==sLONG || t2==sLONG ? sLONG:sINT):sDEC
							t1=t
							t2=t
						}
						if(o in pn4){
							if(t1==sDEV)t1=sSTR
							if(t2==sDEV)t2=sSTR
							t1=t1==sSTR ? t2:t1
							t2=t2==sSTR ? t1:t2
							t=sBOOLN
						}
					}
					//v1=evaluateExpression(rtD,(Map)items[idx],t1).v
					if((String)items[idx].t==t1) v1=items[idx].v
					else v1=evaluateExpression(rtD,(Map)items[idx],t1).v

					//v2=evaluateExpression(rtD,(Map)items[idxPlus],t2).v
					if((String)items[idxPlus].t==t2) v2=items[idxPlus].v
					else v2=evaluateExpression(rtD,(Map)items[idxPlus],t2).v

					v1=v1==sSNULL ? null:v1
					v2=v2==sSNULL ? null:v2
					//noinspection GroovyFallthrough
					switch(o){
						case sQM:
						case sCOLON:
							error "Invalid ternary operator. Ternary operator's syntax is (condition ? trueValue:falseValue ). Please check your syntax.",rtD
							v=sBLK
							break
						case sMINUS:
							v=v1 - v2
							break
						case sMULP:
							v=v1 * v2
							break
						case sDIV:
							v=(v2!=0 ? v1/v2 : 0)
							break
						case sMOD1:
							v=(Integer)Math.floor(v2!=0 ? v1/v2 : 0)
							break
						case sMOD:
							v=(Integer)(v2!=0 ? v1%v2 : 0)
							break
						case sPWR:
							v=v1 ** v2
							break
						case sAMP:
							v=v1 & v2
							break
						case sBOR:
							v=v1 | v2
							break
						case sBXOR:
							v=v1 ^ v2
							break
						case sBNAND:
							v=~(v1 & v2)
							break
						case sBNOR:
							v=~(v1 | v2)
							break
						case sBNXOR:
							v=~(v1 ^ v2)
							break
						case sBNOT:
							v=~v2
							break
						case sSBL:
							v=v1 << v2
							break
						case sSBR:
							v=v1 >> v2
							break
						case sLAND:
							v=!!v1 && !!v2
							break
						case sLOR:
							v=!!v1 || !!v2
							break
						case sLXOR:
							v=!v1!=!v2
							break
						case sLNAND:
							v=!(!!v1 && !!v2)
							break
						case sLNOR:
							v=!(!!v1 || !!v2)
							break
						case sLNXOR:
							v=!(!v1!=!v2)
							break
						case sEQ:
							v=v1==v2
							break
						case sNEQ:
						case sNEQA:
							v=v1!=v2
							break
						case sLTH:
							v=v1<v2
							break
						case sGTH:
							v=v1>v2
							break
						case sLTHE:
							v=v1<=v2
							break
						case sGTHE:
							v=v1>=v2
							break
						case sNEG:
							v=!v2
							break
						case sDNEG:
							v=!!v2
							break
						case sPLUS:
						default:
							v=t==sSTR ? "$v1$v2":v1+v2
							break
					} //end switch

					if((Integer)rtD.logging>2)debug "Calculating ($t1)$v1 $o ($t2)$v2 >> ($t)$v",rtD

					//set the results
					items[idxPlus].t=t
					v=(v instanceof GString)? "$v".toString():v
					items[idxPlus].v=matchCast(rtD,v,t) ? v : cast(rtD,v,t)
				} // end else

				aa=items.remove(idx)

				itmSz=(Integer)items.size()
			} //end while
			result=items[0] ? ((String)items[0].t==sDEV ? (Map)items[0] : evaluateExpression(rtD,(Map)items[0])) : [(sT):sDYN,(sV):null]
			break
	} //end switch

	String ra=result.a
	//when dealing with devices,they need to be "converted" unless the request is to return devices
	if(dataType && dataType!=sDEV && (String)result.t==sDEV){
		List atL= (result.v instanceof List)?(List)result.v:[result.v]
		switch((Integer)atL.size()){
			case 0: result=[(sT):sERROR,(sV):'Empty device list']; break
			case 1: result=getDeviceAttribute(rtD,(String)atL[0],ra,result.i); break
			default:result=[(sT):sSTR,(sV):buildDeviceAttributeList(rtD,atL,ra)]; break
		}
	}
	//return the value, either directly or via cast, if certain data type is requested
	if(dataType){
		String t0=(String)result.t
		def t1=result.v
		if(dataType!=t0){
			Boolean match= (dataType in LS && t0 in LS && t1 instanceof String)
			if(!match)t1=cast(rtD,t1,dataType,t0)
		}
		result=[(sT):dataType,(sV): t1] + (ra ? [(sA):ra]:[:]) + (result.i ? [(sI):result.i]:[:])
	}
	result.d=elapseT(time)
	if((Boolean)rtD.eric) myDetail rtD,mySt+" result:$result".toString(),-1
	return result
}

private static String buildList(List list,String suffix=sAND){
	if(!list)return sBLK
	Integer cnt=1
	String result=sBLK
	Integer t0=(Integer)list.size()
	Integer t1=t0-1
	for(item in list){
		result += item.toString()+(cnt<t0 ? (cnt==t1 ? sSPC+suffix+sSPC:sCOMMA+sSPC):sBLK)
		cnt++
	}
	return result
}

private String buildDeviceList(Map rtD,devices,String suffix=sAND){
	if(!devices)return sBLK
	List nlist=(devices instanceof List)? devices:[devices]
	List list=[]
	Boolean a
	def dev
	for(String device in nlist){
		dev=getDevice(rtD,device)
		if(dev!=null)a=list.push(dev)
	}
	return buildList(list,suffix)
}

private String buildDeviceAttributeList(Map rtD,List devices,String attribute,String suffix=sAND){
	if(!devices)return sBLK
	List list=[]
	Boolean a
	def value
	for(String device in devices){
		value=getDeviceAttribute(rtD,device,attribute).v
		a=list.push(value)
	}
	return buildList(list,suffix)
}

private static Boolean checkParams(Map rtD,List params,Integer minParams){
	if(params==null || !(params instanceof List) || params.size()<minParams) return false
	return true
}

private static Map rtnErr(String msg){
	return [(sT):sERROR,(sV):sEXPECTING+msg]
}

/** dewPoint returns the calculated dew point temperature			**/
/** Usage: dewPoint(temperature,relativeHumidity[, scale])			**/
private Map func_dewpoint(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('dewPoint(temperature,relativeHumidity[, scale])')
	Double t=(Double)evaluateExpression(rtD,params[0],sDEC).v
	Double rh=(Double)evaluateExpression(rtD,params[1],sDEC).v
	//if no temperature scale is provided,we assume the location's temperature scale
	Boolean fahrenheit=((String)cast(rtD,params.size()>2 ? (String)evaluateExpression(rtD,params[2]).v:(String)location.temperatureScale,sSTR)).toUpperCase()=='F'
	if(fahrenheit){
		t=(t-32.0D)*5.0D/9.0D
	}
	//convert rh to percentage
	if((rh>dZERO) && (rh<dONE)){
		rh=rh*100.0D
	}
	Double b=(Math.log(rh/100.0D)+((17.27D*t)/(237.3D+t)))/17.27D
	Double result=(237.3D*b)/(dONE-b)
	if(fahrenheit){
		result=result*9.0D/5.0D+32.0D
	}
	return [(sT):sDEC,(sV):result]
}

/** celsius converts temperature from Fahrenheit to Celsius			**/
/** Usage: celsius(temperature)							**/
private Map func_celsius(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('celsius(temperature)')
	Double t=(Double)evaluateExpression(rtD,params[0],sDEC).v
	return [(sT):sDEC,(sV):(Double)((t-32.0D)*5.0D/9.0D)]
}

/** fahrenheit converts temperature from Celsius to Fahrenheit			**/
/** Usage: fahrenheit(temperature)						**/
private Map func_fahrenheit(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('fahrenheit(temperature)')
	Double t=(Double)evaluateExpression(rtD,params[0],sDEC).v
	return [(sT):sDEC,(sV):(Double)(t*9.0D/5.0D+32.0D)]
}

/** fahrenheit converts temperature between Celsius and Fahrenheit if the	**/
/** units differ from location.temperatureScale					**/
/** Usage: convertTemperatureIfNeeded(celsiusTemperature,'C')			**/
private Map func_converttemperatureifneeded(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('convertTemperatureIfNeeded(temperature,unit)')
	String u=((String)evaluateExpression(rtD,params[1],sSTR).v).toUpperCase()
	switch((String)location.temperatureScale){
		case u: // matches,return value
			Double t=(Double)evaluateExpression(rtD,params[0],sDEC).v
			return [(sT):sDEC,(sV):t]
		case 'F': return func_celsius(rtD,[params[0]])
		case 'C': return func_fahrenheit(rtD,[params[0]])
	}
	return [:]
}

/** integer converts a decimal to integer value			**/
/** Usage: integer(decimal or string)				**/
private Map func_integer(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('integer(decimal or string)')
	return [(sT):sINT,(sV):(Integer)evaluateExpression(rtD,params[0],sINT).v]
}
private Map func_int(Map rtD,List<Map> params){ return func_integer(rtD,params)}

/** decimal/float converts an integer value to it's decimal value		**/
/** Usage: decimal(integer or string)						**/
private Map func_decimal(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('decimal(integer or string)')
	return [(sT):sDEC,(sV):(Double)evaluateExpression(rtD,params[0],sDEC).v]
}
private Map func_float(Map rtD,List<Map> params){ return func_decimal(rtD,params)}
private Map func_number(Map rtD,List<Map> params){ return func_decimal(rtD,params)}

/** string converts an value to it's string value				**/
/** Usage: string(anything)							**/
private Map func_string(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('string(anything)')
	String result=sBLK
	for(Map param in params){
		result += (String)evaluateExpression(rtD,param,sSTR).v
	}
	return [(sT):sSTR,(sV):result]
}
private Map func_concat(Map rtD,List<Map> params){ return func_string(rtD,params)}
private Map func_text(Map rtD,List<Map> params){ return func_string(rtD,params)}

/** Boolean converts a value to it's Boolean value				**/
/** Usage: boolean(anything)							**/
private Map func_boolean(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('boolean(anything)')
	return [(sT):sBOOLN,(sV):(Boolean)evaluateExpression(rtD,params[0],sBOOLN).v]
}
private Map func_bool(Map rtD,List<Map> params){ return func_boolean(rtD,params)}

/** sqr converts a decimal to square decimal value			**/
/** Usage: sqr(integer or decimal or string)				**/
private Map func_sqr(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('sqr(integer or decimal or string)')
	return [(sT):sDEC,(sV):(Double)evaluateExpression(rtD,params[0],sDEC).v**2]
}

/** sqrt converts a decimal to square root decimal value		**/
/** Usage: sqrt(integer or decimal or string)				**/
private Map func_sqrt(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('sqrt(integer or decimal or string)')
	return [(sT):sDEC,(sV):Math.sqrt((Double)evaluateExpression(rtD,params[0],sDEC).v)]
}

/** power converts a decimal to power decimal value			**/
/** Usage: power(integer or decimal or string, power)			**/
private Map func_power(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('sqrt(integer or decimal or string, power)')
	return [(sT):sDEC,(sV):(Double)evaluateExpression(rtD,params[0],sDEC).v ** (Double)evaluateExpression(rtD,params[1],sDEC).v]
}

/** round converts a decimal to rounded value			**/
/** Usage: round(decimal or string[, precision])		**/
private Map func_round(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('round(decimal or string[, precision])')
	Integer precision= params.size()>1 ? (Integer)evaluateExpression(rtD,params[1],sINT).v:0
	return [(sT):sDEC,(sV):Math.round((Double)evaluateExpression(rtD,params[0],sDEC).v * (10 ** precision))/(10 ** precision)]
}

/** floor converts a decimal to closest lower integer value		**/
/** Usage: floor(decimal or string)					**/
private Map func_floor(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('floor(decimal or string)')
	return [(sT):sINT,(sV):(Integer)cast(rtD,Math.floor((Double)evaluateExpression(rtD,params[0],sDEC).v),sINT)]
}

/** ceiling converts a decimal to closest higher integer value	**/
/** Usage: ceiling(decimal or string)						**/
private Map func_ceiling(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('ceiling(decimal or string)')
	return [(sT):sINT,(sV):(Integer)cast(rtD,Math.ceil((Double)evaluateExpression(rtD,params[0],sDEC).v),sINT)]
}
private Map func_ceil(Map rtD,List<Map> params){ return func_ceiling(rtD,params)}


/** sprintf converts formats a series of values into a string			**/
/** Usage: sprintf(format, arguments)						**/
private Map func_sprintf(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('sprintf(format, arguments)')
	String format=sNULL
	List args=[]
	Boolean a
	try{
		format=(String)evaluateExpression(rtD,params[0],sSTR).v
		Integer sz=params.size()
		for(Integer x=1; x<sz; x++){
			a=args.push(evaluateExpression(rtD,params[x]).v)
		}
		return [(sT):sSTR,(sV):sprintf(format,args)]
	}catch(all){
		return rtnErr("$all $format $args".toString())
	}
}
private Map func_format(Map rtD,List<Map> params){ return func_sprintf(rtD,params)}

/** left returns a substring of a value					**/
/** Usage: left(string, count)						**/
private Map func_left(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('left(string, count)')
	String value=(String)evaluateExpression(rtD,params[0],sSTR).v
	Integer count=(Integer)evaluateExpression(rtD,params[1],sINT).v
	Integer sz=value.size()
	if(count>sz)count=sz
	return [(sT):sSTR,(sV):value.substring(0,count)]
}

/** right returns a substring of a value				**/
/** Usage: right(string, count)						**/
private Map func_right(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('right(string, count)')
	String value=(String)evaluateExpression(rtD,params[0],sSTR).v
	Integer count=(Integer)evaluateExpression(rtD,params[1],sINT).v
	Integer sz=value.size()
	if(count>sz)count=sz
	return [(sT):sSTR,(sV):value.substring(sz-count,sz)]
}

/** strlen returns the length of a string value				**/
/** Usage: strlen(string)						**/
private Map func_strlen(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('strlen(string)')
	String value=(String)evaluateExpression(rtD,params[0],sSTR).v
	return [(sT):sINT,(sV):value.size()]
}
private Map func_length(Map rtD,List<Map> params){ return func_strlen(rtD,params)}

/** coalesce returns the first non-empty parameter				**/
/** Usage: coalesce(value1[, value2[, ..., valueN]])				**/
private Map func_coalesce(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('coalesce(value1[, value2[, ..., valueN]])')
	Integer sz=params.size()
	for(i=0; i<sz; i++){
		Map value=evaluateExpression(rtD,params[i])
		if(!(value.v==null || (value.v instanceof List ? value.v==[null] || value.v==[] || value.v==[sSNULL] : false) || (String)value.t==sERROR || value.v==sSNULL || (String)cast(rtD,value.v,sSTR)==sBLK)){
			return value
		}
	}
	return [(sT):sDYN,(sV):null]
}

/** trim removes leading and trailing spaces from a string			**/
/** Usage: trim(value)								**/
private Map func_trim(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('trim(value)')
	String t0=(String)evaluateExpression(rtD,params[0],sSTR).v
	String value=(String)t0.trim()
	return [(sT):sSTR,(sV):value]
}

/** trimleft removes leading spaces from a string				**/
/** Usage: trimLeft(value)							**/
private Map func_trimleft(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('trimLeft(value)')
	String t0=(String)evaluateExpression(rtD,params[0],sSTR).v
	String value=(String)t0.replaceAll('^\\s+',sBLK)
	return [(sT):sSTR,(sV):value]
}
private Map func_ltrim(Map rtD,List<Map> params){ return func_trimleft(rtD,params)}

/** trimright removes trailing spaces from a string				**/
/** Usage: trimRight(value)							**/
private Map func_trimright(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('trimRight(value)')
	String t0=(String)evaluateExpression(rtD,params[0],sSTR).v
	String value=(String)t0.replaceAll('\\s+$',sBLK)
	return [(sT):sSTR,(sV):value]
}
private Map func_rtrim(Map rtD,List<Map> params){ return func_trimright(rtD,params)}

/** substring returns a substring of a value					**/
/** Usage: substring(string, start, count)					**/
private Map func_substring(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('substring(string, start, count)')
	String value=(String)evaluateExpression(rtD,params[0],sSTR).v
	Integer start=(Integer)evaluateExpression(rtD,params[1],sINT).v
	Integer count=params.size()>2 ? (Integer)evaluateExpression(rtD,params[2],sINT).v:null
	//def end=null
	String result=sBLK
	Integer t0=value.size()
	if(start<t0 && start>-t0){
		if(count!=null){
			if(count<0){
				//reverse
				start=start<0 ? -start:t0-start
				count=-count
				value=value.reverse()
			}
			if(start>=0){
				if(count>t0-start)count= t0-start
			}else{
				if(count>-start)count=-start
			}
		}
		start=start>=0 ? start : t0+start
		if(count>t0-start)count=t0-start
		result=(count==null) ? value.substring(start) : value.substring(start,start+count)
	}
	return [(sT):sSTR,(sV):result]
}
private Map func_substr(Map rtD,List<Map> params){ return func_substring(rtD,params)}
private Map func_mid(Map rtD,List<Map> params){ return func_substring(rtD,params)}

/** replace replaces a search text inside of a value				**/
/** Usage: replace(string, search, replace[, [..],search, replace])		**/
private Map func_replace(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,3) || sz%2 != 1) return rtnErr('replace(string, search, replace[, [..],search, replace])')
	String value=(String)evaluateExpression(rtD,params[0],sSTR).v
	Integer cnt=Math.floor((sz-1)/2).toInteger()
	for(Integer i=0; i<cnt; i++){
		String search=(String)evaluateExpression(rtD,params[i*2+1],sSTR).v
		String replace=(String)evaluateExpression(rtD,params[i*2+2],sSTR).v
		sz=search.size()
		if((sz>2)&& (Boolean)search.startsWith(sDIV)&& (Boolean)search.endsWith(sDIV)){
			def ssearch= ~search.substring(1,sz-1)
			value=value.replaceAll(ssearch,replace)
		}else{
			value=value.replace(search,replace)
		}
	}
	return [(sT):sSTR,(sV):value]
}

/** rangeValue returns the matching value in a range					**/
/** Usage: rangeValue(input, defaultValue,point1, value1[, [..],pointN, valueN])	**/
private Map func_rangevalue(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,2) || sz%2!=0) return rtnErr('rangeValue(input, defaultValue,point1, value1[, [..],pointN, valueN])')
	Double input=(Double)evaluateExpression(rtD,params[0],sDEC).v
	Map value=params[1]
	Integer cnt=Math.floor((sz-2)/2).toInteger()
	for(Integer i=0; i<cnt; i++){
		Double point=(Double)evaluateExpression(rtD,params[i*2 +2],sDEC).v
		if(input>=point)value=params[i*2 +3]
	}
	return value
}

/** rainbowValue returns the matching value in a range				**/
/** Usage: rainbowValue(input, minInput, minColor,maxInput, maxColor)		**/
private Map func_rainbowvalue(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,5)) return rtnErr('rainbowValue(input, minColor,minValue,maxInput, maxColor)')
	Integer input=(Integer)evaluateExpression(rtD,params[0],sINT).v
	Integer minInput=(Integer)evaluateExpression(rtD,params[1],sINT).v
	Map minColor=getColor(rtD,(String)evaluateExpression(rtD,params[2],sSTR).v)
	Integer maxInput=(Integer)evaluateExpression(rtD,params[3],sINT).v
	Map maxColor=getColor(rtD,(String)evaluateExpression(rtD,params[4],sSTR).v)
	if(minInput>maxInput){
		Integer x=minInput
		minInput=maxInput
		maxInput=x
		Map x1=minColor
		minColor=maxColor
		maxColor=x1
	}
	input=(input<minInput ? minInput:(input>maxInput ? maxInput:input))
	if((input==minInput)|| (minInput==maxInput))return [(sT):sSTR,(sV):(String)minColor.hex]
	if(input==maxInput)return [(sT):sSTR,(sV):(String)maxColor.hex]
	List start=hexToHsl((String)minColor.hex)
	List end=hexToHsl((String)maxColor.hex)
	Double alpha=dONE*(input-minInput)/(maxInput-minInput+1)
	Integer h=Math.round(start[0]-((input-minInput)*(start[0]-end[0])/(maxInput-minInput))).toInteger()
	Integer s=Math.round(start[1]+(end[1]-start[1])*alpha).toInteger()
	Integer l=Math.round(start[2]+(end[2]-start[2])*alpha).toInteger()
	return [(sT):sSTR,(sV):hslToHex(h,s,l)]
}

/** indexOf finds the first occurrence of a substring in a string		**/
/** Usage: indexOf(stringOrDeviceOrList, substringOrItem)			**/
private Map func_indexof(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,2) || ((String)params[0].t!=sDEV && sz!=2)) return rtnErr('indexOf(stringOrDeviceOrList, substringOrItem)')
	if(((String)params[0].t==sDEV)&& (sz>2)){
		Integer t0=sz-1
		String item=(String)evaluateExpression(rtD,params[t0],sSTR).v
		for(Integer idx=0; idx<t0; idx++){
			Map it=evaluateExpression(rtD,params[idx],sSTR)
			if(it.v==item){
				return [(sT):sINT,(sV):idx]
			}
		}
		return [(sT):sINT,(sV):-1]
	}else if(params[0].v instanceof Map){
		String item=evaluateExpression(rtD,params[1],(String)params[0].t).v
		def key=((Map)params[0].v).find{ it.value==item }?.key
		return [(sT):sSTR,(sV):key]
	}else{
		String value=(String)evaluateExpression(rtD,params[0],sSTR).v
		String substring=(String)evaluateExpression(rtD,params[1],sSTR).v
		return [(sT):sINT,(sV):(Integer)value.indexOf(substring)]
	}
}

/** lastIndexOf finds the last occurrence of a substring in a string		**/
/** Usage: lastIndexOf(string, substring)					**/
private Map func_lastindexof(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,2) || ((String)params[0].t!=sDEV && sz!=2)) return rtnErr('lastIndexOf(string, substring)')
	if(((String)params[0].t==sDEV)&& (sz>2)){
		String item=(String)evaluateExpression(rtD,params[sz-1],sSTR).v
		for(Integer idx=sz-2; idx>=0; idx--){
			Map it=evaluateExpression(rtD,params[idx],sSTR)
			if(it.v==item){
				return [(sT):sINT,(sV):idx]
			}
		}
		return [(sT):sINT,(sV):-1]
	}else if(params[0].v instanceof Map){
		String item=evaluateExpression(rtD,params[1],(String)params[0].t).v
		def key=((Map)params[0].v).find{ it.value==item }?.key
		return [(sT):sSTR,(sV):key]
	}else{
		String value=(String)evaluateExpression(rtD,params[0],sSTR).v
		String substring=(String)evaluateExpression(rtD,params[1],sSTR).v
		return [(sT):sINT,(sV):(Integer)value.lastIndexOf(substring)]
	}
}


/** lower returns a lower case value of a string				**/
/** Usage: lower(string)							**/
private Map func_lower(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('lower(string)')
	String result=sBLK
	for(Map param in params){
		result += (String)evaluateExpression(rtD,param,sSTR).v
	}
	return [(sT):sSTR,(sV):result.toLowerCase()]
}

/** upper returns a upper case value of a string				**/
/** Usage: upper(string)							**/
private Map func_upper(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('upper(string)')
	String result=sBLK
	for(Map param in params){
		result += (String)evaluateExpression(rtD,param,sSTR).v
	}
	return [(sT):sSTR,(sV):result.toUpperCase()]
}

/** title returns a title case value of a string				**/
/** Usage: title(string)							**/
private Map func_title(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('title(string)')
	String result=sBLK
	for(Map param in params){
		result += (String)evaluateExpression(rtD,param,sSTR).v
	}
	return [(sT):sSTR,(sV):((List)result.tokenize(sSPC))*.toLowerCase()*.capitalize().join(sSPC)]
}

/** avg calculates the average of a series of numeric values			**/
/** Usage: avg(values)								**/
private Map func_avg(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('avg'+sVALUEN)
	Double sum=dZERO
	for(Map param in params){
		sum += (Double)evaluateExpression(rtD,param,sDEC).v
	}
	return [(sT):sDEC,(sV):sum/params.size()]
}

/** median returns the value in the middle of a sorted array			**/
/** Usage: median(values)							**/
private Map func_median(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('median'+sVALUEN)
	List data=params.collect{ evaluateExpression(rtD,(Map)it,sDYN)}.sort{ it.v }
	if(data){
		return data[(Integer)Math.floor(data.size()/2)]
	}
	return [(sT):sDYN,(sV):sBLK]
}

/** least returns the value that is least found a series of numeric values	**/
/** Usage: least(values)							**/
private Map func_least(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('least'+sVALUEN)
	Map<Object,Map> data=[:]
	for(Map param in params){
		Map value=evaluateExpression(rtD,param,sDYN)
		data[value.v]=[(sT):(String)value.t,(sV):value.v,(sC):(data[value.v]?.c ?: 0)+1]
	}
	Map value=data.sort{ it.value.c }.collect{ it.value }[0]
	return [(sT):(String)value.t,(sV):value.v]
}

/** most returns the value that is most found a series of numeric values	**/
/** Usage: most(values)								**/
private Map func_most(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('most'+sVALUEN)
	Map<Object,Map> data=[:]
	for(Map param in params){
		Map value=evaluateExpression(rtD,param,sDYN)
		data[value.v]=[(sT):(String)value.t,(sV):value.v,(sC):(data[value.v]?.c ?: 0)+1]
	}
	Map value=data.sort{ -it.value.c }.collect{ it.value }[0]
	return [(sT):(String)value.t,(sV):value.v]
}

/** sum calculates the sum of a series of numeric values			**/
/** Usage: sum(values)								**/
private Map func_sum(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('sum'+sVALUEN)
	Double sum=dZERO
	for(Map param in params){
		sum += (Double)evaluateExpression(rtD,param,sDEC).v
	}
	return [(sT):sDEC,(sV):sum]
}

/** variance calculates the variance of a series of numeric values	**/
/** Usage: variance(values)							**/
private Map func_variance(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('variance'+sVALUEN)
	Double sum=dZERO
	List values=[]
	for(Map param in params){
		Double value=(Double)evaluateExpression(rtD,param,sDEC).v
		Boolean a=values.push(value)
		sum += value
	}
	Integer sz=values.size()
	Double avg=sum/sz
	sum=dZERO
	for(Integer i=0; i<sz; i++){
		sum += ((Double)values[i]-avg)**2
	}
	return [(sT):sDEC,(sV):sum/sz]
}

/** stdev calculates the [population] standard deviation of a series of numeric values	**/
/** Usage: stdev(values)							**/
private Map func_stdev(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)){
		return rtnErr('stdev'+sVALUEN)
	}
	Map result=func_variance(rtD,params)
	return [(sT):sDEC,(sV):Math.sqrt((Double)result.v)]
}

/** min calculates the minimum of a series of numeric values			**/
/** Usage: min(values)								**/
private Map func_min(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('min'+sVALUEN)
	List<Map> data=params.collect{ evaluateExpression(rtD,(Map)it,sDYN)}.sort{ it.v }
	if(data){
		return data[0]
	}
	return [(sT):sDYN, (sV):sBLK]
}

/** max calculates the maximum of a series of numeric values			**/
/** Usage: max(values)								**/
private Map func_max(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('max'+sVALUEN)
	List<Map> data=params.collect{ evaluateExpression(rtD,(Map)it,sDYN)}.sort{ it.v }
	if(data){
		return data[data.size()-1]
	}
	return [(sT):sDYN,(sV):sBLK]
}

/** abs calculates the absolute value of a number				**/
/** Usage: abs(number)								**/
private Map func_abs(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('abs(value)')
	Double value=(Double)evaluateExpression(rtD,params[0],sDEC).v
	String dataType=(value==Math.round(value).toDouble() ? sINT:sDEC)
	return [(sT):dataType,(sV):(Double)cast(rtD,Math.abs(value),dataType,sDEC)]
}

/** hslToHex converts a hue/saturation/level trio to it hex #rrggbb representation	**/
/** Usage: hslToHex(hue,saturation, level)						**/
private Map func_hsltohex(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,3)) return rtnErr('hsl(hue,saturation, level)')
	Double hue=(Double)evaluateExpression(rtD,params[0],sDEC).v
	Double saturation=(Double)evaluateExpression(rtD,params[1],sDEC).v
	Double level=(Double)evaluateExpression(rtD,params[2],sDEC).v
	return [(sT):sSTR,(sV):hslToHex(hue,saturation,level)]
}

/** count calculates the number of true/non-zero/non-empty items in a series of numeric values		**/
/** Usage: count(values)										**/
private Map func_count(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)){
		return [(sT):sINT,(sV):0]
	}
	Integer count=0
	if(params.size()==1 && ((String)params[0].t in [sSTR,sDYN])){
		String[] list=((String)evaluateExpression(rtD,params[0],sSTR).v).split(sCOMMA)
		Integer sz=list.size()
		for(Integer i=0; i<sz; i++){
			Boolean t1=cast(rtD,list[i],sBOOLN)
			count += t1 ? 1:0
		}
	}else{
		for(Map param in params){
			count += (Boolean)evaluateExpression(rtD,param,sBOOLN).v ? 1:0
		}
	}
	return [(sT):sINT,(sV):count]
}

/** size returns the number of values provided				**/
/** Usage: size(values)							**/
private Map func_size(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)){
		return [(sT):sINT,(sV):0]
	}
	Integer count
	Integer sz=params.size()
	if(sz==1 && ((String)params[0].t in [sSTR,sDYN])){
		//List list=((String)evaluateExpression(rtD,params[0],sSTR).v).split(sCOMMA).toList()
		String[] list=((String)evaluateExpression(rtD,params[0],sSTR).v).split(sCOMMA)
		count=list.size()
	}else{
		count=sz
	}
	return [(sT):sINT,(sV):count]
}

/** age returns the number of milliseconds an attribute had the current value	**/
/** Usage: age([device:attribute])						**/
private Map func_age(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('age'+sDATTRH)
	Map param=evaluateExpression(rtD,params[0],sDEV)
	if((String)param.t==sDEV && (String)param.a && ((List)param.v).size()){
		def device=getDevice(rtD,(String)((List)param.v)[0])
		if(device!=null){
			def dstate=device.currentState((String)param.a,true)
			if(dstate){
				Long result=elapseT((Long)((Date)dstate.getDate()).getTime())
				return [(sT):sLONG,(sV):result]
			}
		}
	}
	return [(sT):sERROR,(sV):'Invalid device']
}

/** previousAge returns the number of milliseconds an attribute had the previous value		**/
/** Usage: previousAge([device:attribute])							**/
private Map func_previousage(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('previousAge'+sDATTRH)
	Map param=evaluateExpression(rtD,params[0],sDEV)
	if((String)param.t==sDEV && (String)param.a && ((List)param.v).size()){
		def device=getDevice(rtD,(String)((List)param.v)[0])
		if(device!=null && !isDeviceLocation(device)){
			List states=device.statesSince((String)param.a,new Date(elapseT(604500000L)),[max:5])
			Integer sz=states.size()
			if(sz>1){
				def newValue=states[0].getValue()
				//some events get duplicated,so we really want to look for the last "different valued" state
				for(Integer i=1; i<sz; i++){
					if(states[i].getValue()!=newValue){
						Long result=elapseT((Long)((Date)states[i].getDate()).getTime())
						return [(sT):sLONG,(sV):result]
					}
				}
			}
			//we're saying 7 days,though it may be wrong - but we have no data
			return [(sT):sLONG,(sV):604800000L]
		}
	}
	return [(sT):sERROR,(sV):'Invalid device']
}

/** previousValue returns the previous value of the attribute				**/
/** Usage: previousValue([device:attribute])						**/
private Map func_previousvalue(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('previousValue'+sDATTRH)
	Map param=evaluateExpression(rtD,params[0],sDEV)
	if((String)param.t==sDEV && (String)param.a && ((List)param.v).size()){
		Map attribute=Attributes()[(String)param.a]
		if(attribute!=null){
			def device=getDevice(rtD,(String)((List)param.v)[0])
			if(device!=null && !isDeviceLocation(device)){
				List states=device.statesSince((String)param.a,new Date(elapseT(604500000L)),[max:5])
				Integer sz=states.size()
				if(sz>1){
					def newValue=states[0].getValue()
					//some events get duplicated,so we really want to look for the last "different valued" state
					for(Integer i=1; i<sz; i++){
						def result=states[i].getValue()
						if(result!=newValue){
							return [(sT):(String)attribute.t, (sV):cast(rtD,result,(String)attribute.t)]
						}
					}
				}
				//we're saying no value - we have no data
				return [(sT):sSTR,(sV):sBLK]
			}
		}
	}
	return [(sT):sERROR,(sV):'Invalid device']
}

/** newer returns the number of devices whose attribute had the current		**/
/** value for less than the specified number of milliseconds			**/
/** Usage: newer([device:attribute] [,.., [device:attribute]],threshold)	**/
private Map func_newer(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('newer'+sDATTRHT)
	Integer t0=params.size()-1
	Long threshold=(Long)evaluateExpression(rtD,params[t0],sLONG).v
	Integer result=0
	for(Integer i=0; i<t0; i++){
		Map age=func_age(rtD,[params[i]])
		if((String)age.t!=sERROR && (Long)age.v<threshold)result++
	}
	return [(sT):sINT,(sV):result]
}

/** older returns the number of devices whose attribute had the current		**/
/** value for more than the specified number of milliseconds			**/
/** Usage: older([device:attribute] [,.., [device:attribute]],threshold)	**/
private Map func_older(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('older'+sDATTRHT)
	Integer t0=params.size()-1
	Long threshold=(Long)evaluateExpression(rtD,params[t0],sLONG).v
	Integer result=0
	for(Integer i=0; i<t0; i++){
		Map age=func_age(rtD,[params[i]])
		if((String)age.t!=sERROR && (Long)age.v>=threshold)result++
	}
	return [(sT):sINT,(sV):result]
}

/** startsWith returns true if a string starts with a substring			**/
/** Usage: startsWith(string, substring)					**/
private Map func_startswith(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('startsWith(string, substring)')
	String string=(String)evaluateExpression(rtD,params[0],sSTR).v
	String substring=(String)evaluateExpression(rtD,params[1],sSTR).v
	return [(sT):sBOOLN,(sV):(Boolean)string.startsWith(substring)]
}

/** endsWith returns true if a string ends with a substring				**/
/** Usage: endsWith(string, substring)							**/
private Map func_endswith(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('endsWith(string, substring)')
	String string=(String)evaluateExpression(rtD,params[0],sSTR).v
	String substring=(String)evaluateExpression(rtD,params[1],sSTR).v
	return [(sT):sBOOLN,(sV):(Boolean)string.endsWith(substring)]
}

/** contains returns true if a string contains a substring				**/
/** Usage: contains(string, substring)							**/
private Map func_contains(Map rtD,List<Map> params){
	Integer t0=params.size()
	if(!checkParams(rtD,params,2) || ((String)params[0].t!=sDEV && t0!=2)) return rtnErr('contains(string, substring)')
	if((String)params[0].t==sDEV && t0>2){
		t0=t0-1
		String item=evaluateExpression(rtD,params[t0],sSTR).v
		for(Integer idx=0; idx<t0; idx++){
			Map it=evaluateExpression(rtD,params[idx],sSTR)
			if(it.v==item){
				return [(sT):sBOOLN,(sV):true]
			}
		}
		return [(sT):sBOOLN,(sV):false]
	}else{
		String string=(String)evaluateExpression(rtD,params[0],sSTR).v
		String substring=(String)evaluateExpression(rtD,params[1],sSTR).v
		return [(sT):sBOOLN,(sV):(Boolean)string.contains(substring)]
	}
}

/** matches returns true if a string matches a pattern					**/
/** Usage: matches(string, pattern)							**/
private Map func_matches(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('matches(string, pattern)')
	String string=(String)evaluateExpression(rtD,params[0],sSTR).v
	String pattern=(String)evaluateExpression(rtD,params[1],sSTR).v
	Boolean r=match(string,pattern)
	return [(sT):sBOOLN,(sV):r]
}

/** eq returns true if two values are equal					**/
/** Usage: eq(value1, value2)							**/
private Map func_eq(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('eq(value1, value2)')
	String t=(String)params[0].t==sDEV ? (String)params[1].t:(String)params[0].t
	Map value1=evaluateExpression(rtD,params[0],t)
	Map value2=evaluateExpression(rtD,params[1],t)
	return [(sT):sBOOLN,(sV):value1.v==value2.v]
}

/** lt returns true if value1<value2						**/
/** Usage: lt(value1, value2)							**/
private Map func_lt(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('lt(value1, value2)')
	Map value1=evaluateExpression(rtD,params[0])
	Map value2=evaluateExpression(rtD,params[1],(String)value1.t)
	return [(sT):sBOOLN,(sV):value1.v<value2.v]
}

/** le returns true if value1<=value2						**/
/** Usage: le(value1, value2)							**/
private Map func_le(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('le(value1, value2)')
	Map value1=evaluateExpression(rtD,params[0])
	Map value2=evaluateExpression(rtD,params[1],(String)value1.t)
	return [(sT):sBOOLN,(sV):value1.v<=value2.v]
}

/** gt returns true if value1>value2						**/
/** Usage: gt(value1, value2)							**/
private Map func_gt(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('gt(value1, value2)')
	Map value1=evaluateExpression(rtD,params[0])
	Map value2=evaluateExpression(rtD,params[1],(String)value1.t)
	return [(sT):sBOOLN,(sV):value1.v>value2.v]
}

/** ge returns true if value1>=value2						**/
/** Usage: ge(value1, value2)							**/
private Map func_ge(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('ge(value1, value2)')
	Map value1=evaluateExpression(rtD,params[0])
	Map value2=evaluateExpression(rtD,params[1],(String)value1.t)
	return [(sT):sBOOLN,(sV):value1.v>=value2.v]
}

/** not returns the negative Boolean value					**/
/** Usage: not(value)								**/
private Map func_not(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('not(value)')
	Boolean value=(Boolean)evaluateExpression(rtD,params[0],sBOOLN).v
	return [(sT):sBOOLN,(sV):!value]
}

/** if evaluates a Boolean and returns value1 if true,otherwise value2		**/
/** Usage: if(condition, valueIfTrue,valueIfFalse)				**/
private Map func_if(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,3)) return rtnErr('if(condition, valueIfTrue,valueIfFalse)')
	Boolean value=(Boolean)evaluateExpression(rtD,params[0],sBOOLN).v
	return value ? evaluateExpression(rtD,params[1]) : evaluateExpression(rtD,params[2])
}

/** isEmpty returns true if the value is empty					**/
/** Usage: isEmpty(value)							**/
private Map func_isempty(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('isEmpty(value)')
	Map value=evaluateExpression(rtD,params[0])
	Boolean result=value.v==null || (value.v instanceof List ? value.v==[null] || value.v==[] || value.v==[sSNULL] : false) || (String)value.t==sERROR || value.v==sSNULL || (String)cast(rtD,value.v,sSTR)==sBLK || "$value.v".toString()==sBLK
	return [(sT):sBOOLN,(sV):result]
}

/** datetime returns the value as a datetime type				**/
/** Usage: datetime([value])							**/
private Map func_datetime(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,0) || sz>1) return rtnErr('datetime([value])')
	Long value=sz>0 ? (Long)evaluateExpression(rtD,params[0],sDTIME).v:now()
	return [(sT):sDTIME,(sV):value]
}

/** date returns the value as a date type					**/
/** Usage: date([value])							**/
private Map func_date(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,0) || sz>1) return rtnErr('date([value])')
	Long value=sz>0 ? (Long)evaluateExpression(rtD,params[0],sDATE).v:(Long)cast(rtD,now(),sDATE,sDTIME)
	return [(sT):sDATE,(sV):value]
}

/** time returns the value as a time type					**/
/** Usage: time([value])							**/
private Map func_time(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,0) || sz>1) return rtnErr('time([value])')
	Long value=sz>0 ? (Long)evaluateExpression(rtD,params[0],sTIME).v:(Long)cast(rtD,now(),sTIME,sDTIME)
	return [(sT):sTIME,(sV):value]
}

private Map addtimeHelper(Map rtD,List<Map> params,Long mulp,String msg){
	Integer sz=params.size()
	if(!checkParams(rtD,params,1) || sz>2) return rtnErr(msg)
	Long value=sz==2 ? (Long)evaluateExpression(rtD,params[0],sDTIME).v:now()
	Long delta=(Long)evaluateExpression(rtD,(sz==2 ? params[1]:params[0]),sLONG).v*mulp
	return [(sT):sDTIME,(sV):value+delta]
}

/** addSeconds returns the value as a time type						**/
/** Usage: addSeconds([dateTime,]seconds)						**/
private Map func_addseconds(Map rtD,List<Map> params){
	return addtimeHelper(rtD,params,lTHOUS,'addSeconds([dateTime,]seconds)')
}

/** addMinutes returns the value as a time type						**/
/** Usage: addMinutes([dateTime,]minutes)						**/
private Map func_addminutes(Map rtD,List<Map> params){
	return addtimeHelper(rtD,params,dMSMINT.toLong(),'addMinutes([dateTime,]minutes)')
}

/** addHours returns the value as a time type						**/
/** Usage: addHours([dateTime,]hours)							**/
private Map func_addhours(Map rtD,List<Map> params){
	return addtimeHelper(rtD,params,dMSECHR.toLong(),'addHours([dateTime,]hours)')
}

/** addDays returns the value as a time type						**/
/** Usage: addDays([dateTime,]days)							**/
private Map func_adddays(Map rtD,List<Map> params){
	return addtimeHelper(rtD,params,lMSDAY,'addDays([dateTime,]days)')
}

/** addWeeks returns the value as a time type						**/
/** Usage: addWeeks([dateTime,]weeks)							**/
private Map func_addweeks(Map rtD,List<Map> params){
	return addtimeHelper(rtD,params,604800000L,'addWeeks([dateTime,]weeks)')
}

/** weekDayName returns the name of the week day					**/
/** Usage: weekDayName(dateTimeOrWeekDayIndex)						**/
private Map func_weekdayname(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('weekDayName(dateTimeOrWeekDayIndex)')
	Long value=(Long)evaluateExpression(rtD,params[0],sLONG).v
	Integer index=((value>=lMSDAY)? utcToLocalDate(value).day:value.toInteger()) % 7
	return [(sT):sSTR,(sV):(String)weekDaysFLD[index]]
}

/** monthName returns the name of the month						**/
/** Usage: monthName(dateTimeOrMonthNumber)						**/
private Map func_monthname(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('monthName(dateTimeOrMonthNumber)')
	Long value=(Long)evaluateExpression(rtD,params[0],sLONG).v
	Integer index=((value>=lMSDAY)? utcToLocalDate(value).month: (value-1L).toInteger())%12+1
	return [(sT):sSTR,(sV):(String)yearMonthsFLD[index]]
}

/** arrayItem returns the nth item in the parameter list				**/
/** Usage: arrayItem(index, item0[, item1[, .., itemN]])				**/
private Map func_arrayitem(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,2)) return rtnErr('arrayItem(index, item0[, item1[, .., itemN]])')
	Map serr=[(sT):sERROR, (sV):'Array item index is outside of bounds.']
	Integer index=(Integer)evaluateExpression(rtD,params[0],sINT).v
	Integer sz=params.size()
	if(sz==2 && ((String)params[1].t in [sSTR,sDYN])){
		//List list=((String)evaluateExpression(rtD,params[1],sSTR).v).split(sCOMMA).toList()
		String[] list=((String)evaluateExpression(rtD,params[1],sSTR).v).split(sCOMMA)
		if(index<0 || index>=list.size()) return serr
		return [(sT):sSTR,(sV):list[index]]
	}
	if(index<0 || index>=sz-1) return serr
	return params[index+1]
}

/** isBetween returns true if value>=startValue and value<=endValue		**/
/** Usage: isBetween(value,startValue,endValue)				**/
private Map func_isbetween(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,3)) return rtnErr('isBetween(value,startValue,endValue)')
	Map value=evaluateExpression(rtD,params[0])
	Map startValue=evaluateExpression(rtD,params[1],(String)value.t)
	Map endValue=evaluateExpression(rtD,params[2],(String)value.t)
	return [(sT):sBOOLN,(sV):value.v>=startValue.v && value.v<=endValue.v]
}

/** formatDuration returns a duration in a readable format					**/
/** Usage: formatDuration(value[, friendly=false[, granularity='s'[, showAdverbs=false]]])	**/
private Map func_formatduration(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,1) || sz>4) return rtnErr("formatDuration(value[, friendly=false[, granularity='s'[, showAdverbs=false]]])")
	Long value=(Long)evaluateExpression(rtD,params[0],sLONG).v
	Boolean friendly=sz>1 ? (Boolean)evaluateExpression(rtD,params[1],sBOOLN).v:false
	String granularity=sz>2 ? (String)evaluateExpression(rtD,params[2],sSTR).v:sS
	Boolean showAdverbs=sz>3 ? (Boolean)evaluateExpression(rtD,params[3],sBOOLN).v:false

	Integer sign=(value>=0)? 1:-1
	if(sign<0)value=-value
	Integer ms=(value%1000).toInteger()
	value=Math.floor((value-ms)/dTHOUS).toLong()
	Integer s=(value%60).toInteger()
	value=Math.floor((value-s)/dSIXTY).toLong()
	Integer m=(value%60).toInteger()
	value=Math.floor((value-m)/dSIXTY).toLong()
	Integer h=(value%24).toInteger()
	value=Math.floor((value-h)/24.0D).toLong()
	Integer d=value.toInteger()

	Integer parts
	String partName
	switch(granularity){
		case sD: parts=1; partName='day'; break
		case sH: parts=2; partName='hour'; break
		case 'm': parts=3; partName='minute'; break
		case 'ms': parts=5; partName='millisecond'; break
		default:parts=4; partName='second'; break
	}
	parts=friendly ? parts:(parts<3 ? 3:parts)
	String result
	if(friendly){
		List p=[]
		if(d)Boolean a=p.push("$d day"+(d>1 ? sS:sBLK))
		if(parts>1 && h)Boolean a=p.push("$h hour"+(h>1 ? sS:sBLK))
		if(parts>2 && m)Boolean a=p.push("$m minute"+(m>1 ? sS:sBLK))
		if(parts>3 && s)Boolean a=p.push("$s second"+(s>1 ? sS:sBLK))
		if(parts>4 && ms)Boolean a=p.push("$ms millisecond"+(ms>1 ? sS:sBLK))
		sz=p.size()
		switch(sz){
			case 0:
				result=showAdverbs ? 'now' : '0 '+partName+sS
				break
			case 1:
				result=p[0]
				break
			default:
				result=sBLK
				for(Integer i=0; i<sz; i++){
					result += (i ? (sz>2 ? ',':sSPC):sBLK)+(i==sz-1 ? 'and ':sBLK)+p[i]
				}
				result=(showAdverbs && (sign>0)? 'in ':sBLK)+result+(showAdverbs && (sign<0)? ' ago':sBLK)
				break
		}
	}else{
		result=(sign<0 ? sMINUS:sBLK)+(d>0 ? sprintf("%dd ",d):sBLK)+sprintf("%02d:%02d",h,m)+(parts>3 ? sprintf(":%02d",s):sBLK)+(parts>4 ? sprintf(".%03d",ms):sBLK)
	}
	return [(sT):sSTR,(sV):result]
}

/** formatDateTime returns a datetime in a readable format				**/
/** Usage: formatDateTime(value[, format])						**/
private Map func_formatdatetime(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,1) || sz>2) return rtnErr('formatDateTime(value[, format])')
	Long value=(Long)evaluateExpression(rtD,params[0],sDTIME).v
	String format=sz>1 ? (String)evaluateExpression(rtD,params[1],sSTR).v:sNULL
	return [(sT):sSTR,(sV):(format ? formatLocalTime(value,format) : formatLocalTime(value))]
}

/** random returns a random value						**/
/** Usage: random([range | value1, value2[, ..,valueN]])			**/
private Map func_random(Map rtD,List<Map> params){
	Integer sz=params!=null && (params instanceof List) ? params.size():0
	switch(sz){
		case 0:
			return [(sT):sDEC,(sV):Math.random()]
		case 1:
			Double range=(Double)evaluateExpression(rtD,params[0],sDEC).v
			return [(sT):sINT,(sV):(Integer)Math.round(range*Math.random())]
		case 2:
			List<String> n=[sINT,sDEC]
			if(((String)params[0].t in n) && ((String)params[1].t in n)){
				Double min=(Double)evaluateExpression(rtD,params[0],sDEC).v
				Double max=(Double)evaluateExpression(rtD,params[1],sDEC).v
				if(min>max){
					Double swap=min
					min=max
					max=swap
				}
				return [(sT):sINT,(sV):(Integer)Math.round(min+(max-min)*Math.random())]
			}
	}
	Integer choice=(Integer)Math.round((sz-1)*Math.random())
	if(choice>=sz)choice=sz-1
	return params[choice]
}

/** distance returns a distance measurement							**/
/** Usage: distance((device | latitude,longitude),(device | latitude,longitude)[, unit])	**/
@SuppressWarnings('GroovyVariableNotAssigned')
private Map func_distance(Map rtD,List<Map> params){
	Integer sz=params.size()
	if(!checkParams(rtD,params,2) || sz>5) return rtnErr('distance((device | latitude,longitude),(device | latitude,longitude)[, unit])')
	Double lat1, lng1, lat2,lng2
	String unit
	Integer idx=0
	Integer pidx=0
	String errMsg=sBLK
	while (pidx<sz){
		if((String)params[pidx].t!=sDEV || ((String)params[pidx].t==sDEV && !!params[pidx].a)){
			//a decimal or device attribute is provided
			switch(idx){
			case 0:
				lat1=(Double)evaluateExpression(rtD,params[pidx],sDEC).v
				break
			case 1:
				lng1=(Double)evaluateExpression(rtD,params[pidx],sDEC).v
				break
			case 2:
				lat2=(Double)evaluateExpression(rtD,params[pidx],sDEC).v
				break
			case 3:
				lng2=(Double)evaluateExpression(rtD,params[pidx],sDEC).v
				break
			case 4:
				unit=(String)evaluateExpression(rtD,params[pidx],sSTR).v
			}
			idx += 1
			pidx += 1
			continue
		}else{
			switch(idx){
				case 0:
				case 2:
					params[pidx].a='latitude'
					Double lat=(Double)evaluateExpression(rtD,params[pidx],sDEC).v
					params[pidx].a='longitude'
					Double lng=(Double)evaluateExpression(rtD,params[pidx],sDEC).v
					if(idx==0){
						lat1=lat
						lng1=lng
					}else{
						lat2=lat
						lng2=lng
					}
					idx += 2
					pidx += 1
					continue
				default:
					errMsg="Invalid parameter order. Expecting parameter #${idx+1} to be a decimal,not a device."
					pidx=-1
					break
			}
		}
		if(pidx==-1)break
	}
	if(errMsg!=sBLK)return [(sT):sERROR,(sV):errMsg]
	if(idx<4 || idx>5)return [(sT):sERROR,(sV):'Invalid parameter combination. Expecting either two devices,a device and two decimals,or four decimals,followed by an optional unit.']
	Double earthRadius=6371000.0D //meters
	Double dLat=Math.toRadians(lat2-lat1)
	Double dLng=Math.toRadians(lng2-lng1)
	Double a=Math.sin(dLat/2.0D)*Math.sin(dLat/2.0D)+
		Math.cos(Math.toRadians(lat1))*Math.cos(Math.toRadians(lat2))*
		Math.sin(dLng/2.0D)*Math.sin(dLng/2.0D)
	Double c=2.0D*Math.atan2(Math.sqrt(a),Math.sqrt(dONE-a))
	Double dist=earthRadius*c
	switch(unit!=null ? unit:'m'){
		case 'km':
		case 'kilometer':
		case 'kilometers':
			return [(sT):sDEC,(sV):dist/dTHOUS]
		case 'mi':
		case 'mile':
		case 'miles':
			return [(sT):sDEC,(sV):dist/1609.3440D]
		case 'ft':
		case 'foot':
		case 'feet':
			return [(sT):sDEC,(sV):dist/0.3048D]
		case 'yd':
		case 'yard':
		case 'yards':
			return [(sT):sDEC,(sV):dist/0.9144D]
	}
	return [(sT):sDEC,(sV):dist]
}

/** json encodes data as a JSON string							**/
/** Usage: json(value[, pretty])							**/
private static Map func_json(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1) || params.size()>2) return rtnErr('json(value[, format])')
	JsonBuilder builder=new JsonBuilder([((Map)params[0]).v])
	String op=params[1] ? 'toPrettyString' : 'toString'
	String json=builder."${op}"()
	return [(sT):sSTR,(sV):json[1..-2].trim()]
}

/** urlencode encodes data for use in a URL						**/
/** Usage: urlencode(value)								**/
private Map func_urlencode(Map rtD,List<Map> params){
	if(!checkParams(rtD,params,1)) return rtnErr('urlencode(value])')
	String t0=(String)evaluateExpression(rtD,params[0],sSTR).v
	String value=(t0!=sNULL ? t0:sBLK)
	return [(sT):sSTR,(sV):encodeURIComponent(value)]
}
private Map func_encodeuricomponent(Map rtD,List params){ return func_urlencode(rtD,params)}

/** COMMON PUBLISHED METHODS							**/

private String mem(Boolean showBytes=true){
	String mbytes=new JsonOutput().toJson((Map)state)
	Integer bytes=mbytes.length()
	return Math.round(100.0D*(bytes/100000.0D))+"%${showBytes ? " ($bytes bytes)".toString() : sBLK}"
}

private static String runTimeHis(Map rtD){
	String myId=(String)rtD.id
	return 'Total run history: '+((List)theCacheVFLD[myId].runTimeHis).toString()+'<br>' +
		'Last run details: '+((Map)theCacheVFLD[myId].runStats).toString()
}

/** UTILITIES									**/

private static String encodeURIComponent(value){
	// URLEncoder converts spaces to + which is then indistinguishable from any
	// actual + characters in the value. Match encodeURIComponent in ECMAScript
	// which encodes "a+b c" as "a+b%20c" rather than URLEncoder's "a+b+c"
	String holder='__wc_plus__'
	return URLEncoder.encode(
		"${value}".toString().replaceAll('\\+',holder),
		'UTF-8'
	).replaceAll('\\+','%20').replaceAll(holder,'+')
}

@Field volatile static Map<String,Map> theHashMapVFLD=[:]

private static String getThreeAxisOrientation(Map m /*, Boolean getIndex=false */){
	if((m.x!=null)&& (m.y!=null)&& (m.z!=null)){
		Integer x=Math.abs(m.x.toDouble()).toInteger()
		Integer y=Math.abs(m.y.toDouble()).toInteger()
		Integer z=Math.abs(m.z.toDouble()).toInteger()
		Integer side=(x>y ? (x>z ? 0:2):(y>z ? 1:2))
		side+= ( (side==0 && m.x<0) || (side==1 && m.y<0) || (side==2 && m.z<0) ? 3:0 )
//		if(getIndex){ return side }
		List<String> orientations=['rear','down','left','front','up','right']
		return orientations[side]+' side up'
	}
	return sNULL
}

private Long getTimeToday(Long time){
	Long t0=getMidnightTime()
	Long result=time+t0
	//we need to adjust for time overlapping during DST changes
	return Math.round(result+(((TimeZone)location.timeZone).getOffset(t0)-((TimeZone)location.timeZone).getOffset(result))*dONE)
}

@Field static final List<String> trueStrings= [ '1','true', "on", "open",  "locked",  "active",  "wet",           "detected",    "present",    "occupied",    "muted",  "sleeping"]
@Field static final List<String> falseStrings=[ '0','false',"off","closed","unlocked","inactive","dry","clear",   "not detected","not present","not occupied","unmuted","not sleeping","null"]

private cast(Map rtD,ival,String dataT,String isrcDT=sNULL){
	if(dataT==sDYN)return ival

	String dataType=dataT
	String srcDataType=isrcDT
	def value=ival

	if(value==null){
		value=sBLK
		srcDataType=sSTR
	}
	value=(value instanceof GString)? "$value".toString():value //get rid of GStrings
	if(srcDataType==sNULL || srcDataType.length()==0 || srcDataType==sBOOLN || srcDataType==sDYN){
		if(value instanceof List){srcDataType=sDEV}else
		if(value instanceof Boolean){srcDataType=sBOOLN}else
		if(value instanceof String){srcDataType=sSTR}else
		if(value instanceof Integer){srcDataType=sINT}else
		if(value instanceof Long || value instanceof BigInteger){srcDataType=sLONG}else
		if(value instanceof Double || value instanceof BigDecimal || value instanceof Float){srcDataType=sDEC}else
		if(value instanceof Map && value.x!=null && value.y!=null && value.z!=null){srcDataType='vector3'}else{
			value="$value".toString()
			srcDataType=sSTR
		}
	}
	//overrides
	switch(srcDataType){
		case sBOOL: srcDataType=sBOOLN; break
		case sNUMBER: srcDataType=sDEC; break
		case sENUM: srcDataType=sSTR; break
	}
	switch(dataType){
		case sBOOL: dataType=sBOOLN; break
		case sNUMBER: dataType=sDEC; break
		case sENUM: dataType=sSTR; break
	}
	if((Boolean)rtD.eric)myDetail rtD,"cast $srcDataType $value as $dataType"
	switch(dataType){
		case sSTR:
		case sTEXT:
			switch(srcDataType){
				case sBOOLN: return value ? sTRUE:sFALSE
				case sDEC:
					//if(value instanceof Double)return sprintf('%f',value)
					// strip trailing zeroes (e.g. 5.00 to 5 and 5.030 to 5.03)
					return value.toString().replaceFirst(/(?:\.|(\.\d*?))0+$/,'$1')
				case sINT:
				case sLONG: break
				case sTIME: return formatLocalTime(value,'h:mm:ss a z')
				case sDATE: return formatLocalTime(value,'EEE, MMM d yyyy')
				case sDTIME: return formatLocalTime(value)
				case sDEV: return buildDeviceList(rtD,value)
			}
			return "$value".toString()
		case sINT:
			switch(srcDataType){
				case sSTR:
					String s=value.replaceAll(/[^-\d.-E]/,sBLK)
					if(s.isInteger())
						return s.toInteger()
					if(s.isFloat())
						return (Integer)Math.floor(s.toDouble()).toInteger()
					if(s in trueStrings)
						return (Integer)1
					break
				case sBOOLN: return (Integer)(value ? 1:0)
			}
			Integer result
			try{
				result=(Integer)value.toInteger()
			}catch(ignored){
				result=0
			}
			return result
		case sLONG:
			switch(srcDataType){
				case sSTR:
					String s=value.replaceAll(/[^-\d.-E]/,sBLK)
					if(s.isLong())
						return (Long)s.toLong()
					if(s.isInteger())
						return (Long)s.toLong()
					if(s.isFloat())
						return (Long)Math.floor(s.toDouble()).toLong()
					if(s in trueStrings)
						return 1L
					break
				case sBOOLN: return (value ? 1L:lZERO)
			}
			Long result
			try{
				result=(Long)value.toLong()
			}catch(ignored){
				result=lZERO
			}
			return result
		case sDEC:
			switch(srcDataType){
				case sSTR:
					String s=value.replaceAll(/[^-\d.-E]/,sBLK)
					if(s.isDouble())
						return (Double)s.toDouble()
					if(s.isFloat())
						return (Double)s.toDouble()
					if(s.isLong())
						return (Double)s.toLong()
					if(s.isInteger())
						return (Double)s.toInteger()
					if(s in trueStrings)
						return dONE
					break
				case sBOOLN: return (Double)(value ? dONE:dZERO)
			}
			Double result=dZERO
			try{
				result=(Double)value.toDouble()
			}catch(ignored){}
			return result
		case sBOOLN:
			switch(srcDataType){
				case sINT:
				case sDEC:
				case sBOOLN:
					return !!value
				case sDEV:
					return value instanceof List && ((List)value).size()>0
			}
			if(value){
				String s= "$value".toLowerCase().trim()
				if(s in falseStrings)return false
				if(s in trueStrings)return true
			}
			return !!value
		case sTIME:
			if("$value".isNumber() && value.toLong()<lMSDAY) return value.toLong()
			Long d= srcDataType==sSTR ? stringToTime(value):(Long)value // (Long)cast(rtD,value,sLONG)
			Date t1=new Date(d)
			Long t2=Math.round((t1.hours*dSECHR+(Integer)t1.minutes*dSIXTY+t1.seconds)*dTHOUS)
			return t2
		case sDATE:
			if(srcDataType==sTIME){
				Long tt1=value.toLong()
				if(tt1<lMSDAY) value=getTimeToday(tt1)
			}
			Long d=(srcDataType==sSTR)? stringToTime(value):(Long)value // (Long)cast(rtD,value,sLONG)
			Date t1=new Date(d)
			Long t2=Math.round((Math.floor(d/dTHOUS)*dTHOUS)-((t1.hours*dSECHR+t1.minutes*dSIXTY+t1.seconds)*dTHOUS)) // take ms off and first guess at midnight (could be earlier/later depending if DST change day
			return t2
		case sDTIME:
			if(srcDataType==sTIME){
				Long tt1=value.toLong()
				if(tt1<lMSDAY) value=getTimeToday(tt1)
			}
			return (srcDataType==sSTR)? stringToTime(value):(Long)value // (Long)cast(rtD,value,sLONG)
		case 'vector3':
			return value instanceof Map && value.x!=null && value.y!=null && value.z!=null ? value : [x:0,y:0,z:0]
		case sORIENT:
			return value instanceof Map ? getThreeAxisOrientation(value) : value
		case 'ms':
		case sS:
		case 'm':
		case sH:
		case sD:
		case 'w':
		case sN:
		case 'y':
			Long t1
			switch(srcDataType){
				case sINT:
				case sLONG:
					t1=value.toLong(); break
				default:
					t1=(Long)cast(rtD,value,sLONG)
			}
			switch(dataType){
				case 'ms': return t1
				case sS: return Math.round(t1*dTHOUS)
				case 'm': return Math.round(t1*dMSMINT)
				case sH: return Math.round(t1*dMSECHR)
				case sD: return Math.round(t1*dMSDAY)
				case 'w': return Math.round(t1*604800000.0D)
				case sN: return Math.round(t1*2592000000.0D)
				case 'y': return Math.round(t1*31536000000.0D)
			}
			break
		case sDEV:
		//device type is an array of device Ids
			if(value instanceof List){
				Boolean a=value.removeAll{ !it }
				return value
			}
			String v=(String)cast(rtD,value,sSTR)
			if(v!=sNULL)return [v]
			return []
	}
	//anything else
	return value
}

private Long elapseT(Long st){
	return Math.round(dONE*(Long)now()-st)
}

private Date utcToLocalDate(dateOrTimeOrString=null){ // this is really cast to Date
	def ldate=dateOrTimeOrString
	if(!(ldate instanceof Long)){
		if(ldate instanceof String){
			ldate=stringToTime((String)ldate)
		}
		if(ldate instanceof Date){
			//get unix time
			ldate=(Long)((Date)ldate).getTime()
		}
	}
	if(ldate==null || ldate==lZERO){
		ldate=now()
	}
	if(ldate instanceof Long){
		//HE is set to local timezone of hub by default, so we don't have to try to set local timezone
		return new Date((Long)ldate)
	}
	return null
}

private Date localDate(){ return utcToLocalDate(now())}

//private Long localTime(){ return now()} //utcToLocalTime()}

private Long stringToTime(dateOrTimeOrString){ // this is convert to time
	Long result
	if("$dateOrTimeOrString".isNumber()){
		Long tt=dateOrTimeOrString.toLong()
		if(tt<lMSDAY){
			result=getTimeToday(tt)
			return result
		}else{
// deal with a time in sec (vs. ms)
			Long span=63072000L // Math.round(730*(dMSDAY/dTHOUS)) // 2 years in secs
			Long nowInsecs=now()/lTHOUS
			if(tt<(nowInsecs+span) && tt>(nowInsecs-span)){
				result=tt*lTHOUS
				return result
			}
		}
		return tt
	}

	if(dateOrTimeOrString instanceof String){

		String sdate=dateOrTimeOrString
		try{
			result=(new Date()).parse(sdate)
			return result
		}catch(ignored){}

		try{
			Date tt1=(Date)toDateTime(sdate)
			result=(Long)tt1.getTime()
			return result
		}catch(ignored){}

		// additional ISO 8601 that Hubitat does not parse
		try{
			String tt=sdate
			def regex1 = /Z/
			String tt0=tt.replaceAll(regex1," -0000")
			result= (new Date()).parse("yyyy-MM-dd'T'HH:mm z",tt0).getTime()
			return result
		} catch(ignored){}

		try{
			//get unix time
			//if(!(sdate =~ /(\s[A-Z]{3}((\+|\-)[0-9]{2}\:[0-9]{2}|\s[0-9]{4})?$)/)){
			if(!(sdate =~ /(\s[A-Z]{3}([+\-][0-9]{2}:[0-9]{2}|\s[0-9]{4})?$)/)){
				Long newDate=(new Date()).parse(sdate+sSPC+formatLocalTime(now(),'Z'))
				result=newDate
				return result
			}
			result=(new Date()).parse(sdate)
			return result
		}catch(ignored){}

		try{
			TimeZone tz=(TimeZone)location.timeZone
			if(sdate =~ /\s[A-Z]{3}$/){ // this is not the timezone... strings like CET are not unique.
				try{
					tz=TimeZone.getTimeZone(sdate[-3..-1])
					///sdate=sdate.take(sdate.size()-3).trim()
					sdate=sdate[0..sdate.size()-3].trim()
				}catch(ignored){}
			}

			String t0=sdate?.trim()?: sBLK
			t0=t0.toLowerCase()
			Boolean hasMeridian=false
			Boolean hasAM=false
			Boolean hasPM=false
			if((Boolean)t0.endsWith('a.m.')){
				t0= t0.replaceAll('a\\.m\\.','am')
			}
			if((Boolean)t0.endsWith('p.m.')){
				t0= t0.replaceAll('p\\.m\\.','pm')
			}
			if((Boolean)t0.endsWith('am')){
				hasMeridian=true
				hasAM=true
			}
			if((Boolean)t0.endsWith('pm')){
				hasMeridian=true
				hasPM=true
			}
			Long time=null
			if(hasMeridian) t0=t0[0..-3].trim()

			try{
				if(t0.length() == 8){
					String tt=t0
					time= (new Date()).parse('HH:mm:ss',tt).getTime()
					time=getTimeToday(time)
				}else{
					time=((Date)timeToday(t0,tz)).getTime()
				}
			}catch(ignored){}

			if(hasMeridian && time){
				Date t1=new Date(time)
				Integer hr=t1.hours
				Integer min=t1.minutes
				Integer sec=t1.seconds
				Boolean twelve= hr>=12
				if(twelve && hasAM)hr -= 12
				if(!twelve && hasPM)hr += 12
				String str1="${hr}".toString()
				String str2="${min}".toString()
				//String str3="${sec}".toString()
				if(hr<10)str1=String.format('%02d',hr)
				if(min<10)str2=String.format('%02d',min)
				//if(sec<10)str3=String.format('%02d',sec)
				String str=str1+sCOLON+str2
				time=((Date)timeToday(str,tz)).getTime()
				if(sec != 0) time += sec*1000
			}
			result=time ?: lZERO
			return result
		}catch(ignored){}

//		result=(new Date()).getTime()
//		return result
	}

	if(dateOrTimeOrString instanceof Date){
		result=(Long)((Date)dateOrTimeOrString).getTime()
		return result
	}
	return lZERO
}

private String formatLocalTime(time,String format='EEE, MMM d yyyy @ h:mm:ss a z'){
	def nTime=time
	if("$time".isNumber()){
		Long ltime=(Long)time.toLong()
		if(ltime<lMSDAY)ltime=getTimeToday(ltime)
// deal with a time in sec (vs. ms)
		if(ltime<Math.round((Long)now()/dTHOUS+86400.0D*365.0D))ltime=Math.round(ltime*dTHOUS)
		nTime=new Date(ltime)
	}else if(time instanceof String){
		//get time
		nTime=new Date(stringToTime((String)time))
	}
	if(!(nTime instanceof Date)){
		return sNULL
	}
	SimpleDateFormat formatter=new SimpleDateFormat(format)
	formatter.setTimeZone((TimeZone)location.timeZone)
	return (String)formatter.format(nTime)
}

private static Map hexToColor(String hex){
	String mhex=hex!=sNULL ? hex:sZEROS
	if((Boolean)mhex.startsWith('#'))mhex=mhex.substring(1)
	if(mhex.size()!=6)mhex=sZEROS
	List<Integer> myHsl=hexToHsl(mhex)
	return [
		hue: myHsl[0],
		saturation: myHsl[1],
		level: myHsl[2],
		hex: '#'+mhex
	]
}

private static Double _hue2rgb(Double p,Double q,Double t){
	if(t<dZERO)t += dONE
	if(t>=dONE)t -= dONE
	if(t<dONE/6.0D)return p+(q-p)*6.0D*t
	if(t<dONE/2.0D)return q
	if(t<2.0D/3.0D)return p+(q-p)*(2.0D/3.0D-t)*6.0D
	return p
}

private static String hslToHex(hue,saturation,level){
	Double h=hue/360.0D
	Double s=saturation/100.0D
	Double l=level/100.0D
// argument checking for user calls
	if(h<dZERO)h=dZERO
	if(h>dONE)h=dONE
	if(s<dZERO)s=dZERO
	if(s>dONE)s=dONE
	if(l<dZERO)l=dZERO
	if(l>dONE)l=dONE

	Double r,g,b
	if(s==dZERO){
		r=g=b=l // achromatic
	}else{
		Double q=l<0.5D ? l*(dONE+s) : l+s-(l*s)
		Double p=2.0D*l-q
		r=_hue2rgb(p,q, h+dONE/3.0D)
		g=_hue2rgb(p,q,h)
		b=_hue2rgb(p,q,h-dONE/3.0D)
	}

	return sprintf('#%02X%02X%02X',Math.round(r*255.0D),Math.round(g*255.0D),Math.round(b*255.0D))
}
/*
private static Map<String,Integer> hexToRgb(String hex){
	hex=hex!=sNULL ? hex:sZEROS
	if((Boolean)hex.startsWith('#'))hex=hex.substring(1)
	if(hex.size()!=6)hex=sZEROS
	Integer r1=Integer.parseInt(hex.substring(0,2),16)
	Integer g1=Integer.parseInt(hex.substring(2,4),16)
	Integer b1=Integer.parseInt(hex.substring(4,6),16)
	return [r:r1,g:g1,b:b1]
}*/

private static List<Integer> hexToHsl(String hex){
	String mhex=hex!=sNULL ? hex:sZEROS
	if((Boolean)mhex.startsWith('#'))mhex=mhex.substring(1)
	if(mhex.size()!=6)mhex=sZEROS
	Double r=Integer.parseInt(mhex.substring(0,2),16)/255.0D
	Double g=Integer.parseInt(mhex.substring(2,4),16)/255.0D
	Double b=Integer.parseInt(mhex.substring(4,6),16)/255.0D

	Double max=Math.max(Math.max(r,g),b)
	Double min=Math.min(Math.min(r,g),b)
	Double h=dZERO
	Double s=dZERO
	Double l=(max+min)/2.0D

	if(max==min){
		h=s=dZERO // achromatic
	}else{
		Double d=max-min
		s=l>0.5D ? d/(2.0D-max-min) : d/(max+min)
		switch(max){
			case r: h=(g-b)/d+(g<b ? 6.0D:dZERO); break
			case g: h=(b-r)/d+2.0D; break
			case b: h=(r-g)/d+4.0D; break
		}
		h /= 6.0D
	}
	return [Math.round(h*360.0D).toInteger(),Math.round(s*100.0D).toInteger(),Math.round(l*100.0D).toInteger()]
}

//hubitat device ids can be the same as the location id
private Boolean isDeviceLocation(device){
	if((String)device.id.toString()==(String)location.id.toString()){
		Integer tt0=device.hubs?.size()
		if((tt0!=null?tt0:0)>0)return true
	}
	return false
}

/**							**/
/** DEBUG FUNCTIONS					**/
/**							**/

private void myDetail(Map rtD,String msg,Integer shift=-2){
	Map a=log(msg,rtD,shift,null,sWARN,true,false)
}

private Map log(message,Map rtD,Integer shift=-2,err=null,String cmd=sNULL,Boolean force=false,Boolean svLog=true){
	if(cmd=='timer'){
		return [(sM):message.toString(),(sT):now(),(sS):shift,(sE):err]
	}
	String myMsg=sNULL
	def merr=err
	Integer mshift=shift
	if(message instanceof Map){
		mshift=(Integer)message.s
		merr=message.e
		myMsg=(String)message.m+" (${elapseT((Long)message.t)}ms)".toString()
	} else myMsg=message.toString()
	String mcmd=cmd!=sNULL ? cmd:sDBG
	//shift is
	// 0 - initialize level,level set to 1
	// 1 - start of routine,level up
	// -1 - end of routine,level down
	// anything else - nothing happens
//	Integer maxLevel=4
	Integer level=rtD?.debugLevel ? (Integer)rtD.debugLevel:0
	String prefix="║"
	String prefix2="║"
//	String pad=sBLK //"░"
	//noinspection GroovyFallthrough
	switch(mshift){
		case 0:
			level=0
		case 1:
			level += 1
			prefix="╚"
			prefix2="╔"
//			pad="═"
			break
		case -1:
			level -= 1
//			pad="═"
			prefix="╔"
			prefix2="╚"
			break
	}

	if(level>0){
		prefix=prefix.padLeft(level+(mshift==-1 ? 1:0),"║")
		prefix2=prefix2.padLeft(level+(mshift==-1 ? 1:0),"║")
	}

	rtD.debugLevel=level
	Boolean hasErr=(merr!=null && !!merr)

	if(svLog && rtD!=null && rtD instanceof Map && rtD.logs instanceof List){
		myMsg=myMsg.replaceAll(/(\r\n|\r|\n|\\r\\n|\\r|\\n)+/,"\r")
		if(myMsg.size()>1024){
			myMsg=myMsg[0..1023]+'...[TRUNCATED]'
		}
		List msgs=!hasErr ? myMsg.tokenize("\r"):[myMsg]
		for(msg in msgs){
			Boolean a=((List)rtD.logs).push([(sO): elapseT((Long)rtD.timestamp),(sP): prefix2,(sM): msg+(hasErr ? " $merr".toString() : sBLK),(sC): mcmd])
		}
	}
	String myPad=sSPC
	switch(mcmd){
		case sDBG:
			break
		case 'info':
			myPad=" ░"
			break
		case 'trace':
		case sERROR:
		case sWARN:
			myPad="░"
			break
		default:
			break
	}
	if(hasErr) myMsg += "$merr".toString()
	if((mcmd in [sERROR,sWARN]) || hasErr || force || !svLog || (Boolean)rtD.logsToHE || (Boolean)rtD.eric)log."$mcmd" myPad+prefix+sSPC+myMsg
	return [:]
}

private void info(message,Map rtD,Integer shift=-2,err=null){ Map a=log(message,rtD,shift,err,'info')}
private void trace(message,Map rtD,Integer shift=-2,err=null){ Map a=log(message,rtD,shift,err,'trace')}
private void debug(message,Map rtD,Integer shift=-2,err=null){ Map a=log(message,rtD,shift,err,sDBG)}
private void warn(message,Map rtD,Integer shift=-2,err=null){ Map a=log(message,rtD,shift,err,sWARN)}
private void error(message,Map rtD,Integer shift=-2,err=null){
	Map a=log(message,rtD,shift,err,sERROR)
	String aa=sNULL
	String bb=sNULL
	try{
		if(err){
			aa=getExceptionMessageWithLine(err)
			bb=getStackTrace(err)
		}
	}catch(ignored){}
	if(aa || bb) log.error "webCoRE exception: "+aa +" \n"+bb
}

private Map timer(String message,Map rtD,Integer shift=-2,err=null){ log(message,rtD,shift,err,'timer')}

private void tracePoint(Map rtD,String objectId,Long duration,value){
	if(objectId!=sNULL && rtD!=null && (Map)rtD.trace!=null){
		rtD.trace.points[objectId]=[(sO): elapseT((Long)rtD.trace.t)-duration,(sD): duration,(sV):value]
	}else{
		error "Invalid object ID $objectID for trace point",rtD
	}
}

@Field static final List<String> weekDaysFLD=[
	'Sunday',
	'Monday',
	'Tuesday',
	'Wednesday',
	'Thursday',
	'Friday',
	'Saturday'
]

@Field static final List<String> yearMonthsFLD=[
	'',
	'January',
	'February',
	'March',
	'April',
	'May',
	'June',
	'July',
	'August',
	'September',
	'October',
	'November',
	'December'
]

@Field static Map svSunTFLD

private void initSunriseAndSunset(Map rtD){
	Map t0=svSunTFLD
	Long t=now()
	if(t0!=null){
		if(t<(Long)t0.nextM){ // && t<(Long)t0.sunrise && t<(Long)t0.sunset){
			rtD.sunTimes=[:]+t0
		}else{ t0=null; svSunTFLD=null }
	}
	if(t0==null){
		Map sunTimes=app.getSunriseAndSunset()
		if(sunTimes.sunrise==null){
			warn 'Actual sunrise and sunset times are unavailable; please reset the location for your hub',rtD
			Long t1=getMidnightTime()
			sunTimes.sunrise=new Date(Math.round(t1+7.0D*dMSECHR))
			sunTimes.sunset=new Date(Math.round(t1+19.0D*dMSECHR))
			t=lZERO
		}
		Long a= (Long)((Date)sunTimes.sunrise).getTime()
		Long b= (Long)((Date)sunTimes.sunset).getTime()
		Long nmnght= getNextMidnightTime()
		Long c,d= lZERO
		Long a1// = a
		Long b1// = b
		Boolean good=true
		try{
			a1=(Long)((Date)todaysSunrise).getTime() // requires FW 2.2.3.132 or later
			b1=(Long)((Date)todaysSunset).getTime()
			c=(Long)((Date)tomorrowsSunrise).getTime()
			d=(Long)((Date)tomorrowsSunset).getTime()
		}catch(ignored){
			good=false
			Boolean agtr= a>nmnght
			Boolean bgtr= b>nmnght
			Long srSkew= getSkew(a,'Sunrise')
			Long ssSkew= getSkew(b,'Sunset')
			a1= agtr ? Math.round(a-dMSDAY-srSkew) : a
			b1= bgtr ? Math.round(b-dMSDAY-ssSkew) : b
			c= agtr ? a : Math.round(a+dMSDAY+srSkew)
			d= bgtr ? b : Math.round(b+dMSDAY+ssSkew)
		}
		Long c1=Math.round(c-dMSDAY)
		Long d1=Math.round(d-dMSDAY)
		t0=[
			sunrise: a,
			sunset:b,
			todayssunrise: a1,
			calcsunrise: (a>c1 ? a : c1),
			todayssunset:b1,
			calcsunset:(b>d1 ? b : d1),
			tomorrowssunrise: c,
			tomorrowssunset:d,
			updated: t,
			good: good,
			nextM: nmnght
		]
		if(!good) warn 'Please update HE firmware to improve time handling',rtD
		rtD.sunTimes=t0
		if(t!=lZERO){
			svSunTFLD=t0
			mb()
			if(eric())log.debug "updating global sunrise ${t0}"
		}
	}
//	rtD.sunrise=(Long)rtD.sunTimes.sunrise
//	rtD.sunset=(Long)rtD.sunTimes.sunset
}

private Long getSunriseTime(Map rtD){
	initSunriseAndSunset(rtD)
	//return Math.max((Long)rtD.sunTimes.sunrise,(Long)rtD.sunTimes.calcsunrise) + 10L
	return (Long)rtD.sunTimes.sunrise
}

private Long getSunsetTime(Map rtD){
	initSunriseAndSunset(rtD)
	//return Math.max((Long)rtD.sunTimes.sunset, (Long)rtD.sunTimes.calcsunset) + 10L
	return (Long)rtD.sunTimes.sunset
}

private Long getNextSunriseTime(Map rtD){
	initSunriseAndSunset(rtD)
	return (Long)rtD.sunTimes.tomorrowssunrise
}

private Long getNextSunsetTime(Map rtD){
	initSunriseAndSunset(rtD)
	return (Long)rtD.sunTimes.tomorrowssunset
}

// This is trying to ensure we don't fire sunsets or sunrises twice in same day by ensuring we fire a bit later than actual sunrise or sunset
Long getSkew(Long t4,String ttyp){
	Date t1=new Date(t4)
	Integer curMon=t1.month
	curMon=(BigDecimal)location.latitude>0 ? curMon: ((curMon+6)%12) // normalize for southern hemisphere
	Integer day=t1.date

	Integer addr
	Boolean shorteningDays= (curMon==5 && day > 20) || (curMon > 5 && !(curMon==11 && day > 20))

	if( (shorteningDays && ttyp=='Sunset') || (!shorteningDays && ttyp=='Sunrise') ) addr=1000 // minimize skew when sunrise or sunset moving earlier in day
	else{
		Integer t2=Math.abs((BigDecimal)location.latitude).toInteger()
		Integer t3=curMon%6
		Integer t5=(Integer)Math.round(t3*(365.0D/12.0D)+day).toInteger() // days into period
		addr=Math.round((t5>37 && t5<(182-37) ? t2*2.8D:t2*1.9D)*dTHOUS).toInteger()
	}
	return addr.toLong()
}

private Long getMidnightTime(){
	return (Long)((Date)timeToday('00:00',(TimeZone)location.timeZone)).getTime()
}

private Long getNextMidnightTime(){
	return (Long)((Date)timeTodayAfter('23:59','00:00',(TimeZone)location.timeZone)).getTime()
}

private Long getNoonTime(){
	return (Long)((Date)timeToday('12:00',(TimeZone)location.timeZone)).getTime()
}

private Long getNextNoonTime(){
	return (Long)((Date)timeTodayAfter('23:59','12:00',(TimeZone)location.timeZone)).getTime()
}

private void getLocalVariables(Map rtD,List<Map> vars,Map atomState){
	rtD.localVars=[:]
	Map<String,Object> values=(Map<String,Object>)atomState.vars
	for(Map var in vars){
//		if(eric())log.debug "getLocalVariables ${var}"
		String t0=(String)var.t
		def t1=values[(String)var.n]
		Map<String,Object> variable=[(sT):t0,(sV):var.v!=null ? var.v:((Boolean)t0.endsWith(sRB) ? (t1 instanceof Map ? t1:[:]):cast(rtD,t1,t0)),f: !!var.v] //f means fixed value - we won't save this to the state
		if(var.v!=null && (String)var.a==sS && !(Boolean)t0.endsWith(sRB)) variable.v=evaluateExpression(rtD,(Map)evaluateOperand(rtD,null,(Map)var.v),t0).v
		rtD.localVars[(String)var.n]=variable
	}
//	if(eric())log.debug "getLocalVariables ${rtD.localVars}"
}

private Map<String,LinkedHashMap> getSystemVariablesAndValues(Map rtD){
	LinkedHashMap<String,LinkedHashMap> result=getSystemVariables()
	LinkedHashMap<String,LinkedHashMap> t0=(LinkedHashMap)rtD.cachePersist
	rtD.args=t0[sDOLARGS]
	for(variable in result){
		String keyt1=(String)variable.key
		if(variable.value.d!=null && (Boolean)variable.value.d) variable.value.v=getSystemVariableValue(rtD,keyt1)
		else if(t0[keyt1]!=null)variable.value.v=t0[keyt1].v
	}
	return result.sort { (String)it.key }
}

// UI will not display anything that starts with $current or $previous; variables without d: true will not display variable value
private static LinkedHashMap<String,LinkedHashMap> getSystemVariables(){
	LinkedHashMap dynT=[(sT):sDYN,(sD):true]
	LinkedHashMap strT=[(sT):sSTR,(sD):true]
	LinkedHashMap strN=[(sT):sSTR,(sV):null]
	LinkedHashMap intT=[(sT):sINT,(sD):true]
	LinkedHashMap dtimeT=[(sT):sDTIME,(sD):true]
	LinkedHashMap t=[:]
	String shsm=sDLR+sHSMSTS
	return [
		(sDOLARGS):t+dynT,
		(sDOLJSON):t+dynT,
		'$places':t+dynT,
		(sDOLRESP):t+dynT,
		'$nfl':t+dynT,
		'$weather':t+dynT,
		'$incidents':t+dynT,
		'$hsmTripped':[(sT):sBOOLN,(sD):true],
		(shsm):t+strT,
		(sHTTPCONTENT):t+strN,
		(sHTTPSTSCODE):[(sT):sINT,(sV):null],
		(sHTTPSTSOK):[(sT):sBOOLN,(sV):null],
		(sCURATTR):t+strN,
		(sCURDESC):t+strN,
		(sCURDATE):[(sT):sDTIME,(sV):null],
		(sCURDELAY):[(sT):sINT,(sV):null],
		(sCURDEV):[(sT):sDEV,(sV):null],
		(sCURDEVINDX):[(sT):sINT,(sV):null],
		(sCURPHYS):[(sT):sBOOLN,(sV):null],
//		'$currentEventReceived':[(sT):sDTIME,(sV):null],
		(sCURVALUE):[(sT):sDYN,(sV):null],
		(sCURUNIT):t+strN,
//		'$currentState':t+strN,
//		'$currentStateDuration':t+strN,
//		'$currentStateSince':[(sT):sDTIME,(sV):null],
//		'$nextScheduledTime':[(sT):sDTIME,(sV):null],
		'$name':t+strT,
		'$state':t+strT,
		(sDLLRDEVICE):[(sT):sDEV,(sV):null],
		(sDLLRDEVS):[(sT):sDEV,(sV):null],
		(sDLLRINDX):[(sT):sDEC,(sV):null],
		(sIFTTTSTSCODE):[(sT):sINT,(sV):null],
		(sIFTTTSTSOK):[(sT):sBOOLN,(sV):null],
		'$location':[(sT):sDEV,(sV):null],
		'$locationMode':t+strT,
		'$localNow':t+dtimeT,
		'$now':t+dtimeT,
		'$hour':t+intT,
		'$hour24':t+intT,
		'$minute':t+intT,
		'$second':t+intT,
		'$zipCode':t+strT,
		'$latitude':t+strT,
		'$longitude':t+strT,
		'$meridian':t+strT,
		'$meridianWithDots':t+strT,
		'$day':t+intT,
		'$dayOfWeek':t+intT,
		'$dayOfWeekName':t+strT,
		'$month':t+intT,
		'$monthName':t+strT,
		'$year':t+intT,
		'$midnight':t+dtimeT,
		'$noon':t+dtimeT,
		'$sunrise':t+dtimeT,
		'$sunset':t+dtimeT,
		'$nextMidnight':t+dtimeT,
		'$nextNoon':t+dtimeT,
		'$nextSunrise':t+dtimeT,
		'$nextSunset':t+dtimeT,
		'$time':t+strT,
		'$time24':t+strT,
		'$utc':t+dtimeT,
		'$mediaId':t+strT,
		'$mediaUrl':t+strT,
		'$mediaType':t+strT,
		'$mediaSize':t+intT,
		(sPEVATTR):t+strN,
		(sPEVDESC):t+strN,
		(sPEVDATE):[(sT):sDTIME,(sV):null],
		(sPEVDELAY):[(sT):sINT,(sV):null],
		(sPEVDEV):[(sT):sDEV,(sV):null],
		(sPEVDEVINDX):[(sT):sINT,(sV):null],
		(sPEVPHYS):[(sT):sBOOLN,(sV):null],
//		'$previousEventExecutionTime':[(sT):sINT,(sV):null],
//		'$previousEventReceived':[(sT):sDTIME,(sV):null],
		(sPEVVALUE):[(sT):sDYN,(sV):null],
		(sPEVUNIT):t+strN,
//		'$previousState':t+strN,
//		'$previousStateDuration':t+strN,
//		'$previousStateSince':[(sT):sDTIME,(sV):null],
		'$random':[(sT):sDEC,(sD):true],
		'$randomColor':t+strT,
		'$randomColorName':t+strT,
		'$randomLevel':t+intT,
		'$randomSaturation':t+intT,
		'$randomHue':t+intT,
		'$temperatureScale':t+strT,
		'$tzName':t+strT,
		'$tzId':t+strT,
		'$tzOffset':t+intT,
		'$version':t+strT,
		'$versionH':t+strT
	] as LinkedHashMap<String, LinkedHashMap>
}

private getSystemVariableValue(Map rtD,String name){
	String shsm=sDLR+sHSMSTS
	switch(name){
	case sDOLARGS: return "${rtD.args}".toString()
	case sDOLJSON: return "${rtD.json}".toString()
	case '$places': return "${rtD.settings?.places}".toString()
	case sDOLRESP: return "${rtD.response}".toString()
	case '$weather': return "${rtD.weather}".toString()
	case '$nfl': return "${rtD.nfl}".toString()
	case '$incidents': return "${rtD.incidents}".toString()
	case '$hsmTripped': return rtD.incidents instanceof List && ((List)rtD.incidents).size()>0
	case (shsm): return (String)location.hsmStatus
	case '$mediaId': return rtD.mediaId
	case '$mediaUrl': return (String)rtD.mediaUrl
	case '$mediaType': return (String)rtD.mediaType
	case '$mediaSize': return (rtD.mediaData!=null ? (Integer)rtD.mediaData.size():0)
	case '$name': return (String)app.label
	case '$state': return (String)rtD.state?.new
	case '$tzName': return (String)((TimeZone)location.timeZone).displayName
	case '$tzId': return (String)((TimeZone)location.timeZone).getID()
	case '$tzOffset': return ((TimeZone)location.timeZone).rawOffset
	case '$version': return sVER
	case '$versionH': return sHVER
	case '$localNow': //return (Long)localTime()
	case '$now':
	case '$utc': return (Long)now()
	case '$hour': Integer h=localDate().hours; return (h==0 ? 12:(h>12 ? h-12:h))
	case '$hour24': return localDate().hours
	case '$minute': return localDate().minutes
	case '$second': return localDate().seconds
	case '$zipCode': return location.zipCode
	case '$latitude': return ((BigDecimal)location.latitude).toString()
	case '$longitude': return ((BigDecimal)location.longitude).toString()
	case '$meridian': Integer h=localDate().hours; return (h<12 ? 'AM' : 'PM')
	case '$meridianWithDots': Integer h=localDate().hours; return (h<12 ? 'A.M.' : 'P.M.')
	case '$day': return localDate().date
	case '$dayOfWeek': return localDate().day
	case '$dayOfWeekName': return weekDaysFLD[localDate().day]
	case '$month': return localDate().month+1
	case '$monthName': return yearMonthsFLD[localDate().month+1]
	case '$year': return localDate().year+1900
	case '$midnight': return getMidnightTime()
	case '$noon': return getNoonTime()
	case '$sunrise': return getSunriseTime(rtD)
	case '$sunset': return getSunsetTime(rtD)
	case '$nextMidnight': return getNextMidnightTime()
	case '$nextNoon': return getNextNoonTime()
	case '$nextSunrise': return getNextSunriseTime(rtD)
	case '$nextSunset': return getNextSunsetTime(rtD)
	case '$time': Date t=localDate(); Integer h=t.hours; Integer m=t.minutes; return ((h==0 ? 12:(h>12 ? h-12:h))+sCOLON+(m<10 ? "0$m":"$m")+sSPC+(h <12 ? 'A.M.' : 'P.M.')).toString()
	case '$time24': Date t=localDate(); Integer h=t.hours; Integer m=t.minutes; return (h+sCOLON+(m<10 ? "0$m":"$m")).toString()
	case '$random':
		def tresult=getRandomValue(rtD,name)
		Double result
		if(tresult!=null)result=(Double)tresult
		else{
			result=(Double)Math.random()
			setRandomValue(rtD,name,result)
		}
		return result
	case '$randomColor':
		def tresult=getRandomValue(rtD,name)
		String result
		if(tresult!=null)result=(String)tresult
		else{
			result=(String)getRandomColor().rgb
			setRandomValue(rtD,name,result)
		}
		return result
	case '$randomColorName':
		def tresult=getRandomValue(rtD,name)
		String result
		if(tresult!=null)result=(String)tresult
		else{
			result=(String)getRandomColor().name
			setRandomValue(rtD,name,result)
		}
		return result
	case '$randomLevel':
		def tresult=getRandomValue(rtD,name)
		Integer result
		if(tresult!=null)result=(Integer)tresult
		else{
			result= Math.round(100.0D*Math.random()).toInteger()
			setRandomValue(rtD,name,result)
		}
		return result
	case '$randomSaturation':
		def tresult=getRandomValue(rtD,name)
		Integer result
		if(tresult!=null)result=(Integer)tresult
		else{
			result= Math.round(50.0D+50.0D*Math.random()).toInteger()
			setRandomValue(rtD,name,result)
		}
		return result
	case '$randomHue':
		def tresult=getRandomValue(rtD,name)
		Integer result
		if(tresult!=null)result=(Integer)tresult
		else{
			result= Math.round(360.0D*Math.random()).toInteger()
			setRandomValue(rtD,name,result)
		}
		return result
	case '$locationMode':return (String)location.getMode()
	case '$temperatureScale':return (String)location.getTemperatureScale()
	}
	return null
}

private static void setSystemVariableValue(Map rtD,String name,value,Boolean cachePersist=true){
	Map<String,Object> var=(Map)rtD.systemVars[name]
	if(var==null)return
	if(cachePersist){
		if(name in [
			sDOLARGS,
			sHTTPCONTENT,
			sHTTPSTSCODE,
			sHTTPSTSOK,
			sIFTTTSTSCODE,
			sIFTTTSTSOK]){

			LinkedHashMap<String,LinkedHashMap> t0=(LinkedHashMap)rtD.cachePersist
			t0[name]=[:]+var+[(sV):value]
			rtD.cachePersist=t0
		}
	}
	if(var.d!=null)return
	rtD.systemVars[name].v=value
}

private static getRandomValue(Map rtD,String name){
	return rtD.temp.randoms[name]
}

private static void setRandomValue(Map rtD,String name,value){
	rtD.temp.randoms[name]=value
}

private static void resetRandomValues(Map rtD){
	rtD.temp=[randoms:[:]]
}

private Map getColorByName(String name){
	Map t1=getColors().find{ (String)it.name==name }
	return t1
}

private Map getRandomColor(){
	Integer random=Math.round(Math.random()*(getColors().size()-1)*dONE).toInteger()
	Map t1=getColors()[random]
	return t1
}

private Class HubActionClass(){
	return 'hubitat.device.HubAction' as Class
}

private Class HubProtocolClass(){
	return 'hubitat.device.Protocol' as Class
}

/*private Boolean isHubitat(){
	return hubUID!=sNULL
}*/

@Field static Map<String,Map> theAttributesFLD

//uses i,p,t,m
private Map<String,Map<String,Object>> Attributes(){
	Map result=theAttributesFLD
	if(result==null){
		theAttributesFLD=(Map)parent.getChildAttributes()
	}
	return theAttributesFLD
}

@Field static Map<String,Map> theComparisonsFLD

//uses p,t
private Map<String,Map<String,Map<String,Object>>> Comparisons(){
	Map result=theComparisonsFLD
	if(result==null){
		theComparisonsFLD=(Map)parent.getChildComparisons()
	}
	return theComparisonsFLD
}

@Field static Map<String,Map> theVirtCommandsFLD

//uses o (override phys command),a (aggregate commands)
private Map<String,Map<String,Object>> VirtualCommands(){
	Map result=theVirtCommandsFLD
	if(result==null){
		theVirtCommandsFLD=(Map)parent.getChildVirtCommands()
	}
	return theVirtCommandsFLD
}

//uses c and r
// the command r: is replaced with command c.
// If the VirtualCommand c exists and has o: true we will use that virtual command; otherwise it will be replaced with a physical command
@Field static final Map CommandsOverrides=[
		push:[c:"push",	s:null,r:"pushMomentary"],
		flash:[c:"flash",	s:null,r:"flashNative"] //flash native command conflicts with flash emulated command. Also needs "o" option on command described later
]

@Field static Map<String,Map> theVirtDevicesFLD

//uses ac,o
private Map<String,Map<String,Object>> VirtualDevices(){
	Map result=theVirtDevicesFLD
	if(result==null){
		theVirtDevicesFLD=(Map)parent.getChildVirtDevices()
	}
	return theVirtDevicesFLD
}

@Field static Map<String,Map> thePhysCommandsFLD

//uses a,v
private Map<String,Map<String,Object>> PhysicalCommands(){
	Map result=thePhysCommandsFLD
	if(result==null){
		thePhysCommandsFLD=(Map)parent.getChildCommands()
	}
	return thePhysCommandsFLD
}

@Field static List<Map> theColorsFLD

private List<Map<String,Object>> getColors(){
	List result=theColorsFLD
	if(result==null){
		theColorsFLD=(List)parent.getColors()
	}
	return theColorsFLD
}

private static String sectionTitleStr(String title)	{ return '<h3>'+title+'</h3>' }
private static String inputTitleStr(String title)	{ return '<u>'+title+'</u>' }
//private static String pageTitleStr(String title)	{ return '<h1>'+title+'</h1>' }
//private static String paraTitleStr(String title)	{ return '<b>'+title+'</b>' }

private static String imgTitle(String imgSrc,String titleStr,String color=sNULL,Integer imgWidth=30,Integer imgHeight=0){
	String imgStyle=sBLK
	String myImgSrc='https://raw.githubusercontent.com/ady624/webCoRE/master/resources/icons/'+imgSrc
	imgStyle += imgWidth>0 ? 'width: '+imgWidth.toString()+'px !important;':sBLK
	imgStyle += imgHeight>0 ? imgWidth!=0 ? sSPC:sBLK+'height:'+imgHeight.toString()+'px !important;':sBLK
	if(color!=sNULL){ return """<div style="color: ${color}; font-weight:bold;"><img style="${imgStyle}" src="${myImgSrc}"> ${titleStr}</img></div>""".toString() }
	else{ return """<img style="${imgStyle}" src="${myImgSrc}"> ${titleStr}</img>""".toString() }
}

static String myObj(obj){
	if(obj instanceof String){return 'String'}
	else if(obj instanceof Map){return 'Map'}
	else if(obj instanceof List){return 'List'}
	else if(obj instanceof ArrayList){return 'ArrayList'}
	else if(obj instanceof Integer){return 'Int'}
	else if(obj instanceof BigInteger){return 'BigInt'}
	else if(obj instanceof Long){return 'Long'}
	else if(obj instanceof Boolean){return 'Bool'}
	else if(obj instanceof BigDecimal){return 'BigDec'}
	else if(obj instanceof Float){return 'Float'}
	else if(obj instanceof Byte){return 'Byte'}
	else{ return 'unknown'}
}

private static Boolean isWcDev(String dev){
	return (dev && dev.size()==34 && (Boolean)dev.startsWith(sCOLON) && (Boolean)dev.endsWith(sCOLON))
}

@SuppressWarnings('GroovyAssignabilityCheck')
Map<String,Object> fixHeGType(Boolean toHubV, String typ, v, String dtyp){
	Map ret=[:]
	def myv=v
	if(toHubV){ // from webcore(9 types) -> global(5 types + 3 overloads + sDYN becomes sSTR)
		//noinspection GroovyFallthrough
		switch(typ){
			case sINT:
				ret=[(sINT):v]
				break
			case sBOOLN:
				ret=[(sBOOLN):v]
				break
			case sDEC:
				ret=['bigdecimal':v]
				break
			case sDEV:
				// HE this is a List<String> -> String of words separated by a space (can split())
				List<String> dL= v instanceof List ? (List<String>)v : (v ? (List<String>)[v]:[])
				String res=sNULL
				Boolean ok=true
				dL.each{ String it->
					if(ok && isWcDev(it)){
						res= res ? res+sSPC+it : it
					} else ok=false
				}
				if(ok){
					ret=[(sSTR):res]
					break
				}
			case sDYN:
			case sSTR:
				ret=[(sSTR):v]
				break
			case sTIME:
				if(eric())log.warn "got time $v"
				Long aaa= ("$v".isNumber()) ? v as Long : null
				if(aaa!=null){
					if(aaa<lMSDAY && aaa>=0L){
						Long t0=getMidnightTime()
						Long aa=t0+aaa
						TimeZone tz=(TimeZone)location.timeZone
						myv=Math.round(aa+(tz.getOffset(t0)-tz.getOffset(aa)))
						if(eric())log.warn "extended midnight time by $aaa"
					}else{
						Date t1=new Date(aaa)
						Long t2=Math.round((t1.hours*3600+t1.minutes*60+t1.seconds)*1000.0D)
						myv=t2
						if(eric())log.warn "strange time $aaa new myv is $myv"
					}
				} else if(eric()) warn "trying to convert nonnumber time"
			case sDATE:
			case sDTIME: //@@
				//if(eric())log.warn "found myv is $myv"
				Date nTime=new Date((Long)myv)
				/*TimeZone aa=(TimeZone)location.timeZone
				Boolean a= aa.inDaylightTime(nTime)
				if(eric())log.warn "found inDaylight $a"
				if(eric())log.warn "found current offset is ${aa.getOffset(now())}"
				if(eric())log.warn "found rawoffset is ${aa.rawOffset}"*/
				String format="yyyy-MM-dd'T'HH:mm:ss.sssXX"
				SimpleDateFormat formatter=new SimpleDateFormat(format)
				formatter.setTimeZone((TimeZone)location.timeZone)
				String tt=(String) formatter.format(nTime)
				if(eric())log.warn "found time tt is $tt"
				String[] t1=tt.split('T')

				if (typ==sDATE){
					// comes in long format should be string -> 2021-10-13T99:99:99:999-9999
					String t2=t1[0]+'T99:99:99:999-9999'
					ret=[(sDTIME): t2]
					break
				}
				if (typ==sTIME){
					//comes in long format should be string -> 9999-99-99T14:25:09.009-0700
					String t2='9999-99-99T'+t1[1]
					ret=[(sDTIME): t2]
					break
				}
				//	if (typ==sDTIME){
				// this comes in as a long, needs to be string -> 2021-10-13T14:25:09.009-0700
				ret=[(sDTIME): tt]
				break
				//	}
		}
	}else{ // from global(5 types + 3 overloads ) -> to webcore(8 (cannot restore sDYN)
		switch(typ){
			case sINT:
				ret=[(sINT):v]
				break
			case sBOOLN:
				ret=[(sBOOLN):v]
				break
				// these match
			case 'bigdecimal':
				ret=[(sDEC):v]
				break
			case sSTR:
				// if (dtyp==sDEV)
				List<String> dvL=[]
				Boolean ok=true
				String[] t1=((String)v).split(sSPC)
				t1.each{
					// sDEV is a string in global, need to detect if it is really devices :xxxxx:
					if(ok && isWcDev(it)){
						dvL.push(it)
					} else ok=false
				}
				if(ok){ ret=[(sDEV):dvL]}
				else ret=[(sSTR):v]
				break
				// cannot really return a string to dynamic type here res=sDYN
			case sDTIME: // global times: everything is datetime -> these come in as a string and needs to be a long of appropriate type
				String iD=v
				String mtyp=sDTIME
				String res=v
				if(iD.endsWith("9999") || iD.startsWith("9999")){
					Date nTime=new Date()
					String format="yyyy-MM-dd'T'HH:mm:ss.sssXX"
					SimpleDateFormat formatter=new SimpleDateFormat(format)
					formatter.setTimeZone((TimeZone)location.timeZone)
					String tt= (String)formatter.format(nTime)
					String[] mystart=tt.split('T')

					String[] t1= iD.split('T')

					if(iD.endsWith("9999")){
						mtyp=sDATE
						res= t1[0]+'T'+mystart[1] // 00:15:00.000'+myend //'-9999'
					} else if(iD.startsWith("9999")){
						mtyp=sTIME
						// we are ignoring the -0000 offset at end and using our current one
						String withOutEnd=t1[1][0..-6]
						String myend=tt[-5..-1]
						//if(eric())log.warn "tt: ${tt} myend: ${myend} iD: ${iD} mystart: ${mystart} withOutEnd: ${withOutEnd}"
						res= mystart[0]+'T'+withOutEnd+myend
						//res= mystart[0]+'T'+t1[1]
					}
				}
				Date tt1=(Date)toDateTime(res)
				Long lres=tt1.getTime()
				if(mtyp==sTIME){
					Date m1=new Date(lres)
					Long m2=Math.round((m1.hours*3600+m1.minutes*60+m1.seconds)*1000.0D)
					//if(eric())log.warn "fixing $res $lres to $m2"
					lres=m2
				}
				//if(eric())log.warn "returning $lres"
				ret=[(mtyp):lres]
		}
	}
	return ret
}

private static String md5(String md5){
	MessageDigest md= MessageDigest.getInstance('MD5')
	byte[] array=md.digest(md5.getBytes())
	String result=sBLK
	Integer l=array.size()
	for(Integer i=0; i<l; ++i){
		result += Integer.toHexString((array[i] & 0xFF)| 0x100).substring(1,3)
	}
	return result
}

static void clearHashMap(String wName){
	theHashMapVFLD[wName]=[:]
	theHashMapVFLD=theHashMapVFLD
}

private String hashId(id, Boolean updateCache=true){
	//enabled hash caching for faster processing
	String result
	String myId=id.toString()
	String wName= parent ? parent.id.toString() : app.id.toString()
	if(theHashMapVFLD[wName]==null){ theHashMapVFLD[wName]= [:]; theHashMapVFLD=theHashMapVFLD }
	result=(String)theHashMapVFLD[wName][myId]
	if(result==sNULL){
		result=sCOLON+md5('core.' + myId)+sCOLON
		theHashMapVFLD[wName][myId]=result
		theHashMapVFLD=theHashMapVFLD
		mb()
	}
	return result
}

@Field static Semaphore theMBLockFLD=new Semaphore(0)

// Memory Barrier
static void mb(String meth=sNULL){
	if((Boolean)theMBLockFLD.tryAcquire()){
		theMBLockFLD.release()
	}
}
