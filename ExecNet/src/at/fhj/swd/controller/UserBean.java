package at.fhj.swd.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import at.fhj.swd.business.UserBO;
import at.fhj.swd.domain.User;

public class UserBean {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String oldpassword;
    private String email;
    private String culture = "en";
    private String department;
    private String location;
    private User viewUser;
    private String searchQuery;
    private Collection<User> searchResultUsers;

    private UserBO _bo;

    public UserBean() {
        _bo = new UserBO();
    }

    @PostConstruct
    public void init() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        try {
            Long id = Long.valueOf(context.getRequestParameterMap().get("viewUserId"));
            viewUser = _bo.getOne(id);
        } catch (Exception e) {
            viewUser = null;
        }
    }

    public String login() {
        User user = new User();
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());

        if (_bo.login(user)) {
            return "new_home";
        } else {
            FacesContext.getCurrentInstance().addMessage("login", new FacesMessage("Login not successful!"));
            return "new_login";
        }
    }

    public String logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(true);
        session.removeAttribute("user");
        session.invalidate();
        return "new_login";
    }

    public String register() {
        User user = new User();
        user.setFirstname(this.getFirstname());
        user.setLastname(this.getLastname());
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setEmail(this.getEmail());
        user.setCulture(this.getCulture());
        user.setDepartment(this.getDepartment());
        user.setLocation(this.getLocation());
        user.setAdmin(true);
        user.setPortalAdmin(false);
        user.setActive(true);

        if (_bo.register(user)) {
            return "new_home";
        } else {
            FacesContext.getCurrentInstance().addMessage("register", new FacesMessage("Registration not successful!"));
            return "new_register";
        }
    }

    public String edit() {
        User user = new User();
        user.setFirstname(this.getFirstname());
        user.setLastname(this.getLastname());
        user.setPassword(this.getPassword());
        user.setEmail(this.getEmail());
        user.setCulture(this.getCulture());
        user.setDepartment(this.getDepartment());
        user.setLocation(this.getLocation());
        user.setAdmin(true);
        user.setPortalAdmin(false);
        user.setActive(true);

        if (_bo.edit(user)) {
            return "new_home";
        } else {
            FacesContext.getCurrentInstance().addMessage("edit", new FacesMessage("Edit not successful!"));
            return "new_settings";
        }
    }

    public String changeCulture(String culture) {
        if (_bo.changeCulture(culture))
            return "language-change";
        return "language-change-failed";
    }

    public String searchUser() {
        searchResultUsers = _bo.searchUser(searchQuery);

        if (searchResultUsers == null || searchResultUsers.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage("search", new FacesMessage("User Search not successful!"));
            return "new_users";
        }

        return "new_search";
    }

    @Deprecated
    public String loginNow() {
        User _newuser = new User();
        _newuser.setUsername(this.getUsername());
        _newuser.setPassword(this.getPassword());

        if (_bo.login(_newuser)) {
            return "logged-in";
        } else {
            return "login-failed";
        }
    }

    @Deprecated
    public String logoutNow() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession)ctx.getExternalContext().getSession(true);
        session.removeAttribute("user");
        session.invalidate();
        return "logged-out";
    }

    @Deprecated
    public String registerNow() {
        User _newuser = new User();
        _newuser.setFirstname(this.getFirstname());
        _newuser.setLastname(this.getLastname());
        _newuser.setUsername(this.getUsername());
        _newuser.setPassword(this.getPassword());
        _newuser.setEmail(this.getEmail());
        _newuser.setCulture(this.getCulture());
        _newuser.setDepartment(this.getDepartment());
        _newuser.setLocation(this.getLocation());
        _newuser.setAdmin(true);
        _newuser.setActive(true);
        _newuser.setPortalAdmin(false);

        if (_bo.register(_newuser)) {
            return "logged-in";
        } else {
            return "register-failed";
        }
    }

    @Deprecated
    public String editNow() {
        User _newuser = new User();
        _newuser.setFirstname(this.getFirstname());
        _newuser.setLastname(this.getLastname());
        _newuser.setPassword(this.getPassword());
        _newuser.setEmail(this.getEmail());
        _newuser.setCulture(this.getCulture());
        _newuser.setDepartment(this.getDepartment());
        _newuser.setLocation(this.getLocation());
        _newuser.setAdmin(true);
        _newuser.setActive(true);
        _newuser.setPortalAdmin(false);

        if (_bo.edit(_newuser)) {
            return "user-edited";
        } else {
            return "user-edit-failed";
        }
    }

    public Collection<User> getAll() {
        return _bo.getAll();
    }

    public boolean setAdmin(Long id) {
        return _bo.setAdmin(id);
    }

    public boolean setPortalAdmin(Long id) {
        return _bo.setPortalAdmin(id);
    }

    public boolean setActive(Long id) {
        return _bo.setActive(id);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
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

    public User getViewUser() {
        return viewUser;
    }

    public void setViewUser(User viewUser) {
        this.viewUser = viewUser;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Collection<User> getSearchResultUsers() {
        return searchResultUsers;
    }

    public void setSearchResultUsers(Collection<User> searchResultUsers) {
        this.searchResultUsers = searchResultUsers;
    }
}
