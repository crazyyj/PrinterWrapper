package cn.newchar.printutil.client;


import android.text.TextUtils;


import java.text.SimpleDateFormat;
import java.util.Date;

import cn.newchar.printutil.CompatPrintData;
import cn.newchar.printutil.IPrinterClient;
import cn.newchar.printutil.Utils;

/**
 * Created by newlq on 2017/1/20.
 * 打印机型号Pos88v
 */

public class Pos88vClient implements IPrinterClient {


    private static final int MAX_TEXT_LENGTH_ENGLISH = 48;

    public Pos88vClient() {
//      初始化打印器
    }

    @Override
    public void drawBigText(String text, int aligntype) {

    }

    @Override
    public void drawOrdinaryText(String text, int aligntype) {

    }

    @Override
    public void drawSmallText(String text, int aligntype) {

    }

    @Override
    public void drawLine(String text) {

    }

    private String oneLine(String text) {
        int part = getOneLineMaxLength() / Utils.measureTextWidth(text);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < part; i++) {
            builder.append(text);
        }
        return builder.toString();
    }

    /**
     * 画一行字符
     * @param c 要一行的文字
     * @return 整好的一行的文字
     */
    private String oneLine(char c) {
        int i = 0;
        int j = getOneLineMaxLength() * (Utils.isChinese(c) ? 2 : 1);
        StringBuilder builder = new StringBuilder();
        while (i < j) {
            builder.append(c);
            i++;
        }
        return builder.toString();
    }

    @Override
    public void drawLine(char c) {
//        根据 oneLine(c) 方法打印一行此字符
        drawLineEnd();
    }

    @Override
    public void drawQrCodeImg(String qrCodeText, int aligntype) {
        // TODO: 2017/7/3 打印这个字符串变成的二维码
        drawLineEnd();
    }

    @Override
    public void drawOrdinaryDashed() {
        drawLine('-');
    }

    @Override
    public void drawDoubleRowDashed() {
        drawLine('=');
    }

    @Override
    public void drawSpaceLine() {

        drawLineEnd();
    }

    @Override
    public void drawLineEnd() {

    }

    @Override
    public int getOneLineMaxLength() {
        return MAX_TEXT_LENGTH_ENGLISH;
    }

    @Override
    public int getPrintingPaperType() {
        return CLIENT_TYPE_58;
    }

    @Override
    public void cutPaper(boolean isCutPaper) {

    }

    @Override
    public String drawFinish() {
        return null;
    }

    @Override
    public String getCurrPrintTime(String format) {
        String times;
        if (TextUtils.isEmpty(format)) {
            times = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        } else {
            times = new SimpleDateFormat(format).format(new Date());
        }
        return times;
    }

    /**
     * 0位 真实打印的xml数据
     * 1位 包名
     * 2位 类名
     * 3位来自哪里(类似类名)
     * 4位 sn码(不知道干嘛用的)//应该是打印机的sn码 确定唯一打印机标识
     * 5位 支付模式
     * 6位 支付金额
     *
     * @return 打印必须的PrintData对象
     */
    @Override
    public CompatPrintData buildPrintData(String printInfo) {
//        PrintData printData = new PrintData();
//        printData.setmBuf(printInfo);
//        printData.setmPackageName(packageName);
//        printData.setmClassName(value[2]);
//        printData.setmId(notifyId);
//        printData.setmFrom(value[3]);
//        printData.setmSN(value[4]);
//        printData.setmTime(Utils.formatDate("MM-dd HH:mm:ss", System.currentTimeMillis()));
//        printData.setmPayMode(value[5]);
//        printData.setmPayAmount(value[6]);

//        return new CompatPrintData(PrintConstast.PRINT_TIMES_ZERO, printData);
        return null;
    }

}
