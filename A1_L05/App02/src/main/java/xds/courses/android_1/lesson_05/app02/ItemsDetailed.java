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
import java.util.Map;

/**
 * Class contains data for list view (ArrayAdapter).
 * @author Mikhail Malakhov, 2016
 */
class ItemsDetailed {

    /** The name of field for title. */
    public static final String FIELD_TITLE = "title";

    /** The name of field for checked state. */
    public static final String FIELD_TEXT = "text";

    /** The data fields for items. */
    public static final String[] FIELDS = new String[] {FIELD_TITLE, FIELD_TEXT};

    /**
     * Returns data for adapter.
     * */
    public static List<Map<String, String>> getItems(int count) {

        // Checking count
        if (count <= 0)
            throw new IllegalArgumentException("count <= 0");

        // Make list
        final List<Map<String, String>> items = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            final Map<String, String> map = new HashMap<>(2);
            map.put(FIELD_TITLE, "Item " + (i+1));
            map.put(FIELD_TEXT, "This is item " + (i+1));
            items.add(map);
        }
        return items;
    }

}
