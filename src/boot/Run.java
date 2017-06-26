package boot;

import server.SokoClientHandler;
import server.ThreadPoolServer;

public class Run {
    public static void main(String[] args) throws Exception {
        SokoClientHandler ch=new SokoClientHandler();

        ThreadPoolServer server=new ThreadPoolServer(8080,ch);
        server.runServer();
    }
}
