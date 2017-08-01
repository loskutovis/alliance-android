package is.loskutov.alliance.system;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;

public interface ApiResult<T, Q> {
    void processFinish(ArrayList output);
}