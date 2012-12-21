package at.fhj.swd.controller;

import java.util.Collection;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import at.fhj.swd.business.UserBO;
import at.fhj.swd.domain.User;

public class UserBean {

	private String firstname;
	private String lastname; 
	private String department;
	private String location;
    private String username;
    private String oldpassword;
    private String password;
    private String email;
    private String culture = "en";

    private UserBO _bo;

    public UserBean() {
    	_bo = new UserBO();
    }

    public String changeCulture(String culture) {
        if (_bo.changeCulture(culture))
        	return "language-change";
        return "language-change-failed";
    }

    public String loginNow() {
   
        User _ref = new User();
        _ref.setUsername(this.getUsername());
        _ref.setPassword(this.getPassword());
        
        if (_bo.login(_ref)){
        	return "logged-in";
        } else {
            return "login-failed";
        }
    }

    public String logoutNow() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(true);
        session.removeAttribute("user");
        session.invalidate();
        return "logged-out";
    }

    public String registerNow() {
        
        User _newuser = new User();
        _newuser.setFirstname(this.getFirstname());
        _newuser.setLastname(this.getLastname());
        _newuser.setDepartment(this.getDepartment());
        _newuser.setLocation(this.getLocation());
        _newuser.setUsername(this.getUsername());
        _newuser.setEmail(this.getEmail());
        _newuser.setAdmin(true);
        _newuser.setCulture(this.getCulture());
        _newuser.setPassword(this.getPassword());
        _newuser.setActive(true);
        _newuser.setPortalAdmin(false);

        if (_bo.register(_newuser)) {
            return "logged-in";
        } else {
            return "register-failed";
        }
    }

    public String editNow() {
 
    	User _newuser = new User();;
    	_newuser.setFirstname(this.getFirstname());
        _newuser.setLastname(this.getLastname());
        _newuser.setDepartment(this.getDepartment());
        _newuser.setLocation(this.getLocation());
        _newuser.setEmail(this.getEmail());
        _newuser.setAdmin(true);
        _newuser.setCulture(this.getCulture());
        _newuser.setPassword(this.getPassword());

        if (_bo.edit(_newuser)){
        	return "user-edited";
        } else {
            return "user-edit-failed";
        }
    }

    public Collection<User> getAll() {
        return _bo.getAll();
    }

    public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean setAdmin(int id) {
        return _bo.setAdmin(id);
    }
	
	public boolean setPortalAdmin(int id) {
	        return _bo.setPortalAdmin(id);
	}
	
	public boolean setActive(int id) {
        return _bo.setActive(id);
    }
}
