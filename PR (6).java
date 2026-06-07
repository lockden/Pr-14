import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PR {

    static final int MAX_RECORDS = 50;

    static LocalDate[] dates = new LocalDate[MAX_RECORDS];
    static String[] notes = new String[MAX_RECORDS];

    static int count = 0;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== МІЙ ЩОДЕННИК =====");
            System.out.println("1. Додати запис");
            System.out.println("2. Видалити запис");
            System.out.println("3. Переглянути всі записи");
            System.out.println("4. Вихід");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addRecord(scanner);
                    break;

                case "2":
                    deleteRecord(scanner);
                    break;

                case "3":
                    showRecords();
                    break;

                case "4":
                    System.out.println("Програму завершено.");
                    return;

                default:
                    System.out.println("Невірний пункт меню.");
            }
        }
    }

    static void addRecord(Scanner scanner) {

        if (count >= MAX_RECORDS) {
            System.out.println("Щоденник заповнений.");
            return;
        }

        try {

            System.out.print("Введіть дату (рррр-мм-дд): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.println("Введіть текст запису.");
            System.out.println("Для завершення введіть END:");

            String text = "";
            String line;

            while (true) {

                line = scanner.nextLine();

                if (line.equals("END")) {
                    break;
                }

                text += line + "\n";
            }

            dates[count] = date;
            notes[count] = text;
            count++;

            System.out.println("Запис успішно додано.");

        } catch (DateTimeParseException e) {
            System.out.println("Некоректний формат дати.");
        }
    }

    static void deleteRecord(Scanner scanner) {

        try {

            System.out.print("Введіть дату для видалення (рррр-мм-дд): ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            int index = -1;

            for (int i = 0; i < count; i++) {
                if (dates[i].equals(date)) {
                    index = i;
                    break;
                }
            }

            if (index == -1) {
                System.out.println("Запис не знайдено.");
                return;
            }

            for (int i = index; i < count - 1; i++) {
                dates[i] = dates[i + 1];
                notes[i] = notes[i + 1];
            }

            dates[count - 1] = null;
            notes[count - 1] = null;

            count--;

            System.out.println("Запис видалено.");

        } catch (DateTimeParseException e) {
            System.out.println("Некоректний формат дати.");
        }
    }

    static void showRecords() {

        if (count == 0) {
            System.out.println("Щоденник порожній.");
            return;
        }

        System.out.println("\n===== УСІ ЗАПИСИ =====");

        for (int i = 0; i < count; i++) {

            System.out.println("--------------------");
            System.out.println("Дата: " + dates[i]);
            System.out.println(notes[i]);
        }
    }
}