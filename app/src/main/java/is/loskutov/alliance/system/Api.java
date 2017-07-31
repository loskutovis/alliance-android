package is.loskutov.alliance.system;

import android.os.AsyncTask;
import android.support.v4.util.ArrayMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

@SuppressWarnings (value = "unchecked")
public class Api<T, Q> {
    private static ApiResult delegate = null;
    private static final String API_URL = "http://al-74.ru/api/";

    private Api() {

    }

    public Api(ApiResult delegate){
        Api.delegate = delegate;
    }

    public void execute(String methodName) {
        ApiTask task = new ApiTask();

        task.execute(methodName);
    }

    public ArrayMap<Integer, String> getThemes() throws JSONException {
        ArrayMap<Integer, String> themesList = new ArrayMap<>();
        String json = Json.getJson(API_URL + "getThemes");

        JSONArray themes = new JSONArray(json);

        for (int i = 0; i < themes.length(); i++) {
            JSONObject theme = themes.getJSONObject(i);

            themesList.put(theme.getInt("id"), theme.getString("name"));
        }

        return themesList;
    }

    private class ApiTask extends AsyncTask<String, Void, ArrayMap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayMap<T, Q> doInBackground(String... methodName) {
            try {
                Method method = Api.class.getDeclaredMethod(methodName[0]);

                return (ArrayMap<T, Q>) method.invoke(new Api());
            }
            catch (Exception ex) {
                ex.printStackTrace();

                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayMap result) {
            super.onPostExecute(result);

            delegate.processFinish(result);
        }
    }
}
