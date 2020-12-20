package com.gdavidben.speedtest.model;

import java.util.Properties;

public class ConfigurationModel {

	private final static String SPEED_TEST_SERVER_URI_DL = "http://ipv4.ikoula.testdebit.info/%dM.iso";
	private final static int SOCKET_TIMEOUT = 5000;
	private final static Integer MB = 1_000_000;
	
	private int expectedSpeed;
	private boolean testUpload;
	private int internval;
	private String url;
	private int socketTimeout;
	private int sizeUpload;
	
	public ConfigurationModel(Properties properties) {
		super();
		
		String expectedSpeed = properties.getProperty("expected-speed");
		
		if(expectedSpeed != null && !expectedSpeed.isBlank() && !expectedSpeed.isEmpty()) {
			this.expectedSpeed = Integer.valueOf(expectedSpeed);
		} else {
			this.expectedSpeed = 10;
		}
		
		String testUpload = properties.getProperty("test-upload");
		
		if(testUpload != null && !testUpload.isBlank() && !testUpload.isEmpty()) {
			this.testUpload = Boolean.valueOf(testUpload);
		} else {
			this.testUpload = true;
		}
		
		if(this.testUpload) {
			if(this.expectedSpeed < 10) {
				this.sizeUpload = MB * 10;
			} else {
				this.sizeUpload = MB * 10;
			}
		}
		
		String internval = properties.getProperty("internval");
		
		if(internval != null && !internval.isBlank() && !internval.isEmpty()) {
			this.internval = Integer.valueOf(internval);
		} else {
			this.internval = 1000 * 60 * 5;
		}
		
		String socketTimeout = properties.getProperty("socket-timeout");
		
		if(socketTimeout != null && !socketTimeout.isBlank() && !socketTimeout.isEmpty()) {
			this.socketTimeout = Integer.valueOf(socketTimeout);
		} else {
			this.socketTimeout = SOCKET_TIMEOUT;
		}
		
		if(this.expectedSpeed < 10) {
			this.url = String.format(SPEED_TEST_SERVER_URI_DL, 10);
		} else if(this.expectedSpeed  < 50) {
			this.url = String.format(SPEED_TEST_SERVER_URI_DL, 50);
		} else {
			this.url = String.format(SPEED_TEST_SERVER_URI_DL, 100);
		}
	}

	public int getExpectedSpeed() {
		return expectedSpeed;
	}

	public boolean isTestUpload() {
		return testUpload;
	}

	public int getInternval() {
		return internval;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSocketTimeout() {
		return socketTimeout;
	}
	
	public int getSizeUpload() {
		return sizeUpload;
	}
}
