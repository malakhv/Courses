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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class contains data for multi choice list view (SimpleAdapter).
 * @author Mikhail Malakhov, 2016
 */
class ItemsMultipleChoice {

    /**
     * Number of list items.
     * */
    private static final int ITEMS_COUNT = 50;

    /** The name of field for title. */
    public static final String FIELD_TITLE = "title";

    /** The name of field for checked state. */
    public static final String FIELD_CHECKED = "checked";

    /** The data fields for items. */
    public static final String[] FIELDS = new String[] {FIELD_TITLE, FIELD_CHECKED};

    /**
     * Internal field for store data for list.
     * */
    private static List<ItemData> mListData = null;

    /* Static initialisation */
    static {
        mListData = new ArrayList<>(ITEMS_COUNT);
        for (int i = 1; i <= ITEMS_COUNT; i++) {
            final ItemData itemData = new ItemData();
            final String title = "List Item " + (i < 10 ? "0" : "") + i;
            itemData.setTitle(title);
            mListData.add(itemData);
        }
    }

    /**
     * Returns data for list.
     * */
    public static List<ItemData> getListData() { return mListData; }

    /**
     * Class implements data of one item in list.
     * */
    public static class ItemData extends HashMap<String, Object> {

        /** Construct a new {@link ItemData} instance with default parameters. */
        private ItemData() { super(); setChecked(false); }

        /** Changes the checked state of this item. */
        public void setChecked(boolean checked) { this.put(FIELD_CHECKED, checked); }

        /** Returns the current checked state of this item. */
        public boolean isChecked() { return (boolean) this.get(FIELD_CHECKED); }

        /** Set the title for this item. */
        public void setTitle(String title) { this.put(FIELD_TITLE, title); }

    }

}
