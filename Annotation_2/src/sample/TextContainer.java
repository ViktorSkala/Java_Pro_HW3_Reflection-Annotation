package sample;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

@SaveToFile(filePath = "C:\\Itellij_IDEA_work_space\\demo1\\Annotation_2\\File.txt")
public class TextContainer {
    String text = "You save this text to file";

    @Save
    public void save() throws IOException {
        Class<TextContainer> cls = TextContainer.class;
        File fileOutput = new File(cls.getAnnotation(SaveToFile.class).filePath());
        try (FileWriter fw = new FileWriter(fileOutput)) {
            fw.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Gongratulation");
    }
    public void saveObjectToFile(Object obj) {
        Class<?> cls = obj.getClass();
        String temp = obj.getClass().getSimpleName() + "=" + obj.getClass().getName() + System.lineSeparator();
        File fileOutput = new File(cls.getAnnotation(SaveToFile.class).filePath());
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Save.class)) {
                try {
                    temp += field.getName() + "=" + field.get(obj) + System.lineSeparator();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try (FileWriter fl = new FileWriter(fileOutput)) {
            fl.write(temp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object objectFromFile(String filePath) {
        String temp;
        Map<String,String> map1 = new LinkedHashMap<>();
        File fileInput = new File(filePath);
        try (FileReader fr = new FileReader(fileInput)) {
            BufferedReader br = new BufferedReader(fr);
            for (;(temp = br.readLine()) != null;) {
                String[] fields = temp.split("=");
                map1.put(fields[0],fields[1]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Collection<?> col = map1.values();
        Iterator<?> itr = col.iterator();
        String className = (String) itr.next();
        itr.remove();
        Object[] parameters = new Object[col.size()];
        for (int j = 0; j < parameters.length; j++ ) {
            parameters[j] = itr.next();
        }
        Class<?> cls;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Field[] fields = cls.getDeclaredFields();
        Class[] types = new Class[fields.length];
        for (int i = 0; i < fields.length; i++) {
            types[i] = fields[i].getType();
            if (types[i].getName().equals("int")) {
                parameters[i] = Integer.parseInt((String)parameters[i]);
            }
        }

        Constructor<?> constr;
        try {
            constr = cls.getDeclaredConstructor(types);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Object newObj;
        try {
            newObj = constr.newInstance(parameters);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    return newObj;
    }
}
