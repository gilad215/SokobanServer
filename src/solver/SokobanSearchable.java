package solver;

import searchLib.SearchAction;
import searchLib.Searchable;
import searchLib.State;

import java.util.HashMap;
import java.util.Objects;
import model.data.*;

public class SokobanSearchable<T> implements Searchable<T> {

    private T t;
    private Level level;


    private String type;
    private State<Point> initial;
    private State<Point> goal;
    private MySokobanDisplay display;

    HashMap<SearchAction,State<T>> moves=new HashMap<>();


    public SokobanSearchable(Level l, String Type, Point initial, Point goal){this.level=l;this.goal=new State<>(goal);this.initial=new State<>(initial);setType(Type);display=new MySokobanDisplay(level);}

    @Override
    public State getInitialState() {
        initial.setCost(0);
        return initial;
    }

    @Override
    public State getGoalState() {
        return goal;
    }

    @Override
    public HashMap<SearchAction, State<T>> getAllPossibleMoves(State s) {
        moves.clear();
        Point point=(Point)s.getState();
        System.out.println("looking for states around: "+point.toString()+"Which is: "+level.getBoard().get(point.getY()).get(point.getX()).toString());

        if(Objects.equals(getType(), "box")) {
            switch (level.getBoard().get(point.getY()).get(point.getX() - 1)) //Checks Left
            {
                case (' '): {
                    if (clearAt(point.getX() + 1, point.getY()) || goalAt(point.getX() + 1, point.getY()) || sokoAt(point.getX() + 1, point.getY()) || equalsToInitial(new Point(point.getX() + 1, point.getY()))) {
                        State<Point> possibleState = new State<>(new Point(point.getX() - 1, point.getY()));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("left"), (State<T>) possibleState);
                        break;
                    }
                    break;
                }
                case ('o'): {
                    if (clearAt(point.getX() + 1, point.getY()) || goalAt(point.getX() + 1, point.getY()) || sokoAt(point.getX() + 1, point.getY()) || equalsToInitial(new Point(point.getX() + 1, point.getY()))) {
                        State<Point> possibleState = new State<>(new Point(point.getX() - 1, point.getY()));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("left"), (State<T>) possibleState);
                        break;
                    }
                    break;
                }
            }
            switch (level.getBoard().get(point.getY()).get(point.getX() +1)) //Checks Right
            {
                case (' '): {
                    if (clearAt(point.getX() - 1, point.getY()) || goalAt(point.getX() - 1, point.getY()) || sokoAt(point.getX() - 1, point.getY()) || equalsToInitial(new Point(point.getX() - 1, point.getY()))) {
                        State<Point> possibleState = new State<>(new Point(point.getX() + 1, point.getY()));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("right"), (State<T>) possibleState);
                        break;
                    }
                    break;
                }
                case ('o'): {
                    if (clearAt(point.getX() - 1, point.getY()) || goalAt(point.getX() - 1, point.getY()) || sokoAt(point.getX() - 1, point.getY()) || equalsToInitial(new Point(point.getX() - 1, point.getY()))) {
                        State<Point> possibleState = new State<>(new Point(point.getX() + 1, point.getY()));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("right"), (State<T>) possibleState);
                        break;
                    }
                    break;
                }
            }
            switch (level.getBoard().get(point.getY()-1).get(point.getX())) //Checks Up
            {
                case (' '): {
                    if (clearAt(point.getX(), point.getY()+1) || goalAt(point.getX(), point.getY()+1) || sokoAt(point.getX(), point.getY()+1) || equalsToInitial(new Point(point.getX(), point.getY()+1))) {
                        State<Point> possibleState = new State<>(new Point(point.getX(), point.getY()-1));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("up"), (State<T>) possibleState);
                        break;
                    }
                    break;
                }
                case ('o'): {
                    if (clearAt(point.getX(), point.getY()+1) || goalAt(point.getX(), point.getY()+1) || sokoAt(point.getX(), point.getY()+1) || equalsToInitial(new Point(point.getX(), point.getY()+1))) {
                        State<Point> possibleState = new State<>(new Point(point.getX(), point.getY()-1));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("up"), (State<T>) possibleState);
                        break;
                    }
                    break;
                }
            }
            switch (level.getBoard().get(point.getY()+1).get(point.getX())) //Checks Down
            {
                case (' '): {
                    if (clearAt(point.getX(), point.getY()-1) || goalAt(point.getX(), point.getY()-1) || sokoAt(point.getX(), point.getY()-1) || equalsToInitial(new Point(point.getX(), point.getY()-1))) {
                        State<Point> possibleState = new State<>(new Point(point.getX(), point.getY()+1));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("down"), (State<T>) possibleState);
                        break;
                    }
                    break;
                }
                case ('o'): {
                    if (clearAt(point.getX(), point.getY()-1) || goalAt(point.getX(), point.getY()-1) || sokoAt(point.getX(), point.getY()-1) || equalsToInitial(new Point(point.getX(), point.getY()-1))) {
                        State<Point> possibleState = new State<>(new Point(point.getX(), point.getY()+1));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("down"), (State<T>) possibleState);
                        break;
                    }
                    break;
                }
            }

        }
        else //model
        {
            //display.display();
            switch (level.getBoard().get(point.getY()).get(point.getX() - 1)) //Checks Left of State
            {
                case (' '): {
                    State<Point> possibleState = new State<>(new Point(point.getX() - 1, point.getY()));
                    possibleState.setCost(s.getCost()+1);
                    moves.put(new SearchAction("left"), (State<T>) possibleState);
                    break;
                }
                case ('@'): {
                    if (!(level.getBoard().get(point.getY()).get(point.getX() - 2).equals('@')) && !(level.getBoard().get(point.getY()).get(point.getX() - 2).equals('#'))) {
                        State<Point> possibleState = new State<>(new Point(point.getX() - 1, point.getY()));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("left"), (State<T>) possibleState);
                    }
                    break;
                }
                case ('o'): {
                    State<Point> possibleState = new State<>(new Point(point.getX() - 1, point.getY()));
                    possibleState.setCost(s.getCost()+1);
                    moves.put(new SearchAction("left"), (State<T>) possibleState);
                    break;

                }
            }

            switch (level.getBoard().get(point.getY()).get(point.getX() + 1)) //Checks Right of State
            {
                case (' '): {
                    State<Point> possibleState = new State<>(new Point(point.getX() + 1, point.getY()));
                    possibleState.setCost(s.getCost()+1);
                    moves.put(new SearchAction("right"), (State<T>) possibleState);
                    break;
                }
                case ('@'): {
                    if (!(level.getBoard().get(point.getY()).get(point.getX() + 2).equals('@')) && !(level.getBoard().get(point.getY()).get(point.getX() + 2).equals('#'))) {
                        System.out.println(level.getBoard().get(point.getY()).get(point.getX() + 2));
                        State<Point> possibleState = new State<>(new Point(point.getX() + 1, point.getY()));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("right"), (State<T>) possibleState);
                    }
                    break;

                }
                case ('o'): {
                    State<Point> possibleState = new State<>(new Point(point.getX() + 1, point.getY()));
                    possibleState.setCost(s.getCost()+1);
                    moves.put(new SearchAction("right"), (State<T>) possibleState);
                    break;

                }
            }
            switch (level.getBoard().get(point.getY() - 1).get(point.getX())) //Checks Up of State
            {
                case (' '): {
                    State<Point> possibleState = new State<>(new Point(point.getX(), point.getY() - 1));
                    possibleState.setCost(s.getCost()+1);
                    moves.put(new SearchAction("up"), (State<T>) possibleState);
                    break;
                }
                case ('@'): {
                    if (!(level.getBoard().get(point.getY() - 2).get(point.getX()).equals('@')) && !(level.getBoard().get(point.getY() - 2).get(point.getX()).equals('#'))) {
                        State<Point> possibleState = new State<>(new Point(point.getX(), point.getY() - 1));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("up"), (State<T>) possibleState);
                    }
                    break;
                }
                case ('o'): {
                    State<Point> possibleState = new State<>(new Point(point.getX(), point.getY() - 1));
                    possibleState.setCost(s.getCost()+1);
                    moves.put(new SearchAction("up"), (State<T>) possibleState);
                    break;

                }
            }
            switch (level.getBoard().get(point.getY() + 1).get(point.getX())) //Checks Below of State
            {
                case (' '): {
                    State<Point> possibleState = new State<>(new Point(point.getX(), point.getY() + 1));
                    possibleState.setCost(s.getCost()+1);
                    moves.put(new SearchAction("down"), (State<T>) possibleState);
                    break;
                }
                case ('@'): {
                    if (!(level.getBoard().get(point.getY() + 2).get(point.getX()).equals('@')) && !(level.getBoard().get(point.getY() + 2).get(point.getX()).equals('#'))) {
                        State<Point> possibleState = new State<>(new Point(point.getX(), point.getY() + 1));
                        possibleState.setCost(s.getCost()+1);
                        moves.put(new SearchAction("down"), (State<T>) possibleState);
                    }
                    break;
                }
                case ('o'): {
                    State<Point> possibleState = new State<>(new Point(point.getX(), point.getY() + 1));
                    possibleState.setCost(s.getCost()+1);
                    moves.put(new SearchAction("down"), (State<T>) possibleState);
                    break;

                }
            }
        }
        System.out.println(moves.toString());
            return moves;





    }





    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void setGoal(State<Point> goal) {
        this.goal = goal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private boolean clearAt(int x, int y)
    {
        return level.getBoard().get(y).get(x).equals(' ');
    }
    private boolean boxAt(int x, int y)
    {
        return level.getBoard().get(y).get(x).equals('@');
    }
    private boolean equalsToInitial(Point p)
    {
        Point initial=(Point)getInitialState().getState();
        return initial.equals(p);
    }
    private boolean goalAt(int x, int y)
    {
        return level.getBoard().get(y).get(x).equals('o');
    }
    private boolean sokoAt(int x, int y)
    {
        return level.getBoard().get(y).get(x).equals('A');
    }
}


