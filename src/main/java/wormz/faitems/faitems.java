package wormz.faitems;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import org.apache.logging.log4j.Logger;
import wormz.faitems.proxy.CommonProxy;

@Mod(modid = faitems.MODID, name = faitems.MODNAME, version = faitems.MODVERSION, dependencies = "required-after:forge@[14.23.5.2796,)", useMetadata = true)
public class faitems {

    public static final String MODID = "faitems";
    public static final String MODNAME = "FAItems";
    public static final String MODVERSION = "0.1";

    @SidedProxy(clientSide = "wormz.faitems.proxy.ClientProxy", serverSide = "wormz.faitems.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance
    public static faitems instance;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
