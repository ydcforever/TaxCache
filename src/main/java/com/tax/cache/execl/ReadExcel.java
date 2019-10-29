package com.tax.cache.execl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class ReadExcel {

    public List readFromExcel(String readPath, String sheetId) {
        Workbook workbook = getWorkbook(readPath);
        Sheet sheet = getSheet(workbook, sheetId);
        return readDriver(sheet);
    }

    public static Workbook getWorkbook(String readPath) {
        Workbook workbook = null;
        InputStream is;
        try {
            is = new FileInputStream(readPath);
            if (readPath.endsWith(".xls"))
                workbook = new HSSFWorkbook(is);
            else if (readPath.endsWith(".xlsx"))
                workbook = new XSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }

    public static Sheet getSheet(Workbook workbook, Object sheetId) {
        Sheet sheet;
        if (sheetId instanceof String) {
            sheet = workbook.getSheet(sheetId.toString());
        } else {
            sheet = workbook.getSheetAt((Integer) sheetId);
        }
        return sheet;
    }

    /**
     * custom read logic
     */
    public abstract List readDriver(Sheet sheet);
}
