
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lecture17Example {

    public static void main(String[] args) throws IOException {
        // File file = new File("data.txt");
        // file.createNewFile();
        // System.out.println(file.exists());
        // System.out.println(file.getName());
        // file.delete();

        // try (FileReader reader = new FileReader("lecture_17.md")) {
        //     int ch;
        //     while ((ch = reader.read()) != -1) {
        //         System.out.print((char) ch);
        //     }
        // }
        try (BufferedReader br = new BufferedReader(new FileReader("lecture_17.md"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
