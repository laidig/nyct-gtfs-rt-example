package net.transitdata.gtfsRTNYCT;

import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class StreamUtil {

    public static InputStream getUrlInputStream(URL url) {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            final String key = System.getenv("MTA_KEY");
            if (key.length() <= 2) {
                throw new IllegalArgumentException("key not set. Set the environment variable MTA_KEY");
            }
            System.out.println("Setting api key : " + key);
            conn.addRequestProperty("x-api-key", key);

            conn.setInstanceFollowRedirects(true);

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return conn.getInputStream();
            } else {
                throw new ConnectException();
            }

        } catch (MalformedURLException e) {
            System.out.println("problem with URL " + url.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
