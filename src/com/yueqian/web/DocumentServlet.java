package com.yueqian.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yueqian.entity.Document;
import com.yueqian.entity.User;
import com.yueqian.query.DocumentQueryObject;
import com.yueqian.query.UserQueryObject;
import com.yueqian.service.DocumentService;
import com.yueqian.service.impl.DocumentServiceImpl;
import com.yueqian.util.Myutil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/doc")
public class DocumentServlet extends HttpServlet {
    DocumentService documentService=new DocumentServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String crud = req.getParameter("crud");
        String id = req.getParameter("id");
        String file = req.getParameter("file");
        if("list".equals(crud)){
            list(req,resp);
        }else if("delete".equals(crud)){
            delete(req,resp);
        }else if("download".equals(crud)){
            Document document = documentService.get(Integer.valueOf(id));
            String filepath = document.getFilepath();
            System.out.println(filepath);
            resp.setContentType("application/X-msdownload");
            String filename= document.getFilename();
            /*resp.setHeader("Content-Type", this.getServletContext().getMimeType(filename));*/
            String fname="";
            String userAgent=req.getHeader("User-Agent");
            if(userAgent.contains("MSIE")){
                fname= URLEncoder.encode(filename,"utf-8");
            }else {
                fname=new String(filename.getBytes("utf-8"),"ISO8859-1");
            }
            resp.setHeader("Content-Disposition","attachment;filename="+fname);
            Files.copy(Paths.get(filepath),resp.getOutputStream());
          /*  InputStream in=new FileInputStream(filepath);
            ServletOutputStream outputStream = resp.getOutputStream();
            int length=0;
            byte[] bs=new byte[1024];
            while ((length=in.read(bs))>0){
                outputStream.write(bs,0,length);
            }
            in.close();
            outputStream.close();*/
        }
    }
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DocumentQueryObject dqo=new DocumentQueryObject();
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        String title = req.getParameter("title");
        dqo.setTitle(title);
        if (Myutil.haslength(page)) {
            dqo.setCurrentPage(Integer.valueOf(page));
        }
        if(Myutil.haslength(limit)){
            dqo.setPageSize(Integer.valueOf(limit));
        }
        int user = documentService.listCount(dqo);
        List<Document> docs = documentService.listQp(dqo);
        Map<String,Object> map=new HashMap<>();
        map.put("code","0");
        map.put("count",user);
        map.put("data",docs);
        resp.setContentType("application/json;charset=utf-8");
        resp.getWriter().write(JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect));
    }
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Document document = documentService.get(Integer.valueOf(id));
        File file=new File(document.getFilepath());
        if(file.exists()){
            file.delete();
        }
        int count = documentService.delete(Integer.valueOf(id));
        if(count>0){
            resp.getWriter().write("删除成功");
        }else {
            resp.getWriter().write("删除失败");
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.setCharacterEncoding("utf-8");
       resp.setContentType("application/json;charset=utf-8");
        Document document=new Document();
        try {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> list = upload.parseRequest(req);
            for (FileItem fileItem : list) {
                String fieldName = fileItem.getFieldName();
                if(fileItem.isFormField()){
                    String value = fileItem.getString("utf-8");
                    if("title".equals(fieldName)){
                        document.setTitle(value);
                    }
                    if("remark".equals(fieldName)){
                        document.setRemark(value);
                    }
                }else {
                        String path = req.getServletContext().getRealPath("upload");
                        String name = fileItem.getName();
                        String fileName = System.currentTimeMillis()+ fileItem.getName();
                        document.setFilename(name);
                        document.setCreatedate(new Date());
                        document.setFiletype(fileItem.getName().substring(fileItem.getName().lastIndexOf(".")+1));
                        document.setFilepath(path + "/" + fileName);
                        fileItem.write(new File(path, fileName));
                }
            }
            User user = (User) req.getSession().getAttribute("USER_IN_SESSION");
            document.setUser(user);
            Map<String,String> map=new HashMap<>();
            if(Myutil.haslength(document.getFilepath())&&Myutil.haslength(document.getFilename())&&Myutil.haslength(document.getFiletype())){
               if(Myutil.haslength(document.getTitle())){
                   int count = documentService.insert(document);
                   if(count>0){
                       map.put("msg","上传成功");
                       resp.getWriter().write(JSON.toJSONString(map));
                   }else {
                       map.put("msg","上传失败");
                       resp.getWriter().write(JSON.toJSONString(map));
                   }
               }else {
                   map.put("msg","上传失败");
                   resp.getWriter().write(JSON.toJSONString(map));
               }

            }else {
                map.put("msg","请选择上传的文件");
                resp.getWriter().write(JSON.toJSONString(map));
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
