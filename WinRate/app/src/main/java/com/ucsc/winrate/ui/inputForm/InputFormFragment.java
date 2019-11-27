package com.ucsc.winrate.ui.inputForm;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ucsc.winrate.GameLogAdapter;
import com.ucsc.winrate.MainActivity;
import com.ucsc.winrate.R;
import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.GameLogEntry;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class InputFormFragment extends Fragment{

    private InputFormViewModel inputFormViewModel;

    private String playerDeck, opponentName, opponentDeck;

     private EditText playerDeckText;
     private EditText opponentNameText;
     private EditText opponentDeckText;

    private Button submitButton;
    private Button viewTableButton;

    private Switch winSwitch;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final GameLogAdapter adapter = new GameLogAdapter(getActivity());

        inputFormViewModel = new ViewModelProvider(this).get(InputFormViewModel.class);

        inputFormViewModel.getAllGameLogEntries().observe(this, new Observer<List<GameLogEntry>>() {
            @Override
            public void onChanged(List<GameLogEntry> gameLogEntries) {
                adapter.setGameLogEntries(gameLogEntries);
                //Toast.makeText(getActivity(), "onChanged called", Toast.LENGTH_SHORT).show();
            }
        });

        View root = inflater.inflate(R.layout.fragment_input_form, container, false);

        playerDeckText = root.findViewById(R.id.playerDeckText);
        opponentNameText = root.findViewById(R.id.opponentNameText);
        opponentDeckText = root.findViewById(R.id.opponentDeckText);
        winSwitch = root.findViewById(R.id.winSwitch);


        submitButton = root.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playerDeck = playerDeckText.getText().toString();
                opponentName = opponentNameText.getText().toString();
                opponentDeck = opponentDeckText.getText().toString();


                SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm:ss");

                String curDate = sdf.format(new Date());

                GameLogEntry newEntry = new GameLogEntry(curDate, winSwitch.isChecked(), opponentName, opponentDeck, playerDeck);

                WinRateRepository repository = new WinRateRepository(getActivity().getApplication());

                repository.insert(newEntry);

//                if(adapter.getItemCount() > 0){
//                    //Display the date of the most recently added entry
//                    String entryDate = adapter.getAllGameLogEntries().get(0).getDate();
//                    showToast(entryDate);
//                }

                playerDeckText.setText("");
                opponentNameText.setText("");
                opponentDeckText.setText("");

                showToast("Game Log Entry Submitted!");
            }
        });

        viewTableButton = root.findViewById(R.id.viewTableButton);
        viewTableButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.navGameLogTable);
                }

        });

        return root;
    }

    private void showToast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


}
