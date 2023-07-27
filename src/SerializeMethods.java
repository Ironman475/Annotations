import java.io.*;
import java.lang.reflect.Field;

public class SerializeMethods {
    public static void serialize(Object obj, String path) throws IOException, IllegalAccessException {
        try (FileOutputStream fos = new FileOutputStream(path);
             BufferedOutputStream bof = new BufferedOutputStream(fos);
             DataOutputStream out = new DataOutputStream(bof)){
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields){
                if(field.isAnnotationPresent(Save.class)){
                    field.setAccessible(true);
                    out.writeUTF(field.getName());
                    out.writeUTF(field.getType().getName());
                    out.writeUTF(String.valueOf(field.get(obj)));
                }
            }
        }
    }
    public static void deserialize(Object obj, String path) throws IOException, IllegalAccessException {
        try (FileInputStream fis = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(fis);
        DataInputStream in = new DataInputStream(bis)) {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Save.class)) {
                    field.setAccessible(true);
                    String fieldName = in.readUTF();
                    if (fieldName.equals(field.getName())) {
                        String fieldValue = in.readUTF();
                        field.set(obj, parseValue(field.getType(), fieldValue));
                    }
                }
            }
        }
    }
    private static Object parseValue(Class<?> fieldType, String fieldValue) {
        if (fieldType == boolean.class) {
            return Boolean.parseBoolean(fieldValue);
        } else if (fieldType == byte.class) {
            return Byte.parseByte(fieldValue);
        } else if (fieldType == short.class) {
            return Short.parseShort(fieldValue);
        } else if (fieldType == int.class) {
            return Integer.parseInt(fieldValue);
        } else if (fieldType == long.class) {
            return Long.parseLong(fieldValue);
        } else if (fieldType == float.class) {
            return Float.parseFloat(fieldValue);
        } else if (fieldType == double.class) {
            return Double.parseDouble(fieldValue);
        } else if (fieldType == char.class) {
            return fieldValue.charAt(0);
        } else if (fieldType == String.class) {
            return fieldValue;
        } else {
            throw new IllegalArgumentException("Unsupported field type: " + fieldType.getName());
        }
    }
}
