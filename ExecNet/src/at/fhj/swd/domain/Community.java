package at.fhj.swd.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
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

    @ManyToMany(cascade = CascadeType.REFRESH, targetEntity = User.class)
    @JoinTable(name = "tbl_community_user")
    private Collection<User> users;

    @ManyToOne(cascade = CascadeType.DETACH, targetEntity = User.class)
    private User admin;

    /*
     * @ManyToMany(cascade = CascadeType.MERGE, targetEntity = Post.class)
     * 
     * @JoinTable(name = "tbl_post_community")
     * private Collection<Post> posts;
     */

    @ManyToMany(cascade = CascadeType.MERGE, targetEntity = Document.class)
    private Collection<Document> documents;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Post.class, mappedBy = "community")
    private Collection<Post> posts;

    @PreRemove
    public void preRemove() {
        for (User u : this.getUsers()) {
            u.getCommunities().remove(this);
        }
        for (Post p : this.getPosts()) {
            if (p.getCommunity().equals(this)) {
                p.setCommunity(null);
            }
        }
        for (Document d : this.getDocuments()) {
            d.getCommunities().remove(this);
        }
    }

    public Community() {
    }

    public void addUser(User user) {
        Collection<User> _ul = this.getUsers();
        if (_ul == null) {
            _ul = new ArrayList<User>();
        }
        if (!_ul.contains(user)) {
            _ul.add(user);
        }
        this.setUsers(_ul);
    }

    public void removeUser(User user) {
        Collection<User> _ul = this.getUsers();
        if (_ul == null) {
            _ul = new ArrayList<User>();
        }
        if (_ul.contains(user)) {
            _ul.remove(user);
        }
        this.setUsers(_ul);
    }

    public void addPost(Post post) {
        Collection<Post> _pl = this.getPosts();
        if (_pl == null) {
            _pl = new ArrayList<Post>();
        }
        if (!_pl.contains(post)) {
            _pl.add(post);
        }
        this.setPosts(_pl);
    }

    public void removePost(Post post) {
        Collection<Post> _pl = this.getPosts();
        if (_pl == null) {
            _pl = new ArrayList<Post>();
        }
        if (_pl.contains(post)) {
            _pl.remove(post);
        }
        this.setPosts(_pl);
    }

    public void addDocument(Document document) {
        Collection<Document> _dl = this.getDocuments();
        if (_dl == null) {
            _dl = new ArrayList<Document>();
        }
        if (!_dl.contains(document)) {
            _dl.add(document);
        }
        this.setDocuments(_dl);
    }

    public void removeDocument(Document document) {
        Collection<Document> _dl = this.getDocuments();
        if (_dl == null) {
            _dl = new ArrayList<Document>();
        }
        if (_dl.contains(document)) {
            _dl.remove(document);
        }
        this.setDocuments(_dl);
    }

    @Override
    public String toString() {
        return "ID = " + getId() + ": name = " + name + ": description = " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Community other = (Community)obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public String getEntityName() {
        return Community.ENTITY_NAME;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public Collection<Post> getPosts() {
        return posts;
    }

    /**
     * Posts from Community betwenn DateFrom and DateTo
     * 
     * @param dtNow
     * @return Posts
     */
    public Collection<Post> getPosts(Date dtNow) {
        List<Post> postsNew = new ArrayList<Post>();
        for (Post p : posts) {
            if (p.getDatefrom().before(dtNow) && p.getDateto().after(dtNow)) {
                postsNew.add(p);
            }
        }
        return postsNew;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }

    public Collection<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Collection<Document> documents) {
        this.documents = documents;
    }

    // Fustef: zu testzwecken
    public void setId(Long id) {
        this.id = id;
    }
}
