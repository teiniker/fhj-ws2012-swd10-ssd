package at.fhj.swd.application;


import at.fhj.swd.data.*;
import at.fhj.swd.domain.*;


/**
 * Application is the Context initialization class
 * acts as assembler
 * 
 */
public class Application {

    private static Application _instance;

    private IRuntimeContext _runtime;
    private IDataContext<User> _users;
    private IDataContext<Post> _posts;
    private IDataContext<Comment> _comments;
    private IDataContext<Community> _communities;
    private IDataContext<Document> _documents;

    public Application() {
        _instance = this;
        this.assembleApplication();
    }

    public static Application getInstance() {
        if (_instance == null) {
            _instance = new Application();
        }
        return _instance;
    }

    private void assembleApplication() {
        _posts = new DBContext<Post>();
        _communities = new DBContext<Community>();
        _comments = new DBContext<Comment>();
        _users = new DBContext<User>();
        _documents = new DBContext<Document>();
        _runtime = new WebRuntimeContext();
    }

    public IRuntimeContext getRuntime() {
        return _runtime;
    }

    public void setRuntime(IRuntimeContext runtime) {
        _runtime = runtime;
    }

    public IDataContext<User> getUserContext() {
        return _users;
    }

    public IDataContext<Post> getPostContext() {
        return _posts;
    }

    public IDataContext<Community> getCommunityContext() {
        return _communities;
    }

    public IDataContext<Comment> getCommentContext() {
        return _comments;
    }

    public IDataContext<Document> getDocumentContext() {
        return _documents;
    }

}
