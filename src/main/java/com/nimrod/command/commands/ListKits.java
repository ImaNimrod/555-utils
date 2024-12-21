package com.nimrod.command.commands;

import com.nimrod.command.Command;
import com.nimrod.util.APIUtils;
import com.nimrod.util.LogUtils;

public class ListKits extends Command {
    public ListKits() {
        super("list_kits", "List kits from the kit stock", "");
    }

    @Override
    public boolean execute(String[] args) {
        if (args.length == 0) {
            try {
                LogUtils.chatLog("Kits:");
                for (APIUtils.Kit kit : APIUtils.getKits()) {
                    LogUtils.chatLog("ID: " + kit._id() + " | Name: " + kit.name() + " | Description: " + kit.description() + " | KitID: " + kit.kitId() + " | In Stock: " + kit.inStock());
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.chatLog("Failed to fetch kits");
            }
            return true;
        } else {
            return false;
        }
    }
}
