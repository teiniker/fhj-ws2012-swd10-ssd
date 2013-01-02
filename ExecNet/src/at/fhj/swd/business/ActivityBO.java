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

        Community _c = _cc.readOne(idCommunity, Community.class);

        Post _new = new Post();
        _new.setEntry(entry);
        _new.setAuthor(_u);
        _new.setDate(new Date());
        _new.setActivityEntry(true);
        _new.setDatefrom(datefrom);
        _new.setDateto(dateto);
        _new.setCommunity(_c);

        try {
            if (_pc.create(_new)) {
                _c.addPost(_new);
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
            return _pc.delete(p);
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

    /**
     * is User PortalAdmin
     * 
     * @return
     * @throws Exception
     */
    public Boolean isPortalAdmin(){
        User _u = _rc.getCurrentUser();
        
        if(_u==null){
            return false;
        }
        
        try{
            return _u.isPortalAdmin();
        }
        catch(Exception e){
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
        Collection<Community> cs = _rc.getCurrentUser().getCommunities();
        for (Community c : cs) {
            ps.addAll(c.getPosts());
        }
        return ps;


        /*
         * try {
         * ps = _pc.readAll(Post.class);
         * 
         * Collection<Community> cs = _rc.getCurrentUser().getCommunities();
         * for(Post p : ps){
         * if(p.getCommunity() != null && !cs.contains(p.getCommunity())){
         * ps.remove(p);
         * }
         * }
         * return ps;
         * } catch (Exception e) {
         * // TODO Auto-generated catch block
         * e.printStackTrace();
         * return null;
         * }
         */
    }

    public Collection<Community> getCommunities() {
        Collection<Community> co = new ArrayList<Community>();
        Community cn = new Community();
        cn.setName("Global");
        cn.setId((long)999);
        cn.setDescription("Für alle sichtbar");
        co.add(cn);

        for (Community c : _rc.getCurrentUser().getCommunities()) {
            co.add(c);
        }
        return co;
    }

}
