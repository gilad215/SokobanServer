package server;

import java.io.*;

public class CLIclient implements ClientHandler {
    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try {
            BufferedReader readFromClient=new BufferedReader(new InputStreamReader(inFromClient));
            BufferedReader readFromServer=new BufferedReader(new InputStreamReader(System.in));

            PrintWriter writeToClient=new PrintWriter(outToClient);
            PrintWriter writeToServer=new PrintWriter(System.out);

            System.out.println("thread 1");
            Thread t1=aSyncReadInputsAndSend(readFromServer, writeToClient, "bye");

            System.out.println("thread 2");
            Thread t2=aSyncReadInputsAndSend(readFromClient, writeToServer, "exit");

            t1.join();
            t2.join();

            readFromClient.close();
            readFromClient.close();
            writeToClient.close();
            writeToServer.close();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

