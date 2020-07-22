package com.example.teamwork.Utils;

import com.example.teamwork.Modal.ImageData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("photos")
    Call<List<ImageData>> getDataList();
}
