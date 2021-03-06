package at.fhj.swd.business;

import java.util.Collection;

import at.fhj.swd.application.Application;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.User;

public class CommunityBO extends ABusinessObject {

    private IDataContext<Community> _context;
    private IDataContext<User> _usercontext;

    public CommunityBO() {
        super();
        this._context = Application.getInstance().getCommunityContext();
        this._usercontext = Application.getInstance().getUserContext();
    }

    public boolean add(Community community) {
        try {
            User _u = this.getRuntimeContext().getCurrentUser();
            community.setAdmin(_u);
            community.addUser(_u);
            _u.addCommunity(community);

            if (_context.create(community)) {
                this.getRuntimeContext().setAuthenticated(_usercontext.update(_u));
                logger.info("Community creation successful");
                return true;
            } else {
                logger.info("Community creation not successful");
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public boolean join(Long id) {
        try {
            User _u = this.getRuntimeContext().getCurrentUser();
            Community c = _context.readOne(id, Community.class);
            c.addUser(_u);
            _context.update(c);
            _u.addCommunity(c);
            _usercontext.update(_u);
            this.getRuntimeContext().setAuthenticated(_usercontext.update(_u));
            logger.info("Community entry successful");
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public boolean leave(Long id) {
        try {
            User _u = this.getRuntimeContext().getCurrentUser();
            Community c = _context.readOne(id, Community.class);
            c.removeUser(_u);
            _context.update(c);
            _u.removeCommunity(c);
            _usercontext.update(_u);
            this.getRuntimeContext().setAuthenticated(_usercontext.update(_u));
            logger.info("Community exit successful");
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public boolean isCurrentMember(Long id) {
        try {
            User _u = this.getRuntimeContext().getCurrentUser();
            Community c = _context.readOne(id, Community.class);

            for (User u : c.getUsers()) {
                if (u.getId() == _u.getId())
                    return true;
            }
            return false;
        } catch (Exception e) {
            // No Log Entry - JSF Issue.
            return false;
        }
    }

    public Boolean delete(Long id) {
        try {
            User _u = this.getRuntimeContext().getCurrentUser();
            Community community = _context.readOne(id, Community.class);

            if (_context.delete(community)) {
                this.getRuntimeContext().setAuthenticated(_usercontext.update(_u));
                logger.info("Community deletion successful");
                return true;
            } else {
                logger.info("Community deletion not successful");
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Collection<Community> getAll() {
        try {
            return _context.readAll(Community.class);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public Collection<Community> getMy() {
        try {
            User _u = this.getRuntimeContext().getCurrentUser();
            return _u.getCommunities();
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public Community getOne(Long id) {
        try {
            return _context.readOne(id, Community.class);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

}
