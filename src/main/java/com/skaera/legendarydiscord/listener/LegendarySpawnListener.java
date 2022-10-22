package com.skaera.legendarydiscord.listener;

import com.pixelmonmod.pixelmon.api.events.spawning.SpawnEvent;
import com.pixelmonmod.pixelmon.api.util.helpers.BiomeHelper;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import com.skaera.legendarydiscord.cfg.Config;
import com.skaera.legendarydiscord.discord.DiscordWebHook;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.server.STitlePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.io.IOException;

public class LegendarySpawnListener {
    public String changeStr(String val) {
        char[] arr = val.toCharArray();
        arr[0] = Character.toLowerCase(arr[0]);
        return new String(arr);
    }

    @SubscribeEvent
    public void Event(SpawnEvent event) throws IOException {
        discord(event);
        title(event);

    }

    private void discord(SpawnEvent event)throws IOException {
        PixelmonEntity entity = event.action.getOrCreateEntity() instanceof PixelmonEntity ? (PixelmonEntity) event.action.getOrCreateEntity() : null;
        if(entity == null)
            return;
        if(entity.getPokemon().isLegendary() && !entity.getPokemon().isBossPokemon()) {
            Config config = new Config();
            DiscordWebHook webhook = new DiscordWebHook(config.getString("webhook"));

            String biome = BiomeHelper.getLocalizedBiomeName(event.action.spawnLocation.biome).getString();

            webhook.setContent(config.getString("message").replace("%biome%", biome).replace("%pokemon%", entity.getPokemon().getLocalizedName()));
            webhook.setUsername(config.getString("name").replace("%pokemon%", entity.getPokemon().getLocalizedName()));
            webhook.setAvatarUrl("https://img.pokemondb.net/sprites/home/normal/"+changeStr(entity.getPokemon().getSpecies().getName())+".png");
            webhook.execute();
        }


    }

    private void title(SpawnEvent event) {

        PixelmonEntity entity = event.action.getOrCreateEntity() instanceof PixelmonEntity ? (PixelmonEntity) event.action.getOrCreateEntity() : null;
        if(entity == null)
            return;
        if(entity.getPokemon().isLegendary() && !entity.getPokemon().isBossPokemon()) {
            Config config = new Config();
            if(config.getBool("use_title")) {

                String biome = BiomeHelper.getLocalizedBiomeName(event.action.spawnLocation.biome).getString();
                STitlePacket title = new STitlePacket(STitlePacket.Type.TITLE, new StringTextComponent(config.getString("title").replace("%pokemon%", entity.getPokemon().getLocalizedName()).replace("%biome%", biome)), 20, 100, 20);
                STitlePacket sub = new STitlePacket(STitlePacket.Type.SUBTITLE, new StringTextComponent(config.getString("subtitle").replace("%pokemon%", entity.getPokemon().getLocalizedName()).replace("%biome%", biome)), 20, 100, 20);
                MinecraftServer server = event.action.getOrCreateEntity().getServer();
                for(ServerPlayerEntity player : server.getPlayerList().getPlayers()) {
                    player.connection.sendPacket(title);
                    player.connection.sendPacket(sub);
                }
            }

        }
    }
}
