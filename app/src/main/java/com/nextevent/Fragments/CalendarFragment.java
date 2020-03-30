package com.nextevent.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nextevent.CalendarRecyclerViewAdapter;
import com.nextevent.JavaBeans.CalendarMonth;
import com.nextevent.R;

import org.threeten.bp.YearMonth;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalendarFragment extends Fragment {


    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

//        CalendarView calendarView = view.findViewById(R.id.calendarView);
//        calendarView.setDayBinder(new CustomDayBinder());
//
//        YearMonth currentMonth = YearMonth.now();
//        YearMonth firstMonth = currentMonth.minusMonths(10);
//        YearMonth lastMonth = currentMonth.plusMonths(10);
//        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
//
//        calendarView.setup(firstMonth, lastMonth, firstDayOfWeek);
//        calendarView.setHasBoundaries(true);
//        calendarView.setScrollMode(ScrollMode.PAGED);
//        calendarView.setOrientation(RecyclerView.HORIZONTAL);
//        calendarView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
//        calendarView.setMaxRowCount(6);
//        calendarView.scrollToMonth(currentMonth);

        ArrayList<CalendarMonth> calendarMonths = new ArrayList<>();

        YearMonth currentMonth = YearMonth.now();

        for (int i = 0; i < 10; i++) {
            calendarMonths.add(new CalendarMonth(currentMonth.plusMonths(i), null));
        }

        RecyclerView recyclerView = view.findViewById(R.id.calendarRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(new CalendarRecyclerViewAdapter(calendarMonths));

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        return view;
    }

}
