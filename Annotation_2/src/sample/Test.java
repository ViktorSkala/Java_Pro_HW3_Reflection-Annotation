package sample;

@MyAnnotation(param1 = "Hello", param2 = "Java")
public class Test {
    @MethodAnnotation(param3 = "World")
    public void myMethod(String str) {
        System.out.println(str);
    }
}
