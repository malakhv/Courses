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

package xds.courses.android_1.lesson_05.app03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

/**
 * The main activity in this app.
 * @author Mikhail Malakhov, 2016
 * */
public class MainActivity extends AppCompatActivity {

    /** ListView in this activity */
    private ListView mList = null;

    private PlanetsAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (ListView) this.findViewById(R.id.list);
        mAdapter = new PlanetsAdapter(this);
        mList.setAdapter(mAdapter);
    }
}
