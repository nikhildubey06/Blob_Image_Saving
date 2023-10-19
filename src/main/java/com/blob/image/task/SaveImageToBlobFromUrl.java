package com.blob.image.task;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.net.URL;
import java.io.InputStream;

public class SaveImageToBlobFromUrl {

	public static void main(String[] args) {
		String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=storageimage0981;AccountKey=qJziXhR4cquve5wJ0svxpNp1tgS5iRgIl+6kHjZ111iphTiuJ4jaPMRoOaXDdbwXTKyRL4b8FxLl+AStJClzXA==;EndpointSuffix=core.windows.net";
		String containerName = "democontainer";
		String blobName = "Robot.jpg";
		String imageUrl = "https://cdn.theatlantic.com/thumbor/r4Sw3057WpGBScusiE34v-2uh3A=/302x50:4317x2308/976x549/media/img/mt/2014/08/shutterstock_187027727-1/original.jpg";

		try {
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

			CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

			CloudBlobContainer container = blobClient.getContainerReference(containerName);

			CloudBlockBlob blob = container.getBlockBlobReference(blobName);

			URL url = new URL(imageUrl);
			try (InputStream imageStream = url.openStream()) {
				blob.upload(imageStream, -1);
				blob.getProperties().setContentType("image/jpeg");
				blob.uploadProperties();
				System.out.println("Image uploaded to Azure Blob Storage.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}