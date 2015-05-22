package org.bitbuckets.frc2015.control.advanced;

import java.util.function.Consumer;

public class DynamicDataSender implements DataSender {
	
	Consumer<Object[]> sender;
	
	public DynamicDataSender(Consumer<Object[]> sender){
		this.sender = sender;
	}

	@Override
	public void sendData(Object[] params) {
		sender.accept(params);
	}

}
