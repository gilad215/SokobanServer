package db;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="Games")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int GameID;
    @Column(name="LevelID")
    private int LevelID;
    @Column(name="FirstName")
    private String first_name;
    @Column(name = "LastName")
    private String last_name;
    @Column(name = "FinishTime")
    private int time;
    @Column(name="Steps")
    private int steps;

    public User()
    {}

    public User(int lvlid,String first_name,String last_name,int steps,int time)
    {
        setFirst_name(first_name);
        setLevelID(lvlid);
        setLast_name(last_name);
        setSteps(steps);
        setTime(time);
    }

    public int getGameID() {return GameID;}

    public void setgameID(int gameID) {
        this.GameID = gameID;
    }

    public int getLevelID() {
        return LevelID;
    }

    public void setLevelID(int levelID) {
        LevelID = levelID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void printUser()
    {
        System.out.println("gameID: "+getGameID() +" LevelID : "+getLevelID()+" NAME:"+getFirst_name()+" "+getLast_name()+" FinishTime:"+getTime()+" Steps:"+getSteps());
    }




}
