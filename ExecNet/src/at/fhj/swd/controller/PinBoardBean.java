package at.fhj.swd.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;

import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

public class PinBoardBean {

	private String entry;
	    //private boolean ispublic = true

	private IDataContext<Post> _pc;
    private IRuntimeContext _rc;


    public PinBoardBean() {
    	   this._pc = Application.getInstance().getPostContext();
           this._rc = Application.getInstance().getRuntime();
    }

    public String addNow() {
    	User _u = _rc.getCurrentUser();

        Post _new = new Post();
        _new.setEntry(this.getEntry());
        _new.setPinboard(_u);
        _new.setAuthor(_u);
        _new.setDate(new Date());
        _u.addPinPost(_new);

        try {
            if (_pc.create(_new)) {
                return "post-added";
            } else {
                return "post-failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "post-failed";
        }
    }

    public Boolean delete(Long id) {
        try {
            Post p = _pc.readOne(id, Post.class);
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

    public Collection<Post> getAll() {
        try {
        
            return _pc.readAll(Post.class);
        } catch (Exception e) {
            return null;
        }
    }

    /*
     * Returns only Posts from the current User	
     */
    public Collection<Post> getMy() {
    	
    	 Collection<Post> ps = new ArrayList<Post>();
    	 Collection<Post> psAll = _pc.readAll(Post.class);
         for (Post p : psAll) {    	 
             if (p.getAuthor().getUsername() == _rc.getCurrentUser().getUsername()) {
                 ps.add(p);
             }
         }
       return ps;

    }

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

   
}
