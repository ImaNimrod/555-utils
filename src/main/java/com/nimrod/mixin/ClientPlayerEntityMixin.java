package com.nimrod.mixin;

import com.nimrod.FiveFiveFiveUtils;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Inject(method = "tick()V", at = @At("RETURN"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        FiveFiveFiveUtils.INSTANCE.onTick();
    }   
}
