package com.devteam.idea.settings;

import com.devteam.idea.services.ConfigService;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class DevTeamSettingsConfigurable implements Configurable {
    private DevTeamSettingsPanel settingsPanel;
    private final Project project;

    public DevTeamSettingsConfigurable(Project project) {
        this.project = project;
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "DevTeam";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        settingsPanel = new DevTeamSettingsPanel();
        return settingsPanel.getPanel();
    }

    @Override
    public boolean isModified() {
        ConfigService config = project.getService(ConfigService.class);
        return settingsPanel.isModified(config);
    }

    @Override
    public void apply() {
        ConfigService config = project.getService(ConfigService.class);
        settingsPanel.apply(config);
    }

    @Override
    public void reset() {
        ConfigService config = project.getService(ConfigService.class);
        settingsPanel.reset(config);
    }

    @Override
    public void disposeUIResources() {
        settingsPanel = null;
    }
}
