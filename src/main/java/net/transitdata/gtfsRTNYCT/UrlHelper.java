package net.transitdata.gtfsRTNYCT;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlHelper {
    // set MTA Datamine key as an environment variable or replace this with a string
    private static String key = System.getenv("MTA_KEY");
    private static String baseUrlWithoutFeed = "http://datamine.mta.info/mta_esi.php?key=" + key + "&feed_id=";

    public static URL getFeedUrlFromFeedId(FeedId id) {
        String urlString =baseUrlWithoutFeed + id.getValue();

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}
