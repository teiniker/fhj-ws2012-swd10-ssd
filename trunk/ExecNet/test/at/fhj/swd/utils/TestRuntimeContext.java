package at.fhj.swd.utils;

import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.domain.ActivityStream;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.User;

public class TestRuntimeContext implements IRuntimeContext {

    private User currentUser;
    
    private ActivityStream currentActivityStream;
    private Community currentCommunity;

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
    
    @Override
    public void setAuthenticated(User user) {
        user.authenticate();
       
    }

    @Override
    public ActivityStream getCurrentActivityStream() {
        return currentActivityStream;
    }

    @Override
    public Community getCurrentCommunity() {
        return currentCommunity;
    }
    
    public void setCurrentUser(User user){
        this.currentUser=user;
    }

}
