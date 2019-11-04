package com.ucsc.winrate.ui.contactBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ucsc.winrate.R;

public class ContactsFragment extends Fragment{

    private ContactsViewModel contactsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactsViewModel =
                ViewModelProviders.of(this).get(ContactsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        //final TextView textView = root.findViewById(R.id.text_contacts);
        //contactsViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});
        return root;
    }
}