package com.mixins;

import com.libs.api.mouse.MouseAPI;
import net.minecraft.client.MouseHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MouseHelper.class)
public class MixinMouseHelper {

    /**
     * Overwrite vanilla grabMouse to block it while our GUI is open.
     */
    @Overwrite(remap = false)
    public void grabMouse() {
        if (!MouseAPI.isMouseLocked()) {
            System.out.println("[Mixin] grabMouse executed (allowed)");
            // Можешь сюда вернуть оригинальное поведение,
            // если хочешь (пока оставим пустым)
        } else {
            System.out.println("[Mixin] grabMouse blocked!");
        }
    }
}