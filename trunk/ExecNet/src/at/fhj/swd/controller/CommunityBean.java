package at.fhj.swd.controller;

import java.util.Collection;

import at.fhj.swd.business.CommunityBO;
import at.fhj.swd.domain.Community;

public class CommunityBean {

    private String name;
    private String description;
    
    private CommunityBO _bo;
    
    public CommunityBean() {
    	_bo = new CommunityBO();
    }

    public String addNow() {
        
        Community _new = new Community();
        _new.setDescription(this.getDescription());
        _new.setName(this.getName());
        if (_bo.add(_new)) {
            return "community-added";
        } else {
            return "communityadding-failed";
        }
    }

    public String joinNow(Long id) {
        if (_bo.join(id))
        	return "user-joined";
        return "user-join-failed";
    }

    public String leaveNow(Long id) {
       if (_bo.leave(id))
    	   return "user-left";
       return "user-leave-failed";
    }

    public boolean isCurrentMember(Long id) {
    	return _bo.isCurrentMember(id);
    }

    public Boolean delete(Long id) {
    	return _bo.delete(id);
    }

    public Collection<Community> getAll() {
        return _bo.getAll();
    }

    public Collection<Community> getMy() {
        return _bo.getMy();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
