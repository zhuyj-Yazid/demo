package com.example.demo.util;


import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ExportHelper {

	private final static Logger logger = LoggerFactory.getLogger(ExportHelper.class);
	private XSSFWorkbook wb = null;

	private XSSFSheet sheet = null;
	
	/**表头样式设置**/
	private short titleBackGroundColor = HSSFColor.PALE_BLUE.index;//背景色
	private short titleFillPattern = XSSFCellStyle.SOLID_FOREGROUND;//填充模式
	private short titleAlignment = XSSFCellStyle.ALIGN_CENTER;// 设置单元格居中对齐
	private short titleVerticalAlignment = XSSFCellStyle.VERTICAL_CENTER;// // 设置单元格垂直居中对齐
	private boolean titleWrapText = true;// 创建单元格内容显示不下时自动换行
	private short titleBoldweight = XSSFFont.BOLDWEIGHT_BOLD;// 设置字体加粗
	private String titleFontName = "宋体";
	private short titleFontHeight = (short) 200;
	
	private short titleBorderLeft = XSSFCellStyle.BORDER_THIN;// 设置单元格边框为细线条
	private short titleBorderBottom = XSSFCellStyle.BORDER_THIN;// 设置单元格边框为细线条
	private short titleBorderRight = XSSFCellStyle.BORDER_THIN;// 设置单元格边框为细线条
	private short titleBorderTop = XSSFCellStyle.BORDER_THIN;// 设置单元格边框为细线条
				
	/**表体样式设置**/
	private short bodyAlignment = XSSFCellStyle.ALIGN_CENTER;
	private short bodyVerticalAlignment = XSSFCellStyle.VERTICAL_CENTER;// // 设置单元格垂直居中对齐
	private boolean bodyWrapText = true;// 创建单元格内容显示不下时自动换行
	private short bodyBoldweight = XSSFFont.BOLDWEIGHT_BOLD;// 设置字体加粗
	private String bodyFontName = "宋体";
	private short bodyFontHeight = (short) 200;
	private short bodyBorderLeft = XSSFCellStyle.BORDER_THIN;
	private short bodyBorderBottom = XSSFCellStyle.BORDER_THIN;
	private short bodyBorderRight = XSSFCellStyle.BORDER_THIN;
	private short bodyBorderTop = XSSFCellStyle.BORDER_THIN;		 
  
		 
	/**
	 * 获取excel工作簿		
	 * @param contentList 数据内容
	 * @param sheetName   sheet名称
	 * @return
	 * @throws Exception
	 */
	public XSSFWorkbook ExportExcel(List<Map<String,Object>> contentList, String sheetName) throws Exception{
		this.wb = writeExcel(contentList, sheetName);
		return wb;
	}
	
	/**
	 * 数据填充
	 * @param contentList
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	private XSSFWorkbook writeExcel(List<Map<String,Object>> contentList, String sheetName) throws Exception{
		if (contentList == null || contentList.isEmpty()) {
			return null;
		}
		XSSFWorkbook workBook = new XSSFWorkbook();
		try {
			// 在workbook中添加一个sheet,对应Excel文件中的sheet
			XSSFSheet sheet = workBook.createSheet(sheetName);
			this.wb = workBook;
			this.sheet = sheet;
			XSSFCellStyle headStyle = getHeadStyle();
			XSSFCellStyle bodyStyle = getBodyStyle();
			// 构建表头
			XSSFRow headRow = sheet.createRow(0);
			XSSFCell cell;
			int i=0;
			for (Map.Entry<String, Object> entry : contentList.get(0).entrySet()) {
				cell = headRow.createCell(i);
				cell.setCellStyle(headStyle);
				cell.setCellValue(entry.getKey());
				i++;
			}
			// 构建表体数据
			int a = 1;// 开始行数
			Field[] fields;// 主体数据
			for (Map<String, Object> o : contentList) {
				XSSFRow bodyRow = sheet.createRow(a);
				//fields =
						o.getClass().getDeclaredFields();
				int b = 0;// 开始列
				for (Map.Entry<String, Object> entry : o.entrySet()) {
					Object obj = entry.getValue();
					cell = bodyRow.createCell(b);
					cell.setCellStyle(bodyStyle);
					cell.setCellValue(obj == null ? "" : obj.toString());
					b++;
				}
				a++;
			}
			return workBook;
		} catch (Exception e) {
			logger.error("writeExcel ", "Exception ", e);
			logger.error(e.getMessage(),e);
			return null;
		} 
	}

	/**
	 * 合并单元格后给合并后的单元格加边框
	 * 
	 * @param region
	 * @param cs
	 */
	@SuppressWarnings("unused")
	private void setRegionStyle(CellRangeAddress region, XSSFCellStyle cs) {

		int toprowNum = region.getFirstRow();
		for (int i = toprowNum; i <= region.getLastRow(); i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++) {
				XSSFCell cell = row.getCell(j);// XSSFCellUtil.getCell(row,
				cell.setCellStyle(cs);
			}
		}
	}

	/**
	 * 设置表头的单元格样式
	 * 
	 * @return
	 */
	private XSSFCellStyle getHeadStyle() {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格的背景颜色为淡蓝色
		cellStyle.setFillForegroundColor(this.titleBackGroundColor);
		cellStyle.setFillPattern(this.titleFillPattern);
		// 设置单元格居中对齐
		cellStyle.setAlignment(this.titleAlignment);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(this.titleVerticalAlignment);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(this.titleWrapText);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(this.titleBoldweight);
		font.setFontName(this.titleFontName);
		font.setFontHeight(this.titleFontHeight);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(this.titleBorderLeft);
		cellStyle.setBorderBottom(this.titleBorderBottom);
		cellStyle.setBorderRight(this.titleBorderRight);
		cellStyle.setBorderTop(this.titleBorderTop);
		return cellStyle;
	}

	/**
	 * 设置表体的单元格样式
	 * 
	 * @return
	 */
	private XSSFCellStyle getBodyStyle() {
		// 创建单元格样式
		XSSFCellStyle cellStyle = wb.createCellStyle();
		// 设置单元格居中对齐
		cellStyle.setAlignment(this.bodyAlignment);
		// 设置单元格垂直居中对齐
		cellStyle.setVerticalAlignment(this.bodyVerticalAlignment);
		// 创建单元格内容显示不下时自动换行
		cellStyle.setWrapText(this.bodyWrapText);
		// 设置单元格字体样式
		XSSFFont font = wb.createFont();
		// 设置字体加粗
		font.setBoldweight(this.bodyBoldweight);
		font.setFontName(this.bodyFontName);
		font.setFontHeight(this.bodyFontHeight);
		cellStyle.setFont(font);
		// 设置单元格边框为细线条
		cellStyle.setBorderLeft(this.bodyBorderLeft);
		cellStyle.setBorderBottom(this.bodyBorderBottom);
		cellStyle.setBorderRight(this.bodyBorderRight);
		cellStyle.setBorderTop(this.bodyBorderTop);
		return cellStyle;
	}
	
	public XSSFWorkbook getWb() {
		return wb;
	}

	public void setWb(XSSFWorkbook wb) {
		this.wb = wb;
	}

	public XSSFSheet getSheet() {
		return sheet;
	}

	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}

	public short getTitleBackGroundColor() {
		return titleBackGroundColor;
	}

	public void setTitleBackGroundColor(short titleBackGroundColor) {
		this.titleBackGroundColor = titleBackGroundColor;
	}

	public short getTitleFillPattern() {
		return titleFillPattern;
	}

	public void setTitleFillPattern(short titleFillPattern) {
		this.titleFillPattern = titleFillPattern;
	}

	public short getTitleAlignment() {
		return titleAlignment;
	}

	public void setTitleAlignment(short titleAlignment) {
		this.titleAlignment = titleAlignment;
	}

	public short getTitleVerticalAlignment() {
		return titleVerticalAlignment;
	}

	public void setTitleVerticalAlignment(short titleVerticalAlignment) {
		this.titleVerticalAlignment = titleVerticalAlignment;
	}

	public boolean isTitleWrapText() {
		return titleWrapText;
	}

	public void setTitleWrapText(boolean titleWrapText) {
		this.titleWrapText = titleWrapText;
	}

	public short getTitleBoldweight() {
		return titleBoldweight;
	}

	public void setTitleBoldweight(short titleBoldweight) {
		this.titleBoldweight = titleBoldweight;
	}

	public String getTitleFontName() {
		return titleFontName;
	}

	public void setTitleFontName(String titleFontName) {
		this.titleFontName = titleFontName;
	}

	public short getTitleFontHeight() {
		return titleFontHeight;
	}

	public void setTitleFontHeight(short titleFontHeight) {
		this.titleFontHeight = titleFontHeight;
	}

	public short getTitleBorderLeft() {
		return titleBorderLeft;
	}

	public void setTitleBorderLeft(short titleBorderLeft) {
		this.titleBorderLeft = titleBorderLeft;
	}

	public short getTitleBorderBottom() {
		return titleBorderBottom;
	}

	public void setTitleBorderBottom(short titleBorderBottom) {
		this.titleBorderBottom = titleBorderBottom;
	}

	public short getTitleBorderRight() {
		return titleBorderRight;
	}

	public void setTitleBorderRight(short titleBorderRight) {
		this.titleBorderRight = titleBorderRight;
	}

	public short getTitleBorderTop() {
		return titleBorderTop;
	}

	public void setTitleBorderTop(short titleBorderTop) {
		this.titleBorderTop = titleBorderTop;
	}

	public short getBodyAlignment() {
		return bodyAlignment;
	}

	public void setBodyAlignment(short bodyAlignment) {
		this.bodyAlignment = bodyAlignment;
	}

	public short getBodyVerticalAlignment() {
		return bodyVerticalAlignment;
	}

	public void setBodyVerticalAlignment(short bodyVerticalAlignment) {
		this.bodyVerticalAlignment = bodyVerticalAlignment;
	}

	public boolean isBodyWrapText() {
		return bodyWrapText;
	}

	public void setBodyWrapText(boolean bodyWrapText) {
		this.bodyWrapText = bodyWrapText;
	}

	public short getBodyBoldweight() {
		return bodyBoldweight;
	}

	public void setBodyBoldweight(short bodyBoldweight) {
		this.bodyBoldweight = bodyBoldweight;
	}

	public String getBodyFontName() {
		return bodyFontName;
	}

	public void setBodyFontName(String bodyFontName) {
		this.bodyFontName = bodyFontName;
	}

	public short getBodyFontHeight() {
		return bodyFontHeight;
	}

	public void setBodyFontHeight(short bodyFontHeight) {
		this.bodyFontHeight = bodyFontHeight;
	}

	public short getBodyBorderLeft() {
		return bodyBorderLeft;
	}

	public void setBodyBorderLeft(short bodyBorderLeft) {
		this.bodyBorderLeft = bodyBorderLeft;
	}

	public short getBodyBorderBottom() {
		return bodyBorderBottom;
	}

	public void setBodyBorderBottom(short bodyBorderBottom) {
		this.bodyBorderBottom = bodyBorderBottom;
	}

	public short getBodyBorderRight() {
		return bodyBorderRight;
	}

	public void setBodyBorderRight(short bodyBorderRight) {
		this.bodyBorderRight = bodyBorderRight;
	}

	public short getBodyBorderTop() {
		return bodyBorderTop;
	}

	public void setBodyBorderTop(short bodyBorderTop) {
		this.bodyBorderTop = bodyBorderTop;
	}

}
