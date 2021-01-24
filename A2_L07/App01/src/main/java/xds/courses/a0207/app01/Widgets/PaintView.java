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
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * The view that draws a grid and circles.
 * @author Mikhail.Malakhov
 * */
public class PaintView extends View {
	
	/* Private field for store a Paint object */
	private Paint mPaint = null;	
	
	/* Private field for store a line step value */
	private int cellSize = 30;

	/**
	 * Constructor.  This version is only needed if you will be instantiating
	 * the object manually (not from a layout XML file).
	 * @param context 
	 * */
	public PaintView(Context context) { super(context); this.initView(); }

	/**
	 * Construct object, initializing with any attributes we understand from a
	 * layout file. These attributes are defined in
	 * res/values/attrs.xml
	 * 
	 * @see View#View(Context, AttributeSet)
	 * */
	public PaintView(Context context, AttributeSet attrs) {
		super(context, attrs); this.initView();
	}
	
	/**
	 * Initialize this view
	 * */
	private void initView() {
		
		/* Create a Paint object */
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		mPaint.setStrokeWidth(1);
		
	}
		
	/**
	 * Implement this to do your drawing.
	 * @param canvas the canvas on which the background 
	 * will be drawn
	 * */
	@Override
	protected void onDraw(Canvas canvas) {

		/* Fill view by color */
		canvas.drawColor(Color.WHITE);
		
		/* Get Height and Width */
		int h = this.getHeight();
		int w = this.getWidth();
		
		/* Draw rows */
		for (int r = 0; r <= h / cellSize; r++ ) {
			int y = r * cellSize;			
			canvas.drawLine(0, y, w, y, mPaint);
		}
				
		/* Draw columns */
		for (int c = 0; c <=  w / cellSize; c++ ) {
			int x = c * cellSize;		
			canvas.drawLine(x, 0, x, h, mPaint);
		}
		
		/* Draw circle */		
		int r = (h / cellSize) + 1;				
		mPaint.setAntiAlias(true);	
		for (int i = 1; i < r; i += 2) {
			int xy = cellSize * i;
			canvas.drawCircle(xy,xy,cellSize,mPaint);
		}
		mPaint.setAntiAlias(false);
		
		
	}

}
