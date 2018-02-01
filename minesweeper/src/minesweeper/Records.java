package minesweeper;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TimeZone;


public class Records {

    public static boolean printAndSaveRecords(long timeConsumedMillis) {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String recordsDate = null;
        long recordsTimeMills = 0;
        boolean hasRecords;
        final String RECORDS_FILE_PATH = "minesweeper/src/resources/records.svs";

        try (Scanner fileScanner = new Scanner
                (new FileInputStream(RECORDS_FILE_PATH))) {

            recordsTimeMills = fileScanner.nextLong();
            recordsDate = fileScanner.nextLine();
            hasRecords = true;
        } catch (FileNotFoundException | NoSuchElementException e) {
            hasRecords = false;
        }

        //парсим дату
        Date parsingDate = null;

        try {
            parsingDate = dateTimeFormat.parse(recordsDate);
        } catch (ParseException | NullPointerException e) {
            hasRecords = false;
            System.out.println("Ошибка чтения даты" + dateTimeFormat);
        }

        if (!hasRecords || recordsTimeMills > timeConsumedMillis) {
            try (PrintWriter writer = new PrintWriter
                    (new FileWriter(RECORDS_FILE_PATH))) {
                StringBuilder builder = new StringBuilder();
                builder.append(timeConsumedMillis);
                builder.append(" ");
                builder.append(dateTimeFormat.format(new Date()));

                writer.println(builder.toString());

                if (parsingDate != null) {
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

                    System.out.printf("Новый рекорд: %s!%n", timeFormat.format(timeConsumedMillis));
                    System.out.printf("Прежний рекорд: %s (в %s)%n", timeFormat.format(recordsTimeMills),
                            dateTimeFormat.format(parsingDate));
                }
            } catch (IOException e) {
                System.out.println("Ошибка записи рекорда: невозможно сохранить файл%n");
            }
        }
        return hasRecords;
    }
}

