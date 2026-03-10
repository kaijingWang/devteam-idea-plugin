package com.devteam.idea.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NodeChecker {
    
    /**
     * Check if Node.js is installed
     */
    public static boolean isNodeInstalled() {
        try {
            Process process = Runtime.getRuntime().exec("node --version");
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Node.js version
     */
    public static String getNodeVersion() {
        try {
            Process process = Runtime.getRuntime().exec("node --version");
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            return reader.readLine();
        } catch (Exception e) {
            return "Unknown";
        }
    }

    /**
     * Check if npm is installed
     */
    public static boolean isNpmInstalled() {
        try {
            Process process = Runtime.getRuntime().exec("npm --version");
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get npm version
     */
    public static String getNpmVersion() {
        try {
            Process process = Runtime.getRuntime().exec("npm --version");
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            return reader.readLine();
        } catch (Exception e) {
            return "Unknown";
        }
    }

    /**
     * Check system requirements
     */
    public static SystemRequirements checkRequirements() {
        SystemRequirements req = new SystemRequirements();
        req.nodeInstalled = isNodeInstalled();
        req.nodeVersion = getNodeVersion();
        req.npmInstalled = isNpmInstalled();
        req.npmVersion = getNpmVersion();
        return req;
    }

    public static class SystemRequirements {
        public boolean nodeInstalled;
        public String nodeVersion;
        public boolean npmInstalled;
        public String npmVersion;

        public boolean isValid() {
            return nodeInstalled && npmInstalled;
        }

        public String getMessage() {
            if (!nodeInstalled) {
                return "Node.js is not installed. Please install Node.js 18+ from https://nodejs.org";
            }
            if (!npmInstalled) {
                return "npm is not installed. Please install npm.";
            }
            return "System requirements met: Node.js " + nodeVersion + ", npm " + npmVersion;
        }
    }
}
