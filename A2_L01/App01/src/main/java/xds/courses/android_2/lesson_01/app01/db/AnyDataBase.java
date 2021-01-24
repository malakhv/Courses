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
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class-example minimum implementation of {@link SQLiteOpenHelper}.
 * */
public class AnyDataBase extends SQLiteOpenHelper {

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
	public AnyDataBase(Context context, String name, CursorFactory factory,
			int version, DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
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
	public AnyDataBase(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	/**
	 * Called when the database is created for the first time. This is where the creation of tables
	 * and the initial population of the tables should happen.
	 *
	 * @param db The database.
	 * */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Need to create tables and initialize them with data (if needed)
	}

	/**
	 * Called when the database needs to be upgraded. The implementation should use this method to
	 * drop tables, add tables, or do anything else it needs to upgrade to the new schema version.
	 *
	 * <p> This method executes within a transaction.  If an exception is thrown, all changes will
	 * automatically be rolled back. </p>
	 *
	 * @param db The database.
	 * @param oldVersion The old database version.
	 * @param newVersion The new database version.
	 * */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Need to upgrade a database from oldVersion to newVersion
	}
}