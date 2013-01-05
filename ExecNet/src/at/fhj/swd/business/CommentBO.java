package at.fhj.swd.business;

import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;


public class CommentBO extends ABusinessObject {

    private IDataContext<Comment> _context;
    private IRuntimeContext _runtimecontext;

    public CommentBO() {
        super();
        this._context = Application.getInstance().getCommentContext();
        this._runtimecontext = Application.getInstance().getRuntime();
    }

    public boolean add(Post p, String entry) {
        User _u = _runtimecontext.getCurrentUser();

        Comment _new = new Comment();
        _new.setDate(new Date());
        _new.setEntry(entry);
        _new.setPost(p);
        _new.setUser(_u);

        try {
            if (_context.create(_new)) {
                p.addComment(_new);
                _context.update(_new);
               
                ActivityBO _abo = new ActivityBO();
                _abo.updatepost(p);
                
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }
}
