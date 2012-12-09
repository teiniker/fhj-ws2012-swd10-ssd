package at.fhj.swd.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import at.fhj.swd.business.UserBO;
import at.fhj.swd.domain.User;

public class UserBean {

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
        _newuser.setUsername(this.getUsername());
        _newuser.setEmail(this.getEmail());
        _newuser.setAdmin(true);
        _newuser.setCulture(this.getCulture());
        _newuser.setPassword(this.getPassword());

        if (_bo.register(_newuser)) {
            return "logged-in";
        } else {
            return "register-failed";
        }
    }

    public String editNow() {
 
    	User _newuser = new User();;
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
}
