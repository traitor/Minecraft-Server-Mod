import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class OWorldServer extends OWorld {
    public OChunkProviderServer A;
    public boolean              B = false;
    public boolean              C;
    private MinecraftServer     D;
    private OMCHashTable        E = new OMCHashTable();

    public OWorldServer(MinecraftServer paramMinecraftServer, File paramFile, String paramString, int paramInt) {
        super(paramFile, paramString, new Random().nextLong(), OWorldProvider.a(paramInt));
        D = paramMinecraftServer;
    }

    public void f() {
        super.f();
    }

    public void a(OEntity paramOEntity, boolean paramBoolean) {
        if ((!D.m) && (((paramOEntity instanceof OEntityAnimals)) || ((paramOEntity instanceof OEntityWaterMob)))) {
            paramOEntity.q();
        }
        if ((paramOEntity.j == null) || (!(paramOEntity.j instanceof OEntityPlayer)))
            super.a(paramOEntity, paramBoolean);
    }

    public void b(OEntity paramOEntity, boolean paramBoolean) {
        super.a(paramOEntity, paramBoolean);
    }

    protected OIChunkProvider a(File paramFile) {
        A = new OChunkProviderServer(this, q.a(paramFile), q.c());
        return A;
    }

    public List<OTileEntity> d(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
        ArrayList<OTileEntity> localArrayList = new ArrayList<OTileEntity>();
        for (int i = 0; i < c.size(); i++) {
            OTileEntity localOTileEntity = (OTileEntity) c.get(i);
            if ((localOTileEntity.b >= paramInt1) && (localOTileEntity.c >= paramInt2) && (localOTileEntity.d >= paramInt3) && (localOTileEntity.b < paramInt4) && (localOTileEntity.c < paramInt5) && (localOTileEntity.d < paramInt6)) {
                localArrayList.add(localOTileEntity);
            }
        }
        return localArrayList;
    }

    public boolean a(OEntityPlayer paramOEntityPlayer, int paramInt1, int paramInt2, int paramInt3) {
        int i = (int) OMathHelper.e(paramInt1 - m);
        int j = (int) OMathHelper.e(paramInt3 - o);
        if (i > j)
            j = i;
        return (j > 16) || (D.f.g(paramOEntityPlayer.aw));
    }

    @Override
    public boolean a(OEntity entity) {
        if(entity instanceof OEntityLiving && !(entity instanceof OEntityPlayer))
            // hMod: allow entities to spawn
            if ((Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob((OEntityLiving)entity))))
                return false;
        
        return super.a(entity);
    }

    protected void b(OEntity paramOEntity) {
        super.b(paramOEntity);
        E.a(paramOEntity.g, paramOEntity);
    }

    protected void c(OEntity paramOEntity) {
        super.c(paramOEntity);
        E.d(paramOEntity.g);
    }

    public OEntity a(int paramInt) {
        return (OEntity) E.a(paramInt);
    }

    public void a(OEntity paramOEntity, byte paramByte) {
        OPacket38 localOPacket38 = new OPacket38(paramOEntity.g, paramByte);
        D.k.b(paramOEntity, localOPacket38);
    }

    public OExplosion a(OEntity paramOEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, boolean paramBoolean) {
        OExplosion localOExplosion = super.a(paramOEntity, paramDouble1, paramDouble2, paramDouble3, paramFloat, paramBoolean);
        D.f.a(paramDouble1, paramDouble2, paramDouble3, 64.0D, new OPacket60(paramDouble1, paramDouble2, paramDouble3, paramFloat, localOExplosion.g));
        return localOExplosion;
    }

    public void c(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        super.c(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
        D.f.a(paramInt1, paramInt2, paramInt3, 64.0D, new OPacket54(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5));
    }
}