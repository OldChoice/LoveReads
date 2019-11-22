package gr.free.lovereads.mainreads.manager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import gr.free.lovereads.R;
import gr.free.lovereads.tools.CommClass;

public class WarnDividerItemDecoration extends RecyclerView.ItemDecoration {

    // 写右边字的画笔(具体信息)
    private Paint mPaint;
    private Paint mPaint1;

    // 左 上偏移长度
    private int itemView_leftinterval;
    private int itemView_topinterval;

    // 轴点半径
    private int circle_radius;

    // 图标
//    private Bitmap mIcon;


    // 在构造函数里进行绘制的初始化，如画笔属性设置等
    public WarnDividerItemDecoration(Context context) {

        // 轴点画笔
        mPaint = new Paint();
        mPaint.setColor(context.getResources().getColor(R.color.bottom));

        //圆点
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(context.getResources().getColor(R.color.green_light));

        // 赋值ItemView的左偏移长度，dp尺寸转换为px
        itemView_leftinterval = CommClass.dip2px(context, 100);

        // 赋值ItemView的上偏移长度
        itemView_topinterval = CommClass.dip2px(context, 25);

        // 赋值轴点圆的半径为10
        circle_radius = 10;

//         // 获取图标资源
//         mIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);

    }

    // 重写getItemOffsets（）方法
    // 作用：设置ItemView 左 & 上偏移长度
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 设置ItemView的左 & 上偏移长度分别为100 dp & 25dp,即此为onDraw()可绘制的区域
        outRect.set(itemView_leftinterval, itemView_topinterval, 0, 0);

    }

    // 重写onDraw（）
    // 作用:在间隔区域里绘制时光轴线 & 时间文本
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        // 获取RecyclerView的Child view的个数
        int childCount = parent.getChildCount();

        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {

            // 获取每个Item对象
            View child = parent.getChildAt(i);

            /**
             * 绘制轴点
             */
            // 轴点 = 圆 = 圆心(x,y)
            float centerx = child.getLeft() - itemView_leftinterval / 3;
            float centery = child.getTop() + child.getHeight() / 2;
            // 绘制轴点圆
            c.drawCircle(centerx, centery, 8, mPaint1);
            // 通过Canvas绘制角标
//             c.drawBitmap(mIcon,centerx - circle_radius ,centery - circle_radius,mPaint);

            /**
             * 绘制上半轴线
             */
            // 上端点坐标(x,y)
            float upLine_up_x = centerx;
            float upLine_up_y = child.getTop() - itemView_topinterval;

            // 下端点坐标(x,y)
            float upLine_bottom_x = centerx;
            float upLine_bottom_y = centery - circle_radius;

            //绘制上半部轴线
            c.drawLine(upLine_up_x, upLine_up_y, upLine_bottom_x, upLine_bottom_y, mPaint);

            /**
             * 绘制下半轴线
             */

            // 上端点坐标(x,y)
            float bottomLine_up_x = centerx;
            float bottom_up_y = centery + circle_radius;

            // 下端点坐标(x,y)
            float bottomLine_bottom_x = centerx;
            float bottomLine_bottom_y = child.getBottom();

            //绘制下半部轴线
            c.drawLine(bottomLine_up_x, bottom_up_y, bottomLine_bottom_x, bottomLine_bottom_y, mPaint);

        }
    }

}
