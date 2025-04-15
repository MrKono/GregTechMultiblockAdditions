package kono.gtma.api.utils;

import net.minecraft.resources.ResourceLocation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GTMAValues {

    public static final String MOD_ID = "gtma";
    public static final String MOD_NAME = "GTMultiblockAdditions";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static ResourceLocation modId(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
}
