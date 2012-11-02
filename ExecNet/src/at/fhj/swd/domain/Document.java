package at.fhj.swd.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = Document.ENTITY_NAME)
@Table(name = "tbl_Document")
public class Document implements IEntity {
	
	public static final String ENTITY_NAME = "Document";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "F_DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datecreated;

	@Column(name = "F_NAME", nullable = false)
	private String name;
		
	@Column(name = "F_URL", nullable = false)
	private String url;
	
	@Column(name = "F_ISPUBLIC", nullable = false)
	private boolean ispublic = true;
	
	
	@ManyToOne
	private User owner;
	
	@ManyToMany(cascade=CascadeType.ALL, targetEntity=Community.class)
	@JoinTable(name = "tbl_document_community")
	private Collection<Community> communities;
	
	public Date getDateCreated() {
		return datecreated;
	}
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Collection<Community> getCommunities() {
		return communities;
	}

	public void setCommunities(Collection<Community> communities) {
		this.communities = communities;
	}

	public void addCommunity(Community community){
		
		Collection<Community> _cl = this.getCommunities();
		
		if (_cl == null)			
			_cl = new ArrayList<Community>();
		
		if (!_cl.contains(community))
			_cl.add(community);
	
		this.setCommunities(_cl);
		
		if (!community.getPosts().contains(this))
			community.addDocument(this);
		
	}
	
	public void removeCommunity(Community community){
	Collection<Community> _cl = this.getCommunities();
		
		if (_cl == null)			
			_cl = new ArrayList<Community>();
		
		if (_cl.contains(community))
			_cl.remove(community);
	
		this.setCommunities(_cl);
		
		if (community.getPosts().contains(this))
			community.removeDocument(this);
	}

	public boolean isPublic() {
		return ispublic;
	}

	public void setPublic(boolean ispublic) {
		this.ispublic = ispublic;
	}
	
	
	public String toString() {
		return "ID = "+ id + ", owner = " + owner.getUsername() + ", datecreated = " + datecreated + ": url = " + url; 
	}
	
		
	public String getPostDate() {
		 SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd.MM.yyyy");
		 StringBuilder formatedDate = new StringBuilder( dateformatDDMMYYYY.format( datecreated ));
		 return formatedDate.toString();
	}


	@Override
	public String getEntityName() {
		return Document.ENTITY_NAME;
	}


	@Override
	public Long getId() {
		return this.id;
	}
	
}
