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

    public DocumentBean() {
        this._rt = Application.getInstance().getRuntime();
        this._dc = Application.getInstance().getDocumentContext();
    }

    public String addNow() {
        User _u = _rt.getCurrentUser();

        Document _new = new Document();
        _new.setName(this.getName());
        _new.setDateCreated(this.getDateCreated());
        _new.setPublic(this.isPublic());
        _new.setUrl(this.getUrl());
        _new.setOwner(_u);


        try {
            if (_dc.create(_new)) {
                /*
                 * for (Community c : this.getCommunities()) {
                 * c.addDocument(_new);
                 * }
                 */
                return "document-added";
            } else {
                return "documentadding-failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "documentadding-failed";
        }
    }

    public Collection<Document> getAll() {
        try {
            return _dc.readAll(Document.class);
        } catch (Exception e) {
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
            if (_dc.delete(d)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        setName(event.getFile().getFileName());
        setUrl(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadDirectory")
            + event.getFile().getFileName());
        Date date = Calendar.getInstance().getTime();
        setDateCreated(date);


        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
            addNow();
            // RequestContext requestContext = RequestContext.getCurrentInstance();
            // requestContext.update("@all");
        } catch (IOException e) {
            e.printStackTrace();
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
            System.out.println(e.getMessage());
        }
    }
}
