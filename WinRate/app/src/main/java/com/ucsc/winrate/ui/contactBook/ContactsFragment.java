package com.ucsc.winrate.ui.contactBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ucsc.winrate.OpponentProfileAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.OpponentProfile;

import java.util.ArrayList;
import java.util.List;

public class ContactsFragment extends Fragment implements View.OnClickListener {

    private ContactsViewModel contactsViewModel;
    private FloatingActionButton fab;
    private ListView listView;
    private ArrayList<String> contactNamesArray;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final OpponentProfileAdapter adapter = new OpponentProfileAdapter(getActivity());
        contactsViewModel = new ViewModelProvider(this).get(ContactsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts, container, false);
        fab = root.findViewById(R.id.contactsInputButton);
        fab.setOnClickListener(this);

        listView = (ListView) root.findViewById(R.id.contactsListView);
        contactNamesArray = new ArrayList<String>();

        contactsViewModel.getAllOpponentProfiles().observe(this, new Observer<List<OpponentProfile>>() {
            @Override
            public void onChanged(List<OpponentProfile> opponentProfiles) {
                adapter.setOpponentProfiles(opponentProfiles);
            }
        });

        // test code for putting dummy data into database without using entry form
        ///OpponentProfile newEntry = new OpponentProfile("test", "test2", "test3");
        ///WinRateRepository repository = new WinRateRepository(getActivity().getApplication());
        // repository.insert(newEntry);

        for (int i = 0; i < adapter.getAllOpponentProfiles().size(); i++) {
            contactNamesArray.set(i, (String) adapter.getAllOpponentProfiles().get(i).getFirstName() + " " + adapter.getAllOpponentProfiles().get(i).getLastName());
        }

        if (contactNamesArray.size() > 0) {
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, contactNamesArray);
        listView.setAdapter(listViewAdapter);
        }

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