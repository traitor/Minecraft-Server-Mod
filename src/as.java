/*    */ public class as extends fq
/*    */ {
/*    */ 
/*    */   public as(int paramInt1, int paramInt2)
/*    */   {
/* 14 */     super(paramInt1);
/* 15 */     this.aX = 1;
/* 16 */     this.aY = 64;
/* 17 */     this.a = paramInt2;
/*    */   }

    public boolean a(hh paramgp, ft paramfc, el paramdy, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        if(paramInt4 == 0)
            paramInt2--;
        if(paramInt4 == 1)
            paramInt2++;
        if(paramInt4 == 2)
            paramInt3--;
        if(paramInt4 == 3)
            paramInt3++;
        if(paramInt4 == 4)
            paramInt1--;
        if(paramInt4 == 5)
            paramInt1++;
        if(a == 0)
        {
            int i1 = paramdy.a(paramInt1, paramInt2, paramInt3);
            if(i1 == 8 || i1 == 9 || i1 == 10 || i1 == 11)
                paramdy.d(paramInt1, paramInt2, paramInt3, 0);
        }
        int i = paramdy.a(paramInt1, paramInt2, paramInt3);
        if(i == 0)
            paramdy.d(paramInt1, paramInt2, paramInt3, a);
        paramgp.a(1);
        return true;
    }
/*    */   private int a;
/*    */ }

/* Location:           /Minecraft/server/bin/minecraft_server.jar
 * Qualified Name:     as
 * JD-Core Version:    0.6.0
 */