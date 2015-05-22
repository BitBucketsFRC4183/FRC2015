package org.bitbuckets.frc2015.control.advanced;

import java.util.function.Supplier;

public class DynamicDataRetriever implements DataRetriever{
	
	Supplier<Object[]> data;

	public DynamicDataRetriever(Supplier<Object[]> data){
		this.data = data;
	}
	
	@Override
	public Object[] retrieveData() {
		return data.get();
	}

}
