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

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Class implements adapter for ListView.
 * @author Mikhail Malakhov, 2016
 */
class PlanetsAdapter extends BaseAdapter {

    /** The planets names. */
    private String[] mNames = null;

    /** The planets symbols. */
    private String[] mSymbols = null;

    /** The planets radius. */
    private String[] mRadius = null;

    /** The planets symbols. */
    private TypedArray mIcons = null;

    /**
     * LayoutInflater for inflate item's view.
     * */
    private LayoutInflater mInflater = null;

    /**
     * Construct a new {@link PlanetsAdapter} instance with default values.
     * */
    public PlanetsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        final Resources res = context.getResources();
        mNames = res.getStringArray(R.array.planets);
        mSymbols = res.getStringArray(R.array.planets_symbol);
        mRadius = res.getStringArray(R.array.planets_radius);
        mIcons = res.obtainTypedArray(R.array.planets_icon);
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() { return mNames.length; }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) { return mNames[position]; }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) { return position; }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose
     *                    view we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible
     *                    to convert this view to display the correct data, this method can create
     *                    a new view. Heterogeneous lists can specify their number of view types,
     *                    so that this View is always of the right type (see
     *                    {@link #getViewTypeCount()} and {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // View that will be returned
        View v = convertView;
        if (v == null) {
            v = mInflater.inflate(R.layout.planet_item, parent, false);
        }

        // Fill "Name"
        final String name = mNames[position];
        ((TextView) v.findViewById(R.id.name)).setText(name);

        // Fill "Symbol"
        final String symbol = mSymbols[position];
        ((TextView) v.findViewById(R.id.symbol)).setText(symbol);

        // Fill "Info"
        final String radius = mRadius[position];
        ((TextView) v.findViewById(R.id.radius)).setText(radius);

        // Load icon
        ((ImageView) v.findViewById(R.id.icon)).setImageDrawable(mIcons.getDrawable(position));

        return v;
    }

}
