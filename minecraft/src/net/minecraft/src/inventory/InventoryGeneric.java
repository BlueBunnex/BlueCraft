package net.minecraft.src.inventory;

import net.minecraft.src.item.ItemStack;

public class InventoryGeneric implements IInventory {
	
	private final ItemStack[] slots;
	private final String name;

	public InventoryGeneric(int size, String name) {
		this.slots = new ItemStack[size];
		this.name = name;
	}

	public int getSizeInventory() {
		return slots.length;
	}

	public ItemStack getStackInSlot(int i) {
		
		if (i >= this.getSizeInventory())
			return null;
		
		return slots[i];
	}

	public ItemStack decrStackSize(int i, int amount) {
		
		if (slots[i] == null)
			return null;
		
		slots[i].stackSize -= amount;
		
		int itemID = slots[i].itemID;
		
		if (slots[i].stackSize == 0)
			slots[i] = null;
		
		return new ItemStack(itemID, amount);
	}

	public void setInventorySlotContents(int i, ItemStack stack) {
		
		if (i >= this.getSizeInventory())
			return;
		
		slots[i] = stack;
	}

	public String getInvName() {
		return name;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void onInventoryChanged() {}

}
