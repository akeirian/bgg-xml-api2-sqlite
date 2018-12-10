package pw.akeirian.bgg_xml_api2_sqlite;

import lombok.Data;

@Data
public class Thing {
    private long id;
    private String type;
    private String name;
    private int yearPublished;
    private String image;
    private String thumbnail;
    private String description;
    private int minPlayers;
    private int maxPlayers;
    private int minPlayTime;
    private int maxPlayTime;
    private int usersRated;
    private double bayesAverageRating;
    private int boardGameRank;
    private double averageWeight;
}
