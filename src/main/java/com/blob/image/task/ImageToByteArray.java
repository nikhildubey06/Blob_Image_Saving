package com.blob.image.task;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageToByteArray {
    public static void main(String[] args) {
        try {
        	
            // Load an image from a file
            File imageFile = new File("C:\\Users\\NikhilDubey\\Downloads\\Goku.jpg");
            BufferedImage bufferedImage = ImageIO.read(imageFile);

            // Convert the image to a byte array
            byte[] imageByteArray = imageToByteArray(bufferedImage);
            System.out.println(Arrays.toString(imageByteArray));


            // Now you have the image data in the byte array
            // You can use the imageByteArray as needed

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] imageToByteArray(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }
}
