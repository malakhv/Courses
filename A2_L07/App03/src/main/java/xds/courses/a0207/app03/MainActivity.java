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

package xds.courses.a0207.app03;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * The main activity in this app.
 * @author Mikhail.Malakhov
 * */
public class MainActivity extends Activity {
    /* Private fields for store links to UI components */
    private ListView mList = null;

    /**
     * Initialize UI components.
     * */
    private void initUI() {
        mList = (ListView) this.findViewById(R.id.list);
        ArrayAdapter<String> lvAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.items));
        mList.setAdapter(lvAdapter);
    }

    /**
     * Called when the activity is starting (for more details, please see description into super
     * class).
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initUI();
    }
}
