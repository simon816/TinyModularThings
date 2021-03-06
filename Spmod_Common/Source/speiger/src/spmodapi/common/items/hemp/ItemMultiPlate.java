package speiger.src.spmodapi.common.items.hemp;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import speiger.src.api.items.plates.PlateInterface;
import speiger.src.api.items.plates.PlateManager;
import speiger.src.api.util.SpmodMod;
import speiger.src.spmodapi.common.blocks.deko.MultiPlate;
import speiger.src.spmodapi.common.config.ModObjects.APIBlocks;
import speiger.src.spmodapi.common.config.ModObjects.APIUtils;
import speiger.src.spmodapi.common.handler.PlateHandler;
import speiger.src.spmodapi.common.items.SpmodItem;
import speiger.src.spmodapi.common.recipes.SpmodRecipeRegistry;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMultiPlate extends SpmodItem
{
	
	public ItemMultiPlate(int par1)
	{
		super(par1);
		this.setHasSubtypes(true);
		this.setCreativeTab(APIUtils.tabHempDeko);
		PlateManager.plates = new PlateHandler();
	}

	
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1)
	{
		if(par1 >= textures.length)
		{
			return this.itemIcon;
		}
		return textures[par1];
	}



	Icon[] textures = new Icon[0];
	

	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1)
	{
		textures = new Icon[PlateManager.plates.getAllIdentifiers().size()];
		for(int i = 0;i<textures.length;i++)
		{
			textures[i] = par1.registerIcon(PlateManager.plates.getIconFromIdentity(PlateManager.plates.getAllIdentifiers().get(i)));
		}
	}

	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3)
	{
		PlateInterface manager = PlateManager.plates;
		if(manager != null && manager.getAllIdentifiers().size() > 0)
		{
			for(int i = 0;i<manager.getAllIdentifiers().size();i++)
			{
				par3.add(new ItemStack(par1, 1, manager.getInfoFromIdentity(manager.getAllIdentifiers().get(i)).getMetadata()));
			}
		}
	}



	@Override
	public void registerItems(int id, SpmodMod par0)
	{
		
	}
	
	@Override
	public String getDisplayName(ItemStack par1, SpmodMod par0)
	{
		PlateInterface manager = PlateManager.plates;
		if(manager != null && manager.getAllIdentifiers().size() > 0)
		{
			return manager.getInfoFromIdentity(manager.getAllIdentifiers().get(par1.getItemDamage())).getDisplayName();
		}
		return "Nothing";
	}
	
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        int i1 = par3World.getBlockId(par4, par5, par6);

        if (i1 == Block.snow.blockID && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1)
        {
            par7 = 1;
        }
        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID
                && (Block.blocksList[i1] == null || !Block.blocksList[i1].isBlockReplaceable(par3World, par4, par5, par6)))
        {
            if (par7 == 0)
            {
                --par5;
            }

            if (par7 == 1)
            {
                ++par5;
            }

            if (par7 == 2)
            {
                --par6;
            }

            if (par7 == 3)
            {
                ++par6;
            }

            if (par7 == 4)
            {
                --par4;
            }

            if (par7 == 5)
            {
                ++par4;
            }
        }

        if (par1ItemStack.stackSize == 0)
        {
            return false;
        }
        else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else if (par5 == 255 && Block.blocksList[APIBlocks.multiPlate.blockID].blockMaterial.isSolid())
        {
            return false;
        }
        else
        {
        	if(par3World.setBlock(par4, par5, par6, APIBlocks.multiPlate.blockID))
        	{
        		TileEntity tile = par3World.getBlockTileEntity(par4, par5, par6);
        		if(tile != null && tile instanceof MultiPlate) 
        		{
        			MultiPlate plate = (MultiPlate) tile;
        			plate.setFacing(ForgeDirection.OPPOSITES[par7]);
        			PlateInterface info = PlateManager.plates;
        			if(info != null && info.getAllIdentifiers().size() > 0 && info.getAllIdentifiers().size() > par1ItemStack.getItemDamage())
        			{
        				plate.setIdentity(info.getAllIdentifiers().get(par1ItemStack.getItemDamage()));
        				par1ItemStack.stackSize--;
        				return true;
        			}
        		}
        		par3World.setBlock(par4, par5, par6, 0);
        	}
        }
        return false;
    }
	
}
