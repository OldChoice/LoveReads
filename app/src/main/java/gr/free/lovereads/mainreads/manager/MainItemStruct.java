package gr.free.lovereads.mainreads.manager;

import android.view.View;

/**
 * 表格布局时候方便添加图片文字及点击等操作
 */
public class MainItemStruct {
    private Integer icon;
    private String txt;
    private String msg;

    private View.OnClickListener click;

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public View.OnClickListener getClick() {
        return click;
    }

    public void setClick(View.OnClickListener click) {
        this.click = click;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
