package com.example.teamwork.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.teamwork.Adapter.ImageAdapter;
import com.example.teamwork.Modal.ImageData;
import com.example.teamwork.R;
import com.example.teamwork.Utils.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ViewImagesFragment extends Fragment {



    public ViewImagesFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private ImageAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_images, container, false);
        initViews(view);
        CallApi();
        return view;
    }

    private void initViews(View view) {

        progressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new ImageAdapter(requireActivity());
        recyclerView.setAdapter(adapter);

    }

    private void CallApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create()).
                build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<List<ImageData>> dataList = apiInterface.getDataList();

        dataList.enqueue(new Callback<List<ImageData>>() {
            @Override
            public void onResponse(Call<List<ImageData>> call, Response<List<ImageData>> response) {
                if(!response.isSuccessful())
                {
                    Log.e("Response", ""+response.code());
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                List<ImageData> imageDataList = response.body();
                if (imageDataList != null) {
                    Log.d("Response", ""+imageDataList.size());
                    adapter.setImageList(imageDataList);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<ImageData>> call, Throwable t) {
                Log.e("Response", "onFailure: " +t.getLocalizedMessage());
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}