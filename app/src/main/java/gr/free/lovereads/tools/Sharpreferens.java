package gr.free.lovereads.tools;

import android.content.Context;
import android.content.SharedPreferences;

public class Sharpreferens {

    private Context context;
    private SharedPreferences sp;
    //账号名
    private String UserName;
    //密码
    private String PassWord;
    private String UserId;
    //用户姓名
    private String UserNameStr;
    private String UserType;
    private String TokenId;
    //设置K3密码并保存
    private String K3Psw;

    //泵房详情中泵房ID
    private String PumpId;
    //打印机设置名称和打印机名称
    private String PrinterName;
    private String PrinterCode;
    private String PrinterDeviceName;

    //集团仓库名称和仓位名称
    private String CangKuName;
    private String CangWeiName;
    //仓库编号和仓位编号
    private String CangKuNum;
    private String CangWeiNum;

    //库存中仓库名称
    private String KuCunCangKuName;

    //上函仓库名称和仓位名称
    private String SHCangKuName;
    private String SHCangWeiName;
    //仓库编号和仓位编号
    private String SHCangKuNum;
    private String SHCangWeiNum;
    //快递收发收件人必录状态是否选择要输入收件人工号
    private boolean express;

    //集团中生产选择人员后保存
    private String ProductionUserName;
    private String ProductionUserId;


    public Sharpreferens(Context context) {

        this.setContext(context);
        sp = context.getSharedPreferences("WisdomWater", Context.MODE_PRIVATE);

    }

    public String getK3Psw() {
        String K3Psw = sp.getString("K3Psw", "");
        return K3Psw;
    }

    public void setK3Psw(String k3Psw) {
        K3Psw = k3Psw;
        sp.edit().putString("K3Psw", K3Psw).commit();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getUserName() {
        String UserName = sp.getString("UserName", "");
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
        sp.edit().putString("UserName", UserName).commit();
    }

    public String getUserNameStr() {
        String UserNameStr = sp.getString("UserNameStr", "");
        return UserNameStr;
    }

    public void setUserNameStr(String userNameStr) {
        UserNameStr = userNameStr;
        sp.edit().putString("UserNameStr", UserNameStr).commit();
    }

    public String getPassWord() {
        String PassWord = sp.getString("PassWord", "");
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
        sp.edit().putString("PassWord", PassWord).commit();
    }

    public String getUserId() {
        String UserId = sp.getString("UserId", "");
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
        sp.edit().putString("UserId", UserId).commit();
    }

    public String getUserType() {
        String UserType = sp.getString("UserType", "");
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
        sp.edit().putString("UserType", UserType).commit();
    }

    public String getTokenId() {
        String TokenId = sp.getString("TokenId", "");
        return TokenId;
    }

    public void setTokenId(String tokenId) {
        TokenId = tokenId;
        sp.edit().putString("TokenId", TokenId).commit();
    }

    public String getPumpId() {
        String PumpId = sp.getString("PumpId", "");
        return PumpId;
    }

    public void setPumpId(String pumpId) {
        PumpId = pumpId;
        sp.edit().putString("PumpId", PumpId).commit();
    }

    public String getPrinterName() {
        String PrinterName = sp.getString("PrinterName", "");
        return PrinterName;
    }

    public void setPrinterName(String printerName) {
        PrinterName = printerName;
        sp.edit().putString("PrinterName", PrinterName).commit();
    }

    public String getPrinterCode() {
        String PrinterCode = sp.getString("PrinterCode", "");
        return PrinterCode;
    }

    public void setPrinterCode(String printerCode) {
        PrinterCode = printerCode;
        sp.edit().putString("PrinterCode", PrinterCode).commit();
    }

    public String getPrinterDeviceName() {
        String PrinterDeviceName = sp.getString("PrinterDeviceName", "");
        return PrinterDeviceName;
    }

    public void setPrinterDeviceName(String printerDeviceName) {
        PrinterDeviceName = printerDeviceName;
        sp.edit().putString("PrinterDeviceName", PrinterDeviceName).commit();
    }

    public String getCangKuName() {
        String CangKuName = sp.getString("CangKuName", "");
        return CangKuName;
    }

    public void setCangKuName(String cangKuName) {
        CangKuName = cangKuName;
        sp.edit().putString("CangKuName", CangKuName).commit();
    }

    public String getCangWeiName() {
        String CangWeiName = sp.getString("CangWeiName", "");
        return CangWeiName;
    }

    public void setCangWeiName(String cangWeiName) {
        CangWeiName = cangWeiName;
        sp.edit().putString("CangWeiName", CangWeiName).commit();
    }

    public String getCangKuNum() {
        String CangKuNum = sp.getString("CangKuNum", "");
        return CangKuNum;
    }

    public void setCangKuNum(String cangKuNum) {
        CangKuNum = cangKuNum;
        sp.edit().putString("CangKuNum", CangKuNum).commit();
    }

    public String getCangWeiNum() {
        String CangWeiNum = sp.getString("CangWeiNum", "");
        return CangWeiNum;
    }

    public void setCangWeiNum(String cangWeiNum) {
        CangWeiNum = cangWeiNum;
        sp.edit().putString("CangWeiNum", CangWeiNum).commit();
    }

    public String getKuCunCangKuName() {
        String KuCunCangKuName = sp.getString("KuCunCangKuName", "");
        return KuCunCangKuName;
    }

    public void setKuCunCangKuName(String kuCunCangKuName) {
        KuCunCangKuName = kuCunCangKuName;
        sp.edit().putString("KuCunCangKuName", KuCunCangKuName).commit();
    }


    public String getSHCangKuName() {
        String SHCangKuName = sp.getString("SHCangKuName", "");
        return SHCangKuName;
    }

    public void setSHCangKuName(String shCangKuName) {
        SHCangKuName = shCangKuName;
        sp.edit().putString("SHCangKuName", SHCangKuName).commit();
    }

    public String getSHCangWeiName() {
        String SHCangWeiName = sp.getString("SHCangWeiName", "");
        return SHCangWeiName;
    }

    public void setSHCangWeiName(String shCangWeiName) {
        SHCangWeiName = shCangWeiName;
        sp.edit().putString("SHCangWeiName", SHCangWeiName).commit();
    }

    public String getSHCangKuNum() {
        String SHCangKuNum = sp.getString("SHCangKuNum", "");
        return SHCangKuNum;
    }

    public void setSHCangKuNum(String shCangKuNum) {
        SHCangKuNum = shCangKuNum;
        sp.edit().putString("SHCangKuNum", SHCangKuNum).commit();
    }

    public String getSHCangWeiNum() {
        String SHCangWeiNum = sp.getString("SHCangWeiNum", "");
        return SHCangWeiNum;
    }

    public void setSHCangWeiNum(String shCangWeiNum) {
        SHCangWeiNum = shCangWeiNum;
        sp.edit().putString("SHCangWeiNum", SHCangWeiNum).commit();
    }

    public boolean isExpress() {
        boolean express = sp.getBoolean("express", true);
        return express;
    }

    public void setExpress(boolean express) {
        this.express = express;
        sp.edit().putBoolean("express", express).commit();
    }

    public String getProductionUserName() {
        String ProductionUserName = sp.getString("ProductionUserName", "");
        return ProductionUserName;
    }

    public void setProductionUserName(String productionUserName) {
        ProductionUserName = productionUserName;
        sp.edit().putString("ProductionUserName", ProductionUserName).commit();
    }

    public String getProductionUserId() {
        String ProductionUserId = sp.getString("ProductionUserId", "");
        return ProductionUserId;
    }

    public void setProductionUserId(String productionUserId) {
        ProductionUserId = productionUserId;
        sp.edit().putString("ProductionUserId", ProductionUserId).commit();
    }
}
