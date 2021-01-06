package top.alvinsite.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import top.alvinsite.demo.model.support.Excel;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Alvin
 */
public class ExcelUtils {
    private final static Logger log = LoggerFactory.getLogger(ExcelUtils.class);

    private final static String EXCEL2003 = "xls";
    private final static String EXCEL2007 = "xlsx";
    private final static String FILENAME_MATCHES_REGEX_1 = "^.+\\.(?i)(xls)$";
    private final static String FILENAME_MATCHES_REGEX_2 = "^.+\\.(?i)(xlsx)$";

    private static Workbook getWorkbook(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if (!fileName.matches(FILENAME_MATCHES_REGEX_1) && !fileName.matches(FILENAME_MATCHES_REGEX_2)) {
            log.error("上传文件格式不正确");
        }
        InputStream is = file.getInputStream();
        if (fileName.endsWith(EXCEL2007)) {
            // FileInputStream is = new FileInputStream(new File(path));
            return new XSSFWorkbook(is);
        }
        if (fileName.endsWith(EXCEL2003)) {
            // FileInputStream is = new FileInputStream(new File(path));
            return new HSSFWorkbook(is);
        }
        return null;
    }

    public static <T> List<T> readExcel(String path, Class<T> cls, MultipartFile file){

        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if (!fileName.matches(FILENAME_MATCHES_REGEX_1) && !fileName.matches(FILENAME_MATCHES_REGEX_2)) {
            log.error("上传文件格式不正确");
        }
        List<T> dataList = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = getWorkbook(file);
            if (workbook != null) {
                //类映射  注解 value-->bean columns
                Map<String, List<Field>> classMap = new HashMap<>(16);
                List<Field> fields = Stream.of(cls.getDeclaredFields()).collect(Collectors.toList());
                fields.forEach(
                        field -> {
                            Excel annotation = field.getAnnotation(Excel.class);
                            if (annotation != null) {
                                String value = annotation.name();
                                if (StringUtils.isBlank(value)) {
                                    return;//return起到的作用和continue是相同的 语法
                                }
                                if (!classMap.containsKey(value)) {
                                    classMap.put(value, new ArrayList<>());
                                }
                                field.setAccessible(true);
                                classMap.get(value).add(field);
                            }
                        }
                );
                //索引-->columns
                Map<Integer, List<Field>> reflectionMap = new HashMap<>(16);
                //默认读取第一个sheet
                Sheet sheet = workbook.getSheetAt(0);

                boolean firstRow = true;
                for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    //首行  提取注解
                    if (firstRow) {
                        for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            String cellValue = getCellValue(cell);
                            if (classMap.containsKey(cellValue)) {
                                reflectionMap.put(j, classMap.get(cellValue));
                            }
                        }
                        firstRow = false;
                    } else {
                        //忽略空白行
                        if (row == null) {
                            continue;
                        }
                        try {
                            T t = cls.newInstance();
                            //判断是否为空白行
                            boolean allBlank = true;
                            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                                if (reflectionMap.containsKey(j)) {
                                    Cell cell = row.getCell(j);
                                    String cellValue = getCellValue(cell);
                                    if (StringUtils.isNotBlank(cellValue)) {
                                        allBlank = false;
                                    }
                                    List<Field> fieldList = reflectionMap.get(j);
                                    fieldList.forEach(
                                            x -> {
                                                try {
                                                    handleField(t, cellValue, x);
                                                } catch (IllegalArgumentException e) {
                                                    throw e;
                                                } catch (Exception e) {
                                                    log.error(String.format("reflect field:%s value:%s exception!", x.getName(), cellValue), e);
                                                }
                                            }
                                    );
                                }
                            }
                            if (!allBlank) {
                                dataList.add(t);
                            } else {
                                log.warn(String.format("row:%s is blank ignore!", i));
                            }
                        } catch (IllegalArgumentException e) {
                            throw e;
                        } catch (Exception e) {
                            log.error(String.format("parse row:%s exception!", i), e);
                        }
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            throw e;
        }  catch (Exception e) {
            log.error("parse excel exception!", e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    log.error("parse excel exception!", e);
                }
            }
        }
        return dataList;
    }

    private static <T> void handleField(T t, String value, Field field) throws Exception {
        Class<?> type = field.getType();
        Excel annotation = field.getAnnotation(Excel.class);
        if (annotation.combo().length != 0) {
            List<String> combo = Arrays.asList(annotation.combo());
            if (annotation.isMultiSelect()) {
                String[] temp = value.split(annotation.separator());
                if (!combo.containsAll(Arrays.asList(temp))) {
                    String errorMessage = String.format("%s的值[%s]错误，只能从%s选择", annotation.name(), value, combo);
                    throw new IllegalArgumentException(errorMessage);
                }
            }
            if (!combo.contains(value)) {
                String errorMessage = String.format("%s的值[%s]错误，只能从%s选择", annotation.name(), value, combo);
                throw new IllegalArgumentException(errorMessage);
            }
        }

        if (type == void.class || StringUtils.isBlank(value)) {
            return;
        }
        if (type == Object.class) {
            field.set(t, value);
            //数字类型
        } else if (type.getSuperclass() == null || type.getSuperclass() == Number.class) {
            if (type == int.class || type == Integer.class) {
                field.set(t, NumberUtils.toInt(value));
            } else if (type == long.class || type == Long.class) {
                field.set(t, NumberUtils.toLong(value));
            } else if (type == byte.class || type == Byte.class) {
                field.set(t, NumberUtils.toByte(value));
            } else if (type == short.class || type == Short.class) {
                field.set(t, NumberUtils.toShort(value));
            } else if (type == double.class || type == Double.class) {
                field.set(t, NumberUtils.toDouble(value));
            } else if (type == float.class || type == Float.class) {
                field.set(t, NumberUtils.toFloat(value));
            } else if (type == char.class || type == Character.class) {
                field.set(t, CharUtils.toChar(value));
            } else if (type == boolean.class) {
                field.set(t, BooleanUtils.toBoolean(value));
            } else if (type == BigDecimal.class) {
                field.set(t, new BigDecimal(value));
            }
        } else if (type == Boolean.class) {
            field.set(t, BooleanUtils.toBoolean(value));
        } else if (type == Date.class) {
            //
            field.set(t, value);
        } else if (type == String.class) {
            field.set(t, value);
        } else {
            Constructor<?> constructor = type.getConstructor(String.class);
            field.set(t, constructor.newInstance(value));
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            } else {
                return new BigDecimal(cell.getNumericCellValue()).toString();
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return StringUtils.trimToEmpty(cell.getStringCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return StringUtils.trimToEmpty(cell.getCellFormula());
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }

    }

    public static <T> void writeExcel(HttpServletResponse response, List<T> dataList, Class<T> cls){
        Field[] fields = cls.getDeclaredFields();

        List<Field> fieldList = Arrays.stream(fields)
                .filter(field -> {
                    Excel annotation = field.getAnnotation(Excel.class);
                    if (annotation != null && annotation.col() > 0) {
                        field.setAccessible(true);
                        return true;
                    }
                    return false;
                }).sorted(Comparator.comparing(field -> {
                    int col = 0;
                    Excel annotation = field.getAnnotation(Excel.class);
                    if (annotation != null) {
                        col = annotation.col();
                    }
                    return col;
                })).collect(Collectors.toList());

        Workbook wb = new XSSFWorkbook();

        writeSheet(wb, "sheet1", dataList, fieldList);

        //浏览器下载excel
        buildExcelDocument("abbot.xlsx",wb,response);
        //生成excel文件
//        buildExcelFile(".\\default.xlsx",wb);
    }

    public static <T> void writeSheet(Workbook workbook, String sheetName, List<T> dataList, List<Field> fieldList) {
        Sheet sheet = workbook.createSheet(sheetName);
        AtomicInteger ai = new AtomicInteger();
        {
            Row row = sheet.createRow(ai.getAndIncrement());
            AtomicInteger aj = new AtomicInteger();
            //写入头部
            fieldList.forEach(field -> {
                Excel annotation = field.getAnnotation(Excel.class);
                String columnName = "";
                int columnIndex = 0;
                int columnWidth = 20 * 256;
                if (annotation != null) {
                    columnName = annotation.name();
                    columnIndex = annotation.col() - 1;
                    columnWidth = annotation.width() * 256;
                }
                Cell cell = row.createCell(aj.getAndIncrement());

                CellStyle cellStyle = workbook.createCellStyle();
                cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
                cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
                cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

                Font font = workbook.createFont();
                font.setBoldweight(Font.BOLDWEIGHT_NORMAL);
                cellStyle.setFont(font);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(columnName);
                sheet.setColumnWidth(columnIndex, columnWidth);
            });
        }
        if (CollectionUtils.isNotEmpty(dataList)) {
            dataList.forEach(t -> {
                Row row1 = sheet.createRow(ai.getAndIncrement());
                AtomicInteger aj = new AtomicInteger();
                fieldList.forEach(field -> {
                    Class<?> type = field.getType();
                    Object value = "";
                    try {
                        value = field.get(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Cell cell = row1.createCell(aj.getAndIncrement());
                    if (value != null) {
                        if (type == Date.class) {
                            cell.setCellValue(value.toString());
                        } else {
                            cell.setCellValue(value.toString());
                        }
                        cell.setCellValue(value.toString());
                    }
                });
            });
        }
        //冻结窗格
        workbook.getSheet(sheetName).createFreezePane(0, 1, 0, 1);
    }


    public static class Builder{
        private Workbook workbook;
        private HttpServletResponse response;

        public Builder() {
            this.workbook = new XSSFWorkbook();

        }

        public Builder setResponse(HttpServletResponse response) {
            this.response = response;
            return this;
        }

        private static List<Field> getAllField(Class clazz){
            List<Field> fields = new ArrayList<>();
            while (clazz != null){
                fields.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
                clazz = clazz.getSuperclass();
            }
            return fields;
        }

        public <T> Builder addSheet(String sheetName, List<T> dataList, Class<T> cls) {
            List<Field> fieldList = getAllField(cls).stream()
                    .filter(field -> {
                        Excel annotation = field.getAnnotation(Excel.class);
                        if (annotation != null && annotation.col() > 0) {
                            field.setAccessible(true);
                            return true;
                        }
                        return false;
                    }).sorted(Comparator.comparing(field -> {
                        int col = 0;
                        Excel annotation = field.getAnnotation(Excel.class);
                        if (annotation != null) {
                            col = annotation.col();
                        }
                        return col;
                    })).collect(Collectors.toList());
            ExcelUtils.writeSheet(this.workbook, sheetName, dataList, fieldList);
            return this;
        }

        public Workbook build() {
            // buildExcelDocument(excelName, workbook, response);
            return workbook;
        }

        public void save(String filename) {
            buildExcelFile(filename, workbook);
        }

    }


    /**
     * 浏览器下载excel
     * @param fileName 文件名
     * @param wb workbook
     * @param response 请求响应
     */

    public static void buildExcelDocument(String fileName, Workbook wb, HttpServletResponse response){
        try {
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
            response.flushBuffer();
            wb.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * 生成excel文件
     * @param path 生成excel路径
     * @param wb workbook对象
     */
    private static void buildExcelFile(String path, Workbook wb){

        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            wb.write(new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
