package com.example.moviecatalogueapi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

public class ImageCornerRadius extends android.support.v7.widget.AppCompatImageView {

    private Path path;

    public ImageCornerRadius(Context context) {
        super(context);
        init();
    }

    public ImageCornerRadius(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageCornerRadius(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        float radius = 18.0f;
        path.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}

