import java.io.*;
@SaveTo(path = "D:\\file.txt")
class TextContainer {
    String text = "123456";
    @Save
    public void save(){
        File file = new File(getClass().getAnnotation(SaveTo.class).path());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}