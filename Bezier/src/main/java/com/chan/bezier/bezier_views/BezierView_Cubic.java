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
 * 三阶贝塞尔曲线演示
 */
public class BezierView_Cubic extends View {

	private Paint mPaint;

	private int centerX, centerY; // 视图的中心点

	private PointF start; // 起始点
	private PointF end; // 结束点
	private PointF control1; // 控制点1
	private PointF control2; // 控制点2

	private int mode = 1;// 通过mode判断当前控制哪一个控制点

    public void setMode(int newMode){
        this.mode = newMode;
    }

	public BezierView_Cubic(Context context) {
		super(context);
	}

	public BezierView_Cubic(Context context, AttributeSet attrs) {
		super(context, attrs);

		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setTextSize(60);

		start = new PointF(0, 0);
		end = new PointF(0, 0);
		control1 = new PointF(0, 0);
		control2 = new PointF(0, 0);
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

		control1.x = centerX;
		control1.y = centerY - 100;

		control2.x = centerX;
		control2.y = centerY + 100;
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
		canvas.drawPoint(control1.x, control1.y, mPaint);
		canvas.drawPoint(control2.x, control2.y, mPaint);

		// 绘制辅助线
		mPaint.setColor(Color.BLACK);
		mPaint.setStrokeWidth(5);
		canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
		canvas.drawLine(control1.x, control1.y, control2.x, control2.y, mPaint);
		canvas.drawLine(control2.x, control2.y, end.x, end.y, mPaint);

		// 绘制贝塞尔曲线
		mPaint.setColor(Color.GREEN);
		mPaint.setStrokeWidth(4);

		Path path = new Path();
		path.moveTo(start.x, start.y);
		path.cubicTo(control1.x, control1.y, control2.x, control2.y, end.x,
				end.y);

		canvas.drawPath(path, mPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mode == 1) {
			control1.x = event.getX();
			control1.y = event.getY();
		} else if (mode == 2) {
			control2.x = event.getX();
			control2.y = event.getY();
		}
		invalidate();
		return true;
	}

}
