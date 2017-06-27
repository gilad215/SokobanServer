package model.data;



import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable{

    private ArrayList<Point> goals;
    private ArrayList<ArrayList<Character>> board;
    private String name;
    private static final long serialVersionUID = 1113799434508676095L;



    public Level(ArrayList<ArrayList<Character>> board) {

        this.board = board;
        setGoals();
        System.out.println("Goals has been loaded! Number of Goals:"+getGoals().size());
    }

    public Level(Level l) {
        goals = new ArrayList<>();

        for (Point p : l.getGoals()) {
            goals.add(p);
        }

        board = new ArrayList<ArrayList<Character>>(l.getBoard().size());
        ArrayList<Character> line=null;

        for (ArrayList<Character> arr:l.getBoard()) {
            line=new ArrayList<Character>(arr.size());
            for (Character ch:arr) {
                line.add(ch);
            }
            board.add(line);
        }

    }


    public ArrayList<ArrayList<Character>> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<ArrayList<Character>> board) {
        this.board = board;
    }

    public boolean isEmpty(){
        return board.isEmpty();
    }

    public void setGoals()
    {
        if(getBoard().isEmpty()) return;
        goals=new ArrayList<Point>();
        for (int i = 0; i < getBoard().size(); i++) {
            for (int j = 0; j < getBoard().get(i).size(); j++) {
                if (getBoard().get(i).get(j).equals('o')){
                    //System.out.println("Found goal! its on: ("+j+","+i+")");
                    goals.add(new Point(j, i));
                }
            }
        }
    }
    public ArrayList<Point> getGoals(){return goals;}
    public Point getInitialState()
    {
        for (int i = 0; i < getBoard().size(); i++) {
            for (int j = 0; j < getBoard().get(i).size(); j++) {
                if (getBoard().get(i).get(j).equals('A')) return new Point(j, i);
            }
        }
        return null;
    }

    public void setGoals(ArrayList<Point> goals) {
        this.goals = goals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
