package com.example.assignment_ph38936.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignment_ph38936.api.CarApi;
import com.example.assignment_ph38936.model.Car;
import com.example.assignment_ph38936.util.Resource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarRepository {
    private CarApi carApi;

    public CarRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                // Thử dùng http:// thay vì https:// trong trường hợp không có SSL
                .baseUrl("http://10.0.2.2:5000/") // Đảm bảo rằng server của bạn có thể truy cập từ thiết bị Android
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        carApi = retrofit.create(CarApi.class);
    }

    public LiveData<Resource<List<Car>>> getCars() {
        MutableLiveData<Resource<List<Car>>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null)); // Khi đang loading
        carApi.getCars().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(response.body()));
                    Log.d("CarRepository", "Response: " + response.body());
// Khi thành công
                } else {
                    result.setValue(Resource.error("Error: " + response.code(), null)); // Khi có lỗi từ server
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                result.setValue(Resource.error(t.getMessage(), null)); // Khi có lỗi trong quá trình gọi API
            }
        });
        return result;
    }

    public LiveData<Resource<Car>> createCar(Car car) {
        MutableLiveData<Resource<Car>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null)); // Khi đang loading
        carApi.createCar(car).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(response.body())); // Khi thành công
                } else {
                    result.setValue(Resource.error("Error: " + response.code(), null)); // Khi có lỗi từ server
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                result.setValue(Resource.error(t.getMessage(), null)); // Khi có lỗi trong quá trình gọi API
            }
        });
        return result;
    }

    public LiveData<Resource<Car>> updateCar(String id, Car car) {
        MutableLiveData<Resource<Car>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null)); // Khi đang loading
        carApi.updateCar(id, car).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(response.body())); // Khi thành công
                } else {
                    result.setValue(Resource.error("Error: " + response.code(), null)); // Khi có lỗi từ server
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                result.setValue(Resource.error(t.getMessage(), null)); // Khi có lỗi trong quá trình gọi API
            }
        });
        return result;
    }

    public LiveData<Resource<Void>> deleteCar(String id) {
        MutableLiveData<Resource<Void>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null)); // Khi đang loading
        carApi.deleteCar(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(null)); // Khi thành công
                } else {
                    result.setValue(Resource.error("Error: " + response.code(), null)); // Khi có lỗi từ server
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                result.setValue(Resource.error(t.getMessage(), null)); // Khi có lỗi trong quá trình gọi API
            }
        });
        return result;
    }

    public LiveData<Resource<List<Car>>> searchCars(String query) {
        MutableLiveData<Resource<List<Car>>> result = new MutableLiveData<>();
        result.setValue(Resource.loading(null)); // Khi đang loading
        carApi.searchCars(query).enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                if (response.isSuccessful()) {
                    result.setValue(Resource.success(response.body())); // Khi thành công
                } else {
                    result.setValue(Resource.error("Error: " + response.code(), null)); // Khi có lỗi từ server
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                result.setValue(Resource.error(t.getMessage(), null)); // Khi có lỗi trong quá trình gọi API
            }
        });
        return result;
    }
}
