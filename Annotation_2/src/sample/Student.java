package sample;

@SaveToFile(filePath = "C:\\Itellij_IDEA_work_space\\demo1\\Annotation_2\\Student_file.txt")
public class Student {
    @Save
    String name;
    @Save
    String surname;
    @Save
    int age;
    @Save
    String groupName;

    public Student() {
    }
    public Student(String name, String surname, int age, String groupName) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
