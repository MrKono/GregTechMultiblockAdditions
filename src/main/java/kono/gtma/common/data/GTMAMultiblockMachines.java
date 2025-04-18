package kono.gtma.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;

import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static kono.gtma.api.utils.GTMAValues.modId;
import static kono.gtma.common.data.GTMARegistration.REGISTRATE;

public class GTMAMultiblockMachines {

    static {
        REGISTRATE.creativeModeTab(() -> GTMACreativeTab.BLOCKS);
    }

    public static final MultiblockMachineDefinition ORE_FACTORY = REGISTRATE.multiblock(
            "ore_factory", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTMARecipeTypes.ORE_FACTORY)
            .recipeModifier(GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
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
            "industrial_ore_factory", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.ALL)
            .recipeType(GTMARecipeTypes.ORE_FACTORY)
            .recipeModifiers(GTRecipeModifiers.PARALLEL_HATCH, GTRecipeModifiers.OC_NON_PERFECT_SUBTICK)
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

    public static void init() {}
}
