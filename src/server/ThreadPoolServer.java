package server;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class ThreadPoolServer implements Runnable{
    protected int          serverPort   = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean      isStopped    = false;
    protected Thread       runningThread= null;
    protected ExecutorService threadPool = Executors.newFixedThreadPool(10);

    protected ClientHandler ch;

    public ThreadPoolServer(int port,ClientHandler clientHandler){
        this.serverPort = port;
        this.ch=clientHandler;
    }


    @Override
    public void run() {
        synchronized (this)
        {
            this.runningThread=Thread.currentThread();
        }
        openServerSocket();
        while(!isStopped()){
            Socket aClient = null;
            try {
                aClient = this.serverSocket.accept();
                System.out.println("Client Connected.");
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    break;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            try {
                this.threadPool.execute(ch.handleClient(aClient.getInputStream(), aClient.getOutputStream()));
                aClient.getInputStream().close();
                aClient.getOutputStream().close();
                aClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        this.threadPool.shutdown();
        System.out.println("Server Stopped.") ;
    }


    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }
    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port:"+serverPort, e);
        }
    }
}
