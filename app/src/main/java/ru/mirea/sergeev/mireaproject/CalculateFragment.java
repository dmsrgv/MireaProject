package ru.mirea.sergeev.mireaproject;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalculateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalculateFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public CalculateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalculateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalculateFragment newInstance(String param1, String param2) {
        CalculateFragment fragment = new CalculateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private TextView resultField;
    private EditText numberField1, numberField2;
    private Button bPlus;
    private Button bMinus;
    private Button bUmn;
    private Button bRazdel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculate, container, false);
        numberField1 = view.findViewById(R.id.editText3);
        numberField2 = view.findViewById(R.id.editText2);
        bPlus = view.findViewById(R.id.buttonPlus);
        bPlus.setOnClickListener(this);
        bMinus = view.findViewById(R.id.buttonMinus);
        bMinus.setOnClickListener(this);
        bUmn = view.findViewById(R.id.buttonUmn);
        bUmn.setOnClickListener(this);
        bRazdel = view.findViewById(R.id.buttonRazdel);
        bRazdel.setOnClickListener(this);
        resultField = view.findViewById(R.id.textView2);
        // Inflate the layout for this fragment
        return view;
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void onClick(View view) {
        int buttonIndex = translateIdToIndex(view.getId());
        int n1 = Integer.parseInt(numberField1.getText().toString());
        int n2 = Integer.parseInt(numberField2.getText().toString());
        if (buttonIndex == 1)
        {
            Integer res = n1 + n2;
            resultField.setText((res.toString()));
        }
        if (buttonIndex == 2)
        {
            Integer res = n1 - n2;
            resultField.setText((res.toString()));
        }
        if (buttonIndex == 4)
        {
            Integer res = n1 * n2;
            resultField.setText((res.toString()));
        }
        if (buttonIndex == 3)
        {
            Integer res = n1 / n2;
            resultField.setText((res.toString()));
        }


        // Временный код для получения индекса нажатой кнопки
       // Toast.makeText(getActivity(), String.valueOf(buttonIndex),
        //        Toast.LENGTH_SHORT).show();

    }



    int translateIdToIndex(int id) {
        int index = -1;
        switch (id) {
            case R.id.buttonPlus:
                index = 1;
                break;
            case R.id.buttonMinus:
                index = 2;
                break;
            case R.id.buttonRazdel:
                index = 3;
                break;
            case R.id.buttonUmn:
                index = 4;
                break;
        }
        return index;
    }
    }
