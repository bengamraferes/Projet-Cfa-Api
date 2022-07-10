package fr.dawan.cfa2022.controllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controleur permettant de récupérer le fichier de logs stocké dans storagefolder
 * @author Mohamed
 *
 */
@RestController
@RequestMapping("/api/logs")
public class LogController {

	@Value("${app.storagefolder}")
	private String storageFolder;

	public String getStorageFolder() {
		return storageFolder;
	}

	public void setStorageFolder(String storageFolder) {
		this.storageFolder = storageFolder;
	}
	
	@GetMapping(produces = "application/octet-stream")
	public ResponseEntity<Resource> getLogFile() throws Exception {
		File f = new File(storageFolder + "/app.log");
		
		Path path = Paths.get(f.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
		
		//Pour afficher un boite de téléchargement dans une réponse web au lieu de changer de page, nous devons
		//spécifier un header : Content-Disposition, attachment;filename=app.log
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=app.log");
		
		return ResponseEntity.ok()
				.headers(headers).contentLength(f.length()).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}
	
	
	
	
}
