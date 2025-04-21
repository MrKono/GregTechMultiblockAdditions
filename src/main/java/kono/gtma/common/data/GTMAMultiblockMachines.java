package kono.gtma.common.data;

import net.minecraft.network.chat.Component;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.gregtechceu.gtceu.config.ConfigHolder;
import com.gregtechceu.gtceu.utils.GTUtil;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static kono.gtma.api.utils.GTMAUtils.registerMultiblockTank;
import static kono.gtma.api.utils.GTMAUtils.registerTankValve;
import static kono.gtma.api.utils.GTMAValues.modId;
import static kono.gtma.common.data.GTMARegistration.REGISTRATE;

public class GTMAMultiblockMachines {

    static {
        REGISTRATE.creativeModeTab(() -> GTMACreativeTab.BLOCKS);
    }

    public static final MultiblockMachineDefinition ORE_FACTORY = REGISTRATE.multiblock(
            "ore_processing_factory", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTMARecipeTypes.ORE_FACTORY)
            .recipeModifier(GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .tooltipBuilder((stack, tooltip) -> {
                tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip1"));
                tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip2"));
                if (GTUtil.isCtrlDown()) {
                    tooltip.add(Component.empty());
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process1"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process2"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process3"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process4"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process5"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process6"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process7"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process8"));
                    if (ConfigHolder.INSTANCE.recipes.generateLowQualityGems) {
                        tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process9"));
                    }
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process10"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process11"));
                    tooltip.add(Component.empty());
                } else {
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.hold_ctrl"));
                }
            })
            .appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####",
                            "AGGGGGA#####", "AAAAAAA#####", "############", "############", "############",
                            "############", "############", "############")
                    .aisle("AAAAAAAABBBA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA",
                            "GP$$$PGACCCA", "AAAAAAAACCCA", "########DDD#", "########DDD#", "########DDD#",
                            "########DDD#", "########DDD#", "############")
                    .aisle("AAAAAAABBBBB", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C",
                            "G$P$P$GC$$$C", "AAAAAAAC$$$C", "#######D$$$D", "#######D$$$D", "#######D$$$D",
                            "#######D$$$D", "#######D$$$D", "########EEE#")
                    .aisle("AAAAAAABBBBB", "G$$H$$GC$H$C", "G$$H$$GC$H$C", "G$$H$$GC$H$C", "G$$H$$GC$H$C",
                            "G$$H$$GC$H$C", "AAAAAAAC$H$C", "#######D$H$D", "#######D$H$D", "#######D$H$D",
                            "#######D$H$D", "#######D$H$D", "########EME#")
                    .aisle("AAAAAAABBBBB", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C",
                            "G$P$P$GC$$$C", "AAAAAAAC$$$C", "#######D$$$D", "#######D$$$D", "#######D$$$D",
                            "#######D$$$D", "#######D$$$D", "########EEE#")
                    .aisle("AAAAAAAABBBA", "GP$$$PGACSCA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA",
                            "GP$$$PGACCCA", "AAAAAAAACCCA", "########DDD#", "########DDD#", "########DDD#",
                            "########DDD#", "########DDD#", "############")
                    .aisle("AAAAAAA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####",
                            "AGGGGGA#####", "AAAAAAA#####", "############", "############", "############",
                            "############", "############", "############")
                    .where('A', blocks(GTMABlocks.FACTORY_CASING.get()))
                    .where('B', blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
                            .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(3))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(5, 1)))
                    .where('C', blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
                            .or(autoAbilities(true, true, false))
                            .or(abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(20, 1)))
                    .where('D', blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
                            .or(abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(10, 1)))
                    .where('E', blocks(GTBlocks.CASING_STAINLESS_CLEAN.get()))
                    .where('G', blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                    .where('H', blocks(GTMABlocks.IRIDIUM_GEARBOX_CASING.get()))
                    .where('M', abilities(PartAbility.MUFFLER))
                    .where('P', blocks(GTMABlocks.IRIDIUM_PIPE_CASING.get()))
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('#', any())
                    .where('$', air())
                    .build())
            .workableCasingRenderer(GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                    GTCEu.id("block/multiblock/electric_blast_furnace"))
            .register();
    public static final MultiblockMachineDefinition INDUSTRIAL_ORE_FACTORY = REGISTRATE.multiblock(
            "industrial_ore_processing_factory", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTMARecipeTypes.ORE_FACTORY)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
            .tooltipBuilder((stack, tooltip) -> {
                tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip1"));
                tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip2"));
                if (GTUtil.isCtrlDown()) {
                    tooltip.add(Component.empty());
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process1"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process2"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process3"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process4"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process5"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process6"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process7"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process8"));
                    if (ConfigHolder.INSTANCE.recipes.generateLowQualityGems) {
                        tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process9"));
                    }
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process10"));
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.process11"));
                    tooltip.add(Component.empty());
                } else {
                    tooltip.add(Component.translatable("gtma.machine.ore_factory.tooltip.hold_ctrl"));
                }
            })
            .appearanceBlock(GTMABlocks.FACTORY_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####",
                            "AGGGGGA#####", "AAAAAAA#####", "############", "############", "############",
                            "############", "############", "############")
                    .aisle("AAAAAAAABBBA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA",
                            "GP$$$PGACCCA", "AAAAAAAACCCA", "########DDD#", "########DDD#", "########DDD#",
                            "########DDD#", "########DDD#", "############")
                    .aisle("AAAAAAABBBBB", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C",
                            "G$P$P$GC$$$C", "AAAAAAAC$$$C", "#######D$$$D", "#######D$$$D", "#######D$$$D",
                            "#######D$$$D", "#######D$$$D", "########EEE#")
                    .aisle("AAAAAAABBBBB", "G$$H$$GC$H$C", "G$$H$$GC$H$C", "G$$H$$GC$H$C", "G$$H$$GC$H$C",
                            "G$$H$$GC$H$C", "AAAAAAAC$H$C", "#######D$H$D", "#######D$H$D", "#######D$H$D",
                            "#######D$H$D", "#######D$H$D", "########EME#")
                    .aisle("AAAAAAABBBBB", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C", "G$P$P$GC$$$C",
                            "G$P$P$GC$$$C", "AAAAAAAC$$$C", "#######D$$$D", "#######D$$$D", "#######D$$$D",
                            "#######D$$$D", "#######D$$$D", "########EEE#")
                    .aisle("AAAAAAAABBBA", "GP$$$PGACSCA", "GP$$$PGACCCA", "GP$$$PGACCCA", "GP$$$PGACCCA",
                            "GP$$$PGACCCA", "AAAAAAAACCCA", "########DDD#", "########DDD#", "########DDD#",
                            "########DDD#", "########DDD#", "############")
                    .aisle("AAAAAAA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####", "AGGGGGA#####",
                            "AGGGGGA#####", "AAAAAAA#####", "############", "############", "############",
                            "############", "############", "############")
                    .where('A', blocks(GTMABlocks.ADVANCED_FACTORY_CASING.get()))
                    .where('B', blocks(GTMABlocks.FACTORY_CASING.get())
                            .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1).setMaxGlobalLimited(3))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setMaxGlobalLimited(5, 1)))
                    .where('C', blocks(GTMABlocks.FACTORY_CASING.get())
                            .or(autoAbilities(true, true, true))
                            .or(abilities(PartAbility.IMPORT_ITEMS).setMaxGlobalLimited(20, 1)))
                    .where('D', blocks(GTMABlocks.FACTORY_CASING.get())
                            .or(abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(10, 1)))
                    .where('E', blocks(GTMABlocks.FACTORY_CASING.get()))
                    .where('G', blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                    .where('H', blocks(GTMABlocks.AMERICIUM_GEARBOX_CASING.get()))
                    .where('M', abilities(PartAbility.MUFFLER))
                    .where('P', blocks(GTMABlocks.AMERICIUM_PIPE_CASING.get()))
                    .where('S', controller(blocks(definition.getBlock())))
                    .where('#', any())
                    .where('$', air())
                    .build())
            .workableCasingRenderer(modId("block/casings/metal/machine_casing_iridium"),
                    GTCEu.id("block/multiblock/electric_blast_furnace"))
            .register();

    // Multiblock Tanks
    public static final MachineDefinition INVAR_TANK_VALVE = registerTankValve(
            "invar_tank_valve", "Invar Tank Valve", true,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_invar"), overlay));
    public static final MultiblockMachineDefinition INVAR_MULTIBLOCK_TANK = registerMultiblockTank(
            "invar_multiblock_tank", "Invar Multiblock Tank", 750 * 1000,
            GTMABlocks.TANK_WALL_INVAR, INVAR_TANK_VALVE::getBlock,
            null,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_invar"), overlay));

    public static final MachineDefinition NETHERITE_TANK_VALVE = registerTankValve(
            "netherite_tank_valve", "Netherite Tank Valve", true,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_netherite"), overlay));
    public static final MultiblockMachineDefinition NETHERITE_MULTIBLOCK_TANK = registerMultiblockTank(
            "netherite_multiblock_tank", "Netherite Multiblock Tank", 3500 * 1000,
            GTMABlocks.TANK_WALL_NETHERITE, NETHERITE_TANK_VALVE::getBlock,
            null,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_netherite"), overlay));

    public static final MachineDefinition TITANIUM_TANK_VALVE = registerTankValve(
            "titanium_tank_valve", "Titanium Tank Valve", true,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_titanium"), overlay));
    public static final MultiblockMachineDefinition TITANIUM_MULTIBLOCK_TANK = registerMultiblockTank(
            "titanium_multiblock_tank", "Titanium Multiblock Tank", 8000 * 1000,
            GTMABlocks.TANK_WALL_TITANIUM, TITANIUM_TANK_VALVE::getBlock,
            null,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_titanium"), overlay));

    public static final MachineDefinition TUNGSTEN_TANK_VALVE = registerTankValve(
            "tungsten_tank_valve", "Tungsten Tank Valve", true,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_tungsten"), overlay));
    public static final MultiblockMachineDefinition TUNGSTEN_MULTIBLOCK_TANK = registerMultiblockTank(
            "tungsten_multiblock_tank", "Tungsten Multiblock Tank", 12000 * 1000,
            GTMABlocks.TANK_WALL_TUNGSTEN, TUNGSTEN_TANK_VALVE::getBlock,
            null,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_tungsten"), overlay));

    public static final MachineDefinition TUNGSTENSTEEL_TANK_VALVE = registerTankValve(
            "tungstensteel_tank_valve", "Tungstensteel Tank Valve", true,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_tungstensteel"), overlay));
    public static final MultiblockMachineDefinition TUNGSTENSTEEL_MULTIBLOCK_TANK = registerMultiblockTank(
            "tungstensteel_multiblock_tank", "Tungstensteel Multiblock Tank", 16000 * 1000,
            GTMABlocks.TANK_WALL_TUNGSTENSTEEL, TUNGSTENSTEEL_TANK_VALVE::getBlock,
            null,
            (builder, overlay) -> builder.workableCasingRenderer(
                    modId("block/casings/tank/tank_wall_tungstensteel"), overlay));

    public static void init() {}
}
