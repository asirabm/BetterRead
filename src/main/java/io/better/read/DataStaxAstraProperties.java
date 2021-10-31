package io.better.read;

//import java.beans.JavaBean;
import java.io.File;

import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@ConfigurationProperties(prefix = "datastax.astra")
public class DataStaxAstraProperties {

	private File secureConnectBundle;

	public File getSecureConnectBundle() {
		// System.out.println(secureConnectBundle);
		return secureConnectBundle;
	}

	public void setSecureConnectBundle(File secureConnectBundle) {
		this.secureConnectBundle = secureConnectBundle;
	}

	
}