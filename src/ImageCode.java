import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ImageCode {

    private static final int WIDTH = 150;
    private static final int HEIGHT = 50;
    private static final int FONT_SIZE = 38;
    private static final int CODE_LENGTH = 4;
    private static final String OUTPUT_DIR = "/Users/aiden/Desktop/";
    private static final String FILE_PREFIX = "VerificationCode_";
    private static final String FILE_EXTENSION = ".jpg";

    private static final String[] STRS = {
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "2", "3", "4", "5", "6", "7", "8", "9"
    };

    public static void main(String[] args) {
        try {
            int numCodesToGenerate = 5; // The user can decide how many codes need to be generated
            for (int i = 1; i <= numCodesToGenerate; i++) {
                BufferedImage image = generateImage();
                String outputPath = OUTPUT_DIR + FILE_PREFIX + i + FILE_EXTENSION;
                saveImage(image, outputPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage generateImage() {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // Set the background color to white
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // Set the text color to red and font
        g.setColor(Color.RED);
        g.setFont(new Font("Serif", Font.BOLD, FONT_SIZE));

        // Create a random number generator
        Random random = new Random();

        // Generate and draw a random 4-character code
        StringBuilder code = new StringBuilder();
        int x = 30;
        int y = 30;
        for (int i = 0; i < CODE_LENGTH; i++) {
            int num = random.nextInt(STRS.length);
            String str = STRS[num];
            code.append(str);
            g.drawString(str, x, y);
            x += 25;
        }

        // Add random blue lines for additional security
        g.setColor(Color.BLUE);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(30);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(30) + WIDTH - 30;
            int y2 = random.nextInt(HEIGHT);

            g.drawLine(x1, y1, x2, y2);
        }

        // Dispose of the graphics object
        // Release these resources explicitly
        g.dispose();

        System.out.println("Generated Code: " + code.toString());

        return image;
    }

    /**
     * Saves a BufferedImage as a JPEG image file to the specified output path.
     *
     * @param image      The BufferedImage to be saved as an image.
     * @param outputPath The file path where the image will be saved.
     * @throws IOException If an I/O error occurs during the image saving process.
     */
    private static void saveImage(BufferedImage image, String outputPath) throws IOException {
        // Create a File object representing the output path
        File output = new File(outputPath);

        // Write the BufferedImage as a JPEG image to the specified output file
        ImageIO.write(image, "jpg", output);

        // Print a message to the console indicating that the image has been saved
        System.out.println("Image saved to: " + outputPath);
    }
}
