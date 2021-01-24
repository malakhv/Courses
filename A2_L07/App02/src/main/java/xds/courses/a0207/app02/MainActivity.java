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

package xds.courses.a0207.app02;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * The main activity in this app.
 * @author Mikhail.Malakhov
 * */
public class MainActivity extends Activity implements View.OnClickListener {

    /** Button for start/stop animation. */
    private Button mStart = null;

    /** Animation container. */
    private ImageView mAnimationView = null;

    /** Animation object. */
    private AnimationDrawable mAnimationDrawable = null;

    /**
     * Initialize user interface (UI) components.
     * */
    private void initUi() {
        mStart = (Button) this.findViewById(R.id.act_start);
        mAnimationView = (ImageView) this.findViewById(R.id.animation);
    }

    /**
     * {@inheritDoc}
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
    }

    /**
     * Initialize animation drawable object.
     * */
    private void initAnimationDrawable() {
        if (mAnimationDrawable != null) return;
        // Setting up animation to ImageView
        mAnimationView.setBackgroundResource(R.drawable.frame_by_frame);
        // Get AnimationDrawable object
        mAnimationDrawable = (AnimationDrawable) mAnimationView.getBackground();
        // Sets whether the animation should play once (true) or repeat (false)
        mAnimationDrawable.setOneShot(true);
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v != null ? v.getId() : 0;

        // Start/stop animation
        if (id == R.id.act_start) {
            initAnimationDrawable();
            if (mAnimationDrawable.isRunning()) {
                mAnimationDrawable.stop();
                mAnimationDrawable.start(); // For restart animation
            } else {
                mAnimationDrawable.start();
            }
        }
    }
}