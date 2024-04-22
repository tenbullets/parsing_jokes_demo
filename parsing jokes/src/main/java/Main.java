
import java.awt.*;
import java.io.IOException;

import java.util.List;

public class Main {

    private static final JokeService jokeService = new JokeService();
    private static final ClothesPrint clothesPrint = new ClothesPrint();

    public static void main(String[] args) throws IOException {
        List<String> data = jokeService.getJoke();
//        clothesPrint.makePrint(data, new Clothes("tshirt", "pinkishgrey"), Color.BLACK);
//        clothesPrint.makePrint(data, new Clothes("hoodies", "black"), Color.WHITE);
        clothesPrint.makePrint(data, new Clothes("sweatshirt", "black"), Color.WHITE);
    }
}