package com.nextevent.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import io.ghyeok.stickyswitch.widget.StickySwitch;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nextevent.R;

import static com.nextevent.MainActivity.fab;

/**
 * @author Ghaith Darwish
 * @last Modified: 08/04/2020
 * @since 04/01/2020
 */
public class SettingFragment extends Fragment {

    StickySwitch stickySwitch;
    int layout;
    SharedPreferences.Editor editor;

    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        fab.hide();
        // Link with XML
        stickySwitch = view.findViewById(R.id.sticky_switch);
        // Create SharedPreferences
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        layout = sharedPreferences.getInt("LAYOUT", R.layout.event_item_model);
        // set the stickySwitch switch direction
        if (layout == R.layout.event_item_model)
            stickySwitch.setDirection(StickySwitch.Direction.LEFT);
        else
            stickySwitch.setDirection(StickySwitch.Direction.RIGHT);

        // stickySwitch listener
        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(StickySwitch.Direction direction, String s) {
                if (direction == StickySwitch.Direction.RIGHT) {
                    layout = R.layout.recycler_grid_layout;
                    Toast.makeText(getContext(), "Grid layout is displayed", Toast.LENGTH_SHORT).show();
                } else {
                    layout = R.layout.event_item_model;
                    Toast.makeText(getContext(), "List layout is displayed", Toast.LENGTH_SHORT).show();
                }

                editor.putInt("LAYOUT", layout);
                editor.apply();
            }
        });

        return view;
    }

}
