package org.bitbuckets.frc2015.Dashboard;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DashboardManager {
	
	static SendableChooser autoChooser;
	static ArrayList<Command> commandList = new ArrayList<Command>();
	static ArrayList<Command> inputCommands = new ArrayList<Command>();
	
	public static void init(Command[] commands){
		for(Command c: commands){
			commandList.add(c);
		}
		SmartDashboard.putString("Input a command: ", "");
		SmartDashboard.putString("Status: ", "");
		SmartDashboard.putData("Submit Command", new GetCommand());
	}
	
	public static void update(){
	}
	
	public static void populateChooser(){
		autoChooser = new SendableChooser();
		for(Command c: commandList){
			autoChooser.addObject(c.getName(), c);
		}
	}
	
	public static Command getSelected(){
		if(autoChooser == null || autoChooser.getSelected() == null){
			return null;
		}
		return (Command) autoChooser.getSelected();
	}
	
	public static void getCommands(){
		
	}
	
	public static void submitCommand(Command c){
		inputCommands.add(c);
	}
	
	public static void updateStatus(String status){
		SmartDashboard.putString("Status: ", status);
	}

}
