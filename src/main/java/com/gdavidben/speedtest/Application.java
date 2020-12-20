package com.gdavidben.speedtest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.gdavidben.speedtest.listener.SpeedTestListener;
import com.gdavidben.speedtest.model.ConfigurationModel;

import fr.bmartel.speedtest.SpeedTestSocket;

public class Application {

	public static void main(final String[] args) throws InterruptedException {
		
		ConfigurationModel config = new ConfigurationModel(loadProperties());
		
		while (true) {
			startDownload(config);
			startUpload(config);
			Thread.sleep(config.getInternval() * (60 * 1000));
		}
	}

	public static Properties loadProperties() {
		Properties properties = new Properties();
		try {
			File jarPath = new File(Application.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			String propertiesPath = jarPath.getParentFile().getAbsolutePath();
			properties.load(new FileInputStream(propertiesPath + "/app.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;
	}
	
	public static void startDownload(ConfigurationModel config) {
		final SpeedTestSocket speedTestDownload = new SpeedTestSocket();
		speedTestDownload.setSocketTimeout(config.getSocketTimeout());
		speedTestDownload.addSpeedTestListener(new SpeedTestListener());
		speedTestDownload.startDownload(config.getUrl());
	}

	public static void startUpload(ConfigurationModel config) {
		final SpeedTestSocket speedTestUpload = new SpeedTestSocket();
		speedTestUpload.setSocketTimeout(config.getSocketTimeout());
		speedTestUpload.addSpeedTestListener(new SpeedTestListener());
		speedTestUpload.startUpload(config.getUrl(), config.getSizeUpload());
	}
}