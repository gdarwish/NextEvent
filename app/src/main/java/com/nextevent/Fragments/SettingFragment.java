package com.nextevent.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;
import com.nextevent.API.AbelEventSingleton;
import com.nextevent.JavaBeans.EventLab;
import com.nextevent.R;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {


    public SettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        //Get All Events
        String url = "https://app.ticketmaster.com/discovery/v2/events.json?size=5&apikey=lPOOdRVEnSeJbTISehjlH2Ka529nW9m9";

        Log.d("API", "TEST START");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            EventLab[] events = new EventLab[5];
                            JSONArray results = response.getJSONObject("_embedded").getJSONArray("events");

                            for (int i = 0; i < results.length(); i++) {
                                JSONObject result = results.getJSONObject(i);
                                events[i] = new EventLab(result.getString("name"));
                                Log.d("API RESPONSE", "Event Name: " + events[i].eventName);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("API", "TEST FAIL");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getLocalizedMessage());
            }
        });

        AbelEventSingleton.getInstance(getContext()).getRequestQueue().add(request);

        return view;
    }

}
