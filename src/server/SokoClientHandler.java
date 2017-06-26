package server;

import soko.Level;

import java.io.*;
import java.util.ArrayList;

public class SokoClientHandler implements ClientHandler {
    private Level lvl;


    @Override
    public void handleClient(InputStream in, OutputStream out) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos=new ObjectOutputStream(out);
        ObjectInputStream ois=new ObjectInputStream(in);

        this.lvl=new Level((ArrayList<ArrayList<Character>>)ois.readObject());
        if(lvl!=null) System.out.println(lvl.getBoard());
    }

    private void readInputsAndSend(BufferedReader in, PrintWriter out,String exitStr){
        try {
            String line;
            while(!(line=in.readLine()).equals(exitStr)){
                out.println(line);
                out.flush();
            }
        } catch (IOException e) { e.printStackTrace();}
    }

    private Thread aSyncReadInputsAndSend(BufferedReader in, PrintWriter out, String exitStr){
        Thread t=new Thread(new Runnable() {
            public void run() {readInputsAndSend(in, out, exitStr);}
        });
        t.start();
        return t;
    }

}

