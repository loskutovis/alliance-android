package is.loskutov.alliance.system;

import android.support.v4.util.ArrayMap;

public interface ApiResult<T, Q> {
    void processFinish(ArrayMap<T, Q> output);
}