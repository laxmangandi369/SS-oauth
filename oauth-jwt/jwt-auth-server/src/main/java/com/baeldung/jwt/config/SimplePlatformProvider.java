package com.baeldung.jwt.config;

import java.io.File;

import org.keycloak.platform.PlatformProvider;
import org.keycloak.services.ServicesLogger;

public class SimplePlatformProvider implements PlatformProvider {

	Runnable shutdownHook;

	@Override
	public void onStartup(Runnable startupHook) {
		startupHook.run();
	}

	@Override
	public void onShutdown(Runnable shutdownHook) {
		System.out.println("Laxman updated");
		this.shutdownHook = shutdownHook;
	}

	@Override
	public void exit(Throwable cause) {
		ServicesLogger.LOGGER.fatal(cause);
		exit(1);
	}

	private void exit(int status) {
		new Thread() {
			@Override
			public void run() {
				System.exit(status);
			}
		}.start();
	}
	
	@Override
	public File getTmpDirectory() {
		return new File(System.getProperty("java.io.tmpdir"));
	}
}
