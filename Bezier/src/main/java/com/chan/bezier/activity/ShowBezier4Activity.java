package com.chan.bezier.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chan.bezier.R;
import com.chan.bezier.bezier_views.BezierView_ElasticBall;

public class ShowBezier4Activity extends AppCompatActivity {

	private Button go;
	private BezierView_ElasticBall mElasticBall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_bezier4);
		go = (Button) findViewById(R.id.go);
		mElasticBall = (BezierView_ElasticBall) findViewById(R.id.elasticBall);
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mElasticBall.startAnimation ();
			}
		});
	}
}
