package server.model;

import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class AdminModel {
    private Map<String,Socket> connectedClients=new HashMap<>();

    private static final AdminModel instance=new AdminModel();
    private AdminModel(){}
    public static AdminModel getInstance(){return instance;}

    public void addClient(String username,Socket socket){connectedClients.put(username,socket);}

    public List<String> getClients()
    {
       List<String> clients=new ArrayList<>();
        clients.addAll(connectedClients.keySet());
        return clients;
    }

    public void disconnectClient(String username)
    {
        Socket socket=connectedClients.get(username);
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
