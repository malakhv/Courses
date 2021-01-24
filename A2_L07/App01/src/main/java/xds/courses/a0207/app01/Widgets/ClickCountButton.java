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
import android.util.AttributeSet;
import android.widget.Button;

import xds.courses.a0207.app01.R;

/**
 * The button that counts the number of clicks.
 * @author Mikhail.Malakhov
 * */
public class ClickCountButton extends Button {
	
	/** Field for store a number of clicks */
	private int mClickCount = 0;
		
	/**
	 * Get number of clicks value
	 * */
	public int getClickCount() { return mClickCount; }
		
	/**
	 * Reset number of clicks value
	 * */
	public void resetClickCount() { mClickCount = 0; } 
	
	/**
	 * Call this view's OnClickListener, if it is defined. Performs all normal 
	 * actions associated with clicking: reporting accessibility event, 
	 * playing a sound, etc.
	 * 
	 * @return True there was an assigned OnClickListener that was called, 
	 * false otherwise is returned.
	 *  
	 * */	
	@Override
	public boolean performClick() {
		mClickCount++;
		return super.performClick();
	}
		
	/**
	 * Constructor.  This version is only needed if you will be instantiating
	 * the object manually (not from a layout XML file).
	 * @param context 
	 * */
	public ClickCountButton(Context context) { super(context); }
	
	/**
	 * Construct object, initializing with any attributes we understand from a
	 * layout file. These attributes are defined in
	 * res/values/attrs.xml
	 * 
	 * @see android.view.View#View(Context, AttributeSet)
	 * */	
	public ClickCountButton(Context context, AttributeSet attrs) {
		super(context, attrs);

		/* Load Styled Attributes from resources */ 
		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.ClickCountButton);
		
		/* Load value from Styled Attributes */
		int clickCount = a.getInt(R.styleable.ClickCountButton_clickCoun, 0);

		/* Setting up value for number of clicks */
		mClickCount = clickCount;
		
		/* Give back a previously retrieved StyledAttributes, for later re-use */
		a.recycle();		
		
	}

}



































