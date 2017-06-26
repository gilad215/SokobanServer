package solver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class SolutionSaver {
    private BufferedWriter writer;

    public void save(LinkedList<String> solution,String output) throws IOException
    {
        writer=new BufferedWriter(new FileWriter(output));
        for (String str:solution) {
            writer.write(str);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
