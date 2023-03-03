package com.VentriloScape;

import jdk.vm.ci.meta.Local;
import net.runelite.api.Client;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.Point;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.groundmarkers.GroundMarkerConfig;
import net.runelite.client.plugins.groundmarkers.GroundMarkerPlugin;
import net.runelite.client.ui.overlay.*;

import java.awt.*;
import java.util.Collection;
import java.util.HashSet;

import net.runelite.api.Client;

import javax.inject.Inject;

public class GroundMarkerOverlay extends Overlay {

    private static final int MAX_DRAW_DISTANCE = 32;

    private final Client client;
    private final VentriloScapeConfig config;
    private final VentriloScapePlugin plugin;

    @Inject
    public GroundMarkerOverlay(Client client, VentriloScapeConfig config, VentriloScapePlugin plugin) {
        this.client = client;
        this.config = config;
        this.plugin = plugin;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.LOW);
        setLayer(OverlayLayer.ABOVE_SCENE);
    }


    @Override
    public Dimension render(Graphics2D graphics) {
        final Collection<LocalPoint> pointsToMark = new HashSet<LocalPoint>();
        pointsToMark.add(client.getLocalPlayer().getLocalLocation());
        if (pointsToMark.isEmpty()) {
            return null;
        }

        for (final LocalPoint point : pointsToMark) {
            Color somecolor = new Color(100, 100, 100);
            Point test = new Point(point.getX(), point.getY());
            OverlayUtil.renderTextLocation(graphics, test, "Staff", somecolor);
        }


        return null;
    }
}
