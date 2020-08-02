package tn.uud.ulysse.config;

import java.io.File;

public class GetFilePath {
	private String directory;
	private String file;
	
	public GetFilePath(String directory, String file) {
		this.directory = directory;
		this.file = file;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getFile() {
		File f = new File(this.directory + this.file);
		this.file = f.getAbsolutePath();
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}
}