println "Loading new DSL..."

ScriptingEngine.addScriptingLanguage(new IScriptingLanguage {
	/**
	 * This interface is for adding additional language support. 
	 * @param code file content of the code to be executed
	 * @param args the incoming arguments as a list of objects
	 * @return the objects returned form the code that ran
	 */
	public  Object inlineScriptRun(File code, ArrayList<Object> args) throws Exception{
		
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
		return null;
	}
	
	/**
	 * Returns the shell type of this language
	 * @return
	 */
	public  String getShellType(){
		return "Arduing Scripting Langauge"
	}
	/**
	 * This function should return true is the filename provided is of a supported file extension. 
	 * This function may never be called if this language is only used internally. 
	 * @param filename the filename of the file to be executed
	 * @return true if the file extension is supported, false otherwise.
	 */
	public  boolean isSupportedFileExtenetion(String filename){
		if(	filename.toLowerCase().endsWith(".asl")||
				)
			return true;
		return false;
	}
	/**
	 * This function returns if this is a binary file or a text file
	 * @return true if the file is a text file.
	 */
	public boolean getIsTextFile(){
		return true;
	}
	
}

	
	)