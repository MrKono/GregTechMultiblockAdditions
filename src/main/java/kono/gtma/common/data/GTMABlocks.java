package kono.gtma.common.data;

import net.minecraft.world.level.block.Block;

import static kono.gtma.api.utils.GTMAUtils.createCasingBlock;
import static kono.gtma.api.utils.GTMAValues.modId;
import static kono.gtma.common.data.GTMARegistration.REGISTRATE;

import com.tterrag.registrate.util.entry.BlockEntry;

public class GTMABlocks {

    public static void init() {}

    static {
        REGISTRATE.creativeModeTab(() -> GTMACreativeTab.BLOCKS);
    }

    // MetalCasings
    public static final BlockEntry<Block> FACTORY_CASING = createCasingBlock(
            "iridium_plated_factory_casing",
            modId("block/casings/metal/machine_casing_iridium"));
    public static final BlockEntry<Block> ADVANCED_FACTORY_CASING = createCasingBlock(
            "americium_plated_factory_casing",
            modId("block/casings/metal/machine_casing_americium"));

    // PipeCasings
    public static final BlockEntry<Block> IRIDIUM_PIPE_CASING = createCasingBlock(
            "iridium_pipe_casing",
            modId("block/casings/pipe/machine_casing_pipe_iridium"));
    public static final BlockEntry<Block> AMERICIUM_PIPE_CASING = createCasingBlock(
            "americium_pipe_casing",
            modId("block/casings/pipe/machine_casing_pipe_americium"));

    // GearboxCasings
    public static final BlockEntry<Block> IRIDIUM_GEARBOX_CASING = createCasingBlock(
            "iridium_gearbox_casing",
            modId("block/casings/gear/machine_casing_gearbox_iridium"));
    public static final BlockEntry<Block> AMERICIUM_GEARBOX_CASING = createCasingBlock(
            "americium_gearbox_casing",
            modId("block/casings/gear/machine_casing_gearbox_americium"));

    // Tank Wall
    public static final BlockEntry<Block> TANK_WALL_INVAR = createCasingBlock(
            "invar_tank_wall",
            modId("block/casings/tank/tank_wall_invar"));
    public static final BlockEntry<Block> TANK_WALL_ALUMINIUM = createCasingBlock(
            "aluminium_tank_wall",
            modId("block/casings/tank/tank_wall_aluminium"));
    public static final BlockEntry<Block> TANK_WALL_STAINLESS = createCasingBlock(
            "stainless_steel_tank_wall",
            modId("block/casings/tank/tank_wall_stainlesssteel"));
    public static final BlockEntry<Block> TANK_WALL_NETHERITE = createCasingBlock(
            "netherite_tank_wall",
            modId("block/casings/tank/tank_wall_netherite"));
    public static final BlockEntry<Block> TANK_WALL_TITANIUM = createCasingBlock(
            "titanium_tank_wall",
            modId("block/casings/tank/tank_wall_titanium"));
    public static final BlockEntry<Block> TANK_WALL_TUNGSTEN = createCasingBlock(
            "tungsten_tank_wall",
            modId("block/casings/tank/tank_wall_tungsten"));
    public static final BlockEntry<Block> TANK_WALL_TUNGSTENSTEEL = createCasingBlock(
            "tungstensteel_tank_wall",
            modId("block/casings/tank/tank_wall_tungstensteel"));
}
