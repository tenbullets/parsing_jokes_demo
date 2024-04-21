import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JokeService {
    private Document page;
    private List<String> data = new ArrayList<>();

    public List<String> getJoke() {

        String randomDate = getRandomDate();

        try {
            page = Jsoup.connect("https://www.anekdot.ru/release/aphorism/day/" + randomDate + "/").get();
//            jokes = Jsoup.connect("https://www.anekdot.ru/release/anekdot/week/1997-16/2").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var element = page.getElementsByClass("text");

        Random r = new Random();

        String text = element.get(r.nextInt(element.size())).text();
        data.add(text);
        data.add(randomDate);

        return data;
    }

    public static String getRandomDate() {
        long leftLimit = 946708560000L;
        long rightLimit = Instant.now().toEpochMilli();
        long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        var randomDate = Instant.ofEpochMilli(generatedLong);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone( ZoneId.systemDefault() );
        return formatter.format(randomDate);
    }

}
