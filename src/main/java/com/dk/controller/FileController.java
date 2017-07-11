package com.dk.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dk.object.EmployInfo;
import com.dk.result.Result;
import com.dk.service.EmployService;


@Controller
@RequestMapping("file")
//@Transactional
public class FileController {
	@Resource
	private EmployService server;
	
//	@Transactional(rollbackFor={Exception.class},readOnly=false)
	@RequestMapping("uploadfile.ll")
	@ResponseBody
	public Result uploadFile(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Result result = new Result();
		MultipartHttpServletRequest multiPartRequest = (MultipartHttpServletRequest)request;
		MultipartFile mfile = multiPartRequest.getFile("file");
		String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");
		String filename = mfile.getOriginalFilename();
		try {
			InputStream inputStream = mfile.getInputStream();
			 byte[] b = new byte[1048576];
			 int length = inputStream.read(b);
			 path += "\\" + filename;
			 FileOutputStream outputStream = new FileOutputStream(path);
			 outputStream.write(b, 0, length);
			 inputStream.close();
			 outputStream.close();
			 
			 result = readExcel(path);
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //代码执行事务回滚  
		}
		
		return result;
	}
	
	@SuppressWarnings("resource")
	private Result readExcel(String path){
		Result result = new Result();
		if(!(".xlsx".equals(path.substring(path.lastIndexOf("."))))&&!(".xls".equals(path.substring(path.lastIndexOf("."))))){
			result.setStates(false);
			result.setMessage("文件格式错误");
			return result;
		}
		
		List<List<String>> infos = new ArrayList<List<String>>();
		InputStream is = null;
		try {
			is = new FileInputStream(path);
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			System.out.println(sheet.getLastRowNum());
			for(int i=1;i<=sheet.getLastRowNum();i++){
				Row row = sheet.getRow(i);
				int minCell = row.getFirstCellNum();
				int maxCell = row.getLastCellNum();
				List<String> rowList = new ArrayList<String>();
				for(int j=minCell;j<maxCell;j++){
					Cell cell = row.getCell(j);
					if(cell==null){
						rowList.add("");
					}else{
						rowList.add(cell.toString());
					}
					
				}
				infos.add(rowList);
			}
			List<EmployInfo> eInfos = new ArrayList<EmployInfo>();
			if(infos.size()!=0){
				for(List<String> lists:infos){
					EmployInfo info = new EmployInfo(lists.get(0),lists.get(1),lists.get(2),lists.get(3),lists.get(4));
//					result = server.addInfo(info);
					eInfos.add(info);
//					if(!result.isStates()){
//						result.setStates(false);
//						result.setMessage(lists.get(0)+"导入失败，请检查文件数据");
//						return result;
//					}
				}
			}
			if(eInfos!=null&&eInfos.size()!=0){
				result = server.addFileInfo(eInfos);
			}else{
				result.setStates(false);
				result.setMessage("文件内容为空");
			}
//			result = server.addFileInfo(infos);
//			result.setStates(true);
//			result.setMessage("导入成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			result.setStates(false);
//			result.setMessage("导入失败,请检查文件数据");
			e.printStackTrace();
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
	
//	private void readExcel(String path) throws Exception{
////		Result result = new Result();
////		if(!(".xlsx".equals(path.substring(path.lastIndexOf("."))))&&!(".xls".equals(path.substring(path.lastIndexOf("."))))){
////			result.setStates(false);
////			result.setMessage("文件格式错误");
////			return;
////		}
//		
//		List<List<String>> infos = new ArrayList<List<String>>();
//		InputStream is = null;
////		try {
//			is = new FileInputStream(path);
//			Workbook workbook = new XSSFWorkbook(is);
//			Sheet sheet = workbook.getSheetAt(0);
//			System.out.println(sheet.getLastRowNum());
//			for(int i=1;i<=sheet.getLastRowNum();i++){
//				Row row = sheet.getRow(i);
//				int minCell = row.getFirstCellNum();
//				int maxCell = row.getLastCellNum();
//				List<String> rowList = new ArrayList<String>();
//				for(int j=minCell;j<maxCell;j++){
//					Cell cell = row.getCell(j);
//					if(cell==null){
//						rowList.add("");
//					}else{
//						rowList.add(cell.toString());
//					}
//					
//				}
//				infos.add(rowList);
////			}
//			if(infos.size()!=0){
//				for(List<String> lists:infos){
//					EmployInfo info = new EmployInfo(lists.get(0),lists.get(1),lists.get(2),lists.get(3),lists.get(4));
//					server.addFileInfo(info);
////					result = server.addInfo(info);
////					if(!result.isStates()){
////						result.setStates(false);
////						result.setMessage(lists.get(0)+"导入失败，请检查文件数据");
////						return result;
////					}
//				}
//			}
////			result.setStates(true);
////			result.setMessage("导入成功");
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			result.setStates(false);
////			result.setMessage("导入失败,请检查文件数据");
////			e.printStackTrace();
////			throw new Exception(e);
////		} finally{
////			if(is!=null){
////				try {
////					is.close();
////				} catch (IOException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////			}
//		}
//		
//		return;
//	}

}
