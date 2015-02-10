package org.bitbuckets.frc2015.autonomous;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoProgramGenerator {

    /**
     * Gets the current directory
     */
    static File dir = new File("///TextFiles");

    /**
     * Sets up a textFilter so we can grab only the .txt files
     */
//    static FilenameFilter textFilter = new FilenameFilter() {
//        public boolean accept(File dir, String name) {
//            return name.toLowerCase().endsWith(".txt");
//        }
//    };

    //TODO check for script type

    /**
     * generateAutoPrograms() reads the files in the current directory and returns them in an ArrayList of AutoPrograms
     *
     * @return ArrayList<AutoProgram> of AutoProgram objects
     * @throws IOException
     */
    public static ArrayList<AutoProgram> generateAutoPrograms() throws IOException {
        File[] scripts = getFiles();
        ArrayList<AutoProgram> programs = new ArrayList<AutoProgram>();
        for (File script : scripts) {
            programs.add(new AutoProgram(script));
        }
        return programs;
    }

    /**
     * Returns an array of File objects containing all files matching textFilter in the current directory.
     *
     * @return
     * @throws IOException
     */
    public static File[] getFiles() throws IOException {
        File[] autoScripts = dir.listFiles(/*textFilter*/);
        if (autoScripts.length == 0){
        	SmartDashboard.putString("Is autoScripts empty?", "true");
        }
        for (File script : autoScripts) {
            System.out.println("file: " + script.getCanonicalPath());
        }
        return autoScripts;
    }




//    public static boolean changeDir(String directoryPathName){
//
//    	dir = new File(directoryPathName);
//    	return true;
//    }


}
