package com.devteam.idea.actions;

import com.devteam.idea.ui.DevDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class QuickDevAction extends AnAction {
    
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;

        // Show development dialog
        DevDialog dialog = new DevDialog(project, "quick");
        dialog.show();
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        // Enable only when project is open
        Project project = e.getProject();
        e.getPresentation().setEnabledAndVisible(project != null);
    }
}
