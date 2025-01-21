package com.genai.service;


import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class PackageListService {

    public List<String> getPackages(String applicationPath) {
        List<String> packages = new ArrayList<>();
        File srcDirectory = new File(applicationPath + "/src/main/java");

        if (srcDirectory.exists() && srcDirectory.isDirectory()) {
            // Recursively list package names (i.e., folder structure under 'src/main/java')
            listPackages(srcDirectory, srcDirectory, packages);
        } else {
            System.err.println("The specified path is not a valid 'src/main/java' directory.");
        }

        return packages;
    }

    private void listPackages(File root, File currentDir, List<String> packages) {
        File[] files = currentDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Get relative path for package name
                    String relativePath = file.getAbsolutePath().substring(root.getAbsolutePath().length() + 1)
                            .replace(File.separatorChar, '.');
                    packages.add(relativePath);
                    // Recursively list sub-packages
                    listPackages(root, file, packages);
                }
            }
        }
    }
}
