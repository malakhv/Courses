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
import android.util.Log;
import android.widget.Toast;

/**
 * The {@link BroadcastReceiver} for {@link Intent#ACTION_POWER_CONNECTED} and
 * {@link Intent#ACTION_POWER_DISCONNECTED} actions.
 *
 * @author Mikhail.Malakhov
 * */
public class PowerReceiver extends BroadcastReceiver {

    /** The tag for LogCat. */
    private static final String TAG = PowerReceiver.class.getSimpleName();

    /**
     * This method is called when the BroadcastReceiver is receiving an Intent broadcast.
     *
     * @param context The Context in which the receiver is running.
     * @param intent  The Intent being received.
     * */
    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent != null ? intent.getAction() : null;

        // Message for toast
        int message = 0;
        if (Intent.ACTION_POWER_CONNECTED.equals(action)) {
            message = R.string.msg_power_connected;
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(action)) {
            message = R.string.msg_power_disconnected;
        }

        // Show a toast
        if (message != 0) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } else {
            Log.w(TAG, "onReceive: Unexpected intent action - " + action);
        }
    }
}