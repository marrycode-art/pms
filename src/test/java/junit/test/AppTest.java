package junit.test;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppTest {

    @Test
    public void test02(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.err.println(date);
        String format = simpleDateFormat.format(date);
        System.err.println(format);
    }

    @Test
    public void test01(){
        try {
            //1.获取workbook对象
            Workbook workbook = WorkbookFactory.create(new File("d:\\Desktop\\file.xlsx"));
            //2.从workbook中取出第一个sheet对象
            Sheet sheet = workbook.getSheetAt(0);
            //3.从第一个worksheet中得到第一行
            //Row row = sheet.getRow(0);
            //4.从第一行中的到第一列数据
            //Cell cell = row.getCell(0);

            //遍历每一个sheet表格，得到数据
            for (int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String value = parseValueToString(cell);
                    if (i > 0 && j == 0){
                        value = value.substring(0,value.indexOf("."));
                    }
                    System.err.print(value + "             ");
                }
                System.err.println();
            }

        }catch (Exception exception) {
            exception.printStackTrace();
            System.err.println(exception.getMessage());
        }
    }

    private String parseValueToString(Cell cell){
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
