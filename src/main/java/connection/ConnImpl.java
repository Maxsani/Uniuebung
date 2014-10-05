package connection;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by Markus on 05.10.2014.
 */
public class ConnImpl implements ConnInterface {

    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public String send(String msg) throws ConnectionException {
        FileReader fileReader = null;
        //DatagramSocket socket = null;
        Socket socket = null;
        String output = null;
        try {
            String serverString = "";
            String portString = "";
            fileReader = new FileReader("src/main/resources/server");
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            serverString = lineNumberReader.readLine();
            InetAddress serverAddress = InetAddress.getByName(serverString);
            fileReader.close();
            fileReader = new FileReader("src/main/resources/port");
            lineNumberReader = new LineNumberReader(fileReader);
            portString = lineNumberReader.readLine();
            int portInt = Integer.parseInt(portString);
            fileReader.close();

            logger.info(serverString);
            logger.info(portString);


            socket = new Socket(serverAddress, portInt);

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            logger.info("sending Msg");
            out.println(msg);
            out.flush();
            logger.info("Msg sent");
            logger.info("receiving Msg");
            output = in.readLine();
            logger.info("Msg received");
            out.close();
            in.close();
            /*
            socket = new DatagramSocket();
            DatagramPacket packet = new DatagramPacket(msg.getBytes(), msg.getBytes().length, serverAddress, portInt);
            logger.info("sending Datagram");
            socket.send(packet);
            logger.info("Datagram sent");

            byte[] buffer = new byte[256];
            packet = new DatagramPacket(buffer, buffer.length);
            logger.info("receiving Datagram");
            socket.receive(packet);
            logger.info("Packet received");
            output = new String(packet.getData());
            logger.info("Packet decrypted: " + output);
            */
        } catch (FileNotFoundException e) {
            throw new ConnectionException(e.getMessage());
        } catch (IOException e) {
            throw new ConnectionException(e.getMessage());
        }
        finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                throw new ConnectionException(e.getMessage());
            }
        }
        return output;
    }
}
