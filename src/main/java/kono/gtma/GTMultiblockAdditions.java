package kono.gtma;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import kono.gtma.api.utils.GTMAValues;
import kono.gtma.client.ClientProxy;
import kono.gtma.common.CommonProxy;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GTMAValues.MOD_ID)
public class GTMultiblockAdditions {

    public GTMultiblockAdditions() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register Machine
        // Register RecipeType
        DistExecutor.unsafeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    }
}
