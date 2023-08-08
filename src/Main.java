import java.lang.reflect.Method;

class Pow {
    @Test(a = 5, b = 4)
    public static void test(int a, int b){
        System.out.println("a^b = " + Math.pow(a, b));
    }
}
public class Main {
    public static void main(String[] args) {
        try {
            Class<?> cls = Pow.class;
            Method[] methods = cls.getMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    Test abs = method.getAnnotation(Test.class);
                    method.invoke(null, abs.a(), abs.b());
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}