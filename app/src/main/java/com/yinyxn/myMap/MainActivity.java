package com.yinyxn.myMap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;

public class MainActivity extends AppCompatActivity implements LocationSource {

    MapView mapView;

    AMap map;
    AMapLocationClient locationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        initMap();
    }

    private void initMap() {
        map = mapView.getMap();

        // 设置定位
        map.setLocationSource(this);
        map.setMyLocationType(AMap.LOCATION_TYPE_MAP_ROTATE);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mapView.onDestroy();
    }

    @Override
    public void activate(final OnLocationChangedListener listener) {
        locationClient = new AMapLocationClient(this);

        // 设置监听器
        locationClient.setLocationListener(
                new AMapLocationListener() {
                    @Override
                    public void onLocationChanged(AMapLocation aMapLocation) {

                        listener.onLocationChanged(aMapLocation);
                    }
                });

        // 开始定位
        locationClient.startLocation();
    }

    @Override
    public void deactivate() {
        locationClient.stopLocation();
    }
}
