package com.automation.steps.reusableComponents;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelConfiguration {

    /**
     * Reads data from an Excel file.
     *
     * @param filePath  The path to the Excel file.
     * @param sheetName The name of the sheet from which to read data.
     * @return A list of maps representing the data read from the Excel file.
     */
    public static List<Map<String, String>> readExcelData(String filePath, String sheetName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet != null) {
                int rowCount = sheet.getPhysicalNumberOfRows();

                if (rowCount > 0) {
                    Row headerRow = sheet.getRow(0);

                    // Iterate through each row in the sheet
                    for (int i = 1; i < rowCount; i++) {
                        Row currentRow = sheet.getRow(i);

                        // Check if currentRow is null before processing
                        if (currentRow == null) {
                            System.out.println("Row " + i + " is null in the sheet.");
                            continue;
                        }

                        Map<String, String> dataMap = new HashMap<>();

                        // Iterate through each cell in the row
                        for (int j = 0; j < headerRow.getPhysicalNumberOfCells(); j++) {
                            Cell headerCell = headerRow.getCell(j);
                            Cell currentCell = currentRow.getCell(j);

                            // Check if both headerCell and currentCell are not null, and their types are STRING
                            if (headerCell != null && currentCell != null &&
                                    headerCell.getCellType() == CellType.STRING &&
                                    currentCell.getCellType() == CellType.STRING) {
                                // Add data to the dataMap
                                dataMap.put(headerCell.getStringCellValue(), currentCell.getStringCellValue());
                            }
                        }

                        // Add dataMap to the dataList
                        dataList.add(dataMap);
                    }
                } else {
                    System.out.println("No data found in the sheet.");
                }
            } else {
                System.out.println("Sheet not found in the workbook.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dataList;
    }
}