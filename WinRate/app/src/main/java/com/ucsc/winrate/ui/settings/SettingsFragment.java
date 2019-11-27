package com.ucsc.winrate.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.ucsc.winrate.R;
import com.ucsc.winrate.ui.contactBook.ContactBookViewModel;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    private SettingsViewModel settingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.populateGameLogButton:
                // Insert code here
                break;
            case R.id.deleteGameLogButton:
                // Insert code here
                break;
            case R.id.populateOpponentProfileButton:
                // Insert code here
                break;
            case R.id.deleteOpponentProfileButton:
                // Insert code here
                break;
            case R.id.populateDeckProfileButton:
                // Insert code here
                break;
            case R.id.deleteDeckProfileButton:
                // Insert code here
                break;
        }
    }
}