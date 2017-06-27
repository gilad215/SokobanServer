package model.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class MySokobanLoader implements Loader {
    private HashMap<String, LevelLoader> fileTypes;
    private ArrayList<Point> Goals;
    private Level lvl=null;
    private String pathToFile;


   public MySokobanLoader(String path)
    {
        fileTypes= new HashMap<String, LevelLoader>();
        fileTypes.put("txt", new MyTextLevelLoader());
        fileTypes.put("xml", new MyXMLLevelLoader());
        fileTypes.put("obj", new MyObjectLevelLoader());
        this.pathToFile =path;
    }
    @Override
    public Level getLvl() {
        return lvl;
    }

    @Override
    public void setLvl(Level l) {
        this.lvl=l;
    }


    @Override
    public void load() {
        setLevelname();
       String extension = pathToFile;
        int i = extension.lastIndexOf('.');
        if (i > 0) {
            extension = extension.substring(i+1);    //gets the string after a '.'
        }
        if(!fileTypes.containsKey(extension)) //checks if the hashmap contains the string
        {
            System.out.println("Invalid Extension!");
            return;
        }

        LevelLoader loader=fileTypes.get(extension); // new LevelLoader from type extension.
        if(loader!=null)
        {
            try {
                this.setLvl(loader.loadLevel(new FileInputStream(pathToFile)));
            } catch (FileNotFoundException e) {
                System.out.println("File not Found!");
                return;

            }
            System.out.println("Level from type:"+extension.toUpperCase()+" has been loaded.");
        }
    }

    private void setLevelname()
    {
        File file=new File(pathToFile);
        int i=file.getName().indexOf('.');
        this.lvl.setName(file.getName().substring(0,i));
        System.out.println("LEVEL NAME:"+lvl.getName());
    }

}



