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

package xds.courses.android_2.lesson_02.app01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** The internal request code for image pick action. */
    private static final int REQUEST_IMAGE_PICK = 1;

    /** The view for display images. */
    private ImageView mImage = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = (ImageView) findViewById(R.id.image);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu); return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item != null ? item.getItemId() : 0;

        // Help Activity
        if (id == R.id.act_help) {
            startActivity(HelpActivity.class);
            return true;
        }

        // Share
        if (id == R.id.act_share) {

            // Create "Send" intent
            final Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");  // MIME-type
            intent.putExtra(Intent.EXTRA_TEXT, "Any text for sharing");

            /* Example - 1 */
            //startActivity(intent);

            /* Example - 2 */
            // Create chooser
            final String title = this.getString(R.string.chooser_title);
            Intent chooser = Intent.createChooser(intent, title);

            // Verify the intent will resolve to at least one activity
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(chooser);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v != null ? v.getId() : 0;

        // Open web-page
        if (id == R.id.act_web) {
            Uri page = Uri.parse("http://www.android.com");
            final Intent intent = new Intent(Intent.ACTION_VIEW, page);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }

        // Pick Image
        if (id == R.id.act_image) {
            final Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
            intent.setType("image/*"); // MIME-type
            startActivityForResult(intent, REQUEST_IMAGE_PICK);
        }
    }

    /**
     * Start any activity without extra data.
     * */
    private void startActivity(Class<? extends Activity> actClass) {
        final Intent intent = new Intent(this, actClass);
        this.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Pick Image
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_IMAGE_PICK && data != null) {
            final Uri original = data.getData();
            mImage.setImageURI(original);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}