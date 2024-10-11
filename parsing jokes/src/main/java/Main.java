import lombok.extern.apachecommons.CommonsLog;
import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.util.List;

import static java.lang.Math.sqrt;

public class Main {
    private static final JokeService jokeService = new JokeService();
    private static final ClothesPrint clothesPrint = new ClothesPrint();

    public static void main(String[] args) {
//        anekdot, aphorism

//       x | y | text size | wordsPerPar | str spacing
//        sweatshirt 570_875_34_22_41
//        tshirt 526_823_34_22_41
//        longsleeve 580_875_33_20_41


        String[] colors = {
                "0,0,0",
                "0,0,255",
                "253,255,245",
                "155,45,48",
                "83,86,91",
                "200,172,169",
                "0,0,139",
                "128,128,128",
                "255,255,255"};

        String[] c_name = {
                "black",
                "blue",
                "milk",
                "vinous",
                "graphite",
                "pinkishgrey",
                "darkblue",
                "gray",
                "white"
        };


        double max = 254.99999999999997;

        for (int i = 0; i < colors.length; i++) {
            String r = colors[i].split(",")[0];
            String g = colors[i].split(",")[1];
            String b = colors[i].split(",")[2];

            int r_val = Integer.parseInt(r);
            int g_val = Integer.parseInt(g);
            int b_val = Integer.parseInt(b);

            Color color = new Color(r_val, g_val, b_val);
            double brightness  = 0.2126 * color.getRed() + 0.7152 * color.getGreen() + 0.0722 * color.getBlue();

            double per = brightness * 100 / max;
            System.out.println(c_name[i] + " " +(int) per + "%");

//            > 55 text = black


        }
    }
}