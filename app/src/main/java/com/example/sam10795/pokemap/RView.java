package com.example.sam10795.pokemap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by SAM10795 on 18-09-2015.
 */
public class RView extends View {
    private Context mContext;
    private float x=0,y=0,l=0,u=0;
    Paint p = new Paint();

    public RView(Context context, float x, float y, float llim, float ulim)
    {
        super(context);
        mContext = context;
        this.x = x;
        this.y = y;
        l = llim;
        u = ulim;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p.setColor(getResources().getColor(R.color.material_blue_grey_900));
        p.setAlpha(200);
        canvas.drawRect(x-l,y-l,x+u,y+u,p);
    }
}
