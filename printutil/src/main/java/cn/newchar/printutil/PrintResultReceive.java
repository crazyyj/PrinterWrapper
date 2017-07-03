package cn.newchar.printutil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wiwide.printerproxy_support.ResultData;

/**
 * 打印结果接收
 * Created by yueguang on 16-4-26.
 * <receiver android:name=".print.PrintResultReceive">
 * <intent-filter>
 * <action android:name="com.wiwide.printerproxy" />
 * </intent-filter>
 * </receiver>
 */
public class PrintResultReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            ResultData resultData = intent.getParcelableExtra(ResultData.EXTRA_KEY);
            if (resultData != null && resultData.getmPackageName().equalsIgnoreCase(context.getPackageName())) {
                Intent service = new Intent(context, PrintService.class);//打印结果分发到这个服务中, 从这个服务再分发到bindService的页面中
                service.putExtra(PrintConstast.COMMAND_RUNTIME, PrintConstast.COMMAND_PRINT_END);
                service.putExtra(ResultData.EXTRA_KEY, resultData);
                context.startService(service);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
