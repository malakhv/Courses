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

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * Class contains methods for checking power and battery status of device.
 * @author Mikhail.Malakhov
 * */
public class PowerUtils {

    /** The intent filter for {@link Intent#ACTION_BATTERY_CHANGED} action. */
    public static final IntentFilter FILTER_BATTERY_CHANGED = new IntentFilter(
            Intent.ACTION_BATTERY_CHANGED);

    /**
     * Checking the status of device charging.
     * @param intent The intent with action {@link Intent#ACTION_BATTERY_CHANGED}.
     * @return {@code True}, if device now is charging, otherwise {@code false}.
     * */
    public static boolean isCharging(Intent intent) {
        if (intent == null) return false;
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        return status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
    }

    /**
     * Checking the status of device charging.
     * @param context The current application context.
     * @return {@code True}, if device now is charging, otherwise {@code false}.
     * */
    public static boolean isCharging(Context context) {
        if (context == null) return false;
        final Intent intent = context.registerReceiver(null, FILTER_BATTERY_CHANGED);
        return isCharging(intent);
    }

    /**
     * Returns the current battery level.
     * @param intent The intent with action {@link Intent#ACTION_BATTERY_CHANGED}.
     * @return The current battery level in the percentage, or {@code 0}.
     * */
    public static int getLevel(Intent intent) {
        if (intent == null) return 0;
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        if (level > 0 && scale > 0) {
            return Math.round((level / (float) scale) * 100F);
        } else {
            return 0;
        }
    }

    /**
     * Returns the current battery level.
     * @param context The current application context.
     * @return The current battery level in the percentage, or {@code -1}.
     * */
    public static int getLevel(Context context) {
        if (context == null) return -1;
        final Intent intent = context.registerReceiver(null, FILTER_BATTERY_CHANGED);
        return getLevel(intent);
    }
}