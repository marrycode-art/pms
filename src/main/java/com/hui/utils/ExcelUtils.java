package com.hui.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class ExcelUtils {

    public static String parseValueToString(Cell cell){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String result = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_BLANK:
                result = "";
                break;
            case Cell.CELL_TYPE_STRING:
                result = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)){
                    String format = simpleDateFormat.format(cell.getNumericCellValue());
                    result = String.valueOf(format);
                }else {
                    BigDecimal bigDecimal = new BigDecimal(String.valueOf(cell.getNumericCellValue()));
                    result = bigDecimal.toPlainString();
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                result = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result = String.valueOf(cell.getBooleanCellValue());
                break;
            default:
                result = null;
                break;
        }
        return result;
    }
}
