package kono.gtma.api.utils;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import org.jetbrains.annotations.Nullable;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.fluids.PropertyFluidFilter;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.api.registry.registrate.MultiblockMachineBuilder;
import com.gregtechceu.gtceu.common.machine.multiblock.electric.MultiblockTankMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.TankValvePartMachine;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.DUMMY_RECIPES;
import static kono.gtma.common.data.GTMARegistration.REGISTRATE;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

public class GTMAUtils {

    // Blocks
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

    // Machines
    public static MultiblockMachineDefinition registerMultiblockTank(String name, String displayName, int capacity,
                                                                     Supplier<? extends Block> casing,
                                                                     Supplier<? extends Block> valve,
                                                                     @Nullable PropertyFluidFilter filter,
                                                                     BiConsumer<MultiblockMachineBuilder, ResourceLocation> rendererSetup) {
        MultiblockMachineBuilder builder = REGISTRATE
                .multiblock(name, holder -> new MultiblockTankMachine(holder, capacity, filter))
                .langValue(displayName)
                .tooltips(
                        Component.translatable("gtceu.machine.multiblock.tank.tooltip"),
                        Component.translatable("gtceu.universal.tooltip.fluid_storage_capacity", capacity))
                .rotationState(RotationState.ALL)
                .recipeType(DUMMY_RECIPES)
                .pattern(definition -> FactoryBlockPattern.start()
                        .aisle("CCC", "CCC", "CCC")
                        .aisle("CCC", "C#C", "CCC")
                        .aisle("CCC", "CSC", "CCC")
                        .where('S', controller(blocks(definition.get())))
                        .where('C', blocks(casing.get())
                                .or(blocks(valve.get()).setMaxGlobalLimited(2, 0)))
                        .where('#', air())
                        .build())
                .appearanceBlock(casing);
        rendererSetup.accept(builder, GTCEu.id("block/multiblock/multiblock_tank"));
        return builder.register();
    }

    public static MachineDefinition registerTankValve(String name, String displayName, boolean isMetal,
                                                      BiConsumer<MachineBuilder<?>, ResourceLocation> rendererSetup) {
        MachineBuilder<MachineDefinition> builder = REGISTRATE
                .machine(name, holder -> new TankValvePartMachine(holder, isMetal))
                .langValue(displayName)
                .tooltips(Component.translatable("gtceu.machine.tank_valve.tooltip"))
                .rotationState(RotationState.ALL);
        rendererSetup.accept(builder, GTCEu.id("block/multiblock/tank_valve"));
        return builder.register();
    }
}
