package com.wilbrom.myudacityapplicationgooglemaps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final Double NEWYORK_LAT = 40.7127;
    private static final Double NEWYORK_LNG = -74.0059;

    private static final Double DUBLIN_LAT = 53.3478;
    private static final Double DUBLIN_LNG = 6.2597;

    private static final Double SEATTLE_LAT = 47.6204;
    private static final Double SEATTLE_LNG = -122.3491;

    GoogleMap m_map;
    boolean mapReady;

    MarkerOptions marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMap = (Button) findViewById(R.id.btnMap);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        });

        Button btnSatellite = (Button) findViewById(R.id.btnSatellite);
        btnSatellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            }
        });

        Button btnHybrid = (Button) findViewById(R.id.btnHybrid);
        btnHybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mapReady)
                    m_map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            }
        });

        marker = new MarkerOptions().position(new LatLng(24.711515, 46.674447)).title("Kingdom Tower").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_business_center_black_24dp));
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        LatLng latLng = null;
        CameraPosition target = null;

        switch (id) {
            case R.id.newyork:
                latLng = new LatLng(NEWYORK_LAT, NEWYORK_LNG);
                target = CameraPosition.builder().target(latLng).zoom(14).tilt(65).bearing(112.5f).build();
                m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
                return true;
            case R.id.dublin:
                latLng = new LatLng(DUBLIN_LAT, DUBLIN_LNG);
                target = CameraPosition.builder().target(latLng).zoom(14).tilt(65).bearing(112.5f).build();
                m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
                return true;
            case R.id.seattle:
                latLng = new LatLng(SEATTLE_LAT, SEATTLE_LNG);
                target = CameraPosition.builder().target(latLng).zoom(14).tilt(65).bearing(112.5f).build();
                m_map.animateCamera(CameraUpdateFactory.newCameraPosition(target), 5000, null);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapReady = true;
        m_map = googleMap;
        LatLng riyadh = new LatLng(24.713206, 46.672508);
        CameraPosition target = CameraPosition.builder().target(riyadh).zoom(14).tilt(65).bearing(112.5f).build();
        m_map.addMarker(marker);
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));
    }
}
