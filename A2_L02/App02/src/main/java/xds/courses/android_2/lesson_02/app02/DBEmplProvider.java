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

package xds.courses.android_2.lesson_02.app02;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import xds.courses.android_2.lesson_02.app02.data.DBEmpl;
import xds.courses.android_2.lesson_02.app02.data.DBEmplInfo;

/**
 * The {@link ContentProvider} for {@link DBEmpl} database.
 * @author Mikhail.Malakhov
 */
public class DBEmplProvider extends ContentProvider {

    /** The unique identifier of this {@link ContentProvider}. */
    private static final String AUTHORITY = "xds.courses.providers.hr";

    /** The object for matching URIs. */
    private static final UriMatcher sUriMatcher;

    /** The multiple rows in table TableDep. */
    private static final int MATCH_DEP_DATA = 1;

    /** The one row in table TableDep. */
    private static final int MATCH_DEP_ITEM = 2;

    /** The name field in table TableDep. */
    private static final int MATCH_DEP_ITEM_NAME = 3;

    /** The content URI for table TableDep. */
    public static final Uri CONTENT_DEP;

    /** The MIME-type for all rows in table TableDep. */
    public static final String CONTENT_TYPE_DEP_DATA;

    /** The MIME-type for one row in table TableDep. */
    public static final String CONTENT_TYPE_DEP_ITEM;

    /** The MIME-type for fields in table TableDep. */
    public static final String CONTENT_TYPE_DEP_ITEM_FIELD = "text/plain";

    /* Static initialisation section */
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        // All rows in table TableDep
        sUriMatcher.addURI(AUTHORITY, DBEmplInfo.TableDep.T_NAME, MATCH_DEP_DATA);
        // One row in table TableDep
        sUriMatcher.addURI(AUTHORITY, DBEmplInfo.TableDep.T_NAME + "/#", MATCH_DEP_ITEM);
        // Name field in table TableDep
        sUriMatcher.addURI(AUTHORITY, DBEmplInfo.TableDep.T_NAME + "/" + DBEmplInfo.TableDep.C_NAME,
                MATCH_DEP_ITEM_NAME);

        // Content URI for table TableDep
        final String all = "content://" + AUTHORITY + "/" + DBEmplInfo.TableDep.T_NAME;
        CONTENT_DEP = Uri.parse(all);

        // Types for table TableDep
        CONTENT_TYPE_DEP_DATA = "vnd.android.cursor.dir/" + AUTHORITY + "/" +
                DBEmplInfo.TableDep.T_NAME;
        CONTENT_TYPE_DEP_ITEM = "vnd.android.cursor.item/" + AUTHORITY + "/" +
                DBEmplInfo.TableDep.T_NAME;

    }

    /** The internal database. */
    private DBEmpl mDBEmpl = null;

    /** The {@link} for quick access. */
    private ContentResolver mContentResolver = null;

    /**
     * Implement this to initialize your content provider on startup.
     * @return True if the provider was successfully loaded, false otherwise.
     * */
    @Override
    public boolean onCreate() {
        mContentResolver = this.getContext().getContentResolver();
        mDBEmpl = new DBEmpl(this.getContext());
        return true;
    }

    /**
     * Implement this to handle query requests from clients.
     * @return a Cursor or {@code null}.
     * */
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {

        // All rows from table TableDep
        if (sUriMatcher.match(uri) == MATCH_DEP_DATA) {
            final Cursor c = mDBEmpl.getReadableDatabase().query(DBEmplInfo.TableDep.T_NAME,
                    projection, selection, selectionArgs, null, null, sortOrder);
            c.setNotificationUri(mContentResolver, uri); // For notify about changes
            return c;
        }

        // Default value
        return null;
    }

    /**
     * Implement this to handle requests for the MIME type of the data at the given URI. The
     * returned MIME type should start with <code>vnd.android.cursor.item</code> for a single
     * record, or <code>vnd.android.cursor.dir/</code> for multiple items.
     * @return A MIME type string, or {@code null} if there is no type.
     * */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        // All rows in table TableDep
        if (sUriMatcher.match(uri) == MATCH_DEP_DATA) {
            return CONTENT_TYPE_DEP_DATA;
        }

        // One row in table TableDep
        if (sUriMatcher.match(uri) == MATCH_DEP_ITEM) {
            return CONTENT_TYPE_DEP_ITEM;
        }

        // Name field in table TableDep
        if (sUriMatcher.match(uri) == MATCH_DEP_ITEM_NAME) {
            return CONTENT_TYPE_DEP_ITEM_FIELD;
        }

        // By default
        return null;
    }

    /**
     * Implement this to handle requests to insert a new row.
     * @return The URI for the newly inserted item.
     * */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {

        // Insert into TableDep
        if (sUriMatcher.match(uri) == MATCH_DEP_DATA) {
            long id = mDBEmpl.addDep(values);
            Uri itemUri = ContentUris.withAppendedId(CONTENT_DEP, id);
            mContentResolver.notifyChange(itemUri, null);
            return itemUri;
        }

        // Default value
        return null;
    }

    /**
     * Implement this to handle requests to delete one or more rows.
     * @return The number of rows affected.
     */
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        // Delete one row from TableDep
        if (sUriMatcher.match(uri) == MATCH_DEP_ITEM) {
            long id = ContentUris.parseId(uri);
            if (mDBEmpl.deleteDep(id)) {
                mContentResolver.notifyChange(uri, null);
                return 1;
            }
            return 0;
        }

        // Delete all rows from TableDep
        if (sUriMatcher.match(uri) == MATCH_DEP_DATA) {
            int count = mDBEmpl.getWritableDatabase().delete(DBEmplInfo.TableDep.T_NAME, null,
                    null);
            mContentResolver.notifyChange(uri, null);
            return count;
        }

        // By default
        return 0;
    }

    /**
     * Implement this to handle requests to update one or more rows.
     * @return the number of rows affected.
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}