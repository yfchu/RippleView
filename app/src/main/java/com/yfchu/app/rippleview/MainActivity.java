package com.yfchu.app.rippleview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yfchu.app.customview.RippleView;

public class MainActivity extends AppCompatActivity {

    private Context mContext;
    private RippleView rippleBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        rippleBtn= (RippleView) findViewById(R.id.rippleBtn);

        rippleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext,"Click",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
