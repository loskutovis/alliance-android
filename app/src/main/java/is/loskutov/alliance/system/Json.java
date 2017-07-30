package is.loskutov.alliance.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Json {

    public static String getJson(String url) {
        HttpURLConnection c = null;

        try {
            URL u = new URL(url);

            c = (HttpURLConnection) u.openConnection();

            c.setRequestMethod("GET");
            c.setRequestProperty("Content-Type", "application/json");
            c.connect();

            int status = c.getResponseCode();

            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }

                    br.close();

                    return sb.toString();
            }

        } catch (Exception ex) {
            if (c != null) {
                c.disconnect();
            }
        }

        return null;
    }
}
