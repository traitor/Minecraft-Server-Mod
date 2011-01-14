import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class gx {

    private static final HashMap a = new HashMap();
    private final Map b = new HashMap();
    private boolean c;

    public void a(int paramInt, Object paramObject) {
        Integer localInteger = (Integer) a.get(paramObject.getClass());
        if (localInteger == null) {
            throw new IllegalArgumentException("Unknown data type: " + paramObject.getClass());
        }
        if (paramInt > 31) {
            throw new IllegalArgumentException("Data value id is too big with " + paramInt + "! (Max is " + 31 + ")");
        }
        if (this.b.containsKey(Integer.valueOf(paramInt))) {
            throw new IllegalArgumentException("Duplicate id value for " + paramInt + "!");
        }

        b localb = new b(localInteger.intValue(), paramInt, paramObject);
        this.b.put(Integer.valueOf(paramInt), localb);
    }

    public byte a(int paramInt) {
        Object a = ((b) this.b.get(Integer.valueOf(paramInt))).b();
        if (a instanceof Integer)
            return ((Integer)a).byteValue();
        else
            return ((Byte)a).byteValue();
    }

    public void b(int paramInt, Object paramObject) {
        b localb = (b) this.b.get(Integer.valueOf(paramInt));

        if (!paramObject.equals(localb.b())) {
            localb.a(paramObject);
            localb.a(true);
            this.c = true;
        }
    }

    public boolean a() {
        return this.c;
    }

    public static void a(List paramList, DataOutputStream paramDataOutputStream) {
        if (paramList != null) {
            for (Object localb : paramList) {
                a(paramDataOutputStream, (b)localb);
            }

        }
        try {
            paramDataOutputStream.writeByte(127);
        } catch (IOException ex) {
            Logger.getLogger(gx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList b() {
        ArrayList localArrayList = null;

        if (this.c) {
            for (Object loca : this.b.values()) {
                b localb = (b)loca;
                if (localb.d()) {
                    localb.a(false);

                    if (localArrayList == null) {
                        localArrayList = new ArrayList();
                    }
                    localArrayList.add(localb);
                }
            }
        }
        this.c = false;

        return localArrayList;
    }

    public void a(DataOutputStream paramDataOutputStream) {
        for (Object localb : this.b.values()) {
            a(paramDataOutputStream, (b)localb);
        }
        try {
            paramDataOutputStream.writeByte(127);
        } catch (IOException ex) {
            Logger.getLogger(gx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void a(DataOutputStream paramDataOutputStream, b paramb) {
        try {
            int i = (paramb.c() << 5 | paramb.a() & 0x1F) & 0xFF;
            paramDataOutputStream.writeByte(i);
            switch (paramb.c()) {
                case 0:
                    paramDataOutputStream.writeByte(((Byte) paramb.b()).byteValue());
                    break;
                case 1:
                    paramDataOutputStream.writeShort(((Short) paramb.b()).shortValue());
                    break;
                case 2:
                    paramDataOutputStream.writeInt(((Integer) paramb.b()).intValue());
                    break;
                case 3:
                    paramDataOutputStream.writeFloat(((Float) paramb.b()).floatValue());
                    break;
                case 4:
                    paramDataOutputStream.writeUTF((String) paramb.b());
                    break;
                case 5:
                    jl localjl = (jl) paramb.b();
                    paramDataOutputStream.writeShort(localjl.a().ba);
                    paramDataOutputStream.writeByte(localjl.a);
                    paramDataOutputStream.writeShort(localjl.h());
            }
        } catch (IOException ex) {
            Logger.getLogger(gx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List a(DataInputStream paramDataInputStream) {
        ArrayList localArrayList = null;
        try {
            int i = paramDataInputStream.readByte();
            while (i != 127) {
                if (localArrayList == null) {
                    localArrayList = new ArrayList();
                }
                int j = (i & 0xE0) >> 5;
                int k = i & 0x1F;
                b localb = null;
                switch (j) {
                    case 0:
                        localb = new b(j, k, Byte.valueOf(paramDataInputStream.readByte()));
                        break;
                    case 1:
                        localb = new b(j, k, Short.valueOf(paramDataInputStream.readShort()));
                        break;
                    case 2:
                        localb = new b(j, k, Integer.valueOf(paramDataInputStream.readInt()));
                        break;
                    case 3:
                        localb = new b(j, k, Float.valueOf(paramDataInputStream.readFloat()));
                        break;
                    case 4:
                        localb = new b(j, k, paramDataInputStream.readUTF());
                        break;
                    case 5:
                        int m = paramDataInputStream.readShort();
                        int n = paramDataInputStream.readByte();
                        int i1 = paramDataInputStream.readShort();
                        localb = new b(j, k, new jl(m, n, i1));
                }
                localArrayList.add(localb);
                i = paramDataInputStream.readByte();
            }
        } catch (IOException ex) {
            Logger.getLogger(gx.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localArrayList;
    }

    static {
        a.put(Byte.class, Integer.valueOf(0));
        a.put(Short.class, Integer.valueOf(1));
        a.put(Integer.class, Integer.valueOf(2));
        a.put(Float.class, Integer.valueOf(3));
        a.put(String.class, Integer.valueOf(4));
        a.put(jl.class, Integer.valueOf(5));
    }
}