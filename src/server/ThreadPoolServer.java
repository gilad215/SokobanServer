package server;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolServer {
    private int port = 8080;
    private ClientHandler ch;
    private boolean isStopped = false;
    private ExecutorService threadPool = null;



    public ThreadPoolServer(int port, ClientHandler clientHandler) {
        this.port = port;
        this.ch = clientHandler;
        this.threadPool = Executors.newFixedThreadPool(30);
    }

    public void runServer() throws Exception
    {
        InetAddress addr = InetAddress.getByName("127.0.0.1");
        ServerSocket server=new ServerSocket(8080,50,addr);
        System.out.println("SERVER UP");
        System.out.println(server.getLocalSocketAddress());
        System.out.println(server.getInetAddress());
        server.setSoTimeout(1000);
        while(!isStopped)
        {
            try
            {
                Socket aClient=server.accept();
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("TRYING TO FETCH DATA");
                            ch.handleClient(aClient.getInputStream(),aClient.getOutputStream());
                            aClient.getOutputStream().close();
                            aClient.getInputStream().close();
                            aClient.close();
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                });
            } catch (SocketTimeoutException ignored){}
        }
        server.close();
    }

    public void stopServer()
    {
        threadPool.shutdown();
        try{
            threadPool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e ) {e.printStackTrace();}
        finally {
            isStopped=true;
        }
    }

}



