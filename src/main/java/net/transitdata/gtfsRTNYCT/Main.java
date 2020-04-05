package net.transitdata.gtfsRTNYCT;

import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtimeNYCT;
import com.google.protobuf.ExtensionRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

// This prints all of the TripUpdates that is set in the feedId variable
// below.

import static net.transitdata.gtfsRTNYCT.UrlHelper.getFeedUrlFromFeedId;

public class Main {
    public static void main (String[] args) {
        FeedId feedId = FeedId.FLUSHING;

        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(GtfsRealtimeNYCT.nyctFeedHeader);
        registry.add(GtfsRealtimeNYCT.nyctStopTimeUpdate);
        registry.add(GtfsRealtimeNYCT.nyctTripDescriptor);

        //Change this to another feed
        URL url = getFeedUrlFromFeedId(feedId);
        System.out.println("getting feed from " + url.toString());

        GtfsRealtime.FeedMessage feed = null;
        try {
            InputStream stream = StreamUtil.getUrlInputStream(url);
            feed = GtfsRealtime.FeedMessage.parseFrom(stream, registry);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (feed != null) {
            //noinspection CodeBlock2Expr
            feed.getEntityList()
                    .stream()
                    .filter(GtfsRealtime.FeedEntity::hasTripUpdate)
                    .forEach(entity -> {
                        System.out.println(entity.getTripUpdate().toString());
                    });
        }
    }
}




