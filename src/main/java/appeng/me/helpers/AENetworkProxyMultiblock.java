package appeng.me.helpers;

import java.util.Iterator;

import net.minecraft.item.ItemStack;
import appeng.api.networking.IGridMultiblock;
import appeng.api.networking.IGridNode;
import appeng.me.cluster.IAECluster;
import appeng.me.cluster.IAEMultiBlock;
import appeng.util.iterators.ChainedIterator;
import appeng.util.iterators.ProxyNodeIterator;

public class AENetworkProxyMultiblock extends AENetworkProxy implements IGridMultiblock
{

	IAECluster getCluster()
	{
		return ((IAEMultiBlock) getMachine()).getCluster();
	}

	public AENetworkProxyMultiblock(IGridProxyable te, String nbtName, ItemStack itemStack, boolean inWorld) {
		super( te, nbtName, itemStack, inWorld );
	}

	@Override
	public Iterator<IGridNode> getMultiblockNodes()
	{
		if ( getCluster() == null )
			return new ChainedIterator<IGridNode>();

		return new ProxyNodeIterator( getCluster().getTiles() );
	}
}
