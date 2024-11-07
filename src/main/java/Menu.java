public class Menu {


    public static void main(String[] args) {

        ConsoleHelper.writeMessage("Привет, пользователь!");

        while (true) {
            ConsoleHelper.writeMessage(""" 
                    Выбери действие введя его номер с клавиатуры:\s
                    1 — Зашифровать данные,\s
                    2 — Расшифровать данные,\s
                    3 — Подобрать ключ методом Bruteforce,\s
                    4 — Выполнить частотный анализ текста,\s
                    5 — Выход из программы""");


            String answer = ConsoleHelper.readString();
            switch (answer) {
                case "1" -> new EncryptedDecrypted().encrypted(true);
                case "2" -> new EncryptedDecrypted().encrypted(false);
                case "3" -> new Bruteforce().bruteforce();
                case "4" -> new Parsing().parse();
                case "5" -> {
                    ConsoleHelper.writeMessage("Выход из программы.");
                    return;
                }
            }
        }
    }
}





