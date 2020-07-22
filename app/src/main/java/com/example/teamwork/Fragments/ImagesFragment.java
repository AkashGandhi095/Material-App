package com.example.teamwork.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.teamwork.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

import java.io.InputStream;

import static android.app.Activity.RESULT_OK;


public class ImagesFragment extends Fragment {



    public ImagesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private static final int IMG_TAG = 0x199;
    private MaterialButton galleryButton;
    private MaterialCardView cardView;
    private ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_images, container, false);
        initView(view);

       // pickImage();

        galleryButton.setOnClickListener(v -> pickImage());

        return view;
    }

    private void initView(View view) {
        galleryButton = view.findViewById(R.id.galButton);
        cardView = view.findViewById(R.id.cardView);
        imageView = view.findViewById(R.id.imgView);

    }

    private void pickImage() {
        Intent picker = new Intent(Intent.ACTION_GET_CONTENT);
        picker.setType("image/*");
        startActivityForResult(picker , IMG_TAG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
        {
            try {
                assert data != null;
                final Uri imageUri = data.getData();
                assert imageUri != null;
                InputStream inputStream = requireActivity().getContentResolver()
                        .openInputStream(imageUri);
                Bitmap selectedImage = BitmapFactory.decodeStream(inputStream);
                cardView.setVisibility(View.VISIBLE);
                galleryButton.setVisibility(View.GONE);
                imageView.setImageBitmap(selectedImage);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(requireActivity(), "You have not pick any image from gallery", Toast.LENGTH_SHORT).show();
        }
    }
}