package com.example.assignment_ph38936.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment_ph38936.model.Car;
import com.example.assignment_ph38936.repository.CarRepository;
import com.example.assignment_ph38936.util.Resource;

import java.util.List;

public class CarViewModel extends ViewModel {


    private CarRepository repository;


    public CarViewModel(CarRepository repository) {
        this.repository = new CarRepository();
    }

    public LiveData<Resource<List<Car>>> getCars() {
        return repository.getCars();
    }

    public LiveData<Resource<Car>> createCar(Car car) {
        return repository.createCar(car);
    }

    public LiveData<Resource<Car>> updateCar(String id, Car car) {
        return repository.updateCar(id, car);
    }

    public LiveData<Resource<Void>> deleteCar(String id) {
        return repository.deleteCar(id);
    }

    public LiveData<Resource<List<Car>>> searchCars(String query) {
        return repository.searchCars(query);
    }
}





//public class CarViewModel extends ViewModel {
//    private final CarRepository repository;
//
//    public CarViewModel(CarRepository repository) {
//        this.repository = repository; // Sử dụng repository được truyền từ factory
//    }
//
//    public LiveData<Resource<List<Car>>> getCars() {
//        return repository.getCars();
//    }
//
//    public LiveData<Resource<Car>> createCar(Car car) {
//        return repository.createCar(car);
//    }
//
//    public LiveData<Resource<Car>> updateCar(String id, Car car) {
//        return repository.updateCar(id, car);
//    }
//
//    public LiveData<Resource<Void>> deleteCar(String id) {
//        return repository.deleteCar(id);
//    }
//
//    public LiveData<Resource<List<Car>>> searchCars(String query) {
//        return repository.searchCars(query);
//    }
//}
