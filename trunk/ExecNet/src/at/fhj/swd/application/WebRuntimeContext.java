package at.fhj.swd.application;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.ActivityStream;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.User;

public class WebRuntimeContext implements IRuntimeContext {

    private IDataContext<User> _uc;

    public WebRuntimeContext() {
        this._uc = Application.getInstance().getUserContext();
    }

    @Override
    public User getCurrentUser() {
        FacesContext _ctx = FacesContext.getCurrentInstance();
        HttpSession _session = (HttpSession)_ctx.getExternalContext().getSession(true);
        User sessionuser = (User)_session.getAttribute("user");
        User user = _uc.readOne(sessionuser.getId(), User.class);
        return user;
    }

    @Override
    public ActivityStream getCurrentActivityStream() {
        FacesContext _ctx = FacesContext.getCurrentInstance();
        HttpSession _session = (HttpSession)_ctx.getExternalContext().getSession(true);
        ActivityStream _activitystream = (ActivityStream)_session.getAttribute("activitystream");
        return _activitystream;
    }

    @Override
    public Community getCurrentCommunity() {
        FacesContext _ctx = FacesContext.getCurrentInstance();
        HttpSession _session = (HttpSession)_ctx.getExternalContext().getSession(true);
        Community _community = (Community)_session.getAttribute("community");
        return _community;
    }

    @Override
    public void setAuthenticated(User user) {
        FacesContext _ctx = FacesContext.getCurrentInstance();
        HttpSession _session = (HttpSession)_ctx.getExternalContext().getSession(true);
        user.authenticate();
        _session.setAttribute("user", user);
    }

}
