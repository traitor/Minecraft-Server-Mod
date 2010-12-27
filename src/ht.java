public class ht extends gm
{
	private la a;

	public ht(int paramInt, la paramla)
	{
		super(paramInt);
		this.a = paramla;
		this.aY = 64;
		this.aX = 1;
	}

	public boolean a(il paramil, gq paramgq, ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
		if (paramInt4 != 1) return false;
		paramInt2++;
		gv localgv;
		if (this.a == la.c) localgv = gv.aE; else {
			localgv = gv.aL;
		}
		if (!localgv.a(paramff, paramInt1, paramInt2, paramInt3)) return false;

		// hMod: Doors onItemUse
		Block blockClicked = new Block(paramff.a(paramInt1, paramInt2, paramInt3), paramInt1, paramInt2, paramInt3);
		blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
		Block blockPlaced = new Block(paramff.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);	

		// Call the hook
		if (paramgq instanceof fi) {
			Player player = ((fi) paramgq).getPlayer();
			if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramil))) {
				return false;
			}
		}
		
		int i = ic.b((paramgq.v + 180.0F) * 4.0F / 360.0F - 0.5D) & 0x3;

		int j = 0;
		int k = 0;
		if (i == 0) k = 1;
		if (i == 1) j = -1;
		if (i == 2) k = -1;
		if (i == 3) j = 1;

		int m = (paramff.d(paramInt1 - j, paramInt2, paramInt3 - k) ? 1 : 0) + (paramff.d(paramInt1 - j, paramInt2 + 1, paramInt3 - k) ? 1 : 0);
		int n = (paramff.d(paramInt1 + j, paramInt2, paramInt3 + k) ? 1 : 0) + (paramff.d(paramInt1 + j, paramInt2 + 1, paramInt3 + k) ? 1 : 0);

		int i1 = (paramff.a(paramInt1 - j, paramInt2, paramInt3 - k) == localgv.bh) || (paramff.a(paramInt1 - j, paramInt2 + 1, paramInt3 - k) == localgv.bh) ? 1 : 0;
		int i2 = (paramff.a(paramInt1 + j, paramInt2, paramInt3 + k) == localgv.bh) || (paramff.a(paramInt1 + j, paramInt2 + 1, paramInt3 + k) == localgv.bh) ? 1 : 0;

		int i3 = 0;
		if ((i1 != 0) && (i2 == 0)) i3 = 1;
		else if (n > m) i3 = 1;

		if (i3 != 0) {
			i = i - 1 & 0x3;
			i += 4;
		}

		paramff.d(paramInt1, paramInt2, paramInt3, localgv.bh);
		paramff.b(paramInt1, paramInt2, paramInt3, i);

		paramff.d(paramInt1, paramInt2 + 1, paramInt3, localgv.bh);
		paramff.b(paramInt1, paramInt2 + 1, paramInt3, i + 8);

		paramil.a -= 1;
		return true;
	}
}