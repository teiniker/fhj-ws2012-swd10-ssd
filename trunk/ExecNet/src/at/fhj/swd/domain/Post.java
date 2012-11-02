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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = Post.ENTITY_NAME)
@Table(name = "tbl_Post")
public class Post implements IEntity {

    public static final String ENTITY_NAME = "Post";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "F_DATE", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "F_ENTRY", nullable = false)
    private String entry;

    @Column(name = "F_ISPUBLIC", nullable = false)
    private boolean ispublic = true;

    @ManyToOne(targetEntity = User.class)
    private User author;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(nullable = true)
    private User pinboard;

    @ManyToMany(mappedBy = "posts", cascade = CascadeType.ALL, targetEntity = Community.class)
    private Collection<Community> communities;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Comment.class)
    private Collection<Comment> comments;

    public Post() {
    }

    public void addComment(Comment comment) {
        Collection<Comment> _cl = this.getComments();
        if (_cl == null) {
            _cl = new ArrayList<Comment>();
        }
        if (!_cl.contains(comment)) {
            _cl.add(comment);
        }
        this.setComments(_cl);
        if (!comment.getPost().equals(this)) {
            comment.setPost(this);
        }
    }

    public void removeComment(Comment comment) {
        Collection<Comment> _cl = this.getComments();
        if (_cl == null) {
            _cl = new ArrayList<Comment>();
        }
        if (_cl.contains(comment)) {
            _cl.remove(comment);
        }
        this.setComments(_cl);
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
        if (!community.getPosts().contains(this)) {
            community.addPost(this);
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
        if (community.getPosts().contains(this)) {
            community.removePost(this);
        }
    }

    public String getPostDate() {
        SimpleDateFormat dateformatDDMMYYYY = new SimpleDateFormat("dd.MM.yyyy");
        StringBuilder formatedDate = new StringBuilder(dateformatDDMMYYYY.format(date));
        return formatedDate.toString();
    }

    @Override
    public String toString() {
        return "ID = " + id + ", user = " + pinboard.getUsername() + ", date = " + date + ": entry = " + entry;
    }

    @Override
    public String getEntityName() {
        return Post.ENTITY_NAME;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public boolean isPublic() {
        return ispublic;
    }

    public void setPublic(boolean ispublic) {
        this.ispublic = ispublic;
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

    public Collection<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Collection<Community> communities) {
        this.communities = communities;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }
}
