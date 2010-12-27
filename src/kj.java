
public class kj extends gm
{
	public kj(int paramInt1, int paramInt2)
	{
		super(paramInt1);
		this.aX = 1;
		this.aY = (32 << paramInt2);
	}

	public boolean a(il paramik, gq paramgp, ff paramff, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
	{
		int i = paramff.a(paramInt1, paramInt2, paramInt3);
		la localla = paramff.c(paramInt1, paramInt2 + 1, paramInt3);

		if (((!localla.a()) && (i == gv.u.bh)) || (i == gv.v.bh)) {
			// hMod: Hoes
			Block blockClicked = new Block(i, paramInt1, paramInt2, paramInt3);
			blockClicked.setFaceClicked(Block.Face.fromId(paramInt4));
			Block blockPlaced = new Block(paramff.a(paramInt1, paramInt2 + 1, paramInt3), paramInt1, paramInt2 + 1, paramInt3);	

			// Call the hook
			if (paramgp instanceof fi) {
				Player player = ((fi) paramgp).getPlayer();
				if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, player, blockPlaced, blockClicked, new Item(paramik))) {
					return false;
				}
			}
				
			gv localgv = gv.aA;
			paramff.a(paramInt1 + 0.5F, paramInt2 + 0.5F, paramInt3 + 0.5F, localgv.bq.c(), (localgv.bq.a() + 1.0F) / 2.0F, localgv.bq.b() * 0.8F);

			if (paramff.z) return true;
			paramff.d(paramInt1, paramInt2, paramInt3, localgv.bh);
			paramik.b(1);

			if ((paramff.l.nextInt(8) == 0) && (i == gv.u.bh)) {
				int j = 1;
				for (int k = 0; k < j; k++) {
					float f1 = 0.7F;
					float f2 = paramff.l.nextFloat() * f1 + (1.0F - f1) * 0.5F;
					float f3 = 1.2F;
					float f4 = paramff.l.nextFloat() * f1 + (1.0F - f1) * 0.5F;
					hf localhf = new hf(paramff, paramInt1 + f2, paramInt2 + f3, paramInt3 + f4, new il(gm.Q));
					localhf.c = 10;
					paramff.a(localhf);
				}
			}

			return true;
		}

		return false;
	}
}