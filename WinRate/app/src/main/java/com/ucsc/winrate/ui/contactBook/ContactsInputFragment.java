package com.ucsc.winrate.ui.contactBook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ucsc.winrate.GameLogAdapter;
import com.ucsc.winrate.OpponentProfileAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.GameLogEntry;
import com.ucsc.winrate.table_entities.OpponentProfile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ContactsInputFragment extends Fragment{


    private String firstName, lastName, nickname;
    private ContactsInputViewModel contactsInputViewModel;
    private EditText firstNameText;
    private EditText lastNameText;
    private EditText nicknameText;

    private Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final OpponentProfileAdapter adapter = new OpponentProfileAdapter(getActivity());

        contactsInputViewModel = new ViewModelProvider(this).get(ContactsInputViewModel.class);
        View root = inflater.inflate(R.layout.fragment_contacts_input, container, false);

        contactsInputViewModel.getAllOpponentProfiles().observe(this, new Observer<List<OpponentProfile>>() {
            @Override
            public void onChanged(List<OpponentProfile> opponentProfiles) {
                adapter.setOpponentProfiles(opponentProfiles);
                //Toast.makeText(getActivity(), "onChanged called", Toast.LENGTH_SHORT).show();
            }
        });

        firstNameText = root.findViewById(R.id.contactsInputFirstName);
        lastNameText = root.findViewById(R.id.contactsInputLastName);
        nicknameText = root.findViewById(R.id.contactsInputNickname);
        submitButton = root.findViewById(R.id.submitContactButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstName = firstNameText.getText().toString();
                lastName = lastNameText.getText().toString();
                nickname = nicknameText.getText().toString();

               OpponentProfile newEntry = new OpponentProfile(firstName, lastName, nickname);

                WinRateRepository repository = new WinRateRepository(getActivity().getApplication());

                repository.insert(newEntry);

            }

            //showToast("Submitted!");
        });
        return root;
    }
}