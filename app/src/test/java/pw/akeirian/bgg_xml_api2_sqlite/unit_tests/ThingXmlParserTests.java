package pw.akeirian.bgg_xml_api2_sqlite.unit_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import pw.akeirian.bgg_xml_api2_sqlite.TestUtil;
import pw.akeirian.bgg_xml_api2_sqlite.Thing;
import pw.akeirian.bgg_xml_api2_sqlite.ThingXmlParser;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for @link ThingXmlParser
 */
@RunWith(RobolectricTestRunner.class)
public class ThingXmlParserTests {
    private static final String thingString = "<items termsofuse=\"https://boardgamegeek.com/xmlapi/termsofuse\"><item type=\"boardgame\" id=\"1\"><thumbnail>https://cf.geekdo-images.com/thumb/img/RgXAhOreEqPeNiPpDPEUTwLm5Wk=/fit-in/200x150/pic159509.jpg</thumbnail><image>https://cf.geekdo-images.com/original/img/vOttDcPBg1Tas9F6vFDhRmVaNH8=/0x0/pic159509.jpg</image><name type=\"primary\" sortindex=\"5\" value=\"Die Macher\"/><description>Die Macher is a game about seven sequential political races in different regions of Germany. Players are in charge of national political parties, and must manage limited resources to help their party to victory. The winning party will have the most victory points after all the regional elections. There are four different ways of scoring victory points. First, each regional election can supply one to eighty victory points, depending on the size of the region and how well your party does in it. Second, if a party wins a regional election and has some media influence in the region, then the party will receive some media-control victory points. Third, each party has a national party membership which will grow as the game progresses and this will supply a fair number of victory points. Lastly, parties score some victory points if their party platform matches the national opinions at the end of the game.The 1986 edition featured four parties from the old West Germany and supported 3-4 players. The 1997 edition supports up to five players in the re-united Germany and updated several features of the rules as well. The 2006 edition also supports up to five players and adds a shorter five-round variant and additional rules updates by the original designer.</description><yearpublished value=\"1986\"/><minplayers value=\"3\"/><maxplayers value=\"5\"/><poll name=\"suggested_numplayers\" title=\"User Suggested Number of Players\" totalvotes=\"124\"></poll><playingtime value=\"240\"/><minplaytime value=\"240\"/><maxplaytime value=\"240\"/><minage value=\"14\"/><poll name=\"suggested_playerage\" title=\"User Suggested Player Age\" totalvotes=\"29\"><results><result value=\"2\" numvotes=\"0\"/><result value=\"3\" numvotes=\"0\"/><result value=\"4\" numvotes=\"0\"/><result value=\"5\" numvotes=\"0\"/><result value=\"6\" numvotes=\"0\"/><result value=\"8\" numvotes=\"0\"/><result value=\"10\" numvotes=\"0\"/><result value=\"12\" numvotes=\"6\"/><result value=\"14\" numvotes=\"16\"/><result value=\"16\" numvotes=\"4\"/><result value=\"18\" numvotes=\"2\"/><result value=\"21 and up\" numvotes=\"1\"/></results></poll><poll name=\"language_dependence\" title=\"Language Dependence\" totalvotes=\"47\"><results><result level=\"6\" value=\"No necessary in-game text\" numvotes=\"36\"/><result level=\"7\" value=\"Some necessary text - easily memorized or small crib sheet\" numvotes=\"5\"/><result level=\"8\" value=\"Moderate in-game text - needs crib sheet or paste ups\" numvotes=\"6\"/><result level=\"9\" value=\"Extensive use of text - massive conversion needed to be playable\" numvotes=\"0\"/><result level=\"10\" value=\"Unplayable in another language\" numvotes=\"0\"/></results></poll><link type=\"boardgamecategory\" id=\"1021\" value=\"Economic\"/><link type=\"boardgamecategory\" id=\"1026\" value=\"Negotiation\"/><link type=\"boardgamecategory\" id=\"1001\" value=\"Political\"/><link type=\"boardgamemechanic\" id=\"2080\" value=\"Area Control / Area Influence\"/><link type=\"boardgamemechanic\" id=\"2012\" value=\"Auction/Bidding\"/><link type=\"boardgamemechanic\" id=\"2072\" value=\"Dice Rolling\"/><link type=\"boardgamemechanic\" id=\"2040\" value=\"Hand Management\"/><link type=\"boardgamemechanic\" id=\"2020\" value=\"Simultaneous Action Selection\"/><link type=\"boardgamefamily\" id=\"10643\" value=\"Country: Germany\"/><link type=\"boardgamefamily\" id=\"34116\" value=\"Political: Elections\"/><link type=\"boardgamefamily\" id=\"91\" value=\"Valley Games Classic Line\"/><link type=\"boardgamedesigner\" id=\"1\" value=\"Karl-Heinz Schmiel\"/><link type=\"boardgameartist\" id=\"12517\" value=\"Marcus Gschwendtner\"/><link type=\"boardgamepublisher\" id=\"133\" value=\"Hans im Glück\"/><link type=\"boardgamepublisher\" id=\"2\" value=\"Moskito Spiele\"/><link type=\"boardgamepublisher\" id=\"5382\" value=\"Valley Games, Inc.\"/><statistics page=\"1\"><ratings><usersrated value=\"4725\"/><average value=\"7.64149\"/><bayesaverage value=\"7.20857\"/><ranks><rank type=\"subtype\" id=\"1\" name=\"boardgame\" friendlyname=\"Board Game Rank\" value=\"202\" bayesaverage=\"7.20857\"/><rank type=\"family\" id=\"5497\" name=\"strategygames\" friendlyname=\"Strategy Game Rank\" value=\"122\" bayesaverage=\"7.32557\"/></ranks><stddev value=\"1.59217\"/><median value=\"0\"/><owned value=\"5403\"/><trading value=\"182\"/><wanting value=\"524\"/><wishing value=\"1839\"/><numcomments value=\"1809\"/><numweights value=\"729\"/><averageweight value=\"4.3484\"/></ratings></statistics></item></items>";
    private static final ThingXmlParser thingXmlParser = new ThingXmlParser();

    @Test
    public void testParse() throws XmlPullParserException, IOException {
        InputStream thingInputStream = new ByteArrayInputStream(thingString.getBytes(StandardCharsets.UTF_8));
        Thing thing = thingXmlParser.parse(thingInputStream);
        assertEquals(TestUtil.dieMacher(), thing);
    }
}
