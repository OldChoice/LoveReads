package gr.free.lovereads.mainreads.manager;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import gr.free.lovereads.R;

/**
 * 类描述：
 * 创建人：guorui
 * 创建时间：2017/8/22 20:03
 * 修改人：guorui
 * 修改时间：2017/8/22 20:03
 * 修改备注：
 */

public class LineChartManager {

    private LineChart lineChart;
    private YAxis leftAxis;   //左边Y轴
    private YAxis rightAxis;  //右边Y轴
    private XAxis xAxis;      //X轴

    public LineChartManager(LineChart mLineChart) {
        this.lineChart = mLineChart;
        leftAxis = lineChart.getAxisLeft();
        rightAxis = lineChart.getAxisRight();
        xAxis = lineChart.getXAxis();
    }

    /**
     * 初始化LineChart
     */
    private void initLineChart() {
        lineChart.setDrawGridBackground(false);
        //显示边界
        lineChart.setDrawBorders(true);
        //设置动画效果
        lineChart.animateY(1000, Easing.EasingOption.Linear);
        lineChart.animateX(1000, Easing.EasingOption.Linear);

        //折线图例 标签 设置
        Legend legend = lineChart.getLegend();
        legend.setForm(Legend.LegendForm.LINE);
        legend.setTextSize(10f);
        //显示位置
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

        //XY轴的设置
        //X轴设置显示位置在底部，这里是从1开始
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(1f);
        xAxis.setGranularity(1f);
        //保证Y轴从0开始，不然会上移一点
        leftAxis.setAxisMinimum(0f);
//        leftAxis.setAxisMaximum(6000f);
        rightAxis.setAxisMinimum(0f);
    }

    /**
     * 初始化曲线 每一个LineDataSet代表一条线
     *
     * @param lineDataSet
     * @param color
     * @param mode        折线图是否填充
     * @param i           判断是第几条线
     */
    private void initLineDataSet(Context context, LineDataSet lineDataSet, int color, boolean mode, int i) {
        lineDataSet.setColor(color);
        lineDataSet.setCircleColor(color);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(1f);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(0f);
        //设置折线图填充
        lineDataSet.setDrawFilled(mode);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormSize(15f);
        //线模式为圆滑曲线（默认折线）
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        if (Utils.getSDKInt() >= 18) {
            Drawable drawable;
            // fill drawable only supported on api level 18 and above
            if (i == 0) {
                drawable = ContextCompat.getDrawable(context, R.drawable.linchart_water);
            } else {
                drawable = ContextCompat.getDrawable(context, R.drawable.linchart_light);
            }
            lineDataSet.setFillDrawable(drawable);
        } else {
//            lineDataSet.setFillColor(Color.WHITE);
        }


    }

    /**
     * 展示折线图(一条)
     *
     * @param xAxisValues
     * @param yAxisValues
     * @param label
     * @param color
     */
    public void showLineChart(Context context, List<Float> xAxisValues, List<Float> yAxisValues, String label, int color) {
        initLineChart();
        ArrayList<Entry> entries = new ArrayList<>();
        for (int i = 0; i < xAxisValues.size(); i++) {
            entries.add(new Entry(xAxisValues.get(i), yAxisValues.get(i)));
        }
        // 每一个LineDataSet代表一条线
        LineDataSet lineDataSet = new LineDataSet(entries, label);
        initLineDataSet(context, lineDataSet, color, false,0);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData data = new LineData(dataSets);
        //设置X轴的刻度数
        xAxis.setLabelCount(xAxisValues.size(), true);
        if (xAxisValues.size() > 0)
            lineChart.setData(data);
    }

    /**
     * 展示线性图(多条)
     *
     * @param xAxisValues
     * @param yAxisValues 多条曲线Y轴数据集合的集合
     * @param labels
     * @param colours
     */
    public void showLineChart(Context context, List<Float> xAxisValues, List<List<Float>> yAxisValues, List<String> labels, List<Integer> colours) {
        initLineChart();
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < yAxisValues.size(); i++) {
            ArrayList<Entry> entries = new ArrayList<>();
            for (int j = 0; j < yAxisValues.get(i).size(); j++) {
                if (j >= xAxisValues.size()) {
                    j = xAxisValues.size() - 1;
                }
                entries.add(new Entry(xAxisValues.get(j), yAxisValues.get(i).get(j)));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, labels.get(i));

            initLineDataSet(context, lineDataSet, colours.get(i), true, i);
            dataSets.add(lineDataSet);
        }
        LineData data = new LineData(dataSets);
        xAxis.setLabelCount(xAxisValues.size(), true);
        if (xAxisValues.size() > 0)
            lineChart.setData(data);
    }

    /**
     * 设置Y轴值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setYAxis(float max, float min, int labelCount) {
        if (max < min) {
            return;
        }
        leftAxis.setAxisMaximum(max);
        leftAxis.setAxisMinimum(min);
        leftAxis.setLabelCount(labelCount, false);

        rightAxis.setAxisMaximum(max);
        rightAxis.setAxisMinimum(min);
        rightAxis.setLabelCount(labelCount, false);
        lineChart.invalidate();
    }

    /**
     * 设置X轴的值
     *
     * @param max
     * @param min
     * @param labelCount
     */
    public void setXAxis(float max, float min, int labelCount) {
        xAxis.setAxisMaximum(max);
        xAxis.setAxisMinimum(min);
        xAxis.setLabelCount(labelCount, true);

        lineChart.invalidate();
    }

    /**
     * 设置高限制线
     *
     * @param high
     * @param name
     */
    public void setHightLimitLine(float high, String name, int color) {
        if (name == null) {
            name = "高限制线";
        }
        LimitLine hightLimit = new LimitLine(high, name);
        hightLimit.setLineWidth(2f);
        hightLimit.setTextSize(10f);
        hightLimit.setLineColor(color);
        hightLimit.setTextColor(color);
        leftAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置低限制线
     *
     * @param low
     * @param name
     */
    public void setLowLimitLine(int low, String name) {
        if (name == null) {
            name = "低限制线";
        }
        LimitLine hightLimit = new LimitLine(low, name);
        hightLimit.setLineWidth(4f);
        hightLimit.setTextSize(10f);
        leftAxis.addLimitLine(hightLimit);
        lineChart.invalidate();
    }

    /**
     * 设置描述信息
     *
     * @param str
     */
    public void setDescription(String str) {
        Description description = new Description();
        description.setText(str);
        lineChart.setDescription(description);
        lineChart.invalidate();
    }
}
