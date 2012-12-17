package at.fhj.swd.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;


public class ActivityBO {

    private IDataContext<Post> _pc;
    private IRuntimeContext _rc;

    public ActivityBO() {
        this._pc = Application.getInstance().getPostContext();
        this._rc = Application.getInstance().getRuntime();
    }

    public Boolean add(String entry) {
        User _u = _rc.getCurrentUser();

        Post _new = new Post();
        _new.setEntry(entry);
        _new.setAuthor(_u);
        _new.setDate(new Date());
        _new.setActivityEntry(true);

        try {
            if (_pc.create(_new)) {
                _u.addPinPost(_new);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Post p) {
        try {
            return _pc.delete(p);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean isOwner(Post p) {
        try {
            return p.getAuthor().getId() == _rc.getCurrentUser().getId();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Collection<Post> getAllByUser() {
        Collection<Post> ps = new ArrayList<Post>();
        Collection<Community> cs = _rc.getCurrentUser().getCommunities();
        for (Community c : cs) {
            ps.addAll(c.getPosts());
        }
        return ps;
    }

}
