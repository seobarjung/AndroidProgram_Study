package kr.co.hanbit.mygooglemap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerDragListener, View.OnClickListener {
    private final int MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 1;
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (checkPermission()) {
            mMap.setMyLocationEnabled(true);
        } else {
            requestPermission();
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latlng) {
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latlng));
                //애니메이션 없이 이동
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
            }
        });

        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerDragListener(this);
        //mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        findViewById(R.id.button1).performClick();
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "InfoWindow " + marker.getSnippet(), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this, "Marker " + marker.getTitle(), Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    @Override
    public void onClick(View v) {
        mMap.clear();

        int id = v.getId();

        if (id == R.id.button1) {
            setPosition();
        } else if (id == R.id.button2) {
            addMarker();
        } else if (id == R.id.button3) {
            addPolyline();
        } else if (id == R.id.button4) {
            addPolygon();
        } else if (id == R.id.button5) {
            addCircle();
        }
    }

    private void setPosition() {
        LatLng latlng = new LatLng(37.394927, 127.111134);
        CameraPosition cp = new CameraPosition.Builder().target(latlng).zoom(15).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
    }

    private void addMarker() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.394927, 127.111134))
                .title("판교역").snippet("여기는 분당 판교역입니다.").draggable(true));
        mMap.addMarker(new MarkerOptions().position(new LatLng(37.397033, 127.105480))
                .title("화랑공원").snippet("여기는 화랑공원입니다.").draggable(true));
    }

    private void addPolyline() {
        PolylineOptions options = new PolylineOptions()
                .width(10).color(Color.BLUE)
                .add(new LatLng(37.396275, 127.112918))
                .add(new LatLng(37.396190, 127.118368))
                .add(new LatLng(37.393462, 127.118282))
                .add(new LatLng(37.393394, 127.116759));

        mMap.addPolyline(options);
    }

    private void addPolygon() {
        PolygonOptions options = new PolygonOptions()
                .strokeWidth(10).strokeColor(Color.RED)
                .add(new LatLng(37.399804, 127.109678),
                        new LatLng(37.399804, 127.112768),
                        new LatLng(37.391723, 127.112768),
                        new LatLng(37.391962, 127.109721),
                        new LatLng(37.399804, 127.109678));

        mMap.addPolygon(options);
    }

    private void addCircle() {
        CircleOptions options = new CircleOptions()
                .strokeWidth(10).strokeColor(Color.GREEN)
                .center(new LatLng(37.395218, 127.111158))
                .radius(100); // 미터 단위

        mMap.addCircle(options);
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED );
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
                MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission granted
                    if (checkPermission())
                        mMap.setMyLocationEnabled(true);

                } else {
                    // Permission denied

                }
                break;
            }
        }
    }
}
