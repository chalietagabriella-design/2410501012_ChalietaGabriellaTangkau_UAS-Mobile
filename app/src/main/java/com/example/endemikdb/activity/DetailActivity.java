package com.example.endemikdb.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.endemikdb.R;
import com.example.endemikdb.room.AppDatabase;
import com.example.endemikdb.room.Favorit;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgDetail;

    private ImageButton btnBack;
    private ImageButton btnFavorit;

    private TextView txtNama;
    private TextView txtLatin;
    private TextView txtFamili;
    private TextView txtGenus;
    private TextView txtAsal;
    private TextView txtSebaran;
    private TextView txtDeskripsi;
    private TextView txtStatus;

    private AppDatabase database;

    private boolean isFavorit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        database = Room.databaseBuilder(
                        getApplicationContext(),
                        AppDatabase.class,
                        "endemik_db"
                )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        imgDetail = findViewById(R.id.imgDetail);

        btnBack = findViewById(R.id.btnBack);
        btnFavorit = findViewById(R.id.btnFavorit);

        txtNama = findViewById(R.id.txtNama);
        txtLatin = findViewById(R.id.txtLatin);
        txtFamili = findViewById(R.id.txtFamili);
        txtGenus = findViewById(R.id.txtGenus);
        txtAsal = findViewById(R.id.txtAsal);
        txtSebaran = findViewById(R.id.txtSebaran);
        txtDeskripsi = findViewById(R.id.txtDeskripsi);
        txtStatus = findViewById(R.id.txtStatus);

        btnBack.setOnClickListener(v -> finish());

        // WARNA AWAL FAVORIT
        btnFavorit.setColorFilter(
                getResources().getColor(
                        android.R.color.darker_gray
                )
        );

        String nama = getIntent().getStringExtra("nama");
        String latin = getIntent().getStringExtra("latin");
        String famili = getIntent().getStringExtra("famili");
        String genus = getIntent().getStringExtra("genus");
        String asal = getIntent().getStringExtra("asal");
        String sebaran = getIntent().getStringExtra("sebaran");
        String deskripsi = getIntent().getStringExtra("deskripsi");
        String status = getIntent().getStringExtra("status");
        String foto = getIntent().getStringExtra("foto");

        txtNama.setText(nama);
        txtLatin.setText("Nama Latin : " + latin);
        txtFamili.setText("Famili : " + famili);
        txtGenus.setText("Genus : " + genus);
        txtAsal.setText("Asal : " + asal);
        txtSebaran.setText("Sebaran : " + sebaran);
        txtStatus.setText("Status : " + status);
        txtDeskripsi.setText(deskripsi);

        Glide.with(this)
                .load(foto)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(imgDetail);

        // CEK APAKAH SUDAH FAVORIT
        List<Favorit> favoritList =
                database.favoritDao().getAllFavorit();

        for (Favorit item : favoritList) {

            if (item.getNama() != null &&
                    item.getNama().equals(nama)) {

                isFavorit = true;

                btnFavorit.setColorFilter(
                        getResources().getColor(
                                android.R.color.holo_red_dark
                        )
                );

                break;
            }
        }

        // TOGGLE FAVORIT
        btnFavorit.setOnClickListener(v -> {

            if (isFavorit) {

                List<Favorit> list =
                        database.favoritDao().getAllFavorit();

                for (Favorit item : list) {

                    if (item.getNama() != null &&
                            item.getNama().equals(nama)) {

                        database.favoritDao().delete(item);
                        break;
                    }
                }

                isFavorit = false;

                btnFavorit.setColorFilter(
                        getResources().getColor(
                                android.R.color.darker_gray
                        )
                );

                Toast.makeText(
                        DetailActivity.this,
                        "Favorit dihapus",
                        Toast.LENGTH_SHORT
                ).show();

            } else {

                Favorit favorit = new Favorit();

                favorit.setNama(nama);
                favorit.setFoto(foto);
                favorit.setAsal(asal);
                favorit.setStatus(status);
                favorit.setLatin(latin);
                favorit.setFamili(famili);
                favorit.setGenus(genus);
                favorit.setSebaran(sebaran);
                favorit.setDeskripsi(deskripsi);

                database.favoritDao().insert(favorit);

                isFavorit = true;

                btnFavorit.setColorFilter(
                        getResources().getColor(
                                android.R.color.holo_red_dark
                        )
                );

                Toast.makeText(
                        DetailActivity.this,
                        "Berhasil ditambahkan ke favorit",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}