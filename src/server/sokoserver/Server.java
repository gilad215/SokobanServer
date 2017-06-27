package server.sokoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Observable;

public class Server extends Observable{
    private int port;
    private ClientHandler ch;
    private volatile boolean stop;

    public Server(int port,ClientHandler ch) {
        this.port = port;
        this.ch = ch;
        stop = false;
    }


    public void runServer() {
        try{
        ServerSocket server=new ServerSocket(port);
        System.out.println("Server is LIVE");
        server.setSoTimeout(1000);
            while(!stop){

                    Socket aClient=server.accept();
                System.out.println("Client Connected.");
                    new Thread(new Runnable() {
                        public void run() {
                            try {
                                ch.handleClient(aClient.getInputStream(), aClient.getOutputStream());
                                aClient.getInputStream().close();
                                aClient.getOutputStream().close();
                                aClient.close();
                            } catch (IOException e) {} catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
            server.close();
        }           catch(SocketTimeoutException e) {} catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void stop(){
        stop=true;
    }
    public void start()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    runServer();

                }catch (Exception e) {e.printStackTrace();}

            }
        }).start();
    }
}
