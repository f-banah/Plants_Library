package com.app.plants;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

public class PlanteSpinnerAdapter extends ArrayAdapter<PlanteItem> {

    public PlanteSpinnerAdapter(Context context, ArrayList<PlanteItem> planteList) {
        super(context, 0, planteList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.plante_spinner_row, parent, false
            );
        }

        ImageView imageViewPlant = convertView.findViewById(R.id.image_view_plante);
        TextView textViewName = convertView.findViewById(R.id.text_view_name);

        PlanteItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewPlant.setImageResource(currentItem.getPlantImage());
            textViewName.setText(currentItem.getPlanteName());
        }

        return convertView;
    }
}