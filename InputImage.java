import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.Color;
import java.io.File;
import java.lang.Exception;

import javax.imageio.ImageIO;

public class InputImage {

    BufferedImage currentImg;
    int imageX;
    int imageY;

    public InputImage() {
        
    }

    // I added this incase you want to assign the image immediately.
    // Mostly unused because of the assignImage function.
    public InputImage(String fileUrl) {
        this.assignImage(fileUrl);
    }

    public void assignImage(String fileUrl) {
        this.currentImg = this.createImage(fileUrl);
        this.imageX = this.currentImg.getWidth();
        this.imageY = this.currentImg.getHeight();
    }

    // Returns a BufferedImage from the fileURL.
    public BufferedImage createImage(String fileUrl) {
        try {
            BufferedImage img = ImageIO.read(new File(fileUrl));
            return img;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Returns a 3D matrix. An RGB image is 3 x imageHeight x ImageWidth
    public float[][][] getPictureArray(boolean normalize) {
        float[][][] output = new float[3][imageY][imageX];
        // I figured it would be computational faster to decide whether the values should be normalized before it scan the image.
        // Each case virtually does the same thing except one is normalized between -1 and 1.
        if(normalize) {
            for(int y = 0; y < imageY; y++) {
                for(int x = 0; x < imageX; x++) {
                    output[0][y][x] = (float)((new Color(this.currentImg.getRGB(x, y))).getRed() - 127) / 256;
                    output[1][y][x] = (float)((new Color(this.currentImg.getRGB(x, y))).getGreen() - 127) / 256;
                    output[2][y][x] = (float)((new Color(this.currentImg.getRGB(x, y))).getBlue() - 127) / 256;
                }
            }
        } else {
            for(int y = 0; y < imageY; y++) {
                for(int x = 0; x < imageX; x++) {
                    output[0][y][x] = (float)(new Color(this.currentImg.getRGB(x, y))).getRed();
                    output[1][y][x] = (float)(new Color(this.currentImg.getRGB(x, y))).getGreen();
                    output[2][y][x] = (float)(new Color(this.currentImg.getRGB(x, y))).getBlue();
                }
            }
        }
        return output;
    }

    // This was a failed test for a more efficient way to get image data.
    // Possibly get it working in the future.
    // It uses the images byte data instead of retreaving pixel data individually.
    public int getPixelValues(BufferedImage img, int x, int y) {
        byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
        int index = 4 * (x + (y * (img.getWidth())));
        //System.out.println(index);
        int value = pixels[index+3]; // Add 3 to get the red value;
        if(value < 0) {
            return value + 256;
        }
        return value;
    }

    // Also used to test if the image data was correct.
    // Prints out each 2D panel of pixel data. Essientally a 2D matrix of all the blues values, or red values, etc.
    // If you try using it you can see a vague impression of the image itself.
    public void printImageArray(float[][][] array, int width, int height) {
        for(int c = 0; c < 3; c++) {
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    String space = (10 - array[c][y][x] <= 0) ? (100 - array[c][y][x] <= 0) ? " " : "  " : "   ";
                    System.out.print(array[c][y][x] + space);
                }
                System.out.println("");
            }
            System.out.println("/n");
        }
    }

}

