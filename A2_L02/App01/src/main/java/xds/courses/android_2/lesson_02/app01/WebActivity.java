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
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Activity for action {@link Intent#ACTION_VIEW}.
 * @author Mikhail.Malakhov
 * */
public class WebActivity extends Activity {

    /** Web view for showing web-pages. */
    private WebView mWeb = null;

    /**
     * {@inheritDoc}
     * <p>Load web-page by uri into intent.</p>
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWeb = (WebView) findViewById(R.id.web);

        // Retrieve data from intent
        final Intent intent = getIntent();
        if (intent != null && Intent.ACTION_VIEW.equals(intent.getAction())) {
            final Uri uri = intent.getData();
            if (uri != null) {
                mWeb.loadUrl(uri.toString());
            }
        }
    }
}