package com.nimrod.mixin;

import com.nimrod.FiveFiveFiveUtils;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    private void onChatMessage(String message, CallbackInfo ci) {
        if (message.startsWith(FiveFiveFiveUtils.INSTANCE.commandManager.getPrefix())) {
            FiveFiveFiveUtils.INSTANCE.commandManager.runCommand(message.substring(FiveFiveFiveUtils.INSTANCE.commandManager.getPrefix().length()));
            ci.cancel();
        }
    }

}
