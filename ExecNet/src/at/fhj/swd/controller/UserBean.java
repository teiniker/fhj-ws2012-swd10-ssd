package at.fhj.swd.controller;

import java.security.MessageDigest;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.User;

public class UserBean {

    private String username;
    private String oldpassword;
    private String password;
    private String email;
    private String culture = "en";

    private IDataContext<User> _context;
    private IRuntimeContext _rt;

    public UserBean() {
        _rt = Application.getInstance().getRuntime();
        _context = Application.getInstance().getUserContext();
    }

    public String changeCulture(String culture) {
        User _newuser = _rt.getCurrentUser();
        _newuser.setCulture(culture);
        _rt.setAuthenticated(_context.update(_newuser));
        return "language-change";
    }

    public String loginNow() throws Exception {
        IRuntimeContext _rt = Application.getInstance().getRuntime();
        this.setPassword(hashSHA1(this.getPassword()));

       try {
            String _q = "i.username='" + this.getUsername() + "' and i.password='" + this.getPassword() + "'";
            User _ref;
            _ref = _context.readOneByQuery(_q, User.class);
            _ref.authenticate();
            _rt.setAuthenticated(_ref);
            return "logged-in";
        } catch (Exception e) {
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
        IRuntimeContext _rt = Application.getInstance().getRuntime();

        User _newuser = new User();
        _newuser.setUsername(this.getUsername());
        _newuser.setEmail(this.getEmail());
        _newuser.setAdmin(true);
        _newuser.setCulture(this.getCulture());
        _newuser.setPassword(hashSHA1(this.getPassword()));

        try {
            if (_context.create(_newuser)) {
                _newuser.authenticate();
                _rt.setAuthenticated(_newuser);
                return "logged-in";
            } else {
                return "register-failed";
            }
        } catch (Exception e) {
            return "register-failed";
        }
    }

    public String editNow() {
        try {
            User _newuser = _rt.getCurrentUser();

            if (hashSHA1(this.getPassword()).equals(_newuser.getPassword())) {
                return "edit-failed";
            }
            _newuser.setUsername(this.getUsername());
            _newuser.setEmail(this.getEmail());
            _newuser.setAdmin(true);
            _newuser.setCulture(this.getCulture());
            _newuser.setPassword(hashSHA1(this.getPassword()));

            _rt.setAuthenticated(_context.update(_newuser));
            return "user-edited";
        } catch (Exception e) {
            return "edit-failed";
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
