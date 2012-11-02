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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = User.ENTITY_NAME)
@Table(name = "tbl_User")
public class User implements IEntity {

    public static final String ENTITY_NAME = "User";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "F_UNAME", unique = true)
    private String _username;

    @Column(name = "F_PASS", nullable = false)
    private String _password;

    @Column(name = "F_EMAIL", nullable = false)
    private String _email;

    @Column(name = "F_CULTURE", nullable = false)
    private String _culture = "en";

    @Column(name = "F_ISADMIN", nullable = false)
    private boolean _isAdmin = false;

    @ManyToMany(cascade = CascadeType.MERGE, mappedBy = "users", targetEntity = Community.class)
    private Collection<Community> communities;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Post.class, mappedBy = "pinboard")
    private Collection<Post> pinposts;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Comment.class)
    private Collection<Comment> comments;

    @Transient
    private boolean _isloggedin = false;

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
        if (!community.getUsers().contains(this)) {
            community.addUser(this);
        }
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
        if (community.getUsers().contains(this)) {
            community.removeUser(this);
        }
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
        if (!post.getPinboard().equals(this)) {
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
    public String getEntityName() {
        return User.ENTITY_NAME;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return _username;
    }

    public void setUsername(String _username) {
        this._username = _username;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getCulture() {
        return _culture;
    }

    public void setCulture(String culture) {
        if (culture.equalsIgnoreCase("en") || culture.equalsIgnoreCase("de"))
            this._culture = culture;
    }

    public boolean isAdmin() {
        return _isAdmin;
    }

    public void setAdmin(boolean _isAdmin) {
        this._isAdmin = _isAdmin;
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
