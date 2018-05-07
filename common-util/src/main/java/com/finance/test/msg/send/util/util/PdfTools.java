package com.finance.test.msg.send.util.util;

import com.finance.test.msg.send.controller.dto.UserDto;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.Field;

/**
 * PDF文件工具类
 */
@Slf4j
public class PdfTools {

    // sonar check
    private PdfTools() {
    }

    /**
     * 获取pdf(UserDto中的字段与pdf中的变量应相同-方便映射值)
     * 需要用到工具 Adobe Acrobat DC
     * @param authorizationDTO
     * @return
     * @throws Exception
     */
    public static byte[] getAuthorizationPdf(UserDto authorizationDTO,byte[] imageByte) throws Exception {
        String authorizationPdfTemplate = ResourceUtils.getFile(
                ResourceUtils.CLASSPATH_URL_PREFIX + "pdf/zhonganAuth.pdf").getAbsolutePath();
        PdfReader reader = new PdfReader(authorizationPdfTemplate);
        //生成pdf并插入图片签名
        return authorizationPdfTemplate(reader, authorizationDTO, imageByte);
    }


    /**
     * 获取默认的空白图片
     * @return
     */
    public static byte[] getDefaultImage() {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            File file = ResourceUtils.getFile(
                    ResourceUtils.CLASSPATH_URL_PREFIX + "pdf/image.jpg");
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            return bos.toByteArray();
        }
        catch (Exception e) {
            log.error("读取默认图片发生异常 {}", e);
            return null;
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
                if (null != bos) {
                    bos.close();
                }
            } catch (Exception e) {
                log.error("读取默认图片发生异常 {}", e);
            }
        }
    }

    /**
     * 生成授权书PDF文件
     * @param reader
     * @throws Exception
     */
    public static byte[] authorizationPdfTemplate(PdfReader reader, UserDto authorizationDTO, byte[] imageByte) {
        log.info("开始生成授权书PDF模版文件");
        PdfStamper ps = null;
        ByteArrayOutputStream bos = null;
        try {
            //生成输出流
            bos = new ByteArrayOutputStream();
            ps = new PdfStamper(reader, bos);
            //支持中文字体
            BaseFont bf = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // 暂时注释 2017-6-22 luwanchuan Font fontChinese = new Font(bf, 12, Font.NORMAL)
            //获取pdf模板中的文本域
            AcroFields s =  ps.getAcroFields();
            //设置文本域表单的字体
            // 对于模板要显中文的，在此处设置字体比在pdf模板中设置表单字体的好处：
            //1.模板文件的大小不变；2.字体格式满足中文要求
            Field[] fields = authorizationDTO.getClass().getDeclaredFields();
            for(Field field :fields){
                s.setFieldProperty(field.getName(), "textfont", bf, null);
                field.setAccessible(true);
            }
            // 设置需要填充的值
            //利用反射设置每个属性值
            reflectValue(s, fields, authorizationDTO);
            s.setField("toggle_1","On");
            ps.setFormFlattening(true); // 这句不能少

            PdfContentByte over = ps.getOverContent(1);//设置在第几页打印印章
            Image img = Image.getInstance(imageByte);
            img.setAlignment(1);
            img.scaleAbsolute(80,40);//控制图片大小
            img.setAbsolutePosition(430,270);//控制图片位置
            over.addImage(img);
            ps.close();
            return bos.toByteArray();
        } catch (Exception e) {
            log.error("写入PDF文件异常，异常信息：{}", e.getMessage());
            return null;
        }finally{
            try {
                if(ps != null){
                    ps.close();
                }

                if (bos != null) {
                    bos.close();
                }

                if (reader != null) {
                    reader.close();
                }

            } catch (Exception e) {
                log.error("写入PDF文件异常，异常信息：{}", e.getMessage());
            }
            log.info("生成PDF文件结束");
        }
    }




    /**
     * 根据反射将每一个属性名以及对应的字段值设置到pdf
     * @param s
     * @param fields
     * @param clerkDTO
     * @throws Exception
     */
    private static void reflectValue(AcroFields s, Field[] fields, UserDto clerkDTO ) throws Exception {
        for(Field field : fields){
            s.setField(field.getName(), (String)field.get(clerkDTO));
        }
    }

    /**
     * 流写成文件
     * @param bs
     * @throws Exception
     */
    public void byte2File(byte[] bs) throws Exception{
        OutputStream out = new FileOutputStream("D:\\test.pdf");
        InputStream is = new ByteArrayInputStream(bs);
        byte[] buff = new byte[1024];
        int len = 0;
        while((len=is.read(buff))!=-1){
            out.write(buff, 0, len);
        }
        is.close();
        out.close();
    }
}
