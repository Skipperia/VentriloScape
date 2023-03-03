package com.VentriloScape;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("VentriloScape")
public interface VentriloScapeConfig extends Config
{
	@ConfigItem(
		keyName = "textValueOfSmtn",
		name = "yalla nu:",
		description = "desc"
	)
	default String greeting()
	{
		return "the starting text";
	}
}
