package com.github.controller;

import com.github.entity.GithubRepository;
import com.github.service.GithubRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repositories")
public class GithubRepositoryController {
    private static final Logger log = LoggerFactory.getLogger(GithubRepositoryController.class);
    @Autowired
    private GithubRepositoryService githubRepositoryService;

    @GetMapping("/{owner}/{repoName}")
    public GithubRepository getRepository(@PathVariable String owner, @PathVariable String repoName) {
        return githubRepositoryService.getRepository(owner, repoName);
    }


    @RequestMapping("/error")
    public ResponseEntity<String> handleError() {
        log.error("An error occurred please check the URL and try again");
        return ResponseEntity.ok("An error occurred please check the URL and try again");
    }
}