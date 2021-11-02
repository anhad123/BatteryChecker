package com.example.checker.batteryChecker;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.checker.batteryChecker.Kernel32.SYSTEM_POWER_STATUS;



@SpringBootApplication
public class BatteryCheckerApplication {
	static String filePath;
	public static void main(String[] args) throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
		SpringApplication.run(BatteryCheckerApplication.class, args);

		SYSTEM_POWER_STATUS batteryStatus = new SYSTEM_POWER_STATUS();
		while(!batteryStatus.getBatteryLifePercent().equals("100%")) {
		Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
		}
		System.out.println(batteryStatus.getBatteryLifePercent());
		BatteryCheckerApplication c=new BatteryCheckerApplication();
		c.waitMethod();
	}
	
	private synchronized void waitMethod() throws InterruptedException, IOException, UnsupportedAudioFileException, LineUnavailableException {
		filePath = "D:\\sound.wav";
		AudioInputStream audioInputStream = AudioSystem
				.getAudioInputStream(new File(filePath).getAbsoluteFile());
		Clip clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		this.wait(7200000);
	}
}