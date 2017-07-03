package cn.newchar.printutil;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by newlq on 2017/7/3.
 * 文字大小
 */
@IntDef({TextSize.TEXT_WH_1x2, TextSize.TEXT_WH_1X1, TextSize.TEXT_WH_2x1, TextSize.TEXT_WH_2x2})
@Retention(RetentionPolicy.CLASS)
public @interface TextSizeMode {}
