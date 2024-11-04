import lombok.SneakyThrows;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class FrequencyAnalysis {
    @SneakyThrows
    public void frequencyAnalysis() {
        ConsoleHelper.writeMessage("Введите путь к файлу:");
        String src = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(src, "_frequency_analysis");
        BufferedReader reader = new BufferedReader(new FileReader(src));
        BufferedWriter writer = Files.newBufferedWriter(dst);
        CaesarCipher cipher = new CaesarCipher();
        StringBuilder builder = new StringBuilder();
        while (reader.ready()) {
            String line = reader.readLine();
            builder.append(line).append(System.lineSeparator()); //универсальный перенос строки
            writer.write(line);
        }
        char mostFrequentLetter = findMostFrequentLetter(builder.toString()); //заготовка, проверить самую часто встречающуюся букву
        writer.close();
        ConsoleHelper.writeMessage("Самая часто встречаемая буква: " + mostFrequentLetter);
        //находим самую часто встречающуюся букву, смотрим насколько у нее сдвиг от буквы "о" (условно)
        //для этого используем Цезарь
        // предлагаем проверить кусочек результата
    }
    public static char findMostFrequentLetter(String text) {
        Map<Character, Integer> map = new HashMap<>(); //сразу буква и ее ключ
        text = text.toLowerCase();
        for (int i = 0; i < text.length(); i++) {
            char frequency = text.charAt(i);
            if (Character.isLetter(frequency)) {
                if (map.containsKey(frequency)) {
                    map.put(frequency, map.get(frequency) + 1);
                } else {
                    map.put(frequency, 1);
                }
            }
        }
        char mostFrequentLetter = '\0'; //пустой, нулевой символ, отсутствие букв в тексте
        int maxFrequency = 0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) { // возвращает набор всех записей
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                mostFrequentLetter = entry.getKey();
            }
        }
        return mostFrequentLetter;
    }
}


