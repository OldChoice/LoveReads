package gr.free.lovereads.rxhttp;

import android.os.Environment;
import android.text.TextUtils;

import java.io.File;

/**
 * 类名称：DaoFilePahtUtils
 * 创建者：Create by guorui
 * 创建时间：Create on 2017/5/15
 * 描述：获取文件或文件夹生成路径
 * 最近修改时间：2017/5/15
 */
public class DaoFilePahtUtils {
    public static String getSDPath() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            return Environment.getExternalStorageDirectory().toString();
        } else {
            return "";
        }
    }

    public static String getDBPath() {
        String sdCardPath = getSDPath();
        if (TextUtils.isEmpty(sdCardPath)) {
            return "";
        } else {
            //写数据库基础路径
            return sdCardPath + File.separator + "LoveReads";
        }
    }
}
