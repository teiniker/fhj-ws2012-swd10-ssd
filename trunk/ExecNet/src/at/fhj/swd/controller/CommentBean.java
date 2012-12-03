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
    //Zwischenlösung
    private IDataContext<Post> _pc;

    public CommentBean() {
        this._rt = Application.getInstance().getRuntime();
        this._cc = Application.getInstance().getCommentContext();
        //Zwischenlösung
        this._pc = Application.getInstance().getPostContext();
       
        
    }

    public String addNow() {
        System.out.println("ADD NOW");
        
        User _u = _rt.getCurrentUser();
        //Dumy Post
        Post _p = new Post();
        _p.setDate(new Date());
        _p.setEntry(this.getEntry());
        _p.setActivityEntry(true);
        _p.setAuthor(_u);
        
        
        Comment _new = new Comment();
        _new.setDate(new Date());
        _new.setEntry(this.getEntry());
        _new.setPost(_p);
        _new.setUser(_u);

        try {
            if (_pc.create(_p)&&_cc.create(_new)) {
                _p.addComment(_new);
                
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
