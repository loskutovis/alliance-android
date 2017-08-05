package is.loskutov.alliance.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Themes {
    private int id;
    private String name;

    public Themes(JSONObject theme) throws JSONException {
        this.id = theme.getInt("id");
        this.name = theme.getString("name");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
