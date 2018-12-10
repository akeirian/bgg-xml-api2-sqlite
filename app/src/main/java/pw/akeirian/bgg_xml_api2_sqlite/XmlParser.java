package pw.akeirian.bgg_xml_api2_sqlite;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public abstract class XmlParser {
    private static final String VALUE_ATTRIBUTE = "value";

    /**
     * Return the string from the specified attribute of the current tag, parsed as an Integer.
     *
     * @param parser
     * @param attributeName
     * @return
     */
    protected Integer readAttributeInteger(XmlPullParser parser, String attributeName) {
        try {
            return Integer.parseInt(readAttributeString(parser, attributeName));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Return the string from the specified attribute of the current tag, parsed as an Double.
     *
     * @param parser
     * @param attributeName
     * @return
     */
    protected Double readAttributeDouble(XmlPullParser parser, String attributeName) {
        try {
            return Double.parseDouble(readAttributeString(parser, attributeName));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Return the string from the specified attribute of the current tag.
     *
     * @param parser
     * @param attributeName
     * @return
     */
    protected String readAttributeString(XmlPullParser parser, String attributeName) {
        return parser.getAttributeValue(null, attributeName);
    }

    /**
     * Return either the inner text or the string from the value attribute of the current tag,
     * parsed as an Integer, and advance the XmlPullParser to the matching closing tag.
     *
     * @param parser
     * @param elementName
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     */
    protected Integer readTagInteger(XmlPullParser parser, String elementName)
            throws IOException, XmlPullParserException {
        try {
            return Integer.parseInt(readTagString(parser, elementName));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Return either the inner text or the string from the value attribute of the current tag,
     * parsed as a Double, and advance the XmlPullParser to the matching closing tag.
     *
     * @param parser
     * @param elementName
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     */
    protected Double readTagDouble(XmlPullParser parser, String elementName)
            throws IOException, XmlPullParserException {
        try {
            return Double.parseDouble(readTagString(parser, elementName));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Return either the inner text or the string from the value attribute of the current tag, and
     * advance the XmlPullParser to the matching closing tag.
     *
     * @param parser
     * @param elementName
     * @return
     * @throws IOException
     * @throws XmlPullParserException
     */
    protected String readTagString(XmlPullParser parser, String elementName)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, null, elementName);
        String text = readValueString(parser);
        parser.require(XmlPullParser.END_TAG, null, elementName);
        return text;
    }

    /**
     * Skip the current tag by advancing the XmlPullParser until it reaches the end tag that matches
     * the current start tag.
     *
     * @param parser
     * @throws XmlPullParserException
     * @throws IOException
     */
    protected void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException("START_TAG");
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    /**
     * Return either the inner text or the string from the value attribute of the current tag.
     *
     * @param parser
     * @return value string
     * @throws IOException
     * @throws XmlPullParserException
     */
    private String readValueString(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        } else {
            result = parser.getAttributeValue(null, VALUE_ATTRIBUTE);
        }
        return result;
    }
}
