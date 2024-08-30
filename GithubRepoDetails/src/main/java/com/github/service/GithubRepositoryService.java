package com.github.service;

import com.github.entity.GithubRepository;

public interface GithubRepositoryService {
	public GithubRepository getRepository(String owner, String repoName);

}
