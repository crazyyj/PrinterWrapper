package cn.newchar.printutil.client;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.newchar.printutil.CompatPrintData;
import cn.newchar.printutil.IPrinterClient;
import cn.newchar.printutil.Utils;


/**
 * Created by newlq on 2017/6/19.
 * 热敏打印机 纸宽76
 */

public class PaperType_76_Client implements IPrinterClient {

    private static final int MAX_TEXT_LENGTH_ENGLISH = 42;

    public PaperType_76_Client() {
        super();

    }

    @Override
    public void drawBigText(String text, int aligntype) {

        drawLineEnd();
    }

    @Override
    public void drawOrdinaryText(String text, int aligntype) {

        drawLineEnd();
    }

    @Override
    public void drawSmallText(String text, int aligntype) {

        drawLineEnd();
    }

    @Override
    public void drawLine(String text) {

        drawLineEnd();
    }

    private String oneLine(String text) {
        int part = getOneLineMaxLength() / Utils.measureTextWidth(text);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < part; i++) {
            builder.append(text);
        }
        return builder.toString();
    }

    @Override
    public void drawLine(char c) {
//        oneLine(c)
        drawLineEnd();
    }

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
    public void drawQrCodeImg(String qrCodeText, int aligntype) {

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
        return IPrinterClient.CLIENT_TYPE_76;
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

    @Override
    public CompatPrintData buildPrintData(String buf) {
//        PrintData printData = new PrintData();
//        printData.setmBuf(buf);
//        printData.setmPackageName(packageName);
//        printData.setmClassName(value[2]);
//        printData.setmId(notifyId);
//        printData.setmFrom(value[3]);
//        printData.setmSN(value[4]);
//        printData.setmTime(Utils.formatDate("MM-dd HH:mm:ss", System.currentTimeMillis()));
//        printData.setmPayMode(value[5]);
//        printData.setmPayAmount(value[6]);

        return null;
//        return new CompatPrintData(PrintConstast.PRINT_TIMES_ZERO, printData);
    }

}
