package com.example.demo.model;

import java.time.LocalDateTime;

public class FileInfo {
	private String id;
	private String fileName;
	private long fileSize;
	private LocalDateTime createdOn;

	public FileInfo(String id, String fileName, long fileSize, LocalDateTime createdOn) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.createdOn = createdOn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public FileInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "FileInfo [id=" + id + ", fileName=" + fileName + ", fileSize=" + fileSize + ", createdOn=" + createdOn
				+ "]";
	}

}
