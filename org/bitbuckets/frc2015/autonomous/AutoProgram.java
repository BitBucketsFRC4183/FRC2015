package org.bitbuckets.frc2015.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.bitbuckets.frc2015.command.*;
import org.bitbuckets.frc2015.util.FileManager;
import org.bitbuckets.frc2015.util.FileManager.FileType;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 *
 */
public class AutoProgram extends CommandGroup {

    //commandStr holds strings representing commands and their parameters, in the form "Seq/Par commandName
    ArrayList<String> commandStr = new ArrayList<String>();

    //Name of autonomous program
    public String name;
    
    //Type of program
    public FileType type;


    /**
     * <h1>AutoProgram reads and executes a script to create an autonomous program </h1><p>
     * Acceptable scripts are as follows:
     * <p>
     * Comments are allowed by //. <br>
     * The name of the program is denoted by a line beginning with the String "Name=". <br>
     * Each command is denoted by the following notation:
     * <code>Type Name Param1 Param2 Param3 ...</code> <br>
     * Where type is either "Seq" or "Par", name is the name of the command, and parameters are separated by spaces.<br>
     * <p>
     * Parameters take the following forms:
     * <p>
     * <ul>
     *   <li><strong>Strings</strong> must begin and end with either " or '. The two can be mixed, so "hello' is acceptable.</li>
     * </ul>
     *
     * @throws IOException
     */
    public AutoProgram(File script) throws IOException {
    	
        //br reads the text from the file line by line
        BufferedReader brName = FileManager.readFile(script);
        
        String line;
        
        //get the name of the file
    	name = FileManager.getFileName(brName);
    	
    	//this will move the buffer to the end of the file, so we want a new buffer
    	brName.close();
    	
    	BufferedReader br = FileManager.readFile(script);
    	
        while ((line = br.readLine()) != null) {
        	//get type
        	type = FileType.SCRIPT;
        	
            //check for a character denoting a commented line
            if(line.startsWith("//")) {
                continue;
            }
            //check that the line has something other than whitespace
            if(!line.contains("^\\s+")){
            	continue;
            }
            commandStr.add(line);
        }
        br.close();

        //iterates through the lines, parses them, and adds them either as sequential or parallel commands
        for (String commandName : commandStr) {
            addSeqOrPar(parse(commandName));
        }
    }



    /**
     * Parses the commandName into:
     * 0:  the type of command, either "Seq" or "Par"
     * 1:  the name of the command, case sensitive. Should be passed into getCommandFromString.
     * 2+: the parameters that are fed into the command
     *
     * @param commandName is the name of the desired command.
     * @return an ArrayList containing the
     */
    private ArrayList<String> parse(String commandName) {
        ArrayList<String> parsed = new ArrayList<String>();
        
        //split the line at whitespaces and toss out any //comments
        for (String s : commandName.split("\\s+")) {
        	s = FileManager.removeComments(s);
            parsed.add(s);
        }
        return parsed;
    }

    /**
     * addSeqOrPar adds a command of a name and type.
     * <p/>
     * The parsing is as follows:
     * 0:  the type of command, either "Seq" or "Par"
     * 1:  the name of the command, case sensitive. Should be passed into getCommandFromString.
     * 2+: the parameters that are fed into the command
     *
     * @param parsedName is the parsed line read by BufferedReader.
     * @return true if successful, false if not
     */
    private boolean addSeqOrPar(ArrayList<String> parsedName) {
    	
    	SmartDashboard.putString("Adding command of name:", parsedName.get(1));

        try {
	        if (parsedName.size() < 2) {
	            return false;
	        } else if (parsedName.get(0).equals("Seq")) {
	            addSequential(getCommandFromString(parsedName));
	            return true;
	        } else if (parsedName.get(0).equals("Par")) {
	            addParallel(getCommandFromString(parsedName));
	            return true;
	        } else {
	            return false;
	        }
		} catch (IllegalAccessException | InvocationTargetException
				| InstantiationException | ClassNotFoundException e) {
		}
		return false;
    }

    /**
     * getCommandFromString returns a command with a name identical to the string passed in, case sensitive
     * Each command must be manually added as a new switch case
     * If a command has parameters, then return statement should be of the form:
     * <code>return new commandWithParams((Double)parsedName.get(2), (int)parsedName.get(3));</code>
     *
     * @return the proper command.
     */
    private Command getCommandFromString(ArrayList<String> parsedName) throws IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {

        Constructor<Command>[] constructors;
        Object[] wrappedParams = new Object[parsedName.size()-2];

        for(int i = 2; i < parsedName.size(); i++){
        	wrappedParams[i] = wrapParam(parsedName.get(i));
        }

        //TODO what happens if it is not correct?
        constructors = (Constructor<Command>[]) Class.forName(parsedName.get(1)).getConstructors();

        
        return constructors[constructors.length-1].newInstance(wrappedParams);

        //old code
//        switch (commandType.valueOf(parsedName.get(1))) {
//            case ChangeDriveMode:
//                return new ChangeDriveMode();
//            case CloseGrabber:
//                return new CloseGrabber();
//            case OpenGrabber:
//                return new OpenGrabber();
//            case Wait:
//                return new Wait(Double.parseDouble(parsedName.get(2)));
//            case TiltUp:
//            	return new TiltUp();
//            case TiltDown:
//            	return new TiltDown();
//            default:
//            	return new Wait(0.1);
//
//        }
    }
    
    
    //TODO change to require a suffix letter indicator i d l s c b
    /**
     * Converts a string input into the correct wrapped primitive out of String, Double, Integer
     * 
     * @param param
     * @return
     */
    private Object wrapParam(String param){
    	Object wrapped = null;
    	if(param.startsWith("\"") | param.startsWith("\'")){
    		if(param.endsWith("\"") | param.endsWith("\'")){
    			wrapped = param;
    		}
    	} else if(param.contains(".")){
    		wrapped = Double.parseDouble(param);
    	} else if(!param.contains("[^0-9]")){
    		wrapped = Integer.parseInt(param);
    	} else {
    		wrapped = param;
    	}    	
    	return wrapped;
    }
}
