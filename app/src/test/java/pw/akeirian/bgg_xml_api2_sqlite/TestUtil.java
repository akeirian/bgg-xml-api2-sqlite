package pw.akeirian.bgg_xml_api2_sqlite;

public final class TestUtil {
    public static Thing dieMacher() {
        Thing dieMacher = new Thing();
        dieMacher.setId(1);
        dieMacher.setType("boardgame");
        dieMacher.setName("Die Macher");
        dieMacher.setYearPublished(1986);
        dieMacher.setImage("https://cf.geekdo-images.com/original/img/vOttDcPBg1Tas9F6vFDhRmVaNH8=/0x0/pic159509.jpg");
        dieMacher.setThumbnail("https://cf.geekdo-images.com/thumb/img/RgXAhOreEqPeNiPpDPEUTwLm5Wk=/fit-in/200x150/pic159509.jpg");
        dieMacher.setDescription("Die Macher is a game about seven sequential political races in different regions of Germany. Players are in charge of national political parties, and must manage limited resources to help their party to victory. The winning party will have the most victory points after all the regional elections. There are four different ways of scoring victory points. First, each regional election can supply one to eighty victory points, depending on the size of the region and how well your party does in it. Second, if a party wins a regional election and has some media influence in the region, then the party will receive some media-control victory points. Third, each party has a national party membership which will grow as the game progresses and this will supply a fair number of victory points. Lastly, parties score some victory points if their party platform matches the national opinions at the end of the game.The 1986 edition featured four parties from the old West Germany and supported 3-4 players. The 1997 edition supports up to five players in the re-united Germany and updated several features of the rules as well. The 2006 edition also supports up to five players and adds a shorter five-round variant and additional rules updates by the original designer.");
        dieMacher.setMinPlayers(3);
        dieMacher.setMaxPlayers(5);
        dieMacher.setMinPlayTime(240);
        dieMacher.setMaxPlayTime(240);
        dieMacher.setUsersRated(4725);
        dieMacher.setBayesAverageRating(7.20857);
        dieMacher.setBoardGameRank(202);
        dieMacher.setAverageWeight(4.3484);
        return dieMacher;
    }
}
