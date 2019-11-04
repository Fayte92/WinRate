package com.ucsc.winrate.ui.lifeCounter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ucsc.winrate.R;
import com.ucsc.winrate.ui.contactBook.ContactBookViewModel;

public class LifeCounterFragment extends Fragment{

    private LifeCounterViewModel lifeCounterViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lifeCounterViewModel =
                ViewModelProviders.of(this).get(LifeCounterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_life_counter, container, false);
        //final TextView textView = root.findViewById(R.id.text_lifeCounter);
        //lifeCounterViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});
        return root;
    }
}