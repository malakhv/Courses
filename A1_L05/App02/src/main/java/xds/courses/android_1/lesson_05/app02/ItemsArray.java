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

/**
 * Class contains data for list view (ArrayAdapter).
 * @author Mikhail Malakhov, 2016
 */
class ItemsArray {

    /**
     * Private field for store string array.
     * */
    private static String[] mItems =  { "Item 01", "Item 02", "Item 03", "Item 04", "Item 05",
            "Item 06", "Item 07", "Item 08", "Item 09", "Item 10", "Item 11", "Item 12", "Item 13",
            "Item 14", "Item 15", "Item 16", "Item 17", "Item 18", "Item 19", "Item 20" };

    /**
     * Returns data for adapter.
     * */
    public static String[] getItems() { return mItems; }

}