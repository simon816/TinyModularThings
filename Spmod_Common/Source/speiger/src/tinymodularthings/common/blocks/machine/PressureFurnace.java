package speiger.src.tinymodularthings.common.blocks.machine;

import java.util.LinkedList;
import java.util.Random;

import mods.railcraft.common.items.firestone.ItemFirestoneRefined;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.Icon;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeDirection;
import speiger.src.api.blocks.BlockStack;
import speiger.src.api.inventory.IAcceptor;
import speiger.src.api.inventory.IAcceptor.AcceptorType;
import speiger.src.api.inventory.InterfaceAcceptor;
import speiger.src.api.recipe.pressureFurnace.PressureRecipe;
import speiger.src.api.recipe.pressureFurnace.helper.PressureRecipeList;
import speiger.src.api.util.InventoryUtil;
import speiger.src.spmodapi.common.tile.TileFacing;
import speiger.src.spmodapi.common.util.BlockPosition;
import speiger.src.spmodapi.common.util.data.StructureStorage;
import speiger.src.tinymodularthings.TinyModularThings;
import speiger.src.tinymodularthings.client.gui.machine.PressureFurnaceGui;
import speiger.src.tinymodularthings.common.config.ModObjects.TinyBlocks;
import speiger.src.tinymodularthings.common.enums.EnumIDs;
import speiger.src.tinymodularthings.common.plugins.BC.BCRegistry;
import speiger.src.tinymodularthings.common.plugins.BC.triggers.TriggerFuel;
import speiger.src.tinymodularthings.common.plugins.BC.triggers.TriggerHasWork;
import buildcraft.BuildCraftCore;
import buildcraft.api.gates.IAction;
import buildcraft.api.gates.IActionReceptor;
import buildcraft.api.gates.IOverrideDefaultTriggers;
import buildcraft.api.gates.ITrigger;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PressureFurnace extends TileFacing implements IInventory,
		InterfaceAcceptor, IOverrideDefaultTriggers, IActionReceptor
{
	
	public boolean update = true;
	public boolean valid = false;
	public boolean firstTime = true;
	public final int MaxHeat = 1200;
	public final int MaxProgress = 300;
	public int heat = 0;
	public int fuel = 0;
	public int progress = 0;
	public PressureRecipe currentRecipe = null;
	public static boolean loadRecipes = false;
	public ItemStack[] inv = new ItemStack[7];
	public int interfaces = 0;
	public boolean paused = false;
	
	public boolean hasFuel()
	{
		int totalFuel = fuel;
		if (inv[0] != null)
		{
			totalFuel += (TileEntityFurnace.getItemBurnTime(inv[0]) * 4) * inv[0].stackSize;
		}
		if (totalFuel < 100000)
		{
			return true;
		}
		return false;
		
	}
	
	@Override
	public void onTick()
	{
		super.onTick();
		
		if (!loadRecipes)
		{
			loadRecipes = true;
			PressureRecipeList.getInstance().addFurnaceRecipes();
		}
		
		if (!worldObj.isRemote)
		{
			if ((firstTime && worldObj.getWorldTime() % 5 == 0) || (!firstTime && worldObj.getWorldTime() % 50 == 0))
			{
				updateStructure();
				updateBlock();
				if (firstTime)
				{
					firstTime = false;
				}
			}
			
			if (valid)
			{
				if (fuel < 100)
				{
					refuel();
				}
				if (fuel <= 0 && heat > 0)
				{
					if (worldObj.getWorldTime() % 5 == 0)
					{
						heat--;
					}
					
				}
				doWork();
			}
			else
			{
				if (worldObj.getWorldTime() % 50 == 0)
				{
					removeFromStorage();
				}
			}
			
			if (update)
			{
				onInventoryChanged();
				PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 30, worldObj.provider.dimensionId, getDescriptionPacket());
				updateBlock();
				update = false;
			}
			
		}
		
	}
	
	public void refuel()
	{
		ItemStack fuelItem = inv[0];
		if (fuelItem != null)
		{
			try
			{
				if (fuelItem.getItem() instanceof ItemFirestoneRefined)
				{
					ItemFirestoneRefined refined = (ItemFirestoneRefined) fuelItem.getItem();
					if (refined.getHeatValue(fuelItem) > 0)
					{
						fuel += 20;
						inv[0].stackSize--;
						if (fuelItem.stackSize <= 0)
						{
							inv[0] = fuelItem.getItem().getContainerItemStack(fuelItem);
						}
						heat = Math.min(heat + 100, MaxHeat);
					}
				}
				else
				{
					int gainingFuel = TileEntityFurnace.getItemBurnTime(fuelItem) * 4;
					if (gainingFuel > 0)
					{
						fuel += gainingFuel;
						fuelItem.stackSize--;
						if (fuelItem.stackSize <= 0)
						{
							inv[0] = null;
						}
					}
				}
			}
			catch (Exception e)
			{
				int gainingFuel = TileEntityFurnace.getItemBurnTime(fuelItem) * 4;
				if (gainingFuel > 0)
				{
					fuel += gainingFuel;
					fuelItem.stackSize--;
					if (fuelItem.stackSize <= 0)
					{
						inv[0] = null;
					}
				}
			}
		}
	}
	
	public void doWork()
	{
		if (fuel > 0)
		{
			fuel--;
			
			if (heat < MaxHeat)
			{
				fuel -= 35;
				if (worldObj.getWorldTime() % 5 == 0)
				{
					heat += 1;
				}
			}
			
			if (heat >= MaxHeat)
			{
				if (currentRecipe != null && !PressureRecipeList.getInstance().hasRecipe(inv[1], inv[2], inv[3]))
				{
					currentRecipe = null;
				}
				
				if ((currentRecipe == null && PressureRecipeList.getInstance().hasRecipe(inv[1], inv[2], inv[3])) || (currentRecipe != null && currentRecipe != PressureRecipeList.getInstance().getRecipeOutput(inv[1], inv[2], inv[3])))
				{
					currentRecipe = PressureRecipeList.getInstance().getRecipeOutput(inv[1], inv[2], inv[3]);
				}
				
				if (paused)
				{
					paused = false;
					return;
				}
				
				if (currentRecipe != null && (inv[4] == null || (inv[4] != null && inv[4].isItemEqual(currentRecipe.getOutput()) && inv[4].stackSize + currentRecipe.getOutput().stackSize <= inv[4].getMaxStackSize())))
				{
					if (currentRecipe.isStackSizeSensitive())
					{
						boolean first = false;
						boolean second = false;
						boolean combiner = false;
						
						if (currentRecipe.hasFirstInput())
						{
							if (currentRecipe.getFirstInput().stackSize <= inv[1].stackSize)
							{
								first = true;
							}
						}
						else
						{
							first = true;
						}
						
						if (currentRecipe.hasSecondInput())
						{
							if (currentRecipe.getSecondInput().stackSize <= inv[2].stackSize)
							{
								second = true;
							}
						}
						else
						{
							second = true;
						}
						
						if (currentRecipe.hasCombiner())
						{
							if (currentRecipe.getCombiner().stackSize <= inv[3].stackSize)
							{
								combiner = true;
							}
						}
						else
						{
							combiner = true;
						}
						
						if (combiner && first && second)
						{
							progress += 1;
							fuel--;
							if (progress >= MaxProgress)
							{
								progress = 0;
								ItemStack output = currentRecipe.getOutput().copy();
								
								inv[1] = currentRecipe.consumeInput(inv[1]);
								inv[2] = currentRecipe.consumeSecondInput(inv[2]);
								if (currentRecipe.useCombiner())
								{
									inv[3] = currentRecipe.consumeCombiner(inv[3]);
								}
								
								if (inv[4] == null)
								{
									inv[4] = output.copy();
									return;
								}
								if (inv[4] != null && inv[4].isItemEqual(output))
								{
									inv[4].stackSize += output.stackSize;
								}
							}
						}
					}
					else
					{
						progress += 1;
						fuel--;
						if (progress > MaxProgress)
						{
							progress = 0;
							int maxConsume = 5;
							ItemStack output = currentRecipe.getOutput().copy();
							if (inv[1] != null)
							{
								maxConsume = Math.min(maxConsume, inv[1].stackSize);
							}
							if (inv[2] != null)
							{
								maxConsume = Math.min(maxConsume, inv[2].stackSize);
							}
							if (inv[3] != null)
							{
								maxConsume = Math.min(maxConsume, inv[3].stackSize);
							}
							if (inv[4] != null)
							{
								maxConsume = Math.min(maxConsume, 64 - inv[4].stackSize);
							}
							
							output.stackSize *= maxConsume;
							
							inv[1] = currentRecipe.consumeItem(inv[1], maxConsume);
							inv[2] = currentRecipe.consumeItem(inv[2], maxConsume);
							if (currentRecipe.useCombiner())
							{
								inv[3] = currentRecipe.consumeItem(inv[3], maxConsume);
							}
							if (inv[4] == null)
							{
								inv[4] = output.copy();
								return;
							}
							if (inv[4] != null && inv[4].isItemEqual(output))
							{
								inv[4].stackSize += output.stackSize;
							}
						}
					}
				}
				else
				{
					progress = 0;
				}
				
			}
			
		}
	}
	
	public void updateStructure()
	{
		ForgeDirection oppo = ForgeDirection.getOrientation(getFacing()).getOpposite();
		BlockPosition pos = new BlockPosition(worldObj, xCoord + oppo.offsetX, yCoord - 1, zCoord + oppo.offsetZ, oppo.getOpposite());
		int match = 0;
		int inter = 0;
		for (int x = -1; x < 2; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				for (int z = -1; z < 2; z++)
				{
					int posX = pos.getXCoord() + x;
					int posY = pos.getYCoord() + y;
					int posZ = pos.getZCoord() + z;
					
					BlockStack cuBlock = getBlockFromCoords(x, y, z);
					
					BlockStack realBlock = new BlockStack(worldObj, posX, posY, posZ);
					
					BlockStack item = new BlockStack(TinyBlocks.transportBlock, 1);
					
					if (realBlock.getBlock() != null && realBlock.getBlock().isAirBlock(worldObj, posX, posY, posZ))
					{
						realBlock = new BlockStack();
					}
					
					if (realBlock.match(cuBlock) || realBlock.match(item))
					{
						if (realBlock.match(item))
						{
							inter++;
						}
						
						match++;
					}
					else if (realBlock.getBlock() != null && cuBlock.getBlock() != null && cuBlock.getBlockID() == Block.cobblestone.blockID)
					{
						String name = realBlock.getHiddenName();
						boolean ore = name.contains("Ore") || name.contains("ore");
						if ((!ore && (name.contains("cobble") || name.contains("sandStone") || name.contains("brick") || name.contains("stone"))))
						{
							if (realBlock.getBlock().isBlockNormalCube(worldObj, posX, posY, posZ))
							{
								match++;
							}
							else if (!realBlock.getBlock().isBlockNormalCube(worldObj, posX, posY, posZ) && name.contains("railcraft") && realBlock.getBlock().isBlockSolidOnSide(worldObj, posX, posY, posZ, ForgeDirection.WEST))
							{
								match++;
							}
						}
					}
				}
			}
		}
		
		if (inter > 3)
		{
			match = match - inter;
		}
		
		boolean save = valid;
		
		if (match == 27)
		{
			addToStorage();
			valid = true;
		}
		else
		{
			valid = false;
			int rand = new Random().nextInt(25);
			if (rand == 0)
			{
				heat = 0;
			}
			if (save != valid)
			{
				removeFromStorage();
			}
		}
		
		if (save != valid)
		{
			worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
		}
		
		update = true;
	}
	
	public BlockStack getBlockFromCoords(int x, int y, int z)
	{
		ForgeDirection face = ForgeDirection.getOrientation(getFacing());
		if (x == 0 && y == 1 && z == 0)
		{
			return new BlockStack();
		}
		if (x == 0 + face.offsetX && y == 1 && z == 0 + face.offsetZ)
		{
			return new BlockStack(getBlockType(), getBlockMetadata());
		}
		return new BlockStack(Block.cobblestone);
	}
	
	public void addToStorage()
	{
		ForgeDirection oppo = ForgeDirection.getOrientation(getFacing()).getOpposite();
		BlockPosition pos = new BlockPosition(worldObj, xCoord + oppo.offsetX, yCoord - 1, zCoord + oppo.offsetZ, oppo.getOpposite());
		
		BlockPosition here = new BlockPosition(worldObj, xCoord, yCoord, zCoord);
		
		int matches = 0;
		
		for (int x = -1; x < 2; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				for (int z = -1; z < 2; z++)
				{
					if (y == 1 && x == 0 && z == 0)
					{
						continue;
					}
					
					int posX = pos.getXCoord() + x;
					int posY = pos.getYCoord() + y;
					int posZ = pos.getZCoord() + z;
					
					BlockPosition target = new BlockPosition(worldObj, posX, posY, posZ);
					
					boolean mine = StructureStorage.instance.isRegisteredToMe(here, target);
					if (StructureStorage.instance.isRegistered(target) && mine)
					{
						matches++;
					}
					else if (!StructureStorage.instance.isRegistered(target))
					{
						matches++;
					}
				}
			}
		}
		
		if (matches != 26)
		{
			return;
		}
		
		for (int x = -1; x < 2; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				for (int z = -1; z < 2; z++)
				{
					
					if (y == 1 && x == 0 && z == 0)
					{
						continue;
					}
					
					int posX = pos.getXCoord() + x;
					int posY = pos.getYCoord() + y;
					int posZ = pos.getZCoord() + z;
					
					BlockPosition target = new BlockPosition(worldObj, posX, posY, posZ);
					
					StructureStorage.instance.isRegisteredToMe(here, target);
					
					if (!StructureStorage.instance.isRegistered(target))
					{
						StructureStorage.instance.registerStorage(here, target);
					}
					
				}
			}
		}
	}
	
	public void removeFromStorage()
	{
		BlockPosition here = new BlockPosition(worldObj, xCoord, yCoord, zCoord);
		
		ForgeDirection oppo = ForgeDirection.getOrientation(getFacing()).getOpposite();
		BlockPosition pos = new BlockPosition(worldObj, xCoord + oppo.offsetX, yCoord - 1, zCoord + oppo.offsetZ, oppo.getOpposite());
		for (int x = -1; x < 2; x++)
		{
			for (int y = 0; y < 3; y++)
			{
				for (int z = -1; z < 2; z++)
				{
					int posX = pos.getXCoord() + x;
					int posY = pos.getYCoord() + y;
					int posZ = pos.getZCoord() + z;
					
					BlockPosition end = new BlockPosition(worldObj, posX, posY, posZ);
					
					if (StructureStorage.instance.isRegisteredToMe(here, end))
					{
						FMLLog.getLogger().info("Test1");
						StructureStorage.instance.removePosition(end);
						if (end.doesBlockExsist() && end.hasTileEntity() && end.getTileEntity() instanceof IAcceptor)
						{
							FMLLog.getLogger().info("Test2");
							IAcceptor acept = (IAcceptor) end.getTileEntity();
							acept.targetLeave(this);
						}
					}
					
				}
			}
		}
	}
	
	public int getRecipeModeFromInventory(ItemStack[] par1)
	{
		if (heat < MaxHeat)
		{
			return 0;
		}
		if (par1[3] != null)
		{
			if (PressureRecipeList.getInstance().hasRecipe(par1[1], par1[2], par1[3]))
			{
				return 2;
			}
		}
		else
		{
			if (PressureRecipeList.getInstance().hasRecipe(par1[1], par1[2], par1[3]))
			{
				return 1;
			}
		}
		
		return 0;
	}
	
	@Override
	public boolean onActivated(EntityPlayer par1)
	{
		if (valid)
		{
			par1.openGui(TinyModularThings.instance, EnumIDs.ADVTiles.getId(), worldObj, xCoord, yCoord, zCoord);
			return true;
		}
		return false;
	}
	
	@Override
	public void onPlaced(int facing)
	{
		setFacing(facing);
	}
	
	@Override
	public Icon getIconFromSideAndMetadata(int side, int renderPass)
	{
		if (side == getFacing())
		{
			if (BCRegistry.overrideVanilla)
			{
				if (valid)
				{
					return Block.blocksList[62].getIcon(3, 2);
				}
				else
				{
					return Block.blocksList[61].getIcon(3, 2);
				}
			}
			if (valid)
			{
				return Block.blocksList[62].getIcon(2, 2);
			}
			else
			{
				return Block.blocksList[61].getIcon(2, 2);
			}
		}
		return Block.cobblestone.getBlockTextureFromSide(0);
	}
	
	@Override
	public boolean hasContainer()
	{
		return valid;
	}
	
	@Override
	public Container getInventory(InventoryPlayer par1)
	{
		return new PressureFurnaceInventory(par1, this);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGui(InventoryPlayer par1)
	{
		return new PressureFurnaceGui(par1, this);
	}
	
	@Override
	public int getSizeInventory()
	{
		return inv.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int var1)
	{
		return inv[var1];
	}
	
	@Override
	public ItemStack decrStackSize(int var1, int var2)
	{
		
		if (inv[var1] != null)
		{
			ItemStack var3;
			
			if (inv[var1].stackSize <= var2)
			{
				var3 = inv[var1];
				inv[var1] = null;
				return var3;
			}
			else
			{
				var3 = inv[var1].splitStack(var2);
				
				if (inv[var1].stackSize == 0)
				{
					inv[var1] = null;
				}
				
				return var3;
			}
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int var1)
	{
		if (inv[var1] != null)
		{
			ItemStack var2 = inv[var1];
			inv[var1] = null;
			return var2;
		}
		else
		{
			return null;
		}
	}
	
	@Override
	public void setInventorySlotContents(int var1, ItemStack var2)
	{
		inv[var1] = var2;
		
		if (var2 != null && var2.stackSize > getInventoryStackLimit())
		{
			var2.stackSize = getInventoryStackLimit();
		}
		
	}
	
	@Override
	public String getInvName()
	{
		return "Updating Chest";
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer var1)
	{
		return valid;
	}
	
	@Override
	public void openChest()
	{
	}
	
	@Override
	public void closeChest()
	{
	}
	
	@Override
	public boolean isInvNameLocalized()
	{
		return false;
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		NBTTagList var2 = new NBTTagList();
		
		for (int var3 = 0; var3 < inv.length; ++var3)
		{
			if (inv[var3] != null)
			{
				NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) var3);
				inv[var3].writeToNBT(var4);
				var2.appendTag(var4);
			}
		}
	
		nbt.setTag("Items", var2);
		nbt.setBoolean("Valid", valid);
		nbt.setInteger("Fuel", fuel);
		nbt.setInteger("Heat", heat);
		nbt.setInteger("Progress", progress);
		nbt.setBoolean("Paused", paused);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList var2 = nbt.getTagList("Items");
		inv = new ItemStack[getSizeInventory()];
		
		for (int var3 = 0; var3 < var2.tagCount(); ++var3)
		{
			NBTTagCompound var4 = (NBTTagCompound) var2.tagAt(var3);
			byte var5 = var4.getByte("Slot");
			
			if (var5 >= 0 && var5 < inv.length)
			{
				inv[var5] = ItemStack.loadItemStackFromNBT(var4);
			}
		}
		
		valid = nbt.getBoolean("Valid");
		fuel = nbt.getInteger("Fuel");
		heat = nbt.getInteger("Heat");
		progress = nbt.getInteger("Progress");
		paused = nbt.getBoolean("Paused");
	}
	
	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound var1 = new NBTTagCompound();
		writeToNBT(var1);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, var1);
	}
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		readFromNBT(pkt.data);
	}
	
	@Override
	public boolean isSixSidedFacing()
	{
		return false;
	}
	
	@Override
	public void onReciveGuiInfo(int key, int val)
	{
		if (key == 0)
		{
			valid = val == 1 ? true : false;
		}
		if (key == 1)
		{
			fuel = val;
		}
		if (key == 2)
		{
			heat = val;
		}
		if (key == 3)
		{
			progress = val;
		}
	}
	
	@Override
	public void onSendingGuiInfo(Container par1, ICrafting par2)
	{
		par2.sendProgressBarUpdate(par1, 0, valid ? 1 : 0);
		par2.sendProgressBarUpdate(par1, 1, fuel);
		par2.sendProgressBarUpdate(par1, 2, heat);
		par2.sendProgressBarUpdate(par1, 3, progress);
	}
	
	@Override
	public void onBreaking()
	{
		removeFromStorage();
		InventoryUtil.dropInventory(worldObj, xCoord, yCoord, zCoord);
	}
	
	@Override
	public void onClientTick()
	{
		if (valid && heat > 0)
		{
			int l = getFacing();
			float f = xCoord + 0.5F;
			float f1 = yCoord + 0.0F + worldObj.rand.nextFloat() * 6.0F / 16.0F;
			float f2 = zCoord + 0.5F;
			float f3 = 0.52F;
			float f4 = worldObj.rand.nextFloat() * 0.6F - 0.3F;
			
			if (l == 4)
			{
				worldObj.spawnParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				worldObj.spawnParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 5)
			{
				worldObj.spawnParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
				worldObj.spawnParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 2)
			{
				worldObj.spawnParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
				worldObj.spawnParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
			}
			else if (l == 3)
			{
				worldObj.spawnParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
				worldObj.spawnParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
		}
	}
	
	@Override
	public boolean acceptItems(IAcceptor par1)
	{
		if (par1.getBlock() == null)
		{
			return false;
		}
		String name = par1.getBlock().getHiddenName();
		boolean ore = name.contains("Ore") || name.contains("ore");
		if ((!ore && (name.contains("cobble") || name.contains("sandStone") || name.contains("brick") || name.contains("stone"))))
		{
			par1.getType();
			return par1.getType() == AcceptorType.Items;
		}
		return false;
	}
	
	@Override
	public boolean acceptFluids(IAcceptor par1)
	{
		return false;
	}
	
	@Override
	public boolean acceptEnergy(IAcceptor par1)
	{
		return false;
	}
	
	@Override
	public boolean addAcceptor(IAcceptor par1)
	{
		if (interfaces < 3)
		{
			interfaces++;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean removeAcceptor(IAcceptor par1)
	{
		if(interfaces > 0)
		{
			interfaces--;
			return true;
		}
		return false;
	}
	
	@Override
	public LinkedList<ITrigger> getTriggers()
	{
		LinkedList<ITrigger> trigger = new LinkedList<ITrigger>();
		trigger.add(new TriggerFuel());
		trigger.add(new TriggerHasWork(true));
		trigger.add(new TriggerHasWork(false));
		return trigger;
	}
	
	@Override
	public void actionActivated(IAction action)
	{
		try
		{
			if (action == BuildCraftCore.actionOn)
			{
				paused = false;
			}
			else if (action == BuildCraftCore.actionOff)
			{
				paused = true;
			}
		}
		catch (Exception e)
		{
			TinyModularThings.log.print("Error with BC Gate Actions");
		}
	}

	
}
