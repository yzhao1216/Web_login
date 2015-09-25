package web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by EYlee on 7/28/15.
 */
@WebServlet(name = "CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("checkcode...");
        /*
        一 绘图
        1.
         */
        //step1 创建内存映像对象（画布）
        BufferedImage image = new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);
        //step2 获得画笔
        Graphics g = image.getGraphics();
        //step3 给笔设置颜色
        g.setColor(new Color(255, 255, 255));
        //step4 设置背景颜色
        g.fillRect(5, 8, 10, 20);
        //step5 设置前景颜色
        Random r = new Random();
        g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        //step6 绘图

        String number = getNumber(5);
        //将验证码绑定到session对象上
        HttpSession session = request.getSession();
        //绑定的是字符串 而发给用户的才是图片
        session.setAttribute("number",number);
        g.setFont(new Font(null, Font.ITALIC, 20));
        //String number = r.nextInt(10000) + "";
        g.drawString(number, 5, 25);

        //step7 干扰线
        for (int i = 0; i < 6; i++) {
            g.drawLine(r.nextInt(80), r.nextInt(30), r.nextInt(80), r.nextInt(30));
        }


        /*
        二 将图片压缩并发送给浏览器
        write方法：会将原始图片按照指定算法（“jpeg”）压缩 然后输出
         */

        response.setContentType("image/jpeg");
        OutputStream ops = response.getOutputStream();
        javax.imageio.ImageIO.write(image, "jpeg", ops);
        ops.close();
    }
    //返回长度为size A-Z，0-9
    private String getNumber(int size){
        String str = "ABCDEFGHIJKLMNOPQRSTU01234567";
        String number = "";
        Random r = new Random();
        for (int i = 0; i < size; i++) {
           number+= str.charAt(r.nextInt(str.length()));
        }
        return number;
    }


}
