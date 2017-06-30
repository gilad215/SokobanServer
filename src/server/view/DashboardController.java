package server.view;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.model.AdminModel;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import server.model.AdminModel;

import javax.xml.soap.Text;

public class DashboardController implements Initializable {

    @FXML
    private Button button;
    
    @FXML
    private Label label;

    @FXML
    private ListView myListView;
    private AdminModel model;

    protected ListProperty<String> listProperty = new SimpleListProperty<>();

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
    	updateList();
    }

    @FXML
    private void disconnectButtonAction(ActionEvent event)
    {
        String client;
        if(!myListView.getItems().isEmpty()||myListView.getSelectionModel().isEmpty()) {
            client = (String) myListView.getSelectionModel().getSelectedItem();
            model.disconnectClient(client);
        }
        else
        {
            System.out.println("No Client Selected;");
        }
    }

    @FXML
    private void killServer()
    {
        model.setStopped(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    	model = AdminModel.getInstance();      
        myListView.itemsProperty().bind(listProperty);
        try {
            listProperty.set(FXCollections.observableArrayList(model.getClients()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    private void updateList() throws IOException {
        listProperty.set(FXCollections.observableArrayList(model.getClients()));
    }

    private void disconnectClient(Socket aClient) throws IOException {
        aClient.getOutputStream().close();
        aClient.getInputStream().close();
        aClient.close();
    }

}