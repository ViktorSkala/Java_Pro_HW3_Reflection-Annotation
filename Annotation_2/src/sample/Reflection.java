package sample;

import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.*;

public class Reflection {
    public static void main(String[] args) {
        Test test = new Test();
        Class<?> cls = test.getClass();

        MethodAnnotation metAn = null;
        try {
            metAn = cls.getMethod("myMethod", String.class).getAnnotation(MethodAnnotation.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        test.myMethod(metAn.param3());

        Class<?> cls1 = null;
        try {
            cls1 = Class.forName("sample.TextContainer");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Object o1;
        try {
            o1 = cls1.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        Method m;
        try {
            m = cls1.getDeclaredMethod("save", null);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            m.invoke(o1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        Student student1 = new Student("Viktor","Skala", 32,"automation");
        TextContainer tc = new TextContainer();
        tc.saveObjectToFile(student1);
        Student student2 = (Student) tc.objectFromFile("C:\\Itellij_IDEA_work_space\\demo1\\Annotation_2\\Student2_file.txt");
        System.out.println(student2.toString());
    }
}
