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

    public InputImage(String fileUrl, int x, int y) {
        this.AssignImage(fileUrl);
        this.imageX = x;
        this.imageY = y;
    }

    public void AssignImage(String fileUrl) {
        this.currentImg = this.createImage(fileUrl);
        this.imageX = this.currentImg.getWidth();
        this.imageY = this.currentImg.getHeight();
    }

    public BufferedImage createImage(String fileUrl) {
        try {
            BufferedImage img = ImageIO.read(new File(fileUrl));
            return img;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public float[][][] getPictureArray(boolean normalize) {
        float[][][] output = new float[3][imageY][imageX];
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
