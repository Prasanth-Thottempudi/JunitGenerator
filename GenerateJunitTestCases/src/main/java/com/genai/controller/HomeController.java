package com.genai.controller;




import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
    private static Map<String, StringBuilder> packageClassMap = new HashMap<>();
	
	@GetMapping("/demo")
	public String checking() {
		return "demo checking";
	}
	
	@GetMapping("/list")
	public static Map<String, StringBuilder> getAll(){
		 String projectDir = "C:/Users/PRASANTH/Desktop/Gen AI project/GenerateJunitTestCases";

	        // Start scanning the project folder
	        scanFolder(new File(projectDir));

	        // Print out the package-wise classes
	        packageClassMap.forEach((packageName, classes) -> {
	            System.out.println("Package: " + packageName);
	            System.out.println("Classes: " + classes.toString());
	            System.out.println();
	        });
	        return packageClassMap;
	}
	
	private static void scanFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanFolder(file); // Recurse into directories
                } else if (file.getName().endsWith(".java")) {
                    processJavaFile(file);
                }
            }
        }
    }

    // Method to extract package and class name from the .java file
    private static void processJavaFile(File file) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
            String packageName = getPackageName(content);
            String className = getClassName(content);

            if (packageName != null && className != null) {
                packageClassMap.computeIfAbsent(packageName, k -> new StringBuilder()).append(className).append(", ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getPackageName(String fileContent) {
        for (String line : fileContent.split("\\n")) {
            if (line.startsWith("package ")) {
                return line.replace("package", "").replace(";", "").trim();
            }
        }
        return "default";
    }

    private static String getClassName(String fileContent) {
        for (String line : fileContent.split("\\n")) {
            line = line.trim();
            if (line.matches("(public |private |protected )?(class|interface|enum)\\s+\\w+.*")) {
                String[] parts = line.split("\\s+");
                for (int i = 0; i < parts.length; i++) {
                    if (parts[i].equals("class") || parts[i].equals("interface") || parts[i].equals("enum")) {
                        return parts[i + 1]; 
                    }
                }
            }
        }
        return null;
    }

	

}
