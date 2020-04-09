package com.nextevent.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextevent.R;

import static com.nextevent.MainActivity.fab;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreditsFragment extends Fragment {

    public CreditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_credits, container, false);
        fab.hide();

        return view;
    }
}
