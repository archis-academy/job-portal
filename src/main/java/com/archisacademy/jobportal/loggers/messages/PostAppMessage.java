package com.archisacademy.jobportal.loggers.messages;

public final class PostAppMessage {

    private PostAppMessage() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static final String POST_CREATED_SUCCESS = "Post created successfully.";
    public static final String POST_UPDATED_SUCCESS = "Post updated successfully.";
    public static final String POST_DELETED_SUCCESS = "Post deleted successfully.";
    public static final String POST_NOT_FOUND = "Post not found with id: ";
    public static final String USER_NOT_FOUND = "User not found with UUID: ";
    public static final String USER_NOT_ALLOWED_TO_CREATE_POST = "User is not allowed to create a post.";
    public static final String POST_LIKED_SUCCESS = "Post has been successfully liked.";
    public static final String COMMENT_ADDED_SUCCESS = "Comment has been successfully added.";
    public static final String USER_ALREADY_LIKED_POST = "User has already liked this post.";
}