package at.fhj.swd.business;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.List;

import at.fhj.swd.application.Application;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.User;

public class UserBO extends ABusinessObject {

    private IDataContext<User> _context;

    public UserBO() {
        super();
        this._context = Application.getInstance().getUserContext();
    }

    public boolean login(User user) {
        user.setPassword(hashSHA1(user.getPassword()));

        try {
            String _query = "i.username='" + user.getUsername() + "' and i.password='" + user.getPassword() + "'";
            User _refuser = _context.readOneByQuery(_query, User.class);
            if (_refuser.isActive()) {
                _refuser.authenticate();
                this.getRuntimeContext().setAuthenticated(_refuser);
                logger.info("Login successful");
                return true;
            } else {
                logger.info("Login failed: user is not active");
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public boolean register(User user) {
        user.setPassword(hashSHA1(user.getPassword()));

        try {
            if (_context.create(user)) {
                user.authenticate();
                this.getRuntimeContext().setAuthenticated(user);
                logger.info("Registration successful");
                return true;
            } else {
                logger.info("Registration failed: could not create user");
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public boolean edit(User user) {
        try {
            User _newuser = this.getRuntimeContext().getCurrentUser();
            if (hashSHA1(user.getPassword()).equals(_newuser.getPassword())) {
                logger.info("Edit failed: unequal passwords");
                return false;
            }
            _newuser.setFirstname(user.getFirstname());
            _newuser.setLastname(user.getLastname());
            _newuser.setPassword(hashSHA1(user.getPassword()));
            _newuser.setEmail(user.getEmail());
            _newuser.setCulture(user.getCulture());
            _newuser.setDepartment(user.getDepartment());
            _newuser.setLocation(user.getLocation());
            _newuser.setAdmin(true);
            _newuser.setPortalAdmin(false);
            _newuser.setActive(true);

            this.getRuntimeContext().setAuthenticated(_context.update(_newuser));
            logger.info("Edit successful");
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    /**
     * Create users in the database.
     * 
     * ATTENTION: Be aware that users get created in an inactive status.
     * 
     * @param users
     *            The collection of users that has to be created in the
     *            database.
     * 
     * @return True if batch creation of users succeeded or false if it did not.
     */
    public boolean create(List<User> users) {

        for (User user : users) {
            user.setPassword(hashSHA1(user.getPassword()));

            try {
                if (_context.create(user)) {
                    logger.info("User creation successful");
                } else {
                    logger.info("User Creation failed: could not create user");
                    return false;
                }
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        }

        return true;
    }

    private String hashSHA1(String password) {
        try {
            MessageDigest cript = MessageDigest.getInstance("SHA-1");
            cript.reset();
            cript.update(password.getBytes());
            password = new String(cript.digest());
        } catch (Exception e) {
            logger.error(e);
        }
        return password;
    }

    public boolean changeCulture(String culture) {
        try {
            User _newuser = this.getRuntimeContext().getCurrentUser();
            _newuser.setCulture(culture);
            this.getRuntimeContext().setAuthenticated(_context.update(_newuser));
            logger.info("Change culture successful");
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Collection<User> searchUser(String searchQuery) {
        String[] tokens = searchQuery.trim().split("\\s+");

        StringBuffer queryBuffer = new StringBuffer();
        for (int i = 0; i < tokens.length; i++) {
            queryBuffer.append("i.firstname like '%" + tokens[i] + "%' or i.lastname like '%" + tokens[i] + "%'");

            if (i < tokens.length - 1) {
                queryBuffer.append(" or ");
            }
        }
        String query = queryBuffer.toString();

        try {
            Collection<User> users = _context.readByQuery(query, User.class);
            return users;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public Boolean setAdmin(Long id) {
        try {
            User u = _context.readOne(id, User.class);

            if (u.isAdmin() == true) {
                u.setAdmin(false);
                logger.info("setAdmin: false");
            } else {
                u.setAdmin(true);
                logger.info("setAdmin: true");
            }

            _context.update(u);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Boolean setPortalAdmin(Long id) {
        try {
            User u = _context.readOne(id, User.class);

            if (u.isPortalAdmin() == true) {
                u.setPortalAdmin(false);
                logger.info("setPortalAdmin: false");
            } else {
                u.setPortalAdmin(true);
                logger.info("setPortalAdmin: true");
            }

            _context.update(u);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Boolean setActive(Long id) {
        try {
            User u = _context.readOne(id, User.class);

            if (u.isActive() == true) {
                u.setActive(false);
                logger.info("setActive: false");
            } else {
                u.setActive(true);
                logger.info("setActive: true");
            }

            _context.update(u);
            return true;
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Collection<User> getAll() {
        try {
            return _context.readAll(User.class);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public User getOne(Long id) {
        try {
            return _context.readOne(id, User.class);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

}
