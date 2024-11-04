import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.*;
import java.nio.file.Path;

public class ConsoleHelper { //утильтарный класс, за пределами класса нельзя создать объект, все методы статические

    private static final BufferedReader CONSOLE = new BufferedReader(new InputStreamReader(System.in));

    private ConsoleHelper() { //явный конструктор класса

    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    @SneakyThrows
    public static String readString() {
        return CONSOLE.readLine();
    }

    public static int readInt() {
        return Integer.parseInt(readString());
    }

    public static Path buildFileName(String path, String suffix) {
        Path fullPath = Path.of(path);
        Path parent = fullPath.getParent();
        String fullName = fullPath.getFileName().toString();
        String newFileName;
        if (fullName.contains(".")) {
            int index = fullName.lastIndexOf(".");
            newFileName = fullName.substring(0, index) + suffix + fullName.substring(index);
        } else {
            newFileName = fullName + suffix;
        }
        Path newPath = parent.resolve(newFileName);
        return newPath;
    }

}
