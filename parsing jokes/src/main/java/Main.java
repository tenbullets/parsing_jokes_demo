import java.util.List;

public class Main {
    private static final JokeService jokeService = new JokeService();
    private static final ClothesPrint clothesPrint = new ClothesPrint();

    public static void main(String[] args) {
//        anekdot, aphorism

//       x | y | text size | wordsPerPar | str spacing
//        sweatshirt 570_875_34_22_41
//        tshirt 526_823_34_22_41
//        longsleeve 580_875_33_20_41

        List<String> data = jokeService.getJoke("aphorism");
        clothesPrint.makePrint(data, new Clothes("sweatshirt", "milk"), "570_875_34_22_41");
    }
}