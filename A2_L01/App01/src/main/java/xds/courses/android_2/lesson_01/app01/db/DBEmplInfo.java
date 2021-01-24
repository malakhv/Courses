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

package xds.courses.android_2.lesson_01.app01.db;

import android.provider.BaseColumns;

/**
 * Class-contract for EmplDB databases.
 * @see TableEmpl
 * @see TableDep
 *
 * @author Mikhail.Malakhov
 * */
public final class DBEmplInfo {

    /** Since this class has only static data, not needed to create an instance. */
    private DBEmplInfo() {}

    /**
     * Class contains information about {@code TableEmpl} table
     * @see TableDep
     * */
    public static class TableEmpl implements BaseColumns {

        /** Name of this table. */
        public static final String T_NAME = "tEmpl";

        /**
         * The name of employee.
         * <P>Type: TEXT</P>
         */
        public static final String C_NAME = "name";

        /**
         * Information about employee.
         * <P>Type: TEXT</P>
         */
        public static final String C_INFO = "info";

        /**
         * Department (id) that related with this employee.
         * <P>Type: INTEGER</P>
         */
        public static final String C_DEP_ID  = "dep_id";

        /** SQL query for a create this table. */
        public static final String SQL_CREATE = "CREATE TABLE " + T_NAME +
                " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                C_NAME + " TEXT," +
                C_INFO + " TEXT," +
                C_DEP_ID + " INTEGER)";
    }

    /**
     * Class contains information about {@code TableDep} table
     * @see TableEmpl
     * */
    public static class TableDep implements BaseColumns {

        /** Name of this table. */
        public static final String T_NAME = "tDep";

        /**
         * The name of department.
         * <P>Type: TEXT</P>
         */
        public static final String C_NAME = "NAME";

        /**
         * Department location.
         * <P>Type: TEXT</P>
         */
        public static final String C_LOCATION = "LOCATION";

        /** SQL query for a create this table. */
        public static final String SQL_CREATE = "CREATE TABLE " + T_NAME +
                " (" + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                C_NAME + " TEXT," +
                C_LOCATION + " TEXT)";
    }
}