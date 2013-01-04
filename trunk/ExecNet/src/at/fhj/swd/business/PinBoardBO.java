package at.fhj.swd.business;

import java.util.ArrayList;
import java.util.Collection;

import at.fhj.swd.application.Application;
import at.fhj.swd.data.IDataContext;
import at.fhj.swd.domain.Post;
import at.fhj.swd.domain.User;

public class PinBoardBO extends ABusinessObject {

    private IDataContext<Post> _context;

    public PinBoardBO() {
        super();
        this._context = Application.getInstance().getPostContext();
    }

    public boolean add(Post post) {
        try {
            User _u = this.getRuntimeContext().getCurrentUser();
            _u.addPinPost(post);

            if (_context.create(post)) {
                logger.info("Post creation successful");
                return true;
            } else {
                logger.info("Post creation not successful");
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Boolean delete(Long id) {
        try {
            Post post = _context.readOne(id, Post.class);

            if (_context.delete(post)) {
                logger.info("Post deletion successful");
                return true;
            } else {
                logger.info("Post deletion not successful");
                return false;
            }
        } catch (Exception e) {
            logger.error(e);
            return false;
        }
    }

    public Collection<Post> getAll() {
        try {
            return _context.readAll(Post.class);
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public Collection<Post> getMy() {
        try {
            User _u = this.getRuntimeContext().getCurrentUser();

            Collection<Post> resultingPosts = new ArrayList<Post>();
            Collection<Post> allPosts = _context.readAll(Post.class);

            for (Post post : allPosts) {
                if (post.getAuthor().getUsername() == _u.getUsername()) {
                    resultingPosts.add(post);
                }
            }

            return resultingPosts;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

    public Collection<Post> getOthers(Long id) {
        try {
            Collection<Post> resultingPosts = new ArrayList<Post>();
            Collection<Post> allPosts = _context.readAll(Post.class);

            for (Post post : allPosts) {
                if (post.getAuthor().getId() == id) {
                    resultingPosts.add(post);
                }
            }

            return resultingPosts;
        } catch (Exception e) {
            logger.error(e);
            return null;
        }
    }

}
