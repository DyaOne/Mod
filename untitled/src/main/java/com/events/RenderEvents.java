package com.events;

import com.libs.CharacterInventoryRenderer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderEvents {

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent.Post event) {
        if (InputEvents.showCharacterInventory) {
            CharacterInventoryRenderer.renderIfOpen(InputEvents.showCharacterInventory);
        }
    }
}
