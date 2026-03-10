package com.devteam.idea.ui;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;

public class DevTeamToolWindow {
    private final Project project;
    private JPanel mainPanel;
    private JTextArea logArea;
    private ProgressPanel progressPanel;

    public DevTeamToolWindow(Project project) {
        this.project = project;
        initUI();
    }

    private void initUI() {
        mainPanel = new JPanel(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("DevTeam - AI Development Team");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Progress panel
        progressPanel = new ProgressPanel();

        // Log area
        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        logArea.setText("DevTeam Tool Window\n\nReady to start development!\n\nRight-click on project and select DevTeam → Quick Development\n");
        
        JBScrollPane scrollPane = new JBScrollPane(logArea);

        // Toolbar
        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> clearLog());
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> stopExecution());
        toolbar.add(clearButton);
        toolbar.add(stopButton);

        // Top panel (title + progress)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(progressPanel, BorderLayout.CENTER);

        // Add components
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(toolbar, BorderLayout.SOUTH);
    }

    public JComponent getContent() {
        return mainPanel;
    }

    public void appendLog(String text) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(text + "\n");
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }

    public void clearLog() {
        SwingUtilities.invokeLater(() -> {
            logArea.setText("");
            progressPanel.reset();
        });
    }

    public void setProgress(int value) {
        progressPanel.setProgress(value);
    }

    public void setStatus(String status) {
        progressPanel.setStatus(status);
    }

    public void setAgent(String agent) {
        progressPanel.setAgent(agent);
    }

    private void stopExecution() {
        // TODO: Implement stop functionality
        appendLog("\n⏸️ Stopping execution...");
    }
}
