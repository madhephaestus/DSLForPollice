import com.neuronrobotics.bowlerstudio.scripting.*

println "Loading new DSL..."

ScriptingEngine.addScriptingLanguage(new IScriptingLanguage() {
	/**
	 * This interface is for adding additional language support. 
	 * @param code file content of the code to be executed
	 * @param args the incoming arguments as a list of objects
	 * @return the objects returned form the code that ran
	 */
	public  Object inlineScriptRun(File code, ArrayList<Object> args) throws Exception{
		// Generate the INO file and directory structure
		File parent = code.getParentFile();
		String codeBase = code.getName().split("." + getFileExtenetion().get(0))[0];
		File inoDir = new File(parent.getAbsolutePath() + "/" + codeBase);
		if (!inoDir.exists()) {
			inoDir.mkdir();
		}
		File ino = new File(parent.getAbsolutePath() + "/" + codeBase + "/" + codeBase + ".ino");
		if (!ino.exists()) {
			ino.createNewFile();
		}
		//With the valid ion and parent directory created, generate code
		String text = "void setup(){\n" + 
		"\tSerial.begin(9600);\n" +
		"}\n" + 
		"void loop(){\n" 
		code.readLines().each{
		 text+="\tSerial.println(\""+ it +"\");\n"  // print the contents of the code file in the arduino loop
		}
		text+="}";
		
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(ino));
			output.write(text);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (output != null) {
				output.close();
			}
		}

		System.out.println("Pushing INO to arduino compile  and flash device: " + ino.getAbsolutePath());

		ScriptingEngine.inlineFileScriptRun(ino, args);

		return null;
	}
	
	/**
	 * This interface is for adding additional language support. 
	 * @param code the text content of the code to be executed
	 * @param args the incoming arguments as a list of objects
	 * @return the objects returned form the code that ran
	 * @throws Exception 
	 */
	public  Object inlineScriptRun(String code, ArrayList<Object> args) throws Exception{
		println "code..."+code
		return null;
	}
	
	/**
	 * Returns the HashMap key for this language
	 * @return
	 */
	public  String getShellType(){
		return "ArduingScriptingLangauge"
	}
	/**
	 * Returns the list of supported file extentions
	 * Convention is to provide just the leters that make up the file extention
	 * @return
	 */
	public  ArrayList<String> getFileExtenetion(){
		return ["asl"]
	}
	
	/**
	 * This function returns if this is a binary file or a text file
	 * @return true if the file is a text file.
	 */
	public boolean getIsTextFile(){
		return true;
	}
	
})

