package com.example.endemikdb.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.endemikdb.R;
import com.example.endemikdb.adapter.FavoritAdapter;
import com.example.endemikdb.room.AppDatabase;
import com.example.endemikdb.room.Favorit;

import java.util.List;

public class FavoritFragment extends Fragment {

    private RecyclerView recyclerView;
    private AppDatabase database;
    private FavoritAdapter adapter;

    public FavoritFragment() {
        super(R.layout.fragment_favorit);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerFavorit);

        recyclerView.setLayoutManager(
                new GridLayoutManager(requireContext(), 2)
        );

        database = Room.databaseBuilder(
                        requireContext(),
                        AppDatabase.class,
                        "endemik_db"
                )
                .allowMainThreadQueries()
                .build();

        loadFavorit();
    }

    private void loadFavorit() {

        List<Favorit> favoritList =
                database.favoritDao().getAllFavorit();

        adapter = new FavoritAdapter(
                requireContext(),
                favoritList
        );

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadFavorit();
    }
}