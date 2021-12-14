package com.furybase.animations;

import android.animation.TimeInterpolator;


public class QuintOut implements TimeInterpolator {
    @Override
    public float getInterpolation(float t) {
        return (t -= 1) * t * t * t * t + 1;
    }
}