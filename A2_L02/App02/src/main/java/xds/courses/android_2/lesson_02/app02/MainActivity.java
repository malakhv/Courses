package xds.courses.android_2.lesson_02.app02;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import xds.courses.android_2.lesson_02.app02.data.DBEmplInfo;

public class MainActivity extends AppCompatActivity {

    /** The adapter for ListView. */
    private SimpleCursorAdapter mAdapter = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		/* Initialize UI components */
        ListView list = (ListView) findViewById(R.id.list);
        registerForContextMenu(list);

        // Receive a data from ContentProvider
        final Cursor c = getContentResolver().query(DBEmplProvider.CONTENT_DEP, null, null,
                null, null);

        /* Create arrays of columns and UI elements */
        String[] from = {DBEmplInfo.TableDep.C_NAME, DBEmplInfo.TableDep.C_LOCATION};
        int[] to = {R.id.name, R.id.location};

		/* Create simple Cursor adapter */
        mAdapter = new SimpleCursorAdapter(this, R.layout.dep_item, c, from, to, 1);

        // Setting up adapter for list
        if (list != null) list.setAdapter(mAdapter);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        int id = v != null ? v.getId() : 0;
        final MenuInflater inflater = getMenuInflater();

        // Menu for list
        if (id == R.id.list) {
            inflater.inflate(R.menu.list_menu, menu);
        }

    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item != null ? item.getItemId() : 0;

        // Add new item
        if (id == R.id.action_add) {
            // Prepare data
            ContentValues v = new ContentValues();
            int count = mAdapter.getCount();
            v.put(DBEmplInfo.TableDep.C_NAME, "New department " + ++count);
            v.put(DBEmplInfo.TableDep.C_LOCATION, "Location " + count);

            // Insert new record to a ContentProvider
            getContentResolver().insert(DBEmplProvider.CONTENT_DEP, v);
        }

        // Clear Items
        if (id == R.id.action_clear) {
            getContentResolver().delete(DBEmplProvider.CONTENT_DEP, null, null);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item != null ? item.getItemId() : 0;

        // Delete item
        if (id == R.id.act_delete_item) {
            final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                    item.getMenuInfo();
            final Uri uri = ContentUris.withAppendedId(DBEmplProvider.CONTENT_DEP, info.id);
            getContentResolver().delete(uri, null, null);
        }

        return super.onContextItemSelected(item);
    }
}