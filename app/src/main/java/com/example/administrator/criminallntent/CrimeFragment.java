package com.example.administrator.criminallntent;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CrimeFragment extends Fragment {

    private static final String ARG_CRIME_ID="crime_id";
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    public static CrimeFragment newInstance(UUID crimeid){
        Bundle args=new Bundle();
        args.putSerializable(ARG_CRIME_ID,crimeid);

        CrimeFragment fragment=new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID crimeId=(UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mCrime=CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_crime,container,false);
       mTitleField=(EditText)v.findViewById(R.id.crime_title);
       mTitleField.setText(mCrime.getTitle());
       mTitleField.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    mCrime.setTitle(charSequence.toString());
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });

       mDateButton=(Button)v.findViewById(R.id.crime_date);
         Date time=new Date();
        SimpleDateFormat df=new SimpleDateFormat("EEEE,MMMMdd,yyyy");
        String dateString=df.format(mCrime.getDate());
       mDateButton.setText(dateString);
       mDateButton.setEnabled(false);

       mSolvedCheckBox =(CheckBox)v.findViewById(R.id.crime_solved);
       mSolvedCheckBox.setChecked(mCrime.isSolved());
       mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               mCrime.setSolved(isChecked);
           }
       });
       return v;
    }
}
