package com.ucsc.winrate.ui.inputForm;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

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

    String playerDeck, opponentName, opponentDeck;

    EditText playerDeckText;
    EditText opponentNameText;
    EditText opponentDeckText;

    Button submitButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        inputFormViewModel = ViewModelProvider(this).get(InputFormViewModel.class);

        //Observer for cached database:
        final GameLogAdapter adapter = new GameLogAdapter(getActivity());
        inputFormViewModel.getAllGameLogEntries().observe(new Observer<java.util.List<GameLogEntry>>() {
            @Override
            public void onChanged(@Nullable final List<GameLogEntry> entries) {
                adapter.setGameLogEntries(entries);

            }
        });

        View root = inflater.inflate(R.layout.fragment_input_form, container, false);

        playerDeckText = root.findViewById(R.id.playerDeckText);
        opponentNameText = root.findViewById(R.id.opponentNameText);
        opponentDeckText = root.findViewById(R.id.opponentDeckText);


        submitButton = root.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                playerDeck = playerDeckText.getText().toString();
                opponentName = opponentNameText.getText().toString();
                opponentDeck = opponentDeckText.getText().toString();

                SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm:ss");

                String curDate = sdf.format(new Date());

                GameLogEntry newEntry = new GameLogEntry(curDate, true, opponentName, opponentDeck, playerDeck);

                WinRateRepository repository = new WinRateRepository(getActivity().getApplication());

                repository.insert(newEntry);



                showToast("Submitted!");

                //GameLogEntry theEntry = repository.getGameLogEntryByDate(curDate);

                //showToast(theEntry.getOpponentName());
                //showToast(playerDeck);
                //showToast(opponentName);
                //showToast(opponentDeck);

            }
        });

        //final TextView textView = root.findViewById(R.id.text_inputForm);
        //inputFormViewModel.getText().observe(this, new Observer<String>() {
        //    @Override
        //    public void onChanged(@Nullable String s) {
        //        textView.setText(s);
        //    }
        //});
        return root;
    }

    private void showToast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }


}
