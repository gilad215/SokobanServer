package model.data;

import java.util.ArrayList;

public class MySokobanPolicy implements Policy {
    private ArrayList<Point> Goals;
    private Level lvl;
    private boolean moveMade=true;
    private int playerY;
    private int playerX;
    private Point playerPoint;

    public MySokobanPolicy(Level l)
    {
        this.lvl=l;
        Goals = new ArrayList<>();
        this.Goals=lvl.getGoals();
        setPlayer();
    }
    public boolean isFinished()
    {
        for (Point p:Goals) {
            if(lvl.getBoard().get(p.getY()).get(p.getX())!='@') return false;
        }
        return true;
    }
    private void notAllowed() {
        moveMade=false;
    }

    public void Move(String move) {
        System.out.println("Move to be made:"+move);


                switch (move) {
                    case ("up"): {
                        //System.out.println("Moving Up...");
                        char DesiredSpot = lvl.getBoard().get(playerY - 1).get(playerX);
                        switch (DesiredSpot) {
                            case ('#'):
                            {
                                notAllowed();
                                return;

                            }
                            case ('o'): {
                                lvl.getBoard().get(playerY - 1).set(playerX, 'A');
                                if (isGoal(playerPoint)) lvl.getBoard().get(playerY).set(playerX, 'o');
                                else lvl.getBoard().get(playerY).set(playerX, ' ');
                                break;
                            }
                            case (' '):
                                lvl.getBoard().get(playerY - 1).set(playerX, 'A');
                                if (isGoal(playerPoint)) lvl.getBoard().get(playerY).set(playerX, 'o');
                                else lvl.getBoard().get(playerY).set(playerX, ' ');
                                break;

                            case ('@'): {
                                if (lvl.getBoard().get(playerY - 2).get(playerX).equals('#'))
                                {
                                    notAllowed();
                                    return;
                                }
                                if (lvl.getBoard().get(playerY - 2).get(playerX).equals('@'))
                                {
                                    notAllowed();
                                    return;
                                }
                                if (lvl.getBoard().get(playerY - 2).get(playerX).equals(' ')) {
                                    lvl.getBoard().get(playerY - 2).set(playerX, '@');
                                    lvl.getBoard().get(playerY - 1).set(playerX, 'A');
                                    if (isGoal(playerPoint)) {
                                        lvl.getBoard().get(playerY).set(playerX, 'o');
                                    } else lvl.getBoard().get(playerY).set(playerX, ' ');
                                    break;
                                }
                                if (lvl.getBoard().get(playerY - 2).get(playerX).equals('o')) {
                                    lvl.getBoard().get(playerY - 2).set(playerX, '@');
                                    lvl.getBoard().get(playerY - 1).set(playerX, 'A');
                                    if (isGoal(playerPoint)) {
                                        lvl.getBoard().get(playerY).set(playerX, 'o');
                                    } else lvl.getBoard().get(playerY).set(playerX, ' ');
                                    break;
                                }
                                break;
                            }

                        }
                        break;
                    }
                    case ("down"): {
                        //System.out.println("Moving Down...");
                        char DesiredSpot = lvl.getBoard().get(playerY + 1).get(playerX);
                        switch (DesiredSpot) {
                            case ('#'):
                            {
                                notAllowed();
                                return;
                            }
                            case ('o'): {
                                lvl.getBoard().get(playerY + 1).set(playerX, 'A');
                                if (isGoal(playerPoint)) lvl.getBoard().get(playerY).set(playerX, 'o');
                                else lvl.getBoard().get(playerY).set(playerX, ' ');
                                break;
                            }
                            case (' '): {
                                lvl.getBoard().get(playerY + 1).set(playerX, 'A');
                                if (isGoal(playerPoint)) {
                                    lvl.getBoard().get(playerY).set(playerX, 'o');
                                } else {
                                    lvl.getBoard().get(playerY).set(playerX, ' ');
                                }
                                break;
                            }

                            case ('@'): {
                                if (lvl.getBoard().get(playerY + 2).get(playerX).equals('#'))
                                {
                                    notAllowed();
                                    return;
                                }
                                if (lvl.getBoard().get(playerY + 2).get(playerX).equals('@'))
                                {
                                    notAllowed();
                                    return;
                                }
                                if (lvl.getBoard().get(playerY + 2).get(playerX).equals(' ')) {
                                    lvl.getBoard().get(playerY + 2).set(playerX, '@');
                                    lvl.getBoard().get(playerY + 1).set(playerX, 'A');
                                    if (isGoal(playerPoint)) {
                                        lvl.getBoard().get(playerY).set(playerX, 'o');
                                    } else lvl.getBoard().get(playerY).set(playerX, ' ');
                                    break;
                                }
                                if (lvl.getBoard().get(playerY + 2).get(playerX).equals('o')) {
                                    lvl.getBoard().get(playerY + 2).set(playerX, '@');
                                    lvl.getBoard().get(playerY + 1).set(playerX, 'A');
                                    if (isGoal(playerPoint)) {
                                        lvl.getBoard().get(playerY).set(playerX, 'o');
                                    } else lvl.getBoard().get(playerY).set(playerX, ' ');
                                    break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                    case ("right"): {
                        //System.out.println("Moving Right...");
                        char DesiredSpot = lvl.getBoard().get(playerY).get(playerX + 1);
                        switch (DesiredSpot) {
                            case ('#'):
                            {
                                notAllowed();
                                return;
                            }
                            case ('o'): {
                                lvl.getBoard().get(playerY).set(playerX + 1, 'A');
                                if (isGoal(playerPoint)) lvl.getBoard().get(playerY).set(playerX, 'o');
                                else lvl.getBoard().get(playerY).set(playerX, ' ');
                                break;
                            }
                            case (' '): {
                                lvl.getBoard().get(playerY).set(playerX + 1, 'A');
                                if (isGoal(playerPoint)) {
                                    lvl.getBoard().get(playerY).set(playerX, 'o');
                                } else {
                                    lvl.getBoard().get(playerY).set(playerX, ' ');
                                }
                                break;
                            }

                            case ('@'): {
                                if (lvl.getBoard().get(playerY).get(playerX + 2).equals('#'))
                                {
                                    notAllowed();
                                    return;
                                }
                                if (lvl.getBoard().get(playerY).get(playerX + 2).equals('@'))
                                {
                                    notAllowed();
                                    return;
                                }
                                if (lvl.getBoard().get(playerY).get(playerX + 2).equals(' ')) {
                                    lvl.getBoard().get(playerY).set(playerX + 2, '@');
                                    lvl.getBoard().get(playerY).set(playerX + 1, 'A');
                                    if (isGoal(playerPoint)) {
                                        lvl.getBoard().get(playerY).set(playerX, 'o');
                                    } else lvl.getBoard().get(playerY).set(playerX, ' ');
                                    break;
                                }
                                if (lvl.getBoard().get(playerY).get(playerX + 2).equals('o')) {
                                    lvl.getBoard().get(playerY).set(playerX + 2, '@');
                                    lvl.getBoard().get(playerY).set(playerX + 1, 'A');
                                    if (isGoal(playerPoint)) {
                                        lvl.getBoard().get(playerY).set(playerX, 'o');
                                    } else lvl.getBoard().get(playerY).set(playerX, ' ');
                                    break;
                                }
                                break;
                            }
                        }
                        break;
                    }
                    case ("left"): {
                        //System.out.println("Moving Left...");
                        char DesiredSpot = lvl.getBoard().get(playerY).get(playerX - 1);
                        switch (DesiredSpot) {
                            case ('#'):
                            {
                                notAllowed();
                                return;
                            }
                            case ('o'): {
                                lvl.getBoard().get(playerY).set(playerX - 1, 'A');
                                if (isGoal(playerPoint)) lvl.getBoard().get(playerY).set(playerX, 'o');
                                else lvl.getBoard().get(playerY).set(playerX, ' ');
                                break;
                            }
                            case (' '): {
                                lvl.getBoard().get(playerY).set(playerX - 1, 'A');
                                if (isGoal(playerPoint)) {
                                    lvl.getBoard().get(playerY).set(playerX, 'o');
                                } else {
                                    lvl.getBoard().get(playerY).set(playerX, ' ');
                                }
                                break;
                            }

                            case ('@'): {
                                if (lvl.getBoard().get(playerY).get(playerX - 2).equals('#'))
                                {
                                    notAllowed();
                                    return;
                                }
                                if (lvl.getBoard().get(playerY).get(playerX - 2).equals('@'))
                                {
                                    notAllowed();
                                    return;
                                }
                                if (lvl.getBoard().get(playerY).get(playerX - 2).equals(' ')) {
                                    lvl.getBoard().get(playerY).set(playerX - 2, '@');
                                    lvl.getBoard().get(playerY).set(playerX - 1, 'A');
                                    if (isGoal(playerPoint)) {
                                        lvl.getBoard().get(playerY).set(playerX, 'o');
                                    } else lvl.getBoard().get(playerY).set(playerX, ' ');
                                    break;
                                }//
                                if (lvl.getBoard().get(playerY).get(playerX - 2).equals('o')) {
                                    lvl.getBoard().get(playerY).set(playerX - 2, '@');
                                    lvl.getBoard().get(playerY).set(playerX - 1, 'A');
                                    if (isGoal(playerPoint)) {
                                        lvl.getBoard().get(playerY).set(playerX, 'o');
                                    } else lvl.getBoard().get(playerY).set(playerX, ' ');
                                    break;
                                }
                                break;
                            }
                        }
                    }
                }
            }

    private boolean isGoal(Point p) {
        for (Point g:Goals) {
            if(g.toString().equals(p.toString())) return true;
        }
        return false;}

    private int getIndexbyChar(char c, ArrayList<Character> arr) {
        int index = -1;
        for (Character line : arr) {
            if (line.equals(c)) index = arr.indexOf(line);
        }
        return index;
    }

    public Level getLvl(){return this.lvl;}
    public void setPlayer()
    {
        int Y = lvl.getBoard().size();
        playerX = -1;
        playerY = lvl.getBoard().size();


        for (int i = 0; i < Y; i++) {
            playerX = getIndexbyChar('A', lvl.getBoard().get(i));
            if (playerX != -1) {
                playerY = i;
                break;
            }

        }
        if (playerX == -1) {
            System.out.println("Level is invalid.");
            return;
        }
        playerPoint =new Point(playerX,playerY);
    }
    public Point getPlayer() {return playerPoint;}
    public boolean moveMade(){return moveMade;}


}
