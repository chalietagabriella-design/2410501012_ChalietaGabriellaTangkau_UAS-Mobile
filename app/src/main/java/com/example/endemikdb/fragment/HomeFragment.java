package com.example.endemikdb.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.endemikdb.R;
import com.example.endemikdb.viewpager.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    private ImageButton btnSearch;
    private SearchView searchView;

    private ViewPagerAdapter adapter;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        btnSearch = view.findViewById(R.id.btnSearch);
        searchView = view.findViewById(R.id.searchView);

        adapter =
                new ViewPagerAdapter(requireActivity());

        viewPager.setAdapter(adapter);

        new TabLayoutMediator(
                tabLayout,
                viewPager,
                (tab, position) -> {

                    if (position == 0) {
                        tab.setText("Hewan");
                    } else {
                        tab.setText("Tumbuhan");
                    }
                }
        ).attach();

        tabLayout.setTabTextColors(
                getResources().getColor(R.color.grayText),
                getResources().getColor(R.color.grayText)
        );

        tabLayout.setSelectedTabIndicatorColor(
                getResources().getColor(R.color.grayText)
        );

        // Buka Search
        btnSearch.setOnClickListener(v -> {

            btnSearch.setVisibility(View.GONE);

            searchView.setVisibility(View.VISIBLE);

            searchView.setIconified(false);

            searchView.requestFocus();
        });

        // Tombol X Search
        int closeButtonId =
                androidx.appcompat.R.id.search_close_btn;

        ImageView closeButton =
                searchView.findViewById(closeButtonId);

        if (closeButton != null) {

            closeButton.setOnClickListener(v -> {

                searchView.setQuery("", false);

                adapter.getHewanFragment()
                        .filterData("");

                adapter.getTumbuhanFragment()
                        .filterData("");

                searchView.clearFocus();

                searchView.setVisibility(View.GONE);

                btnSearch.setVisibility(View.VISIBLE);
            });
        }

        // Search realtime
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {

                    @Override
                    public boolean onQueryTextSubmit(
                            String query
                    ) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(
                            String newText
                    ) {

                        adapter.getHewanFragment()
                                .filterData(newText);

                        adapter.getTumbuhanFragment()
                                .filterData(newText);

                        return true;
                    }
                });
    }
}