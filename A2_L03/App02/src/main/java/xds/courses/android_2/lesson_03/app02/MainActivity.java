/**
 * Copyright (C) 2013 xDevStudio
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */

package xds.courses.android_2.lesson_03.app02;

import android.app.Activity;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * The main {@link Activity} in this app.
 * @author Mikhail.Malakhov
 * */
public class MainActivity extends Activity implements OnMapReadyCallback {

    /** The Google Map object. */
    private GoogleMap mMap = null;

    /** Location manager */
    LocationManager mLocManager = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Location manager instance
        mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Receive Google Map object
        final MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Manipulates the map once available. This callback is triggered when the map is ready to be
     * used.
     * */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Enable the my-location layer in the map
        mMap.setMyLocationEnabled(true);

        // Disable my-location button
        mMap.getUiSettings().setMyLocationButtonEnabled(false);

        // Enable zoom controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item != null ? item.getItemId() : 0;

        // Map type - Normal
        if (id == R.id.menu_map_mode_normal) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
        // Map type - Satellite
        if (id == R.id.menu_map_mode_satellite) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            return true;
        }
        // Map type - Terrain
        if (id == R.id.menu_map_mode_terrain) {
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            return true;
        }

        // Show traffic, or not
        if (id == R.id.menu_map_traffic) {
            mMap.setTrafficEnabled(!mMap.isTrafficEnabled());
        }

        // My Location
        if (id == R.id.menu_map_location && mMap.isMyLocationEnabled()) {

            // Get last know location
            final Location loc = mLocManager.getLastKnownLocation(
                    LocationManager.PASSIVE_PROVIDER);

            // If location available
            if (loc != null) {
                // Create LatLng object for Maps
                LatLng target = new LatLng(loc.getLatitude(), loc.getLongitude());
                // Defines a camera move. An object of this type can be used to modify a map's camera
                // by calling moveCamera()
                CameraUpdate camUpdate = CameraUpdateFactory.newLatLngZoom(target, 15F);
                // Move camera to point with Animation
                mMap.animateCamera(camUpdate);
            }
        }

        // Add point to map
        if (id == R.id.menu_map_point_new) {
            // Add a marker in Moscow, and move the camera
            LatLng moscow = new LatLng(55.752830, 37.617257);
            mMap.addMarker(new MarkerOptions().position(moscow).title("Marker in Moscow"));
            moveCamera(moscow, 11F);
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Move Map's camera to specified target.
     * */
    private void moveCamera(LatLng target, float zoom) {
        if (target == null || zoom < 1) return;
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(target, zoom));
    }
}
