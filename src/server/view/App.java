package server.view;

import server.sokoserver.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main( String[] args )
    {
        ThreadPoolServer server = new ThreadPoolServer(5555, new SokoClientHandler());
        try {
        	new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						server.runServer();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			
        	}).start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
        
        Scene scene = new Scene(root, 300, 275);
    
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.show();
	}
}
