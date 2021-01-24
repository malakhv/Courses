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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * The widget in this app.
 * @author Mikhail.Malakhov
 * */
public class AppWidget extends AppWidgetProvider {

	/* The link to manager for quick access. */
	private AppWidgetManager widgetManager = null;
	
	/** All widgets ids. */
	int[] widgetIds = null;	
	
	/** The timer for updated time. */
	private Timer timer = null;	
	
	/** The current context. */
	private Context context = null;
	
	/** The link to preference for quick access. */
	private SharedPreferences options = null;
	
	/**
	 * Called when widget need to be updated.
	 * */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		// Store context
		this.context = context;

		// Shared preference
		options = context.getSharedPreferences(WidgetConfig.WIDGET_PREF, Context.MODE_PRIVATE);

		// Store widget manager
		widgetManager = appWidgetManager;
		
		// Cloning all ids for future work
		widgetIds = appWidgetIds.clone();				
		
		/* Configure timer */
		if (timer == null) {
			timer = new Timer();
			Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.SECOND, 1);
		    cal.set(Calendar.MILLISECOND, 0);
		    timer.scheduleAtFixedRate(new TimerTask() {				
				@Override
				public void run() { updateWidgets();}
			}, cal.getTime(), 1000);

		}

		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	/**
	 * Обновляет виджеты.
	 * */
	private void updateWidgets() {
		
		// Current time
		SimpleDateFormat format = new SimpleDateFormat("k:mm:ss", Locale.getDefault());
        String time = format.format(new Date());

        /* Example - 1 */
		// Update all widgets
		for (int i = 0; i < widgetIds.length; i++) {
			
			// Create view
			RemoteViews v = new RemoteViews(context.getPackageName(),
					R.layout.appwidget);			
			v.setTextViewText(R.id.tvTime, time);
						
			// Get color from preference and set it
			int color = options.getInt(String.valueOf(widgetIds[i]), -1);
			if (color != -1) v.setTextColor(R.id.tvTime, color);
			
			// Update widget
			widgetManager.updateAppWidget(widgetIds[i], v);
			
		}				
		
		/* Example - 2 */
		/* RemoteViews v = new RemoteViews(context.getPackageName(),
				R.layout.appwidget);			
		v.setTextViewText(R.id.tvTime, time);	
		widgetManager.updateAppWidget(widgetIds, v); */ 
	}
	
	/**
	 * При удалении виджета необходимо удалить информацию о нем из настроек.
	 * */
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {		
		super.onDeleted(context, appWidgetIds);
		if (options == null) return;
		SharedPreferences.Editor editor = options.edit();
	    for (int id : appWidgetIds) {
            editor.remove(String.valueOf(id));
        }
	    editor.commit();
	  }
}