package com.chan.bezier.bezier_views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chan.bezier.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chan on 2016/5/22.
 *
 * 使用贝塞尔曲线绘制上升的爱心
 */
public class BezierView_FallInLove extends RelativeLayout {

	// 存放爱心的图片
	private List<Integer> heartList;

	// 屏幕宽高
	private int mWidth;
	private int mHeight;

	private Random random = new Random();

	private LayoutParams params;

	private PointF start; // 起始点
	private PointF end; // 终止点
	private PointF control1;// 控制点1
	private PointF control2;// 控制点2

	private Paint paint;

	public BezierView_FallInLove(Context context) {
		super(context);
		init();
		setWillNotDraw(false);
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(5);
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
	}

	public BezierView_FallInLove(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
		setWillNotDraw(false);
		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(5);
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
	}

	private void init() {
		start = new PointF();
		end = new PointF();
		control1 = new PointF();
		control2 = new PointF();

		// 初始化图片资源集合
		heartList = new ArrayList<>();
		heartList.add(R.drawable.h1);
		heartList.add(R.drawable.h2);
		heartList.add(R.drawable.h3);

		// 初始化布局参数
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params.addRule(ALIGN_PARENT_BOTTOM);
		params.addRule(CENTER_HORIZONTAL);

	}

	/**
	 * 添加心
	 */
	public void addHeart(Context context) {
		ImageView heart = new ImageView(context);
		heart.setImageResource(heartList.get(random.nextInt(3)));
		addView(heart, params);
		riseHeart(heart);
	}

	/**
	 * 添加上升动画
	 *
	 * 动画包括: 透明度由1.0f-0f
	 *
	 * 沿着贝塞尔曲线移动
	 */
	private void riseHeart(final ImageView imageView) {

		/* 前500ms让心形添加透明度动画，伸缩动画 */
		ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView,
				"alpha", 0.3f, 1.0f);
		ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(imageView,
				"scaleX", 0.2f, 1.0f);
		ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(imageView,
				"scaleY", 0.2f, 1.0f);
		AnimatorSet mAnimatorSet = new AnimatorSet();
		mAnimatorSet.setDuration(500);
		mAnimatorSet
				.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator);
		mAnimatorSet.setTarget(imageView);

		BezierEvalutor evalutor = new BezierEvalutor(control1, control2);
		ValueAnimator valueAnimator = ValueAnimator.ofObject(evalutor, start,
				end);
		valueAnimator
				.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						// 获取从估值器中计算后的坐标
						PointF pointF = (PointF) animation.getAnimatedValue();
						imageView.setX(pointF.x);
						imageView.setY(pointF.y);
						// 设置图片的透明度，让图片由1渐变到0透明度,getFraction()相当于动画的进度
						imageView.setAlpha(1 - animation.getAnimatedFraction());
					}
				});
		valueAnimator.setTarget(imageView);
		valueAnimator.setDuration(3000);
		valueAnimator.start();

		AnimatorSet allAnimationSet = new AnimatorSet();
		// 先后执行两个动画
		allAnimationSet.setTarget(imageView);
		allAnimationSet.playSequentially(mAnimatorSet, valueAnimator);
		allAnimationSet.start();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		mWidth = getMeasuredWidth();
		mHeight = getMeasuredHeight();

		// 初始化各个点
		start.x = mWidth / 2;
		start.y = mHeight;
		end.x = mWidth / 2;
		end.y = 0;
		Log.d("tag", "onMeasure()=" + start.x + "--" + start.y);
		Log.d("tag", "onMeasure()=" + end.x + "--" + end.y);

		control1.x = random.nextInt(mWidth / 2);
		control1.y = random.nextInt(mHeight / 2) + mHeight / 2;

		control2.x = random.nextInt(mWidth / 2) + mWidth / 2;
		control2.y = random.nextInt(mHeight / 2);
	}

	/**
	 * 自定义一个估值器实现起始点到终止点的过程中坐标的变化
	 */
	public class BezierEvalutor implements TypeEvaluator<PointF> {

		PointF control1Point;
		PointF control2Point;

		public BezierEvalutor(PointF control1Point, PointF control2Point) {
			super();
			this.control1Point = control1Point;
			this.control2Point = control2Point;
		}

		@Override
		public PointF evaluate(float t, PointF start, PointF end) {
			// 时间因子t: 0~1
			PointF point = new PointF();
			// 实现贝塞尔公式:
			point.x = start.x * (1 - t) * (1 - t) * (1 - t) + 3
					* control1Point.x * t * (1 - t) * (1 - t) + 3
					* control2Point.x * (1 - t) * t * t + end.x * t * t * t;// 实时计算最新的点X坐标
			point.y = start.y * (1 - t) * (1 - t) * (1 - t) + 3
					* control1Point.y * t * (1 - t) * (1 - t) + 3
					* control2Point.y * (1 - t) * t * t + end.y * t * t * t;// 实时计算最新的点Y坐标
			return point;// 实时返回最新计算出的点对象
		}

	}

}
