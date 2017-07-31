package is.loskutov.alliance.system;

import android.os.AsyncTask;
import android.support.v4.util.ArrayMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

@SuppressWarnings (value = "unchecked")
public class Api<T, Q> {
    private static final String API_URL = "http://al-74.ru/api/";
    private ArrayMap<T, Q> response;

    public void execute(String methodName) {
        ApiTask task = new ApiTask();

        task.execute(methodName);
    }

    public void getThemes() throws JSONException {
        ArrayMap<Integer, String> themesList = new ArrayMap<>();

        String json = Json.getJson(API_URL + "getThemes");

        JSONArray themes = new JSONArray(json);

        for (int i = 0; i < themes.length(); i++) {
            JSONObject theme = themes.getJSONObject(i);

            themesList.put(Integer.parseInt(theme.getString("id")), theme.getString("name"));
        }

        this.response = (ArrayMap<T, Q>) themesList;
    }

    private static class ApiTask extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... methodName) {
            try {
                Method method = Api.class.getDeclaredMethod(methodName[0]);

                method.invoke(Api.class);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
        }
    }
}
