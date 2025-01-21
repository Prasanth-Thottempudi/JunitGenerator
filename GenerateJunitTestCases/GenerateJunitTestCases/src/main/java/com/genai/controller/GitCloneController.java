package com.genai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genai.service.GitCloneService;

@RestController
public class GitCloneController {

    @Autowired
    private GitCloneService gitCloneService;

    @GetMapping("/clone-repo")
    public String cloneRepository(@RequestParam String repoUrl, @RequestParam String localPath) {
        gitCloneService.cloneRepository(repoUrl, localPath);
        return "Cloning started, check the logs for progress.";
    }
}