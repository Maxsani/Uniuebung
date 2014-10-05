package service;

/**
 * Created by Markus on 05.10.2014.
 */
public class ServiceSingleton {

    private static MsgServiceInterface msgServiceInterface;

    private ServiceSingleton() {}

    public static MsgServiceInterface getMsgService() {
        if (msgServiceInterface == null) {
            msgServiceInterface = new MsgServiceImpl();
        }
        return msgServiceInterface;
    }
}
