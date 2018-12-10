package pw.akeirian.bgg_xml_api2_sqlite;

import lombok.Data;

@Data
public class ThingApiConfig extends ApiConfig {
    private final long id;
    private boolean includeStats;
    private ThingType type;

    public ThingApiConfig includeStats() {
        setIncludeStats(true);
        return this;
    }

    public ThingApiConfig type(ThingType type) {
        this.type = type;
        return this;
    }

    @androidx.annotation.NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(BGG_XML_API2_BASE)
                .append("thing")
                .append("?id=")
                .append(id);
        if (includeStats) {
            sb.append("&stats=1");
        }
        if (type != null) {
            sb.append("&type=")
                    .append(type.toString().toLowerCase());
        }
        return sb.toString();
    }
}
