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
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import xds.courses.android_2.lesson_02.app02.data.DBEmpl;

/**
 * The {@link ContentProvider} for {@link DBEmpl} database.
 * @author Mikhail.Malakhov
 */
public class AnyContentProvider extends ContentProvider {

    /**
     * Implement this to initialize your content provider on startup. This method is called for all
     * registered content providers on the application main thread at application launch time. It
     * must not perform lengthy operations, or application startup will be delayed.
     *
     * @return True if the provider was successfully loaded, false otherwise.
     * */
    @Override
    public boolean onCreate() {
        return false;
    }

    /**
     * Implement this to handle query requests from clients. This method can be called from
     * multiple threads.
     *
     * @param uri The URI to query. This will be the full URI sent by the client. If the client is
     *            requesting a specific record, the URI will end in a record number.
     * @param projection The list of columns to put into the cursor. If {@code null} all columns
     *                   are included.
     * @param selection A selection criteria to apply when filtering rows. If {@code null} then all
     *                  rows are included.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values
     *                      from selectionArgs, in order that they appear in the selection. The
     *                      values will be bound as Strings.
     * @param sortOrder How the rows in the cursor should be sorted. If {@code null} then the
     *                  provider is free to define the sort order.
     *
     * @return a Cursor or {@code null}.
     * */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
            String sortOrder) {
        return null;
    }

    /**
     * Implement this to handle requests for the MIME type of the data at the given URI. The
     * returned MIME type should start with <code>vnd.android.cursor.item</code> for a single
     * record, or <code>vnd.android.cursor.dir/</code> for multiple items. This method can be
     * called from multiple threads.
     * @param uri the URI to query.
     * @return A MIME type string, or {@code null} if there is no type.
     * */
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    /**
     * Implement this to handle requests to insert a new row. This method can be called from
     * multiple threads.
     * @param uri The content:// URI of the insertion request. This must not be {@code null}.
     * @param values A set of column_name/value pairs to add to the database. This must not
     *               be {@code null}.
     * @return The URI for the newly inserted item.
     * */
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        return null;
    }

    /**
     * Implement this to handle requests to delete one or more rows. The implementation should
     * apply the selection clause when performing deletion, allowing the operation to affect
     * multiple rows in a directory. This method can be called from multiple threads.
     *
     * @param uri The full URI to query, including a row ID (if a specific record is requested).
     * @param selection An optional restriction to apply to rows when deleting.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values
     *                      from selectionArgs, in order that they appear in the selection. The
     *                      values will be bound as Strings.
     * @return The number of rows affected.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    /**
     * Implement this to handle requests to update one or more rows. The implementation should
     * update all rows matching the selection to set the columns according to the provided values
     * map. This method can be called from multiple threads.
     *
     * @param uri The URI to query. This can potentially have a record ID if this is an update
     *            request for a specific record.
     * @param values A set of column_name/value pairs to update in the database. This must not
     *               be {@code null}.
     * @param selection An optional filter to match rows to update.
     * @param selectionArgs You may include ?s in selection, which will be replaced by the values
     *                      from selectionArgs, in order that they appear in the selection. The
     *                      values will be bound as Strings.
     * @return the number of rows affected.
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}