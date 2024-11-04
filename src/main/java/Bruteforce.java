import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Bruteforce {

    @SneakyThrows
    public void bruteforce() {
        ConsoleHelper.writeMessage("Введите путь к файлу:");
        String src = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(src, "_bruteforce");
        CaesarCipher caesarCipher = new CaesarCipher();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(src));
             BufferedWriter writer = Files.newBufferedWriter(dst)) {
            int length = caesarCipher.alphabetLength();
            StringBuilder builder = new StringBuilder();
            List<String> list = new ArrayList<>(); //исходный, до
            while (reader.ready()) {
                String line = reader.readLine();
                builder.append(line).append(System.lineSeparator()); //универсальный перенос строки
                list.add(line);
            }
            for (int i = 0; i < length; i++) {
                String result = caesarCipher.decrypt(builder.toString(), i);
                if (isValidate(result)) {
                    for (String line : list) {
                        String decrypt = caesarCipher.decrypt(line, i);
                        writer.write(decrypt);
                        writer.newLine();
                    }

                    ConsoleHelper.writeMessage("Результат успешно записан в " + dst);
                    ConsoleHelper.writeMessage("Ключе шифрования: " + i);
                    break;
                }

            }
        }
    }


    private boolean isValidate(String text) {
        String[] words = text.split(" ");
        boolean isValidate = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 28) {
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
