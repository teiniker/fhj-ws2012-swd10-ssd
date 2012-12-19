package at.fhj.swd.business;

import java.security.MessageDigest;
import java.util.Collection;

import at.fhj.swd.application.Application;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.User;

public class UserBO extends ABusinessObject {
	

    private IDataContext<User> _context;
   
    public UserBO(){
    	super();
    	
    	this._context = Application.getInstance().getUserContext();
    }
    
    public Collection<User> getAll() {
        try {
            return _context.readAll(User.class);
        } catch (Exception e) {
        	logger.error(e);
            return null;
        }
    }
    
    public boolean login(User user) {
        user.setPassword(hashSHA1(user.getPassword()));

        try {
            String _q = "i.username='" + user.getUsername() + "' and i.password='" + user.getPassword() + "'";
            User _ref;
            _ref = _context.readOneByQuery(_q, User.class);
            _ref.authenticate();
            this.getRuntimeContext().setAuthenticated(_ref);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    

    public boolean register(User user) {

    	try {
    		user.setPassword(hashSHA1(user.getPassword()));
            if (_context.create(user)) {
                user.authenticate();
                this.getRuntimeContext().setAuthenticated(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public boolean edit(User user) {
        try {
            User _newuser = this.getRuntimeContext().getCurrentUser();

            if (hashSHA1(user.getPassword()).equals(_newuser.getPassword())) {
                return false;
            }
            _newuser.setFirstname(user.getFirstname());
            _newuser.setLastname(user.getLastname());
            _newuser.setDepartment(user.getDepartment());
            _newuser.setLocation(user.getLocation());
            _newuser.setEmail(user.getEmail());
            _newuser.setAdmin(true);
            _newuser.setCulture(user.getCulture());
            _newuser.setPassword(hashSHA1(user.getPassword()));

            this.getRuntimeContext().setAuthenticated(_context.update(_newuser));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean changeCulture(String culture) {
        try {
	        User _newuser = this.getRuntimeContext().getCurrentUser();
	        _newuser.setCulture(culture);
	        this.getRuntimeContext().setAuthenticated(_context.update(_newuser));
	        return true;
        }
        catch (Exception e) {
        	return false;
        }
    }

    private String hashSHA1(String password) {
        try {
            MessageDigest cript;
            cript = MessageDigest.getInstance("SHA-1");
            cript.reset();
            cript.update(password.getBytes());
            password = new String(cript.digest());
        } catch (Exception e) {
            // assert plain text password
        }
        return password;
    }


}
