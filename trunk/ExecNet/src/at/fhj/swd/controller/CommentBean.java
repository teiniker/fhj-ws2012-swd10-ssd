package at.fhj.swd.controller;

import java.util.ArrayList;
import java.util.Collection;

import at.fhj.swd.business.CommentBO;
import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Post;

public class CommentBean {

    private String entry;
    private String inputEntry;

    public String getInputEntry() {
        return inputEntry;
    }


    public void setInputEntry(String e) {
        this.inputEntry = e;
    }

    private CommentBO _bo;


    public CommentBean() {
        _bo = new CommentBO();

    }

    public Boolean commentsOrPostEmpty(Post p) {
        if (p == null) {
            return true;
        } else {
            if (p.getComments() == null) {
                return true;
            } else {
                return false;
            }

        }
    }

    public Collection<Comment> getcomments(Post p) {
        Collection<Comment> _c = new ArrayList<Comment>();
        if (p == null) {
            return null;
        } else {
            _c.addAll(p.getComments());
            return _c;
        }
    }

    public String add(Post p) {
        if (_bo.add(p, getInputEntry())) {
            setInputEntry("");
            return "comment-added";
        } else {
            return "commentadding-failed";
        }
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
