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

package xds.courses.android_2.lesson_03.app03;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

/**
 * The main {@link Activity} in this app.
 * @author Mikhail.Malakhov
 * */
public class MainActivity extends Activity {

    /** The tag for LogCat. */
    private final static String TAG = "LOCATION";

    /** The "no data" message. */
    private final static String MSG_NO_DATA = "No data";

    /* Section - UI components */
    private TextView mNetInfo = null;
    private TextView mGpsInfo = null;
    private TextView mPassiveInfo = null;
    private TextView mAddress = null;
    /* End of Section */

    /** The link to a {@link LocationManager} instance for quick access. */
    private LocationManager mLocManager = null;

    /** The link to a Location Listener instance. */
    private LocListener mLocListener = null;

    /**
     * Initialize UI components.
     * */
    private void initUi() {
        mNetInfo = (TextView) this.findViewById(R.id.net);
        mGpsInfo = (TextView) this.findViewById(R.id.gps);
        mPassiveInfo = (TextView) this.findViewById(R.id.passive);
        mAddress = (TextView) this.findViewById(R.id.address);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI
        initUi();

        // Location manager
        mLocManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        // Current Location
        Location loc = null;

        // Receive information from NET provider
        loc = mLocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        mNetInfo.setText(locToString(loc));

        // Receive information from GPS provider
        loc = mLocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mGpsInfo.setText(locToString(loc));

        // Receive information from Passive (virtual) provider
        loc = mLocManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        mPassiveInfo.setText(locToString(loc));

        // Request address by location
        if (loc != null) {
            mAddress.setText(getAddressByLoc(loc));
        }
    }

    /**
     * {@inheritDoc}
     * <p>Enable location listener.</p>
     * */
    @Override
    protected void onResume() {

        // Invoke a parent method, at first
        super.onResume();

		// Create Location Listener object (if needed)
        if (mLocListener == null) mLocListener = new LocListener();

		// Setting up Location Listener
        // min time - 3 seconds
        // min distance - 1 meter
        mLocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                3000L, 1.0F, mLocListener);
    }

    @Override
    protected void onPause() {

		// Remove Location Listener
        if (mLocListener != null) mLocManager.removeUpdates(mLocListener);

        // Invoke a parent method
        super.onPause();

    }

    /**
     * Convert location to string
     * */
    private String locToString(Location loc) {
        if (loc == null) return MSG_NO_DATA;
        return "Latitude: " + String.valueOf(loc.getLatitude()) + "\n" +
                "Longitude: " + String.valueOf(loc.getLongitude());
    }

    /**
     * Get address string by location
     * */
    private String getAddressByLoc(Location loc) {

		// Create geocoder
        final Geocoder geo = new Geocoder(this);

        // Try to get addresses list
        List<Address> list = null;
        try {
            list = geo.getFromLocation(loc.getLatitude(), loc.getLongitude(), 5);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }

        // If list is empty, return "No data" string
        if (list.isEmpty()) return MSG_NO_DATA;

        // Get first element from List
        Address a = list.get(0);

        // Get a Postal Code
        final int index = a.getMaxAddressLineIndex();
        String postal = null;
        if (index >= 0) {
            postal = a.getAddressLine(index);
        }

        // Make address string
        StringBuilder builder = new StringBuilder();
        final String sep = ", ";
        builder.append(postal).append(sep)
                .append(a.getCountryName()).append(sep)
                .append(a.getAdminArea()).append(sep)
                .append(a.getThoroughfare()).append(sep)
                .append(a.getSubThoroughfare());

        return builder.toString();
    }


    /**
     * Class that implements Location Listener interface
     * */
    private final class LocListener implements LocationListener {

        /**
         *  Called when the location has changed.
         * */
        @Override
        public void onLocationChanged(Location location) {
            Log.d(TAG, "onLocationChanged: " + location.toString());
            mGpsInfo.setText(locToString(location));
            mAddress.setText(getAddressByLoc(location));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { /* Empty */ }
        @Override
        public void onProviderEnabled(String provider) { /* Empty */ }
        @Override
        public void onProviderDisabled(String provider) { /* Empty */ }
    }

}
