package server.sokoserver;

import db.TableUtil;
import db.User;
import javafx.application.Platform;
import model.data.Level;
import solver.SokobanSolver;

import java.io.*;
import java.util.LinkedList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.*;

public class SokoClientHandler implements ClientHandler {
    private Level lvl;
    private TableUtil tableUtil=new TableUtil();


    @Override
    public void handleClient(InputStream in, OutputStream out) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos=new ObjectOutputStream(out);
        ObjectInputStream ois=new ObjectInputStream(in);

        Object input=ois.readObject();
        if(input instanceof Level) {
            this.lvl = (Level) input;
            System.out.println("Level Detected.");
            System.out.println("Checking Web Service");

            String solution = getSolutionFromService(lvl.getName());
            if (solution == null) {
                System.out.println("No Solution, Calling strips");
                SokobanSolver solver = new SokobanSolver(lvl);
                System.out.println("CLIENT GOT SOLUTION");
                StringBuilder finalsolution = new StringBuilder();
                LinkedList<String> list = solver.solve();
                for (String s : list) {
                    finalsolution.append(s);
                    finalsolution.append(" ");
                }

                String url = "http://localhost:8080/resources/solutions";
                Client client = ClientBuilder.newClient();
                WebTarget target = client.target(url);
                MultivaluedHashMap<String, String> formdata = new MultivaluedHashMap<>();
                formdata.add("name", lvl.getName());
                formdata.add("solution", finalsolution.toString());
                Response response = target.request().post(Entity.form(formdata));
                oos.writeObject(finalsolution.toString());
            } else {
                oos.writeObject(solution);
                System.out.println("Solution Already Made, Sending..");
            }
        }
        else {
            if (input instanceof User) {
                User gamer = (User) input;
                tableUtil.addUser(gamer);
            } if(input instanceof String){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            tableUtil.showLeaderboard();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
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

    private String getSolutionFromService(String name)
    {
        String url="http://localhost:8080/resources/solutions/"+name;
        Client client=ClientBuilder.newClient();
        WebTarget webTarget=client.target(url);
        Response response=webTarget.request(MediaType.TEXT_PLAIN).get(Response.class);
        if(response.getStatus()==200)
        {
            String solution = response.readEntity(new GenericType<String>(){});
            System.out.println("handler found webservice solution: " + solution);
            return solution;
        }
        else
        {
            System.out.println(response.getHeaderString("errorResponse"));
        }
        return null;
    }

}

