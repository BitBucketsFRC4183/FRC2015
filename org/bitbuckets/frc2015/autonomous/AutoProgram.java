package org.bitbuckets.frc2015.autonomous;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	
	//Title of autonomous program
	public String title;
	
	/**
	 * <h1>AutoProgram reads and executes a script to create an autonomous program </h1><p>
	 * Acceptable scripts are as follows: <p>
	 * Comments are allowed by //. <br>
	 *     The name of the program is denoted by a line beginning with the String "Title=". <br>
	 *     Each command is denoted by the following notation:
	 *       <code>Type Name Param1 Param2 Param3 ...</code> <br>
	 *     Where type is either "Seq" or "Par", name is the name of the command, and parameters are separated by spaces.<br>
	 * 
	 * @param fileName is the name of the file
	 * @throws IOException
	 */
    public  AutoProgram(File script) throws IOException {
    	//created BufferedReader from the file
    	br = readFile(script);
    	String line = null;
    	//adds each line to the ArrayList
    	while ((line = br.readLine()) != null) {
    		//check for a line denoting the title of the script
    		if(line.startsWith("Title=")){
    			title = line.substring(line.indexOf("="));
    			continue;
    		}
    		//check for a character denoting a commented line
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
    private BufferedReader readFile(File script) throws IOException{
    	
    	FileInputStream fis = new FileInputStream(script);
    	 
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
