package at.fhj.swd.controller;

import java.util.Collection;

import at.fhj.swd.business.ActivityBO;
import at.fhj.swd.domain.Post;

public class ActivityPostBean {

    private String entry;

    private ActivityBO _bo;

    public ActivityPostBean() {
        this._bo = new ActivityBO();
    }

    public String addNow() {
        System.out.println("Activity addNow");
        return _bo.add(entry) ? "post-added" : "post-failed";
    }

    public String delete(Long id) {
        System.out.println("Activity delete");
        return _bo.delete(id) ? "actitity_deleted" : "acitivity_deleted_failed";
    }

    public Collection<Post> getAll() {
        return _bo.getAll();
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
