package com.example.skycast;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;

public class CrossfadeDrawable extends Drawable {

    private final Drawable mDrawable1;
    private final Drawable mDrawable2;
    private int mAlpha = 0;

    public CrossfadeDrawable(Drawable drawable1, Drawable drawable2, Callback callback) {
        mDrawable1 = drawable1;
        mDrawable2 = drawable2;
        mDrawable1.setCallback(callback);
        mDrawable2.setCallback(callback);
    }



    @Override
    public void draw(Canvas canvas) {
        mDrawable1.draw(canvas);
        mDrawable2.draw(canvas);
        onCrossfade();
    }

    private void onCrossfade() {
        if (mAlpha >= 255) {
            return;
        }

        mAlpha += 5; // Adjust the step as needed for a smoother transition

        // Schedule the next draw
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            invalidateSelf();
        }, 16);

        invalidateSelf();
    }

    @Override
    public void setAlpha(int alpha) {
        // Not used
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        // Not used
    }

    @Override
    public int getOpacity() {
        return mDrawable1.getOpacity();
    }
}
