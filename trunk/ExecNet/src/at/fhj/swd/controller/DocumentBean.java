package at.fhj.swd.controller;

import java.util.Collection;
import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;
import at.fhj.swd.domain.User;

public class DocumentBean {

    private Date datecreated;
    private String name;
    private String url;
    private boolean ispublic = true;

    private Collection<Community> communities;

    private IRuntimeContext _rt;
    private IDataContext<Document> _dc;

    public DocumentBean() {
        this._rt = Application.getInstance().getRuntime();
        this._dc = Application.getInstance().getDocumentContext();
    }

    public String addNow() {
        User _u = _rt.getCurrentUser();

        Document _new = new Document();
        _new.setOwner(_u);
        _new.setPublic(this.isPublic());
        _new.setUrl(this.getUrl());

        try {
            if (_dc.create(_new)) {
                for (Community c : this.getCommunities()) {
                    c.addDocument(_new);
                }
                return "document-added";
            } else {
                return "documentadding-failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "documentadding-failed";
        }
    }

    public Date getDateCreated() {
        return datecreated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPublic() {
        return ispublic;
    }

    public void setPublic(boolean ispublic) {
        this.ispublic = ispublic;
    }

    public Collection<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Collection<Community> communities) {
        this.communities = communities;
    }
}
