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
    private IDataContext<Community> _cc;

    private IRuntimeContext _rc;


    public void set_rc(IRuntimeContext _rc) {
        this._rc = _rc;
    }

    public ActivityBO() {
        this._pc = Application.getInstance().getPostContext();
        this._rc = Application.getInstance().getRuntime();
        this._cc = Application.getInstance().getCommunityContext();
    }

    /**
     * Adding new Post
     * 
     * @param entry
     * @return
     */
    public Boolean add(String entry, Date datefrom, Date dateto, long idCommunity) {
        User _u = _rc.getCurrentUser();

        Post _new = new Post();
        _new.setEntry(entry);
        _new.setAuthor(_u);
        _new.setDate(new Date());
        _new.setActivityEntry(true);
        _new.setDatefrom(datefrom);
        _new.setDateto(dateto);

        Community _c = null;
        if (idCommunity != 0L) {
            _c = _cc.readOne(idCommunity, Community.class);
            _new.setCommunity(_c);
        }

        try {
            if (_pc.create(_new)) {
                if (_c != null) {
                    _c.addPost(_new);
                    _cc.update(_c);
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * update
     * 
     * @param idPost
     * @param entry
     * @param datefrom
     * @param dateto
     * @param idCommunity
     * @return
     */
    public Boolean updatePost(Post p) {
        Community _c = p.getCommunity();

        try {
            if (_pc.update(p) != null) {
                _c.addPost(p);
                _cc.update(_c);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getCurrentCulture() {
        return _rc.getCurrentUser().getCulture();
    }

    /**
     * delete Post
     * 
     * @param p
     * @return
     */
    public Boolean delete(Post p) {
        try {
            if (_pc.delete(p)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Long idPost) {
        try {
            Post p = _pc.readOne(idPost, Post.class);
            if (_pc.delete(p)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * is user logged in author of the post
     * 
     * @param p
     * @return
     */
    public Boolean isAuthor(Post p) {
        if (p == null) {
            return false;
        }
        try {
            return p.getAuthor().getId() == _rc.getCurrentUser().getId();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean isPortalAdmin() {
        User _u = _rc.getCurrentUser();

        if (_u == null) {
            return false;
        }
        try {
            return _u.isPortalAdmin();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * All posts from communities where the user logged in is member
     * 
     * @return
     * @throws Exception
     */
    public Collection<Post> getAllByUser() {
        // alle Post holen welche keiner community zugewiesen und somit global sind
        // so irgendwie
        // Collection<Post> ps = _pc.readByQuery("WHERE community IS NULL AND pinpost IS NULL", Post.class);

        Collection<Post> ps = new ArrayList<Post>();

        Date tdNow = new Date();

        Collection<Community> cs = _rc.getCurrentUser().getCommunities();
        for (Community c : cs) {
            // zum jetztigen zeitpunkt gültigen posts holen
            ps.addAll(c.getPosts(tdNow));
        }

        Collection<Post> psAll = _pc.readAll(Post.class);
        for (Post p : psAll) {
            // wenn post Global ist (community == null) und zum jetztigen zeitpunkt gültigkeit hat.
            if (p.getCommunity() == null && p.getDatefrom().before(tdNow) && p.getDateto().after(tdNow)) {
                ps.add(p);
            }
        }

        return ps;

    }

    public Collection<Community> getCommunities() {
        Collection<Community> co = new ArrayList<Community>();
        Community cn = new Community();
        cn.setName("Global");
        cn.setId(0L);
        co.add(cn);

        for (Community c : _rc.getCurrentUser().getCommunities()) {
            co.add(c);
        }
        return co;
    }

}
