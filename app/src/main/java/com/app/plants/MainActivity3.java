package com.app.plants;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;


public class MainActivity3 extends AppCompatActivity {


    private ArrayList<PlanteItem> mPlanteList;
    private PlanteSpinnerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        initList();

        Spinner spinnerPlantes = findViewById(R.id.spinner_plantes);

        mAdapter = new PlanteSpinnerAdapter(this, mPlanteList);
        spinnerPlantes.setAdapter(mAdapter);

        spinnerPlantes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PlanteItem clickedItem = (PlanteItem) parent.getItemAtPosition(position);
                String clickedPlanteName = clickedItem.getPlanteName();
                Toast.makeText(MainActivity3.this, clickedPlanteName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initList() {
        mPlanteList = new ArrayList<>();
        mPlanteList.add(new PlanteItem("Plante médicinale", R.drawable.plantes));
        mPlanteList.add(new PlanteItem("Safran", R.drawable.safran));
        mPlanteList.add(new PlanteItem("Romarin", R.drawable.romarin));
        mPlanteList.add(new PlanteItem("Échinacée", R.drawable.echinacee));
        mPlanteList.add(new PlanteItem("verge d'or", R.drawable.vergedor));
        mPlanteList.add(new PlanteItem("colendula", R.drawable.colendula));
    }
}




