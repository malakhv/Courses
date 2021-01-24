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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import xds.courses.android_2.lesson_01.app01.R;

/**
 * The class implements a database in this app.
 * @author Mikhail.Malakhov
 * */
public class DBEmpl extends DBSQLite {

    /** The SQL WHERE statement for select a one element by id. */
    private static final String SQL_WHERE_BY_ID = BaseColumns._ID + "=?"; // _id=?

    /** The name of database file. */
    private static final String DB_NAME = "DBEmpl.db";

    /** The database version. */
    private static final int DB_VERSION = 4;

    /**
     * Construct {@link DBEmpl} instance with default parameter.
     * @param context	The current application context.
     * */
    public DBEmpl(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     * @param db The database.
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create tables
        DBSQLite.execSQL(db, DBEmplInfo.TableEmpl.SQL_CREATE);
        DBSQLite.execSQL(db, DBEmplInfo.TableDep.SQL_CREATE);

        /* Init TableDep */

        // Loading raw data from resources
        String[] items = getContext().getResources().getStringArray(R.array.dep_items);
        // Create object for store couples of names and values
        ContentValues values = new ContentValues(2);
        // Fill table
        for (int i = 0; i < items.length; i++) {
            String[] item = items[i].split("-");
            // Fill values
            values.put(DBEmplInfo.TableDep.C_NAME, item[0]);
            values.put(DBEmplInfo.TableDep.C_LOCATION, item[1]);
            // Add record to a data base
            db.insert(DBEmplInfo.TableDep.T_NAME, null, values);
        }
    }

    /**
     * Add new department to database.
     * @param name The name of department.
     * @param location The department location.
     * @return The row ID of the newly inserted department, or -1 if an error occurred.
     * */
    public long addDep(String name, String location) {
        ContentValues v = new ContentValues();
        v.put(DBEmplInfo.TableDep.C_NAME, name);
        v.put(DBEmplInfo.TableDep.C_LOCATION, location);
        return this.getWritableDatabase().insert(DBEmplInfo.TableDep.T_NAME, null, v);
    }

    /**
     * Update information about department into a data base.
     * @param id The id of department that will be updated.
     * @param name	new department name
     * @param location	new department location
     * @return True, if was been updated only one element
     * */
    public boolean updateDep(long id, String name, String location) {
        ContentValues v = new ContentValues();
        v.put(DBEmplInfo.TableDep.C_NAME, name);
        v.put(DBEmplInfo.TableDep.C_LOCATION, location);
        return 1 == this.getWritableDatabase().update(DBEmplInfo.TableDep.T_NAME, v,
                SQL_WHERE_BY_ID, new String[] {String.valueOf(id)});
    }

    /**
     * Delete department from data base.
     * @return True, if deleted successfully
     * */
    public boolean deleteDep(long id) {
        return 1 == this.getWritableDatabase().delete(DBEmplInfo.TableDep.T_NAME, SQL_WHERE_BY_ID,
                new String[]{String.valueOf(id)});
    }

    /**
     * Returns all data from TableDep table.
     * */
    public Cursor getDeps() { return getReadableCursor(DBEmplInfo.TableDep.T_NAME); }

    /**
     * Delete all departments from data base.
     * */
    public void clearDeps() {
        this.getWritableDatabase().delete(DBEmplInfo.TableDep.T_NAME, null, null);
    }

    /**
     * Called when the database needs to be upgraded.
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTable(db, DBEmplInfo.TableDep.T_NAME);
        dropTable(db, DBEmplInfo.TableEmpl.T_NAME);
        onCreate(db);
    }
}