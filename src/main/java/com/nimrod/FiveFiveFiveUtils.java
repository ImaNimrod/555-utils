package com.nimrod;

import com.nimrod.command.CommandManager;
import com.nimrod.util.LogUtils;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import org.lwjgl.glfw.GLFW;

public class FiveFiveFiveUtils implements ModInitializer {
    public static final String NAME = "555-utils";
    public static final String VERSION = "1.0.0";
    public static final String AUTHOR = "nimrod";

    public static final FiveFiveFiveUtils INSTANCE = new FiveFiveFiveUtils();

    public final CommandManager commandManager = new CommandManager();

    private final MinecraftClient mc = MinecraftClient.getInstance();

    @Override
    public void onInitialize() {
        LogUtils.consoleLog("initializing " + NAME + " v" + VERSION + " created by " + AUTHOR);
    }

    public void onKey(int key, int action) {
        if (mc.currentScreen == null && mc.getOverlay() == null) {
            if (commandManager.getPrefix().equals(GLFW.glfwGetKeyName(key, 0)) && action == GLFW.GLFW_RELEASE) {
                mc.setScreen(new ChatScreen(commandManager.getPrefix()));
            }
        }
    }

    public void onTick() {
    }
}
