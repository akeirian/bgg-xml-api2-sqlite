package pw.akeirian.bgg_xml_api2_sqlite.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import pw.akeirian.bgg_xml_api2_sqlite.TestUtil;
import pw.akeirian.bgg_xml_api2_sqlite.Thing;
import pw.akeirian.bgg_xml_api2_sqlite.ThingApiConfig;
import pw.akeirian.bgg_xml_api2_sqlite.ThingApiConsumer;
import pw.akeirian.bgg_xml_api2_sqlite.ThingType;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for @link ThingApiConsumer
 */
@RunWith(RobolectricTestRunner.class)
public class ThingApiConsumerTests {
    private static final ThingApiConsumer thingApiConsumer = new ThingApiConsumer();

    @Test
    public void testGetThing() throws InterruptedException, XmlPullParserException, IOException {
        Thing thing = thingApiConsumer.getThing(new ThingApiConfig(1).includeStats().type(ThingType.BOARDGAME));
        assertEquals(TestUtil.dieMacher(), thing);
    }
}
