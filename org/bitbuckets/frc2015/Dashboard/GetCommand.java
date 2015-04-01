package org.bitbuckets.frc2015.Dashboard;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *
 */
public class GetCommand extends Command {
	
	String commandStr = "";

    public GetCommand() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try{
    		commandStr = SmartDashboard.getString("Input a command: ", "");
    	} catch(TableKeyNotDefinedException e){
    		e.printStackTrace();
    		DashboardManager.updateStatus("Error: TableKeyNotDefinedException");
    	}
    	Command command = generateCommand(commandStr);
    	if(command == null){
    		return;
    	}
    	DashboardManager.submitCommand(command);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
	private Command generateCommand(String input){
		if(input == null){
			DashboardManager.updateStatus("Input is null");
			return null;
		}
		if(input.equals("")){
			DashboardManager.updateStatus("Input is empty");
			return null;
		}
	}
    
}
