package com.etop.management.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * @Description : 导出Excel工具类
 * @author 何利庭
 * @date : 2016/12/27 10:10
 **/
public class ExcelUtil {

    /**
     * @Description : excel导出(第一种方法)
     * @author 何利庭
     * @date : 2016/12/27 14:38
     **/
    public static void createXlsFileWithListZbxb(List<Map<String, Object>> resultList,
                                          List<String> SbContent, List<String> sbFildes, HttpServletResponse response, Integer type) {
        WritableWorkbook book = null;
        OutputStream outStream = null;
        try {

            // 数据填写完毕，进行导出---------------------------------------------------------------------------------------------

            outStream = response.getOutputStream();// 取得输出流
            response.reset();// 清空输出流
            String fileName = changeType(type);
            // response.setContentType("application/msexcel");// 定义输出类型
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(( fileName + ".xls").getBytes(), "iso-8859-1"));
            response.setContentType("octets/stream");// 定义输出类型

            // 打开一个文件的副本，并且指定数据写回到原文件
            book = jxl.Workbook.createWorkbook(outStream);
            // 添加一个工作表
            WritableSheet sheet = book.createSheet(fileName, 0);

            // 设置单元格的文字格式 表头
            WritableFont wf1 = new WritableFont(WritableFont.ARIAL, 15,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);
            // 单元格的格式对象
            WritableCellFormat cellFormat1 = new WritableCellFormat(wf1);
            cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat1
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

            WritableCellFormat cellFormat2 = new WritableCellFormat(wf1);
            cellFormat2.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat2
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            // 设置边框及边框颜色
            // cellFormat1.setBorder(Border.ALL, BorderLineStyle.THIN,
            // jxl.format.Colour.BLACK);

            // ==================================================表头
            // ===============================================列明
            WritableFont wf2 = new WritableFont(WritableFont.COURIER, 11,
                    WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);

            // 单元格的格式对象
            WritableCellFormat cellFormat3 = new WritableCellFormat(wf2);
            // 设置边框及边框颜色
            cellFormat3.setBorder(Border.ALL, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK);
            cellFormat3.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat3
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat3.setWrap(true);
            // 列明======================================================
            // ====================内容=======================================
            // 导出内容
            WritableFont wf3 = new WritableFont(WritableFont.ARIAL, 11,
                    WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                    Colour.BLACK);

            // 单元格的格式对象
            WritableCellFormat cellFormat4 = new WritableCellFormat(wf3);
            // 设置边框及边框颜色
            cellFormat4.setBorder(Border.ALL, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK);
            cellFormat4.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat4
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat4.setWrap(true);


            // 单元格的格式对象
            // 设置边框及边框颜色
            cellFormat4.setBorder(Border.ALL, BorderLineStyle.THIN,
                    jxl.format.Colour.BLACK);
            cellFormat4.setAlignment(jxl.format.Alignment.CENTRE);
            cellFormat4
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat4.setWrap(true);
            // ====================内容=======================================

            sheet.setColumnView(0, 10); // 设置列的宽度
            for (int i = 1; i <= sbFildes.size(); i++) {
                sheet.setColumnView(i, 15); // 设置列的宽度
            }

            sheet.mergeCells(0, 0, sbFildes.size(), 0);// 第一行合并单元格
            // sheet.mergeCells(4, 1, 7, 1);
            sheet.addCell(new Label(0, 0, fileName,
                    cellFormat1));// 第一行表头
            sheet.addCell(new Label(0, 1, "序号", cellFormat3));// 列名
            for (int i = 0; i < sbFildes.size(); i++) {
                sheet.addCell(new Label(i + 1, 1, sbFildes.get(i),
                        cellFormat3));
            }
            int dataRowIndex = 1;
            int i = 1;
            Iterator<Map<String, Object>> it = resultList.iterator();
            while (it.hasNext()) {
                Map<String, Object> m = (Map<String, Object>) it.next();
                sheet.addCell(new Label(0, i + dataRowIndex, Integer
                        .toString(i), cellFormat4));// 序号
                for (int j = 0; j < SbContent.size(); j++) {
                    if (m.get(SbContent.get(j)) != null
                            && !"".equals(m.get(SbContent.get(j)))) {
                        sheet.addCell(new Label(j + 1, i + dataRowIndex, m.get(
                                SbContent.get(j)).toString(), cellFormat4));
                    } else {
                        sheet.addCell(new Label(j + 1, i + dataRowIndex, "",
                                cellFormat4));
                    }

                }
                i = i + 1;
            }

            // 将文件写出来
            book.write();
            //result = ResultType.getSuccess("导出成功");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                book.close();
                outStream.flush();
                outStream.close();
                // response.reset();
            } catch (WriteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }


    /**
     * @Description : excel导出（第二种方法）
     * @author 何利庭
     * @date : 2016/12/27 10:10
     **/
    public  static void export(HttpServletRequest request,HttpServletResponse response,String fileName,List<Map<String,Object>> list, String columnNames[],String keys[]) throws IOException{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ExcelUtil.createWorkBook(fileName,list,keys,columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
		byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
	}

    /**
     * 创建excel文档
     * @param list 数据
     * @param keys list中map的key数组集合
     * @param columnNames excel的列名
     * */
    public static Workbook createWorkBook(String filename,List<Map<String, Object>> list,String []keys,String columnNames[]) {
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(list.get(0).get("sheetName").toString());
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。

        for(int i=0;i<keys.length;i++){
            sheet.setColumnWidth((short)i,(short)(35.7*150));
        }
        // 创建第一行
        Row row = sheet.createRow((short) 0);
        // 创建两种单元格格式
        CellStyle cs = getTitleCellStyle(wb);
        CellStyle cs2 = getBasicCellStyle(wb);

        //设置列名
        for(int i=0;i<columnNames.length;i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(columnNames[i]);
            cell.setCellStyle(cs);
        }
        //设置每行每列的值
        for (short i = 1; i < list.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) i);
            // 在row行上创建一个方格
            for(short j=0;j<keys.length;j++){
                Cell cell = row1.createCell(j);
                cell.setCellValue(list.get(i).get(keys[j]) == null?"": list.get(i).get(keys[j]).toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

    /**
     *
    * @Description: 标题栏单元格格式
    * @author
    * @date
    * @throws
     */
    public static CellStyle getFirstTitleCellStyle(Workbook wb){
    	CellStyle cs = wb.createCellStyle();
        Font f = wb.createFont();
        f.setFontHeightInPoints((short) 15);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
        cs.setFont(f);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        return cs;
    }


    /**
     *
    * @Description: 得到列名单元格格式
    * @author
    * @date
    * @throws
     */
    public static CellStyle getTitleCellStyle(Workbook wb){
    	CellStyle cs = wb.createCellStyle();
    	Font f = wb.createFont();
    	 // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 11);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
     // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setAlignment(CellStyle.ALIGN_CENTER);
        return cs;
    }

    /**
     *
    * @Description: 字体格式
    * @author
    * @date
    * @throws
     */
    public static CellStyle getBasicCellStyle(Workbook wb){
    	CellStyle cs = wb.createCellStyle();
    	Font f = wb.createFont();
    	 // 创建第一种字体样式（用于列名）
    	// 创建第二种字体样式（用于值）
        f.setFontHeightInPoints((short) 11);
        f.setColor(IndexedColors.BLACK.getIndex());
        cs.setFont(f);
        cs.setAlignment(CellStyle.ALIGN_CENTER);//内容居中
        return cs;
    }
    public static String changeType(Integer type){
        String fileName = "";
        if(type == 1) {
            fileName = "园区动态";
        }else if(type == 2){
            fileName = "出租率";
        }else if(type == 3){
            fileName = "欠款统计";
        }else if(type == 4){
            fileName = "费金回收";
        }else if(type == 5){
            fileName = "收支统计";
        }else if(type == 6){
            fileName = "服务统计";
        }else if(type == 7){
            fileName = "客户信息";
        }else if(type == 8){
            fileName = "房间信息";
        }
        return fileName;
    }
}