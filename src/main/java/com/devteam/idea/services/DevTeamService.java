package com.devteam.idea.services;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.components.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public final class DevTeamService {
    private final Project project;
    private Process currentProcess;

    public DevTeamService(Project project) {
        this.project = project;
    }

    /**
     * Execute DevTeam CLI command
     */
    public void execute(String command, String requirement, Consumer<String> outputCallback) {
        try {
            // Build command
            List<String> cmd = new ArrayList<>();
            cmd.add("devteam");
            cmd.add(command);
            cmd.add(requirement);

            // Create process
            ProcessBuilder pb = new ProcessBuilder(cmd);
            pb.directory(new java.io.File(project.getBasePath()));
            pb.redirectErrorStream(true);

            // Start process
            currentProcess = pb.start();

            // Read output
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(currentProcess.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                outputCallback.accept(line);
            }

            int exitCode = currentProcess.waitFor();
            if (exitCode == 0) {
                outputCallback.accept("\n✅ DevTeam execution completed successfully!");
            } else {
                outputCallback.accept("\n❌ DevTeam execution failed with code: " + exitCode);
            }

        } catch (Exception e) {
            outputCallback.accept("\n❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Stop current execution
     */
    public void stop() {
        if (currentProcess != null && currentProcess.isAlive()) {
            currentProcess.destroy();
            currentProcess = null;
        }
    }

    /**
     * Check if DevTeam CLI is installed
     */
    public boolean isDevTeamInstalled() {
        try {
            Process process = Runtime.getRuntime().exec("devteam --version");
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get DevTeam CLI version
     */
    public String getDevTeamVersion() {
        try {
            Process process = Runtime.getRuntime().exec("devteam --version");
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            return output.toString();
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
