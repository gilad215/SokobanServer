package server.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class AdminModel {
    private Map<String,Socket> connectedClients=new HashMap<>();
    private boolean isStopped=false;
    private static final AdminModel instance=new AdminModel();
    private AdminModel(){}
    public static AdminModel getInstance(){return instance;}

    public void addClient(String username,Socket socket){connectedClients.put(username,socket);}

    public List<String> getClients() throws IOException {
//        for (Socket sock:connectedClients.values()) {
//            System.out.println("Checking SOCKET:"+sock.getRemoteSocketAddress().toString());
//            PrintWriter writer = new PrintWriter(sock.getOutputStream(), true);
//            if(!writer.checkError())
//            {
//                System.out.println("SOCKET:"+sock.getInetAddress().toString()+" is Unreachable! Removing..");
//                for (String s:connectedClients.keySet()) {
//                    if(connectedClients.get(s).equals(sock)) connectedClients.remove(s);
//                }
//            }
//        }

       List<String> clients=new ArrayList<>();
        clients.addAll(connectedClients.keySet());
        return clients;
    }

    public void disconnectClient(String username)
    {
        Socket user=connectedClients.remove(username);
        System.out.println("Disconnecting Client "+user.getRemoteSocketAddress().toString()+"..");
        try {
            user.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(user.isClosed())System.out.println("Disconnected Successfully");
        else System.out.println("Client Has Not Disconnected");

    }

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }
}
