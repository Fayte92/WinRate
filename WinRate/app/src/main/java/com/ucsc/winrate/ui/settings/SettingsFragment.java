package com.ucsc.winrate.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ucsc.winrate.DeckProfileAdapter;
import com.ucsc.winrate.GameLogAdapter;
import com.ucsc.winrate.OpponentProfileAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.DeckProfile;
import com.ucsc.winrate.table_entities.GameLogEntry;
import com.ucsc.winrate.table_entities.OpponentProfile;
import com.ucsc.winrate.ui.contactBook.ContactBookViewModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private SettingsViewModel settingsViewModel;
    private String[] populateOpponentNames = {"Andrew Leamy", "Arin Redd", "Yuhao Deng",
            "Chuanshi Zhu", "Julius Fan", "Iron Man", "Captain America", "The Hulk", "Thor",
            "Prof. Gelder", "The Man", "Black Widow", "Vision", "Scarlet Witch", "Mom", "Adam Warlock",
            "A Mysterious Stranger", "Hawkeye", "Ant-Man", "Deadpool", "Captain Marvel", "Your Evil Twin"};

    private String[] populateDeckNames = {"G/W Lifegain", "Vampire Tribal", "Eldrazi tron",
    "Mardu Aggro", "Izzet Burn", "Oko is OP", "Generic Aggro", "Gerneric Midrange", "Generic Control",
    "Ban Jace", "Unban Jace"};

    private Button populateGameLogsButton;
    private Button deleteGameLogsButton;
    private Button populateOpponentProfileButton;
    private Button deleteOpponentProfileButton;
    private Button populateDeckProfileButton;
    private Button deleteDeckProfileButton;

    private EditText InputUserName;
    private Button SaveName;
    private String curUserName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        populateGameLogsButton = root.findViewById(R.id.populateGameLogButton);
        deleteGameLogsButton = root.findViewById(R.id.deleteGameLogButton);
        populateOpponentProfileButton = root.findViewById(R.id.populateOpponentProfileButton);
        deleteOpponentProfileButton = root.findViewById(R.id.deleteOpponentProfileButton);
        populateDeckProfileButton = root.findViewById((R.id.populateDeckProfileButton));
        deleteDeckProfileButton = root.findViewById(R.id.deleteDeckProfileButton);

        populateGameLogsButton.setOnClickListener(this::onClick);
        deleteGameLogsButton.setOnClickListener(this::onClick);
        populateOpponentProfileButton.setOnClickListener(this::onClick);
        deleteOpponentProfileButton.setOnClickListener(this::onClick);
        populateDeckProfileButton.setOnClickListener(this::onClick);
        deleteDeckProfileButton.setOnClickListener(this::onClick);

        final GameLogAdapter gameLogAdapter = new GameLogAdapter(getActivity());
        final OpponentProfileAdapter opponentProfileAdapter = new OpponentProfileAdapter(getActivity());
        final DeckProfileAdapter deckProfileAdapter = new DeckProfileAdapter((getActivity()));

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        //Observer for game log entry table
        settingsViewModel.getAllGameLogEntries().observe(this, new Observer<List<GameLogEntry>>() {
            @Override
            public void onChanged(List<GameLogEntry> gameLogEntries) {
                gameLogAdapter.setGameLogEntries(gameLogEntries);
            }
        });

        //Observer for opponent profile table
        settingsViewModel.getAllOpponentProfiles().observe(this, new Observer<List<OpponentProfile>>() {
            @Override
            public void onChanged(List<OpponentProfile> opponentProfiles) {
                opponentProfileAdapter.setOpponentProfiles(opponentProfiles);
            }
        });

        //Observer for deck profile table
        settingsViewModel.getAllDeckProfiles().observe(this, new Observer<List<DeckProfile>>() {
            @Override
            public void onChanged(List<DeckProfile> deckProfiles) {
                deckProfileAdapter.setDeckProfiles(deckProfiles);
            }
        });


        InputUserName = root.findViewById(R.id.username);
        SaveName = root.findViewById(R.id.SaveName);


        SaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curUserName = InputUserName.getText().toString();
                Toast.makeText(getActivity(),curUserName, Toast.LENGTH_SHORT).show();
                SaveUserName();
            }
        });
        return root;
    }


    @Override
    public void onClick(View view) {
        WinRateRepository repository = new WinRateRepository(getActivity().getApplication());
        Random rand = new Random();
        int randName, randDeck, randOppDeck;
        switch (view.getId()) {
            case R.id.populateGameLogButton:
                //Disable for 10 seconds to prevent gamelog entries with duplicate dates
                populateGameLogsButton.setEnabled(false);
                populateGameLogsButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        populateGameLogsButton.setEnabled(true);
                    }
                }, 10000);

                SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm");
                SimpleDateFormat seconds = new SimpleDateFormat("ss");
                int offsetSeconds = Integer.parseInt(seconds.format(new Date()));
                String curDate = sdf.format(new Date());


                for (int i = 0; i < 10; i++){
                    String offsetDate = curDate + ":" + String.valueOf(offsetSeconds);
                    offsetSeconds++; //TODO add error handling for offset seconds going abouve 60
                    randName = rand.nextInt(populateOpponentNames.length);
                    randDeck = rand.nextInt(populateDeckNames.length);
                    randOppDeck = rand.nextInt(populateDeckNames.length);
                    GameLogEntry newEntry = new GameLogEntry(offsetDate, rand.nextBoolean(),
                            populateOpponentNames[randName], populateDeckNames[randDeck],
                            populateDeckNames[randOppDeck]);
                    repository.insert(newEntry);
                }

                showToast("10 Entries Added");
                break;
            case R.id.deleteGameLogButton:
                repository.deleteAllGameLogEntries();
                showToast("All Game Log Entries Deleted");
                break;
            case R.id.populateOpponentProfileButton:
                for (int i = 0; i < 10; i++){
                    randName = rand.nextInt(populateOpponentNames.length);
                    OpponentProfile newProfile = new OpponentProfile(populateOpponentNames[randName], "");
                    repository.insert(newProfile);
                }
                showToast("10 Opponent Profiles Added");
                break;
            case R.id.deleteOpponentProfileButton:
                repository.deleteAllOpponentProfils();
                showToast("All Opponent Profiles Deleted");
                break;
            case R.id.populateDeckProfileButton:

                for (int i = 0; i < 10; i++){
                    randName = rand.nextInt(populateOpponentNames.length);
                    randDeck = rand.nextInt(populateDeckNames.length);
                    DeckProfile newProfile = new DeckProfile(populateDeckNames[randDeck],
                            populateOpponentNames[randName],-1);
                    repository.insert(newProfile);
                }
                showToast("10 Deck Profiles Added");
                break;
            case R.id.deleteDeckProfileButton:
                repository.deleteAllDeckProfiles();
                showToast("All Deck Profiles Deleted");
                break;
        }
    }

    private void showToast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
    public void SaveUserName(){
        try {
            FileOutputStream fileOutputStream = getContext().getApplicationContext().openFileOutput("local_username.txt", Context.MODE_PRIVATE);
            fileOutputStream.write(curUserName.getBytes());
            fileOutputStream.close();

            InputUserName.setText("");
            Toast.makeText(getActivity(),"save successful",Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}