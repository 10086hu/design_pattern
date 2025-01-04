package com.tj_JavaEE.controller;

import com.tj_JavaEE.dto.AuditPostInfo;
import com.tj_JavaEE.dto.PostId;
import com.tj_JavaEE.dto.Pst;
import com.tj_JavaEE.entity.Result;
import com.tj_JavaEE.service.PostService;
import com.tj_JavaEE.service.ScoreService;
import com.tj_JavaEE.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/auditPostInfo")
    public ResponseEntity<List<AuditPostInfo>> auditPostInfoList(){
        System.out.println("auditPostInfo");
        return ResponseEntity.ok(postService.auditPostInfoList());
    }

    @PostMapping("/postPass")
    public ResponseEntity<Boolean> postPass(@RequestBody PostId postId){
        System.out.println("postPass");
        postService.postStatusChange(postId.getPostId(),"reviewed");
        return ResponseEntity.ok(true);
    }

    @PostMapping("/postRefund")
    public ResponseEntity<Boolean> postRefund(@RequestBody PostId postId){
        System.out.println("postRefund");
        postService.postStatusChange(postId.getPostId(),"refunded");
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{postId}")
    public Result getPost(@PathVariable long postId) {
        Pst post = postService.getPostById(postId);
        Map<String, Object> decoratedPost = postService.getPostWithDecorators(post);
        return Result.success(decoratedPost);
    }


    @PostMapping
    public Result createPost(@RequestBody Pst pst,@RequestHeader("Authorization") String token){
        token = token.substring(7);
        Claims claims = JwtUtils.parseJwt(token);
        int userId = claims.get("userId",Integer.class);
        pst.setAuthorId(userId);

        postService.createPost(pst);

        // 计算并更新用户积分
        scoreService.processScore("post", userId, "create");

        return Result.success();
    }

    @GetMapping("/search/{keyword}")
    public Result getPostList(@PathVariable String keyword){
        return Result.success(postService.search(keyword));
    }

    @DeleteMapping("/{postId}")
    public Result deletePost(@PathVariable long postId){
        Post post = postService.getPostById(postId);
        postService.deletePost(postId);

        // 计算并更新用户积分
        scoreService.processScore("post", post.getUserId(), "delete");

        return Result.success();
    }

    @PostMapping("/{postId}/like")
    public Result likePost(@PathVariable long postId,@RequestHeader("Authorization") String token){
        token = token.substring(7);
        Claims claims = JwtUtils.parseJwt(token);
        int userId = claims.get("userId",Integer.class);
        postService.likePost(userId,postId);

        return Result.success();
    }

    @PostMapping("/{postId}/report")
    public Result reportPost(@PathVariable long postId,@RequestHeader("Authorization") String token){
        token = token.substring(7);
        Claims claims = JwtUtils.parseJwt(token);
        int userId = claims.get("userId",Integer.class);
        postService.reportPost(postId,userId);
        return Result.success();
    }
}
