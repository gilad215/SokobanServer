package boot;

import server.SokoClientHandler;
import server.ThreadPoolServer;

public class ServerRun {
    public static void main(String[] args) throws Exception {
        SokoClientHandler ch=new SokoClientHandler();

        ThreadPoolServer server=new ThreadPoolServer(8021,ch);
        server.runServer();
    }
}
