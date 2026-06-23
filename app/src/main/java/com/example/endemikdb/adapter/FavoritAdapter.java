package com.example.endemikdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.endemikdb.R;
import com.example.endemikdb.activity.DetailActivity;
import com.example.endemikdb.room.AppDatabase;
import com.example.endemikdb.room.Favorit;

import java.util.List;

public class FavoritAdapter
        extends RecyclerView.Adapter<FavoritAdapter.ViewHolder> {

    Context context;
    List<Favorit> favoritList;

    private AppDatabase database;

    public FavoritAdapter(
            Context context,
            List<Favorit> favoritList
    ) {

        this.context = context;
        this.favoritList = favoritList;

        database = Room.databaseBuilder(
                        context,
                        AppDatabase.class,
                        "endemik_db"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {

        View view = LayoutInflater
                .from(context)
                .inflate(
                        R.layout.item_favorit,
                        parent,
                        false
                );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {

        Favorit data = favoritList.get(position);

        holder.txtNama.setText(data.getNama());
        holder.txtAsal.setText(data.getAsal());
        holder.txtStatus.setText(data.getStatus());

        Glide.with(context)
                .load(data.getFoto())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.imgFoto);

        // Klik Card -> Detail
        holder.itemView.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            context,
                            DetailActivity.class
                    );

            intent.putExtra("nama", data.getNama());
            intent.putExtra("latin", data.getLatin());
            intent.putExtra("famili", data.getFamili());
            intent.putExtra("genus", data.getGenus());
            intent.putExtra("asal", data.getAsal());
            intent.putExtra("sebaran", data.getSebaran());
            intent.putExtra("deskripsi", data.getDeskripsi());
            intent.putExtra("status", data.getStatus());
            intent.putExtra("foto", data.getFoto());

            context.startActivity(intent);
        });

        // Tombol Hapus
        holder.btnHapus.setOnClickListener(v -> {

            int currentPosition =
                    holder.getAdapterPosition();

            if (currentPosition != RecyclerView.NO_POSITION) {

                database.favoritDao()
                        .delete(data);

                favoritList.remove(currentPosition);

                notifyItemRemoved(currentPosition);

                Toast.makeText(
                        context,
                        "Favorit dihapus",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoritList.size();
    }

    public static class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView imgFoto;
        TextView txtNama;
        TextView txtAsal;
        TextView txtStatus;
        ImageButton btnHapus;

        public ViewHolder(
                @NonNull View itemView
        ) {
            super(itemView);

            imgFoto =
                    itemView.findViewById(R.id.imgFoto);

            txtNama =
                    itemView.findViewById(R.id.txtNama);

            txtAsal =
                    itemView.findViewById(R.id.txtAsal);

            txtStatus =
                    itemView.findViewById(R.id.txtStatus);

            btnHapus =
                    itemView.findViewById(R.id.btnHapus);
        }
    }
}