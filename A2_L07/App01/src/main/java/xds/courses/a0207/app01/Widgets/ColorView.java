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

package xds.courses.a0207.app01.Widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import xds.courses.a0207.app01.R;

/**
 * The simple view that fills its background with a solid color.
 * @author Mikhail.Malakhov
 * */
public class ColorView extends View {

    /** The paint object for drawing on canvas. */
    private Paint mPaint = null;

    /** The color for left half. */
    private int mLeftColor = Color.GREEN;

    /** The color for right half. */
    private int mRightColor = Color.RED;

	/**
	 * Constructor. This version is only needed if you will be instantiating the object manually
     * (not from a layout XML file).
     *
	 * @param context current application context
	 * */
	public ColorView(Context context) { super(context); init(); }

	/**
	 * Construct object, initializing with any attributes we understand from a layout file. These
     * attributes are defined in res/values/attrs.xml.
	 * 
	 * @see View#View(Context, AttributeSet)
	 * */
	public ColorView(Context context, AttributeSet attrs) {
        super(context, attrs); init();

        // Obtain style attributes
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ColorView);
        mLeftColor = a.getColor(R.styleable.ColorView_colorLeft, mLeftColor);
        mRightColor = a.getColor(R.styleable.ColorView_colorRight, mRightColor);
        a.recycle();

    }

    /**
     * Initialize inner components.
     * */
    private void init() {
        mPaint = new Paint();
    }
	
	/**
	 * Implement this to do your drawing.
	 * @param canvas the canvas on which the background will be drawn
	 * */
	@Override
	protected void onDraw(Canvas canvas) {

        /* Example 1 - fill view by color */
        //canvas.drawColor(Color.YELLOW);

        /* Example 2 - fill view by different colors */
        final int w = this.getWidth() / 2; // half of Width
        final int h = this.getHeight();

		// Left color
        mPaint.setColor(mLeftColor);
        canvas.drawRect(0, 0, w, h, mPaint);

        // Right color
        mPaint.setColor(mRightColor);
        canvas.drawRect(w, 0, this.getWidth(), h, mPaint);
	}
}