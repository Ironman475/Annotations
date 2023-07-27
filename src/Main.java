import java.io.IOException;

public class Main {
    @Save
    public String name;
    @Save
    public int age;

    public Main(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Main(){
    }
    public static void main(String[] args) throws IOException, IllegalAccessException {
        Main obj = new Main("Sam", 20);
        SerializeMethods.serialize(obj, "D:\\test.txt");
        System.out.println(obj.name);
        System.out.println(obj.age);
        Main obj2 = new Main();
        SerializeMethods.deserialize(obj2, "D:\\test.txt");
        System.out.println(obj2.name);
        System.out.println(obj2.age);
    }
}
