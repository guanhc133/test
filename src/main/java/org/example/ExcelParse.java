package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

/**
 * 思路是按照Workbook，Sheet，Row，Cell一层一层往下读取。
 */
public class ExcelParse {

    //1，初始化workbook
    public Workbook getReadWorkBook(String filePath) {
        FileInputStream is = null;
        Workbook wk = null;
        try {
            is = new FileInputStream(filePath);
            if (filePath.toLowerCase().endsWith("xls")) {
                wk = new HSSFWorkbook(is);
            } else if (filePath.toLowerCase().endsWith("xlsx")) {
                wk = new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            System.out.println("流异常，error: " + e);
        } finally {
            IOUtils.closeQuietly(is);
        }
        return wk;
    }


}
