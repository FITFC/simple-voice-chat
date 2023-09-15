package de.maxhenkel.voicechat.integration;

import de.maxhenkel.voicechat.Voicechat;
import de.maxhenkel.voicechat.intercompatibility.CommonCompatibilityManager;
import de.maxhenkel.voicechat.net.FabricNetManager;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import com.viaversion.viaversion.protocols.protocol1_13to1_12_2.Protocol1_13To1_12_2;

import java.util.Set;

public class ViaVersionCompatibility {

    private static final String OLD_VOICECHAT_PREFIX = "vc";

    public static void register() {
        try {
            if (FabricLoader.getInstance().isModLoaded("viaversion")) {
                registerMappings();
                Voicechat.LOGGER.info("Successfully registered ViaVersion mappings");
            }
        } catch (Throwable t) {
            Voicechat.LOGGER.error("Failed to register ViaVersion mappings", t);
        }
    }

    private static void registerMappings() {
        Set<ResourceLocation> packets = ((FabricNetManager) CommonCompatibilityManager.INSTANCE.getNetManager()).getPackets();
        for (ResourceLocation id : packets) {
            Protocol1_13To1_12_2.MAPPINGS.getChannelMappings().put(String.format("%s:%s", OLD_VOICECHAT_PREFIX, id.getPath()), id.toString());
        }
    }

}
