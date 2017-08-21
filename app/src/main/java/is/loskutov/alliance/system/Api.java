package is.loskutov.alliance.system;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

import is.loskutov.alliance.model.ApiMethod;
import is.loskutov.alliance.model.Category;
import is.loskutov.alliance.model.Testing;
import is.loskutov.alliance.model.Themes;

@SuppressWarnings (value = "unchecked")
public class Api<T, Q> {
    private static ApiResult delegate = null;
    private static final String API_URL = "http://al-74.ru/api/";
    public static final int CATEGORIES = 0, THEMES = 1, WEAPON = 2;

    private Api() {

    }

    public Api(ApiResult delegate){
        Api.delegate = delegate;
    }

    public void execute(ApiMethod<Q> method) {
        ApiTask task = new ApiTask();

        task.execute(method);
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

        JSONObject testing = new JSONObject(json);

        testingResult.add(new Testing(testing));

        return testingResult;
    }

    public ArrayList<Testing> getTesting(int type) throws JSONException {
        Category category = new Category(0, type);

        return getTesting(category);
    }

    private class ApiTask extends AsyncTask<ApiMethod, Void, ArrayList> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<T> doInBackground(ApiMethod... invokeMethod) {
            ArrayList<T> result;

            try {
                Method method;
                Q params = (Q) invokeMethod[0].getMethodParams();

                if (params == null) {
                    method = Api.class.getDeclaredMethod(invokeMethod[0].getMethodName());
                    result = (ArrayList<T>) method.invoke(new Api());
                }
                else {
                    Class[] methodParams = new Class[1];
                    methodParams[0] = params.getClass();

                    method = Api.class.getDeclaredMethod(invokeMethod[0].getMethodName(), methodParams);
                    result = (ArrayList<T>) method.invoke(new Api(), params);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();

                result = null;
            }

            return result;
        }

        @Override
        protected void onPostExecute(ArrayList result) {
            super.onPostExecute(result);

            delegate.processFinish(result);
        }
    }
}
