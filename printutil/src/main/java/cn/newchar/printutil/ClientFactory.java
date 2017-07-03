package cn.newchar.printutil;

import com.wiwide.printutil.client.PaperType_76_Client;
import com.wiwide.printutil.client.PaperType_80_Client;
import com.wiwide.printutil.client.Pos88vClient;

import cn.newchar.printutil.client.Pos88vClient;

/**
 * @author newlq
 * Created by newlq on 2017/6/19.
 */

final class ClientFactory {

    static IPrinterClient createPrintClient(@IPrinterClient.ClientType int type){
        IPrinterClient client;
        switch(type){
            case IPrinterClient.CLIENT_TYPE_58:
                client = new Pos88vClient();
                break;
            case IPrinterClient.CLIENT_TYPE_76:
                client = new PaperType_76_Client();
                break;
            case IPrinterClient.CLIENT_TYPE_80:
                client = new PaperType_80_Client();
                break;
            default:
                client = new Pos88vClient();
                break;
        }
        return client;
    }



}
