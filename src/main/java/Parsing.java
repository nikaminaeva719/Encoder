import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.stream.Collectors;


public class Parsing {

    @SneakyThrows
    public void parse() {
        ConsoleHelper.writeMessage("Введите путь к файлу для его расшифровки");
        String pathEcnr = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введить путь к файлу для набора статистики.");
        String pathStat = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(pathEcnr, "_pars");
        List<Map.Entry<Character, Integer>> listEncr = convertToList(pathEcnr);
        List<Map.Entry<Character, Integer>> listStat = convertToList(pathStat);
        Map<Character, Character> decr = new HashMap<>();
        for (int i = 0; i < listEncr.size(); i++) {
            decr.put(listEncr.get(i).getKey(), listStat.get(i).getKey());
        }
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEcnr));
             BufferedWriter writer = Files.newBufferedWriter(dst)) {
            while (reader.ready()) {
                StringBuilder builder = new StringBuilder();
                String str = reader.readLine();
                char[] chars = str.toCharArray();
                for (char aChar : chars) {
                    Character decryptedCHar = decr.get(aChar);
                    builder.append(decryptedCHar);
                }
                writer.write(builder.toString());
                writer.newLine();
            }
        }
    }
    @SneakyThrows
    private List<Map.Entry<Character, Integer>> convertToList(String path) {
        Map<Character, Integer> map = new HashMap<>();
        String content = Files.readString(Paths.get(path));
        for (char aChar : content.toCharArray()) {
            map.merge(aChar, 1, Integer::sum);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return list;
    }

    @SneakyThrows
    private List<Map.Entry<String, Long>> convertToList1(String path) {
        return Arrays.stream(Files.readString(Paths.get(path)).split(""))
                .collect(Collectors.groupingBy(str -> str, Collectors.counting())).entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .toList();
    }
}
