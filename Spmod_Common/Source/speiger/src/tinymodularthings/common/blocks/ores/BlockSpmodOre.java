package speiger.src.tinymodularthings.common.blocks.ores;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import speiger.src.tinymodularthings.common.config.ModObjects.TinyItems;
import speiger.src.tinymodularthings.common.utils.TinyTextureHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSpmodOre extends Block
{
	
	public BlockSpmodOre(int par1)
	{
		super(par1, Material.rock);
		setHardness(4.0F);
		setResistance(4.0F);
		setCreativeTab(CreativeTabs.tabFood);
		MinecraftForge.setBlockHarvestLevel(this, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(this, 1, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(this, 2, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(this, 3, "pickaxe", 2);
		MinecraftForge.setBlockHarvestLevel(this, 5, "pickaxe", 3);
		OreDictionary.registerOre("oreCopper", new ItemStack(this));
		OreDictionary.registerOre("oreTin", new ItemStack(this, 1, 1));
		OreDictionary.registerOre("oreSilver", new ItemStack(this, 1, 2));
		OreDictionary.registerOre("oreLead", new ItemStack(this, 1, 3));
	}
	
	@Override
	public int idDropped(int meta, Random par2Random, int dmg)
	{
		if (meta == 5)
		{
			return TinyItems.IridiumDrop.itemID;
		}
		return blockID;
	}
	
	@Override
	public int damageDropped(int par1)
	{
		if (par1 == 5)
		{
			return 0;
		}
		return par1;
	}
	
	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		int id = world.getBlockId(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		return new ItemStack(id, 1, meta);
	}
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random)
	{
		return 1;
	}
	
	@Override
	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	{
		return 4.0F;
	}
	
	Icon[] oreTexture = new Icon[6];
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2)
	{
		return oreTexture[par2];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1)
	{
		oreTexture[0] = par1.registerIcon(TinyTextureHelper.getTextureStringFromName("ores/oreCopper"));
		oreTexture[1] = par1.registerIcon(TinyTextureHelper.getTextureStringFromName("ores/oreTin"));
		oreTexture[2] = par1.registerIcon(TinyTextureHelper.getTextureStringFromName("ores/oreSilver"));
		oreTexture[3] = par1.registerIcon(TinyTextureHelper.getTextureStringFromName("ores/oreLead"));
		oreTexture[4] = par1.registerIcon(TinyTextureHelper.getTextureStringFromName("ores/oreBauxit"));
		oreTexture[5] = par1.registerIcon(TinyTextureHelper.getTextureStringFromName("ores/oreIridium"));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3)
	{
		for (int i = 0; i < 6; i++)
		{
			if (i == 4)
			{
				continue;
			}
			
			par3.add(new ItemStack(par1, 1, i));
		}
	}
	
}