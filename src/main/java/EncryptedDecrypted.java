import lombok.SneakyThrows;

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
        String content = Files.readString(Paths.get(src));
        Files.writeString(dst, flag ? caesar.encrypt(content, key) : caesar.decrypt(content, key));
        ConsoleHelper.writeMessage("Результат получен:" + dst.getFileName());
    }
}
