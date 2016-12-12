package soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIPServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIp = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("91.221.60.74");
        assertEquals(geoIp.getCountryCode(), "RUS");
    }
}
