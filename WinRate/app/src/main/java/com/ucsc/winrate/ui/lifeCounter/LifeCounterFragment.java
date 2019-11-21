package com.ucsc.winrate.ui.lifeCounter;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ucsc.winrate.OpponentProfileAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.table_entities.OpponentProfile;
import com.ucsc.winrate.ui.contactBook.ContactBookViewModel;

import java.util.List;

public class LifeCounterFragment extends Fragment{

    private LifeCounterViewModel lifeCounterViewModel;

    private TextView mylife;
    private TextView opponentlife;
    int mlife = 20;
    int olife = 20;
    private Button myp;
    private Button oppp;
    private Button mym;
    private Button oppm;
    private TextView myname;
    private TextView mydeck;
    private TextView opponame;
    private TextView oppodeck;
    private OpponentProfile curProfile;
    //private OpponentProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        lifeCounterViewModel =
                new ViewModelProvider(this).get(LifeCounterViewModel.class);
        View root = inflater.inflate(R.layout.fragment_life_counter, container, false);

        final OpponentProfileAdapter adapter = new OpponentProfileAdapter(getActivity());
        /*observer for opponent profiles table cache*/
        lifeCounterViewModel.getAllOpponentProfiles().observe(this, new Observer<List<OpponentProfile>>() {
            @Override
            public void onChanged(List<OpponentProfile> opponentProfiles) {
                adapter.setOpponentProfiles(opponentProfiles);
            }
        });


        mylife = root.findViewById(R.id.mylife);
        opponentlife = root.findViewById(R.id.opponentlife);
        myname = root.findViewById(R.id.myname);
        mydeck = root.findViewById(R.id.mydeck);
        opponame = root.findViewById(R.id.opponame);
        oppodeck = root.findViewById(R.id.oppodeck);

        /*Set my name equal to first name of first opponent profile entry*/
        if(!adapter.getAllOpponentProfiles().isEmpty()){
            myname.setText(adapter.getAllOpponentProfiles().get(0).getFirstName());
        }

        //profileViewModel = new ViewModelProvider(this).get(OpponentProfileViewModel.class);

        myp = root.findViewById(R.id.myp);
        oppp = root.findViewById(R.id.oppp);
        mym = root.findViewById(R.id.mym);
        oppm = root.findViewById(R.id.oppm);

        myp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlife++;
                mylife.setText("" + mlife);
            }
        });
        oppp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                olife++;
                opponentlife.setText("" + olife);
            }
        });
        mym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlife >= 1) {
                    mlife--;
                    mylife.setText("" + mlife);
                }
            }
        });
        oppm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(olife >= 1) {
                    olife--;
                    opponentlife.setText("" + olife);
                }
            }
        });

        mylife.setText("" + mlife);
        opponentlife.setText("" + olife);



        /*profileViewModel.getSingleProfileVM().observe(this, new Observer<OpponentProfile>() {
            @Override
            public void onChanged(@Nullable final OpponentProfile profile) {
                String OpponentName = profile.getNickname();
                String OpponentDeck = profile.getDeck();

                opponame.setText(OpponentName);
                oppodeck.setText(OpponentDeck);
            }
        });*/
        return root;
    }
}