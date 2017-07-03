package cn.newchar.printutil;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by newlq on 2017/1/11.
 */

public final class Utils {

    static boolean textIsEmpty(String text) {
        return TextUtils.isEmpty(text) || "null".equalsIgnoreCase(text);
    }

    static String CompatString(String str) {
        return textIsEmpty(str) ? "" : str;
    }

    /**
     * @param time 毫秒值时间
     * @return 以这个毫秒值为标准返回按照规则格式化的字符串对象
     */
    public static String formatDate(String format, long time) {
        String times;
        if (TextUtils.isEmpty(format) || time > 0) {
            times = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        } else {
            times = new SimpleDateFormat(format).format(new Date(time));
        }
        return times;
    }

    /**
     * 结构样式需要
     * 英文占位 为中文的一半
     *
     * @param c 被判断的char字符
     * @return 判断传入的字符c 是否为中文字符
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 判断是否包含中文字符 中文标点等。
     *
     * @param text 被检测的字符串， 不可以传null
     * @return 是否包含
     */
    public static boolean isContainChinese(String text) {
        for (char c : text.toCharArray()) {
            if (isChinese(c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 以英文字符宽度计算Text宽度
     *
     * @param text 被计算的字符串
     * @return 一共有多少
     */
    public static int measureTextWidth(String text) {
        int textWidth = 0;
        if (text != null && !text.isEmpty()) {
            for (char c : text.toCharArray()) {
                textWidth += isChinese(c) ? 2 : 1;
            }
        }
        return textWidth;
    }

    /**
     * 将指定的文本 以textSize大小切割成若干份。
     *
     * @param textSize   每一份的大小(以英文字符宽度标准)
     * @param textSource 原数据(可能中英文混杂)
     * @return 等待被打印的文字, 结果分成几份的字符串
     */
    public static String[] spiltTextOfParts(int textSize, String textSource) {
        if (textSource == null && textSize < 0) {
            return null;
        }
        if (textSize <= 1 || TextUtils.isEmpty(textSource) || measureTextWidth(textSource) <= textSize) {
            return new String[]{textSource};
        }
        List<String> resultArr = new ArrayList<>();
        char[] sourceArray = textSource.toCharArray();
        int tempRecrod = 0;
        int tempSpiltWhere = 0;
        for (int i = 0; i < sourceArray.length; i++) {
            if (isChinese(sourceArray[i])) {
                tempRecrod += 2;
            } else {
                tempRecrod++;
            }
            if (tempRecrod > textSize) {
                resultArr.add(textSource.substring(tempSpiltWhere, i));
                tempRecrod = 0;
                tempSpiltWhere = i;
                if ((sourceArray.length - i) < textSize) {
                    resultArr.add(textSource.substring(i, sourceArray.length));
                    break;
                }
            }
        }
        return resultArr.toArray(new String[resultArr.size()]);
    }

    /**
     * 计算份数
     * (需求) 超长的汉字 分成固定长度的份数 结尾会有一些字符不满足一份的长度 也需要作为一份输出
     * 这个函数结果就是得到这个份数
     * @param total_length  总长度
     * @param partLength    一份的长度
     * @return              匹配出来的份数
     */
    private int measureLength(int total_length, int partLength){
        return (total_length / partLength) + ((total_length % partLength) != 0 ? 1 : 0);
    }

    /**
     * 平均分了这些字符串
     *
     * @param source    字符串
     * @param rowLength 一行的宽度(英文字符宽度标准)
     * @return          一行数据
     */
    public static String averageText(String[] source, int rowLength) {
        int partlength = rowLength / source.length;                     //求份数
        StringBuilder final_one_line_Text = new StringBuilder();                    //总字符串
        for (int i = 0, length = source.length; i < length; i++) {      //循环字符串份数每一份, 得到文本并计算空格
            StringBuilder onePart = new StringBuilder(source[i]);
            int itemTextWidth = measureTextWidth(source[i]);            //计算每个条目文本的宽度
            for (int j = 0; j < partlength - itemTextWidth; j++) {      //当每个条目的文本, 没有份数宽度宽的时候前后加空格
                if (i == 0) {
                    final_one_line_Text.append(" ");
                } else if (i == source.length - 1) {
                    final_one_line_Text.insert(0, " ");
                } else {
                    if (j % 2 == 1) {
                        onePart = onePart.insert(0, " ");
                    } else {
                        onePart.append(" ");
                    }
                }
            }
            final_one_line_Text.append(onePart);
        }
        return final_one_line_Text.toString();
    }

    /**
     * 转换打印的方位 传入指定的靠左 靠右类型 转换成当前jar包客户端使用的枚举类型
     *
     * @param alignType 靠边类型
     * @return 转换后的类型
     */
    public static void convertAlignType(int alignType) {
    }

}
