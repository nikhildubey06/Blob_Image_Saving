package com.blob.image.task;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobClient;

import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FetchFromBlobAccount {

	String connectionString = "DefaultEndpointsProtocol=https;AccountName=storageimage0981;AccountKey=qJziXhR4cquve5wJ0svxpNp1tgS5iRgIl+6kHjZ111iphTiuJ4jaPMRoOaXDdbwXTKyRL4b8FxLl+AStJClzXA==;EndpointSuffix=core.windows.net";

	@GetMapping("/image")
	public ResponseEntity<byte[]> getImage(@RequestParam String container, @RequestParam String blob)
			throws IOException {
		BlobServiceClient serviceClient = new BlobServiceClientBuilder()
				.connectionString(connectionString)
				.buildClient();

		BlobContainerClient containerClient = serviceClient.getBlobContainerClient(container);

		BlobClient blobClient = containerClient.getBlobClient(blob);

		if (blobClient.exists()) {
			byte[] imageBytes = blobClient.openInputStream().readAllBytes();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

//http://localhost:8080/image?container=democontainer&blob=God
