package cn.edu.ncu.servlet;

import cn.edu.ncu.bean.Employee;
import cn.edu.ncu.bean.Meeting;
import cn.edu.ncu.bean.Option;
import cn.edu.ncu.bean.OptionInfo;
import cn.edu.ncu.service.MeetingService;
import cn.edu.ncu.service.OptionService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sang on 2017/8/23.
 */
public class MeetingDetailsServlet extends HttpServlet {
    private MeetingService meetingService = new MeetingService();
    private OptionService optionService = new OptionService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int empid = ((Employee) req.getSession().getAttribute("loginUser")).getEmployeeid();
        String mid = req.getParameter("mid");
        req.setAttribute("mid", mid);
        String type = req.getParameter("type");

        //参会人员需要填写的信息框
        Meeting meeting = meetingService.getMeetingDetailsByMeetingId(Integer.parseInt(mid));
        List<Employee> emps = meetingService.getEmps();
        List<Option> oplists = meetingService.getOptions(Integer.parseInt(mid));
        req.setAttribute("oplists", oplists);
        req.setAttribute("mt", meeting);
        req.setAttribute("emps", emps);
        req.setAttribute("type", type);

        //从数据库拉取每个参会人员填写的信息
        List<OptionInfo> list = new ArrayList<>();
        for (int i = 0;i<emps.size();i++) {
            Employee emp = emps.get(i);
            int empsid = emp.getEmployeeid();
            OptionInfo info = optionService.getOptionInfoByEmpid(empsid, Integer.parseInt(mid));
            if(info != null) {
                list.add(info);
            } else {
                list.add(new OptionInfo(null,null,null,null));
            }
        }
        //打包成list在jsp循环
        List<OptionInfo> opinfo = list;
        req.setAttribute("opinfolists", opinfo);

        //判断该参会人员信息是否上传
        int status = optionService.justifyupdate(empid, Integer.parseInt(mid));
        if (status == -1) {
            req.setAttribute("error", "Information to be filled in by participants has been uploaded");
        } else {
            req.setAttribute("error", null);
        }
        //用QRCode.jar来生成二维码
//        String url = "http://localhost:8080/meeting/meetingdetail?mid="+mid;//扫出来的url
//        if (url != null && !"".equals(url)) {
//            ServletOutputStream stream = null;
//            try {
//                int width = 200;//图片的宽度
//                int height = 200;//高度
//                stream = resp.getOutputStream();
//                QRCodeWriter writer = new QRCodeWriter();
//                BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
//                MatrixToImageWriter.writeToStream(m, "png", stream);
//            } catch (WriterException e) {
//                e.printStackTrace();
//            } finally {
//                if (stream != null) {
//                    stream.flush();
//                    stream.close();
//                }
//            }
//        }
        //用zxing.jar来生成二维码

        // 定义参数：
        int width = 300; // 图片宽度
        int height = 300; // 图片高度
        String format = "png"; // 图片格式
        String content = "http://localhost:8080/meeting/meetingdetail?mid="+mid; // 二维码内容

        // 定义二维码的参数
        HashMap hints = new HashMap();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        // 生成二维码

        // 1.定义HashMap hints
        // 2.hints调用put函数设置字符集、间距以及纠错度为M
        // 3.最后用MultiformatWriter函数类调用echoed函数并返回一个值 然后写入文件

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 这里路径后面的img.png不可省略，前面是自己选取生成的图片地址
            Path file = new File("E:/IntelliJ/Project location/Meeting/web/images/qrcode/img.png").toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.getRequestDispatcher("/meetingdetails.jsp").forward(req, resp);

    }
}



