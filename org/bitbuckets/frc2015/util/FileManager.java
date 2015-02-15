package org.bitbuckets.frc2015.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManager {
	
    /**
     * The current directory. All user scripts, constant files, etc. must reside in this directory or they will be ignored.
     */
    private static File dir = new File("///TextFiles");
    
    /**
     * An ArrayList containing a list of files from the current directory. This must be repopulated by calling fetchFiles()
     * if any files are added or removed from the directory, or if the directory is changed.
     */
    private static ArrayList<File> files;
    
    /**
     * 
     */
    public enum FileType{
    	SCRIPT, CONSTANTS;
    }
    
	/**
	 * Populates FileManager with all of the files in the current directory. Only grabs .txt files.
	 */
	public static void fetchFiles(){
		if(!isInitialized()){
			files = new ArrayList<File>();
		}
	    files.clear();
	    try{
		    files.addAll(new ArrayList<File>(Arrays.asList(dir.listFiles(new FileFilter() {
		        @Override
		        public boolean accept(File pathname) {
		            String name = pathname.getName().toLowerCase();
		            return name.endsWith(".txt") && pathname.isFile();
		        }
		    }))));
	    } catch(NullPointerException e){
	    }
	}

	/**
	 * getFilesOfType will retrieve all files of a desired type from the current directory.
	 * 
	 * @param type is the type of file desired. Must be a valid FileType input.
	 * @return an ArrayList<File> containing all files of a particular FileType from the current directory.
	 */
	public static ArrayList<File> getFilesOfType(FileType type){
		ArrayList<File> files = new ArrayList<File>();
		for(File f: files){
			try{
				BufferedReader br = readFile(f);
		        if(getFileType(br) == type){
		        	files.add(f);
		        }
		        br.close();
			} catch (IOException e){
			}
		}
		return files;
	}
	
	/**
	 * getFileOfName returns a single file by name.
	 * 
	 * @param name of the file desired
	 * @return a file in the current directory
	 * @throws IOException
	 */
	public static File getFileOfName(String name){
		File file = null;
		for(File f: files){
			try{
				BufferedReader br = readFile(f);
		        if(getFileName(br) == name){
		        	file = f;
		        }
				br.close();
			} catch(IOException e ){
			}
		}
		return file;
	}
	
	/**
     * Returns a buffered reader for reading a file.
     * 
     * @return a BufferedReader for a particular file. If the file is not found, is a directory, or is invalid for any
     * other reason, this function returns null.
     * @throws IOException
     */
    public static BufferedReader readFile(File file){

        FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			return null;
		}

        //Construct BufferedReader from InputStreamReader
        return new BufferedReader(new InputStreamReader(fis));
    }
    
    //TODO is there a way to get around having to type in every FileType? More importantly, should we?
    /**
     * getFileType returns the FileType of a file.
     * 
     * @param br is a BufferedReader associated with a particular File
     * @return The FileType of the file. If br is null or causes an IOException, this function returns null.
     */
	public static FileType getFileType(BufferedReader br) {
		FileType type = null;
		String line = null;
		String value = null;
		
		try {
			while((line = br.readLine()) != null){
				if(line.toLowerCase().contains("type=")){
					value = line.substring(line.indexOf("=")+1);
				}
			}
		} catch (IOException | NullPointerException e) {
			return null;
		}
		
		if(value.toLowerCase().equals("script")){
			type = FileType.SCRIPT;
		} else if(value.toLowerCase().equals("constants")){
			type = FileType.CONSTANTS;
		} else{
			//TODO inform the user that there was an error
		}
		return type;
	}
	
    /**
     * Returns the name of a file. 
     * <p>
     * <strong>NOTE:</strong> this does not return the name of the file's path, but rather the name as stated by the user
     * within the file. Typically these will be the same, but they may be different.
     *  
     * @param br is a BufferedReader associated with a particular File
     * @return The name of the file. If br is null or causes an IOException, this function returns null.
     */
	public static String getFileName(BufferedReader br) {
		String name = null;
		String line = null;
		
		try {
			while((line = br.readLine()) != null){
				if(line.toLowerCase().contains("name=")){
					name = line.substring(line.indexOf("=")+1);
				}
			}
		} catch (IOException | NullPointerException e) {
			return null;
		}
		return name;
	}
	
	/**
	 * Checks whether or not FileManager has been populated with files.
	 * <p>
	 * 
	 * @return
	 */
	public static boolean isInitialized(){
		if(files == null){
			return false;
		}
		return true;
	}
	
	/**
	 * Removes comments of both <code>//comment</code> and <code>/*comment{@literal *}/</code> styles.
	 * Examples:
	 * <ul>
	 *   <li>Seq go 1.0 /*this is in feet per second{@literal *}/
	 *   <li>//the following lines happen in parallel
	 *   <li>Par resetGrabber //this requires the grabber to not be active
	 * </ul>
	 * 
	 * @param line - A <code>String</code> which may or may not contain any number of comments. If the line contains no
	 * comments, the string will be returned unmodified. If there is an unclosed in-line comment, it will return unmodifed
	 * and (<strong>Not Yet Implemented</strong> - informs user through error message).
	 */
	public static String removeComments(String line){
		try{
			if(line.equals("") || line.startsWith("//")){
				return line;
			}
			//    ab/*cd*/ef
			//    0123456789
			//      --  --
			while(line.contains("/*")){
				if(line.contains("/*")){
					line = line.substring(0, line.indexOf("/*")+1) + line.substring(line.indexOf("*/")+2);
				}
			}
			if(line.contains("//")){
				line = line.substring(0, line.indexOf("//")+1);
			}
		} catch(IndexOutOfBoundsException e){
			//TODO tell user they didn't close an inline comment
		}
		return line;
	}
	
	/**
	 * Changes the directory which FileManager points towards for all user created files. Only files in this directory
	 * will be read. The default directory is "///TextFiles".
	 * <p>
	 * If <code>filePath</code> is either not a valid abstract path or null, then the directory remains unchanged.
	 * 
	 * @param filePath - A <code>String</code> which denotes the abstract path name of the desired directory.
	 * 
	 */
	public static void changeDirectory(String filePath){
		try{
			File newDir = new File(filePath);
			if(newDir.isDirectory()){
				dir = newDir;
			}
		} catch(NullPointerException e){
			return;
		}
	}
	
	/**
	 * Returns the absolute path name of the current directory.
	 * 
	 * @return The absolute path name of the current directory.
	 */
	public static String getDirectory(){
		return dir.getAbsolutePath();
	}

}
