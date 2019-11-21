package com.ucsc.winrate.ui.contactBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ucsc.winrate.GameLogAdapter;
import com.ucsc.winrate.OpponentProfileAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.table_entities.GameLogEntry;
import com.ucsc.winrate.table_entities.OpponentProfile;
import com.ucsc.winrate.ui.inputForm.GameLogTableViewModel;

import java.util.List;

public class ContactBookFragment extends Fragment implements View.OnClickListener {

    private ContactBookViewModel contactBookViewModel;
    private Button contactsButton;
    private Button decksButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        contactBookViewModel =
                new ViewModelProvider(this).get(ContactBookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contact_book, container, false);

        contactsButton = root.findViewById(R.id.contactsButton);
        contactsButton.setOnClickListener(this);
        decksButton = root.findViewById(R.id.decksButton);
        decksButton.setOnClickListener(this);
        //final TextView textView = root.findViewById(R.id.text_contactBook);
        //contactBookViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});

        return root;
    }

    @Override
    public void onClick(View view) {
        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_contact_fragment);
        switch (view.getId()) {
            case R.id.contactsButton:
                navController.navigate(R.id.navContacts);
                break;
            case R.id.decksButton:
                navController.navigate(R.id.navDecks);
                break;
        }
    }
}