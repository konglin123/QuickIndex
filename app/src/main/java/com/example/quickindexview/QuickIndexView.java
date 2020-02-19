package com.example.quickindexview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class QuickIndexView extends View {

    private int itemWidth;
    private int itemHeight;
    private Paint paint;
    private int wordWidth;
    private int wordHeight;

    private String[] words= { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "A", "S",
            "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B",
            "N", "M", "P" };
    private int wordX;
    private int wordY;
    private int touchIndex=-1;
    private OnWordChangeListener onWordChangeListener;

    public QuickIndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setTextSize(30);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth=getMeasuredWidth();
        itemHeight=getMeasuredHeight()/26;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < words.length; i++) {
            if(i==touchIndex){
                paint.setColor(Color.GRAY);
            }else{
                paint.setColor(Color.WHITE);
            }
            Rect rect = new Rect();
            paint.getTextBounds(words[i],0,1,rect);
            wordWidth=rect.width();
            wordHeight=rect.height();

            wordX = itemWidth/2-wordWidth/2;
            wordY = itemHeight/2+wordHeight/2+i*itemHeight;

            canvas.drawText(words[i],wordX,wordY,paint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                float y = event.getY();
                int index= (int) (y/itemHeight);
                if(index!=touchIndex){
                    touchIndex=index;
                    if(onWordChangeListener!=null){
                        onWordChangeListener.onWordChange(words[touchIndex]);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                touchIndex=-1;
                invalidate();
                break;

        }
        return true;
    }

    public interface OnWordChangeListener{
        void onWordChange(String word);
    }

    public void setOnWordChangeListener(OnWordChangeListener onWordChangeListener) {
        this.onWordChangeListener = onWordChangeListener;
    }
}
