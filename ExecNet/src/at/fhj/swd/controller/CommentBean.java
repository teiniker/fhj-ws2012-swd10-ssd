package at.fhj.swd.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.business.CommentBO;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

public class CommentBean {

    private String entry;
    private ArrayList<Comment> commentList;
    private CommentBO _bo;

    private IRuntimeContext _rt;
    private IDataContext<Comment> _cc;
    //Zwischenlösung
    private IDataContext<Post> _pc;

    public CommentBean() {
        _bo = new CommentBO();
        this._rt = Application.getInstance().getRuntime();
        this._cc = Application.getInstance().getCommentContext();
        //Zwischenlösung
        this._pc = Application.getInstance().getPostContext();
        
    
       
        
    }
    
    /*public List<Comments> getComments(int id_post){
        this._rt = Application.getInstance().getRuntime();
        this._cc = Application.getInstance().getCommentContext();
        
        return _cc.getComments();
       
        
    } */

    public String addNow(int id_post) {
       return (_bo.add(id_post,this.getEntry()))?"comment-added":"commentadding-failed";
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
