package org.bitbuckets.frc2015.Dashboard;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardManager {
	
	static SendableChooser autoChooser;
	static ArrayList<Command> commandList = new ArrayList<Command>();
	static ArrayList<Command> inputCommands = new ArrayList<Command>();
	
	/**
	 * Initializes the DashboardManager. This adds some commands to the chooser
	 * and ensures that the necessary Dashboard keys for inputting commands are not null.
	 * 
	 * @param commands - A list of commands add to the chooser. This can be changed later.
	 */
	public static void init(Command[] commands){
		for(Command c: commands){
			commandList.add(c);
		}
		SmartDashboard.putString("Input a command: ", "");
		SmartDashboard.putString("Status: ", "");
		SmartDashboard.putData("Submit Command", new GetCommand());
	}
	
	/**
	 * 
	 */
	public static void update(){
	}
	
	/**
	 * 
	 */
	public static void populateChooser(){
		autoChooser = new SendableChooser();
		for(Command c: commandList){
			autoChooser.addObject(c.getName(), c);
		}
		updateStatus("Chooser populated");
	}
	
	/**
	 * 
	 * @return
	 */
	public static Command getSelected(){
		if(autoChooser == null || autoChooser.getSelected() == null){
			return null;
		}
		return (Command) autoChooser.getSelected();
	}
	
	/**
	 * 
	 * @return
	 */
	public static ArrayList<Command> getCommands(){
		return commandList;
	}
	
	/**
	 * 
	 * @param c
	 */
	public static void submitCommand(Command c){
    	if(c == null){
    		updateStatus("Tried to add a null command");
    		return;
    	}
		inputCommands.add(c);
		updateStatus("Command added successfully");
	}
	
	/**
	 * 
	 * @param status
	 */
	public static void updateStatus(String status){
		SmartDashboard.putString("Status: ", status);
	}

}
