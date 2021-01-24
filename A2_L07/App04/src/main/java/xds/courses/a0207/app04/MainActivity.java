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

package xds.courses.a0207.app04;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * The main activity in this app.
 * @author Mikhail.Malakhov
 * */
public class MainActivity extends Activity implements View.OnClickListener {
    private View mImage = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImage = this.findViewById(R.id.image);

        // Enter and Exit transition
        getWindow().setEnterTransition(new Fade());
        getWindow().setExitTransition(new Fade());
    }

    /**
     * Called when a view has been clicked.
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        final int id = v != null ? v.getId() : 0;

        // Show/hide view
        if (id == R.id.act_show) {

            /* Example 1 */
            /* if (mImage.getVisibility() == View.VISIBLE) {
                hideImage();
            } else {
                showImage();
            } */

            /* Example 2*/
            final Intent intent = new Intent(this, ImageActivity.class);
            startActivity(intent,
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        }
    }

    /**
     * Show image.
     * */
    private void showImage() {

        // Get the center for the clipping circle
        int cx = (mImage.getLeft() + mImage.getRight()) / 2;
        int cy = (mImage.getTop() + mImage.getBottom()) / 2;

        // Get the final radius for the clipping circle
        int finalRadius = Math.max(mImage.getWidth(), mImage.getHeight());

        // Create the animator for this view (the start radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(mImage, cx, cy, 0, finalRadius);

        // Make the view visible and start the animation
        mImage.setVisibility(View.VISIBLE);
        anim.start();
    }

    /**
     * Hide image
     * */
    private void hideImage() {

        // Get the center for the clipping circle
        int cx = (mImage.getLeft() + mImage.getRight()) / 2;
        int cy = (mImage.getTop() + mImage.getBottom()) / 2;

        // Get the initial radius for the clipping circle
        int initialRadius = mImage.getHeight();

        // Create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(mImage, cx, cy, initialRadius, 0);

        // Make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mImage.setVisibility(View.INVISIBLE);
            }
        });

        // Start the animation
        anim.start();
    }

}
