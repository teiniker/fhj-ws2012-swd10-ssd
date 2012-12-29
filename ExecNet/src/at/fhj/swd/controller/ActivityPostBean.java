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

    public ActivityPostBean() {
        _bo = new ActivityBO();
        idPostToEdit = 0;
        ThreadLocals.setPostToEdit(null);
        setPopup(true);
        setLocale(Locale.GERMAN);
    }

    public String addNow() {
        System.out.println("Activity addNow");
        if (dateto.before(datefrom)) {
            dateto = datefrom;
        }
        if (_bo.add(entry, getDatefrom(), getDateto(), getIdCommunity())) {
            return Nav.activity_add.toString();
        } else {
            ThreadLocals.setErrorMessage("add activity failed");
            return Nav.FAIL.toString();
        }
    }

    public String delete(Post p) {
        System.out.println("Activity delete");
        if (_bo.delete(p)) {
            return Nav.actitity_delete.toString();
        } else {
            ThreadLocals.setErrorMessage("delete activity failed");
            return Nav.FAIL.toString();
        }
    }

    public String edit(Post p) {
        ThreadLocals.setPostToEdit(p);
        return Nav.activity_edit.toString();
    }

    public Collection<Community> getCommunities() {
        return _bo.getCommunities();

    }

    public Boolean isAuthor(Post p) {
        return _bo.isAuthor(p);
    }

    public Collection<Post> getAllByCommunity(Community c) {
        return c.getPosts();
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

    public void setIdPostToEdit(long idPostToEdit) {
        this.idPostToEdit = idPostToEdit;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        if (_bo.getCurrentCulture().toUpperCase().equals("EN")) {
            locale = Locale.ENGLISH;
        } else {
            this.locale = locale;
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
}
