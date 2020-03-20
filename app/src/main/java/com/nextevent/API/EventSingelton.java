package com.nextevent.API;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class EventSingelton {

    private static EventSingelton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private EventSingelton(Context context) {
        this.context = context;
    }

    public static EventSingelton getInstance(Context context) {
        if (instance == null) {
            instance = new EventSingelton(context);
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
