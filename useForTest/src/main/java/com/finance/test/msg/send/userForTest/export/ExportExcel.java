package com.finance.test.msg.send.userForTest.export;

import com.finance.test.msg.send.facade.response.UserRespDto;
import com.finance.test.msg.send.manager.UserManager;
import com.finance.test.msg.send.model.UserInfo;
import com.finance.test.msg.send.util.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/8/7 ProjectName:test Version:
 */
@Service
@Slf4j
@Controller
public class ExportExcel {

    @Autowired
    private UserManager userManager;
    @Autowired
    private Mapper dozerMap;
    @Value("${user_filepath}")
    protected String user_filepath;

    /**
     * 配合前端 excel.jsp演示请求
     * 导出excel
     */
    @RequestMapping("exporyExcel")
    @ResponseBody
    public void exporyExcel(HttpServletResponse response, int id) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/x-download");
        String resourcePath = WebApplicationContext.class.getResource("/user").getPath();
        String ofPath = resourcePath + "/" + user_filepath;
//        String newPath = ofPath.replaceFirst("/", "");
        try {
            InputStream in = new FileInputStream(new File(ofPath));
            org.apache.poi.ss.usermodel.Workbook wb = WorkbookFactory.create(in);
//            Workbook wb = this.create(in);
//            Workbook wb = new XSSFWorkbook(in);
            org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);
//            Sheet sheet = wb.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            CellStyle cellStyle = sheet.getRow(1).getCell(2).getCellStyle();
            String fileName = cell.getStringCellValue() + ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

            List<UserInfo> list = userManager.queryAll();
            for (int i = 0; i < list.size(); i++) {
                UserRespDto resp = dozerMap.map(list.get(i), UserRespDto.class);
                Row row1 = sheet.createRow(i + 2);
                Cell cel = row1.createCell(0);
                cel.setCellValue(i + 1);
                cel.setCellStyle(cellStyle);

                cel = row1.createCell(1);
                cel.setCellValue(resp.getUserName());
                cel.setCellStyle(cellStyle);

                cel = row1.createCell(2);
                cel.setCellValue(resp.getPassword());
                cel.setCellStyle(cellStyle);

                cel = row1.createCell(3);
                cel.setCellValue(resp.getEmail());
                cel.setCellStyle(cellStyle);

                cel = row1.createCell(4);
                cel.setCellValue("ss");
                cel.setCellStyle(cellStyle);
            }
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
        } catch (InvalidFormatException e) {
            log.error("格式错误");
        } catch (FileNotFoundException e) {
            log.error("导出excel文件：文件路径错误");
        } catch (IOException e) {
            log.error("错误，e:{}", e);
        }
    }

    public static Workbook create(InputStream inp) throws IOException, InvalidFormatException {
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if (POIFSFileSystem.hasPOIFSHeader(inp)) {
            return new HSSFWorkbook(inp);
        }
        if (POIXMLDocument.hasOOXMLHeader(inp)) {
            return new XSSFWorkbook(OPCPackage.open(inp));
        }
        throw new IllegalArgumentException("你的excel版本目前poi解析不了");
    }
}
