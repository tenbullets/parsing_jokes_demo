
import java.io.IOException;

import java.util.List;

public class Main {

    private static final JokeService jokeService = new JokeService();
    private static final ClothesPrint clothesPrint = new ClothesPrint();

    public static void main(String[] args) throws IOException {
        List<String> data = jokeService.getJoke();
        System.out.println("1");
        clothesPrint.makePrint(data);
        System.out.println("2");
    }
}