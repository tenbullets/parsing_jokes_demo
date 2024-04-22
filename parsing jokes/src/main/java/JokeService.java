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
    private final List<String> data = new ArrayList<>();

    public List<String> getJoke(String type) {
        String randomDate = getRandomDate();
        String res = getText(randomDate, type);

        int lim = 70;
        if(type.equals("anekdot")) lim = 85;

        while(res.toCharArray().length > lim) {
            res = getText(randomDate, type);
            randomDate = getRandomDate();
            System.out.println(res.toCharArray().length);
        }

        data.add(res);
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

    public static String getText(String date, String type) {
        Document page;
        try {
            page = Jsoup.connect("https://www.anekdot.ru/release/" + type + "/day/" + date + "/").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var element = page.getElementsByClass("text");
        Random r = new Random();

        return element.get(r.nextInt(element.size())).text();
    }

}
