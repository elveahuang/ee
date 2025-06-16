package cc.elvea.platform.commons.extensions.ip;

/**
 * @author elvea
 */
public class GlobalIpManager {

    private static volatile GeoLite globalGeoLite = new GeoLite();

    public static GeoLite getGeoLite() {
        return globalGeoLite;
    }

    public static void setGeoLite(GeoLite geoLite) {
        globalGeoLite = geoLite;
    }

}
