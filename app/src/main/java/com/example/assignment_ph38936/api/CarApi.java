package com.example.assignment_ph38936.api;

import com.example.assignment_ph38936.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CarApi {
    @GET("cars")
    Call<List<Car>> getCars();

    @GET("cars/{id}")
    Call<Car> getCar(@Path("id") String id);

    @POST("cars")
    Call<Car> createCar(@Body Car car);

    @PUT("cars/{id}")
    Call<Car> updateCar(@Path("id") String id, @Body Car car);

    @DELETE("cars/{id}")
    Call<Void> deleteCar(@Path("id") String id);

    @GET("cars/search")
    Call<List<Car>> searchCars(@Query("term") String searchTerm);
}
