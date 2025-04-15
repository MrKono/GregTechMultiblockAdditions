package kono.gtma.common.data.lang;

import java.util.Map;
import java.util.Set;

import com.gregtechceu.gtceu.data.lang.LangHandler;

import dev.toma.configuration.config.value.ConfigValue;
import dev.toma.configuration.config.value.ObjectValue;

import kono.gtma.api.utils.GTMAValues;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class EnglishLangHandler extends LangHandler {

    public static void init(RegistrateLangProvider provider) {
        // Materials
        // Misc
        // Recipe Name
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
