package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileInfo;
import com.example.demo.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	private List<FileInfo> fileList = new ArrayList<FileInfo>();

	@GetMapping()
	public ResponseEntity<List<FileInfo>> getFiles() {
		return ResponseEntity.ok(fileList);
	}

	@GetMapping("{fileId}")
	public ResponseEntity<byte[]> downloadFileById(@PathVariable String fileId) {
		Optional<FileInfo> file = fileList.stream().filter(f -> f.getId().equals(fileId)).findFirst();
		if (file.isPresent()) {
			byte[] readFile = fileService.readFile(file.get());
			return ResponseEntity.ok().body(readFile);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping()
	public ResponseEntity<FileInfo> add(@RequestParam("file") MultipartFile file) {
		Optional<FileInfo> save = fileService.save(file);
		if (save.isPresent()) {
			fileList.add(save.get());
			return ResponseEntity.ok(save.get());
		}
		return ResponseEntity.internalServerError().body(null);
	}
}
