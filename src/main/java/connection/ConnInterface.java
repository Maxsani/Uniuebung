package connection;

/**
 * Created by Markus on 05.10.2014.
 */
public interface ConnInterface {

    public String send(String msg) throws ConnectionException;
}
