package com.routing.ace.ace08;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    MyRoundLayout mrl1,mrl2,mrl3,mrl4;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mrl1 = (MyRoundLayout) this.findViewById(R.id.round_mrl1);
        mrl2 = (MyRoundLayout) this.findViewById(R.id.round_mrl2);
        mrl3 = (MyRoundLayout) this.findViewById(R.id.round_mrl3);
        mrl4 = (MyRoundLayout) this.findViewById(R.id.round_mrl4);
        iv = (ImageView) this.findViewById(R.id.round_iv);

        mrl1.setDrawTopLeft(15.0f);
        mrl1.setDrawBottomLeft(0.0f);
        mrl1.setDrawBottomRight(0.0f);
        mrl1.setDrawTopRight(0.0f);
        mrl2.setLeftDiagonal(15.0f);
        mrl3.setBottomDiagonal(15.0f);
        mrl4.setAllDiagonal(15.0f);

        ColorMatrix matrixpic = new ColorMatrix();
        matrixpic.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrixpic);
        iv.setColorFilter(filter);
    }
}
