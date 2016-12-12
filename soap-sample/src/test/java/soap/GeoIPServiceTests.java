package soap;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIPServiceTests {

    @Test
    public void testMyIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("91.221.60.74");
        assertEquals(geoIP.getCountryCode(), "CZE");
    }

    @Test
    public void testInvalidIp() {
        GeoIP geoIP = new GeoIPService().getGeoIPServiceSoap12().getGeoIP("91.221.60.xxx");
        assertEquals(geoIP.getCountryCode(), "RUS");
    }
}
