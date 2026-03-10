package com.devteam.idea.ui;

import com.devteam.idea.services.DevTeamService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class DevDialog extends DialogWrapper {
    private final Project project;
    private final String mode;
    private JTextArea requirementArea;
    private JCheckBox autoFixCheckBox;
    private JCheckBox validateCheckBox;

    public DevDialog(Project project, String mode) {
        super(project);
        this.project = project;
        this.mode = mode;
        setTitle("DevTeam - " + getModeTitle());
        init();
    }

    private String getModeTitle() {
        switch (mode) {
            case "quick": return "Quick Development";
            case "parallel": return "Parallel Development";
            case "iterate": return "Iterate Optimization";
            case "refine": return "UI Refinement";
            default: return "Development";
        }
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setPreferredSize(new Dimension(600, 400));

        // Requirement input
        JLabel label = new JLabel("Requirement:");
        requirementArea = new JTextArea(10, 50);
        requirementArea.setLineWrap(true);
        requirementArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(requirementArea);

        // Options
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        autoFixCheckBox = new JCheckBox("Auto fix common issues", true);
        validateCheckBox = new JCheckBox("Validate code quality", true);
        optionsPanel.add(autoFixCheckBox);
        optionsPanel.add(validateCheckBox);

        // Add components
        panel.add(label, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(optionsPanel, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    protected void doOKAction() {
        String requirement = requirementArea.getText().trim();
        if (requirement.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter a requirement!");
            return;
        }

        super.doOKAction();

        // Execute DevTeam
        DevTeamService service = project.getService(DevTeamService.class);
        
        // Show tool window
        ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("DevTeam");
        if (toolWindow != null) {
            toolWindow.show();
        }

        // Execute in background
        new Thread(() -> {
            service.execute(mode, requirement, output -> {
                // Update tool window with output
                SwingUtilities.invokeLater(() -> {
                    if (toolWindow != null) {
                        DevTeamToolWindow content = (DevTeamToolWindow) toolWindow.getContentManager().getContent(0).getComponent();
                        content.appendLog(output);
                    }
                });
            });
        }).start();
    }
}
