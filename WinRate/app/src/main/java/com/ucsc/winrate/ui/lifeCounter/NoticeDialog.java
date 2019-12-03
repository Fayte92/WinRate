package com.ucsc.winrate.ui.lifeCounter;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ucsc.winrate.GameLogAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.WinRateRepository;
import com.ucsc.winrate.table_entities.GameLogEntry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NoticeDialog extends AppCompatDialogFragment {
    private TextView testopponame;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String myDeck, oName, opponentDeck,winCondition;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.lifecounter_save_result, null);

        Bundle curDATA = this.getArguments();

        myDeck = curDATA.getString("myDeck");
        opponentDeck = curDATA.getString("opponentDeck");

        oName = curDATA.getString("opponentName");

        SimpleDateFormat sdf = new SimpleDateFormat("MM dd yyyy HH:mm:ss");
        String curDate = sdf.format(new Date());

        winCondition = curDATA.getString("winCondition");



        final GameLogAdapter adapter = new GameLogAdapter(getActivity());

        NoticeDialogViewModel noticeDialogViewModel = new ViewModelProvider(this).get(NoticeDialogViewModel.class);

        noticeDialogViewModel.getAllGameLogEntries().observe(this, new Observer<List<GameLogEntry>>() {
            @Override
            public void onChanged(List<GameLogEntry> gameLogEntries) {
                adapter.setGameLogEntries(gameLogEntries);
            }
        });

        builder.setView(view).setTitle("Record This Game ?")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Boolean winLose = Boolean.valueOf(winCondition);
                        GameLogEntry newEntry = new GameLogEntry(curDate, winLose, oName, opponentDeck, myDeck);

                        WinRateRepository repository = new WinRateRepository(getActivity().getApplication());
                        repository.insert(newEntry);
                        Toast.makeText(getActivity(),"Save Successful!",Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }


//    @Override
//    public void applyTexts(String oname){
//            testopponame.setText(oname);
//    }
}
