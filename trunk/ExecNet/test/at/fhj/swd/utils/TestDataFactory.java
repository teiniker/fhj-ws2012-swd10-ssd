package at.fhj.swd.utils;

import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.domain.*;

public class TestDataFactory {
	
	Application _app = Application.getInstance();
	private TestRuntimeContext _runtime;
	
	public TestDataFactory() 
	{
		setRuntime(new TestRuntimeContext());
		_app.setRuntime(getRuntime());
	}
	
	public User createUser(String UserName){
		User _result = new User();
		
		_result.setEmail("jacke@wie.hose");
		_result.setPassword("p@ssword");
		_result.setAdmin(false);
		_result.setUsername(UserName);
		
		return _result;
		
	}
	
	public Comment createComment(String text){
		Comment _result = new Comment();
		
		_result.setDate(new Date());
		_result.setEntry(text);
		
		return _result;
	}
	
	public Post createPost(String text){
		Post _result = new Post();
		
		_result.setDate(new Date());
		_result.setIspublic(true);
		_result.setEntry(text);
		
		return _result;
	}
	
	public Document createDocument(String name){
		Document _result = new Document();
		
		_result.setPublic(true);
		_result.setUrl("http://wurscht.bla.com");
		_result.setName(name);
		
		return _result;
	}
	
	public Community createCommunity(String name){
		Community _result = new Community();
		
		_result.setName(name);
		_result.setDescription("yet another test community");
		
		return _result;
	}

	public TestRuntimeContext getRuntime() {
		return _runtime;
	}

	public void setRuntime(TestRuntimeContext _runtime) {
		this._runtime = _runtime;
	}

}
