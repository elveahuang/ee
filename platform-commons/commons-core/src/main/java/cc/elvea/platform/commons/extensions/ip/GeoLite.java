package cc.elvea.platform.commons.extensions.ip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author elvea
 */
@Slf4j
public class GeoLite {

    private DatabaseReader reader;

    public void init(Resource resource) {
        log.info("GeoLite [{}] init start.", resource.getFilename());
        try {
            reader = new DatabaseReader.Builder(resource.getInputStream()).build();
            log.info("GeoLite [{}] init done .", resource.getFilename());
        } catch (IOException e) {
            log.info("GeoLite [{}] init failed .", resource.getFilename(), e);
            throw new RuntimeException(e);
        }
    }

    public Ip search(String ip) throws Exception {
        CountryResponse country = this.reader.country(InetAddress.getByName(ip));
        CityResponse city = this.reader.city(InetAddress.getByName(ip));
        return Ip.builder()
                .country(Ip.Country.builder().code(country.getCountry().getIsoCode()).name(country.getCountry().getName()).label(country.getCountry().getNames()).build())
                .city(Ip.City.builder().name(city.getCity().getName()).label(city.getCity().getNames()).build())
                .build();
    }

}
