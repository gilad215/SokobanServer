package model.data;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class XMLUtil implements LevelSaver {

    public Object decode(InputStream fileName) throws IOException,
            ClassNotFoundException
    {
        XMLDecoder decoder=new XMLDecoder(new BufferedInputStream(fileName));
        Object obj=decoder.readObject();
        return obj;
    }

    @Override
    public void save(Object obj, String fileName) throws IOException {
        FileOutputStream fos= new FileOutputStream(fileName);
        XMLEncoder encoder=new XMLEncoder(fos);
        encoder.writeObject(obj);
        encoder.close();
        fos.close();
    }
}
