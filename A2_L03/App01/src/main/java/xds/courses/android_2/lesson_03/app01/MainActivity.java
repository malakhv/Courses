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

package xds.courses.android_2.lesson_03.app01;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

/**
 * The main {@link Activity} in this app.
 * @author Mikhail.Malakhov
 * */
public class MainActivity extends Activity implements View.OnClickListener {

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     * */
    @Override
    public void onClick(View v) {
        final int id = v != null? v.getId() : 0;

        // Location
        if (id == R.id.btn_1) {
            // Define string for URI scheme
			String geo = "geo:55.749792,37.632495";
			// Create intent object
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
            // You could specify package for use the GoogleMaps app, only
            intent.setPackage("com.google.android.apps.maps");
			// Start maps activity
			this.startActivity(intent);
        }

        // Location with zoom 3
        if (id == R.id.btn_2) {
            // Define string for URI scheme
            String geo = "geo:55.749792,37.632495?z=3";
            // Create intent object
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
            // You could specify package for use the GoogleMaps app, only
            intent.setPackage("com.google.android.apps.maps");
            // Start maps activity
            this.startActivity(intent);
        }

        // Location with zoom 15
        if (id == R.id.btn_3) {
            // Define string for URI scheme
            String geo = "geo:55.749792,37.632495?z=15";
            // Create intent object
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
            // You could specify package for use the GoogleMaps app, only
            intent.setPackage("com.google.android.apps.maps");
            // Start maps activity
            this.startActivity(intent);
        }

        // Location by address
        if (id == R.id.btn_4) {
            // Define string for URI scheme
            String geo = "geo:0,0?q=Красная площадь, дом 2";
            // Create intent object
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
            // You could specify package for use the GoogleMaps app, only
            //intent.setPackage("com.google.android.apps.maps");
            // Start maps activity
            this.startActivity(intent);
        }

        // Find any places by request
        if (id == R.id.btn_5) {
            // Define string for URI scheme
            String geo = "geo:0,0?q=Кафе в центре Москвы";
            // Create intent object
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geo));
            // You could specify package for use the GoogleMaps app, only
            intent.setPackage("com.google.android.apps.maps");
            // Start maps activity
            this.startActivity(intent);
        }
    }
}