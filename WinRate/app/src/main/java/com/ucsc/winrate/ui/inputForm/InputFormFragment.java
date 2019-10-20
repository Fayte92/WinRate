package com.ucsc.winrate.ui.inputForm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsc.winrate.R;

public class InputFormFragment extends Fragment{

    private InputFormViewModel inputFormViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inputFormViewModel =
                ViewModelProviders.of(this).get(InputFormViewModel.class);
        View root = inflater.inflate(R.layout.fragment_input_form, container, false);
        final TextView textView = root.findViewById(R.id.text_inputForm);
        inputFormViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
