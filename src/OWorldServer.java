
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.server.MinecraftServer;

public class OWorldServer extends OWorld {

    public OChunkProviderServer u;
    public boolean v = false;
    public boolean w;
    private MinecraftServer x;
    private OMCHashTable y = new OMCHashTable();

    public OWorldServer(MinecraftServer paramMinecraftServer, OISaveHandler paramOISaveHandler, String paramString, int paramInt) {
        super(paramOISaveHandler, paramString, new Random().nextLong(), OWorldProvider.a(paramInt));
        x = paramMinecraftServer;
    }

    public void a(OEntity paramOEntity, boolean paramBoolean) {
        if ((!x.m) && (((paramOEntity instanceof OEntityAnimals)) || ((paramOEntity instanceof OEntityWaterMob)))) {
            paramOEntity.C();
        }
        if ((paramOEntity.aD == null) || (!(paramOEntity.aD instanceof OEntityPlayer))) {
            super.a(paramOEntity, paramBoolean);
        }
    }

    public void b(OEntity paramOEntity, boolean paramBoolean) {
        super.a(paramOEntity, paramBoolean);
    }

    protected OIChunkProvider b() {
        OIChunkLoader localOIChunkLoader = p.a(m);
        u = new OChunkProviderServer(this, localOIChunkLoader, m.c());
        return u;
    }

    public List d(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6) {
        ArrayList localArrayList = new ArrayList();
        for (int i = 0; i < c.size(); i++) {
            OTileEntity localOTileEntity = (OTileEntity) c.get(i);
            if ((localOTileEntity.e >= paramInt1) && (localOTileEntity.f >= paramInt2) && (localOTileEntity.g >= paramInt3) && (localOTileEntity.e < paramInt4) && (localOTileEntity.f < paramInt5) && (localOTileEntity.g < paramInt6)) {
                localArrayList.add(localOTileEntity);
            }
        }
        return localArrayList;
    }

    public boolean a(OEntityPlayer paramOEntityPlayer, int paramInt1, int paramInt2, int paramInt3) {
        int i = (int) OMathHelper.e(paramInt1 - q.c());
        int j = (int) OMathHelper.e(paramInt3 - q.e());
        if (i > j) {
            j = i;
        }
        return (j > 16) || (x.f.h(paramOEntityPlayer.r));
    }

    protected void b(OEntity paramOEntity) {
        super.b(paramOEntity);
        y.a(paramOEntity.aA, paramOEntity);
    }

    protected void c(OEntity paramOEntity) {
        super.c(paramOEntity);
        y.d(paramOEntity.aA);
    }

    public OEntity a(int paramInt) {
        return (OEntity) y.a(paramInt);
    }

    public void a(OEntity paramOEntity, byte paramByte) {
        OPacket38 localOPacket38 = new OPacket38(paramOEntity.aA, paramByte);
        x.k.b(paramOEntity, localOPacket38);
    }

    public OExplosion a(OEntity paramOEntity, double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, boolean paramBoolean) {
        OExplosion localOExplosion = super.a(paramOEntity, paramDouble1, paramDouble2, paramDouble3, paramFloat, paramBoolean);
        x.f.a(paramDouble1, paramDouble2, paramDouble3, 64.0D, new OPacket60(paramDouble1, paramDouble2, paramDouble3, paramFloat, localOExplosion.g));
        return localOExplosion;
    }

    public void d(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
        super.d(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
        x.f.a(paramInt1, paramInt2, paramInt3, 64.0D, new OPacket54(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5));
    }

    public void r() {
        p.e();
    }

    @Override
    public boolean a(OEntity entity) {
        if(entity instanceof OEntityLiving && !(entity instanceof OEntityPlayer))
            // hMod: allow entities to spawn
            if ((Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob((OEntityLiving)entity))))
                return false;

        return super.a(entity);
    }
}
