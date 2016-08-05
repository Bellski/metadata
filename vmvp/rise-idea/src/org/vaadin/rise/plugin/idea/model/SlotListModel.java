package org.vaadin.rise.plugin.idea.model;

import com.intellij.ui.CollectionComboBoxModel;

import java.util.List;

/**
 * Created by oem on 8/4/16.
 */
public class SlotListModel extends CollectionComboBoxModel<SlotItem> {
    public SlotListModel(List<SlotItem> slotList) {
        super(slotList);
        slotList.add(0, null);
    }
}
