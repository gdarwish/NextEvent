package com.nextevent.JavaBeans;

import com.nextevent.Fragments.DetailEventFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * @author Ghaith Darwish
 * @Last Modified: 04/07/2020
 * @since 04/07/2020
 */
public class ViewPagerDetailAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Event> events;

    public ViewPagerDetailAdapter(@NonNull FragmentManager fm, ArrayList<Event> events) {
        super(fm);
        this.events = events;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // return newInstance of DetailEventFragment with the event position
        return DetailEventFragment.newInstance(events.get(position));
    }

    @Override
    public int getItemPosition(@NonNull Object event) {
        if (events.contains(event)) {
            return events.indexOf(event);
        } else {
            return POSITION_NONE;
        }
    }

    @Override
    public int getCount() {
        return events.size();
    }
}
