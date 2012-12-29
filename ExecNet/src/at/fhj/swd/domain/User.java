package at.fhj.swd.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = User.ENTITY_NAME)
@Table(name = "tbl_User")
public class User implements IEntity {

    public static final String ENTITY_NAME = "User";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "F_FNAME", unique = true)
    private String firstname;

    @Column(name = "F_LNAME", unique = true)
    private String lastname;

    @Column(name = "F_DEPT", unique = true)
    private String department;

    @Column(name = "F_LOC", unique = true)
    private String location;

    @Column(name = "F_UNAME", unique = true)
    private String username;

    @Column(name = "F_PASS", nullable = false)
    private String password;

    @Column(name = "F_EMAIL", nullable = false)
    private String email;

    @Column(name = "F_CULTURE", nullable = false)
    private String culture = "en";

    @Column(name = "F_ISADMIN", nullable = false)
    private boolean isAdmin = false;

    @Column(name = "F_ISPORTALADMIN", nullable = false)
    private boolean isPortalAdmin = false;

    @Column(name = "F_ISACTIVE", nullable = false)
    private boolean isActive = false;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "users", targetEntity = Community.class)
    private Collection<Community> communities;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Post.class, mappedBy = "pinboard")
    private Collection<Post> pinposts;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Comment.class)
    private Collection<Comment> comments;


    @Transient
    private boolean _isloggedin = false;

    @PreRemove
    public void preRemove() {
        for (Community c : this.getCommunities()) {
            c.getUsers().remove(this);
        }
        this.setPinPosts(null);
        this.setComments(null);
    }

    public User() {
    }

    public void addCommunity(Community community) {
        Collection<Community> _cl = this.getCommunities();
        if (_cl == null) {
            _cl = new ArrayList<Community>();
        }
        if (!_cl.contains(community)) {
            _cl.add(community);
        }
        this.setCommunities(_cl);
    }

    public void removeCommunity(Community community) {
        Collection<Community> _cl = this.getCommunities();
        if (_cl == null) {
            _cl = new ArrayList<Community>();
        }
        if (_cl.contains(community)) {
            _cl.remove(community);
        }
        this.setCommunities(_cl);
    }

    public void addPinPost(Post post) {
        Collection<Post> _pl = this.getPinPosts();
        if (_pl == null) {
            _pl = new ArrayList<Post>();
        }
        if (!_pl.contains(post)) {
            _pl.add(post);
        }
        this.setPinPosts(_pl);
        if (post.getPinboard() != null && !post.getPinboard().equals(this)) {
            post.setPinboard(this);
        }
    }

    public void removePinPost(Post post) {
        Collection<Post> _pl = this.getPinPosts();
        if (_pl == null) {
            _pl = new ArrayList<Post>();
        }
        if (_pl.contains(post)) {
            _pl.remove(post);
        }
        this.setPinPosts(_pl);
    }

    public void authenticate() {
        this._isloggedin = true;
    }

    public boolean isLoggedIn() {
        return this._isloggedin;
    }

    @Override
    public String toString() {
        return "ID = " + id + ", username = " + username + ", isAdmin = " + isAdmin + ", isPortalAdmin = "
            + isPortalAdmin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User)obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        return result;
    }

    @Override
    public String getEntityName() {
        return User.ENTITY_NAME;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String _username) {
        this.username = _username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String _email) {
        this.email = _email;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        if (culture.equalsIgnoreCase("en") || culture.equalsIgnoreCase("de"))
            this.culture = culture;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isPortalAdmin() {
        return isPortalAdmin;
    }

    public void setAdmin(boolean _isAdmin) {
        this.isAdmin = _isAdmin;
    }

    public void setPortalAdmin(boolean _isPortalAdmin) {
        this.isPortalAdmin = _isPortalAdmin;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean _isActive) {
        this.isActive = _isActive;
    }

    public Collection<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Collection<Community> communities) {
        this.communities = communities;
    }

    public Collection<Post> getPinPosts() {
        return this.pinposts;
    }

    public void setPinPosts(Collection<Post> _tasks) {
        this.pinposts = _tasks;
    }

    public Collection<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Collection<Comment> _comments) {
        this.comments = _comments;
    }
}
