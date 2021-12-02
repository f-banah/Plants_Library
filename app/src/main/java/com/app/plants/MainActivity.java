package com.app.plants;

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


import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;






public class MainActivity extends AppCompatActivity {

    EditText edtnom, edtnomScientifique , edtfamille, edtorigine ,  edttype , edtgenre , edttoxicite , edtusage ;
    Button btnChoose, btnAdd, btnList;
    ImageView imageView;
    final int REQUEST_CODE_GALLERY = 999;
    public static SQLiteHelper sqLiteHelper;


    //private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button = (Button) findViewById(R.id.button);
        //button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        openActivity2();
        //    }
        //});

        init();

        sqLiteHelper = new SQLiteHelper(this, "PlanteDB.sqlite", null, 1);

        sqLiteHelper.queryData("CREATE TABLE IF NOT EXISTS Plantes(Id INTEGER PRIMARY KEY AUTOINCREMENT, nom VARCHAR, nomScientifique VARCHAR, famille VARCHAR, origine VARCHAR ,type VARCHAR, genre VARCHAR, toxicite VARCHAR, usage VARCHAR, image BLOB)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    sqLiteHelper.insertData(
                            edtnom.getText().toString().trim(),
                            edtnomScientifique.getText().toString().trim(),
                            edtfamille.getText().toString().trim(),
                            edtorigine.getText().toString().trim(),
                            edttype.getText().toString().trim(),
                            edtgenre.getText().toString().trim(),
                            edttoxicite.getText().toString().trim(),
                            edtusage.getText().toString().trim(),

                            imageViewToByte(imageView)
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();


                    edtnom.setText("");
                    edtnomScientifique.setText("");
                    edtfamille.setText("");
                    edtorigine.setText("");
                    edttype.setText("");
                    edtgenre.setText("");
                    edttoxicite.setText("");
                    edtusage.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlanteList.class);
                startActivity(intent);
            }
        });



    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        edtnom = (EditText) findViewById(R.id.edtnom);
        edtnomScientifique = (EditText) findViewById(R.id.edtnomScientifique);
        edtfamille = (EditText) findViewById(R.id.edtfamille);
        edtorigine = (EditText) findViewById(R.id.edtorigine);
        edttype = (EditText) findViewById(R.id.edttype);
        edtgenre = (EditText) findViewById(R.id.edtgenre);
        edttoxicite = (EditText) findViewById(R.id.edttoxicite);
        edtusage = (EditText) findViewById(R.id.edtusage);
        btnChoose = (Button) findViewById(R.id.btnChoose);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnList = (Button) findViewById(R.id.btnList);
        imageView = (ImageView) findViewById(R.id.imageView);


    }



    //public void openActivity2() {
    //    Intent intent = new Intent(this, MainActivity2.class);
    //    startActivity(intent);
    //}

}