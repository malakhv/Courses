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

package xds.courses.android_2.lesson_05.app02;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

/**
 * The widgets configuration Activity.
 * @author Mikhail.Malakhov
 * */
public class WidgetConfig extends Activity implements View.OnClickListener {

    /** The widget id which need to configure. */
    private int mWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    /** The Activity result intent. */
    private Intent mResult = null;

    /** The name of preference. */
    public final static String WIDGET_PREF = "widget_pref";

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_config);

		// Get widget id from intent and check it
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        if (mWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) finish(); // if wrong id!

        // Activity result
        mResult = new Intent();
        mResult.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mWidgetId);

	    /*
	     * Cancel by default
	     * */
        setResult(RESULT_CANCELED, mResult);
    }

    @Override
    public void onClick(View v) {

		// Check view ang get color
        if (v == null) { finish(); return; }
        int color = Color.parseColor(v.getTag().toString());

		// Save id and color to preference
        SharedPreferences options = getSharedPreferences(WIDGET_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = options.edit();
        editor.putInt(String.valueOf(mWidgetId), color);
        editor.commit();

	    // Result - OK
        setResult(RESULT_OK, mResult);
        finish();
    }
}
