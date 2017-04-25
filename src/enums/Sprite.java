package enums;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public enum Sprite {

    WATER_SPRITE(0, 16, 16, "src/resources/water.png"),
    GRASS_SPRITE(1, 16, 16, "src/resources/grass.png"),
    VOID_SPRITE(2, 16, 16, "src/resources/void.png"),
    MUD_SPRITE(3, 16, 16, "src/resources/mud.png");

    private final int code;
    private final int sizeX;
    private final int sizeY;
    private final String path;
    private BufferedImage img;
    private int[][] pixels;

    private Sprite(int code, int sizeX, int sizeY, String path) {
        this.code = code;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.path = path;
        img = loadImage();
        pixels = convertToPixels(img);
    }

    public BufferedImage loadImage() {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(this.path));
            convertToPixels(img);
        } catch (IOException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Image could not be loaded.");
        }
        
        return img;
    }

    private int[][] convertToPixels(BufferedImage image) {
        final byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < data.length; pixel += pixelLength) {
                int argb = 0;
                argb += (((int) data[pixel] & 0xff) << 24); // alpha
                argb += ((int) data[pixel + 1] & 0xff); // blue
                argb += (((int) data[pixel + 2] & 0xff) << 8); // green
                argb += (((int) data[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            for (int pixel = 0, row = 0, col = 0; pixel < data.length; pixel += pixelLength) {
                int argb = 0;
                argb += -16777216; // 255 alpha
                argb += ((int) data[pixel] & 0xff); // blue
                argb += (((int) data[pixel + 1] & 0xff) << 8); // green
                argb += (((int) data[pixel + 2] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        }

        return result;
    }

    public int getCode() {
        return code;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public String getPath() {
        return path;
    }

    public int[][] getPixels() {
        return pixels;
    }

}
