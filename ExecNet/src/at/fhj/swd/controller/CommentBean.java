package at.fhj.swd.controller;

import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

public class CommentBean {

    private String entry;

    private IRuntimeContext _rt;
    private IDataContext<Comment> _cc;

    public CommentBean() {
        this._rt = Application.getInstance().getRuntime();
        this._cc = Application.getInstance().getCommentContext();
    }

    public String addNow() {
        System.out.println("ADD NOW");
        
        User _u = _rt.getCurrentUser();
        Post post = new Post();
   
        Comment _new = new Comment();
        _new.setDate(new Date());
        _new.setEntry(this.getEntry());
        _new.setPost(post);
        _new.setUser(_u);

        try {
            if (_cc.create(_new)) {
                post.addComment(_new);
                
                return "comment-added";
            } else {
                return "commentadding-failed";
            }
        } catch (Exception e) {
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
