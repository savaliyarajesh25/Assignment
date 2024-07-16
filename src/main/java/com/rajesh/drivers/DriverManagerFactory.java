package com.rajesh.drivers;

import com.rajesh.enums.DriverType;

public class DriverManagerFactory {
	
	public static CreateDriver getManager(DriverType driverType) {

		switch (driverType) {
		
		case CHROME : {
			return new DriverManagerChrome();
		}
		case FIREFOX : {
			return new DriverManagerFirefox();
		}
		default : throw new IllegalArgumentException("Invalid Driver: " + driverType);	
		}
	}

}
