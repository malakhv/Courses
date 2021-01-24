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

package xds.courses.android_2.lesson_01.app01;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import xds.courses.android_2.lesson_01.app01.db.DBEmpl;
import xds.courses.android_2.lesson_01.app01.db.DBEmplInfo;

/**
 * The main Activity in this app.
 * */
public class MainActivity extends AppCompatActivity {

    /** ListView */
    private ListView mList = null;

    /** The adapter for ListView. */
    private ListAdapter mAdapter = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList = (ListView) findViewById(R.id.list);

        /* Example 1 */
        // Add several department to database
        final DBEmpl dep = MyApp.getDB();
        dep.addDep("Department in Moscow", "Moscow");

        /* Example 2 - Load data from db */
        Cursor c = dep.getDeps();
        final ArrayList<String> depList = new ArrayList<>();
        if (c != null && c.moveToFirst()) {
            int c_name = c.getColumnIndex(DBEmplInfo.TableDep.C_NAME);
            int c_location = c.getColumnIndex(DBEmplInfo.TableDep.C_LOCATION);

            // Helper variables
            String name;
            String location;

            // Read data for each record
            do {
                name = c.getString(c_name);
                location = c.getString(c_location);
                depList.add(name + " - " + location);
            } while (c.moveToNext());
        }
        if (c != null) c.close();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                depList.toArray());
        mList.setAdapter(mAdapter);

        /* Example 3 - SimpleCursorAdapter */
        c = MyApp.getDB().getReadableCursor(DBEmplInfo.TableDep.T_NAME);
        String[] from = {DBEmplInfo.TableDep.C_NAME, DBEmplInfo.TableDep.C_LOCATION};
        int[] to = {R.id.name, R.id.location};
        mAdapter = new SimpleCursorAdapter(this, R.layout.dep_item, c, from, to, 1);
        mList.setAdapter(mAdapter);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item != null ? item.getItemId() : -1;

        if (id == R.id.action_add) {
            int count = mAdapter.getCount();
            MyApp.getDB().addDep("New department " + ++count, "Any location");
            ((SimpleCursorAdapter) mAdapter).changeCursor(MyApp.getDB().getDeps());
        }

        if (id == R.id.action_clear) {
            MyApp.getDB().clearDeps();
            ((SimpleCursorAdapter) mAdapter).changeCursor(MyApp.getDB().getDeps());
        }

        return super.onOptionsItemSelected(item);
    }

}