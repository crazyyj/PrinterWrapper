package cn.newchar.printutil;

/**
 * Created by newlq on 2017/1/3.
 */

public interface PrintConstast {


    int TEXT_SIZE_MODE_SMALL = -1;
    int TEXT_SIZE_MODE_ORDINARY = 0;
    int TEXT_SIZE_MODE_BIG = 1;

    int ALIGN_TYPE_LEFT = 0;
    int ALIGN_TYPE_CENTER = 1;
    int ALIGN_TYPE_RIGHT = 2;

    String COMMAND_RUNTIME = "command";

    /** 打印完成后,暂时0 以后改1 */
    int COMMAND_PRINT_END = 0;

    /** 开始打印 */
    int COMMAND_ACTION_START_PRINT = 2;

    int PRINT_TIMES_ZERO = 0;
    int PRINT_TIMES_ONCE = 1;
    int PRINT_TIMES_TWICE = 2;
    int PRINT_TIMES_THREE = 3;
    int PRINT_TIMES_MORE = 4;

    String PRINT_BASE_DATA = "baseData";
//    String Extra_Times = "Times";//打印次数 并不是PrintData的时间time
//
//    String Extra_PackageName = "PackageName";
//    String Extra_ClassName = "ClassName";
//    String Extra_PayAmount = "PayAmount";
//    String Extra_PrintBuf = "PrintBuff";
//    String Extra_PayMode = "PayMode";
//    String Extra_ComeFrom = "From";
//    String Extra_SNCode = "sn";
//    String Extra_id = "mId";

}
