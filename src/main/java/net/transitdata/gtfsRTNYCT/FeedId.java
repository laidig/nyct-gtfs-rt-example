package net.transitdata.gtfsRTNYCT;

/**
 * Helper for translating feeds to their integer ID
 feed_id=1
  1-6, S  Lines

 feed_id=16
  N Q R W  Lines (Beta Version)

 feed_id=21
  B D  Lines (Beta Version)

 feed_id=2
  L  Line

 feed_id=11
  Staten Island Railway

 feed_id=51
 7 Line (labeled FLUSHING below)
 */
public enum FeedId {
    A_DIVISION (1),
    NQRW (16),
    BD (21),
    L (2),
    SIR (11),
    G ( 31),
    JZ (36),
    FLUSHING (51);


    private final int value;

    FeedId(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
