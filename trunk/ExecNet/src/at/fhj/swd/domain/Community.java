package at.fhj.swd.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity(name = Community.ENTITY_NAME)
@Table(name = "tbl_Community")

public class Community implements IEntity {

	public static final String ENTITY_NAME = "Community";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "F_NAME", nullable = false)
	private String name;
	
	@Column(name = "F_DESC", nullable = false)
	private String description;
	

	@ManyToMany(cascade=CascadeType.MERGE, targetEntity=User.class)
	@JoinTable(name = "tbl_community_user")
	private Collection<User> users;
	
	@ManyToOne(cascade=CascadeType.DETACH, targetEntity=User.class)
	private User admin;
	
	
	@ManyToMany(cascade=CascadeType.MERGE, targetEntity = Post.class)
	@JoinTable(name = "tbl_post_community")
	private Collection<Post> posts;

	@ManyToMany(cascade=CascadeType.MERGE, targetEntity = Post.class)
	@JoinTable(name = "tbl_document_community")
	private Collection<Document> documents;

	
	
	public Collection<User> getUsers() {
		return users;
	}


	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	public void addUser(User user){
		
		Collection<User> _ul = this.getUsers();
		
		if (_ul == null)			
			_ul = new ArrayList<User>();
		
		if (!_ul.contains(user))
			_ul.add(user);
	
		this.setUsers(_ul);
		
//		if (!user.getCommunities().contains(this))
//			user.addCommunity(this);
	
	}
	
	public void removeUser(User user){
		Collection<User> _ul = this.getUsers();
		
		if (_ul == null)			
			_ul = new ArrayList<User>();
		
		if (_ul.contains(user))
			_ul.remove(user);
	
		this.setUsers(_ul);
		
//		if (user.getCommunities().contains(this))
//			user.removeCommunity(this);
	}

	public Collection<Post> getPosts() {
		return posts;
	}


	public void setPosts(Collection<Post> posts) {
		this.posts = posts;
	}
	
	public void addPost(Post post){
		
		Collection<Post> _pl = this.getPosts();
		
		if (_pl == null)			
			_pl = new ArrayList<Post>();
		
		if (!_pl.contains(post))
			_pl.add(post);
	
		this.setPosts(_pl);
		
		if (!post.getCommunities().contains(this))
			post.addCommunity(this);
		
	}
	
	public void removePost(Post post){
		Collection<Post> _pl = this.getPosts();
		
		if (_pl == null)			
			_pl = new ArrayList<Post>();
		
		if (_pl.contains(post))
			_pl.remove(post);
	
		this.setPosts(_pl);
		
		if (post.getCommunities().contains(this))
			post.removeCommunity(this);
	}



	public Collection<Document> getDocuments() {
		return documents;
	}


	public void setDocuments(Collection<Document> documents) {
		this.documents = documents;
	}

	public void addDocument(Document document){
		
		Collection<Document> _dl = this.getDocuments();
		
		if (_dl == null)			
			_dl = new ArrayList<Document>();
		
		if (!_dl.contains(document))
			_dl.add(document);
	
		this.setDocuments(_dl);
		
		if (!document.getCommunities().contains(this))
			document.addCommunity(this);		
	}
	
	public void removeDocument(Document document){
		
		Collection<Document> _dl = this.getDocuments();
		
		if (_dl == null)			
			_dl = new ArrayList<Document>();
		
		if (_dl.contains(document))
			_dl.remove(document);
	
		this.setDocuments(_dl);
		
		if (document.getCommunities().contains(this))
			document.removeCommunity(this);		
	}
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public User getAdmin() {
		return admin;
	}


	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	
	public String toString() {
		return "ID = "+ getId() + ": name = " + name + ": description = " + description; 
	}
	
	@Override
	public String getEntityName() {
		return Community.ENTITY_NAME;
	}

	

}