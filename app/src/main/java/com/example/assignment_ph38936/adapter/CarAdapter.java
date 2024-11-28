package com.example.assignment_ph38936.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_ph38936.R;
import com.example.assignment_ph38936.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter extends ListAdapter<Car, CarAdapter.CarViewHolder> {

    private final List<Car> carList;

    public CarAdapter(List<Car> carList) {
        super(DIFF_CALLBACK);
        this.carList = carList;
    }


    private static final DiffUtil.ItemCallback<Car> DIFF_CALLBACK = new DiffUtil.ItemCallback<Car>() {
        @Override
        public boolean areItemsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Car oldItem, @NonNull Car newItem) {
            return oldItem.getMaXe().equals(newItem.getMaXe()) &&
                    oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getManufacturer().equals(newItem.getManufacturer()) &&
                    oldItem.getYear() == newItem.getYear() &&
                    oldItem.getPrice() == newItem.getPrice();
        }
    };

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = getItem(position);
        holder.bind(car);
    }

    static class CarViewHolder extends RecyclerView.ViewHolder {
        private TextView maXeTextView;
        private TextView nameTextView;
        private TextView manufacturerTextView;
        private TextView yearTextView;
        private TextView priceTextView;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            maXeTextView = itemView.findViewById(R.id.text_view_maxe);
            nameTextView = itemView.findViewById(R.id.text_view_name);
            manufacturerTextView = itemView.findViewById(R.id.text_view_manufacturer);
            yearTextView = itemView.findViewById(R.id.text_view_year);
            priceTextView = itemView.findViewById(R.id.text_view_price);
        }

        public void bind(Car car) {
            maXeTextView.setText(car.getMaXe());
            nameTextView.setText(car.getName());
            manufacturerTextView.setText(car.getManufacturer());
            yearTextView.setText(String.valueOf(car.getYear()));
            priceTextView.setText(String.format("$%.2f", car.getPrice()));
        }
    }
}

