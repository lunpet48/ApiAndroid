package com.android.api.service.serviceImpl;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.android.api.config.StorageProperties;
import com.android.api.exception.StorageNotFoundException;

@Service
public class FileSystemStorageServiceImpl implements com.android.api.service.StorageService {
	private final Path rootlocation;

	@Override
	public String getStorageFilename(MultipartFile file, String id) {
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		return "p" + id + "." + ext;
	}

	public FileSystemStorageServiceImpl(StorageProperties properties) {
		this.rootlocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file, String storeFilename) {
		try {
			if (file.isEmpty())
				throw new StorageNotFoundException("Failed to store empty file");
			Path destinationFile = this.rootlocation.resolve(Paths.get(storeFilename)).normalize().toAbsolutePath();
			if (!destinationFile.getParent().equals(this.rootlocation.toAbsolutePath())) {
				throw new StorageNotFoundException("Cannot store file outside current directory");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
			}
		} catch (Exception e) {
			throw new StorageNotFoundException("Failed to store file: " + e);
		}
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			throw new StorageNotFoundException("Cannot read file:" + filename);
		} catch (Exception e) {
			throw new StorageNotFoundException("Could not read file:" + filename);
		}
	}

	@Override
	public Path load(String filename) {
		return rootlocation.resolve(filename);
	}

	@Override
	public void delete(String storeFileName) throws Exception {
		Path destinationFile = rootlocation.resolve(Paths.get(storeFileName)).normalize();
		Files.delete(destinationFile);
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootlocation);
			System.out.print(rootlocation.toString());
		} catch (Exception e) {
			throw new StorageNotFoundException("Could not read file:" + e);
		}
	}

	@Override
	public void store(MultipartFile[] featuredImagesFile, String[] featuredImages) {
		int index = 0;
		for (MultipartFile file : featuredImagesFile) {
			try {
				if (file.isEmpty())
					throw new StorageNotFoundException("Failed to store empty file");
				Path destinationFile = this.rootlocation.resolve(Paths.get(featuredImages[index])).normalize().toAbsolutePath();
				if (!destinationFile.getParent().equals(this.rootlocation.toAbsolutePath())) {
					throw new StorageNotFoundException("Cannot store file outside current directory");
				}
				try (InputStream inputStream = file.getInputStream()) {
					Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
				}
			} catch (Exception e) {
				throw new StorageNotFoundException("Failed to store file: " + e);
			}
			index++;
		}
	}
}
