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
import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity for action {@link Intent#ACTION_SEND}.
 * @author Mikhail.Malakhov
 * */
public class TextActivity extends Activity {

    /**
     * {@inheritDoc}
     * <p>Show text from intent.</p>
     * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        // Retrieve data from intent
        final Intent intent = getIntent();
        if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
            final TextView textView = (TextView) this.findViewById(R.id.text);
            textView.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        }
    }
}
