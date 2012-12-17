package at.fhj.swd.controller;

import java.util.Collection;

import at.fhj.swd.business.CommentBO;
import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Post;

public class CommentBean {

    private String entry;
    private CommentBO _bo;


    public CommentBean() {
        _bo = new CommentBO();

    }


    public Collection<Comment> getComments(Post p) {
        return p.getComments();
    }

    public String addNow(Post p) {
        return (_bo.add(p, this.getEntry())) ? "comment-added" : "commentadding-failed";
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
