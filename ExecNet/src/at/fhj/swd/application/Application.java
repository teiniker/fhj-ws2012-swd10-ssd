package at.fhj.swd.application;

import at.fhj.swd.data.DBContext;
import at.fhj.swd.data.DBContextFactory;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.data.IDataContextFactory;
import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;
import at.fhj.swd.domain.News;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

/**
 * Application is the Context initialization class, acts as assembler.
 */
public class Application {

    private static Application _instance;

    private IRuntimeContext _runtime;
    private IDataContextFactory _df;
    
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
        
    	this.setDataContextFactory(new DBContextFactory());
    	this.setRuntime(new WebRuntimeContext());
        
    }

    public IRuntimeContext getRuntime() {
        return _runtime;
    }

    public void setRuntime(IRuntimeContext runtime) {
        _runtime = runtime;
    }

    public IDataContextFactory getDataContextFactory() {
		return _df;
	}

	public void setDataContextFactory(IDataContextFactory _df) {
		this._df = _df;
	}

	public IDataContext<User> getUserContext() {
        return _df.getUserContext();
    }

    public IDataContext<Post> getPostContext() {
        return _df.getPostContext();
    }

    public IDataContext<Community> getCommunityContext() {
        return _df.getCommunityContext();
    }

    public IDataContext<Comment> getCommentContext() {
        return _df.getCommentContext();
    }

    public IDataContext<Document> getDocumentContext() {
        return _df.getDocumentContext();
    }
    public IDataContext<News> getNewsContext() {
        return _df.getNewsContext();
    }
}
