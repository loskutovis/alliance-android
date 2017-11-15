package is.loskutov.alliance.system;

import java.util.ArrayList;

public interface ApiResult<T, Q> {
    void processFinish(ArrayList output);
}