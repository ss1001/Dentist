package com.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by 佘松 on 2017/10/14.
 */

public class Utils {
    public static final void saveObject(String path, Object saveObject){
        FileOutputStream fos=null;
        ObjectOutputStream oos=null;
        File f=new File(path);
        try {
            fos=new FileOutputStream(f);
            oos=new ObjectOutputStream(fos);
            oos.writeObject(saveObject);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
                try {
                    if(oos!=null) {
                        oos.close();
                    }
                    if(fos!=null){
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }
    public static final Object restoreObject(String path){
        FileInputStream fis=null;
        ObjectInputStream ois=null;
        Object object=null;
        File f=new File(path);
        if(!f.exists()){
            return null;
        }
        try {
            fis=new FileInputStream(f);
            ois=new ObjectInputStream(fis);
            object=ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try{
                if(fis!=null){
                    fis.close();
                }
                if(ois!=null){
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
