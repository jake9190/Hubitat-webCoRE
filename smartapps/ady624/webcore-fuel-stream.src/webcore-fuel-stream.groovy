import java.text.SimpleDateFormat

private static String handle() { return "webCoRE" }
public static String version() { return "v0.3.113.20210203" }
public static String HEversion() { return "v0.3.113.20211005_HE" }

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

preferences {
	page(name: "settingsPage")
}

def settingsPage(){
 	dynamicPage(name: "settingsPage", title: "Settings", uninstall: true, install: true){
		section(){
			input "maxSize", "number", title: "Max size of all fuelStream data in KB", defaultValue: 95

			def storageSize = (Integer)(state.toString().size() / 1024.0)
			paragraph("Current memory usage is ${storageSize}KB")
		}
	}
}

def installed(){
	log.debug "Installed with settings $settings"
	initialize()
}

def updated(){
	log.debug "Updated with settings $settings"
 	initialize()
}

void createStream(settings){
	state.fuelStream = [i: settings.id, c: (settings.canister ?: ""), n: settings.name, w: 1, t: getFormattedDate(new Date())]
}

def initialize(){
	unsubscribe()
	unschedule()

	if(app.id){
		def a=getFuelStreamData()
		cleanFuelStreams()
	}
}

List<Map> getFuelStreamData(){
	if(!state.fuelStreamData){
	 	state.fuelStreamData = []
	}
	return (List)state.fuelStreamData
}

void cleanFuelStreams(){
	//ensure max size is obeyed
	Double storageSize = (Integer)(state.toString().size() / 1024.0)
	Integer max = (settings.maxSize ?: 95).toInteger()

	if(storageSize > max){
		log.debug "Trim down fuel stream"
		Integer points = getFuelStreamData().size()
		Double averageSize = points > 0 ? storageSize/points : 0

		Integer pointsToRemove = averageSize > 0 ? (Integer)((storageSize - max) / averageSize) : 0
		pointsToRemove = pointsToRemove > 0 ? pointsToRemove : 0

		log.debug "Size ${storageSize}KB Points ${points} Avg $averageSize Remove $pointsToRemove"
		List<Map> toBeRemoved = getFuelStreamData().sort { it.i }.take(pointsToRemove)
		Boolean a=getFuelStreamData().removeAll(toBeRemoved)
	}

	getFuelStreamData().each {
	 	Boolean a=it.keySet().remove('t')
	}
}

def updateFuelStream(req){
//	def canister = req.c ?: ""
//	def name = req.n
	def data = req.d
//	def instance = req.i
//	def source = req.s

	getFuelStreamData().add([d: data, i: now()])

	cleanFuelStreams()
}

String getFormattedDate(Date date = new Date()){
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	format.setTimeZone(TimeZone.getTimeZone("UTC"))
	format.format(date)
}

Map getFuelStream(){
	(Map)state.fuelStream
}

List<Map> listFuelStreamData(){
	getFuelStreamData().collect{ it + [t: getFormattedDate(new Date((Long)it.i))]}
}

def uninstalled(){
	parent.resetFuelStreamList()
}
