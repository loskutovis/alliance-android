package is.loskutov.alliance.system;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.Method;
import java.util.ArrayList;

import is.loskutov.alliance.model.Category;
import is.loskutov.alliance.model.Testing;
import is.loskutov.alliance.model.Themes;

@SuppressWarnings (value = "unchecked")
public class Api<T> {
    private static ApiResult delegate = null;
    private static final String API_URL = "http://al-74.ru/api/";
    public static final int CATEGORIES = 0, THEMES = 1, WEAPON = 2;

    private Api() {

    }

    public Api(ApiResult delegate){
        Api.delegate = delegate;
    }

    public void execute(String methodName) {
        ApiTask task = new ApiTask();

        task.execute(methodName);
    }

    private static String getTypeById(int id) {
        String type = "";

        switch (id) {
            case CATEGORIES:
                type = "category";
                break;
            case THEMES:
                type = "theme";
                break;
            case WEAPON:
                type = "weapon";
                break;
        }

        return type;
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

    public ArrayList<Testing> getTesting(Category category) throws JSONException {
        ArrayList<Testing> testingResult = new ArrayList<>();

        String url = API_URL + "getQuestions?type=" + Api.getTypeById(category.getCategory());

        if (category.getId() != 0) {
            url +=  "&id=" + category.getId();
        }

        String json = Json.getJson(url);

        JSONArray testing = new JSONArray(json);

        for (int i = 0; i < testing.length(); i++) {
            testingResult.add(new Testing(testing.getJSONObject(i)));
        }

        return testingResult;
    }

    public ArrayList<Testing> getTesting(int type) throws JSONException {
        Category category = new Category(0, type);

        return getTesting(category);
    }

    private class ApiTask extends AsyncTask<String, Void, ArrayList> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<T> doInBackground(String... params) {
            try {
                Method method = Api.class.getDeclaredMethod(params[0]);

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
