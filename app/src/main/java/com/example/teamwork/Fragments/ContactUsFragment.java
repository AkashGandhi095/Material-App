package com.example.teamwork.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.teamwork.R;
import com.google.android.material.textview.MaterialTextView;


public class ContactUsFragment extends Fragment {



    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private Intent intent;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact_us, container, false);

        MaterialTextView phoneNumber = view.findViewById(R.id.editphoneNumber);
        MaterialTextView email = view.findViewById(R.id.homeEmail);

        String number = "+91 9123456789";
        String mEmail = "abcde@xyz.com";

        phoneNumber.setText("Phone Number : " + number);
        phoneNumber.setOnClickListener(v -> {

            intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" +number));
            startActivity(intent);

        });

        email.setText("Email : "+mEmail);
        email.setOnClickListener(v -> {

            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL , new String[]{mEmail});
            startActivity(intent);


        });
        return view;
    }
}