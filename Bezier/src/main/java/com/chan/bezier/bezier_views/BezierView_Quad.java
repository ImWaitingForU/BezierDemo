package com.chan.bezier.bezier_views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Chan on 2016/5/19.
 *
 * 二阶贝塞尔曲线的理解练习,根据触摸的位置更新控制点
 */
public class BezierView_Quad extends View {

	private Paint mPaint;

	private int centerX, centerY; // 视图的中心点

	private PointF start; // 起始点
	private PointF end; // 结束点
	private PointF control; // 控制点

	public BezierView_Quad (Context context) {
		super(context);

	}

	public BezierView_Quad (Context context, AttributeSet attrs) {
		super (context, attrs);

		mPaint = new Paint();
		mPaint.setColor(Color.BLUE);
		mPaint.setStrokeWidth(5f);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setTextSize(60);

		start = new PointF(0, 0);
		end = new PointF(0, 0);
		control = new PointF(0, 0);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		centerX = w / 2;
		centerY = h / 2;

		// 初始化数据点和控制点的位置
		start.x = centerX - 200;
		start.y = centerY;

		end.x = centerX + 200;
		end.y = centerY;

		control.x = centerX;
		control.y = centerY - 100;

	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制数据点和控制点
		mPaint.setColor(Color.RED);
		mPaint.setStrokeWidth(20);
		canvas.drawPoint(start.x, start.y, mPaint);
		canvas.drawPoint(end.x, end.y, mPaint);
		mPaint.setColor(Color.BLUE);
		canvas.drawPoint(control.x, control.y, mPaint);

        //绘制辅助线
        mPaint.setColor (Color.BLACK);
        mPaint.setStrokeWidth (5);
        canvas.drawLine (start.x,start.y,control.x,control.y,mPaint);
        canvas.drawLine (control.x,control.y,end.x,end.y,mPaint);

        //绘制贝塞尔曲线
        mPaint.setColor (Color.GREEN);
        mPaint.setStrokeWidth (4);

        Path path = new Path ();
        path.moveTo (start.x,start.y);
        path.quadTo (control.x,control.y,end.x,end.y);

        canvas.drawPath (path,mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		control.x = event.getX();
		control.y = event.getY();
		invalidate();
		return true;
	}
}
