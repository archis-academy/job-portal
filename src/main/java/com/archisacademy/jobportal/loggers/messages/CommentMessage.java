package com.archisacademy.jobportal.loggers.messages;

public final class CommentMessage {

    private CommentMessage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String COMMENT_NOT_FOUND = "Comment not found with id: ";
    public static final String POST_NOT_FOUND = "Post not found with id: ";
    public static final String COMMENT_CREATED_SUCCESS = "Comment created successfully";
    public static final String COMMENT_DELETED_SUCCESS = "Comment deleted successfully";
    public static final String COMMENT_UPDATED_SUCCESS = "Comment updated successfully";
    public static final String COMMENT_DELETED_LOG = "Comment with id %d deleted successfully.";
}
