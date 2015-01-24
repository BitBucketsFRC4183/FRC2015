package org.bitbuckets.frc2015.autonomous;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import org.bitbuckets.frc2015.command.*;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoProgram extends CommandGroup {
    
	//commandStr holds strings representing commands and their parameters, in the form "Seq/Par commandName 
	ArrayList<String> commandStr = new ArrayList<String>();
	
	//br reads the text from the file line by line
	BufferedReader br;
	
    public  AutoProgram() throws IOException {
    	//created BufferedReader from the file
    	br = readFile();
    	String line = null;
    	//adds each line to the ArrayList
    	while ((line = br.readLine()) != null) {
    		if(line.startsWith("//")){
    			continue;
    		}
    		commandStr.add(line);
    	}
    	//closes the buffered reader
    	br.close();
    	
    	//iterates through the lines, parses them, and adds them either as sequential or parallel commands
    	for(String commandName: commandStr){
    		addSeqOrPar(parse(commandName));
    	}
    }
    
    /**
     * Reads the file AutoProgram.txt
     * 
     * @return a BufferedReader for AutoProgram.txt
     * @throws IOException 
     */
    private BufferedReader readFile() throws IOException{
    	//get the file location
    	File dir = new File(".");
    	File fin = new File(dir.getCanonicalPath() + File.separator + "AutoProgram.txt");
    	
    	FileInputStream fis = new FileInputStream(fin);
    	 
    	//Construct BufferedReader from InputStreamReader
    	return new BufferedReader(new InputStreamReader(fis));
    }
    
    /**
     * Parses the commandName into:
     *  0:  the type of command, either "Seq" or "Par"
     *  1:  the name of the command, case sensitive. Should be passed into getCommandFromString.
     *  2+: the parameters that are fed into the command
     * 
     * @param commandName is the name of the desired command.
     * @return an ArrayList containing the 
     */
    private ArrayList<String> parse(String commandName){
    	ArrayList<String> parsed = new ArrayList<String>();
    	for(String s: commandName.split("\\s+")){
    		if(s.contains("//")){
    			parsed.add(s.substring(0, s.indexOf("//")));
    			break;
    		}
    		parsed.add(s);
    	}
    	return parsed;
    }
    
    /**
     * addSeqOrPar adds a command of a name and type.
     * 
     * The parsing is as follows:
     *  0:  the type of command, either "Seq" or "Par"
     *  1:  the name of the command, case sensitive. Should be passed into getCommandFromString.
     *  2+: the parameters that are fed into the command
     * 
     * @param parsedName is the parsed line read by BufferedReader.
     * @return true if successful, false if not
     */
    private boolean addSeqOrPar(ArrayList<String> parsedName){    	
    	if(parsedName.size()<2){
    		return false;
    	} else if(parsedName.get(0).equals("Seq")){
    		addSequential(getCommandFromString(parsedName));
    		return true;
    	} else if(parsedName.get(0).equals("Par")){
    		addParallel(getCommandFromString(parsedName));
    		return true;
    	} else{
    		return false;
    	}
    }
    
    /**
     * getCommandFromString returns a command with a name identical to the string passed in, case sensitive
     * Each command must be manually added as a new switch case
     * If a command has parameters, then return statement should be of the form:
     * <code>return new commandWithParams((Double)parsedName.get(2), (int)parsedName.get(3));</code>
     * 
     * @param commandName is the name of the command which should be added.
     * @return the proper command.
     */
    private Command getCommandFromString(ArrayList<String> parsedName){
    	
    	switch(parsedName.get(1)){
    		case "CloseGrabber":
    			return new CloseGrabber();
    		case "DriveTestCommand":
    			return new DriveTestCommand();
    		case "OpenGrabber":
    			return new OpenGrabber();
    			
    	}
    	return null;
    }
}
