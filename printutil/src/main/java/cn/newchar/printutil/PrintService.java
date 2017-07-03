package cn.newchar.printutil;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.wiwide.printerproxy_support.PrintData;
import com.wiwide.printerproxy_support.ResultData;

import java.util.LinkedList;

/**
 * Created by newlq on 2016/12/26.
 * <li> 保证打印过程的完整 </li>
 * <li> 打印命令在服务中进行 </li>
 */

public class PrintService extends Service {

    private OnPrintCompleteListener onPrintCompleteListener;
    private LinkedList<CompatPrintData> mPrintQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mPrintQueue = new LinkedList<>();
    }

    //        打印的操作过程, 去构建数据 多次打印的触发
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (intent.getIntExtra(PrintConstast.COMMAND_RUNTIME, 0)) {
            case PrintConstast.COMMAND_PRINT_END:
                ResultData resultData = intent.getParcelableExtra(ResultData.EXTRA_KEY);
                if (onPrintCompleteListener != null && resultData != null) {
                    if (resultData.getmResultCode() == ResultData.RESCODE_PRINT_OK) {
                        onPrintCompleteListener.onPrintSuccess(resultData);
                    } else {
                        onPrintCompleteListener.onPrintError(resultData, resultData.getmResultCode());
                    }
                }

                if (mPrintQueue != null && !mPrintQueue.isEmpty()) {
                    CompatPrintData onPrintEndPrintData = mPrintQueue.getFirst();
                    onPrintEndPrintData.onPrintEnd();
                    if (onPrintEndPrintData.hasNextTimes()) {
                        prepareStartPrint(onPrintEndPrintData.getPrintData());
                    } else {
                        mPrintQueue.removeFirst();
                        if (!mPrintQueue.isEmpty()) {
                            prepareStartPrint(mPrintQueue.getFirst().getPrintData());
                        } else {
                            stopSelf();
                        }
                    }
                } else {
                    stopSelf();
                }
                break;
            case PrintConstast.COMMAND_ACTION_START_PRINT:
                CompatPrintData printData = intent.getParcelableExtra(PrintConstast.PRINT_BASE_DATA);
                mPrintQueue.add(printData);

                if (mPrintQueue.size() == 1) {
                    prepareStartPrint(mPrintQueue.getFirst().getPrintData());
                }
                break;
            default:
                break;
        }
        return START_STICKY;
    }

    private void prepareStartPrint(PrintData printData) {
        Intent i = new Intent();
        i.setClassName("com.wiwide.printerproxy", "com.wiwide.printerproxy.PrinterProxyActivity");
        i.putExtra(PrintData.EXTRA_KEY, printData);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    public void setOnPrintCompleteListener(OnPrintCompleteListener l) {
        onPrintCompleteListener = l;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new PrintServiceBinder();
    }

    public class PrintServiceBinder extends Binder {
        public PrintService getService() {
            return PrintService.this;
        }
    }

}
