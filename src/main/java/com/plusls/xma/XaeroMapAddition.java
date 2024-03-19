package com.plusls.xma;

import com.plusls.xma.config.Configs;
import net.fabricmc.api.ClientModInitializer;
import top.hendrixshen.magiclib.dependency.api.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.api.annotation.Dependency;
import top.hendrixshen.magiclib.malilib.impl.ConfigHandler;
import top.hendrixshen.magiclib.malilib.impl.ConfigManager;

public class XaeroMapAddition implements ClientModInitializer {
    private static final int CONFIG_VERSION = 1;

    @Dependencies(and = {
            //#if MC > 11904
            @Dependency(value = "xaerominimap", versionPredicate = ">=24.0.2", optional = true),
            @Dependency(value = "xaerobetterpvp", versionPredicate = ">=23.9.4", optional = true),
            @Dependency(value = "xaeroworldmap", versionPredicate = ">=1.38.1", optional = true)
            //#elseif MC > 11902
            //$$ @Dependency(value = "xaerominimap", versionPredicate = ">=23.9.3", optional = true),
            //$$ @Dependency(value = "xaerobetterpvp", versionPredicate = ">=23.9.4", optional = true),
            //$$ @Dependency(value = "xaeroworldmap", versionPredicate = ">=1.37.2", optional = true)
            //#elseif MC > 11701
            //$$ @Dependency(value = "xaerominimap", versionPredicate = ">=24.0.2", optional = true),
            //$$ @Dependency(value = "xaerobetterpvp", versionPredicate = ">=23.9.4", optional = true),
            //$$ @Dependency(value = "xaeroworldmap", versionPredicate = ">=1.38.1", optional = true)
            //#elseif MC > 11605
            //$$ @Dependency(value = "xaerominimap", versionPredicate = ">=23.9.7", optional = true),
            //$$ @Dependency(value = "xaerobetterpvp", versionPredicate = ">=23.9.4", optional = true),
            //$$ @Dependency(value = "xaeroworldmap", versionPredicate = ">=1.37.8", optional = true)
            //#elseif MC > 11502
            //$$ @Dependency(value = "xaerominimap", versionPredicate = ">=24.0.2", optional = true),
            //$$ @Dependency(value = "xaerobetterpvp", versionPredicate = ">=23.9.4", optional = true),
            //$$ @Dependency(value = "xaeroworldmap", versionPredicate = ">=1.38.1", optional = true)
            //#else
            //$$ @Dependency(value = "xaerominimap", versionPredicate = ">=21.10.0.4", optional = true),
            //$$ @Dependency(value = "xaerobetterpvp", versionPredicate = ">=21.10.0.4", optional = true),
            //$$ @Dependency(value = "xaeroworldmap", versionPredicate = ">=1.14.1", optional = true)
            //#endif
    })
    @Override
    public void onInitializeClient() {
        ConfigManager cm = ConfigManager.get(ModInfo.getModIdentifier());
        cm.parseConfigClass(Configs.class);
        ConfigHandler.register(new ConfigHandler(ModInfo.getModIdentifier(), cm, CONFIG_VERSION));
        Configs.init(cm);
    }
}
