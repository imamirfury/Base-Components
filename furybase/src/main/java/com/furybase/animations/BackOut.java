package com.furybase.animations;

import android.animation.TimeInterpolator;


public class BackOut implements TimeInterpolator {
    protected float param_s = 1.70158f;

    @Override
    public float getInterpolation(float t) {
        float s = param_s;
        return (t -= 1) * t * ((s + 1) * t + s) + 1;
    }

    public BackOut amount(float s) {
        param_s = s;
        return this;
    }
}