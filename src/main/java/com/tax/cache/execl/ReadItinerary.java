package com.tax.cache.execl;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ydc on 2019/10/24.
 */
public class ReadItinerary extends ReadExcel {

    private String path;

    private String sheetName;

    public ReadItinerary(String path, String sheetName) {
        this.path = path;
        this.sheetName = sheetName;
    }

    public List begin() {
        return readFromExcel(path, sheetName);
    }

    @Override
    public List<String> readDriver(Sheet sheet) {
        List<String> list = new ArrayList<String>();
        for(int i = 1, len = sheet.getLastRowNum(); i < len; i ++) {
            Row row = sheet.getRow(i);
            list.add(row.getCell(1).getStringCellValue());
        }
        return list;
    }
}

