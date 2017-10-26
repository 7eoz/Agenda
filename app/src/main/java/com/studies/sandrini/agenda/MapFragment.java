package com.studies.sandrini.agenda;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.studies.sandrini.agenda.dao.StudentDAO;
import com.studies.sandrini.agenda.model.Student;

import java.io.IOException;
import java.util.List;


public class MapFragment extends SupportMapFragment implements OnMapReadyCallback {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng schoolPosition = getAddressCoordinate("Rua da Trindade, 290");
        if(schoolPosition != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(schoolPosition, 17);
            googleMap.moveCamera(update);
        }

        StudentDAO studentDAO = new StudentDAO(getContext());
        for(Student student: studentDAO.searchStudents()){
            LatLng coordinate = getAddressCoordinate(student.getAdress());
            if(coordinate !=null) {
                MarkerOptions marker = new MarkerOptions();
                marker.position(coordinate);
                marker.title(student.getName());
                marker.snippet(String.valueOf(student.getGrade()));
                googleMap.addMarker(marker);
            }
        }
        studentDAO.close();

        new GPSLocator(getContext(), googleMap);

    }

    private LatLng getAddressCoordinate(String address) {
        try {
            Geocoder geocoder = new Geocoder(getContext());
            List<Address> results = geocoder.getFromLocationName(address, 1);
            if(!results.isEmpty()){
                LatLng position = new LatLng(results.get(0).getLatitude(),results.get(0).getLongitude());
                return position;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
