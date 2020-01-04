package wormz.faitems;

import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class FissionRecipe {
    static Map<ItemStack, ItemStack> recipes = new HashMap<>();

    public static void addRecipe(IIngredient ingredient, IItemStack output) {
        if (ingredient.getItems() == null) throw new AssertionError("Null Items");
        for (IItemStack stack : ingredient.getItems()) {
            //todo:handle add recipe
        }
    }

    public static ItemStack getOutput(ItemStack stack) {
        return recipes.get(stack);
    }
}
