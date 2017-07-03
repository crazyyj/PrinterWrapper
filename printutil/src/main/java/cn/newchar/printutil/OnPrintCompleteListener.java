package cn.newchar.printutil;

import com.wiwide.printerproxy_support.ResultData;

/**
 * Created by newlq on 2017/1/12.
 */

public interface OnPrintCompleteListener {

    void onPrintSuccess(ResultData resultData);

    void onPrintError(ResultData resultData, int errorCode);
}
