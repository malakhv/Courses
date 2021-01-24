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

package xds.courses.a0207.app01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import xds.courses.a0207.app01.Widgets.ClickCountButton;

/**
 * The main activity in this app.
 * @author Mikhail.Malakhov
 * */
public class MainActivity extends Activity implements View.OnClickListener {

    private ClickCountButton mClickCountButton = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mClickCountButton = (ClickCountButton) this.findViewById(R.id.click_count);
        displayClickCount();
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        final int id = v != null ? v.getId() : 0;

        // ClickCountButton
        if (id == R.id.click_count) {
            displayClickCount();
        }
    }

    /**
     * Display click count value on view.
     * */
    private void displayClickCount() {
        final int count = mClickCountButton.getClickCount();
        mClickCountButton.setText(String.valueOf(count));
    }

}
