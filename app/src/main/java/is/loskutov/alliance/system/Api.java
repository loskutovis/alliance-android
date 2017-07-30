package is.loskutov.alliance.system;

import android.support.v4.util.ArrayMap;

import org.json.JSONException;
import org.json.JSONObject;

public class Api {
    private static final String API_URL = "http://al-74.ru/api/";

    public static ArrayMap<Integer, String> getThemes() throws JSONException {
        ArrayMap themesList = new ArrayMap();

        JSONObject themes = new JSONObject(Json.getJson(API_URL + "getThemes"));


        return themesList;
    }
}
