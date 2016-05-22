package com.chan.bezier.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.chan.bezier.bezier_views.BezierView_Cubic;
import com.chan.bezier.R;

public class ShowBezier2Activity extends AppCompatActivity {

    private RadioGroup rg;
    private BezierView_Cubic mBezierView_cubic;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_show_bezier2);

        mBezierView_cubic = (BezierView_Cubic) findViewById (R.id.bezierview_cubic);
        rg = (RadioGroup) findViewById (R.id.rg);

        rg.setOnCheckedChangeListener (new RadioGroup.OnCheckedChangeListener () {
            @Override
            public void onCheckedChanged (RadioGroup group, int checkedId) {
                if (checkedId==R.id.rb1){
                    mBezierView_cubic.setMode (1);
                }else if (checkedId==R.id.rb2){
                    mBezierView_cubic.setMode (2);
                }
            }
        });
    }
}
