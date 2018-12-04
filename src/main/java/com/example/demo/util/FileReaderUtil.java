package com.example.demo.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.*;

public class FileReaderUtil {

    private final static Logger logger = LoggerFactory.getLogger(FileReaderUtil.class);

    public static void readEXCEL2003(InputStream stream, List<List<Map<String, Object>>> result, String[][] cellNamesArray) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(stream);
        int cellNamesSize = ArrayUtils.getLength(cellNamesArray);
        for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
            if (numSheets >= cellNamesSize) {
                break;
            }
            String[] cellNameArray = cellNamesArray[numSheets];
            int cellNameSize = ArrayUtils.getLength(cellNameArray);
            HSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
            List<Map<String, Object>> list = new ArrayList<>();
            for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                Map<String, Object> rowMap = new HashMap<>();
                HSSFRow aRow = aSheet.getRow(rowNumOfSheet);    // 获得一个行
                if(null == aRow){
                    continue;
                }
                for (short cellNumOfRow = 0; cellNumOfRow <= aRow
                        .getLastCellNum(); cellNumOfRow++) {
                    if (cellNumOfRow >= cellNameSize) {
                        break;
                    }
                    String cellName = cellNameArray[cellNumOfRow];
                    HSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                    String value = convertCell(aCell);
                    rowMap.put(cellName, value);
                }
                list.add(rowMap);
            }
            result.add(list);
        }
    }

    public static void readEXCEL2007(InputStream stream, List<List<Map<String, Object>>> result, String[][] cellNamesArray) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook(stream);
        int cellNamesSize = ArrayUtils.getLength(cellNamesArray);
        for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
            if (numSheets >= cellNamesSize) {
                break;
            }
            String[] cellNameArray = cellNamesArray[numSheets];
            int cellNameSize = ArrayUtils.getLength(cellNameArray);
            XSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
            List<Map<String, Object>> list = new ArrayList<>();
            for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
                Map<String, Object> rowMap = new HashMap<>();
                XSSFRow aRow = aSheet.getRow(rowNumOfSheet);    // 获得一个行
                if(null == aRow){
                    continue;
                }
                for (short cellNumOfRow = 0; cellNumOfRow <= aRow
                        .getLastCellNum(); cellNumOfRow++) {
                    if (cellNumOfRow >= cellNameSize) {
                        break;
                    }
                    String cellName = cellNameArray[cellNumOfRow];
                    XSSFCell aCell = aRow.getCell(cellNumOfRow);// 获得列值
                    String value = convertCell(aCell);
                    rowMap.put(cellName, value);
                }
                list.add(rowMap);
            }
            result.add(list);
        }
    }

    private static String convertCell(Cell cell) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        String cellValue = StringUtils.EMPTY;
        if (cell == null) {
            return cellValue;
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_NUMERIC:
                cellValue = numberFormat.format(cell.getNumericCellValue());
                // 判断是日期类型 避免读取excel文件中的日期格式数据被转换成数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellValue = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
                }
                break;
            case HSSFCell.CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                cellValue = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                cellValue = Boolean.valueOf(cell.getBooleanCellValue()).toString();
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                cellValue = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                cellValue = StringUtils.EMPTY;
        }
        return StringUtils.trim(cellValue);
    }


}
