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


    public Document createDocument(String name, List<Community> communities, User owner, String uploadDirectory) {

        try {

            Document _new = new Document();

            _new.setName(name);
            _new.setUrl("");

            Date CreationTime = Calendar.getInstance().getTime();
            _new.setDateCreated(CreationTime);
            _new.setPublic(true);
            _new.setOwner(owner);
            _dc.create(_new);

            for (Community c : communities) {

                // System.out.println(c.toString());
                Community _tc = _cc.readOne(c.getId(), Community.class);
                _new.addCommunity(_tc);
            }

            // getting file extension
            String ext = "";
            String[] TempArray = _new.getName().split("\\.");
            if (TempArray.length > 1) {
                ext = "." + TempArray[TempArray.length - 1];
            }

            // setting url with Document.id as filename
            _new.setUrl(uploadDirectory + _new.getId().toString() + ext);

            _new = _dc.update(_new);


            return _new;
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return null;
        }
    }

    public void upload(UploadedFile _file, List<Community> communities) {

        try {

            String _URL = createDocument(_file.getName(), communities, _rt.getCurrentUser(),
                FacesContext.getCurrentInstance().getExternalContext().getInitParameter("uploadDirectory")).getUrl();

            WebFileIO.copyFile(_file, _URL);

        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
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

            if (_dc.delete(d) && WebFileIO.deleteFile(d.getUrl())) {

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.error(getClass().getSimpleName(), e);
            return false;
        }
    }

    // public boolean addCommunity(Document document, Community community) {
    // try {
    // User _newdoc = this.getRuntimeContext().getCurrentUser();
    //
    // return true;
    // } catch (Exception e) {
    // logger.error(getClass().getSimpleName(), e);
    // return false;
    // }
    // }
    //
    // public boolean addCommunites(Document document, Collection<Community> communites) {
    // try {
    // User _newdoc = this.getRuntimeContext().getCurrentUser();
    //
    // return true;
    // } catch (Exception e) {
    // logger.error(getClass().getSimpleName(), e);
    // return false;
    // }
    // }
    //
    // public boolean removeCommunity(Document document, Community community) {
    // try {
    //
    // return true;
    // } catch (Exception e) {
    // logger.error(getClass().getSimpleName(), e);
    // return false;
    // }
    // }
    //
    // public boolean removeCommunites(Document document) {
    // try {
    //
    //
    // return true;
    // } catch (Exception e) {
    // logger.error(getClass().getSimpleName(), e);
    // return false;
    // }
    // }

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
