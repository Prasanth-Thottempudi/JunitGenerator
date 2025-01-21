package com.genai.service;


import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class GitCloneService {

    public void cloneRepository(String repoUrl, String localPath) {
        try {
            // Clone the GitHub repository to the specified local path
            Git.cloneRepository()
                .setURI(repoUrl)
                .setDirectory(new File(localPath))
                .call();
            System.out.println("Repository cloned successfully.");
        } catch (GitAPIException e) {
            System.err.println("Git API error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error cloning repository: " + e.getMessage());
        }
    }
}
