package model.data;

import java.util.ArrayList;

public class MySokobanDisplay implements Displayer {
    private Level lvl;
    public MySokobanDisplay(Level l)
    {
        this.lvl=l;
    }

    @Override
    public void display() {
        ArrayList<ArrayList<Character>> board = getLvl().getBoard();
        for (ArrayList<Character> b : board) {
            for (Character c : b) {
                System.out.print(c);
            }
            System.out.println();

        }
//hi
    }

    private Level getLvl() {
        return lvl;
    }

    public void setLvl(Level lvl) {
        this.lvl = lvl;
    }
}
