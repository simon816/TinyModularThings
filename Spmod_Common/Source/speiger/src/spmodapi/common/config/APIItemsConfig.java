package speiger.src.spmodapi.common.config;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import speiger.src.api.items.DisplayStack;
import speiger.src.api.items.plates.PlateManager;
import speiger.src.api.language.LanguageRegister;
import speiger.src.spmodapi.SpmodAPI;
import speiger.src.spmodapi.common.config.ModObjects.APIItems;
import speiger.src.spmodapi.common.fluids.hemp.ItemHempResin;
import speiger.src.spmodapi.common.items.armor.HempArmor;
import speiger.src.spmodapi.common.items.crafting.ItemBlueDye;
import speiger.src.spmodapi.common.items.crafting.ItemColorCard;
import speiger.src.spmodapi.common.items.crafting.ItemGear;
import speiger.src.spmodapi.common.items.exp.ExpBottle;
import speiger.src.spmodapi.common.items.hemp.ItemCompressedHemp;
import speiger.src.spmodapi.common.items.hemp.ItemHemp;
import speiger.src.spmodapi.common.items.hemp.ItemHempBucket;
import speiger.src.spmodapi.common.items.hemp.ItemHempSeed;
import speiger.src.spmodapi.common.items.hemp.ItemMultiPlate;
import speiger.src.spmodapi.common.lib.SpmodAPILib;
import speiger.src.spmodapi.common.util.proxy.RegisterProxy;

public class APIItemsConfig
{
	private static SpmodConfig config;
	private static APIItems items;
	
	public static void loadItems()
	{
		Item.enderPearl.setMaxStackSize(64);
		Item.egg.setMaxStackSize(64);
		
		items.hempSeed = new ItemHempSeed(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.hempSeed);
		config.itemIDs.updateToNextID();
		
		items.hemp = new ItemHemp(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.hemp);
		config.itemIDs.updateToNextID();
		
		items.hempBoots = new HempArmor(config.itemIDs.getCurrentID(), 3);
		RegisterProxy.RegisterItem(items.hempBoots);
		config.itemIDs.updateToNextID();
		
		items.hempLeggings = new HempArmor(config.itemIDs.getCurrentID(), 2);
		RegisterProxy.RegisterItem(items.hempLeggings);
		config.itemIDs.updateToNextID();
		
		items.hempChestPlate = new HempArmor(config.itemIDs.getCurrentID(), 1);
		RegisterProxy.RegisterItem(items.hempChestPlate);
		config.itemIDs.updateToNextID();
		
		items.hempHelmet = new HempArmor(config.itemIDs.getCurrentID(), 0);
		RegisterProxy.RegisterItem(items.hempHelmet);
		config.itemIDs.updateToNextID();
		
		items.compressedHemp = new ItemCompressedHemp(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.compressedHemp);
		config.itemIDs.updateToNextID();
		
		items.hempResin = new ItemHempResin(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.hempResin);
		config.itemIDs.updateToNextID();
		
		items.gears = new ItemGear(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.gears);
		config.itemIDs.updateToNextID();
		
		items.blueDye = new ItemBlueDye(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.blueDye);
		config.itemIDs.updateToNextID();
		
		items.multiPlate = new ItemMultiPlate(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.multiPlate);
		config.itemIDs.updateToNextID();
		
		items.colorCard = new ItemColorCard(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.colorCard);
		config.itemIDs.updateToNextID();
		
		items.hempResinBucket = new ItemHempBucket(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.hempResinBucket);
		config.itemIDs.updateToNextID();
		
		items.expBottles = new ExpBottle(config.itemIDs.getCurrentID());
		RegisterProxy.RegisterItem(items.expBottles);
		config.itemIDs.updateToNextID();
		
		initHempPlates();
	}
	
	private static void initHempPlates()
	{
		ResourceLocation[] models = new ResourceLocation[]{
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/HanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/BlackHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/RedHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/GreenHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/BrownHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/BlueHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/PurpleHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/CyanHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/SilverHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/GrayHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/PinkHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/LimeHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/YellowHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/LightBlueHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/MagentaHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/OrangeHanfSign.png"),
				new ResourceLocation(SpmodAPILib.ModID.toLowerCase()+":textures/models/plates/WhiteHanfSign.png")
		};
		String[] identifiers = new String[]{
			"hemp.Sign",
			"hemp.Sign.black",
			"hemp.Sign.red",
			"hemp.Sign.green",
			"hemp.Sign.brown",
			"hemp.Sign.blue",
			"hemp.Sign.purple",
			"hemp.Sign.cyan",
			"hemp.Sign.lightGray",
			"hemp.Sign.gray",
			"hemp.Sign.pink",
			"hemp.Sign.lime",
			"hemp.Sign.yellow",
			"hemp.Sign.lightBlue",
			"hemp.Sign.magenta",
			"hemp.Sign.orange",
			"hemp.Sign.white",
		};
		String[] textures = new String[]{
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSign",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignBlack",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignRed",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignGreen",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignBrown",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignBlue",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignPurple",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignCyan",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignSilver",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignGray",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignPink",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignLime",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignYellow",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignLightBlue",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignMagenta",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignOrange",
				SpmodAPILib.ModID.toLowerCase()+":plates/HanfSignWhite"
		};
		items.hempPlates = new ItemStack[models.length];
		for(int i = 0;i<items.hempPlates.length;i++)
		{
			items.hempPlates[i] = PlateManager.plates.registerPlate(models[i], textures[i], identifiers[i], getDisplayName(identifiers[i])).getItemStack();
		}
	}
	
	static String getDisplayName(String par1)
	{
		return LanguageRegister.getLanguageName(new DisplayStack(null, 0), par1, SpmodAPI.instance);
	}
}
