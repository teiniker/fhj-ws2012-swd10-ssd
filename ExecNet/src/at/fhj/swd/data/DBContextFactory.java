package at.fhj.swd.data;

import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;
import at.fhj.swd.domain.News;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

public final class DBContextFactory implements IDataContextFactory {

	/* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContextFactory#getUserContext()
	 */
    @Override
	public IDataContext<User> getUserContext() {
        return new DBContext<User>();
    }

    /* (non-Javadoc)
     * @see at.fhj.swd.data.IDataContextFactory#getNewsContext()
     */
    @Override
    public IDataContext<News> getNewsContext() {
        return new DBContext<News>();
    }
    
    /* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContextFactory#getPostContext()
	 */
    @Override
	public IDataContext<Post> getPostContext() {
        return new DBContext<Post>();
    }

    /* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContextFactory#getCommunityContext()
	 */
    @Override
	public IDataContext<Community> getCommunityContext() {
        return new DBContext<Community>();
    }

    /* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContextFactory#getCommentContext()
	 */
    @Override
	public IDataContext<Comment> getCommentContext() {
        return new DBContext<Comment>();
    }

    /* (non-Javadoc)
	 * @see at.fhj.swd.data.IDataContextFactory#getDocumentContext()
	 */
    @Override
	public IDataContext<Document> getDocumentContext() {
        return new DBContext<Document>();
    }

}
