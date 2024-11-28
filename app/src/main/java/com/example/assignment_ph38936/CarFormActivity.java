package com.example.assignment_ph38936;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment_ph38936.model.Car;
import com.example.assignment_ph38936.repository.CarRepository;
import com.example.assignment_ph38936.util.Resource;
import com.example.assignment_ph38936.viewmodel.CarViewModel;
import com.example.assignment_ph38936.viewmodel.CarViewModelFactory;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class CarFormActivity extends AppCompatActivity {

    private CarViewModel viewModel;
    private TextInputEditText etMaXe, etName, etManufacturer, etYear, etPrice, etDescription;
    private Button btnSave;

    private String carId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_form);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // ViewModel initialization
        CarRepository repository = new CarRepository();
        CarViewModelFactory factory = new CarViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(CarViewModel.class);

        // Initialize views
        etMaXe = findViewById(R.id.et_maxe);
        etName = findViewById(R.id.et_name);
        etManufacturer = findViewById(R.id.et_manufacturer);
        etYear = findViewById(R.id.et_year);
        etPrice = findViewById(R.id.et_price);
        etDescription = findViewById(R.id.et_description);
        btnSave = findViewById(R.id.btn_save);

        // Get carId from intent to determine if we're editing or adding
        carId = getIntent().getStringExtra("CAR_ID");

        if (carId != null) {
            setTitle(R.string.edit_car);
            // TODO: Fetch car details from ViewModel if carId is not null
        } else {
            setTitle(R.string.add_new_car);
        }

        btnSave.setOnClickListener(v -> saveCar());
    }

    private void saveCar() {
        String maXe = Objects.requireNonNull(etMaXe.getText()).toString().trim();
        String name = Objects.requireNonNull(etName.getText()).toString().trim();
        String manufacturer = Objects.requireNonNull(etManufacturer.getText()).toString().trim();
        String yearStr = Objects.requireNonNull(etYear.getText()).toString().trim();
        String priceStr = Objects.requireNonNull(etPrice.getText()).toString().trim();
        String description = Objects.requireNonNull(etDescription.getText()).toString().trim();

        // Check if required fields are not empty
        if (maXe.isEmpty() || name.isEmpty() || manufacturer.isEmpty() || yearStr.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert year and price strings to appropriate types
        int year = Integer.parseInt(yearStr);
        double price = Double.parseDouble(priceStr);

        // Create a Car object
        Car car = new Car(carId, maXe, name, manufacturer, year, price, description);

        // Create or update car based on whether carId is null
        if (carId == null) {
            viewModel.createCar(car).observe(this, this::handleResult);
        } else {
            viewModel.updateCar(carId, car).observe(this, this::handleResult);
        }
    }

    private void handleResult(Resource<Car> result) {
        if (result.getStatus() == Resource.Status.SUCCESS) {
            Toast.makeText(this, R.string.success_saving_car, Toast.LENGTH_SHORT).show();
            finish(); // Go back to previous activity after success
        } else if (result.getStatus() == Resource.Status.ERROR) {
            Toast.makeText(this, getString(R.string.error_saving_car) + ": " + result.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Handle back button click
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
