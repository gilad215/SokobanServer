package db;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class TableUtil {
    private TableView<User> usertable;
    private HashMap<String,Integer> levelIDs;
    private int lvlid=0;

    private TableColumn<User, Integer> gameidColumn;
    private TableColumn<User, Integer> LevelidColumn;
    private TableColumn<User, Integer> FirstNameColumn;
    private TableColumn<User, Integer> LastNameColumn;
    private TableColumn<User, Integer> FinishTimeColumn;
    private TableColumn<User, Integer> StepsColumn;


    public TableUtil()
    {
        levelIDs=new HashMap<>();
        levelIDs.put("LEVEL1",1);
        levelIDs.put("LEVEL2",2);
        levelIDs.put("LEVEL3",3);
        levelIDs.put("LEVEL4",4);

        usertable = new TableView<>();

        gameidColumn = new TableColumn<>("Game ID");
        gameidColumn.setMinWidth(100);
        gameidColumn.setCellValueFactory(new PropertyValueFactory<>("GameID"));

        LevelidColumn = new TableColumn<>("Level ID");
        LevelidColumn.setMinWidth(100);
        LevelidColumn.setCellValueFactory(new PropertyValueFactory<>("levelID"));

        FirstNameColumn = new TableColumn<>("First Name");
        FirstNameColumn.setMinWidth(150);
        FirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("First_name"));

        LastNameColumn = new TableColumn<>("Last Name");
        LastNameColumn.setMinWidth(150);
        LastNameColumn.setCellValueFactory(new PropertyValueFactory<>("last_name"));

        FinishTimeColumn = new TableColumn<>("Finish Time");
        FinishTimeColumn.setMinWidth(100);
        FinishTimeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));

        StepsColumn = new TableColumn<>("Steps");
        StepsColumn.setMinWidth(100);
        StepsColumn.setCellValueFactory(new PropertyValueFactory<>("steps"));


    }

    public void showLeaderboard() throws FileNotFoundException {
        usertable.getColumns().clear();
        usertable.getItems().clear();
        usertable.setItems(getUsers());
        usertable.getColumns().addAll(gameidColumn, LevelidColumn, FirstNameColumn, LastNameColumn, FinishTimeColumn, StepsColumn);
        usertable.getSortOrder().add(LevelidColumn);
        usertable.getSortOrder().add(FinishTimeColumn);
        usertable.getSortOrder().add(StepsColumn);

        Label search=new Label("Search");
        TextField searchfield=new TextField();
        searchfield.setText("Press ENTER to Search");
        HBox hb=new HBox();
        hb.getChildren().addAll(search,searchfield);
        hb.setSpacing(10);

        Stage table = new Stage();
        table.setTitle("Leaderboards");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hb,usertable);

        Scene scene = new Scene(vBox);
        table.setScene(scene);
        //table.getIcons().add(new Image(new FileInputStream("./Extras/images/leaderboard.png")));
        table.show();
        searchfield.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode().equals(KeyCode.ENTER))
                {
                    String[] split=searchfield.getText().split(" ");
                    if(split.length>1)
                    {
                        try {
                            showPlayerTable(new User(10,split[0],split[1],1,1));
                            table.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(split[0].toUpperCase());
                    if(levelIDs.containsKey(split[0].toUpperCase())) try {
                        showLevelTable(levelIDs.get(split[0].toUpperCase()));
                        table.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        usertable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.isPrimaryButtonDown() && event.getClickCount()==2)
                    try {
                        showPlayerTable(usertable.getSelectionModel().getSelectedItem());
                        table.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                //usertable.getSelectionModel().getSelectedItem().printUser();
            }
        });

    }
    public void showPlayerTable(User selectedUser) throws FileNotFoundException
    {
        usertable.getColumns().clear();
        usertable.getItems().clear();
        usertable.setItems(getUser(selectedUser));
        usertable.getColumns().addAll(gameidColumn, LevelidColumn, FirstNameColumn, LastNameColumn, FinishTimeColumn, StepsColumn);
        usertable.getSortOrder().add(LevelidColumn);
        usertable.getSortOrder().add(FinishTimeColumn);
        usertable.getSortOrder().add(StepsColumn);


        Stage table = new Stage();
        table.setTitle("Player Stats");
//        table.getIcons().add(new Image(new FileInputStream("./Extras/images/leaderboard.png")));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(usertable);

        Scene scene = new Scene(vBox);

        table.setScene(scene);
        table.show();

    }
    public void showLevelTable(int lvlid) throws FileNotFoundException
    {
        System.out.println("INSIDE SHOWLEVELTABLE + "+lvlid);
        usertable.getColumns().clear();
        usertable.getItems().clear();
        usertable.setItems(getLevel(lvlid));
        usertable.getColumns().addAll(gameidColumn, LevelidColumn, FirstNameColumn, LastNameColumn, FinishTimeColumn, StepsColumn);
        usertable.getSortOrder().add(LevelidColumn);
        usertable.getSortOrder().add(FinishTimeColumn);
        usertable.getSortOrder().add(StepsColumn);


        Stage table = new Stage();
        table.setTitle("Player Stats");
        table.getIcons().add(new Image(new FileInputStream("./Extras/images/leaderboard.png")));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(usertable);

        Scene scene = new Scene(vBox);
        table.setScene(scene);
        table.show();
    }

    public ObservableList<User> getUsers()
    {
        ObservableList<User> users= FXCollections.observableArrayList();
        Query<User> query= HibernateUtil.getSessionFactory().openSession().createQuery("from Games");
        //query.setMaxResults(20);
        List<User> list=query.list();
        for (User u:list) {
            if(lvlid==0) users.add(u);
            else {
                if (u.getLevelID() == lvlid) users.add(u);
            }

        }
        for(User user:users) user.printUser();
        HibernateUtil.getSessionFactory().getCurrentSession().close();
        return users;
    }
    public ObservableList<User> getUser(User selectedUser)
    {
        ObservableList<User> users= FXCollections.observableArrayList();
        Query<User> query= HibernateUtil.getSessionFactory().openSession().createQuery("from Games");
        List<User> list=query.list();
        for (User u:list) {
            if(selectedUser.getFirst_name().compareTo(u.getFirst_name())==0 && selectedUser.getLast_name().compareTo(u.getLast_name())==0)
                users.add(u);
        }
        for(User user:users) user.printUser();
        HibernateUtil.getSessionFactory().getCurrentSession().close();
        return users;
    }
    public ObservableList<User> getLevel(int lvlid)
    {
        ObservableList<User> users= FXCollections.observableArrayList();
        Query<User> query= HibernateUtil.getSessionFactory().openSession().createQuery("from Games");
        List<User> list=query.list();
        for (User u:list) {
            if(lvlid==u.getLevelID())
                users.add(u);
        }
        for(User user:users) user.printUser();
        HibernateUtil.getSessionFactory().getCurrentSession().close();
        return users;
    }
    public void setLvlid(int l) {this.lvlid=l;}
    public int getLvlid(){return this.lvlid;}
    public HashMap<String,Integer> getLevelIDs(){return levelIDs;}

    public void addUser(User u)
    {

        Session session= HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Session opened");

        try {
            session.beginTransaction();
            session.save(u);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }


    }
}
