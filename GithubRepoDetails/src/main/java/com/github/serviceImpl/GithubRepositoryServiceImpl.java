package com.github.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.entity.GithubRepository;
import com.github.exception.RepositoryNotFoundException;
import com.github.repository.GithubRepositoryJPARepository;
import com.github.service.GithubRepositoryService;


@Service
public class GithubRepositoryServiceImpl implements GithubRepositoryService{
	 private static final Logger logger = LoggerFactory.getLogger(GithubRepositoryServiceImpl.class);
	 
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 @Autowired
	 private GithubRepositoryJPARepository githubRepositoryJPARepository;

	@Override
	public GithubRepository getRepository(String owner, String repoName) {
        if (owner == null || owner.isEmpty() || repoName == null || repoName.isEmpty()) {
            throw new IllegalArgumentException("Owner and repoName must be provided");
        }
        String id = owner + "/" + repoName;
        GithubRepository repository = githubRepositoryJPARepository.findById(id).orElse(null);
        if (repository == null) {
            try {
                String url = "https://api.github.com/repos/" + owner + "/" + repoName;
                logger.info("Making request to GitHub API: {}", url);
                ResponseEntity<GithubRepository> response = restTemplate.getForEntity(url, GithubRepository.class);
                logger.info("Response from GitHub API: {}", response.getBody().toString());
                repository = response.getBody();

                GithubRepository repositoryWithCorrectId = new GithubRepository();
                repositoryWithCorrectId.setId(id);
                repositoryWithCorrectId.setFullName(repository.getFullName());
                repositoryWithCorrectId.setCloneUrl(repository.getCloneUrl());
                repositoryWithCorrectId.setStars(repository.getStars());
                repositoryWithCorrectId.setCreatedAt(repository.getCreatedAt());

                logger.info("Saving repository to database: {}", repositoryWithCorrectId.toString());
                githubRepositoryJPARepository.save(repositoryWithCorrectId);
                repository = repositoryWithCorrectId;
            } catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new RepositoryNotFoundException("Repository not found: " + id);
                } else {
                    throw e;
                }
            }
        }
        return repository;
	}
	 
	 

	

}
