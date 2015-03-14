package edu.nyu.scps.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


/**
 * TODO: document your custom view class.
 */
public class TouchView extends View {
    private PointF p = new PointF();	//holds 2 floats
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Canvas canvas;

    public TouchView(final Context context) {
        super(context);
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        int x, y, initX, initY, offsetX, offsetY;

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float offsetX, offsetY = 0;
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        return true;

                    case MotionEvent.ACTION_UP:
                        Toast toast = Toast.makeText(context, "X = "+event.getX() + " Y = "+event.getY(), Toast.LENGTH_LONG);
                        toast.show();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        p.set(event.getX(), event.getY());
                        invalidate();

                        int yInt = 255-Math.round(event.getY()/15);

                        canvas.drawColor(Color.rgb(yInt, yInt, yInt));

                        paint.setColor(Color.rgb(255, 255-Math.round(event.getY()/25), 0));

                        return true;

                    case MotionEvent.ACTION_CANCEL:
                        p.set(0, 0);
                        paint.setColor(Color.RED);
                        invalidate();
                        return true;

                    default:
                        return false;
                }
            }
        });

    }


    @Override
    protected void onDraw(Canvas canvas) {


        super.onDraw(canvas);

        this.canvas = canvas;

        final int width = getWidth();
        final int height = getHeight();
        float radius = .1f * Math.min(width, height);

        //canvas.drawColor(Color.WHITE);	//background
        canvas.drawCircle(p.x, p.y, radius, paint);

    }
}
