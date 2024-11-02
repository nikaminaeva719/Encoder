import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EncryptedDecrypted {

    @SneakyThrows
    public void encrypted(boolean flag) {
        ConsoleHelper.writeMessage("Введите путь для " + (flag ? "зашифровки." : "дешифровки."));
        String src = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите ключ:");
        int key = ConsoleHelper.readInt();
        Path dst = ConsoleHelper.buildFileName(src, (flag ? "_encr" : "_decr"));
        CaesarCipher caesar = new CaesarCipher();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(src))) {
            try (BufferedWriter writer = Files.newBufferedWriter(dst)) {
                while (reader.ready()) {
                    String string = reader.readLine();
                    String encryptedDecrypted = flag ? caesar.encrypt(string, key) : caesar.decrypt(string, key);
                    writer.write(encryptedDecrypted);
                    writer.newLine();
                }
            }
        }
        ConsoleHelper.writeMessage("Результат получен:" + dst.getFileName());
    }
}
