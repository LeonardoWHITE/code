package com.example.view;



import com.example.json.JsonHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class InsuranceView {
    public static final String MEDIA_JSON_TYPE = "application/json,charset=utf-8";

    private String message;
    private boolean success;
    private Map<String, Object> content;

    private InsuranceView() {

    }


    public InsuranceView(String message, boolean success, Map content) {
        this.message = message;
        this.success = success;
        this.content = content;
    }

    public static InsuranceView suceess(String message) {
        return new InsuranceView(message, true, new HashMap<>());
    }

    public static InsuranceView failure(String message) {
        return new InsuranceView(message, false, new HashMap<>());
    }

    public InsuranceView put(String key, Object value) {
        content.put(key, value);
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public void responseWebClient(HttpServletResponse response) {
        response.setContentType(MEDIA_JSON_TYPE);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JsonHelper.toJSON(this));
            writer.close();
        } catch (IOException e) {

        }

    }


//    public static void main(String[] args) {
//        InsuranceView view = InsuranceView.suceess("注册成功").put("username","莫家健");
//        String json = JsonHelper.toJSON(view);
//        System.out.println("json" + json);
//    }

}
