package is.loskutov.alliance.system;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

import is.loskutov.alliance.models.Themes;

@SuppressWarnings (value = "unchecked")
public class Api<T> {
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

    public ArrayList<Themes> getThemes() throws JSONException {
        ArrayList<Themes> themesList = new ArrayList<>();
        String json = Json.getJson(API_URL + "getThemes");

        JSONArray themes = new JSONArray(json);

        for (int i = 0; i < themes.length(); i++) {
            themesList.add(new Themes(themes.getJSONObject(i)));
        }

        return themesList;
    }

    public void getQuestions(String type, int category) throws JSONException {
        String json = Json.getJson(API_URL + "getQuestions?type=" + type + "&category=" + category);

        JSONArray testing = new JSONArray(json);

        for (int i = 0; i < testing.length(); i++) {
            JSONObject questions = testing.getJSONObject(i);
        }
    }

    private class ApiTask extends AsyncTask<String, Void, ArrayList> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<T> doInBackground(String... methodName) {
            try {
                Method method = Api.class.getDeclaredMethod(methodName[0]);

                return (ArrayList<T>) method.invoke(new Api());
            }
            catch (Exception ex) {
                ex.printStackTrace();

                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList result) {
            super.onPostExecute(result);

            delegate.processFinish(result);
        }
    }
}
