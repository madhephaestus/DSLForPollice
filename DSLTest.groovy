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
		String content = new Scanner(new File(code.getAbsolutePath())).useDelimiter("\\Z").next(); 
		// Generate the INO file and directory structure
		return inlineScriptRun(content,args);
	}
	
	/**
	 * This interface is for adding additional language support. 
	 * @param code the text content of the code to be executed
	 * @param args the incoming arguments as a list of objects
	 * @return the objects returned form the code that ran
	 * @throws Exception 
	 */
	public  Object inlineScriptRun(String code, ArrayList<Object> args) throws Exception{
		println "Compiling code..."+code
		return null;
	}
	
	/**
	 * Returns the shell type of this language
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

