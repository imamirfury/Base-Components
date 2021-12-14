package com.furybase.animations;

import android.animation.TimeInterpolator;


public class ExpoOut implements TimeInterpolator {
    @Override
    public float getInterpolation(float t) {
        return (t == 1) ? 1 : -(float) Math.pow(2, -10 * t) + 1;
    }
}