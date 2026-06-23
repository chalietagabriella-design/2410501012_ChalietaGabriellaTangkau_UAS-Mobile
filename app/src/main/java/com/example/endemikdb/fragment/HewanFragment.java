
package com.example.endemikdb.fragment;

import android.os.Bundle;
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

public class HewanFragment extends Fragment {

    private RecyclerView recyclerView;

    private ArrayList<Endemik> hewanList;
    private ArrayList<Endemik> allHewanList;

    private EndemikAdapter adapter;

    public HewanFragment() {
        super(R.layout.fragment_hewan);
    }

    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState
    ) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerHewan);

        recyclerView.setLayoutManager(
                new GridLayoutManager(requireContext(), 2)
        );

        hewanList = new ArrayList<>();
        allHewanList = new ArrayList<>();

        loadData();
    }

    private void loadData() {

        ApiService apiService =
                ApiClient.getClient().create(ApiService.class);

        apiService.getEndemik().enqueue(
                new Callback<List<Endemik>>() {

                    @Override
                    public void onResponse(
                            Call<List<Endemik>> call,
                            Response<List<Endemik>> response
                    ) {

                        if (!isAdded()) {
                            return;
                        }

                        if (response.isSuccessful()
                                && response.body() != null) {

                            hewanList.clear();
                            allHewanList.clear();

                            for (Endemik item : response.body()) {

                                if (item.getTipe() != null &&
                                        item.getTipe()
                                                .equalsIgnoreCase("Hewan")) {

                                    hewanList.add(item);
                                    allHewanList.add(item);
                                }
                            }

                            adapter = new EndemikAdapter(
                                    getContext(),
                                    hewanList
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
                }
        );
    }

    public void filterData(String keyword) {

        if (adapter == null) return;

        ArrayList<Endemik> filteredList =
                new ArrayList<>();

        if (keyword == null ||
                keyword.trim().isEmpty()) {

            filteredList.addAll(allHewanList);

        } else {

            for (Endemik item : allHewanList) {

                if (item.getNama() != null &&
                        item.getNama()
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