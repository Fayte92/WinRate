package com.ucsc.winrate.ui.contactBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucsc.winrate.R;

public class ContactsFragment extends Fragment implements View.OnClickListener{

    private ContactsViewModel contactsViewModel;
    private FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        fab = root.findViewById(R.id.contactsInputButton);
        fab.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_contact_fragment);
        switch (view.getId()) {
            case R.id.contactsInputButton:
                navController.navigate(R.id.navContactsInput);
                break;
        }
    }
}