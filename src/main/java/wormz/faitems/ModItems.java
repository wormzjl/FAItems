package wormz.faitems;

import nc.enumm.MetaEnums;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import wormz.faitems.items.ItemCell;
import wormz.faitems.items.ItemCellBreeder;
import wormz.faitems.items.ItemCellDepleted;


public class ModItems {
    public static ItemCellBreeder fuel_thorium;
    public static ItemCellBreeder fuel_uranium;
    public static ItemCellBreeder fuel_neptunium;
    public static ItemCellBreeder fuel_plutonium;
    public static ItemCellBreeder fuel_mixed_oxide;
    public static ItemCellBreeder fuel_americium;
    public static ItemCellBreeder fuel_curium;
    public static ItemCellBreeder fuel_berkelium;
    public static ItemCellBreeder fuel_californium;
    public static ItemCellDepleted depleted_fuel_thorium;
    public static ItemCellDepleted depleted_fuel_uranium;
    public static ItemCellDepleted depleted_fuel_neptunium;
    public static ItemCellDepleted depleted_fuel_plutonium;
    public static ItemCellDepleted depleted_fuel_mixed_oxide;
    public static ItemCellDepleted depleted_fuel_americium;
    public static ItemCellDepleted depleted_fuel_curium;
    public static ItemCellDepleted depleted_fuel_berkelium;
    public static ItemCellDepleted depleted_fuel_californium;
    public static ItemCell empty_cell;

    static {
        fuel_thorium = new ItemCellBreeder(MetaEnums.ThoriumFuelType.class, "fuel_thorium");
        fuel_uranium = new ItemCellBreeder(MetaEnums.UraniumFuelType.class, "fuel_uranium");
        fuel_neptunium = new ItemCellBreeder(MetaEnums.NeptuniumFuelType.class, "fuel_neptunium");
        fuel_plutonium = new ItemCellBreeder(MetaEnums.PlutoniumFuelType.class, "fuel_plutonium");
        fuel_mixed_oxide = new ItemCellBreeder(MetaEnums.MixedOxideFuelType.class, "fuel_mixed_oxide");
        fuel_americium = new ItemCellBreeder(MetaEnums.AmericiumFuelType.class, "fuel_americium");
        fuel_curium = new ItemCellBreeder(MetaEnums.CuriumFuelType.class, "fuel_curium");
        fuel_berkelium = new ItemCellBreeder(MetaEnums.BerkeliumFuelType.class, "fuel_berkelium");
        fuel_californium = new ItemCellBreeder(MetaEnums.CaliforniumFuelType.class, "fuel_californium");
        depleted_fuel_thorium = new ItemCellDepleted(MetaEnums.ThoriumFuelType.class, "depleted_fuel_thorium");
        depleted_fuel_uranium = new ItemCellDepleted(MetaEnums.UraniumFuelType.class, "depleted_fuel_uranium");
        depleted_fuel_neptunium = new ItemCellDepleted(MetaEnums.NeptuniumFuelType.class, "depleted_fuel_neptunium");
        depleted_fuel_plutonium = new ItemCellDepleted(MetaEnums.PlutoniumFuelType.class, "depleted_fuel_plutonium");
        depleted_fuel_mixed_oxide = new ItemCellDepleted(MetaEnums.MixedOxideFuelType.class, "depleted_fuel_mixed_oxide");
        depleted_fuel_americium = new ItemCellDepleted(MetaEnums.AmericiumFuelType.class, "depleted_fuel_americium");
        depleted_fuel_curium = new ItemCellDepleted(MetaEnums.CuriumFuelType.class, "depleted_fuel_curium");
        depleted_fuel_berkelium = new ItemCellDepleted(MetaEnums.BerkeliumFuelType.class, "depleted_fuel_berkelium");
        depleted_fuel_californium = new ItemCellDepleted(MetaEnums.CaliforniumFuelType.class, "depleted_fuel_californium");
        empty_cell = new ItemCell("empty_cell");
    }

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        fuel_thorium.initModel();
        fuel_uranium.initModel();
        fuel_neptunium.initModel();
        fuel_plutonium.initModel();
        fuel_mixed_oxide.initModel();
        fuel_americium.initModel();
        fuel_curium.initModel();
        fuel_berkelium.initModel();
        fuel_californium.initModel();
        depleted_fuel_thorium.initModel();
        depleted_fuel_uranium.initModel();
        depleted_fuel_neptunium.initModel();
        depleted_fuel_plutonium.initModel();
        depleted_fuel_mixed_oxide.initModel();
        depleted_fuel_americium.initModel();
        depleted_fuel_curium.initModel();
        depleted_fuel_berkelium.initModel();
        depleted_fuel_californium.initModel();
        empty_cell.initModel();
    }

    @SideOnly(Side.CLIENT)
    public static void initColorHandler(ItemColors itemColor) {
        fuel_thorium.initColorHandler(itemColor);
        fuel_uranium.initColorHandler(itemColor);
        fuel_neptunium.initColorHandler(itemColor);
        fuel_plutonium.initColorHandler(itemColor);
        fuel_mixed_oxide.initColorHandler(itemColor);
        fuel_americium.initColorHandler(itemColor);
        fuel_curium.initColorHandler(itemColor);
        fuel_berkelium.initColorHandler(itemColor);
        fuel_californium.initColorHandler(itemColor);
        depleted_fuel_thorium.initColorHandler(itemColor);
        depleted_fuel_uranium.initColorHandler(itemColor);
        depleted_fuel_neptunium.initColorHandler(itemColor);
        depleted_fuel_plutonium.initColorHandler(itemColor);
        depleted_fuel_mixed_oxide.initColorHandler(itemColor);
        depleted_fuel_americium.initColorHandler(itemColor);
        depleted_fuel_curium.initColorHandler(itemColor);
        depleted_fuel_berkelium.initColorHandler(itemColor);
        depleted_fuel_californium.initColorHandler(itemColor);
    }

    public static void register(IForgeRegistry<Item> registry) {
        registry.register(fuel_thorium);
        registry.register(fuel_uranium);
        registry.register(fuel_americium);
        registry.register(fuel_berkelium);
        registry.register(fuel_californium);
        registry.register(fuel_curium);
        registry.register(fuel_mixed_oxide);
        registry.register(fuel_neptunium);
        registry.register(fuel_plutonium);
        registry.register(depleted_fuel_thorium);
        registry.register(depleted_fuel_uranium);
        registry.register(depleted_fuel_americium);
        registry.register(depleted_fuel_berkelium);
        registry.register(depleted_fuel_californium);
        registry.register(depleted_fuel_curium);
        registry.register(depleted_fuel_mixed_oxide);
        registry.register(depleted_fuel_neptunium);
        registry.register(depleted_fuel_plutonium);
        registry.register(empty_cell);
    }

}
