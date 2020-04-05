package net.transitdata.gtfsRTNYCT;

import java.net.MalformedURLException;
import java.net.URL;

class UrlHelper {
    //https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs-ace
    static URL getFeedUrlFromFeedId(FeedId id) {
        String baseUrlWithoutFeed = "https://api-endpoint.mta.info/Dataservice/mtagtfsfeeds/nyct%2Fgtfs";
        String urlString = baseUrlWithoutFeed + "-" + id.getValue();

        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
