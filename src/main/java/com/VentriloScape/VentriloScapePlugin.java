package com.VentriloScape;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Player;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.groundmarkers.GroundMarkerConfig;
import net.runelite.client.plugins.groundmarkers.GroundMarkerPlugin;
import net.runelite.client.ui.overlay.*;

import java.awt.*;
import java.awt.image.BufferedImage;

@Slf4j
@PluginDescriptor(
        name = "VentriloScape"
)
public class VentriloScapePlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private GroundMarkerOverlay gmo;

    @Inject
    private OverlayManager overlayManager;


    @Inject
    SpriteManager spriteManager;

    @Inject
    private VentriloScapeConfig config;

    @Override
    protected void startUp() throws Exception {
        log.info("VentriloScape started!");
        overlayManager.add(gmo);
    }

    @Override
    protected void shutDown() throws Exception {
        log.info("VentriloScape stopped!");
        overlayManager.remove(gmo);
    }

    @Subscribe
    public void onGameTick(GameTick event) {
        Player localPlayer = client.getLocalPlayer();
        LocalPoint playerLocation = localPlayer.getLocalLocation();
        log.info("Your current location is:" + playerLocation);
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged gameStateChanged) {
        log.info("Current game state:" + gameStateChanged);
        if (gameStateChanged.getGameState() == GameState.LOGGED_IN) {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
            client.getLocalPlayer().getLocalLocation();
        }
    }


    @Provides
    VentriloScapeConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(VentriloScapeConfig.class);
    }
}
