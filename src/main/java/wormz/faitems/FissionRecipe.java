package wormz.faitems;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethodStatic;

import java.util.HashMap;
import java.util.Map;

@ZenClass(value = "faitems.FissionRecipe")
@ZenRegister
public class FissionRecipe {
    static Map<ItemStack, ItemStack> recipes = new HashMap<>();

    @ZenMethodStatic
    public static void addRecipe(IIngredient ingredient, IItemStack output) {
        if (ingredient.getItems() == null) throw new AssertionError("Null Items");
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(output.getName()));
        ItemStack OutPut = new ItemStack(item);
        item.setDamage(OutPut, output.getDamage());
        for (IItemStack stack : ingredient.getItems()) {
            item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(stack.getName()));
            ItemStack Ingredient = new ItemStack(item);
            item.setDamage(Ingredient, stack.getDamage());
            recipes.put(Ingredient, OutPut);
        }
    }

    public static ItemStack getOutput(ItemStack stack) {
        return recipes.get(stack).copy();
    }
}
