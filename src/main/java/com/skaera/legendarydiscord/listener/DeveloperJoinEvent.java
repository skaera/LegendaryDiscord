package com.skaera.legendarydiscord.listener;

import com.pixelmonmod.pixelmon.comm.ChatHandler;
import com.skaera.legendarydiscord.Legendarydiscord;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class DeveloperJoinEvent {
    @SubscribeEvent
    public void on(PlayerEvent.PlayerLoggedInEvent event) {
        UUID id = event.getEntity().getUniqueID();
        if(id.toString().equalsIgnoreCase("a8a8bbdc-c5a3-4bab-86ea-957439b337cb") || id.toString().equalsIgnoreCase("a2272eb3-446a-4a30-803b-532e20bd8890")) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
            ChatHandler.sendChat(player, "§f[ §b디스코드 전설 알리미를 사용중인 서버 입니다. §f]");
            ChatHandler.sendChat(player, "§f- §eVersion : "+ Legendarydiscord.VERSION);

        }
    }

}
