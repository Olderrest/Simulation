package com.stasuma.util;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.stasuma.objects.SimulationInfo;
import com.stasuma.service.impl.NetworkService;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CsvUtil {
    private static final Logger logger = Logger.getLogger(CsvUtil.class);

    private static final char SEPARATOR = ',';
    private static final String FILE_NAME = Constants.PATH_TO_APP_FOLDER + File.separator + Constants.CSV_FILE_NAME;
    private static final String FILE_HEADER = "Year,Month,Day,Hour,Minute,Stop_Id,Route_Id,Bus_Id,Passengers_In_Count,Passengers_Out_Count,Passengers_In_Bus_Count";
    private static final char QUOTE_CHARACTER = '"';
    private static final int START_LINE = 1;
    private static boolean addHeader = false;

    private CsvUtil() {
    }

    public static void writeToCsv(SimulationInfo info) {
        boolean newFile = false;
        File file = new File(FILE_NAME);
        if ((file.exists()) && (!file.isDirectory())) {
            newFile = true;
        }
        try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_NAME, newFile))) {
            if (!addHeader) {
                writer.writeNext(FILE_HEADER.split(","));
                addHeader = true;
            }
            String[] record = getStringArray(info);
            writer.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readCsvAndWriteToDB() {
        int size = 500;
        Set<SimulationInfo> infos = new HashSet<>(size);
        try (CSVReader reader = new CSVReader(new FileReader(FILE_NAME), SEPARATOR, QUOTE_CHARACTER, START_LINE)) {
            String[] record = null;
            reader.readNext();
            while ((record = reader.readNext()) != null) {
                SimulationInfo info = getObjectFromStrings(record);
                infos.add(info);
                if (infos.size() == size) {
                    NetworkService.saveAllSimulationInfo(infos);
                    infos = new HashSet<>(size);
                    logger.debug("Bus info written.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Finish writing data");
    }

    public static void deleteCsv(){
        File fileCsv = new File(FILE_NAME);
        logger.debug("Csv file deleted?: " + fileCsv.delete());
    }

    private static String[] getStringArray(SimulationInfo info) {
        return new String[]{String.valueOf(info.getYear()), String.valueOf(info.getMonth()), String.valueOf(info.getDay()),
                String.valueOf(info.getHour()), String.valueOf(info.getMinute()), String.valueOf(info.getStopId()),
                String.valueOf(info.getRouteId()), String.valueOf(info.getBusId()), String.valueOf(info.getPassengersInCount()),
                String.valueOf(info.getPassengersOutCount()), String.valueOf(info.getPassengersInBusCount())};
    }

    private static SimulationInfo getObjectFromStrings(String[] record) {
        return new SimulationInfo(Integer.valueOf(record[0]), Integer.valueOf(record[1]), Integer.valueOf(record[2]),
                Integer.valueOf(record[3]), Integer.valueOf(record[4]), Integer.valueOf(record[5]),
                Integer.valueOf(record[6]), Integer.valueOf(record[7]), Integer.valueOf(record[8]),
                Integer.valueOf(record[9]), Integer.valueOf(record[10]));
    }

}
