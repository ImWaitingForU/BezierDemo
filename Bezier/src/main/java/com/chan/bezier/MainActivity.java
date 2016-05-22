package com.chan.bezier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chan.bezier.activity.ShowBezier1Activity;
import com.chan.bezier.activity.ShowBezier2Activity;
import com.chan.bezier.activity.ShowBezier3Activity;
import com.chan.bezier.activity.ShowBezier4Activity;
import com.chan.bezier.activity.ShowBezier5Activity;

public class MainActivity extends AppCompatActivity
		implements
			View.OnClickListener {

	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn3 = (Button) findViewById(R.id.btn3);
		btn4 = (Button) findViewById(R.id.btn4);
		btn5 = (Button) findViewById(R.id.btn5);

		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		btn4.setOnClickListener(this);
		btn5.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn1 :
				startActivity(new Intent(MainActivity.this,
						ShowBezier1Activity.class));

				break;
			case R.id.btn2 :
				startActivity(new Intent(MainActivity.this,
						ShowBezier2Activity.class));
				break;
			case R.id.btn3 :
				startActivity(new Intent(MainActivity.this,
				                         ShowBezier3Activity.class));
				break;
			case R.id.btn4 :
				startActivity(new Intent(MainActivity.this,
				                         ShowBezier4Activity.class));
				break;
			case R.id.btn5 :
				startActivity(new Intent(MainActivity.this,
				                         ShowBezier5Activity.class));
				break;

			default :
				break;
		}
	}
}
