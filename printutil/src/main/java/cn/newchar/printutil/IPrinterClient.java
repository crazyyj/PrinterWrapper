package cn.newchar.printutil;

import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by newlq on 2017/1/20.
 */

public interface IPrinterClient {

    int CLIENT_TYPE_58 = 0;
    int CLIENT_TYPE_76 = 1;
    int CLIENT_TYPE_80 = 2;

    /**
     * 定义纸宽类型, 默认58类型(窄纸不会打得换行)
     */
    @IntDef(flag = true, value = {CLIENT_TYPE_58, CLIENT_TYPE_76, CLIENT_TYPE_80})
    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.PARAMETER, ElementType.METHOD})
    @interface ClientType {}

    /**
     * 画一行大号字(2倍高2倍宽)
     *
     * @param text 要打印的文本
     * @param aligntype 位置类型
     */
    void drawBigText(String text, int aligntype);

    /**
     * 画一行中号字(默认大小, 1倍高1倍宽)
     *
     * @param text 要打印的文本
     * @param aligntype 位置类型
     */
    void drawOrdinaryText(String text, int aligntype);

    /**
     * 画一行小文字
     *
     * @param text  要打印的文本
     * @param aligntype 位置类型
     */
    void drawSmallText(String text, int aligntype);

    /**
     * 画一行某个字符
     *
     * @param text 要打印的文本
     */
    void drawLine(String text);

    void drawLine(char c);

    /**
     * 画一个二维码
     *
     * @param qrCodeText 二维码中包含的文字
     * @param aligntype 位置类型
     */
    void drawQrCodeImg(String qrCodeText, int aligntype);

    /**
     * 画一行虚线
     */
    void drawOrdinaryDashed();

    /**
     * 画一行双虚线, 其实是等号
     */
    void drawDoubleRowDashed();

    /**
     * 画一个空行
     */
    void drawSpaceLine();

    /**
     * 结束一行
     */
    void drawLineEnd();

    /**
     * 获取一行最多可以有多少个字符(普通字号中文)
     *
     * @return 字符个数
     */
    int getOneLineMaxLength();

    /**
     * 获取打印纸类型
     *
     * @return 返回常量类型
     */
    @ClientType
    int getPrintingPaperType();

    /**
     *  切纸
     */
    void cutPaper(boolean isCutPaper);

    /**
     * 结束方法 获取全部内容
     */
    String drawFinish();

    /**
     * 获取当前打印的时间
     *
     * @param format 需要格式化的格式, 可以传null(默认格式 2017-01-01 12:12:12)
     * @return 格式化好的时间
     */
    String getCurrPrintTime(@Nullable String format);

    /**
     * 打印的实体类 包含打印次数的封装类
     *
     * @param buf 要被打印的数据
     * @return 打印的包装类
     */
    CompatPrintData buildPrintData(String buf);
}
