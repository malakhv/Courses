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

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * The base class for any data bases in this app.
 * @author Mikhail.Malakhov
 * */
public abstract class DBSQLite extends SQLiteOpenHelper {

	/** The field store a link to {@link Context} for quick access. */
	private Context mContext = null;

	/**
     * Create a helper object to create, open, and/or manage a database. This method always returns
     * very quickly.  The database is not actually created or opened until one of
     * {@link #getWritableDatabase} or {@link #getReadableDatabase} is called.
     *
     * @param context The application context to use to open or create the database.
     * @param name The name of the database file, or null for an in-memory database.
     * @param factory The {@code CursorFactory} to use for creating cursor objects, or null for
     *                the default.
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database.
     * @param errorHandler the {@link DatabaseErrorHandler} to be used when sqlite reports database
     *                     corruption, or null to use the default error handler.
     * */
	public DBSQLite(Context context, String name, CursorFactory factory, int version,
            DatabaseErrorHandler errorHandler) {
		
		// Invoke a parent method
		super(context, name, factory, version, errorHandler);
		
		// Store context
		mContext = context; 
	}

    /**
     * Create a helper object to create, open, and/or manage a database. This method always returns
     * very quickly.  The database is not actually created or opened until one of
     * {@link #getWritableDatabase} or {@link #getReadableDatabase} is called.
     *
     * @param context The application context to use to open or create the database.
     * @param name The name of the database file, or null for an in-memory database.
     * @param factory The {@code CursorFactory} to use for creating cursor objects, or null for
     *                the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     * */
	public DBSQLite(Context context, String name, CursorFactory factory, int version) {
		
		/* Invoke a parent method */
		super(context, name, factory, version);
		
		/* Setting up Context value  */
		mContext = context;		
	}	

	/**
	 * Returns a link to the current {@link Context} object.
	 * */
	protected Context getContext() { return mContext; }

	/**
	 * Returns readable {@link Cursor} instance for a specified table.
	 * @param table The	name of table.
	 * */
	public Cursor getReadableCursor(String table) {		
		return this.getReadableDatabase().query(table, null, null, null, null, 
				null, null); 				
	}
	
	/**
	 * Returns writable {@link Cursor} instance for a specified table.
	 * @param table The	name of table.
	 * */
	public Cursor getWritableCursor(String table) {		
		return this.getWritableDatabase().query(table,null, null, null, null, 
				null, null); 				
	}	

	/**
	 * Execute a single SQL statement that is NOT a SELECT or any other SQL statement that returns
     * data.
	 * @param sql The SQL statement to be executed. Multiple statements separated by semicolons are
     *            not supported.
	 * */
	public static boolean execSQL(SQLiteDatabase db, String sql) {
		
		// Checking a data base object
		if (db == null) return false;

        // Checking a sql statement
        if (sql == null || sql.isEmpty()) return false;

		// Try to execute SQL statement
		try {
			db.execSQL(sql);
		} catch (SQLException  e) { e.printStackTrace(); return false; }
		return true;
	}
	
	/**
	 * Execute SQL query for drop specified table from data base.
	 * 	@param db The writable data base object.
	 * 	@param table The name of table that will be deleted.
	 * */
	public static boolean dropTable(SQLiteDatabase db, String table) {
		return DBSQLite.execSQL(db, "DROP TABLE IF EXISTS " + table);
	}
}