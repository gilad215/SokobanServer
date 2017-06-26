package soko;

import java.util.Objects;

public class Point {
    private int x;
    private int y;

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    Point()
    {
        x=0;
        y=0;
    }
    Point(Point p)
    {
        this.x=p.x;
        this.y=p.y;
    }
    public Point(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    //    public String getPoint()
//    {
//        return ("("+x+","+y+")");
//    }
    public String toString()
    {
        return ("("+x+","+y+")");
    }


    @Override
    public boolean equals(Object o) {

        Point p=(Point)o;
        return x==p.getX() && y==p.getY();
    }

    public boolean equals(Point p)
    {
        if(x==p.getX() && y==p.getY()) return true;
        else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x,y);
    }

}
