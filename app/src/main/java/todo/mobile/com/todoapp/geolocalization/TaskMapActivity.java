package todo.mobile.com.todoapp.geolocalization;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import todo.mobile.com.todoapp.R;

public class TaskMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;
    Button btnOpciones, btnMove, btnAnimate, btnPosition, btnMarker, btnLine, btnPolygon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ui();
    }

    private void ui() {
        btnOpciones = (Button) findViewById(R.id.btnOpciones);
        btnMove = (Button) findViewById(R.id.btnMove);
        btnAnimate = (Button) findViewById(R.id.btnAnimate);
        btnPosition = (Button) findViewById(R.id.btnPosition);

        btnMarker = (Button) findViewById(R.id.btnMarker);
        btnLine = (Button) findViewById(R.id.btnLines);
        btnPolygon = (Button) findViewById(R.id.btnPolygon);

        btnOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePosition();
            }
        });
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToMadrid();
            }
        });
        btnAnimate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateMadrid();
            }
        });
        btnPosition.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getPosition();
            }
        });

        btnMarker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertMarker();
            }
        });

        btnLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLines();
            }
        });

        btnPolygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPolygon();
            }
        });
    }

    private void showPolygon() {
        PolygonOptions rectangle = new PolygonOptions()
                .add(new LatLng(45.0, -12.0))
                .add(new LatLng(45.0, 5.0))
                .add(new LatLng(34.5, 5.0))
                .add(new LatLng(34.5, -12.0))
                .add(new LatLng(45.0, -12.0));

        rectangle.strokeWidth(8);
        rectangle.strokeColor(Color.RED);
        map.addPolygon(rectangle);
    }

    private void showLines() {
        PolygonOptions lines = new PolygonOptions()
                .add(new LatLng(45.0, -12.0))
                .add(new LatLng(45.0, 5.0))
                .add(new LatLng(34.5, 5.0))
                .add(new LatLng(34.5, -12.0))
                .add(new LatLng(45.0, -12.0));

        lines.strokeWidth(8);
        lines.strokeColor(Color.RED);
        map.addPolygon(lines);
    }

    private void insertMarker() {
        map.addMarker(new MarkerOptions()
            .position(new LatLng(40.3936945, -3.701519))
            .title("Country: Spain"));
    }

    private void getPosition() {
        CameraPosition cameraPosition = map.getCameraPosition();
        LatLng coords = cameraPosition.target;
        double latitude = coords.latitude;
        double longitude = coords.longitude;
        Toast.makeText(this, "Lat: " + latitude + " long: " + longitude, Toast.LENGTH_SHORT).show();
    }

    private void animateMadrid() {
        LatLng madrid = new LatLng(40.417325, -3.683081);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(madrid)
                .zoom(19)
                .bearing(45)
                .tilt(70)
                .build();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        map.animateCamera(cameraUpdate);
    }

    private void moveToMadrid() {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(40.41, -3.69), 5);
        map.moveCamera(cameraUpdate);
    }

    private void changePosition() {
        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        map.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMapToolbarEnabled(false);//Remove buttons at the bottom from map

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                Projection proj = map.getProjection();
                Point coord = proj.toScreenLocation(point);

                Toast.makeText(TaskMapActivity.this,
                        "Click\n" +
                        "Lat: " + point.latitude + "\n" +
                        "Long: " + point.longitude + "\n" +
                        "X: " + coord.x + " Y: " + coord.y,
                        Toast.LENGTH_SHORT).show();
            }
        });

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                Toast.makeText(TaskMapActivity.this,
                        "Camera change\n" +
                        "Lat: " + position.target.latitude + "\n" +
                        "Long: " + position.target.longitude + "\n" +
                        "Zoom: " + position.zoom + "\n" +
                        "Orientation: " + position.bearing + "\n" +
                                "Angulo: " + position.tilt
                        , Toast.LENGTH_SHORT).show();
            }
        });

        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(TaskMapActivity.this,
                        "Mark touched: \n" +
                marker.getTitle(),
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
