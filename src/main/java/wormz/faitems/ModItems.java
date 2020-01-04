package wormz.faitems;

import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import wormz.faitems.items.ItemBreederCell;
import wormz.faitems.items.ItemCell;
import wormz.faitems.items.ItemDepletedCell;
import wormz.faitems.items.ItemFuelCell;


public class ModItems {
    public static ItemFuelCell fuel_thorium;
    public static ItemFuelCell fuel_uranium;
    public static ItemFuelCell fuel_neptunium;
    public static ItemFuelCell fuel_plutonium;
    public static ItemFuelCell fuel_mixed_oxide;
    public static ItemFuelCell fuel_americium;
    public static ItemFuelCell fuel_curium;
    public static ItemFuelCell fuel_berkelium;
    public static ItemFuelCell fuel_californium;
    public static ItemBreederCell breeder_fuel_thorium;
    public static ItemBreederCell breeder_fuel_uranium;
    public static ItemBreederCell breeder_fuel_neptunium;
    public static ItemBreederCell breeder_fuel_plutonium;
    public static ItemBreederCell breeder_fuel_mixed_oxide;
    public static ItemBreederCell breeder_fuel_americium;
    public static ItemBreederCell breeder_fuel_curium;
    public static ItemBreederCell breeder_fuel_berkelium;
    public static ItemBreederCell breeder_fuel_californium;
    public static ItemDepletedCell depleted_fuel_thorium;
    public static ItemDepletedCell depleted_fuel_uranium;
    public static ItemDepletedCell depleted_fuel_neptunium;
    public static ItemDepletedCell depleted_fuel_plutonium;
    public static ItemDepletedCell depleted_fuel_mixed_oxide;
    public static ItemDepletedCell depleted_fuel_americium;
    public static ItemDepletedCell depleted_fuel_curium;
    public static ItemDepletedCell depleted_fuel_berkelium;
    public static ItemDepletedCell depleted_fuel_californium;
    public static ItemCell empty_cell;

    static {
        fuel_thorium = new ItemFuelCell(Fuels.ThoriumFuelType.class, "fuel_thorium");
        fuel_uranium = new ItemFuelCell(Fuels.UraniumFuelType.class, "fuel_uranium");
        fuel_neptunium = new ItemFuelCell(Fuels.NeptuniumFuelType.class, "fuel_neptunium");
        fuel_plutonium = new ItemFuelCell(Fuels.PlutoniumFuelType.class, "fuel_plutonium");
        fuel_mixed_oxide = new ItemFuelCell(Fuels.MixedFuelType.class, "fuel_mixed_oxide");
        fuel_americium = new ItemFuelCell(Fuels.AmericiumFuelType.class, "fuel_americium");
        fuel_curium = new ItemFuelCell(Fuels.CuriumFuelType.class, "fuel_curium");
        fuel_berkelium = new ItemFuelCell(Fuels.BerkeliumFuelType.class, "fuel_berkelium");
        fuel_californium = new ItemFuelCell(Fuels.CaliforniumFuelType.class, "fuel_californium");
        breeder_fuel_thorium = new ItemBreederCell(Fuels.ThoriumFuelType.class, "breeder_fuel_thorium");
        breeder_fuel_uranium = new ItemBreederCell(Fuels.UraniumFuelType.class, "breeder_fuel_uranium");
        breeder_fuel_neptunium = new ItemBreederCell(Fuels.NeptuniumFuelType.class, "breeder_fuel_neptunium");
        breeder_fuel_plutonium = new ItemBreederCell(Fuels.PlutoniumFuelType.class, "breeder_fuel_plutonium");
        breeder_fuel_mixed_oxide = new ItemBreederCell(Fuels.MixedFuelType.class, "breeder_fuel_mixed_oxide");
        breeder_fuel_americium = new ItemBreederCell(Fuels.AmericiumFuelType.class, "breeder_fuel_americium");
        breeder_fuel_curium = new ItemBreederCell(Fuels.CuriumFuelType.class, "breeder_fuel_curium");
        breeder_fuel_berkelium = new ItemBreederCell(Fuels.BerkeliumFuelType.class, "breeder_fuel_berkelium");
        breeder_fuel_californium = new ItemBreederCell(Fuels.CaliforniumFuelType.class, "breeder_fuel_californium");
        depleted_fuel_thorium = new ItemDepletedCell(Fuels.ThoriumFuelType.class, "depleted_fuel_thorium");
        depleted_fuel_uranium = new ItemDepletedCell(Fuels.UraniumFuelType.class, "depleted_fuel_uranium");
        depleted_fuel_neptunium = new ItemDepletedCell(Fuels.NeptuniumFuelType.class, "depleted_fuel_neptunium");
        depleted_fuel_plutonium = new ItemDepletedCell(Fuels.PlutoniumFuelType.class, "depleted_fuel_plutonium");
        depleted_fuel_mixed_oxide = new ItemDepletedCell(Fuels.MixedFuelType.class, "depleted_fuel_mixed_oxide");
        depleted_fuel_americium = new ItemDepletedCell(Fuels.AmericiumFuelType.class, "depleted_fuel_americium");
        depleted_fuel_curium = new ItemDepletedCell(Fuels.CuriumFuelType.class, "depleted_fuel_curium");
        depleted_fuel_berkelium = new ItemDepletedCell(Fuels.BerkeliumFuelType.class, "depleted_fuel_berkelium");
        depleted_fuel_californium = new ItemDepletedCell(Fuels.CaliforniumFuelType.class, "depleted_fuel_californium");
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
        breeder_fuel_thorium.initModel();
        breeder_fuel_uranium.initModel();
        breeder_fuel_neptunium.initModel();
        breeder_fuel_plutonium.initModel();
        breeder_fuel_mixed_oxide.initModel();
        breeder_fuel_americium.initModel();
        breeder_fuel_curium.initModel();
        breeder_fuel_berkelium.initModel();
        breeder_fuel_californium.initModel();
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
        breeder_fuel_thorium.initColorHandler(itemColor);
        breeder_fuel_uranium.initColorHandler(itemColor);
        breeder_fuel_neptunium.initColorHandler(itemColor);
        breeder_fuel_plutonium.initColorHandler(itemColor);
        breeder_fuel_mixed_oxide.initColorHandler(itemColor);
        breeder_fuel_americium.initColorHandler(itemColor);
        breeder_fuel_curium.initColorHandler(itemColor);
        breeder_fuel_berkelium.initColorHandler(itemColor);
        breeder_fuel_californium.initColorHandler(itemColor);
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
        registry.register(breeder_fuel_thorium);
        registry.register(breeder_fuel_uranium);
        registry.register(breeder_fuel_americium);
        registry.register(breeder_fuel_berkelium);
        registry.register(breeder_fuel_californium);
        registry.register(breeder_fuel_curium);
        registry.register(breeder_fuel_mixed_oxide);
        registry.register(breeder_fuel_neptunium);
        registry.register(breeder_fuel_plutonium);
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
