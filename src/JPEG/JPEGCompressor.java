/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPEG;

import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

/**
 *
 * @author kruminsh
 */
public class JPEGCompressor {
    
    public static final float DEFAULT_COMPRESSION_QUALITY = 0.2f;
            
    private RenderedImage originalImage;
    
    private float compressionQuality = DEFAULT_COMPRESSION_QUALITY;
    
    private ImageWriter jpegWriter;
    
    private ImageWriteParam jpegWriteParam;
    
    /**
     * Default constructor
     */
    JPEGCompressor() {
        jpegWriter = ImageIO.getImageWritersByFormatName("jpg").next();
        jpegWriteParam = jpegWriter.getDefaultWriteParam();
        jpegWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegWriteParam.setCompressionQuality(this.compressionQuality);
    }
    
    public boolean originalImageSet() {
        return originalImage == null ? false : true;
    }
    
    public void setCompressionQuality(float value) {
        this.compressionQuality = value;
        jpegWriteParam.setCompressionQuality(this.compressionQuality);
    }
    
    public void setOriginalImage(File imageFile) throws IOException  {
        this.originalImage = ImageIO.read(imageFile);
    }
    
    
    public byte[] getCompressedBytes() throws IOException {
        byte[] compressedImageBytes;
        
        try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ImageOutputStream output = new MemoryCacheImageOutputStream(baos)) {
            jpegWriter.setOutput(output);
            IIOImage outputImage = new IIOImage(originalImage, null, null);
            jpegWriter.write(null, outputImage, jpegWriteParam);
            compressedImageBytes = baos.toByteArray();
        }
                
        return compressedImageBytes;
    }
    
    /**
     * Compresses image using JPEG
     * @param compressedImage
     * @throws IOException 
     */
    public void compressJPEGImage(File compressedImage) throws IOException {        
        try(ImageOutputStream output = ImageIO.createImageOutputStream(compressedImage)) {
            jpegWriter.setOutput(output);
            IIOImage outputImage = new IIOImage(originalImage, null, null);
            jpegWriter.write(null, outputImage, jpegWriteParam);
            
        }
    }
    
}
