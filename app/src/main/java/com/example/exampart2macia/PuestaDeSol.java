package com.example.exampart2macia;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PuestaDeSol extends AppCompatActivity
{
    private View mSunView;
    private View mSkyView;
    private View mMarView;

    private int FirstSkyColor, SecondSkyColor, ThirdSkyColor,ForthSkyColor;

    private boolean clicked;


    @Override
   protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_puestadesol);


        mSunView = findViewById(R.id.sol);
        mSkyView = findViewById(R.id.cielo);
        mMarView = findViewById(R.id.mar);
        Resources resources = getResources();
        FirstSkyColor = resources.getColor(R.color.azul);
        SecondSkyColor = resources.getColor(R.color.gris);
        ThirdSkyColor = resources.getColor(R.color.naranja);
        ForthSkyColor = resources.getColor(R.color.negroclaro);

        clicked = true;

        mSkyView.setOnClickListener(v -> {
            if (clicked == true)
            {
                startAnimation();
                clicked = false;
            }

        });

    }

    private void startAnimation()
    {
        float sunYstart = mSkyView.getTop();
        float sunYEnd = mSkyView.getHeight();
        ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(mSunView,"y", sunYstart, sunYEnd);
        heightAnimator.setDuration(3000);
        heightAnimator.setTarget(mSunView);

        ValueAnimator fondoAnimator = ValueAnimator.ofObject(new ArgbEvaluator(),FirstSkyColor,SecondSkyColor,ThirdSkyColor,ForthSkyColor);
        fondoAnimator.setDuration(4500);
        fondoAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSkyView.setBackgroundColor((int) fondoAnimator.getAnimatedValue());
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(heightAnimator).with(fondoAnimator);
        animatorSet.start();

    }


}



