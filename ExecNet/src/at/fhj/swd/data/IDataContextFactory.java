package at.fhj.swd.data;

import at.fhj.swd.domain.Comment;
import at.fhj.swd.domain.Community;
import at.fhj.swd.domain.Document;
import at.fhj.swd.domain.News;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

public interface IDataContextFactory {

	public abstract IDataContext<User> getUserContext();

	public abstract IDataContext<Post> getPostContext();

	public abstract IDataContext<News> getNewsContext();
	
	public abstract IDataContext<Community> getCommunityContext();

	public abstract IDataContext<Comment> getCommentContext();

	public abstract IDataContext<Document> getDocumentContext();

}