package org.bitbuckets.frc2015.autonomous;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoProgramGenerator {
	
	/**
	 * Gets the current directory
	 */
    static File dir = new File(".");

    /**
     * Sets up a textFilter so we can grab only the .txt files
     */
    static FilenameFilter textFilter = new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(".txt");
        }
    };
    
    /**
     * 
     * @param scripts
     * @return
     * @throws IOException
     */
    public static ArrayList<AutoProgram> generateAutoPrograms() throws IOException{
    	File[] scripts = getFiles();
    	ArrayList<AutoProgram> programs = new ArrayList<AutoProgram>();
    	for(File script: scripts){
    		programs.add(new AutoProgram(script));
    	}
    	return programs;
    }
    
    /**
     * 
     * @return
     * @throws IOException
     */
    public static File[] getFiles() throws IOException{
	    File[] autoScripts = dir.listFiles(textFilter);
	    for (File script : autoScripts) {
	        System.out.println("file: " + script.getCanonicalPath());
	    }
	    return autoScripts;
    }
    

}
