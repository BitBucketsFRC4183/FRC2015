package org.bitbuckets.frc2015.autonomous;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.util.FileManager;
import org.bitbuckets.frc2015.util.FileManager.FileType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoProgramGenerator {


    /**
     * generateAutoPrograms() reads the files in the current directory and returns them in an ArrayList of AutoPrograms
     * @return 
     *
     * @return ArrayList<AutoProgram> of AutoProgram objects
     * @throws IOException
     */
    public static void generateAutoPrograms() throws IOException {
        ArrayList<File> scripts = FileManager.getFilesOfType(FileType.SCRIPT);
        ArrayList<AutoProgram> programs = new ArrayList<AutoProgram>();
        
        for (File script : scripts) {
            programs.add(new AutoProgram(script));
        }
        
        for (AutoProgram a : programs) {
            Robot.autoChooser.addObject(a.name, a);
        }
        SmartDashboard.putData("Autonomous code chooser", Robot.autoChooser);
    }




//    public static boolean changeDir(String directoryPathName){
//
//    	dir = new File(directoryPathName);
//    	return true;
//    }


}
