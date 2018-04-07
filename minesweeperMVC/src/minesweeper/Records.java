package minesweeper;

/**
 * Считывает рекорды из records.svs, записи в котором могут быть на отсортированы по уровням сложности
 * если для текущего уровня появился новый рекорд, записывает старые сохранения и текущее, сортируя по уровням
 */

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Records {

    private long currentRecord;
    private long previousRecord;
    private Date previousRecordDate;
    boolean isNewRecord = false;

    public boolean updateRecord(long timeConsumedMillis, int level) {
        long recordsTimeMillsFromFile = 0;
        boolean isRecordParsedFromFile = false;
        Date parsingDate = null;

        final String RECORDS_FILE_PATH = "minesweeperMVC/src/resources/records.svs";
        final int LEVELS = 5;
        String[] loadedRecords = new String[LEVELS];


        try (Scanner fileScanner = new Scanner
                (new FileInputStream(RECORDS_FILE_PATH))) {

            while (fileScanner.hasNext()) {

                String loadedRecord = fileScanner.nextLine();

                Pattern pattern = Pattern.compile("^lvl[1-4] [0-9]{1,9} [0-9]{2}\\.[0-9]{2}\\.[0-9]{4} [0-9]{2}:[0-9]{2}:[0-9]{2}$");
                Matcher matcher = pattern.matcher(loadedRecord);

                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

                if (matcher.matches()) {
                    String[] array = loadedRecord.split(" ");
                    array[0] = array[0].substring(3); //прочитать уровень

                    int parsedLevel = Integer.parseInt(array[0]);
                    loadedRecords[parsedLevel] = loadedRecord;

                    if (parsedLevel == level) {
                        //парсим дальше эту строку
                        recordsTimeMillsFromFile = Long.parseLong(array[1]);

                        try {
                            parsingDate = dateTimeFormat.parse(array[2] + " " + array[3]);
                            isRecordParsedFromFile = true;
                        } catch (ParseException | NullPointerException e) {
                            isRecordParsedFromFile = false;
                            System.out.println("Ошибка чтения даты" + dateTimeFormat);
                        }
                    }
                }
            }
        } catch (FileNotFoundException | NoSuchElementException e) {
            System.out.println("Файл records.svs не найден");
        }

        currentRecord = timeConsumedMillis;

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        if (!isRecordParsedFromFile || recordsTimeMillsFromFile > timeConsumedMillis) {
            isNewRecord = true;

            //упорядочить и перезаписать рекорды
            try (PrintWriter writer = new PrintWriter
                    (new FileWriter(RECORDS_FILE_PATH))) {

                for (int i = 1; i < LEVELS; i++) {

                    if (i == level) {
                        String builder = "lvl" + level + " " + String.valueOf(timeConsumedMillis) +
                                " " +
                                dateTimeFormat.format(new Date());

                        writer.println(builder);
                    } else {
                        if (loadedRecords[i] != null) {
                            writer.println(loadedRecords[i]);
                        }
                    }
                    if (parsingDate != null) {
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

                        previousRecord = recordsTimeMillsFromFile;
                        previousRecordDate = parsingDate;
                    }
                }
            } catch (IOException e) {
                System.out.println("Ошибка записи рекорда: невозможно сохранить файл%n");
            }
        }
        return isNewRecord;
    }

    public long getCurrentRecord() {
        return currentRecord;
    }

    public long getPreviousRecord() {
        return previousRecord;
    }

    public Date getPreviousRecordDate() {
        return previousRecordDate;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }
}