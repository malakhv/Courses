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

package app01.lesson_05.android_2.courses.xds.a2_l05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * The main {@code Activity} in this app.
 * @author Mikhail.Malakhov
 * */
public class MainActivity extends AppCompatActivity {

    /** The broadcast receiver for {@link Intent#ACTION_BATTERY_CHANGED} action. */
    private BatteryChangedReceiver mBatteryChangedReceiver = null;

    /** The battery level */
    private TextView mLevel = null;

    /** The charging message */
    private TextView mCharging = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLevel = findViewWithId(R.id.level);
        mCharging = findViewWithId(R.id.charging);
    }

    /**
     * {@inheritDoc}
     * <p>Register broadcast receiver.</p>
     * */
    @Override
    protected void onResume() {
        super.onResume();
        // Register broadcast receiver and update battery status
        mBatteryChangedReceiver = new BatteryChangedReceiver();
        final Intent intent = this.registerReceiver(mBatteryChangedReceiver,
                PowerUtils.FILTER_BATTERY_CHANGED);
        updateBatteryStatus(intent);
    }

    /**
     * {@inheritDoc}
     * <p>Unregister broadcast receiver.</p>
     * */
    @Override
    protected void onPause() {
        // Unregister broadcast receiver
        if (mBatteryChangedReceiver != null) {
            this.unregisterReceiver(mBatteryChangedReceiver);
            mBatteryChangedReceiver = null;
        }
        super.onPause();
    }

    /**
     * Update battery status.
     * */
    private void updateBatteryStatus(int level, boolean charging) {
        final String l = level + "%";
        mLevel.setText(l);
        mCharging.setVisibility(charging ? View.VISIBLE : View.GONE);
    }

    /**
     * Update battery status.
     * */
    private void updateBatteryStatus(Intent intent) {
        if (intent == null) return;
        final boolean charging = PowerUtils.isCharging(intent);
        final int level = PowerUtils.getLevel(intent);
        updateBatteryStatus(level, charging);
    }

    /**
     * Finds a view that was identified by the id attribute from the XML that was processed in
     * {@link #onCreate}.
     * @return The view if found or null otherwise.
     * */
    @Nullable
    @SuppressWarnings("unchecked")
    private <T> T findViewWithId(@IdRes int id) {
        return (T) this.findViewById(id);
    }

    /**
     * The broadcast receiver for {@link Intent#ACTION_BATTERY_CHANGED} action.
     * */
    private final class BatteryChangedReceiver extends BroadcastReceiver {

        /** The tag for LogCat. */
        private final String TAG = BatteryChangedReceiver.class.getSimpleName();

        /**
         * This method is called when the BroadcastReceiver is receiving an Intent broadcast.
         * @param context The Context in which the receiver is running.
         * @param intent  The Intent being received.
         */
        @Override
        public void onReceive(Context context, Intent intent) {

            // Check intent
            final String action;
            if (intent != null) {
                action = intent.getAction();
                Log.d(TAG, "onReceive: " + action);
            } else {
                Log.w(TAG, "onReceive: intent is null");
                return;
            }

            // Action ACTION_BATTERY_CHANGED
            if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
                updateBatteryStatus(intent);
                return;
            }

            // Unexpected action!
            Log.w(TAG, "onReceive: Unexpected intent action - " + action);
        }
    }
}