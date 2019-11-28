package com.ucsc.winrate.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import android.graphics.Color;

import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.components.Legend;


import java.util.ArrayList;
import java.util.List;

import com.ucsc.winrate.GameLogAdapter;
import com.ucsc.winrate.R;
import com.ucsc.winrate.table_entities.GameLogEntry;

public class StatsFragment extends Fragment{

    private StatsViewModel statsViewModel;
    private PieChart mChart;
    private Button updateChartButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        statsViewModel = new ViewModelProvider(this).get(StatsViewModel.class);

        View v = inflater.inflate(R.layout.fragment_stats, container, false);


        //Set up observer for room data:
        final GameLogAdapter adapter = new GameLogAdapter(getActivity());

        statsViewModel.getAllGameLogEntries().observe(this, new Observer<List<GameLogEntry>>() {
            @Override
            public void onChanged(List<GameLogEntry> gameLogEntries) {
                adapter.setGameLogEntries(gameLogEntries);
            }
        });

        mChart = (PieChart) v.findViewById(R.id.pieChart);
        mChart.getDescription().setText("WinRate Data");

        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        //Grab Game Log table data

        updateChartButton = v.findViewById(R.id.statsUpdateChartButton);

        updateChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("clicked!");
                List<GameLogEntry> allEntries = adapter.getAllGameLogEntries();
                float numLoss = 0;
                float numWin = 0;
                for(int i = 0; i < adapter.getAllGameLogEntries().size(); i++){
                    if(adapter.getAllGameLogEntries().get(i).getWinStatus()){
                        numWin++;
                    } else {
                        numLoss++;
                    }
                }

                float statusAmount[] = {numWin, numLoss };
                String statusNames[] = {"Win", "Loss"};
                List<PieEntry> pieEntries = new ArrayList<>();
                for (int i = 0; i < statusAmount.length; i++) {
                    pieEntries.add(new PieEntry(statusAmount[i], statusNames[i]));
                }
                PieDataSet dataSet = new PieDataSet(pieEntries, "Overall Winrate");
                dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                PieData data = new PieData(dataSet);
                mChart.setData(data);
            }
        });



        return v;
    }

    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Win Rate");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

//    private PieData grabWinRateData() {
//        //TODO add variables to the adapater which automatically keeps track of num win vs loss
//        final GameLogAdapter adapter = new GameLogAdapter(getActivity());
//
//
//        return data;
//    }

    private void showToast(String text){
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }
}
