package com.logWatch.demo.service;

import java.io.IOException;
import java.io.RandomAccessFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class logWatchService 

{
	private final static String FILE_NAME_STRING = "log.txt";
	private final static String READ_MODE_STRING = "r";
	private final static String DESTINATION_STRING = "/log";
	private long offset;
	
	RandomAccessFile randomAccessFile;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	public logWatchService() throws IOException
	{
		randomAccessFile = new RandomAccessFile(FILE_NAME_STRING, READ_MODE_STRING);
		
		offset = setOffset(); 
	}
	
	@Scheduled(fixedDelay = 100, initialDelay = 6000)
	public void sendUpdates() throws IOException
	{
		long len = randomAccessFile.length();
		
		randomAccessFile.seek(offset);
		while (randomAccessFile.getFilePointer() < len)
		{
			String fileDataString = randomAccessFile.readLine();
			String payloadString = fileDataString;
			messagingTemplate.convertAndSend(DESTINATION_STRING, payloadString);
		}
		
		offset = len;
	}
	
	
	public long setOffset() throws IOException
	{
		int lineCount = 0;
		while (randomAccessFile.readLine() != null)
		{
			lineCount += 1 ;
		}
		
		if (lineCount > 10)
		{
			offset = lineCount - 10;
		}
		
		return offset;
			
	}

}
