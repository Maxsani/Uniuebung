package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import service.MsgServiceInterface;
import service.ServiceException;
import service.ServiceSingleton;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private MsgServiceInterface msgService;
    private Logger logger = Logger.getLogger(this.getClass());

    @FXML
    private Button sendBtn;
    @FXML
    private Label infoLbl;
    @FXML
    private TextField sendText;
    @FXML
    private ListView<String> receiveList;

    @FXML
    private void onKlickSendBtn() {

        //Login Procedure - LAB 0
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("src/main/resources/command");
            String msg = "";
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            msg = lineNumberReader.readLine();
            logger.info(msg);
            msg = msgService.send(msg);
            infoLbl.setText(msg);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (ServiceException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msgService = ServiceSingleton.getMsgService();
    }
}
