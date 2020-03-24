package com.nextevent.API;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nextevent.Fragments.EventsFragment;

import org.json.JSONObject;

import java.util.Map;

public class EventSingleton {

    private static EventSingleton instance;
    private int statusCode;
    private Map<String, String> headers;
    private Context context;
    private RequestQueue queue;
    private boolean debugMode = false;

    public EventSingleton(Context context) {
        this.context = context;
        queue = Volley.newRequestQueue(context);
    }

    public static EventSingleton getInstance(Context context) {
        if (instance == null)
            instance = new EventSingleton(context);
        return instance;
    }

    public <T extends APIResponse> void jsonObjectRequest(int method, String url, JSONObject jsonObject, final EventsFragment callback) {
        JsonObjectRequest request = new JsonObjectRequest(method, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                callback.onSuccess(response, statusCode);
                if (debugMode) {
                    Log.e("TAG", "Status: " + statusCode);
                    Log.e("TAG", response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFailure(error, statusCode);
                if (debugMode) {
                    Log.e("TAG", "Status: " + statusCode);
                    Log.e("TAG", error.toString());
                }
            }
        }) {
            public Map<String, String> getHeaders() throws AuthFailureError {
                if (headers == null) return super.getHeaders();
                return headers;
            }
        };
        queue.add(request);
    }

    public EventSingleton setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public EventSingleton setDebugMode(boolean mode) {
        debugMode = mode;
        return this;
    }
}
