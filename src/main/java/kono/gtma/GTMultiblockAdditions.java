package kono.gtma;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;

import kono.gtma.api.utils.GTMAValues;
import kono.gtma.client.ClientProxy;
import kono.gtma.common.CommonProxy;
import kono.gtma.event.EventHandler;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GTMAValues.MOD_ID)
public class GTMultiblockAdditions {

    public GTMultiblockAdditions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register Machine
        modEventBus.addGenericListener(MachineDefinition.class, EventHandler::registerMachines);
        // Register RecipeType
        modEventBus.addGenericListener(GTRecipeType.class, EventHandler::registerRecipeTypes);
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }
}
