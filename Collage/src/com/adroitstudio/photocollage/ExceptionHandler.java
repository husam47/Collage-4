package com.adroitstudio.photocollage;

import java.lang.Thread.UncaughtExceptionHandler;

import android.util.Log;

public class ExceptionHandler implements UncaughtExceptionHandler {

	private ExceptionHandler() {
	
	}

	@Override
	public void uncaughtException(Thread thread, Throwable e) {
		Log.e("THE-MKR", "ExceptionHandler.uncaughtException   Jhinga-La-La " + e.getMessage());
		e.printStackTrace();
		System.exit(0);
	}

	public static void attachActivity() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler());
	}

}
