package connection;

/**
 * Created by Markus on 05.10.2014.
 */
public class ConnSingleton {

    private static ConnInterface connInterface;

    private ConnSingleton() {}

    public static ConnInterface getConn() {
        if (connInterface== null) {
            connInterface = new ConnImpl();
        }
        return connInterface;
    }
}
