package service;

import connection.ConnInterface;
import connection.ConnSingleton;
import connection.ConnectionException;

/**
 * Created by Markus on 05.10.2014.
 */
public class MsgServiceImpl implements MsgServiceInterface {

    private ConnInterface connection;

    protected MsgServiceImpl() {
        connection = ConnSingleton.getConn();
    }

    @Override
    public String send(String msg) throws ServiceException {
        try {
            return connection.send(msg);
        } catch (ConnectionException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
