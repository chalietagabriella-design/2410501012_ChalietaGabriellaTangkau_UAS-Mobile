package com.example.endemikdb.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endemikdb.R;
import com.example.endemikdb.adapter.EndemikAdapter;
import com.example.endemikdb.api.ApiClient;
import com.example.endemikdb.api.ApiService;
import com.example.endemikdb.model.Endemik;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TumbuhanFragment extends Fragment {

    private RecyclerView recyclerView;

    private ArrayList<Endemik> tumbuhanList;
    private ArrayList<Endemik> allTumbuhanList;

    private EndemikAdapter adapter;

    public TumbuhanFragment() {
        super(R.layout.fragment_tumbuhan);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable android.os.Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerTumbuhan);

        recyclerView.setLayoutManager(
                new GridLayoutManager(requireContext(), 2)
        );

        tumbuhanList = new ArrayList<>();
        allTumbuhanList = new ArrayList<>();

        loadData();
    }

    private void loadData() {

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        apiService.getEndemik().enqueue(new Callback<List<Endemik>>() {

            @Override
            public void onResponse(
                    Call<List<Endemik>> call,
                    Response<List<Endemik>> response
            ) {

                if (response.isSuccessful()
                        && response.body() != null) {

                    tumbuhanList.clear();
                    allTumbuhanList.clear();

                    for (Endemik item : response.body()) {

                        if (item.getTipe()
                                .equalsIgnoreCase("Tumbuhan")) {

                            tumbuhanList.add(item);
                            allTumbuhanList.add(item);
                        }
                    }

                    adapter = new EndemikAdapter(
                            requireContext(),
                            tumbuhanList
                    );

                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(
                    Call<List<Endemik>> call,
                    Throwable t
            ) {

            }
        });
    }

    public void filterData(String keyword) {

        if (adapter == null) return;

        ArrayList<Endemik> filteredList =
                new ArrayList<>();

        if (keyword == null
                || keyword.trim().isEmpty()) {

            filteredList.addAll(allTumbuhanList);

        } else {

            for (Endemik item : allTumbuhanList) {

                if (item.getNama() != null
                        && item.getNama()
                        .toLowerCase()
                        .contains(
                                keyword.toLowerCase()
                        )) {

                    filteredList.add(item);
                }
            }
        }

        adapter.updateData(filteredList);
    }
}