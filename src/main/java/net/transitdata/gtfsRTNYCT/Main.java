package net.transitdata.gtfsRTNYCT;

import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtimeNYCT;
import com.google.protobuf.ExtensionRegistry;

import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main (String[] args) {
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(GtfsRealtimeNYCT.nyctFeedHeader);
        registry.add(GtfsRealtimeNYCT.nyctStopTimeUpdate);
        registry.add(GtfsRealtimeNYCT.nyctTripDescriptor);

        URL url = UrlHelper.getFeedUrlFromFeedId(FeedId.BD);

        System.out.println("getting feed from " + url.toString());

        GtfsRealtime.FeedMessage feed = null;
        try {
            feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream(), registry);
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




