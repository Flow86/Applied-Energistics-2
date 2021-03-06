package appeng.parts.reporting;

import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import appeng.client.texture.CableBusTextures;
import appeng.core.sync.GuiBridge;
import appeng.tile.inventory.AppEngInternalInventory;
import appeng.tile.inventory.IAEAppEngInventory;
import appeng.tile.inventory.InvOperation;

public class PartCraftingTerminal extends PartTerminal implements IAEAppEngInventory
{

	AppEngInternalInventory craftingGrid = new AppEngInternalInventory( this, 9 );

	@Override
	public void writeToNBT(NBTTagCompound data)
	{
		super.writeToNBT( data );
		craftingGrid.writeToNBT( data, "craftingGrid" );
	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT( data );
		craftingGrid.readFromNBT( data, "craftingGrid" );
	}

	@Override
	public void getDrops(List<ItemStack> drops, boolean wrenched)
	{
		super.getDrops(drops, wrenched);
		
		for (ItemStack is : craftingGrid)
			if ( is != null )
				drops.add( is );
	}

	public PartCraftingTerminal(ItemStack is) {
		super( PartCraftingTerminal.class, is );
		frontBright = CableBusTextures.PartCraftingTerm_Bright;
		frontColored = CableBusTextures.PartCraftingTerm_Colored;
		frontDark = CableBusTextures.PartCraftingTerm_Dark;
		// frontSolid = CableBusTextures.PartCraftingTerm_Solid;
	}

	public GuiBridge getGui()
	{
		return GuiBridge.GUI_CRAFTING_TERMINAL;
	}

	@Override
	public void onChangeInventory(IInventory inv, int slot, InvOperation mc, ItemStack removedStack, ItemStack newStack)
	{
		host.markForSave();
	}

	@Override
	public IInventory getInventoryByName(String name)
	{
		if ( name.equals( "crafting" ) )
			return craftingGrid;
		return super.getInventoryByName( name );
	}

}
