package at.fhj.swd.controller;

import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import at.fhj.swd.business.ActivityBO;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;

public class ActivityPostBean {

    private String entry;
    private ActivityBO _bo;
    private long idCommunity;
    private long idPostToEdit;
    private Locale locale;
    private boolean popup;
    private Date datefrom;
    private Date dateto;

    private String entryforUpdate;
    private long idCommunityforUpdate;
    private Date datefromforUpdate;
    private Date datetoforUpdate;

    public ActivityPostBean() {
        _bo = new ActivityBO();
        idPostToEdit = 0;
        ThreadLocals.setPostToEdit(null);
        setPopup(true);
        setLocale(Locale.ENGLISH);
    }

    public String addNow() {
        System.out.println("Activity addNow");
        if (dateto.before(datefrom)) {
            dateto = datefrom;
        }
        if (_bo.add(entry, getDatefrom(), getDateto(), getIdCommunity())) {
            setEntry("");
            setDatefrom(null);
            setDateto(null);
            return Nav.activity_add.toString();
        } else {
            ThreadLocals.setErrorMessage("add activity failed");
            return Nav.FAIL.toString();
        }
    }

    public String deletepost(Post p) {
        _bo.delete(p);
        return Nav.actitity_delete.toString();
    }

    public String editpost(Post p) {
        // ThreadLocals.setPostToEdit(p);
        setDatefromforUpdate(p.getDatefrom());
        setDatetoforUpdate(p.getDateto());
        setEntryforUpdate(p.getEntry());
        
        if (p.getCommunity() ==null){
        	setIdCommunityforUpdate(0l);
        }
        else{
        	setIdCommunityforUpdate(p.getCommunity().getId());
        }
        
        idPostToEdit = p.getId();
        
        return Nav.activity_edit.toString();
    }

    public String updatepost(Post p) {
       _bo.updatepost(p);
       return Nav.activity_update.toString();
    }

    public Collection<Community> getCommunities() {
        return _bo.getCommunities();

    }

    public Boolean isAuthor(Post p) {
        return _bo.isAuthor(p);
    }

    public Boolean isPortalAdmin() {
        return _bo.isPortalAdmin();
    }

    public Collection<Post> getAllByCommunity(Community c) {
        return c.getPosts(new Date());
    }

    public Collection<Post> getAllByUser() {
        return _bo.getAllByUser();
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public long getIdCommunity() {
        return idCommunity;
    }

    public void setIdCommunity(long idCommunity) {
        this.idCommunity = idCommunity;
    }

    public long getIdPostToEdit() {
        return idPostToEdit;
    }

    public Boolean compareIDs(Long id) {
        return getIdPostToEdit() == id;
    }

    public void setIdPostToEdit(long idPostToEdit) {
        this.idPostToEdit = idPostToEdit;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        if (_bo.getCurrentCulture().toUpperCase().equals("EN")) {
            this.locale = locale;
        } else {
            this.locale = Locale.GERMAN;
        }
    }

    public boolean isPopup() {
        return popup;
    }

    public void setPopup(boolean popup) {
        this.popup = popup;
    }

    public Date getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(Date datefrom) {
        this.datefrom = datefrom;
    }

    public Date getDateto() {
        return dateto;
    }

    public void setDateto(Date dateto) {
        this.dateto = dateto;
    }

    public String getEntryforUpdate() {
        return entryforUpdate;
    }


    public void setEntryforUpdate(String entryforUpdate) {
        this.entryforUpdate = entryforUpdate;
    }


    public long getIdCommunityforUpdate() {
        return idCommunityforUpdate;
    }


    public void setIdCommunityforUpdate(long idCommunityforUpdate) {
        this.idCommunityforUpdate = idCommunityforUpdate;
    }


    public Date getDatefromforUpdate() {
        return datefromforUpdate;
    }


    public void setDatefromforUpdate(Date datefromforUpdate) {
        this.datefromforUpdate = datefromforUpdate;
    }


    public Date getDatetoforUpdate() {
        return datetoforUpdate;
    }


    public void setDatetoforUpdate(Date datetoforUpdate) {
        this.datetoforUpdate = datetoforUpdate;
    }

}
