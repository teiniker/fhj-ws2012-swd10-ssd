package at.fhj.swd.utils;

import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.domain.ActivityStream;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.User;

public class TestRuntimeContext implements IRuntimeContext {

	private User currentuser;
	private ActivityStream currentas;
	private Community currentcommunity;
	
	
	public void setCurrentUser(User user){
		currentuser = user;
	}
	
	@Override
	public User getCurrentUser() {

		return currentuser;
	}

	@Override
	public ActivityStream getCurrentActivityStream() {
		return currentas;
	}

	@Override
	public Community getCurrentCommunity() {
		return currentcommunity;
	}

	@Override
	public void setAuthenticated(User user) {
		// TODO Auto-generated method stub
		
	}

}
