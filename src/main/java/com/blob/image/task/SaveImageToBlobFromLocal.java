package com.blob.image.task;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.io.File;
import java.io.FileInputStream;

public class SaveImageToBlobFromLocal {

    public static void main(String[] args) {
        String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=storageimage0981;AccountKey=qJziXhR4cquve5wJ0svxpNp1tgS5iRgIl+6kHjZ111iphTiuJ4jaPMRoOaXDdbwXTKyRL4b8FxLl+AStJClzXA==;EndpointSuffix=core.windows.net";
        String containerName = "democontainer";
        String blobName = "Buddha.jpg";
        String localFilePath = "C:\\Users\\NikhilDubey\\Downloads\\Buddha.jpg";

        try {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
            
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            
            CloudBlobContainer container = blobClient.getContainerReference(containerName);
            
            CloudBlockBlob blob = container.getBlockBlobReference(blobName);

            try (FileInputStream fileStream = new FileInputStream(localFilePath)) {
                blob.upload(fileStream, new File(localFilePath).length());
                blob.getProperties().setContentType("image/jpeg");
                blob.uploadProperties();
                System.out.println("Image uploaded to Azure Blob Storage.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
