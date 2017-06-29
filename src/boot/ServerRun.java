package boot;

import server.sokoserver.SokoClientHandler;
import server.sokoserver.ThreadPoolServer;

public class ServerRun {
    public static void main(String[] args) throws Exception {
        SokoClientHandler ch=new SokoClientHandler();

        ThreadPoolServer server=new ThreadPoolServer(5555,ch);
        server.runServer();
    }
}
