package com.furybase.animations;

import android.animation.TimeInterpolator;


public class ExpoIn implements TimeInterpolator {
    @Override
    public float getInterpolation(float t) {
        return (t == 0) ? 0 : (float) Math.pow(2, 10 * (t - 1));
    }
}