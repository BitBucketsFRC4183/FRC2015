package org.bitbuckets.frc2015.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.util.FileManager;
import org.bitbuckets.frc2015.util.FileManager.FileType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AutoProgramGenerator {

    //TODO implement intelligent default programs

    /**
     * generateAutoPrograms() reads the files in the current directory and returns them in an ArrayList of AutoPrograms
     *
     * @return ArrayList<AutoProgram> of AutoProgram objects
     * @throws IOException
     */
    public static void generateAutoPrograms() {
        ArrayList<File> scripts = FileManager.getFilesOfType(FileType.SCRIPT);
        ArrayList<AutoProgram> programs = new ArrayList<AutoProgram>();

        for (File script : scripts) {
            try {
                System.out.println("Adding the script: " + script.getName());
                programs.add(new AutoProgram(script));
            } catch (IOException e) {
            }
        }

        Robot.autoChooser.addDefault("Dont Pick Me", new DefaultProgram());

        for (AutoProgram a : programs) {
            System.out.println("Adding the script " + a.name + " to the autoChooser");
            Robot.autoChooser.addObject(a.name, a);
        }

        if (RandomConstants.TESTING) {
            SmartDashboard.putData("Autonomous code chooser", Robot.autoChooser);
        }
    }


//    public static boolean changeDir(String directoryPathName){
//
//    	dir = new File(directoryPathName);
//    	return true;
//    }


}
