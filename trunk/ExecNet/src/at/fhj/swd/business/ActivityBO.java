package at.fhj.swd.business;

import java.util.Collection;
import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;


public class ActivityBO {

    private IDataContext<Post> _pc;
    private IRuntimeContext _rc;

    public ActivityBO() {
        this._pc = Application.getInstance().getPostContext();
        this._rc = Application.getInstance().getRuntime();
    }

    public Boolean add(String entry, boolean isPublic) {
        User _u = _rc.getCurrentUser();

        Post _new = new Post();
        _new.setEntry(entry);
        _new.setPinboard(_u);
        _new.setAuthor(_u);
        _new.setDate(new Date());
        _new.setActivityEntry(true);
        _new.setPublic(isPublic);
        _u.addPinPost(_new);


        try {
            return _pc.create(_new);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(long id) {
        try {
            Post p = _pc.readOne(id, Post.class);
            return (_pc.delete(p));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Collection<Post> getAll() {
        try {
            return _pc.readAll(Post.class);
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean isOwner(Long id) {
        try {
            Post p = _pc.readOne(id, Post.class);
            if (p == null) {
                return null;
            } else {
                return p.getAuthor().getId() == _rc.getCurrentUser().getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
