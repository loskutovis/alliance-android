package is.loskutov.alliance.model;

public class ApiMethod<T> {
    private String methodName;
    private T methodParams;

    public ApiMethod(String name, T params) {
        this.methodName = name;
        this.methodParams = params;
    }

    public ApiMethod(String name) {
        this.methodName = name;
        this.methodParams = null;
    }

    public String getMethodName() {
        return methodName;
    }

    public T getMethodParams() {
        return methodParams;
    }
}
