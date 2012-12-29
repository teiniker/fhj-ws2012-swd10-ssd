package at.fhj.swd.business;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.richfaces.model.UploadedFile;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;
import at.fhj.swd.domain.User;
import at.fhj.swd.utils.WebFileIO;

public class DocumentBO extends ABusinessObject {

    private IRuntimeContext _rt;
    private IDataContext<Document> _dc;
    private IDataContext<Community> _cc;


    public DocumentBO() {
        this._rt = Application.getInstance().getRuntime();
        this._dc = Application.getInstance().getDocumentContext();
        this._cc = Application.getInstance().getCommunityContext();
    }

    public Collection<Document> getAll() {
        try {
            return _dc.readAll(Document.class);
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return null;
        }
    }


    public void upload(UploadedFile _file, List<Community> selectionItems) {

        try {

            User _u = _rt.getCurrentUser();
            Document _new = new Document();
            _new.setName(_file.getName());
            // set empty url -> filename = Document.id
            _new.setUrl("");
            Date CreationTime = Calendar.getInstance().getTime();
            _new.setDateCreated(CreationTime);
            _new.setPublic(true);
            _new.setOwner(_u);
            _dc.create(_new);

            _new = _dc.update(_new);

            for (Community c : selectionItems) {

                System.out.println(c.toString());
                Community _tc = _cc.readOne(c.getId(), Community.class);
                _new.addCommunity(_tc);
            }
            // getting file extension
            String Ext = "";
            String[] TempArray = _new.getName().split("\\.");
            if (TempArray.length > 1) {
                Ext = "." + TempArray[TempArray.length - 1];
            }

            // setting url with Document.id as filename
            _new.setUrl(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadDirectory")
                + _new.getId().toString() + Ext);

            _new.setUrl(_new.getUrl());
            _new = _dc.update(_new);

            WebFileIO.copyFile(_file, _new.getUrl());

        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
        }
    }

    public boolean update(User Document) {
        try {
            User _newdoc = this.getRuntimeContext().getCurrentUser();
            return true;
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return false;
        }
    }

    public boolean remove(long id) {
        try {
            Document d = _dc.readOne(id, Document.class);
            List<Community> cList = new ArrayList<Community>(d.getCommunities());

            if (d.getCommunities() != null) {
                for (Community c : cList) {
                    Community _tc = _cc.readOne(c.getId(), Community.class);

                    _tc.removeDocument(d);
                    _cc.update(_tc);

                    d.removeCommunity(_tc);
                    d = _dc.update(d);
                }
            }

            if (WebFileIO.deleteFile(d.getUrl()) && _dc.delete(d)) {

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return false;
        }
    }

    public boolean addCommunity(Document document, Community community) {
        try {
            User _newdoc = this.getRuntimeContext().getCurrentUser();

            return true;
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return false;
        }
    }

    public boolean addCommunites(Document document, Collection<Community> communites) {
        try {
            User _newdoc = this.getRuntimeContext().getCurrentUser();

            return true;
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return false;
        }
    }

    public boolean removeCommunity(Document document, Community community) {
        try {

            return true;
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return false;
        }
    }

    public boolean removeCommunites(Document document) {
        try {


            return true;
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return false;
        }
    }

    public boolean download(Document doc) {

        try {
            File file = new File(doc.getUrl());
            HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext()
                .getResponse();

            WebFileIO.writeOutContent(response, file, doc.getName());
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return false;
        }
        return true;
    }

}
