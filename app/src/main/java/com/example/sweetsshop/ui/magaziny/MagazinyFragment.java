package com.example.sweetsshop.ui.magaziny;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sweetsshop.R;
import com.example.sweetsshop.databinding.FragmentMagazinyBinding;
import com.example.sweetsshop.models.Map;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MagazinyFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap gMap;
    private MapView mapView;

    private FragmentMagazinyBinding binding;
    List<Map> list_map = new ArrayList<>();
    MAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentMagazinyBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        createList();
        adapter = new MAdapter();
        adapter.setList_m(list_map);
        binding.rvMap.setAdapter(adapter);
        mapView = binding.mapView;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        return root;
    }

    private void createList() {
        list_map.add(new Map("pr. Chuy, 230", "+996 (502) 90-38-86", "3 km", R.drawable.place1));
        list_map.add(new Map("bul. Molodoy Gvardii, 3", "+996 (502) 59-03-61", "1.7 km", R.drawable.place2));
        list_map.add(new Map("ul. Moskovskaya, 195", "+996 (700) 65-35-65", "4 km", R.drawable.place3));
        list_map.add(new Map("ul. Kosmonavtov, 10", "+996 (502) 46-56-80", "1 km", R.drawable.place4));
        list_map.add(new Map("ul. Cheboksarskaya, 39", "+996 (702) 70-00-77", "2.7 km", R.drawable.place5));
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        LatLng location = new LatLng(42.882004, 74.582748);
        gMap.addMarker(new MarkerOptions().position(location).title("Bishkek"));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12));
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

}