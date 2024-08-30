package com.github.test;
import com.github.app.GithubRepoInfoApplication;
import com.github.entity.GithubRepository;
import com.github.exception.RepositoryNotFoundException;
import com.github.serviceImpl.GithubRepositoryServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GithubRepoInfoApplication.class)
public class GithubRepositoryServiceTest {
    @Autowired
    private GithubRepositoryServiceImpl githubRepositoryServiceImpl;

    @Test
    public void testGetRepository() {
        GithubRepository repository = githubRepositoryServiceImpl.getRepository("octocat", "Hello-World");
        assertNotNull(repository);
        assertEquals("octocat/Hello-World", repository.getFullName());
    }

    @Test
    public void testGetNonExistentRepository() {
        assertThrows(RepositoryNotFoundException.class, () -> {
        	githubRepositoryServiceImpl.getRepository("octocat", "NonExistentRepo");
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRepositoryWithInvalidInput() {
    	githubRepositoryServiceImpl.getRepository("", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetRepositoryWithNullInput() {
    	githubRepositoryServiceImpl.getRepository(null, null);
    }

    @Test
    public void testCachingBehavior() {
        GithubRepository repository1 = githubRepositoryServiceImpl.getRepository("octocat", "Hello-World");
        GithubRepository repository2 = githubRepositoryServiceImpl.getRepository("octocat", "Hello-World");
        assertEquals(repository1, repository2);
    }
}
