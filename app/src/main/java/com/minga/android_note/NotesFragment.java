package com.minga.android_note;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    ArrayList<SimpleNote> simpleNotes = new ArrayList<>();
    private SimpleNote simpleNote;
    private static final String ARG_NOTE = "note";
    private boolean isLandscape;

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

            tv.setPadding(getResources().getDimensionPixelSize(R.dimen.padding_left),
                    getResources().getDimensionPixelSize(R.dimen.padding_top),
                    getResources().getDimensionPixelSize(R.dimen.padding_right),
                    getResources().getDimensionPixelSize(R.dimen.padding_bottom));
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    simpleNote = new SimpleNote(simpleNotes.get(fi).getTitle()
                            , simpleNotes.get(fi).getDesc()
                            , simpleNotes.get(fi).getDate());
                    showNotes(simpleNote);
                }

            });
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(ARG_NOTE,simpleNote);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        if (savedInstanceState != null) {
            simpleNote = savedInstanceState.getParcelable(ARG_NOTE);
        } else {
            simpleNote = simpleNotes.get(0);
        }

        if (isLandscape) {
            showLandNote(simpleNote);
        }
    }

    private void showNotes(SimpleNote simpleNote) {
        if (isLandscape) {
            showLandNote(simpleNote);
        } else {
            showPortNote(simpleNote);
        }
    }

    private void showLandNote(SimpleNote simpleNote) {
        NotesBlankFragment detail = NotesBlankFragment.newInstanse(simpleNote);
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.notes, detail);

        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }

    private void showPortNote(SimpleNote simpleNote) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), NotesActivity.class);
        intent.putExtra(NotesFragment.ARG_NOTE, simpleNote);
        startActivity(intent);
    }

    private void initNotes() {
        simpleNotes.add(new SimpleNote("First", "first entry", "19.02.2021"));
        simpleNotes.add(new SimpleNote("Second", "second entry", "19.02.2021"));
        simpleNotes.add(new SimpleNote("Third", "third entry", "19.02.2021"));
    }
}