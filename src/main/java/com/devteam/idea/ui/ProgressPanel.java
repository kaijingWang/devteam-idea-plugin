package com.devteam.idea.ui;

import javax.swing.*;
import java.awt.*;

public class ProgressPanel extends JPanel {
    private JProgressBar progressBar;
    private JLabel statusLabel;
    private JLabel agentLabel;

    public ProgressPanel() {
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Status label
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 12));

        // Agent label
        agentLabel = new JLabel("Waiting...");
        agentLabel.setFont(new Font("Arial", Font.PLAIN, 11));

        // Progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setValue(0);

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(statusLabel, BorderLayout.WEST);
        topPanel.add(agentLabel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
        add(progressBar, BorderLayout.CENTER);
    }

    public void setStatus(String status) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(status));
    }

    public void setAgent(String agent) {
        SwingUtilities.invokeLater(() -> agentLabel.setText(agent));
    }

    public void setProgress(int value) {
        SwingUtilities.invokeLater(() -> {
            progressBar.setValue(value);
            progressBar.setString(value + "%");
        });
    }

    public void setIndeterminate(boolean indeterminate) {
        SwingUtilities.invokeLater(() -> progressBar.setIndeterminate(indeterminate));
    }

    public void reset() {
        SwingUtilities.invokeLater(() -> {
            statusLabel.setText("Ready");
            agentLabel.setText("Waiting...");
            progressBar.setValue(0);
            progressBar.setIndeterminate(false);
        });
    }
}
