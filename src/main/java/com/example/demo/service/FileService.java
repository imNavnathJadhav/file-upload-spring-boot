package com.example.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileInfo;

@Service
public class FileService {
	private final String folderLocation = "src\\main\\resources\\static\\uploads";

	public Optional<FileInfo> save(MultipartFile file) {
		FileInfo fileInfo = toFileInfo(file);
		String fileLocation = new File(folderLocation).getAbsolutePath() + "\\" + file.getOriginalFilename();

		FileOutputStream output;
		try {
			output = new FileOutputStream(fileLocation);
			output.write(file.getBytes());
			output.close();
			return Optional.of(fileInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	private FileInfo toFileInfo(MultipartFile file) {
		FileInfo info = new FileInfo();
		info.setId(UUID.randomUUID().toString());
		info.setFileName(file.getOriginalFilename());
		info.setCreatedOn(LocalDateTime.now());
		info.setFileSize(file.getSize());
		return info;
	}

	public byte[] readFile(FileInfo fileInfo) {
		String fileLocation = new File(folderLocation.concat("\\").concat(fileInfo.getFileName())).getAbsolutePath();
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		try (InputStream is = new FileInputStream(fileLocation)) {
			while ((nRead = is.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			return buffer.toByteArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
