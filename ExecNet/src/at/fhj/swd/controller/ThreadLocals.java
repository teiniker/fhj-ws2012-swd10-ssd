package at.fhj.swd.controller;

import at.fhj.swd.domain.Post;

public abstract class ThreadLocals {

    private static final ThreadLocal<String> errorMessage = new ThreadLocal<String>();
    private static final ThreadLocal<Post> postToEdit = new ThreadLocal<Post>();

    public static String getErrorMessage() {
        return errorMessage.get();
    }

    public static void setErrorMessage(String value) {
        errorMessage.set(value);
    }

    public static Post getPostToEdit() {
        return postToEdit.get();
    }

    public static void setPostToEdit(Post value) {
        postToEdit.set(value);
    }
}
