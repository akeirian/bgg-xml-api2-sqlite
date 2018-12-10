package pw.akeirian.bgg_xml_api2_sqlite;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

/**
 * BGG XML API2 thing endpoint consumer
 */
public class ThingApiConsumer extends ApiConsumer {
    private static final ThingXmlParser thingXmlParser = new ThingXmlParser();

    public Thing getThing(ThingApiConfig thingApiConfig) throws IOException, XmlPullParserException, InterruptedException {
        final InputStream thingInputStream = getInputStream(new URL(thingApiConfig.toString()));
        return thingXmlParser.parse(thingInputStream);
    }
}
