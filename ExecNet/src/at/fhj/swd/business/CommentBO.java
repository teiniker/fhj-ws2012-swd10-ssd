package at.fhj.swd.business;

import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.controller.CommentBean;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;


public class CommentBO extends ABusinessObject {
    
    private IDataContext<Comment> _context;
    private IDataContext<Post> _postcontext;
    private IRuntimeContext _runtimecontext;
    private CommentBean _cb;

    public CommentBO() {
        super();
        this._context = Application.getInstance().getCommentContext();
        this._postcontext = Application.getInstance().getPostContext();
        this._runtimecontext = Application.getInstance().getRuntime();
    }

    public boolean add(int id_post, String entry){
        User _u = _runtimecontext.getCurrentUser();
        _cb = new CommentBean();
 
        Post _p = _postcontext.readOne(id_post, Post.class);
     
        
        Comment _new = new Comment();
        _new.setDate(new Date());
        _new.setEntry(entry);
        _new.setPost(_p);
        _new.setUser(_u);
        
        try {
            if (_postcontext.create(_p)&&_context.create(_new)) {
                _p.addComment(_new);
                
                return true;
            } else {
                return false;
            }
         
        }
        catch (Exception e) {
            logger.error(e);
            return false;
        }
    }
}
