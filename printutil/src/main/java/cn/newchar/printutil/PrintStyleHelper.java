package cn.newchar.printutil;

import android.text.TextUtils;

import cn.newchar.printutil.client.Pos88vClient;

/**
 * Created by newlq on 2016/12/23.
 * 代替封装打印样式
 * 打印功能的父类 封装功能打印方法
 */
public abstract class PrintStyleHelper implements IBuildPrintStyle {

    private final IPrinterClient client;

    public PrintStyleHelper(@IPrinterClient.ClientType int clientType) {
        client = ClientFactory.createPrintClient(clientType);
    }

    public PrintStyleHelper() {
        client = new Pos88vClient();
    }

    /**
     * 打印大文字
     *
     * @param text      要被打印的文字
     * @param alignType 靠边
     */
    protected void addBigText(String text, int alignType) {
        client.drawBigText(text, alignType);
    }

    /**
     * 打印大文字, 默认靠左
     *
     * @param text 要被打印的文字
     */
    protected void addBigText(String text) {
        client.drawBigText(text, PrintConstast.ALIGN_TYPE_LEFT);
    }

    /**
     * 打印小文字
     *
     * @param text      要被打印的文字
     * @param alignType 靠边
     */
    protected void addOrdinaryText(String text, int alignType) {
        client.drawOrdinaryText(text, alignType);
    }

    /**
     * 打印小文字, 默认靠左
     *
     * @param text 要被打印的文字
     */
    protected void addOrdinaryText(String text) {
        client.drawOrdinaryText(text, PrintConstast.ALIGN_TYPE_LEFT);
    }


    /**
     * 添加一行虚线
     */
    protected void addOrdinaryDashed() {
        client.drawOrdinaryDashed();
    }

    /**
     * 添加双行虚线（等号）
     */
    protected void addDoubleRowDashed() {
        client.drawDoubleRowDashed();
    }

    /**
     * 画空行
     */
    protected void addSpaceLine() {
        client.drawSpaceLine();
    }


    /**
     * 打印二维码
     */
    protected void addOrderQrCode(String qrCodeText) {
        client.drawQrCodeImg(qrCodeText, PrintConstast.ALIGN_TYPE_CENTER);
    }

    /**
     * 两个数组的份数务必要设置成相同的
     *
     * @param parts    一份占多少个字符, (以英文宽度为标准)
     * @param itemText 每份的文字
     * @see #addInvoluteTextItem(String[], int[])
     */
    @Deprecated
    protected void addInvoluteTextItem(int[] parts, String... itemText) {
        final String kong_1 = " ";
        if (parts == null || itemText == null || itemText.length == 0 || TextUtils.isEmpty(itemText[0])) {
            addSpaceLine();
            return;
        }
        StringBuilder wait_for_printed_text = new StringBuilder();
        if (parts.length <= 1) {
            for (String anItemText : itemText) {
                wait_for_printed_text.append(anItemText).append(kong_1);
            }
        } else {
            for (int i = 0; i < parts.length; i++) {
                StringBuilder onePart = new StringBuilder(itemText[i]);
                int length = parts[i] - Utils.measureTextWidth(itemText[i]);
                for (int j = 0; j < length; j++) {
                    if (i == 0) {
                        onePart.append(kong_1);
                    } else if (i < parts.length - 1) {
                        if (j % 2 == 1) {
                            onePart = onePart.insert(0, kong_1);
                        } else {
                            onePart.append(kong_1);
                        }
                    } else {
                        onePart.insert(0, kong_1);
                    }
                }
                wait_for_printed_text.append(onePart);
            }
        }
        addOrdinaryText(wait_for_printed_text.toString());
    }

    /**
     * 数字份数可以不传, 必须有字符串
     *
     * @param itemText 每份的文字
     * @param parts    一份占多少个字符, (以英文宽度为标准)
     */
    protected void addInvoluteTextItem(String[] itemText, int... parts) {
        final String TEXT_SPACE_ONE = " ";
        StringBuilder wait_for_printed_text = new StringBuilder();
        if (parts == null || parts.length < 1) {
            Utils.averageText(itemText, getOneLineMaxLength());
        } else {
            for (int i = 0; i < parts.length; i++) {
                StringBuilder onePart = new StringBuilder(itemText[i]);
                int length = parts[i] - Utils.measureTextWidth(itemText[i]);
                for (int j = 0; j < length; j++) {
                    if (i == 0) {
                        onePart.append(TEXT_SPACE_ONE);
                    } else if (i < parts.length - 1) {
                        if (j % 2 == 1) {
                            onePart = onePart.insert(0, TEXT_SPACE_ONE);
                        } else {
                            onePart.append(TEXT_SPACE_ONE);
                        }
                    } else {
                        onePart.insert(0, TEXT_SPACE_ONE);
                    }
                }
                wait_for_printed_text.append(onePart);
            }
        }
        addOrdinaryText(wait_for_printed_text.toString());
    }


    /**
     * 打印左右两边的普通号文字
     * 如果给出的文字 之和超过宽度, 则先打印左测文字， 分第二行打印右侧文字
     * 如果 其中一份为null 或者 “” 则只打印有的那一部分。
     * 如果都是null 或者 “” 则打印一行空行
     *
     * @param leftText  打印的左边的文字
     * @param rightText 打印的右边的文字
     */
    protected void addBothSidesOfTheText(String leftText, String rightText) {
        final String space = " ";
        if (!TextUtils.isEmpty(leftText) && !TextUtils.isEmpty(rightText)) {
            int space_partsNum = getOneLineMaxLength() - Utils.measureTextWidth(leftText + rightText);
            if (space_partsNum > 0) {
                StringBuilder builder = new StringBuilder(leftText);
                for (int i = 0; i < space_partsNum; i++) {
                    builder.append(space) ;
                }
                addOrdinaryText(builder.append(rightText).toString());
            } else {
                addOrdinaryText(leftText, PrintConstast.ALIGN_TYPE_LEFT);
                addOrdinaryText(rightText, PrintConstast.ALIGN_TYPE_RIGHT);
            }
        } else if (TextUtils.isEmpty(leftText) && !TextUtils.isEmpty(rightText)) {
            addOrdinaryText(rightText, PrintConstast.ALIGN_TYPE_RIGHT);
        } else if (!TextUtils.isEmpty(leftText) && TextUtils.isEmpty(rightText)) {
            addOrdinaryText(leftText, PrintConstast.ALIGN_TYPE_LEFT);
        } else {
            addSpaceLine();
        }
    }

    protected String getCurrTime(String format) {
        return client.getCurrPrintTime(format);
    }

    protected void addLine(String text) {
        client.drawLine(text);
    }

    @IPrinterClient.ClientType
    protected int getPrinterType() {
        return client.getPrintingPaperType();
    }

    protected int getOneLineMaxLength() {
        return client.getOneLineMaxLength();
    }

    private String build() {
        return client.drawFinish();
    }

    protected abstract void buildBillStyle();

    @Override
    public final CompatPrintData buildPrintData() {
        buildBillStyle();
        String buff = build();
        return client.buildPrintData(buff);
    }

}
