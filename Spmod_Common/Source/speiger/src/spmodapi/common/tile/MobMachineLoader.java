package speiger.src.spmodapi.common.tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import speiger.src.api.items.ItemCollection;
import speiger.src.spmodapi.common.blocks.utils.MobMachine;
import speiger.src.spmodapi.common.blocks.utils.MobMachine.DropType;
import speiger.src.spmodapi.common.config.SpmodConfig;
import speiger.src.spmodapi.common.config.ModObjects.APIItems;
import speiger.src.spmodapi.common.enums.EnumColor;
import speiger.src.spmodapi.common.lib.SpmodAPILib;
import speiger.src.spmodapi.common.util.proxy.PathProxy;

public class MobMachineLoader 
{
	public static Random rand = new Random();
	
	public static void initMobMachines()
	{
		//Not Inited
		MobMachine.createMob(0, false, 0, getMobMachineTextures("Uninitialized"));
		
		//Pig
		MobMachine.createMob(1, true, 10, getMobMachineTextures("Pig"));
		MobMachine.setName(1, "Pig");
		MobMachine.addActivators(1, new ItemStack(Item.porkRaw), new ItemStack(Item.porkCooked));
		MobMachine.addFood(1, new ItemStack[]{new ItemStack(Item.carrot), new ItemStack(APIItems.hemp)}, new int[]{500, 50});
		MobMachine.addDrops(1, DropType.Common, new ItemStack(Item.porkRaw), new ItemStack(Item.porkCooked));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(1, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 90));
		}
		
		//Sheep
		MobMachine.createMob(2, true, 20, getMobMachineTextures("Sheep"));
		MobMachine.setName(2, "Sheep");
		MobMachine.addActivators(2, ItemCollection.getAllWool());
		MobMachine.addFood(2, new ItemStack[]{new ItemStack(Item.wheat), new ItemStack(APIItems.hemp)}, new int[]{500, 50});
		MobMachine.addDrops(2, DropType.Common, ItemCollection.getAllWool());
		MobMachine.addDrops(2, DropType.Rare, ItemCollection.getAllDye());
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(2, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 91));
		}
		
		//Chicken
		MobMachine.createMob(3, true, 5, getMobMachineTextures("Chicken"));
		MobMachine.setName(3, "Chicken");
		MobMachine.addActivators(3, new ItemStack(Item.egg), new ItemStack(Item.chickenRaw), new ItemStack(Item.chickenCooked));
		MobMachine.addFood(3, new ItemStack[]{new ItemStack(Item.seeds), new ItemStack(APIItems.hempSeed)}, new int[]{500});
		MobMachine.addDrops(3, DropType.Common, new ItemStack(Item.egg), new ItemStack(Item.feather), new ItemStack(Item.chickenRaw), new ItemStack(Item.chickenCooked));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(3, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 93));
		}
		
		//Cow
		MobMachine.createMob(4, true, 15, getMobMachineTextures("Cow"));
		MobMachine.setName(4, "Cow");
		MobMachine.addActivators(4, new ItemStack(Item.leather), new ItemStack(Item.beefRaw), new ItemStack(Item.beefCooked));
		MobMachine.addFood(4, new ItemStack[]{new ItemStack(Item.wheat), new ItemStack(APIItems.hemp)}, new int[]{500, 50});
		MobMachine.addDrops(4, DropType.Common, new ItemStack(Item.leather), new ItemStack(Item.beefRaw), new ItemStack(Item.beefCooked));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(4, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 92));
		}
		
		//Mooshrooms
		MobMachine.createMob(5, true, 25, getMobMachineTextures("Mooshroom"));
		MobMachine.addActivators(5, new ItemStack(Block.mushroomBrown), new ItemStack(Block.mushroomRed), new ItemStack(Item.bowlSoup));
		MobMachine.addFood(5, new ItemStack[]{new ItemStack(Item.wheat), new ItemStack(APIItems.hemp)}, new int[]{500, 50});
		MobMachine.addDrops(5, DropType.Common, new ItemStack(Item.leather), new ItemStack(Item.beefRaw), new ItemStack(Item.beefCooked), new ItemStack(Block.mushroomRed), new ItemStack(Block.mushroomBrown));
		MobMachine.addDrops(5, DropType.Rare, new ItemStack(Item.bowlSoup), new ItemStack(Block.mushroomCapBrown), new ItemStack(Block.mushroomCapRed));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(5, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 96));
		}
			
		//Squid
		MobMachine.createMob(6, true, 7, getMobMachineTextures("Squid"));
		MobMachine.setName(6, "Squid");
		MobMachine.addActivators(6, OreDictionary.getOres("dyeBlack").toArray(new ItemStack[OreDictionary.getOres("dyeBlack").size()]));
		MobMachine.addFood(6, new ItemStack(Item.fishRaw), 1500);
		MobMachine.addFood(6, PathProxy.getFluidContainerItems(FluidRegistry.WATER), new int[]{500});
		MobMachine.addDrops(6, DropType.Common, new ItemStack(Item.dyePowder, 1, EnumColor.BLACK.getAsDye()));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(6, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 94));
		}
		
		//Zombie
		MobMachine.createMob(7, false, 5, getMobMachineTextures("Zombey"));
		MobMachine.setName(7, "Zombey");
		MobMachine.addActivator(7, new ItemStack(Item.rottenFlesh));
		MobMachine.addFood(7, new ItemStack[]{new ItemStack(Item.porkRaw), new ItemStack(Item.beefRaw), new ItemStack(Item.chickenRaw), new ItemStack(Item.porkCooked), new ItemStack(Item.beefCooked), new ItemStack(Item.chickenCooked)}, new int[]{900, 900, 900, 2500, 2500, 2500});
		MobMachine.addDrops(7, DropType.Common, new ItemStack(Item.rottenFlesh), new ItemStack(Item.poisonousPotato));
		MobMachine.addDrops(7, DropType.Rare, new ItemStack(Item.bone), new ItemStack(Item.potato), new ItemStack(Item.carrot), new ItemStack(Item.ingotIron), new ItemStack(Item.skull, 1, 2));
		MobMachine.addDrops(7, DropType.Rare, getItem(5, Item.shovelIron));
		MobMachine.addDrops(7, DropType.Rare, getItem(5, Item.swordIron));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(7, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 54));
		}
		
		//Skelete
		MobMachine.createMob(8, false, 4, getMobMachineTextures("Skeletor"));
		MobMachine.setName(8, "Skeletor");
		MobMachine.addActivators(8, new ItemStack(Item.bone), new ItemStack(Item.arrow));
		MobMachine.addFood(8, new ItemStack(Item.feather), 1000);
		MobMachine.addDrops(8, DropType.Common, new ItemStack(Item.arrow), new ItemStack(Item.bone), new ItemStack(Item.skull));
		MobMachine.addDrops(8, DropType.Rare, getItem(20, Item.bow));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(8, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 51));
		}
		
		//Spider
		MobMachine.createMob(9, false, 3, getMobMachineTextures("Spider"));
		MobMachine.setName(9, "Spider");
		MobMachine.addActivators(9, new ItemStack(Item.spiderEye), new ItemStack(Item.silk));
		MobMachine.addFood(9, new ItemStack[]{new ItemStack(Item.porkRaw), new ItemStack(Item.beefRaw), new ItemStack(Item.chickenRaw), new ItemStack(Item.fishRaw)}, new int[]{1000, 1000, 1000, 2000});
		MobMachine.addDrops(9, DropType.Common, new ItemStack(Item.silk), new ItemStack(Item.spiderEye), new ItemStack(Item.fermentedSpiderEye));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(9, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 52));
		}
		
		//Cave Spider
		MobMachine.createMob(10, false, 4, getMobMachineTextures("CaveSpider"));
		MobMachine.setName(10, "Cave Spider");
		MobMachine.addActivators(10, new ItemStack(Item.fermentedSpiderEye), new ItemStack(Block.web));
		MobMachine.addFood(10, new ItemStack[]{new ItemStack(Item.porkRaw), new ItemStack(Item.beefRaw), new ItemStack(Item.chickenRaw), new ItemStack(Item.fishRaw), new ItemStack(Block.mushroomBrown), new ItemStack(Block.mushroomRed)}, new int[]{1000, 1000, 1000, 2000, 2500, 2500});
		MobMachine.addDrops(10, DropType.Common, new ItemStack(Item.silk), new ItemStack(Item.spiderEye), new ItemStack(Item.fermentedSpiderEye), new ItemStack(Block.web));
		MobMachine.addDrops(10, DropType.Rare, new ItemStack(Item.potion, 1, 8196), new ItemStack(Item.potion, 1, 8260), new ItemStack(Item.potion, 1, 8228));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(10, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 59));
		}
		
		//Pig Zombey
		MobMachine.createMob(11, false, 8, getMobMachineTextures("PigZombey"));
		MobMachine.setName(11, "Pig Zombey");
		MobMachine.addActivators(11, new ItemStack(Item.goldNugget), new ItemStack(Block.netherrack));
		MobMachine.addFood(11, new ItemStack[]{new ItemStack(Item.appleRed), new ItemStack(Item.bread), new ItemStack(Item.bowlSoup), new ItemStack(Item.melon), new ItemStack(Block.melon)}, new int[]{900, 1200, 5000, 200, 1800});
		MobMachine.addDrops(11, DropType.Common, new ItemStack(Item.goldNugget), new ItemStack(Item.goldNugget, 5), new ItemStack(Item.ingotGold), new ItemStack(Item.swordGold));
		MobMachine.addDrops(11, DropType.Rare, getItem(35, Item.swordGold));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(11, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 57));
		}
		
		//Creeper
		MobMachine.createMob(12, false, 12, getMobMachineTextures("Creeper"));
		MobMachine.setName(12, "Creeper");
		MobMachine.addActivators(12, new ItemStack(Item.gunpowder), new ItemStack(Block.tnt));
		MobMachine.addFood(12, new ItemStack[]{new ItemStack(Item.ingotIron), new ItemStack(Item.goldNugget), new ItemStack(Item.ingotGold), new ItemStack(Item.diamond), new ItemStack(Item.emerald), new ItemStack(Block.blockLapis), new ItemStack(Block.blockIron), new ItemStack(Block.blockGold), new ItemStack(Block.blockDiamond), new ItemStack(Block.blockEmerald)}, new int[]{500, 600, 2400, 3000, 4000, 5000, 4500, 10000, 15000, 20000});
		MobMachine.addDrops(12, DropType.Common, new ItemStack(Item.gunpowder));
		MobMachine.addDrops(12, DropType.Rare, new ItemStack(Item.skull, 1, 4));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(12, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 50));
		}
		
		//Ghast
		MobMachine.createMob(13, false, 20, getMobMachineTextures("Ghast"));
		MobMachine.setName(13, "Ghast");
		MobMachine.addActivators(13, new ItemStack(Item.ghastTear), new ItemStack(Block.netherBrick));
		MobMachine.addFood(13, PathProxy.getFluidContainerItems(FluidRegistry.LAVA), new int[]{2000});
		MobMachine.addDrops(13, DropType.Common, new ItemStack(Item.gunpowder), new ItemStack(Item.ghastTear));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(13, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 56));
		}
		
		//Witch
		MobMachine.createMob(14, false, 18, getMobMachineTextures("Witch"));
		MobMachine.setName(14, "Witch");
		MobMachine.addActivator(14, new ItemStack(Item.netherStalkSeeds));
		MobMachine.addFood(14, new ItemStack[]{new ItemStack(Item.sugar), new ItemStack(Block.mushroomBrown), new ItemStack(Block.mushroomRed), new ItemStack(Item.bread)}, new int[]{250, 1500, 1700, 1200}); 
		MobMachine.addDrops(14, DropType.Common, getPotions(false, Item.potion));
		MobMachine.addDrops(14, DropType.Rare, getPotions(true, Item.potion));
		if(SpmodConfig.MobMachineEggs)
		{
			MobMachine.addDrops(14, DropType.Legendary, new ItemStack(Item.monsterPlacer, 1, 66));
		}
		
		//Wither Skelete
		MobMachine.createMob(15, false, 25, getMobMachineTextures("WitherSkeletor"));
		
		//Endermann
		MobMachine.createMob(16, false, 32, getMobMachineTextures("Endermann"));
		
		//Ender Dragon
		MobMachine.createMob(17, false, 55, getMobMachineTextures("EnderDragon"));
		
		//Wither
		MobMachine.createMob(18, false, 40, getMobMachineTextures("Wither"));
		
		//Blaze
		MobMachine.createMob(19, false, 15, getMobMachineTextures("Blaze"));
	
	
	}
	
	
	private static String[] getMobMachineTextures(String name)
	{
		String[] tex = new String[2];
		tex[0] = SpmodAPILib.ModID.toLowerCase()+":mobmachine/MobMachine_"+name+"_Side";
		tex[1] = SpmodAPILib.ModID.toLowerCase()+":mobmachine/MobMachine_"+name+"_Front";
		return tex;
	}
	
	public static ItemStack[] getItem(int types, Item item)
	{
		ItemStack[] array = new ItemStack[types];
		ItemStack stack = null;
		for(int i = 0;i<types;i++)
		{
			stack = new ItemStack(item);
			EnchantmentHelper.addRandomEnchantment(rand, stack, rand.nextInt(types));
			array[i] = stack.copy();
		}
		
		return array;
	}
	
	public static ItemStack[] getPotions(boolean good, ItemPotion potion)
	{
		ArrayList<ItemStack> item = new ArrayList<ItemStack>();
		potion.getSubItems(potion.itemID, CreativeTabs.tabBrewing, item);
		ArrayList<ItemStack> end = new ArrayList<ItemStack>();
		for(ItemStack cu : item)
		{
			if(potion.hasEffect(cu))
			{
				List list = potion.getEffects(cu);
				boolean flag = true;
				for(int i = 0;i<list.size();i++)
				{
					PotionEffect effect = (PotionEffect) list.get(i);
					if(Potion.potionTypes[effect.getPotionID()].isBadEffect())
					{
						flag = false;
					}
				}
				
				if(good == flag)
				{
					end.add(cu);
				}
				
			}
		}
		return end.toArray(new ItemStack[end.size()]);
	}
	
}
