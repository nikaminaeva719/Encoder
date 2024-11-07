import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Bruteforce {

    private static final int MAX_WORD_LENGTH = 28;

    @SneakyThrows
    public void bruteforce() {
        ConsoleHelper.writeMessage("Введите путь к файлу:");
        String src = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(src, "_bruteforce");
        CaesarCipher caesar = new CaesarCipher();
        String content = Files.readString(Paths.get(src));
        for (int i = 0; i < caesar.alphabetLength(); i++) {
            String decrypt = caesar.decrypt(content, i);
            if (isValidate(decrypt)) {
                Files.writeString(dst, decrypt);
                ConsoleHelper.writeMessage("Результат успешно записан в " + dst);
                ConsoleHelper.writeMessage("Ключе шифрования: " + i);
                break;
            }
        }
    }

    private boolean isValidate(String text) {
        boolean isValidate = false;
        for (String word : text.split(" ")) {
            if (word.length() > MAX_WORD_LENGTH) {
                return false;
            }
        }
        if (text.contains("! ") || text.contains("? ") || text.contains(", ") || text.contains(". ")) {
            isValidate = true;
        }

        while (isValidate) {
            ConsoleHelper.writeMessage(text);
            ConsoleHelper.writeMessage("Понятен ли этот текст? Введите \"y\", если \"да\" и введите \"n\", если \"нет\"");
            String answer = ConsoleHelper.readString();
            if (answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            } else {
                ConsoleHelper.writeMessage("Пожалуйста, выберите из предложенных вариантов.");
            }
        }
        return false;
    }
}
