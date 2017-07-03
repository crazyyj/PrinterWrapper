package cn.newchar.printutil.client;

import cn.newchar.printutil.CompatPrintData;
import cn.newchar.printutil.IPrinterClient;
import cn.newchar.printutil.Utils;

/**
 * Created by newlq on 2017/6/19.
 */

public class PaperType_80_Client implements IPrinterClient {

    private static final int MAX_TEXT_LENGTH_ENGLISH = 48;

    public PaperType_80_Client() {
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

    @Override
    public void drawLine(char c) {

        drawLineEnd();
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
        return IPrinterClient.CLIENT_TYPE_80;
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
        return Utils.formatDate(format, System.currentTimeMillis());
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

    private String oneLine(String text) {
        int textParts = getOneLineMaxLength() / Utils.measureTextWidth(text);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < textParts; i++) {
            builder.append(text);
        }
        return builder.toString();
    }

}
