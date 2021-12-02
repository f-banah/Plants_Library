package com.app.plants;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class PlanteList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Plante> list;
    PlanteListAdapter adapter = null;

    private Button button;
    private Button button2;
    private Button button3;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plante_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new PlanteListAdapter(this, R.layout.plante_items, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM Plantes");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nom = cursor.getString(1);
            String nomScientifique = cursor.getString(2);
            String famille = cursor.getString(3);
            String origine = cursor.getString(4);
            String type = cursor.getString(5);
            String genre = cursor.getString(6);
            String toxicite = cursor.getString(7);
            String usage = cursor.getString(8);
            byte[] image = cursor.getBlob(9);

            list.add(new Plante(nom, nomScientifique, famille, origine , type , genre , toxicite, usage,image , id));
        }
        adapter.notifyDataSetChanged();

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                CharSequence[] items = {"Modifer", "Supprimer"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(PlanteList.this);

                dialog.setTitle("Choisir une option");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM Plantes");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(PlanteList.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = MainActivity.sqLiteHelper.getData("SELECT id FROM Plantes");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });


        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });


        button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });


        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity4();
            }
        });


    }

    ImageView imageViewPlante;
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_plante_activity);
        dialog.setTitle("Modification");

        imageViewPlante = (ImageView) dialog.findViewById(R.id.imageViewPlante);
        //final EditText edtnom = (EditText) findViewById(R.id.edtnom);
        //final EditText edtnomScientifique = (EditText) findViewById(R.id.edtnomScientifique);
        //final EditText edtfamille = (EditText) findViewById(R.id.edtfamille);
        //final EditText edtorigine = (EditText) findViewById(R.id.edtorigine);
        final EditText edttype = (EditText) findViewById(R.id.edttype);
        //final EditText edtgenre = (EditText) findViewById(R.id.edtgenre);
        //final EditText edttoxicite = (EditText) findViewById(R.id.edttoxicite);
        final EditText edtusage = (EditText) findViewById(R.id.edtusage);
        Button btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        imageViewPlante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // request photo library
                ActivityCompat.requestPermissions(
                        PlanteList.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                try {
                    MainActivity.sqLiteHelper.updateData(
                            //edtnom.getText().toString().trim(),
                            //edtnomScientifique.getText().toString().trim(),
                            //edtfamille.getText().toString().trim(),
                           // edtorigine.getText().toString().trim(),
                            edttype.getText().toString().trim(),
                            //edtgenre.getText().toString().trim(),
                            //edttoxicite.getText().toString().trim(),
                            edtusage.getText().toString().trim(),

                            MainActivity.imageViewToByte(imageViewPlante),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Ajoutée avec succès !",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updatePlanteList();
            }
        });
    }

    private void showDialogDelete(final int idPlante){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(PlanteList.this);

        dialogDelete.setTitle("Attention!!");
        dialogDelete.setMessage("Êtes-vous sûr de supprimer cette plante?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    MainActivity.sqLiteHelper.deleteData(idPlante);
                    Toast.makeText(getApplicationContext(), "Supprimé avec succès!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("erreur", e.getMessage());
                }
                updatePlanteList();
            }
        });

        dialogDelete.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updatePlanteList(){
        // get all data from sqlite
        Cursor cursor = MainActivity.sqLiteHelper.getData("SELECT * FROM Plantes");
        list.clear();
        while (cursor.moveToNext()) {


            int id = cursor.getInt(0);
            String nom = cursor.getString(1);
            String nomScientifique = cursor.getString(2);
            String famille = cursor.getString(3);
            String origine = cursor.getString(4);
            String type = cursor.getString(5);
            String genre = cursor.getString(6);
            String toxicite = cursor.getString(7);
            String usage = cursor.getString(8);
            byte[] image = cursor.getBlob(9);

            list.add(new Plante(nom, nomScientifique, famille, origine , type , genre , toxicite, usage,image , id));


        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == 888){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 888);
            }
            else {
                Toast.makeText(getApplicationContext(), "Vous n'êtes pas autorisé à accéder à l'emplacement du fichier !", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewPlante.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



    public void openActivity2() {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }


    public void openActivity3() {
        Intent intent = new Intent(this, MainActivity3.class);
        startActivity(intent);
    }

    public void openActivity4() {
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }


}