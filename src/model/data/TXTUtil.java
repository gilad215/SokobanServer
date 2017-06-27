package model.data;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TXTUtil implements LevelSaver {
    private Level lvl;
    private BufferedWriter writer;

    @Override
    public void save(Object obj, String fileName) throws IOException {
        this.lvl=(Level)obj;
        writer=new BufferedWriter(new FileWriter(fileName));
        for(int i=0;i<lvl.getBoard().size();i++)
        {
            for (int j=0;j<lvl.getBoard().get(i).size();j++)
            {
                writer.write(lvl.getBoard().get(i).get(j));
            }
            writer.newLine();
            writer.flush();

        }
        writer.close();
    }
    public Level load(InputStream input) {
        ArrayList<ArrayList<Character>> boardArray=new ArrayList<ArrayList<Character>>();
        Scanner fileScanner=new Scanner(new BufferedReader(new InputStreamReader(input)));
        while(fileScanner.hasNextLine())
        {
            boardArray.add(createRow(fileScanner.nextLine()));
        }
        return new Level(boardArray);
    }
    private ArrayList<Character> createRow(String s)
    {
        char[] c =s.toCharArray();
        ArrayList<Character> chars= new ArrayList<Character>();
        for (char ch :c) {
            chars.add(ch);
        }
        return chars;
    }
}
