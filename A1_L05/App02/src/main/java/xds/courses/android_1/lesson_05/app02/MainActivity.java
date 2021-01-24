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

package xds.courses.android_1.lesson_05.app02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * The main activity in this app.
 * @author Mikhail Malakhov, 2016
 * */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /** The tag for LogCat. */
    private static final String TAG = "App02";

    /** The UI component. ListView. */
    private ListView mList = null;

    /** The adapter for ListView. */
    private ListAdapter mAdapter = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mList = (ListView) this.findViewById(R.id.list);

        /* Example 01 - ArrayAdapter with default layout (one text field) */
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                ItemsArray.getItems());

        /* Example 02 - ArrayAdapter with default layout and data from resources */
        /* mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.list_items)); */

        /* Example 03 - ArrayAdapter with custom layout (TextView) */
        /* mAdapter = new ArrayAdapter<>(this, R.layout.list_item_text_view,
                ItemsArray.getItems()); */

        /* Example 04 - SimpleAdapter with default layout (two text fields) */
        /* mAdapter = new SimpleAdapter(this, ItemsDetailed.getItems(30),
                android.R.layout.simple_list_item_2,
                ItemsDetailed.FIELDS,
                new int[] {android.R.id.text1, android.R.id.text2}
        ); */

        /* Example 05 - Simple Adapter with default layout (multiple choice) */
        /* mAdapter = new SimpleAdapter(this, ItemsMultipleChoice.getListData(),
                android.R.layout.simple_list_item_multiple_choice, ItemsMultipleChoice.FIELDS,
                new int[] {android.R.id.text1, android.R.id.text1}
        ); */

        // Apply adapter to list
        mList.setAdapter(mAdapter);

        // Setting up listener for item click action
        mList.setOnItemClickListener(this);
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p/>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (mAdapter.getItem(position) instanceof ItemsMultipleChoice.ItemData) {

            // Get item by position
            final ItemsMultipleChoice.ItemData itemData = (ItemsMultipleChoice.ItemData)
                    mAdapter.getItem(position);

            // Get and update checked state
            boolean checked = !itemData.isChecked();
            itemData.setChecked(checked);

            // Notifies that the underlying data has been changed and any View reflecting the
            // data set should refresh itself
            ((SimpleAdapter) mAdapter).notifyDataSetChanged();

            // Write a log with item position and checked state
            Log.d(TAG, "Item in position " + position + " was " + (checked ? "checked" :
                    "unchecked"));

        } else {
            Log.d(TAG, "Item was clicked"); // Just write a log
        }
    }
}