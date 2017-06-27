package model.data;


import java.io.*;

public class OBJUtil implements LevelSaver {

    // deserialize to Object from given file
    public Object deserialize(InputStream fileName) throws IOException,
            ClassNotFoundException {
        //FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fileName);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    @Override
    public void save(Object obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);

        fos.close();
    }
}

    // serialize the given object and save it to file

