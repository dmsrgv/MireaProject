package ru.mirea.sergeev.mireaproject.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ru.mirea.sergeev.mireaproject.R;

public class MusicFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_music, container, false);
        final Button buttonPlay = root.findViewById(R.id.button3);
        final Button buttonStop = root.findViewById(R.id.button4);
        return root;
    }
}