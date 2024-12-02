package com.example.assignment_ph38936;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_ph38936.adapter.CarAdapter;
import com.example.assignment_ph38936.repository.CarRepository;
import com.example.assignment_ph38936.util.Resource;
import com.example.assignment_ph38936.viewmodel.CarViewModel;
import com.example.assignment_ph38936.viewmodel.CarViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CarViewModel viewModel;
    private CarAdapter adapter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CarRepository repository = new CarRepository();
        CarViewModelFactory factory = new CarViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(CarViewModel.class);

        progressBar = findViewById(R.id.progress_bar);


        setupRecyclerView();
        setupFab();
        loadCars();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new CarAdapter(new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupFab() {
        FloatingActionButton fab = findViewById(R.id.fab_add_car);
        fab.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CarFormActivity.class)));
    }

    private void loadCars() {
        viewModel.getCars().observe(this, resource -> {
            if (resource.getStatus() == Resource.Status.SUCCESS) {
                if (resource.getData() == null || resource.getData().isEmpty()) {
                    Toast.makeText(this, getString(R.string.Nono), Toast.LENGTH_SHORT).show();
                }
                adapter.submitList(resource.getData());
                progressBar.setVisibility(View.GONE);
            } else if (resource.getStatus() == Resource.Status.ERROR) {
                Log.e("LOL", "loadCars:" +resource.getMessage() );
                Toast.makeText(this, getString(R.string.error_loading_cars) + ": " + resource.getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            } else if (resource.getStatus() == Resource.Status.LOADING) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void performSearch(String query) {
        viewModel.searchCars(query).removeObservers(this);
        viewModel.searchCars(query).observe(this, resource -> {
            if (resource.getStatus() == Resource.Status.SUCCESS) {
                if (resource.getData() == null || resource.getData().isEmpty()) {
                    Toast.makeText(this, getString(R.string.no), Toast.LENGTH_SHORT).show();
                }
                adapter.submitList(resource.getData());
            } else if (resource.getStatus() == Resource.Status.ERROR) {
                Toast.makeText(this, getString(R.string.error_searching_cars) + ": " + resource.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
