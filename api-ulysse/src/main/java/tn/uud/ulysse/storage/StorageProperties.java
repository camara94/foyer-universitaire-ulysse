package tn.uud.ulysse.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private String location;

	public StorageProperties() {}

	public StorageProperties(String location) {
		super();
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
    
}
