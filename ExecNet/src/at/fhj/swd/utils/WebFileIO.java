package at.fhj.swd.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.richfaces.model.UploadedFile;

import at.fhj.swd.controller.DocumentBean;


public class WebFileIO {

    protected static final Logger logger = Logger.getLogger(DocumentBean.class.getName());

    public static void copyFile(UploadedFile _file, String destination) {

        try {

            // write the inputStream to a FileOutputStream
            InputStream in = _file.getInputStream();
            OutputStream out = new FileOutputStream(new File(destination));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            logger.error(WebFileIO.class.getSimpleName(), e);
        }
    }

    public static boolean deleteFile(String file) {

        boolean bRet = false;
        try {
            File f = new File(file);
            bRet = f.delete();
        } catch (Exception e) {
            logger.error(WebFileIO.class.getSimpleName(), e);
        }

        return bRet;
    }

    public static void writeOutContent(final HttpServletResponse res, final File content, String name) {
        if (content == null) {
            return;
        }
        try {
            // res.setHeader("Pragma", "no-cache");
            res.setDateHeader("Expires", 0);
            res.setHeader("Content-Disposition", "attachment; filename=\"" + name + "\"");
            ServletContext servletContext = (ServletContext)FacesContext.getCurrentInstance().getExternalContext()
                .getContext();
            String contentType = servletContext.getMimeType(content.getName());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
            res.setContentType(contentType);
            FileInputStream fis = new FileInputStream(content);
            ServletOutputStream os = res.getOutputStream();
            int length = 0;
            int data = fis.read();
            while (data != -1) {
                length += 1;
                os.write(data);
                data = fis.read();
            }
            fis.close();
            res.setContentLength(length);
            os.flush();
            os.close();
        } catch (final IOException e) {
            logger.error(WebFileIO.class.getSimpleName(), e);
        }
    }

}
