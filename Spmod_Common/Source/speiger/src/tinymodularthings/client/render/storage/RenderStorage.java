package speiger.src.tinymodularthings.client.render.storage;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import speiger.src.tinymodularthings.common.blocks.storage.TinyTank;
import speiger.src.tinymodularthings.common.enums.EnumIDs;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderStorage implements ISimpleBlockRenderingHandler
{
	
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
	}
	
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if (tile != null && tile instanceof TinyTank)
		{
			renderTank((TinyTank) tile, block, renderer, world, x, y, z);
		}
		
		return true;
	}
	
	private void renderTank(TinyTank tile, Block block, RenderBlocks render, IBlockAccess world, int x, int y, int z)
	{
		FluidStack stack = tile.tank.getFluid();
		if (stack != null && stack.getFluid() != null && tile.renderLiquid)
		{
			Fluid fluid = stack.getFluid();
			Icon icon = fluid.getIcon(stack);
			
			double amount = (stack.amount / (tile.tank.getCapacity() * 0.114)) / 10;
			
			if (amount < 0.125)
			{
				double amountb = 0.13 - amount;
				amount += amountb;
			}
			if (amount > 0.875)
			{
				amount = 0.875;
			}
			
			fluid.getLuminosity(stack);
			
			render.setRenderBounds(0.125, 0.125, 0.125, 0.875, amount, 0.875);
			render.setOverrideBlockTexture(icon);
			render.renderStandardBlock(block, x, y, z);
			render.clearOverrideBlockTexture();
		}
		
	}
	
	@Override
	public boolean shouldRender3DInInventory()
	{
		return false;
	}
	
	@Override
	public int getRenderId()
	{
		return EnumIDs.StorageBlock.getId();
	}
	
}
