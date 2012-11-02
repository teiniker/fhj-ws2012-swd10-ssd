package at.fhj.swd.application;

import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.ActivityStream;
import at.fhj.swd.domain.User;

public interface IRuntimeContext {
	
	public User getCurrentUser();
	public void setAuthenticated(User user);
	public ActivityStream getCurrentActivityStream();
	public Community getCurrentCommunity();

}
