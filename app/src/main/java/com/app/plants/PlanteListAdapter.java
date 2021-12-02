package com.app.plants;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class PlanteListAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Plante> plantesList;

    public PlanteListAdapter(Context context, int layout, ArrayList<Plante> plantesList) {
        this.context = context;
        this.layout = layout;
        this.plantesList = plantesList;
    }

    @Override
    public int getCount() {
        return plantesList.size();
    }

    @Override
    public Object getItem(int position) {
        return plantesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    private class ViewHolder{
        ImageView imageView;
        TextView txtnom, txtnomScientifique , txtfamille, txtorigine , txttype, txtgenre,txttoxicite, txtusage;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtnom = (TextView) row.findViewById(R.id.txtnom);
            holder.txtnomScientifique = (TextView) row.findViewById(R.id.txtnomScientifique);
            holder.txtfamille = (TextView) row.findViewById(R.id.txtfamille);
            holder.txtorigine = (TextView) row.findViewById(R.id.txtorigine);
            holder.txttype = (TextView) row.findViewById(R.id.txttype);
            holder.txtgenre = (TextView) row.findViewById(R.id.txtgenre);
            holder.txttoxicite = (TextView) row.findViewById(R.id.txttoxicite);
            holder.txtusage = (TextView) row.findViewById(R.id.txtusage);
            holder.imageView = (ImageView) row.findViewById(R.id.imgPlante);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Plante plante = plantesList.get(position);

        holder.txtnom.setText(plante.getNom());
        holder.txtnomScientifique.setText(plante.getNomScientifique());
        holder.txtfamille.setText(plante.getFamille());
        holder.txtorigine.setText(plante.getOrigine());
        holder.txttype.setText(plante.getType());
        holder.txtgenre.setText(plante.getGenre());
        holder.txttoxicite.setText(plante.getToxicite());
        holder.txtusage.setText(plante.getUsage());


        byte[] planteImage = plante.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(planteImage, 0, planteImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
