package util.helpers;

import lombok.extern.log4j.Log4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ExcelHelper {

    public static HSSFSheet getSheetFromFile(String sheetName, File excelFile) {

        HSSFWorkbook workbook = null;
        BufferedInputStream xlsReport = null;

        try {
            xlsReport = new BufferedInputStream(new FileInputStream(excelFile));
        } catch (FileNotFoundException e) {
            log.error("Unable to create stream for Excel file!", e);
        }

        if (xlsReport != null) {
            try {
                workbook = new HSSFWorkbook(xlsReport);
            } catch (IOException e) {
                log.error("Unable to create workbook from Excel file!", e);
            }

            try {
                xlsReport.close();
            } catch (IOException e) {
                log.error("Problem while closing Excel file!", e);
            }
        }

        if (workbook != null)
            return workbook.getSheet(sheetName);
        else
            return null;
    }

    public static void closeWorkbook(HSSFSheet sheet) {
        try {
            sheet.getWorkbook().close();
        } catch (IOException e) {
            log.error("Problem while closing Excel workbook!", e);
        }
    }

    public static List<String> getStringFlavourValuesFromColumn(File file, String columnName) {

        List<String> values;

        HSSFSheet sheet = getSheetFromFile("Sheet1", file);

        HSSFRow firstRow = getFirstRow(sheet);

        int rowsNumber = sheet.getLastRowNum() + 1;

        short columnsNumber = firstRow.getLastCellNum();

        int cellNumber = getCellNumber(columnName, firstRow, columnsNumber);

        values = getColumnsStringValues(sheet, cellNumber, rowsNumber);

        ExcelHelper.closeWorkbook(sheet);

        return values;
    }

    public static List<Double> getNumericFlavourValuesFromColumn(File file, String columnName) {

        List<Double> values;

        HSSFSheet sheet = getSheetFromFile("Sheet1", file);

        HSSFRow firstRow = getFirstRow(sheet);

        int rowsNumber = sheet.getLastRowNum() + 1;

        short columnsNumber = firstRow.getLastCellNum();

        int cellNumber = getCellNumber(columnName, firstRow, columnsNumber);

        values = getColumnNumericValues(sheet, cellNumber, rowsNumber);

        ExcelHelper.closeWorkbook(sheet);

        return values;
    }

    private static HSSFRow getFirstRow(HSSFSheet sheet) {
        int firstRowNumber = sheet.getFirstRowNum();

        return sheet.getRow(firstRowNumber);
    }

    private static List<Double> getColumnNumericValues(HSSFSheet sheet, int cellNumber, int rowsNumber) {
        List<Double> intFlavourValues = new ArrayList<>();
        for (int i = 1; i < rowsNumber; i++) {
            intFlavourValues.add(sheet.getRow(i).getCell(cellNumber).getNumericCellValue());
        }

        return intFlavourValues;
    }

    private static List<String> getColumnsStringValues(HSSFSheet sheet, int cellNumber, int rowsNumber) {
        List<String> stringFlavourValues = new ArrayList<>();
        for (int i = 1; i < rowsNumber; i++) {
            stringFlavourValues.add(sheet.getRow(i).getCell(cellNumber).getStringCellValue());
        }

        return stringFlavourValues;
    }

    private static Integer getCellNumber(String columnName, HSSFRow row, short columnsNumber) {

        for (int i = 0; i < columnsNumber; i++) {
            if (row.getCell(i).getStringCellValue().equals(columnName)) {
                return i;
            }
        }

        return null;
    }
}
