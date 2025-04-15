package kono.gtma.data;

import kono.gtma.common.data.GTMARegistration;
import kono.gtma.common.data.lang.EnglishLangHandler;

import com.tterrag.registrate.providers.ProviderType;

public class GTMADataGen {

    public static void init() {
        GTMARegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, EnglishLangHandler::init);
    }
}
