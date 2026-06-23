package com.example.endemikdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.endemikdb.R;
import com.example.endemikdb.activity.DetailActivity;
import com.example.endemikdb.model.Endemik;

import java.util.List;

public class EndemikAdapter extends RecyclerView.Adapter<EndemikAdapter.ViewHolder> {

    private Context context;
    private List<Endemik> endemikList;

    public EndemikAdapter(Context context, List<Endemik> endemikList) {
        this.context = context;
        this.endemikList = endemikList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_endemik, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        Endemik data = endemikList.get(position);

        holder.txtNama.setText(data.getNama());
        holder.txtAsal.setText(data.getAsal());
        holder.txtStatus.setText(data.getStatus());

        Glide.with(context)
                .load(data.getFoto())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgFoto);

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(context, DetailActivity.class);

            intent.putExtra("nama", data.getNama());
            intent.putExtra("latin", data.getNama_latin());
            intent.putExtra("famili", data.getFamili());
            intent.putExtra("genus", data.getGenus());
            intent.putExtra("asal", data.getAsal());
            intent.putExtra("sebaran", data.getSebaran());
            intent.putExtra("deskripsi", data.getDeskripsi());
            intent.putExtra("status", data.getStatus());
            intent.putExtra("foto", data.getFoto());

            // buka dari Home
            intent.putExtra("dari_favorit", false);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return endemikList != null ? endemikList.size() : 0;
    }

    // Untuk update data saat search
    public void updateData(List<Endemik> newList) {

        if (endemikList == null) return;

        endemikList.clear();

        if (newList != null) {
            endemikList.addAll(newList);
        }

        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFoto;
        TextView txtNama;
        TextView txtAsal;
        TextView txtStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFoto = itemView.findViewById(R.id.imgFoto);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtAsal = itemView.findViewById(R.id.txtAsal);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }
    }
}