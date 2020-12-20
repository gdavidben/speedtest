package com.gdavidben.speedtest.listener;

import java.math.BigDecimal;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;

public class SpeedTestListener implements ISpeedTestListener {
	private final static Integer MB = 1_000_000;
	
	public void onCompletion(SpeedTestReport report) {
		long totalTime = (report.getReportTime() - report.getStartTime()) / 1000000000;
		BigDecimal speed = report.getTransferRateBit().divide(new BigDecimal(MB)).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				
		StringBuilder sb = new StringBuilder();
		sb.append("TYPE: ").append(report.getSpeedTestMode()).append(" ");
		sb.append("TIME: ").append(totalTime).append("s ");
		sb.append("SIZE: ").append(report.getTotalPacketSize() / MB).append("MBs").append(" ");
		sb.append("SPEED: ").append(speed).append("MBs");
		System.out.println(sb);
	}

	public void onProgress(float percent, SpeedTestReport report) {
		
	}

	public void onError(SpeedTestError speedTestError, String errorMessage) {
		
	}
}
