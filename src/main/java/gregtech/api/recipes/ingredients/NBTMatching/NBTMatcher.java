package gregtech.api.recipes.ingredients.NBTMatching;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

/**
 * This class is used to match NBT tags. Used to match a MapItemStackNBTIngredient NBT tag to a given NBT tag value.
 */
public class NBTMatcher {

    public NBTMatcher() {

    }

    /**
     * Return true without checking if the NBT actually tags match or exists.
     */
    public static NBTMatcher ANY = new NBTMatcher() {
        @Override
        public boolean evaluate(ItemStack stack, NBTcondition NBTcondition) {
            return true;
        }
    };

    public static NBTMatcher LESS_THAN = new NBTMatcher() {
        @Override
        public boolean keyValueMatches(Object stack, String nbtKey, Long value) {
            return getKeyValue(stack, nbtKey) < value;
        }
    };

    public static NBTMatcher LESS_THAN_OR_EQUALS = new NBTMatcher() {
        @Override
        public boolean keyValueMatches(Object stack, String nbtKey, Long value) {
            return getKeyValue(stack, nbtKey) <= value;
        }
    };

    public static NBTMatcher EQUALS = new NBTMatcher() {
        @Override
        public boolean keyValueMatches(Object stack, String nbtKey, Long value) {
            return getKeyValue(stack, nbtKey) == value;
        }
    };

    public static NBTMatcher GREATER_THAN_OR_EQUALS = new NBTMatcher() {
        @Override
        public boolean keyValueMatches(Object stack, String nbtKey, Long value) {
            return getKeyValue(stack, nbtKey) >= value;
        }
    };

    public static NBTMatcher GREATER_THAN = new NBTMatcher() {
        @Override
        public boolean keyValueMatches(Object stack, String nbtKey, Long value) {
            return getKeyValue(stack, nbtKey) > value;
        }
    };

    public boolean hasKey(ItemStack stack, String nbtKey) {
        if (stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt != null) {
                return nbt.hasKey(nbtKey);
            }
        }
        return false;
    }

    public boolean hasKey(FluidStack stack, String nbtKey) {
        if (stack.tag != null) {
            NBTTagCompound nbt = stack.tag;
            return nbt.hasKey(nbtKey);
        }
        return false;
    }

    public long getKeyValue(Object stack, String nbtKey) {
        NBTTagCompound nbt = null;
        if (stack instanceof ItemStack) {
            nbt = ((ItemStack)stack).getTagCompound();
        } else if (stack instanceof FluidStack) {
            nbt = ((FluidStack)stack).tag;
        }
        if (nbt != null) {
            return nbt.getLong(nbtKey);
        }
        return 0;
    }

    public boolean evaluate(ItemStack stack, NBTcondition NBTcondition) {
        return hasKey(stack, NBTcondition.nbtKey) && keyValueMatches(stack, NBTcondition.nbtKey, NBTcondition.value);
    }

    public boolean evaluate(FluidStack stack, NBTcondition NBTcondition) {
        return hasKey(stack, NBTcondition.nbtKey) && keyValueMatches(stack, NBTcondition.nbtKey, NBTcondition.value);
    }

    public boolean keyValueMatches(Object stack, String nbtKey, Long value) {
        return false;
    }
}
