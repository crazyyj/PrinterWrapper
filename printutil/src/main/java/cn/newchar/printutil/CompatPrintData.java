package cn.newchar.printutil;

import android.os.Parcel;
import android.os.Parcelable;

import com.wiwide.printerproxy_support.PrintData;

/**
 * Created by newlq on 2017/1/16.
 */

public class CompatPrintData implements Parcelable {

    private int mTimes;
    private PrintData mPrintData;

    public CompatPrintData(int times, PrintData printData) {
        this.mTimes = times;
        this.mPrintData = printData;
    }

    void setTimes(int times) {
        this.mTimes = times;
    }

    public int getTimes() {
        return mTimes;
    }

    PrintData getPrintData() {
        return mPrintData;
    }

    /**
     * @return true
     */
    boolean hasNextTimes() {
        return mTimes != PrintConstast.PRINT_TIMES_ZERO;
    }

    void onPrintEnd() {
        if (--mTimes <= 0) {
            mTimes = PrintConstast.PRINT_TIMES_ZERO;
            mPrintData = null;
        }
    }


    protected CompatPrintData(Parcel in) {
        mTimes = in.readInt();
        mPrintData = in.readParcelable(PrintData.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTimes);
        dest.writeParcelable(mPrintData, flags);
    }

    public static final Creator<CompatPrintData> CREATOR = new Creator<CompatPrintData>() {
        @Override
        public CompatPrintData createFromParcel(Parcel in) {
            return new CompatPrintData(in);
        }

        @Override
        public CompatPrintData[] newArray(int size) {
            return new CompatPrintData[size];
        }
    };
}
