package model.data;

public interface Policy {
    boolean isFinished();
    boolean moveMade();
    void Move(String move);
    Level getLvl();
}
