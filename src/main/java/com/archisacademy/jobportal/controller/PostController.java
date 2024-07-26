package com.archisacademy.jobportal.controller;

import com.archisacademy.jobportal.dto.CommentDto;
import com.archisacademy.jobportal.dto.PostDto;
import com.archisacademy.jobportal.services.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        PostDto postDto = postService.getPostById(postId);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping
    public ResponseEntity<Page<PostDto>> getAllPosts(@RequestParam int pageNo, @RequestParam int pageSize) {
        Page<PostDto> posts = postService.getAllPosts(pageNo, pageSize);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
        String response = postService.updatePost(postId, postDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId) {
        String response = postService.deletePost(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{postId}/like/{userUuid}")
    public ResponseEntity<String> likePost(@PathVariable Long postId, @PathVariable String userUuid) {
        String response = postService.likePost(postId, userUuid);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<String> addComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
        String response = postService.addComment(postId, commentDto);
        return ResponseEntity.ok(response);
    }
}
