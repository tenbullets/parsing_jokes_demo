import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClothesPrint {

    private static Font customFont;
    private static final String source = "C:\\Users\\MSI\\IdeaProjects\\parsing jokes\\src\\main\\java\\imgs\\Свитшот_черный.jpg";

    public void makePrint(List<String> data) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File(source));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> res = divideIntoParagraphs(data.get(0).toLowerCase(), 22);

        try {
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/fonts/PTSerif-Regular.ttf")).deriveFont(22f);
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/fonts/PTSerif-Regular.ttf")).deriveFont(19f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        Graphics g = image.getGraphics();
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);


        g2d.setFont(customFont);
        g2d.setColor(new Color(255, 255, 255, 210));

        int x = 330;
        int y = 525;
        for (String re : res) {
            g2d.drawString(re, x, y);
            y += 22;
        }
        y += 19;
        g2d.drawString(data.get(1), x, y);
        g2d.dispose();


        JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
        jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegParams.setCompressionQuality(1f);

        final ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        try {
            writer.setOutput(new FileImageOutputStream(new File("src/main/java/imgs/test.jpg")));
            writer.write(null, new IIOImage(image, null, null), jpegParams);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static List<String> divideIntoParagraphs(String text, int wordsPerParagraph) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder paragraph = new StringBuilder();

        for (String word : words) {
            paragraph.append(word).append(" ");
            if (paragraph.length() >= wordsPerParagraph) {
                lines.add(paragraph.toString());
                paragraph = new StringBuilder();
            }
        }

        if (!paragraph.isEmpty())
            lines.add(paragraph.toString());

        return lines;
    }
}
