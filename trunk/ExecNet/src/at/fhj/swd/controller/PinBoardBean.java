package at.fhj.swd.controller;

import java.util.Collection;
import java.util.Date;

import at.fhj.swd.business.PinBoardBO;
import at.fhj.swd.domain.Post;

public class PinBoardBean {

    private String entry;

    private PinBoardBO _bo;

    public PinBoardBean() {
        _bo = new PinBoardBO();
    }

    public String addNow() {
        Post _new = new Post();
        _new.setEntry(this.getEntry());
        _new.setDate(new Date());

        if (_bo.add(_new)) {
            return "post-added";
        } else {
            return "post-failed";
        }
    }

    public Boolean delete(Long id) {
        return _bo.delete(id);
    }

    public Collection<Post> getAll() {
        return _bo.getAll();
    }

    public Collection<Post> getMy() {
        return _bo.getMy();
    }

    public Collection<Post> getOthers(Long id) {
        return _bo.getOthers(id);
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
