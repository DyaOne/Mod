package com.render.renderGUI;


import com.attribute.AttributeUtils;
import com.attribute.player.PlayerAttributes;
import com.stats.player.PlayerStats;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TextFormatting;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class ClientHUDRenderer {

    @SubscribeEvent
    public static void onRenderStatsHUD(RenderGameOverlayEvent.Text event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) return;

        PlayerAttributes attrs = AttributeUtils.getAttributes(player);
        if (attrs == null) return;

        int x = event.getWindow().getGuiScaledWidth() - 60;
        int y = 10;

        Minecraft.getInstance().font.draw(event.getMatrixStack(), TextFormatting.RED + "STR: " + attrs.getStrength().getTotal(), x, y, 0xFFFFFF);
        Minecraft.getInstance().font.draw(event.getMatrixStack(), TextFormatting.BLUE + "INT: " + attrs.getIntellect().getTotal(), x, y + 10, 0xFFFFFF);
        Minecraft.getInstance().font.draw(event.getMatrixStack(), TextFormatting.GREEN + "AGI: " + attrs.getAgility().getTotal(), x, y + 20, 0xFFFFFF);

        PlayerStats stats = AttributeUtils.getStats(player);
        if (stats == null) return;

        int screenWidth = event.getWindow().getGuiScaledWidth();
        int screenHeight = event.getWindow().getGuiScaledHeight();

// === Текстовые строки ===
        String hpText = TextFormatting.RED + "HP: " + stats.getHealth().getCurrent() + " / " + stats.getHealth().getMax();
        String mpText = TextFormatting.BLUE + "MP: " + stats.getMana().getCurrent() + " / " + stats.getMana().getMax();
        String spText = TextFormatting.GREEN + "SP: " + stats.getStamina().getCurrent() + " / " + stats.getStamina().getMax();

        Minecraft mc = Minecraft.getInstance();

// === HP: Левый нижний угол ===
        mc.font.draw(event.getMatrixStack(), hpText, 10, screenHeight - 30, 0xFFFFFF);

// === MP: Центр снизу ===
        int mpWidth = mc.font.width(mpText);
        mc.font.draw(event.getMatrixStack(), mpText, (screenWidth / 2f) - (mpWidth / 2f), screenHeight - 30, 0xFFFFFF);

// === SP: Правый нижний угол ===
        int spWidth = mc.font.width(spText);
        mc.font.draw(event.getMatrixStack(), spText, screenWidth - spWidth - 10, screenHeight - 30, 0xFFFFFF);

    }
}