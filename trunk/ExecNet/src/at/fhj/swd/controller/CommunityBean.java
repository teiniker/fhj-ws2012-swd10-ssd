package at.fhj.swd.controller;

import java.util.Collection;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.User;

public class CommunityBean {

    private String name;
    private String description;

    private IRuntimeContext _rt;
    private IDataContext<Community> _cc;
    private IDataContext<User> _uc;

    public CommunityBean() {
        _cc = Application.getInstance().getCommunityContext();
        _uc = Application.getInstance().getUserContext();
        _rt = Application.getInstance().getRuntime();
    }

    public String addNow() {
        User _u = _rt.getCurrentUser();

        
        Community _new = new Community();
        _new.setDescription(this.getDescription());
        _new.setName(this.getName());
        _new.setAdmin(_u);
        _new.addUser(_u);
        _u.addCommunity(_new);

        try {
            if (_cc.create(_new)) {
                _rt.setAuthenticated(_uc.update(_u));
                return "community-added";
            } else {
                return "communityadding-failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "communityadding-failed";
        }
    }

    public String joinNow(Long id) {
        User _u = _rt.getCurrentUser();
        Community c = _cc.readOne(id, Community.class);
        c.addUser(_u);
        _cc.update(c);
        _u.addCommunity(c);
        _uc.update(_u);
        return "user-joined";
    }

    public String leaveNow(Long id) {
        User _u = _rt.getCurrentUser();
        Community c = _cc.readOne(id, Community.class);
        c.removeUser(_u);
        _cc.update(c);
        _u.removeCommunity(c);
        _uc.update(_u);
        return "user-left";
    }

    public boolean isCurrentMember(Long id) {
        User _u = _rt.getCurrentUser();
        Community c = _cc.readOne(id, Community.class);

        for (User u : c.getUsers()) {
            if (u.getId() == _u.getId())
                return true;
        }
        return false;
    }

    public Boolean delete(Long id) {
        try {
            Community c = _cc.readOne(id, Community.class);
            if (_cc.delete(c)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Collection<Community> getAll() {
        try {
            return _cc.readAll(Community.class);
        } catch (Exception e) {
            return null;
        }
    }

    public Collection<Community> getMy() {
        User _u = _rt.getCurrentUser();
        return _u.getCommunities();
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
