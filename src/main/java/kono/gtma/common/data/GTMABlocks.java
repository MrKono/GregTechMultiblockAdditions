package kono.gtma.common.data;

import java.util.function.Supplier;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static kono.gtma.api.utils.GTMAValues.modId;
import static kono.gtma.common.data.GTMARegistration.REGISTRATE;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

public class GTMABlocks {

    public static void init() {}

    static {
        REGISTRATE.creativeModeTab(() -> GTMACreativeTab.BLOCKS);
    }

    // MetalCasings
    public static final BlockEntry<Block> FACTORY_CASING = createCasingBlock(
            "factory_casing",
            modId("block/casings/metal/machine_casing_iridium"));
    public static final BlockEntry<Block> ADVANCED_FACTORY_CASING = createCasingBlock(
            "advanced_factory_casing",
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

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    @SuppressWarnings("all")
    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false)).addLayer(type)
                .blockstate((ctx, prov) -> {
                    prov.simpleBlock(ctx.getEntry(), prov.models().cubeAll(name, texture));
                })
                .tag(TagKey.create(BuiltInRegistries.BLOCK.key(), new ResourceLocation("forge", "mineable/wrench")),
                        BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }
}
