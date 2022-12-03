package com.skaera.legendarydiscord;

import com.pixelmonmod.pixelmon.Pixelmon;
import com.skaera.legendarydiscord.cfg.Config;
import com.skaera.legendarydiscord.listener.DeveloperJoinEvent;
import com.skaera.legendarydiscord.listener.LegendarySpawnListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("legendarydiscord")
public class Legendarydiscord {

    public static String VERSION = "1.0.2";

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Legendarydiscord() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(Legendarydiscord::init);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event)  {
        if(new File("config/LegendaryDiscord/").mkdirs())
        Config.FileSaver.save();
        Pixelmon.EVENT_BUS.register(new LegendarySpawnListener());
        MinecraftForge.EVENT_BUS.register(new DeveloperJoinEvent());
        LOGGER.info("§e 디스코드 전설 알리미 시스템이 로드 되었습니다 ! ( 적용된 버전 : " +VERSION+" )");
        LOGGER.info("§b - 제작자 : Asa#7777 / 민석#1234");
        LOGGER.info("§d - 디스코드 주소 : https://discord.gg/qBzAH7gA93");
    }

}
