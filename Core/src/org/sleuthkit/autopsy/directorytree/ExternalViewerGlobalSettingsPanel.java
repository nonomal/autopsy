/*
 * Autopsy Forensic Browser
 *
 * Copyright 2011-2018 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.directorytree;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import org.sleuthkit.autopsy.corecomponents.OptionsPanel;
import javax.swing.JOptionPane;
import org.openide.util.NbBundle;
import org.netbeans.spi.options.OptionsPanelController;
import org.sleuthkit.autopsy.casemodule.GeneralFilter;
import org.sleuthkit.autopsy.core.UserPreferences;
import org.sleuthkit.autopsy.coreutils.PlatformUtil;

/**
 * An options panel for the user to create, edit, and delete associations for
 * opening files in external viewers. Users can associate a file by either MIME
 * type or by extension to an executable file.
 */
@SuppressWarnings("PMD.SingularField") // UI widgets cause lots of false positives
final class ExternalViewerGlobalSettingsPanel extends javax.swing.JPanel implements OptionsPanel {

    private ExternalViewerGlobalSettingsTableModel tableModel;

    public ExternalViewerGlobalSettingsPanel() {
        this(new ExternalViewerGlobalSettingsTableModel(new String[] {
            "Mime type/Extension", "Application"}));
    }
    
    /**
     * Creates new form ExternalViewerGlobalSettingsPanel
     */
    public ExternalViewerGlobalSettingsPanel(ExternalViewerGlobalSettingsTableModel tableModel) {
        initComponents();
        this.tableModel = tableModel;
        customizeComponents(tableModel);
    }

    /**
     * Initializes field variables. Adds a listener to the list of rules.
     */
    private void customizeComponents(ExternalViewerGlobalSettingsTableModel tableModel) {
        ExternalViewerRulesTable.setModel(tableModel);
        ExternalViewerRulesTable.setAutoCreateRowSorter(true);
    }
    
    /**
     * Simulate the delete rule button click action.
     * 
     * @param selectedIndex Index to delete in JTable
     */
    public void deleteRuleButtonClick(int selectedIndex) {
        ExternalViewerRulesTable.getSelectionModel().setSelectionInterval(selectedIndex, selectedIndex);
        deleteRuleButton.getListeners(ActionListener.class)[0].actionPerformed(null);
    }
            
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newRuleButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        newRuleButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        ExternalViewerRulesTable = new javax.swing.JTable();
        externalViewerTitleLabel = new javax.swing.JLabel();
        deleteRuleButton = new javax.swing.JButton();
        editRuleButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        HxDPath = new javax.swing.JTextField();
        HxDLabel = new javax.swing.JLabel();
        ContentViewerExtensionLabel = new javax.swing.JLabel();
        browseHxDDirectory = new javax.swing.JButton();

        newRuleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/images/add16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(newRuleButton1, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.newRuleButton1.text")); // NOI18N
        newRuleButton1.setMaximumSize(new java.awt.Dimension(111, 25));
        newRuleButton1.setMinimumSize(new java.awt.Dimension(111, 25));

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.jButton2.text")); // NOI18N

        setPreferredSize(new java.awt.Dimension(701, 453));

        jPanel1.setPreferredSize(new java.awt.Dimension(701, 453));

        newRuleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/images/add16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(newRuleButton, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.newRuleButton.text")); // NOI18N
        newRuleButton.setMaximumSize(new java.awt.Dimension(111, 25));
        newRuleButton.setMinimumSize(new java.awt.Dimension(111, 25));
        newRuleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newRuleButtonActionPerformed(evt);
            }
        });

        jScrollPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jScrollPane4.setViewportView(ExternalViewerRulesTable);
        if (ExternalViewerRulesTable.getColumnModel().getColumnCount() > 0) {
            ExternalViewerRulesTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.ExternalViewerRulesTable.columnModel.title0")); // NOI18N
            ExternalViewerRulesTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.ExternalViewerRulesTable.columnModel.title1")); // NOI18N
        }

        org.openide.awt.Mnemonics.setLocalizedText(externalViewerTitleLabel, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.externalViewerTitleLabel.text")); // NOI18N

        deleteRuleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/images/delete16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(deleteRuleButton, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.deleteRuleButton.text")); // NOI18N
        deleteRuleButton.setMaximumSize(new java.awt.Dimension(111, 25));
        deleteRuleButton.setMinimumSize(new java.awt.Dimension(111, 25));
        deleteRuleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRuleButtonActionPerformed(evt);
            }
        });

        editRuleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/sleuthkit/autopsy/images/edit16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(editRuleButton, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.editRuleButton.text")); // NOI18N
        editRuleButton.setMaximumSize(new java.awt.Dimension(111, 25));
        editRuleButton.setMinimumSize(new java.awt.Dimension(111, 25));
        editRuleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRuleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(externalViewerTitleLabel)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(newRuleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editRuleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteRuleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(externalViewerTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newRuleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editRuleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteRuleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        HxDPath.setEditable(false);

        org.openide.awt.Mnemonics.setLocalizedText(HxDLabel, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.HxDLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(ContentViewerExtensionLabel, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.ContentViewerExtensionLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(browseHxDDirectory, org.openide.util.NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.browseHxDDirectory.text")); // NOI18N
        browseHxDDirectory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseHxDDirectoryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(ContentViewerExtensionLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(HxDLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HxDPath, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(browseHxDDirectory, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(ContentViewerExtensionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HxDPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HxDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseHxDDirectory))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        browseHxDDirectory.setEnabled(PlatformUtil.isWindowsOS());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(547, 547, 547))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1191, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newRuleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newRuleButtonActionPerformed
        AddExternalViewerRuleDialog dialog = new AddExternalViewerRuleDialog();
        AddExternalViewerRuleDialog.BUTTON_PRESSED result = dialog.getResult();
        if (result == AddExternalViewerRuleDialog.BUTTON_PRESSED.OK) {
            ExternalViewerRule newRule = dialog.getRule();
            // Only allow one association for each MIME type or extension.
            if (tableModel.containsRule(newRule)) {
                JOptionPane.showMessageDialog(this,
                        NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.JOptionPane.ruleAlreadyExists.message"),
                        NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.JOptionPane.ruleAlreadyExists.title"),
                        JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.addRule(newRule);
                enableButtons();
                firePropertyChange(OptionsPanelController.PROP_CHANGED, null, null);
            }
        }
    }//GEN-LAST:event_newRuleButtonActionPerformed

    private void editRuleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRuleButtonActionPerformed
        int selectedIndex = ExternalViewerRulesTable.convertRowIndexToModel(ExternalViewerRulesTable.getSelectedRow());
        ExternalViewerRule selectedRule = tableModel.getRuleAt(selectedIndex);
        AddExternalViewerRuleDialog dialog = new AddExternalViewerRuleDialog(selectedRule);
        AddExternalViewerRuleDialog.BUTTON_PRESSED result = dialog.getResult();
        if (result == AddExternalViewerRuleDialog.BUTTON_PRESSED.OK) {
            ExternalViewerRule newRule = dialog.getRule();
            // Only allow one association for each MIME type or extension.
            if (tableModel.containsRule(newRule)) {
                JOptionPane.showMessageDialog(this,
                        NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.JOptionPane.ruleAlreadyExists.message"),
                        NbBundle.getMessage(ExternalViewerGlobalSettingsPanel.class, "ExternalViewerGlobalSettingsPanel.JOptionPane.ruleAlreadyExists.title"),
                        JOptionPane.ERROR_MESSAGE);
            } else {
                tableModel.setRule(selectedIndex, dialog.getRule());
                firePropertyChange(OptionsPanelController.PROP_CHANGED, null, null);
            }
        }
    }//GEN-LAST:event_editRuleButtonActionPerformed

    private void deleteRuleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRuleButtonActionPerformed
        tableModel.removeRule(ExternalViewerRulesTable.convertRowIndexToModel(ExternalViewerRulesTable.getSelectedRow()));
        ExternalViewerRulesTable.getSelectionModel().clearSelection();
        enableButtons();
        firePropertyChange(OptionsPanelController.PROP_CHANGED, null, null);
    }//GEN-LAST:event_deleteRuleButtonActionPerformed

    private void browseHxDDirectoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseHxDDirectoryActionPerformed
        JFileChooser fileWindow = new JFileChooser();
        fileWindow.setFileSelectionMode(JFileChooser.FILES_ONLY);
        GeneralFilter exeFilter = new GeneralFilter(GeneralFilter.EXECUTABLE_EXTS, GeneralFilter.EXECUTABLE_DESC);
        File HxDPathFile = new File(HxDPath.getText());
        if(HxDPathFile.exists() && HxDPathFile.canExecute()) {
            fileWindow.setCurrentDirectory(new File(HxDPath.getText()));
        }
        fileWindow.setDragEnabled(false);
        fileWindow.setFileFilter(exeFilter);
        fileWindow.setMultiSelectionEnabled(false);
        int returnVal = fileWindow.showSaveDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File HxDExecutable = fileWindow.getSelectedFile();
            HxDPath.setForeground(Color.BLACK);
            HxDPath.setText(HxDExecutable.getAbsolutePath());
            firePropertyChange(OptionsPanelController.PROP_CHANGED, null, null);
        }
    }//GEN-LAST:event_browseHxDDirectoryActionPerformed

    @Override
    public void store() {
        //Dump rules from table model into a list to be stored by the rules manager.
        List<ExternalViewerRule> rules = new ArrayList<>();
        for(int i = 0; i < tableModel.getRowCount(); i++) {
            rules.add(tableModel.getRuleAt(i));
        }
        ExternalViewerRulesManager.getInstance().setUserRules(rules);
        UserPreferences.setHdXEditorPath(HxDPath.getText());
    }

    @Override
    public void load() {
        List<ExternalViewerRule> rules = ExternalViewerRulesManager.getInstance().getUserRules();
        for(ExternalViewerRule rule : rules) {
            if(!tableModel.containsRule(rule)) {
                tableModel.addRule(rule);
            }
        }
        String editorPath = UserPreferences.getHdXEditorPath();
        File HdXExecutable = new File(editorPath);
        if(HdXExecutable.exists() || HdXExecutable.canExecute()) {
            HxDPath.setText(editorPath);    
        } else {
            HxDPath.setForeground(Color.RED);
            HxDPath.setText(editorPath);
        }
        enableButtons();
    }

    /**
     * Enable edit and delete buttons if there is a rule selected.
     */
    boolean enableButtons() {
        boolean ruleIsSelected = ExternalViewerRulesTable.getRowCount() > 0;
        editRuleButton.setEnabled(ruleIsSelected);
        deleteRuleButton.setEnabled(ruleIsSelected);
        return ruleIsSelected;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ContentViewerExtensionLabel;
    private javax.swing.JTable ExternalViewerRulesTable;
    private javax.swing.JLabel HxDLabel;
    private javax.swing.JTextField HxDPath;
    private javax.swing.JButton browseHxDDirectory;
    private javax.swing.JButton deleteRuleButton;
    private javax.swing.JButton editRuleButton;
    private javax.swing.JLabel externalViewerTitleLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton newRuleButton;
    private javax.swing.JButton newRuleButton1;
    // End of variables declaration//GEN-END:variables
}
