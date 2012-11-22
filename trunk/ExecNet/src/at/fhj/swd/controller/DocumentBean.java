package at.fhj.swd.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.FileUploadEvent;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;
import at.fhj.swd.domain.User;

public class DocumentBean {

    private Date _datecreated;
    private String _name;
    private String _url;
    private boolean _ispublic = true;

    private Collection<Community> _communities;
    private IRuntimeContext _rt;
    private IDataContext<Document> _dc;

    protected static final Logger logger = Logger.getLogger(DocumentBean.class.getName());

    public DocumentBean() {
        this._rt = Application.getInstance().getRuntime();
        this._dc = Application.getInstance().getDocumentContext();
    }


    public Collection<Document> getAll() {
        try {
            return _dc.readAll(Document.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public Date getDateCreated() {
        return _datecreated;
    }

    public void setDateCreated(Date _datecreated) {
        this._datecreated = _datecreated;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        this._url = url;
    }

    public boolean isPublic() {
        return _ispublic;
    }

    public Boolean delete(Long id) {
        try {
            Document d = _dc.readOne(id, Document.class);
            if (_dc.delete(d) && deleteFile(d.getUrl())) {

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public void setPublic(boolean ispublic) {
        this._ispublic = ispublic;
    }

    public Collection<Community> getCommunities() {
        return _communities;
    }

    public void setCommunities(Collection<Community> communities) {
        this._communities = communities;
    }

    public void upload(FileUploadEvent event) {

        User _u = _rt.getCurrentUser();
        setName(event.getFile().getFileName());
        // set empty url -> filename = Document.id
        setUrl("");
        Date CreationTime = Calendar.getInstance().getTime();
        setDateCreated(CreationTime);

        try {

            Document _new = new Document();
            _new.setName(this.getName());
            _new.setDateCreated(this.getDateCreated());
            _new.setPublic(this.isPublic());
            _new.setUrl(this.getUrl());
            _new.setOwner(_u);
            _dc.create(_new);

            // getting file extension
            String Ext = "";
            String[] TempArray = this.getName().split("\\.");
            if (TempArray.length > 1) {
                Ext = "." + TempArray[TempArray.length - 1];
            }

            // setting url with Document.id as filename
            setUrl(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadDirectory")
                + _new.getId().toString() + Ext);


            _new.setUrl(this.getUrl());
            _new = _dc.update(_new);


            copyFile(this.getName(), event.getFile().getInputstream());

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void copyFile(String fileName, InputStream in) {

        try {


            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(_url));

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public boolean deleteFile(String file) {
        File f = new File(file);
        boolean bRet = f.delete();

        if (bRet) {
            logger.info("file deleted");
        } else {
            logger.error("file deletion failed");
        }

        return bRet;

    }

}
