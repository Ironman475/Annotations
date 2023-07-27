import java.lang.reflect.Method;
public class Saver {
    public static void main(String[] args) throws Exception {
        TextContainer container = new TextContainer();
        Method method = container.getClass().getDeclaredMethod("save");
        if(method.isAnnotationPresent(Save.class)){
                method.invoke(container);
                System.out.println("Text save to file: " + container.getClass().getAnnotation(SaveTo.class).path());
        }
    }
}

