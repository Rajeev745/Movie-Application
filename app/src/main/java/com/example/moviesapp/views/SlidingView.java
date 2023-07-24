package com.example.moviesapp.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.example.moviesapp.R;

public class SlidingView extends LinearLayout {
    public SlidingView(Context context) {
        super(context);
        init();
    }

    public SlidingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlidingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.sliding_view_layout, this);

        // Set the initial position of the view off-screen (outside the left side)
        setTranslationY(-getWidth());
    }

    public void slideIn() {
        // Use ObjectAnimator to animate the sliding motion
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationX", 0);
        animator.setDuration(500); // Adjust the duration as needed
        animator.start();
    }
}
