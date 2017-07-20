package com.finance.test.msg.send.servlet;

import com.finance.test.msg.send.captcha.VerifyCodeUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 描述：
 * <p>
 * #
 * </p>
 * User: guanhc Date: 2017/6/30 ProjectName:test Version:
 */
@Service
public class ImageCaptchaServlet extends HttpServlet {

    private static final long serialVersionUID = 9002600596623752111L;

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //存入会话session
        HttpSession session = request.getSession(true);
        session.setAttribute("rand", verifyCode.toLowerCase());
        System.out.println(session.getId()+"java");
        //生成图片
        int w = 200, h = 80;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }
}
