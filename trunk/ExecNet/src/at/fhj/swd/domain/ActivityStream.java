package at.fhj.swd.domain;

import java.util.ArrayList;
import java.util.List;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;

public class ActivityStream {
	
	private IRuntimeContext _runtime;
	

	public ActivityStream(){
		Application _app = Application.getInstance();
		_runtime = _app.getRuntime();
	}
	
/*
	public List<Post> getAllPosts(){
		User _user = _runtime.getCurrentUser();
		List<Post> _result = new ArrayList<Post>();
		if (_user.isLoggedIn()){
			_result = _pcontext.getPostsByUser(_user, false);
		}
		return _result;
	}
	
	public List<Post> getPublicPosts(){
		User _user = _runtime.getCurrentUser();
		List<Post> _result = new ArrayList<Post>();
		if (_user.isLoggedIn()){
			_result = _pcontext.getPostsByUser(_user, true);
		}
		return _result;
	}
	
	public List<Post> getPosts(Community community){
		User _user = _runtime.getCurrentUser();
		List<Post> _result = new ArrayList<Post>();
		if (_user.isLoggedIn() && _user.getCommunities().contains(community)){
			_result = _pcontext.getPostsByCommunity(community);
		}
		return _result;
	}


	public Community getCommunity() {
		return _community;
	}


	public void setCommunity(Community community) {
		this._community = community;
	} */
}