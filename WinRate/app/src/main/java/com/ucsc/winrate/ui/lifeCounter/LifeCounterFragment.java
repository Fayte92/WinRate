package com.ucsc.winrate.ui.lifeCounter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ucsc.winrate.OpponentProfileAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.table_entities.OpponentProfile;

import java.util.List;

public class LifeCounterFragment extends Fragment{

    private LifeCounterViewModel lifeCounterViewModel;

    private TextView mylife;
    private TextView opponentlife;
    int mlife = 20;
    int olife = 20;
    private Button myp; //My life plus button
    private Button oppp; //Opponent life plus button
    private Button mym; //My life minus button
    private Button oppm; //Opponent life minus button
    private Button upopponame; //Cycles UP through opponent names
    private Button downopponame; //Cycles DOWN through opponent names
    private TextView myname;
    private TextView mydeck;
    private TextView opponame;
    private String defaultOpponentName = "A Stranger";
    private TextView oppodeck;
    private OpponentProfile curProfile;
    private int namenum = 0;
    private String winCondition;
    private int size;
    //private LifeCounterFragmentListener listener;
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
                size = adapter.getAllOpponentProfiles().size();
                if(size > 0) {
                    opponame.setText(adapter.getAllOpponentProfiles().get(namenum).getFirstName());
                } else{
                    opponame.setText(defaultOpponentName);
                }
                String oname = opponame.getText().toString();
                //listener.applyTexts(oname);
            }
        });


        mylife = root.findViewById(R.id.mylife);
        opponentlife = root.findViewById(R.id.opponentlife);
        myname = root.findViewById(R.id.myname);
        mydeck = root.findViewById(R.id.mydeck);
        opponame = root.findViewById(R.id.opponame);
        oppodeck = root.findViewById(R.id.oppodeck);

        //String currentName = adapter.getAllOpponentProfiles().get(0).getFirstName();

        //profileViewModel = new ViewModelProvider(this).get(OpponentProfileViewModel.class);

        myp = root.findViewById(R.id.myp);
        oppp = root.findViewById(R.id.oppp);
        mym = root.findViewById(R.id.mym);
        oppm = root.findViewById(R.id.oppm);
        upopponame = root.findViewById(R.id.upopponame);
        downopponame = root.findViewById(R.id.downopponame);

        downopponame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size < 1){
                    opponame.setText(defaultOpponentName);
                } else if(namenum < 0) {
                    namenum = size - 1;
                    opponame.setText(adapter.getAllOpponentProfiles().get(namenum).getFirstName());
                }else if(namenum >= 1){
                    namenum--;
                    opponame.setText(adapter.getAllOpponentProfiles().get(namenum).getFirstName());
                }else { //namenum == 0
                    namenum = -1;
                    opponame.setText(defaultOpponentName);
                }
            }
        });

        upopponame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(size < 1) {
                    opponame.setText(defaultOpponentName);
                } else if(namenum < 0){
                    namenum = 0;
                    opponame.setText(adapter.getAllOpponentProfiles().get(namenum).getFirstName());
                }else if(namenum < (size-1)){
                    namenum++;
                    opponame.setText(adapter.getAllOpponentProfiles().get(namenum).getFirstName());
                }else if(namenum == (size-1)){
                    namenum = -1;
                    opponame.setText(defaultOpponentName);
                }
            }
        });



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
                }else{
                    winCondition = String.valueOf(false);
                    openDialog();
                }
            }
        });
        oppm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(olife >= 1) {
                    olife--;
                    opponentlife.setText("" + olife);
                }else{
                    winCondition = String.valueOf(true);
                    openDialog();
                }
            }
        });

        mylife.setText("" + mlife);
        opponentlife.setText("" + olife);
        return root;
    }
    public void openDialog(){


        String myDeck = mydeck.getText().toString();
        String opponentName = opponame.getText().toString();
        String opponentDeck = oppodeck.getText().toString();
        //String myDeck = "The tag for my deck";
        //String opponentDeck = "The tag for opponent deck";
        //winCondition = String.valueOf(true);

        Bundle DATA = new Bundle();
        DATA.putString("myDeck", myDeck);
        DATA.putString("opponentName", opponentName);
        DATA.putString("opponentDeck", opponentDeck);
        DATA.putString("winCondition",winCondition);

        NoticeDialog noticeForLifeCounter = new NoticeDialog();
        noticeForLifeCounter.setArguments(DATA);
        noticeForLifeCounter.show(getFragmentManager(),"Notice For Life Counter");

    }

//
//    public static NoticeDialog newInstance(String myDeck, String opponentName, String opponentDeck, String winCondition, String curDate){
//        NoticeDialog f = new NoticeDialog();
//        // Supply index input as an argument.
//        Bundle args = new Bundle();
//        args.putString("myDeck", myDeck);
//        args.putString("opponentName", opponentName);
//        args.putString("opponentDeck", opponentDeck);
//        args.putString("winCondition",winCondition);
//        args.putString("curDate", curDate);
//        f.setArguments(args);
//        return f;
//    }

    public static void PirntWTF( Bundle F){

    }

//    @Override
//    public void onAttach(Context context){
//        super.onAttach(context);
//        try {
//            listener = (LifeCounterFragmentListener) context;
//        }catch(ClassCastException e){
//            throw new ClassCastException(context.toString()+"iii");
//        }
//    }
//
//    public interface LifeCounterFragmentListener{
//        void applyTexts(String oname);
//    }

}