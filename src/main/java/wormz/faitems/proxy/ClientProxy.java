package wormz.faitems.proxy;

import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import wormz.faitems.Fuels;
import wormz.faitems.IFuel;
import wormz.faitems.ModItems;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModItems.initModels();
    }

    @SubscribeEvent
    public static void registerColorHandlers(ColorHandlerEvent.Item event) {
        ModItems.initColorHandler(event.getItemColors());
    }
}