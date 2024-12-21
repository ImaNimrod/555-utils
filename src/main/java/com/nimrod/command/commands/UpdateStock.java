package com.nimrod.command.commands;

import com.nimrod.command.Command;
import com.nimrod.util.APIUtils;
import com.nimrod.util.LogUtils;

public class UpdateStock extends Command {
    public UpdateStock() {
        super("update_stock", "Set whether or not a kit is in stock", "<id> <true/false>");
    }

    @Override
    public boolean execute(String[] args) {
        if (args.length == 2) {
            try {
                boolean res = APIUtils.updateKitStock(args[0], Boolean.parseBoolean(args[1]));
                if (res) {
                    LogUtils.chatLog("Updated kit stock");
                } else {
                    LogUtils.chatLog("No kit found with that kit ID");
                }
            } catch (Exception e) {
                e.printStackTrace();
                LogUtils.chatLog("Failed to update kit");
            }

            return true;
        } else {
            return false;
        }
    }
}
