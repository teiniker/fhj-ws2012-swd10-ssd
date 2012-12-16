package at.fhj.swd.controller;

import java.util.Collection;

import at.fhj.swd.business.ActivityBO;
import at.fhj.swd.domain.Post;

public class ActivityPostBean {

    private String entry;
    private boolean isPublic;
    private ActivityBO _bo;

    public ActivityPostBean() {
        this._bo = new ActivityBO();
    }

    public nav addNow() {
        System.out.println("Activity addNow");
        return (_bo.add(entry, isPublic)) ? nav.activity_add : nav.activity_add_FAIL;
    }

    public nav delete(Long id) {
        System.out.println("Activity delete");
        return _bo.delete(id) ? nav.actitity_delete : nav.actitity_delete_FAIL;
    }

    public nav edit(Long id) {
        return null;
    }

    public Boolean isOwner(Long id) {
        return _bo.isOwner(id);
    }

    public Collection<Post> getAll(boolean isPublic) {
        return _bo.getAll(isPublic);
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
}