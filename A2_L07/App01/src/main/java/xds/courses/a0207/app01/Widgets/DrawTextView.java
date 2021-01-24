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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * The view that draws text by path.
 * @author Mikhail.Malakhov
 * */
public class DrawTextView extends View {

	/* Private field for store a Paint object */
	private Paint mPaint = null;	
	
	/* Private field for store a text */
	private final String mText = "Android";
	
	/* Private field for store a text size */
	private final int  mTextSize = 45;

	/**
	 * Constructor.  This version is only needed if you will be instantiating
	 * the object manually (not from a layout XML file).
	 * @param context 
	 * */
	public DrawTextView(Context context) {
		super(context); this.initView();
	}

	/**
	 * Construct object, initializing with any attributes we understand from a
	 * layout file. These attributes are defined in
	 * res/values/attrs.xml
	 * 
	 * @see View#View(Context, AttributeSet)
	 * */
	public DrawTextView(Context context, AttributeSet attrs) {
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
		mPaint.setTextSize(mTextSize);
		mPaint.setAntiAlias(true);
	}
		
	/**
	 * Implement this to do your drawing.
	 * @param canvas the canvas on which the background 
	 * will be drawn
	 * */
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
	
		/* 
		 * Подготовка данных 
		 * */
		
		/* Рссчет ширины текста */
		int textWidth = (int) mPaint.measureText(mText);
		
		/* Ширина компонента */
		int width = this.getWidth();
		
		/* Высота компонента */
		int height = this.getHeight();

		/** Example 1 - рисование текста */
		
		/* Заполнение области рисования сплошным цветом */
		canvas.drawColor(Color.WHITE);		
		
		/*
		 * Расчет координат точки начала отрисовки текста
		 * */
		int x = (width - textWidth) / 2;
		int y = (height + mTextSize) / 2;
		
		/* Отрисовка текста */
		canvas.drawText(mText, x, y, mPaint);
				
		/** 
		 * Example 2 - рисование текста по дуге 
		 * */		
		
		/* Заполнение области рисования сплошным цветом */
		canvas.drawColor(Color.WHITE);		
			
		/* 
		 * Расчет координат ограничивающего прямоугольника (левая, верхняя, 
		 * правая, нижняя). Коодинаты расчитываются таким образом, что бы текст
		 * помещался по центру (примерно) 
		 * */ 
		float l = (width / 2) - (textWidth / 2);
		float t = (height / 2);
		float r = l + textWidth;
		float b = t + height / 4;
		
		/* Создаем прямоугольник по координатам */
		RectF rect = new RectF(l,t,r,b);
		
		/* Рисуем прямоугольник (только для примера) */
		mPaint.setAlpha(25);
		canvas.drawRect(rect, mPaint);
				
		/* 
		 * Создание нового "Пути" - линии, вдоль которой будем выводить текст 
		 * */
		Path path = new Path();		
		
		/* 
		 * Добавляем дугу, вдоль которой будем выводить текст
		 * rect - границы овала, определяющие форму и размер дуги
		 * 240 - начальный угол (в градусах), где дуга начинается
		 * 90 - угол развертки (в градусах), считается по часовой стрелке
		 * 
		 * Дуга рисуется по часовой стрелке, угол в 0 градусов соответствует 
		 * геометрическому углу 0 градусов.
		 * 
		 * */		
		path.addArc(rect, 180, 180);
		
		/* Отрисовка дуги (только для примера) */
		mPaint.setAlpha(75);
		canvas.drawArc(rect, 180, 180, false, mPaint);	
				
		/* Рисуем текст вдоль дуги */
		mPaint.setAlpha(255);
		canvas.drawTextOnPath(mText, path, 0, 0, mPaint);
		
	}

}
