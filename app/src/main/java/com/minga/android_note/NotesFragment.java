package com.minga.android_note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    ArrayList<SimpleNote> simpleNotes = new ArrayList<>();
    private static final String ARG_NOTE = "note";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initNotes();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;

        for (int i = 0; i < simpleNotes.size(); i++) {
            String note_title = simpleNotes.get(i).getTitle();
            TextView tv = new TextView(getContext());
            tv.setText(note_title);
            tv.setTextSize(40);
            tv.setPadding(32, 16, 16, 0);
            layoutView.addView(tv);
        }
    }

    private void initNotes() {
        simpleNotes.add(new SimpleNote("First", "first entry", "19.02.2021"));
        simpleNotes.add(new SimpleNote("Second", "second entry", "19.02.2021"));
        simpleNotes.add(new SimpleNote("Third", "third entry", "19.02.2021"));
    }
}