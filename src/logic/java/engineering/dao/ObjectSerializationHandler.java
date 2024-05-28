package engineering.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ObjectSerializationHandler<Object>{

    private String filePath;

    public ObjectSerializationHandler(String filePath){
        this.filePath = filePath;
    }

    private void writeObjectsCleaned(List<Object> objects) throws IOException {
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            for (Object o : objects) {
                outputStream.writeObject(o);
            }
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public void writeObjects(Object object) throws IOException, ClassNotFoundException {
        List<Object> objs = readObjects();
        objs.add(object);
        writeObjectsCleaned(objs);

    }

    public void writeObjects(List<Object> objects) throws IOException, ClassNotFoundException {
        List<Object> objs = readObjects();
        objs.addAll(objects);
        writeObjectsCleaned(objs);
    }

    public List<Object> readObjects() throws IOException, ClassNotFoundException {
        List<Object> objects = new ArrayList<>();
        ObjectInputStream inputStream = null;
        try{
            inputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filePath)));
            while (true){
                Object object = (Object) inputStream.readObject();
                objects.add(object);
            }
        } catch (EOFException e){
            return objects;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public List<Object> findObject(Predicate<Object> predicate) throws IOException, ClassNotFoundException {
        List<Object> objects = readObjects();
        objects = objects.stream().filter(predicate).toList();
        return objects;
    }

    public void deleteObjects(Predicate<Object> predicate) throws IOException, ClassNotFoundException {
        List<Object> objects = readObjects();
        objects.removeIf(predicate);
        writeObjectsCleaned(objects);
    }

    public void deleteObjects(List<Predicate<Object>> predicates) throws IOException, ClassNotFoundException {
        List<Object> objs = readObjects();
        for (Predicate<Object> p : predicates) {
                objs.removeIf(p);
        }
        writeObjectsCleaned(objs);
    }

}

