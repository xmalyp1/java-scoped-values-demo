package org.example;

public class DummyRequestContext {

    private String token;
    private String requestId;
    public DummyRequestContext() {
    }
    public DummyRequestContext(String requestId) {
        this.requestId = requestId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
