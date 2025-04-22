package kono.gtma.common.data.lang;

import java.util.Map;
import java.util.Set;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import dev.toma.configuration.config.value.ConfigValue;
import dev.toma.configuration.config.value.ObjectValue;

import kono.gtma.api.utils.GTMAValues;
import kono.gtma.common.data.GTMAMaterials;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishLangHandler extends LangHandler {

    public static void init(RegistrateLangProvider provider) {
        // Materials
        replace(provider, GTMAMaterials.Tribaloy.getUnlocalizedName(), "Tribaloy");
        replace(provider, GTMAMaterials.Staballoy.getUnlocalizedName(), "Staballoy");
        // Misc
        // Recipe Name
        replace(provider, "gtceu.ore_factory", "Ore Processing Factory");
        replace(provider, "gtceu.atmosphere_collector", "Atmosphere Collector");
        // Tooltips
        // ==Ore Factory==
        replace(provider, "gtma.machine.ore_factory.tooltip1", "All ore-processing in a single step");
        replace(provider, "gtma.machine.ore_factory.tooltip2", "Processing varies depending on §eProgrammed Circuit§7");
        replace(provider, "gtma.machine.ore_factory.tooltip.hold_ctrl", "Hold CTRL to show processing details");
        replace(provider, "gtma.machine.ore_factory.tooltip.process1",
                "§e1.§7 Macerate -> Ore Washer -> Macerate -> Centrifuge");
        replace(provider, "gtma.machine.ore_factory.tooltip.process2",
                "§e2.§7 Macerate -> Ore Washer -> Thermal Centrifuge -> Macerate");
        replace(provider, "gtma.machine.ore_factory.tooltip.process3",
                "§e3.§7 Macerate -> Ore Washer -> Sifter -> Centrifuge");
        replace(provider, "gtma.machine.ore_factory.tooltip.process4", "§e4.§7 Macerate -> Macerate -> Centrifuge");
        replace(provider, "gtma.machine.ore_factory.tooltip.process5",
                "§e5.§7 Macerate -> Thermal Centrifuge -> Macerate");
        replace(provider, "gtma.machine.ore_factory.tooltip.process6",
                "§e6.§7 Macerate -> Chemical Bathing -> Macerate -> Centrifuge");
        replace(provider, "gtma.machine.ore_factory.tooltip.process7",
                "§e7.§7 Macerate -> Chemical Bathing -> Thermal Centrifuge -> Macerate");
        replace(provider, "gtma.machine.ore_factory.tooltip.process8",
                "§e8.§7 Macerate -> Ore Washer -> Sifter -> Centrifuge / Macerate (All Gems)");
        replace(provider, "gtma.machine.ore_factory.tooltip.process9",
                "§e9.§7 Macerate -> Ore Washer -> Sifter -> Centrifuge / Macerate (Flawed and Chipped)");
        replace(provider, "gtma.machine.ore_factory.tooltip.process10",
                "§e10.§7 Macerate -> Ore Washer -> Macerate -> Electromagnetic Separating");
        replace(provider, "gtma.machine.ore_factory.tooltip.process11",
                "§e11.§7 Macerate -> Chemical Bathing -> Macerate -> Electromagnetic Separating");
        // Config
    }

    private static void dfs(RegistrateLangProvider provider, Set<String> added, Map<String, ConfigValue<?>> map) {
        for (var entry : map.entrySet()) {
            var id = entry.getValue().getId();
            if (added.add(id)) {
                provider.add(String.format("config.%s.option.%s", GTMAValues.MOD_ID, id), id);
            }
            if (entry.getValue() instanceof ObjectValue objectValue) {
                dfs(provider, added, objectValue.get());
            }
        }
    }
}
