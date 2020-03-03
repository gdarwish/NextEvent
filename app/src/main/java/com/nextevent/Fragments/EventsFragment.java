package com.nextevent.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nextevent.JavaBeans.CustomRecyclerviewAdapter;
import com.nextevent.JavaBeans.EventModel;
import com.nextevent.R;

import java.util.ArrayList;

import static com.nextevent.MainActivity.fab;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    RecyclerView recyclerView;
    CustomRecyclerviewAdapter adapter;
    ArrayList<EventModel> events = new ArrayList<>();
    EditText searBar;


    public EventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);

        fab.hide();
        searBar = view.findViewById(R.id.searchBar);
        recyclerView = view.findViewById(R.id.eventRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        events.add(new EventModel("Ghaith", "Ghaith", "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQUVJQyaDhAU2TrCTE86fMkrchtrGkGd8zmO_mFMWcZ83fGs7Oh"));
        events.add(new EventModel("Ghaith", "Ghaith", "https://lh3.googleusercontent.com/proxy/Oz9rjqVE1WcapCGRU4ei4xZbRTf0Ca7TeZTW8mLuv4wLV_6BILgDhkcCB23ChxU5x597MLFKrCSSLNcDiFIW_12gJEUREVOG08L2bMngmrNAnNvzx7BvGkE"));
        events.add(new EventModel("Ghaith", "Ghaith", "https://fintechbelgium.be/wp-content/uploads/2018/06/https___image.flaticon.com_icons_png_512_314_314550-480x480.png"));
        events.add(new EventModel("Ghaith", "Ghaith", "https://i.dlpng.com/static/png/4614747_thumb.png"));
        events.add(new EventModel("Ghaith", "Ghaith", "https://sustainablecortland00.files.wordpress.com/2013/07/event-icon.png"));
        events.add(new EventModel("Ghaith", "Ghaith", "https://www.iconninja.com/files/618/341/273/chart-event-calendar-schedule-business-plan-month-icon.svg"));
        events.add(new EventModel("Ghaith", "Ghaith", "https://www.transparentpng.com/thumb/calendar/red-calendar-events-icon-png-9.png"));
        events.add(new EventModel("Ghaith", "Ghaith", "https://images.squarespace-cdn.com/content/v1/5c0887728ab7228323e0d320/1572381330660-GVXS072JMAGOCZZOHW9P/ke17ZwdGBToddI8pDm48kLxnK526YWAH1qleWz-y7AFZw-zPPgdn4jUwVcJE1ZvWEtT5uBSRWt4vQZAgTJucoTqqXjS3CfNDSuuf31e0tVFUQAah1E2d0qOFNma4CJuw0VgyloEfPuSsyFRoaaKT76QvevUbj177dmcMs1F0H-0/BrandAlive-Icon-Inhouse2.png"));
        events.add(new EventModel("Ghaith", "Ghaith", "https://i.dlpng.com/static/png/4614724_thumb.png"));
        // creating new instance of CustomRecyclerviewAdapter
        adapter = new CustomRecyclerviewAdapter(events, getActivity());
        // Setting the adapter for the recyclerview
        recyclerView.setAdapter(adapter);

        searBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // we will use this method to get the result of the search when we implement the API

            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        return view;
    }

}
