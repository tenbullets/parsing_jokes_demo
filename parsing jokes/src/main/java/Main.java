import java.util.List;

public class Main {
    private static final JokeService jokeService = new JokeService();
    private static final ClothesPrint clothesPrint = new ClothesPrint();

    public static void main(String[] args) {
//        anekdot, aphorism
        List<String> data = jokeService.getJoke("aphorism");
        clothesPrint.makePrint(data, new Clothes("longsleeve", "white"));
    }
}