package com.nextevent.API;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class AbelEventSingleton {

    private static AbelEventSingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private AbelEventSingleton(Context context) {
        this.context = context;
    }

    public static AbelEventSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new AbelEventSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

}
