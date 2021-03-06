package speiger.src.api.hopper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;

public class HopperRegistry
{
	static HashMap<String, HopperUpgrade> upgrades = new HashMap<String, HopperUpgrade>();
	static HashMap<List<Integer>, HopperUpgrade> items = new HashMap<List<Integer>, HopperUpgrade>();
	
	public static void registerHopperUpgrade(HopperUpgrade par1)
	{
		upgrades.put(par1.getNBTName(), par1);
	}
	
	public static void registerBasicHopperUpgrade(Item item, int meta, HopperUpgrade par1)
	{
		String name = par1.getNBTName();
		if(!upgrades.containsKey(name))
		{
			upgrades.put(name, par1);
		}
		if(item != null)
		{
			List<Integer> list = Arrays.asList(item.itemID, meta);
			if(!items.containsKey(list))
			{
				items.put(list, par1);
			}
		}
		
	}
	/**
	 * Function for Advanced HopperUpgrade Adding.
	 * Do NOT CHANGE THIS DATA IN YOUR ITEM. Else it does no longer Count as Valid Hopper Upgrade
	 */
	public static void registerCustomHopperUpgrade(HopperUpgrade par1, ItemStack par2)
	{
		String name = par1.getNBTName();
		
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setString("HopperUpgrade", name);
		nbt.setBoolean("Remove", false);
		nbt.setBoolean("Infinite", false);
		par2.setTagInfo("Hopper", nbt);
		if(!upgrades.containsValue(par1))
		{
			upgrades.put(name, par1);
		}
	}
	
	public static boolean canApplyUpgrade(HopperUpgrade par1, ArrayList<HopperUpgrade> par2)
	{
		int maxSize = par1.getMaxStackSize();
		int cuSize = 0;
		
		if(!upgrades.containsKey(par1.getNBTName()))
		{
			return false;
		}
		
		for(int i = 0;i<par2.size();i++)
		{
			HopperUpgrade cu = par2.get(i);
			if(cu.getNBTName().equalsIgnoreCase(par1.getNBTName()))
			{
				cuSize++;
			}
		}
		if(maxSize <= cuSize)
		{
			return false;
		}
		
		return true;
	}
	
	public static void makeGuiProviderForUpgrade(ItemStack open, HopperUpgrade target)
	{
		NBTTagString string = new NBTTagString("UpgradeGui", target.getNBTName());
		open.setTagInfo(string.getName(), string);
	}
	
	public static void removeGuiProviderFromItem(ItemStack par1)
	{
		if(par1.hasTagCompound())
		{
			par1.getTagCompound().removeTag("UpgradeGui");
		}
	}
	
	public static HashMap<String, HopperUpgrade> getHopperUpgrades()
	{
		return upgrades;
	}
	
	public static HopperUpgrade getHopperUpgradeFromNBT(String name)
	{
		return upgrades.get(name);
	}
	
	public static enum HopperEffect
	{
		Speed(EffectType.Else, 0),
		AllSlots(EffectType.Else, 0);
		
		int number;
		EffectType type;
		
		private HopperEffect(EffectType par0, int numbers)
		{
			number = numbers;
			type = par0;
		}
		
		public int getBoost()
		{
			return number;
		}
		
		public EffectType getUpgradeType()
		{
			return type;
		}
		
		public static enum EffectType
		{
			Item, Fluid, Energy, Else;
		}
	}

	public static boolean containsHopperUpgrade(ItemStack item)
	{
		List<Integer> ids = Arrays.asList(item.itemID, item.getItemDamage());
		if(items.containsKey(ids))
		{
			return true;
		}
		else if(item.hasTagCompound() && item.getTagCompound().hasKey("Hopper"))
		{
			return true;
		}
		return false;
	}
	
	public static HopperUpgrade getUpgradeFromItem(ItemStack item)
	{
		List<Integer> ids = Arrays.asList(item.itemID, item.getItemDamage());
		if(items.containsKey(ids))
		{
			return items.get(ids);
		}
		else if(item.hasTagCompound() && item.getTagCompound().hasKey("Hopper"))
		{
			NBTTagCompound nbt = item.getTagCompound().getCompoundTag("Hopper");
			return upgrades.get(nbt.getString("HopperUpgrade"));
		}
		return null;
	}

	public static boolean isRemovingUpgrade(ItemStack item)
	{
		return item.getTagCompound().getCompoundTag("Hopper").getBoolean("Remove");
	}

	public static boolean isUpgradeInfinte(ItemStack item)
	{
		return item.getTagCompound().getCompoundTag("Hopper").getBoolean("Infinite");
	}
}
