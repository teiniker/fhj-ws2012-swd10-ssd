package at.fhj.swd.controller;

import java.util.Collection;

import at.fhj.swd.business.ActivityBO;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;

public class ActivityPostBean {

    private String entry;
    private ActivityBO _bo;
    private long idCommunity;


    public ActivityPostBean() {
        this._bo = new ActivityBO();
        ThreadLocals.setPostToEdit(null);
    }

    public String addNow() {
        System.out.println("Activity addNow");
        if (_bo.add(entry)) {
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
}
