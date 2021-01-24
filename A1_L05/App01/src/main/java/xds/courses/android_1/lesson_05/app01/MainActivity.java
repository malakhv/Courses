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

package xds.courses.android_1.lesson_05.app01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * The main activity in this app.
 * @author Mikhail Malakhov, 2016
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /** The object with some value. In this case this is model. */
    private MyValue mModel = null;

    /** The UI component for display value. */
    private TextView mText = null;

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) this.findViewById(R.id.text);
        mModel = new MyValue(5);
        showValue();
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = (v != null ? v.getId() : 0);

        // Button "Increase value"
        if (id == R.id.btn_increase) {
            mModel.inc();
            showValue();
        }

    }

    /**
     * Shows value in view.
     * */
    private void showValue() {
        mText.setText(mModel.toString());
    }

}
