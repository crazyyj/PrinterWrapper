package cn.newchar.printutil;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import com.wiwide.printerproxy_support.PrintData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by newlq on 2017/1/3.
 * 打印机操作类, 打印操作的入口类
 */

public class Printer {

    //    private static String mPackageName;
    private static int mId;
    private static int mTimes;
    private static String mSNCode;
    private static String mPayMode;
    private static String mComeFrom;
    private static String mPayAmount;
    private static String mClassName;

    private Context mContext;
    private IBuildPrintStyle mStyle;
    private ServiceConnection mServiceConnection;

    private static final ExecutorService mThreadPools = Executors.newSingleThreadExecutor();


    public Printer() {
    }

    public Printer(Context mContext, IBuildPrintStyle mStyle, ServiceConnection mServiceConnection) {
        if (mContext == null || mStyle == null)
            throw new NullPointerException("缺少必要属性参数");
        this.mContext = mContext;
        this.mStyle = mStyle;
        this.mServiceConnection = mServiceConnection;
    }

    /**
     * @param context 上下文
     * @return 打印机抽象类对象
     */
    public Printer setContext(Context context){
        mContext = context.getApplicationContext();
        return this;
    }

    public Printer setServiceConnection(ServiceConnection serviceConnection) {
        mServiceConnection = serviceConnection;
        return this;
    }

    public Printer setId(int id) {
        mId = id;
        return this;
    }

    public Printer setComeFrom(String comeFrom) {
        mComeFrom = comeFrom;
        return this;
    }

    public Printer setSNCode(String SNCode) {
        mSNCode = SNCode;
        return this;
    }

    public Printer setPayMode(String payMode) {
        mPayMode = payMode;
        return this;
    }

    public Printer setPayAmount(String payAmount) {
        mPayAmount = payAmount;
        return this;
    }

    public Printer setStyle(IBuildPrintStyle style) {
        this.mStyle = style;
        return this;
    }

    public Printer setTimes(int times) {
        mTimes = times;
        return this;
    }

    public Printer setClassName(String className) {
        mClassName = className;
        return this;
    }

    /**
     * 如果调用此方法 可能会需要传入打印次数 默认打印2次
     * <li>需要传入类名 后期维护类内部回调使用</li>
     */
    public final void start(){
        if (mContext != null && mStyle != null && mTimes > PrintConstast.PRINT_TIMES_ZERO) {
            mThreadPools.submit(new PrintTask(mContext, mStyle, mTimes, mServiceConnection));
        }else {
            throw new NullPointerException("缺少必要的属性");
        }
    }

    /**
     * 打印机入口静态方法
     * @param context 上下文
     * @param style                 要打印的样式
     * @param times                 打印的次数
     */
    public static void start(Context context, IBuildPrintStyle style, int times){
        if (context != null && style != null && times > PrintConstast.PRINT_TIMES_ZERO) {
            mThreadPools.submit(new PrintTask(context.getApplicationContext(), style, times, null));
        }else {
            throw new NullPointerException("打印缺少必要的属性");
        }
    }

    public void unbindService(){
        if (mContext != null && mServiceConnection != null) {
            mContext.unbindService(mServiceConnection);
        }
    }

    public void closeSercice(){
        if (mContext != null) {
            mContext.stopService(new Intent(mContext, PrintService.class));
        }
    }


    private static class PrintTask implements Runnable{

        private int mTimes;
        private IBuildPrintStyle mStyle;
        private Context mContext;
        private ServiceConnection mServiceConnection;

        PrintTask(Context context, IBuildPrintStyle style, int times, ServiceConnection serviceConnection) {
            mTimes = times;
            mStyle = style;
            mServiceConnection = serviceConnection;
            mContext = context.getApplicationContext();
        }

        @Override
        public void run() {
            if (mContext != null) {
                CompatPrintData printData = compatPrintData(mContext.getPackageName(), mStyle.buildPrintData());
                printData.setTimes(mTimes);

                Intent intent = new Intent(mContext, PrintService.class);
                intent.putExtra(PrintConstast.PRINT_BASE_DATA, printData);
                intent.putExtra(PrintConstast.COMMAND_RUNTIME, PrintConstast.COMMAND_ACTION_START_PRINT);
                mContext.startService(intent);

                if (mServiceConnection != null)
                    mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
            }
        }

        private CompatPrintData compatPrintData(String packageName, CompatPrintData compatPrintData) {
            PrintData printData = compatPrintData.getPrintData();
            printData.setmId(mId);
            printData.setmPackageName(Utils.CompatString(packageName));
            printData.setmSN(Utils.CompatString(mSNCode));
            printData.setmFrom(Utils.CompatString(mComeFrom));
            printData.setmPayMode(Utils.CompatString(mPayMode));
            printData.setmClassName(Utils.CompatString(mClassName));
            printData.setmPayAmount(Utils.CompatString(mPayAmount));
            printData.setmTime(Utils.formatDate("MM-dd HH:mm:ss", System.currentTimeMillis()));
            return compatPrintData;
        }
    }
}
