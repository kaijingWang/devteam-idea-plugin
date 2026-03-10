package com.devteam.idea.settings;

import com.devteam.idea.services.ConfigService;

import javax.swing.*;
import java.awt.*;

public class DevTeamSettingsPanel {
    private JPanel mainPanel;
    private JTextField apiKeyField;
    private JTextField apiUrlField;
    private JTextField modelField;
    private JTextField maxTokensField;
    private JTextField temperatureField;
    private JTextField workspaceRootField;
    private JCheckBox enableCacheCheckBox;
    private JCheckBox autoFixCheckBox;
    private JCheckBox validateCheckBox;

    public DevTeamSettingsPanel() {
        initUI();
    }

    private void initUI() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // LLM Configuration
        addSectionTitle("LLM Configuration", gbc, 0);
        
        apiKeyField = addField("API Key:", gbc, 1);
        apiUrlField = addField("API URL:", gbc, 2);
        modelField = addField("Model:", gbc, 3);
        maxTokensField = addField("Max Tokens:", gbc, 4);
        temperatureField = addField("Temperature:", gbc, 5);

        // Workspace
        addSectionTitle("Workspace", gbc, 6);
        workspaceRootField = addField("Root Directory:", gbc, 7);

        // Options
        addSectionTitle("Options", gbc, 8);
        
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        enableCacheCheckBox = new JCheckBox("Enable cache (24h TTL, 100MB limit)");
        mainPanel.add(enableCacheCheckBox, gbc);

        gbc.gridy = 10;
        autoFixCheckBox = new JCheckBox("Auto fix common issues");
        mainPanel.add(autoFixCheckBox, gbc);

        gbc.gridy = 11;
        validateCheckBox = new JCheckBox("Validate code quality");
        mainPanel.add(validateCheckBox, gbc);
    }

    private void addSectionTitle(String title, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        JLabel label = new JLabel(title);
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        mainPanel.add(label, gbc);
        gbc.gridwidth = 1;
    }

    private JTextField addField(String labelText, GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        mainPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JTextField field = new JTextField(30);
        mainPanel.add(field, gbc);
        
        return field;
    }

    public JPanel getPanel() {
        return mainPanel;
    }

    public boolean isModified(ConfigService config) {
        return !apiKeyField.getText().equals(config.getApiKey()) ||
               !apiUrlField.getText().equals(config.getApiUrl()) ||
               !modelField.getText().equals(config.getModel()) ||
               !maxTokensField.getText().equals(String.valueOf(config.getMaxTokens())) ||
               !temperatureField.getText().equals(String.valueOf(config.getTemperature())) ||
               !workspaceRootField.getText().equals(config.getWorkspaceRoot()) ||
               enableCacheCheckBox.isSelected() != config.isEnableCache() ||
               autoFixCheckBox.isSelected() != config.isAutoFix() ||
               validateCheckBox.isSelected() != config.isValidate();
    }

    public void apply(ConfigService config) {
        config.setApiKey(apiKeyField.getText());
        config.setApiUrl(apiUrlField.getText());
        config.setModel(modelField.getText());
        config.setMaxTokens(Integer.parseInt(maxTokensField.getText()));
        config.setTemperature(Double.parseDouble(temperatureField.getText()));
        config.setWorkspaceRoot(workspaceRootField.getText());
        config.setEnableCache(enableCacheCheckBox.isSelected());
        config.setAutoFix(autoFixCheckBox.isSelected());
        config.setValidate(validateCheckBox.isSelected());
    }

    public void reset(ConfigService config) {
        apiKeyField.setText(config.getApiKey());
        apiUrlField.setText(config.getApiUrl());
        modelField.setText(config.getModel());
        maxTokensField.setText(String.valueOf(config.getMaxTokens()));
        temperatureField.setText(String.valueOf(config.getTemperature()));
        workspaceRootField.setText(config.getWorkspaceRoot());
        enableCacheCheckBox.setSelected(config.isEnableCache());
        autoFixCheckBox.setSelected(config.isAutoFix());
        validateCheckBox.setSelected(config.isValidate());
    }
}
