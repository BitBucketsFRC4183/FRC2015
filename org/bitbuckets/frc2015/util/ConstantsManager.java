package org.bitbuckets.frc2015.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.util.FileManager.FileType;

/**
 * ConstantManager is a utility class which bridges together compiled classes and user files of the CONSTANTS type
 * dynamically. A user generated file should meet the following formatting rules:
 * <ul>
 *   <li>Statements are separated by newlines.
 *   <li>There are three special lines available for CONSTANTS type files, and the order of these does not matter.
 *     <ul>
 *       <li>A name line: this is the name of this file. This will generally be the same as the actual file name,
 *           but it may be different if the user desires. Some examples:
 *         <ul>
 *           <li><code>Name=DriveConstants</code>
 *           <li><code>Name=OI_values</code>
 *           <li><code>Name=Constants for drive code</code>
 *         </ul>
 *       <li>A type line: this value indicates that the file is a CONSTANTS file. 
 *           For ConstantsManager to recognize it, this value must be CONSTANTS. For example:
 *         <ul>
 *           <li><code>Type=CONSTANTS</code>
 *         </ul>
 *       <li>A destination line: this value indicates to which class values should be written. 
 *           This will remain the destination until it is changed again. Because the order of files is not guaranteed,
 *           the destination should be set at the start of every file. An example:
 *         <ul>
 *           <li><code>Destination=DriveConstants</code>
 *         </ul>
 *     </ul>
 *   <li>The rest of the lines are values to be written to files. These are of the format:
 *       <p><code>FieldName=ValueCharacter</code>
 *       <p>where <code>FieldName</code> is the name of the field to be changed, <code>Value</code> is the new value to be 
 *       written to this field, and <code>Character</code> is a single character indicating what data type <code>Value</code> is.
 *   <li>Valid data type identifier characters are:
 *     <ul>
 *       <li><code>i</code> indicates an integer
 *       <li><code>d</code> indicates a double
 *       <li><code>l</code> indicates a long
 *       <li><code>s</code> indicates a short
 *       <li><code>b</code> indicates a byte
 *       <li><code>c</code> indicates a character
 *     </ul>
 *   <li>Constant files can be commented.
 *       
 * 
 * @author Miles
 *
 */
public final class ConstantsManager {
	
    //TODO find a good way to handle repeated constants
    //TODO what if constant has no value?
	
	/**
	 * Destination is the target <code>Class</code> to which constant values will be written.
	 */
	static Class<?> destination;
	
	/**
	 * Private Constructor disallows any instancing of ConstantsManager.
	 */
	private ConstantsManager(){
	}

	/**
	 * Fetches the constants from all files in the current directory of FileManager which are of the <code>FileType</code>
	 * <code>FileType.CONSTANTS</code> and updates them. <code>fetchConstants</code> does guarantee any particular 
	 * order of reading files.
	 * <p>
	 * If FileManager has not been initialized, this function will make no changes.
	 */
	public static void fetchConstants(){
		if(!FileManager.isInitialized()){
			return;
		}
		
		ArrayList<File> constFiles = FileManager.getFilesOfType(FileType.CONSTANTS);
		String line = null;
		String constName;
		Object constVal;
		
    	for(File f: constFiles){
    		try{
    		BufferedReader br = FileManager.readFile(f);
	            while ((line = br.readLine()) != null) {
	            	line = FileManager.removeComments(line);
	            	if(line.startsWith("Destination=")){
	            		destination = getDestination(line);
	            		continue;
	            	}
	            	if(!(line.startsWith("Name") || line.startsWith("Type"))){
		            	constName = parseName(line);
		            	constVal = parseValue(line);
		            	updateField(constName, constVal);
	            	}
	            }
    		} catch(IOException e){
    		}
    	}
	}
	
	//TODO make it so you can only switch it to text files
	/**
	 * This function reads a text line and gives you a <code>Class</code>. It takes lines of the format:
	 * <p>
	 * <code>Destination=NameOfTheDestinationClass</code>
	 * 
	 * @param line - The name of the destination <code>Class</code>.
	 * @return the <code>Class</code> denoted by <code>line</code>.
	 */
	private static Class<?> getDestination(String line) {
		Class<?> dest = null;
		try {
			dest = Class.forName(line.substring(line.indexOf("=")+1));
		} catch (ClassNotFoundException e) {
		}
		return dest;
	}

	/**
	 * Parses a line from a constants file and returns a constant's name
	 * 
	 * @param line - The String containing the value and type
	 * @return a substring of the input up until a "=" is reached
	 */
	private static String parseName(String line){
		return line.substring(0, line.indexOf("=")+1);
	}

	/**
	 * Parses a line from a constants file and wraps the value according to the letter designation. Returns null if it cannot
	 * determine the type designation character.
	 * 
	 * @param line - A <code>String</code> containing the value and type of a constant.
	 * @return A String or wrapped primitive. Returns null if the last character is not present or a valid designation.
	 */
	private static Object parseValue(String line){
		String value = null;
		char primType;
		try{
			value = line.substring(line.indexOf("=")+1, line.length()-1);
			primType = line.charAt(line.length()-1);
		} catch(IndexOutOfBoundsException e){
			return null;
		}
		try{
			switch(primType){
				case 'i':
					return Integer.valueOf(value);
				case 'd':
					return Double.valueOf(value);
				case 'l':
					return Long.valueOf(value);
				case 's':
					return Short.valueOf(value);
				case 'c':
					return value.charAt(0);
				case 'b':
					return Byte.valueOf(value);
			}
		} catch(NumberFormatException e){
		}
		return null;
	}

	/**
	 * Sets the value of the field <code>constName</code> to <code>constVal</code>. Because <code>fetchConstants</code>
	 * does not guarantee any particular order of files, if a constant appears twice, an unknown one of the two
	 * values will be taken.
	 * 
	 * @param constName - The name of the field to be modified. See 
	 * @param constVal - The String or wrapped primitive which will be the new value
	 */
	private static void updateField(String constName, Object constVal) {
		// TODO figure out what reflection even is doing
		try{
			destination.getField(constName).set(RandomConstants.class, constVal);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e){
			//TODO somehow inform user of the error, preferably with lots of info
		}
	}
}
