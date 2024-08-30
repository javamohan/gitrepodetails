package com.github.repository;


import com.github.entity.GithubRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubRepositoryJPARepository extends JpaRepository<GithubRepository, String> {}
