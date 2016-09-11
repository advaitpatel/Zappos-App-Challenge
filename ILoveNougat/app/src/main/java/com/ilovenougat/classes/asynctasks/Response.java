package com.ilovenougat.classes.asynctasks;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Advait on 9/11/2016.
 */
public class Response {
    private String originalTerm;
    private String currentResultCount;
    private String totalResultCount;
    private String term;
    private Object results;
    private int statusCode;

    public String getOriginalTerm() {
        return this.originalTerm;
    }

    public String getCurrentResultCount() {
        return this.currentResultCount;
    }

    public String getTotalResultCount() {
        return this.totalResultCount;
    }

    public String getTerm() {
        return this.term;
    }

    public Object getResults() {
        return this.results;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

}
