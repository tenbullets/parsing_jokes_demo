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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClothesPrint {

    private static final String source = "src/main/java/imgs/clothes/";
    private static BufferedImage image;
    private static Font customFont;

    public void makePrint(List<String> data, Clothes clothes, Color textColor) {
        Map<String, int[]> mdata = new HashMap<>();
        // x, y, text size, wordsPerPar, spacing
        mdata.put("sweatshirt", new int[] {570, 875, 34, 22, 41});
        mdata.put("tshirt", new int[] {526, 823, 34, 22, 41});
        mdata.put("hoodies", new int[] {248, 352, 17, 17, 18});

        int[] mDataArr = mdata.get(clothes.type);
        List<String> res = divideIntoParagraphs(data.get(0).toLowerCase(), mDataArr[3]);

        uploadResources(clothes, mDataArr);


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
        g2d.setColor(new Color(textColor.getRed(), textColor.getGreen(), textColor.getBlue(), 210));


        for (String re : res) {
            g2d.drawString(re, mDataArr[0], mDataArr[1]);
            mDataArr[1] += mDataArr[4];
        }

        mDataArr[1] += (mDataArr[4]/2) + (mDataArr[4]/4);
        g2d.drawString(data.get(1), mDataArr[0], mDataArr[1]);
        g2d.dispose();


        JPEGImageWriteParam jpegParams = new JPEGImageWriteParam(null);
        jpegParams.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        jpegParams.setCompressionQuality(1f);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        try {
            writer.setOutput(new FileImageOutputStream(new File("src/main/java/imgs/result.jpg")));
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

        if (!paragraph.isEmpty()) lines.add(paragraph.toString());
        return lines;
    }

    public static void uploadResources(Clothes clothes, int[] mDataArr) {
        String resSource = source + clothes.type + "/" + clothes.type + "_" + clothes.color + ".jpg";
        try {
            image = ImageIO.read(new File(resSource));

            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/fonts/PTSerif-Regular.ttf")).deriveFont((float) mDataArr[2]);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

}
