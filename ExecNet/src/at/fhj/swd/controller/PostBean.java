package at.fhj.swd.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import at.fhj.swd.application.Application;
import at.fhj.swd.application.IRuntimeContext;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

public class PostBean{
	
	private Date date;
	private String entry;
	private boolean ispublic = true;
	private User author;
	private User pinboard;
	private Collection<Community> communities;
	private IDataContext<Post> _pc;
	private IRuntimeContext _rc;
	
	public PostBean() {
		this._pc = Application.getInstance().getPostContext();
		this._rc = Application.getInstance().getRuntime();
	}
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}


	public User getPinboard() {
		return pinboard;
	}
	public void setPinboard(User user) {
		this.pinboard = user;
	}
	public String getEntry() {
		return entry;
	}
	public void setEntry(String entry) {
		this.entry = entry;
	}
	
		
	
	public Collection<Community> getCommunities() {
		return communities;
	}


	public void setCommunities(Collection<Community> communities) {
		this.communities = communities;
	}


	public String addNow() {
		
		User _u =_rc.getCurrentUser();
		
		try {
			Post _new = new Post();
			_new.setAuthor(_u);
			_new.setDate(new Date());
			_new.setEntry(this.getEntry());
			_new.setIspublic(this.ispublic);
			
			if (this.getPinboard() != null) {
				this.getPinboard().addPinPost(_new);
			} else {
				for (Community _c : this.getCommunities()){
					_c.addPost(_new);
				}
			}
			if (_pc.create(_new)) {
				return "post-added";
			}
			else {
				return "postadding-failed";
			}
		}catch (Exception e){
			return "postadding-failed";
		}
		
		
	}
	
	
	public String getPostDate() {
		 SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd.MM.yyyy");
		 StringBuilder formatedDate = new StringBuilder( dateformatDDMMYYYY.format( date ));
		 return formatedDate.toString();
	}


}
