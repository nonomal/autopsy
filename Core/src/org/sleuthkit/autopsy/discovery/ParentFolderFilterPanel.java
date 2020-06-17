/*
 * Autopsy
 *
 * Copyright 2020 Basis Technology Corp.
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
package org.sleuthkit.autopsy.discovery;

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import org.sleuthkit.autopsy.discovery.FileSearchFiltering.ParentSearchTerm;

/**
 * Panel to allow configuration of the Parent Folder filter.
 */
final class ParentFolderFilterPanel extends AbstractDiscoveryFilterPanel {

    private static final long serialVersionUID = 1L;
    private DefaultListModel<FileSearchFiltering.ParentSearchTerm> parentListModel;
    private static final String[] DEFAULT_IGNORED_PATHS = {"/Windows/", "/Program Files/"}; //NON-NLS

    /**
     * Creates new form ParentFolderFilterPanel.
     */
    ParentFolderFilterPanel() {
        initComponents();
        setUpParentPathFilter();
    }

    /**
     * Initialize the parent path filter.
     */
    private void setUpParentPathFilter() {
        fullRadioButton.setSelected(true);
        includeRadioButton.setSelected(true);
        parentListModel = (DefaultListModel<FileSearchFiltering.ParentSearchTerm>) parentList.getModel();
        for (String ignorePath : DEFAULT_IGNORED_PATHS) {
            parentListModel.add(parentListModel.size(), new ParentSearchTerm(ignorePath, false, false));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        parentCheckbox = new javax.swing.JCheckBox();
        parentLabel = new javax.swing.JLabel();
        parentScrollPane = new javax.swing.JScrollPane();
        parentList = new javax.swing.JList<>();
        fullRadioButton = new javax.swing.JRadioButton();
        includeRadioButton = new javax.swing.JRadioButton();
        substringRadioButton = new javax.swing.JRadioButton();
        excludeRadioButton = new javax.swing.JRadioButton();
        deleteButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        parentTextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(parentCheckbox, org.openide.util.NbBundle.getMessage(ParentFolderFilterPanel.class, "ParentFolderFilterPanel.parentCheckbox.text_1")); // NOI18N
        parentCheckbox.setMaximumSize(new java.awt.Dimension(150, 25));
        parentCheckbox.setMinimumSize(new java.awt.Dimension(150, 25));
        parentCheckbox.setPreferredSize(new java.awt.Dimension(150, 25));
        parentCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parentCheckboxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(parentLabel, org.openide.util.NbBundle.getMessage(ParentFolderFilterPanel.class, "ParentFolderFilterPanel.parentLabel.text_1")); // NOI18N
        parentLabel.setMaximumSize(new java.awt.Dimension(150, 25));
        parentLabel.setMinimumSize(new java.awt.Dimension(150, 25));
        parentLabel.setPreferredSize(new java.awt.Dimension(150, 25));

        setMinimumSize(new java.awt.Dimension(250, 120));
        setPreferredSize(new java.awt.Dimension(250, 120));

        parentScrollPane.setPreferredSize(new java.awt.Dimension(27, 27));

        parentList.setModel(new DefaultListModel<ParentSearchTerm>());
        parentList.setEnabled(false);
        parentList.setVisibleRowCount(4);
        parentList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                parentListValueChanged(evt);
            }
        });
        parentScrollPane.setViewportView(parentList);

        fullRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(fullRadioButton, org.openide.util.NbBundle.getMessage(ParentFolderFilterPanel.class, "ParentFolderFilterPanel.fullRadioButton.text_1")); // NOI18N
        fullRadioButton.setEnabled(false);

        includeRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(includeRadioButton, org.openide.util.NbBundle.getMessage(ParentFolderFilterPanel.class, "ParentFolderFilterPanel.includeRadioButton.text_1")); // NOI18N
        includeRadioButton.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(substringRadioButton, org.openide.util.NbBundle.getMessage(ParentFolderFilterPanel.class, "ParentFolderFilterPanel.substringRadioButton.text_1")); // NOI18N
        substringRadioButton.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(excludeRadioButton, org.openide.util.NbBundle.getMessage(ParentFolderFilterPanel.class, "ParentFolderFilterPanel.excludeRadioButton.text_1")); // NOI18N
        excludeRadioButton.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(deleteButton, org.openide.util.NbBundle.getMessage(ParentFolderFilterPanel.class, "ParentFolderFilterPanel.deleteButton.text_1")); // NOI18N
        deleteButton.setEnabled(false);
        deleteButton.setMaximumSize(new java.awt.Dimension(70, 23));
        deleteButton.setMinimumSize(new java.awt.Dimension(70, 23));
        deleteButton.setPreferredSize(new java.awt.Dimension(70, 23));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(addButton, org.openide.util.NbBundle.getMessage(ParentFolderFilterPanel.class, "ParentFolderFilterPanel.addButton.text_1")); // NOI18N
        addButton.setEnabled(false);
        addButton.setMaximumSize(new java.awt.Dimension(70, 23));
        addButton.setMinimumSize(new java.awt.Dimension(70, 23));
        addButton.setPreferredSize(new java.awt.Dimension(70, 23));
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        parentTextField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(includeRadioButton)
                    .addComponent(fullRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(substringRadioButton)
                    .addComponent(excludeRadioButton)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(parentScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                    .addComponent(parentTextField)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(parentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fullRadioButton)
                    .addComponent(substringRadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(includeRadioButton)
                    .addComponent(excludeRadioButton)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(parentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void parentCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parentCheckboxActionPerformed
//        parentFilterSettings(true, true, parentCheckbox.isSelected(), null);
    }//GEN-LAST:event_parentCheckboxActionPerformed

    private void parentListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_parentListValueChanged
        if (parentList.getSelectedValuesList().isEmpty()) {
            deleteButton.setEnabled(false);
        } else {
            deleteButton.setEnabled(true);
        }
    }//GEN-LAST:event_parentListValueChanged

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int index = parentList.getSelectedIndex();
        if (index >= 0) {
            parentListModel.remove(index);
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if (!parentTextField.getText().isEmpty()) {
            ParentSearchTerm searchTerm;
            searchTerm = new ParentSearchTerm(parentTextField.getText(), fullRadioButton.isSelected(), includeRadioButton.isSelected());
            parentListModel.add(parentListModel.size(), searchTerm);
            parentTextField.setText("");
        }
    }//GEN-LAST:event_addButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JRadioButton excludeRadioButton;
    private javax.swing.JRadioButton fullRadioButton;
    private javax.swing.JRadioButton includeRadioButton;
    private javax.swing.JCheckBox parentCheckbox;
    private javax.swing.JLabel parentLabel;
    private javax.swing.JList<ParentSearchTerm> parentList;
    private javax.swing.JScrollPane parentScrollPane;
    private javax.swing.JTextField parentTextField;
    private javax.swing.JRadioButton substringRadioButton;
    // End of variables declaration//GEN-END:variables

    @Override
    void configurePanel(boolean selected, int[] indicesSelected) {
        parentCheckbox.setSelected(selected);
        if (parentCheckbox.isEnabled() && parentCheckbox.isSelected()) {
            parentScrollPane.setEnabled(true);
            includeRadioButton.setEnabled(true);
            excludeRadioButton.setEnabled(true);
            fullRadioButton.setEnabled(true);
            substringRadioButton.setEnabled(true);
            addButton.setEnabled(true);
            deleteButton.setEnabled(!parentListModel.isEmpty());
            parentList.setEnabled(true);
            parentTextField.setEnabled(true);
            if (indicesSelected != null) {
                parentList.setSelectedIndices(indicesSelected);
            }
        } else {
            parentScrollPane.setEnabled(false);
            parentList.setEnabled(false);
            includeRadioButton.setEnabled(false);
            excludeRadioButton.setEnabled(false);
            fullRadioButton.setEnabled(false);
            substringRadioButton.setEnabled(false);
            addButton.setEnabled(false);
            deleteButton.setEnabled(false);
            parentTextField.setEnabled(false);
        }
    }

    @Override
    JCheckBox getCheckbox() {
        return parentCheckbox;
    }

    @Override
    JLabel getAdditionalLabel() {
        return parentLabel;
    }

    @Override
    String checkForError() {
        // Parent uses everything in the box
        if (parentCheckbox.isSelected() && getParentPaths().isEmpty()) {
            return "At least one parent path must be entered";
        }
        return "";
    }

    /**
     * Utility method to get the parent path objects out of the JList.
     *
     * @return The list of entered ParentSearchTerm objects
     */
    private List<FileSearchFiltering.ParentSearchTerm> getParentPaths() {
        List<FileSearchFiltering.ParentSearchTerm> results = new ArrayList<>();
        for (int i = 0; i < parentListModel.getSize(); i++) {
            results.add(parentListModel.get(i));
        }
        return results;
    }

    @Override
    JList<?> getList() {
        return parentList;
    }

    @Override
    FileSearchFiltering.FileFilter getFilter() {
        if (parentCheckbox.isSelected()) {
            return new FileSearchFiltering.ParentFilter(getParentPaths());
        }
        return null;
    }
}
