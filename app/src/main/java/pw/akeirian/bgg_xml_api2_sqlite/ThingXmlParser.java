package pw.akeirian.bgg_xml_api2_sqlite;

import android.text.Html;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

public class ThingXmlParser extends XmlParser {
    private static final String ITEMS_TAG = "items";
    private static final String ITEM_TAG = "item";
    private static final String ITEM_ID_ATTRIBUTE = "id";
    private static final String ITEM_TYPE_ATTRIBUTE = "type";
    private static final String THUMBNAIL_TAG = "thumbnail";
    private static final String IMAGE_TAG = "image";
    private static final String NAME_TAG = "name";
    private static final String NAME_TYPE_ATTRIBUTE = "type";
    private static final String PRIMARY_NAME_TYPE = "primary";
    private static final String DESCRIPTION_TAG = "description";
    private static final String YEAR_PUBLISHED_TAG = "yearpublished";
    private static final String MIN_PLAYERS_TAG = "minplayers";
    private static final String MAX_PLAYERS_TAG = "maxplayers";
    private static final String MIN_PLAY_TIME_TAG = "minplaytime";
    private static final String MAX_PLAY_TIME_TAG = "maxplaytime";
    private static final String STATISTICS_TAG = "statistics";
    private static final String RATINGS_TAG = "ratings";
    private static final String USERS_RATED_TAG = "usersrated";
    private static final String BAYES_AVERAGE_TAG = "bayesaverage";
    private static final String RANKS_TAG = "ranks";
    private static final String RANK_TAG = "rank";
    private static final String RANK_ID_ATTRIBUTE = "id";
    private static final String BOARD_GAME_RANK_ID = "1";
    private static final String AVERAGEWEIGHT_TAG = "averageweight";

    public Thing parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            Thing thing = new Thing();
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            parser.require(XmlPullParser.START_TAG, null, ITEMS_TAG);
            while (parser.next() != XmlPullParser.END_TAG) {
                if (parser.getEventType() != XmlPullParser.START_TAG) {
                    continue;
                }
                switch (parser.getName()) {
                    case ITEM_TAG:
                        parser.require(XmlPullParser.START_TAG, null, ITEM_TAG);
                        thing.setId(readAttributeInteger(parser, ITEM_ID_ATTRIBUTE));
                        thing.setType(readAttributeString(parser, ITEM_TYPE_ATTRIBUTE));
                        while (parser.next() != XmlPullParser.END_TAG) {
                            if (parser.getEventType() != XmlPullParser.START_TAG) {
                                continue;
                            }
                            switch (parser.getName()) {
                                case THUMBNAIL_TAG:
                                    thing.setThumbnail(readTagString(parser, THUMBNAIL_TAG));
                                    break;
                                case IMAGE_TAG:
                                    thing.setImage(readTagString(parser, IMAGE_TAG));
                                    break;
                                case NAME_TAG:
                                    if (PRIMARY_NAME_TYPE
                                            .equals(parser.getAttributeValue(null, NAME_TYPE_ATTRIBUTE))) {
                                        thing.setName(readTagString(parser, NAME_TAG));
                                    } else {
                                        skip(parser);
                                    }
                                    break;
                                case DESCRIPTION_TAG:
                                    thing.setDescription(
                                            Html.fromHtml(readTagString(parser, DESCRIPTION_TAG)).toString());
                                    break;
                                case YEAR_PUBLISHED_TAG:
                                    Integer yearPublished = readTagInteger(parser, YEAR_PUBLISHED_TAG);
                                    if (yearPublished != null) {
                                        thing.setYearPublished(yearPublished);
                                    }
                                    break;
                                case MIN_PLAYERS_TAG:
                                    Integer minPlayers = readTagInteger(parser, MIN_PLAYERS_TAG);
                                    if (minPlayers != null) {
                                        thing.setMinPlayers(minPlayers);
                                    }
                                    break;
                                case MAX_PLAYERS_TAG:
                                    Integer maxPlayers = readTagInteger(parser, MAX_PLAYERS_TAG);
                                    if (maxPlayers != null) {
                                        thing.setMaxPlayers(maxPlayers);
                                    }
                                    break;
                                case MIN_PLAY_TIME_TAG:
                                    Integer minPlayTime = readTagInteger(parser, MIN_PLAY_TIME_TAG);
                                    if (minPlayTime != null) {
                                        thing.setMinPlayTime(minPlayTime);
                                    }
                                    break;
                                case MAX_PLAY_TIME_TAG:
                                    Integer maxPlayTime = readTagInteger(parser, MAX_PLAY_TIME_TAG);
                                    if (maxPlayTime != null) {
                                        thing.setMaxPlayTime(maxPlayTime);
                                    }
                                    break;
                                case STATISTICS_TAG:
                                    parser.require(XmlPullParser.START_TAG, null, STATISTICS_TAG);
                                    while (parser.next() != XmlPullParser.END_TAG) {
                                        if (parser.getEventType() != XmlPullParser.START_TAG) {
                                            continue;
                                        }
                                        switch (parser.getName()) {
                                            case RATINGS_TAG:
                                                parser.require(XmlPullParser.START_TAG, null, RATINGS_TAG);
                                                while (parser.next() != XmlPullParser.END_TAG) {
                                                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                                                        continue;
                                                    }
                                                    switch (parser.getName()) {
                                                        case USERS_RATED_TAG:
                                                            Integer usersRated = readTagInteger(parser, USERS_RATED_TAG);
                                                            if (usersRated != null) {
                                                                thing.setUsersRated(usersRated);
                                                            }
                                                            break;
                                                        case BAYES_AVERAGE_TAG:
                                                            Double bayesAverage =
                                                                    readTagDouble(parser, BAYES_AVERAGE_TAG);
                                                            if (bayesAverage != null) {
                                                                thing.setBayesAverageRating(bayesAverage);
                                                            }
                                                            break;
                                                        case RANKS_TAG:
                                                            parser.require(XmlPullParser.START_TAG, null, RANKS_TAG);
                                                            while (parser.next() != XmlPullParser.END_TAG) {
                                                                if (parser.getEventType() != XmlPullParser.START_TAG) {
                                                                    continue;
                                                                }
                                                                parser.require(XmlPullParser.START_TAG, null, RANK_TAG);
                                                                if (BOARD_GAME_RANK_ID
                                                                        .equals(parser.getAttributeValue(
                                                                                null, RANK_ID_ATTRIBUTE))) {
                                                                    Integer boardGameRank =
                                                                            readTagInteger(parser, RANK_TAG);
                                                                    if (boardGameRank != null) {
                                                                        thing.setBoardGameRank(boardGameRank);
                                                                    }
                                                                } else {
                                                                    skip(parser);
                                                                }
                                                            }
                                                            parser.require(XmlPullParser.END_TAG, null, RANKS_TAG);
                                                            break;
                                                        case AVERAGEWEIGHT_TAG:
                                                            Double averageWeight =
                                                                    readTagDouble(parser, AVERAGEWEIGHT_TAG);
                                                            if (averageWeight != null) {
                                                                thing.setAverageWeight(averageWeight);
                                                            }
                                                            break;
                                                        default:
                                                            skip(parser);
                                                            break;
                                                    }
                                                }
                                                parser.require(XmlPullParser.END_TAG, null, RATINGS_TAG);
                                                break;
                                            default:
                                                skip(parser);
                                                break;
                                        }
                                    }
                                    parser.require(XmlPullParser.END_TAG, null, STATISTICS_TAG);
                                    break;
                                default:
                                    skip(parser);
                                    break;
                            }
                        }
                        break;
                    default:
                        skip(parser);
                        break;
                }
            }
            return thing;
        } finally {
            in.close();
        }
    }
}
