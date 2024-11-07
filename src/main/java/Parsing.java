import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Parsing {

    @SneakyThrows
    public void parse() {
        ConsoleHelper.writeMessage("Введите путь к файлу для его расшифровки");
        String pathEcnr = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введить путь к файлу для набора статистики.");
        String pathStat = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(pathEcnr, "_pars");
        Map<Character, Integer> mapEncr = fillMapValues(pathEcnr);
        Map<Character, Integer> mapStat = fillMapValues(pathStat);
        List<Map.Entry<Character, Integer>> listEncr = mapToList(mapEncr);
        List<Map.Entry<Character, Integer>> listStat = mapToList(mapStat);
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
    private Map<Character, Integer> fillMapValues(String path) {
        Map<Character, Integer> map = new HashMap<>(); // ctr alt T
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            while (reader.ready()) {
                builder.append(reader.readLine()).append(System.lineSeparator());
            }
        }
        for (char aChar : builder.toString().toCharArray()) {
            if (!map.containsKey(aChar)) {
                map.put(aChar, 1);
            } else {
                int value = map.get(aChar);
                map.put(aChar, value + 1);
            }

        }
        return map;
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        Set<Map.Entry<Character, Integer>> set = map.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(set);
        Comparator<Map.Entry<Character, Integer>> comparator = new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue() < o2.getValue()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        list.sort(comparator);
        return list;
    }

}
