package com.chan.bezier.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chan.bezier.R;
import com.chan.bezier.bezier_views.BezierView_FallInLove;

public class ShowBezier5Activity extends AppCompatActivity {

    private Button btn_fallInLove;
    private BezierView_FallInLove fallInLove;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_show_bezier5);

        fallInLove = (BezierView_FallInLove) findViewById (R.id.fallInLove);

        btn_fallInLove = (Button) findViewById (R.id.btn_fallInLove);
        btn_fallInLove.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick (View v) {
                fallInLove.addHeart (ShowBezier5Activity.this);
            }
        });
    }
}
