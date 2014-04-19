package com.FMM.block;

import com.FMM.FMM;
import com.FMM.client.interfaces.GuiHandler;
import com.FMM.reference.BlockInfo;
import com.FMM.tileEntity.TileEntityMachine;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;

public class BlockMachine extends BlockContainer
{
	// METADATA FLAGS : 0bXYZW WHERE BITS X AND Y = TYPE, Z = LAST REDSTONE
	// SIGNAL AND W = DISABLED

	@SideOnly(Side.CLIENT)
	private IIcon topIcon;

	@SideOnly(Side.CLIENT)
	private IIcon botIcon;

	@SideOnly(Side.CLIENT)
	private IIcon[] sideIcons;

	@SideOnly(Side.CLIENT)
	private IIcon disabledIcon;

	public BlockMachine()
	{
		super(Material.iron);
		setCreativeTab(FMM.tabFMM);
		setHardness(2.0f);
		setStepSound(Block.soundTypeMetal);
		setBlockName(BlockInfo.MACHINE_NAME_UNLOCALIZED);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
		topIcon = ir.registerIcon(BlockInfo.MACHINE_TOP);
		botIcon = ir.registerIcon(BlockInfo.MACHINE_BOT);
		disabledIcon = ir.registerIcon(BlockInfo.MACHINE_DISABLED);
		sideIcons = new IIcon[BlockInfo.MACHINE_SIDES.length];
		for (int i = 0; i < sideIcons.length; i++)
			sideIcons[i] = ir.registerIcon(BlockInfo.MACHINE_SIDES[i]);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0)
			return botIcon;
		else if (side == 1)
			return isDisabled(meta) ? disabledIcon : topIcon;
		else
		{
			int type = meta >> 2;
			return sideIcons[type];
		}
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityMachine)
		{
			TileEntityMachine machine = (TileEntityMachine) te;
			if (!world.isRemote && !isDisabled(world.getBlockMetadata(x, y, z)))
			{
				spawnAnvil(world, machine, x, y + machine.heightSetting, z);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote && world.isBlockIndirectlyGettingPowered(x, y, z) && !getLast(meta))
		{
			TileEntity te = world.getTileEntity(x, y, z);
			if (te != null && te instanceof TileEntityMachine)
			{
				TileEntityMachine machine = (TileEntityMachine) te;
				if (!isDisabled(meta))
				{
					switch (meta >> 2)
					{
						case 0:
							spawnAnvil(world, machine, x, y + machine.heightSetting, z);
							break;
						case 1:
							for (int i = -1; i <= 1; i++)
							{
								spawnAnvil(world, machine, x + i, y + machine.heightSetting, z - 2);
								spawnAnvil(world, machine, x + i, y + machine.heightSetting, z + 2);
								spawnAnvil(world, machine, x - 2, y + machine.heightSetting, z + i);
								spawnAnvil(world, machine, x + 2, y + machine.heightSetting, z + i);
							}
							break;
						case 2:
							for(int i = 0; i < machine.customSetup.length; i++)
                            {
                                if (machine.customSetup[i])
                                {
                                    int dropX = x + i / 7 - 3;
                                    int dropZ = z + i % 7 - 3;
                                    spawnAnvil(world, machine, dropX, y + machine.heightSetting, dropZ);
                                }
                            }
                            break;
						case 3:
							for (int i = 1; i < 3; i++)
							{
								spawnAnvil(world, machine, x + i, y + machine.heightSetting, z);
								spawnAnvil(world, machine, x - i, y + machine.heightSetting, z);
								spawnAnvil(world, machine, x, y + machine.heightSetting, z + i);
								spawnAnvil(world, machine, x, y + machine.heightSetting, z - i);
							}
							break;
					}
				}
			}
			world.setBlockMetadataWithNotify(x, y, z, meta | (1 << 1), 3);
		} else if (!world.isRemote && !(world.isBlockIndirectlyGettingPowered(x, y, z)) && getLast(meta))
		{
			world.setBlockMetadataWithNotify(x, y, z, meta & ~(1 << 1), 3);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && !(player.isSneaking()))
		{
			FMLNetworkHandler.openGui(player, FMM.instance, GuiHandler.MACHINE, world, x, y, z);
		}
		return true;
	}

	@Override
    @SuppressWarnings("unchecked")
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < BlockInfo.MACHINE_SIDES.length; i++)
		{
			list.add(new ItemStack(item, 1, i << 2));
		}
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	private void spawnAnvil(World world, IInventory inv, int x, int y, int z)
	{
		if (world.isAirBlock(x, y, z))
		{
			for(int i = 0; i < inv.getSizeInventory(); i++)
			{
				ItemStack stack = inv.getStackInSlot(i);
				if(stack != null && stack.getItem() == Item.getItemFromBlock(Blocks.anvil))
				{
					inv.decrStackSize(i, 1);
					world.setBlock(x, y, z, Blocks.anvil);
					return;
				}
			}		
		}
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getCollisionBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 start, Vec3 end)
	{
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.collisionRayTrace(world, x, y, z, start, end);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		world.getBlock(x, y, z).setBlockBounds(0, 0, 0, 1, 1, 1);
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(0, 0, 0, 1, 1, 1);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return BlockInfo.machineRenderId;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityMachine();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof IInventory)
		{
			IInventory inventory = (IInventory) te;
			for (int i = 0; i < inventory.getSizeInventory(); i++)
			{
				ItemStack item = inventory.getStackInSlotOnClosing(i);
				if (item != null)
				{
					float spawnX = x + world.rand.nextFloat();
					float spawnY = y + world.rand.nextFloat();
					float spawnZ = z + world.rand.nextFloat();

					EntityItem dropped = new EntityItem(world, spawnX, spawnY, spawnZ, item);

					float motionFactor = 0.05f;
					dropped.motionX = (-0.5 + world.rand.nextFloat()) * motionFactor;
					dropped.motionY = (4 + world.rand.nextFloat()) * motionFactor;
					dropped.motionZ = (-0.5 + world.rand.nextFloat()) * motionFactor;

					world.spawnEntityInWorld(dropped);
				}
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	private boolean isDisabled(int meta)
	{
		return (meta & 1) == 1;
	}

	private boolean getLast(int meta)
	{
		return ((meta >> 1) & 1) == 1;
	}
}
