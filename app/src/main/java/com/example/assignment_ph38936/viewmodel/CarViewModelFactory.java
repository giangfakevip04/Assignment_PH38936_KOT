package com.example.assignment_ph38936.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment_ph38936.repository.CarRepository;

public class CarViewModelFactory implements ViewModelProvider.Factory {
    private final CarRepository repository;

    public CarViewModelFactory(CarRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CarViewModel.class)) {
            return (T) new CarViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
