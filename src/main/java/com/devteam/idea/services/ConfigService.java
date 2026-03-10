package com.devteam.idea.services;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service
@State(
    name = "DevTeamConfig",
    storages = @Storage("devteam.xml")
)
public final class ConfigService implements PersistentStateComponent<ConfigService.State> {
    
    public static class State {
        public String apiKey = "";
        public String apiUrl = "https://api.anthropic.com";
        public String model = "claude-sonnet-4-6";
        public int maxTokens = 8192;
        public double temperature = 0.7;
        public String workspaceRoot = "./devteam-workspace";
        public boolean enableCache = true;
        public boolean autoFix = true;
        public boolean validate = true;
    }

    private State state = new State();

    @Nullable
    @Override
    public State getState() {
        return state;
    }

    @Override
    public void loadState(@NotNull State state) {
        this.state = state;
    }

    // Getters
    public String getApiKey() { return state.apiKey; }
    public String getApiUrl() { return state.apiUrl; }
    public String getModel() { return state.model; }
    public int getMaxTokens() { return state.maxTokens; }
    public double getTemperature() { return state.temperature; }
    public String getWorkspaceRoot() { return state.workspaceRoot; }
    public boolean isEnableCache() { return state.enableCache; }
    public boolean isAutoFix() { return state.autoFix; }
    public boolean isValidate() { return state.validate; }

    // Setters
    public void setApiKey(String apiKey) { state.apiKey = apiKey; }
    public void setApiUrl(String apiUrl) { state.apiUrl = apiUrl; }
    public void setModel(String model) { state.model = model; }
    public void setMaxTokens(int maxTokens) { state.maxTokens = maxTokens; }
    public void setTemperature(double temperature) { state.temperature = temperature; }
    public void setWorkspaceRoot(String workspaceRoot) { state.workspaceRoot = workspaceRoot; }
    public void setEnableCache(boolean enableCache) { state.enableCache = enableCache; }
    public void setAutoFix(boolean autoFix) { state.autoFix = autoFix; }
    public void setValidate(boolean validate) { state.validate = validate; }
}
