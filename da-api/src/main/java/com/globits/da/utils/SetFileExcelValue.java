package com.globits.da.utils;

import com.globits.da.dto.EmployeeDTO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Component
public class SetFileExcelValue {
    public static final int COLUMN_INDEX_CODE = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_EMAIL = 2;
    public static final int COLUMN_INDEX_PHONE = 3;
    public static final int COLUMN_INDEX_AGE = 4;
    public static final int COLUMN_INDEX_PROVINCE_ID = 5;
    public static final int COLUMN_INDEX_DISTRICT_ID = 6;
    public static final int COLUMN_INDEX_COMMUNE_ID = 7;
    public static final int COLUMN_INDEX_PROVINCE_NAME = 8;
    public static final int COLUMN_INDEX_DISTRICT_NAME = 9;
    public static final int COLUMN_INDEX_COMMUNE_NAME = 10;
    public static final int COLUMN_INDEX_CREATE_DATE = 11;
    public static final int COLUMN_INDEX_CREATE_BY = 12;
    public static final int COLUMN_INDEX_MODIFY_DATE = 13;
    public static final int COLUMN_INDEX_MODIFY_BY = 14;

    // Get Workbook
    public Workbook createFileExcel(String excelFilePath) {
        Workbook workbook;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }
        return workbook;
    }

    public void writeHeaderRow(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_CODE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("CODE");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("NAME");

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("EMAIL");

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("PHONE");

        cell = row.createCell(COLUMN_INDEX_AGE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("AGE");

//        cell = row.createCell(COLUMN_INDEX_PROVINCE_ID);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("PROVINCE_ID");
//
//        cell = row.createCell(COLUMN_INDEX_DISTRICT_ID);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("DISTRICT_ID");
//
//        cell = row.createCell(COLUMN_INDEX_COMMUNE_ID);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("COMMUNE_ID");
//
//        cell = row.createCell(COLUMN_INDEX_PROVINCE_NAME);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("PROVINCE_NAME");
//
//        cell = row.createCell(COLUMN_INDEX_DISTRICT_NAME);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("DISTRICT_NAME");
//
//        cell = row.createCell(COLUMN_INDEX_COMMUNE_NAME);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("COMMUNE_NAME");
//
//        cell = row.createCell(COLUMN_INDEX_CREATE_DATE);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("CREATE_DATE");
//
//        cell = row.createCell(COLUMN_INDEX_CREATE_BY);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("CREATE_BY");
//
//        cell = row.createCell(COLUMN_INDEX_MODIFY_DATE);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("MODIFY_DATE");
//
//        cell = row.createCell(COLUMN_INDEX_MODIFY_BY);
//        cell.setCellStyle(cellStyle);
//        cell.setCellValue("MODIFY_BY");
    }

    public void writeDataRows(EmployeeDTO employeeDto, Row row) {
        if (employeeDto == null) {
            throw new IllegalArgumentException("EmployeeDTO cannot be null");
        }

        Cell cell = row.createCell(COLUMN_INDEX_CODE);
        cell.setCellValue(employeeDto.getCode() != null ? employeeDto.getCode() : "");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(employeeDto.getName() != null ? employeeDto.getName() : "");

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellValue(employeeDto.getEmail() != null ? employeeDto.getEmail() : "");

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellValue(employeeDto.getPhone() != null ? employeeDto.getPhone() : "");

        cell = row.createCell(COLUMN_INDEX_AGE);
        if (employeeDto.getAge() != null) {
            cell.setCellValue(employeeDto.getAge());
        } else {
            cell.setCellValue("");
        }
    }


    // Create output file
    public void writeFileExcel(Workbook workbook, String excelFilePath) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    // Create CellStyle for header
    public CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 12); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.AUTOMATIC.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    public void setEmployeeValueFromExcel(EmployeeDTO employeeDto, Row row) {
        int index = 0;
        employeeDto.setCode(row.getCell(index++).getStringCellValue());
        employeeDto.setName(row.getCell(index++).getStringCellValue());
        employeeDto.setEmail(row.getCell(index++).getStringCellValue());
        employeeDto.setPhone(row.getCell(index++).getStringCellValue());
        employeeDto.setAge((int) row.getCell(index++).getNumericCellValue());
    }
}
