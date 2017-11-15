import gnu.io.*
String port ="/dev/ttyUSB0";
Integer baudRate = 9600;
// Look for the serial port holding device in the DeviceManager
// if it is missing, call this lambda to create it
def dev = DeviceManager.getSpecificDevice( "aslSerialPort",{
	//If the device does not exist, prompt for the connection
	ScriptingEngine.gitScriptRun(
	       "https://github.com/madhephaestus/DSLForPollice.git", // git location of the library
            "DSLTest.groovy" , // file to load
            null// no parameters 
            );
	NRSerialPort serial = (NRSerialPort) ScriptingEngine.gitScriptRun(
	            "https://github.com/madhephaestus/DSLForPollice.git", // git location of the library
	            "TestLang.asl" , // file to load
	            [port,baudRate]as ArrayList<Object>
	            );
     //Create a device to be managed by the UI
     //Device can be disconnected and reprogrammed 
     //o device can stay persistant and be communicated with. 
	NonBowlerDevice d = new NonBowlerDevice(){
		public NRSerialPort mySerial = serial;
		public DataInputStream ins = new DataInputStream(serial.getInputStream());
		public DataOutputStream outs = new DataOutputStream(serial.getOutputStream());
		/**
		 * This method tells the connection object to disconnect its pipes and close out the connection. Once this is called, it is safe to remove your device.
		 */
		
		public void disconnectDeviceImp(){
			serial.disconnect()
		}
		
		/**
		 * Connect device imp.
		 *
		 * @return true, if successful
		 */
		public  boolean connectDeviceImp(){
			return serial.connect()
		}
		
		/**
		 * Gets the namespaces imp.
		 *
		 * @return the namespaces imp
		 */
		public  ArrayList<String>  getNamespacesImp(){
			return []
		}
	}
	d.connect(); // Connect to it.
	return d
})


//Read a byte from the arduino
byte b = dev.ins.read();
//Write a byte to the arduino
dev.outs.write(b);
