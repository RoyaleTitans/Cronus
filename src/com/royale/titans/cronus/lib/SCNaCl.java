package com.royale.titans.cronus.lib;

import java.util.Arrays;

public class SCNaCl {
    private static byte[] sigma = { 101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107 };
    private static int[] minusp = new int[] { 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 252 };
    private static final int BOX_NONCE_BYTES = 24;
    private static final int SIGNATURE_SIZE_BYTES = 64;
    private static final int SECRETBOX_OVERHEAD_BYTES = 16;
    private static final int SECRETBOX_INTERNAL_OVERHEAD_BYTES = 32;

    public static byte[] crypto_box(byte[] message, byte[] nonce, byte[] sk) {
        if (nonce.length != BOX_NONCE_BYTES)
            throw new IllegalStateException("Illegal nonce length: "+nonce.length);
        byte[] cipherText = new byte[SECRETBOX_INTERNAL_OVERHEAD_BYTES + message.length];
        byte[] paddedMessage = new byte[SECRETBOX_INTERNAL_OVERHEAD_BYTES + message.length];
        System.arraycopy(message, 0, paddedMessage, SECRETBOX_INTERNAL_OVERHEAD_BYTES, message.length);
        crypto_box_afternm(cipherText, paddedMessage, paddedMessage.length, nonce, sk);
        return Arrays.copyOfRange(cipherText, 16, cipherText.length);
    }

    public static byte[] crypto_box_open(byte[] cipher, byte[] nonce, byte[] sk) {
        byte[] paddedCipher = new byte[cipher.length + 16];
        System.arraycopy(cipher, 0, paddedCipher, 16, cipher.length);
        byte[] rawText = new byte[paddedCipher.length];
        crypto_box_open_afternm(rawText, paddedCipher, paddedCipher.length, nonce, sk);
        return Arrays.copyOfRange(rawText, 32, rawText.length);
    }

    private static void crypto_box_open_afternm(byte[] m, byte[] c, long d, byte[] n, byte[] k) {
        crypto_secretbox_open(m, c, d, n, k);
    }

    private static int crypto_box_afternm(byte[] c,byte[] m,long d,byte[] n,byte[] k) {
        return crypto_secretbox(c, m, d, n, k);
    }

    private static int crypto_secretbox_open(byte[] m,byte[] c,long d,byte[] n,byte[] k) {
        int i;
        byte[] x = new byte[32];
        if (d < 32) return -1;
        crypto_stream(x,32,n,k);
        if (crypto_onetimeauth_verify(c, 16,c, 32,d - 32,x) != 0) return -1;
        crypto_stream_xor(m, c, d, n, k);
        for (i=0;i < 32;++i)m[i] = 0;
        return 0;
    }

    private static int crypto_secretbox(byte[] c,byte[] m,long d,byte[] n,byte[] k) {
        int i;
        if (d < 32) return -1;
        crypto_stream_xor(c,m,d,n,k);
        crypto_onetimeauth(c, 16, c, 32, d - 32, c);
        for (i=0;i < 16;++i)c[i] = 0;
        return 0;
    }

    private static int crypto_onetimeauth_verify(byte[] h, int hOff, byte[] m, int mOff, long n,byte[] k) {
        byte[] x = new byte[16];
        crypto_onetimeauth(x, 0, m, mOff, n,k);
        return crypto_verify_16(h, hOff, x);
    }

    private static void crypto_stream(byte[] c, long d, byte[] n, byte[] k) {
        byte[] s = new byte[32];
        crypto_core_hsalsa20(s,n,k,sigma);
        crypto_stream_salsa20(c, d, n, 16, s);
    }

    private static void crypto_stream_xor(byte[] c, byte[] m, long d, byte[] n, byte[] k) {
        byte[] s = new byte[32];
        crypto_core_hsalsa20(s,n,k,sigma);
        crypto_stream_salsa20_xor(c, m, d, n, 16, s);
    }

    private static int crypto_stream_salsa20(byte[] c,long d,byte[] n, int nOff, byte[] k) {
        return crypto_stream_salsa20_xor(c,null,d,n, nOff, k);
    }

    private static void crypto_core_salsa20(byte[] out, byte[] in, byte[] k, byte[] c) {
        core(out,in,k,c,0);
    }

    private static void crypto_core_hsalsa20(byte[] out, byte[] in, byte[] k, byte[] c) {
        core(out,in,k,c,1);
    }

    private static int crypto_verify_16(byte[] x, int xOff, byte[] y) {
        return vn(x, xOff, y);
    }

    private static void crypto_onetimeauth(byte[] out, int outOff, byte[] m, int mOff, long n, byte[] k) {
        int s,i,j,u;
        int[] x = new int[17],r = new int[17],h = new int[17],c = new int[17],g = new int[17];

        for (j=0;j < 17;++j)
            r[j]= h[j] = 0;
        for (j=0;j < 16;++j)
            r[j] = 0xff & k[j];
        r[3]&=15;
        r[4]&=252;
        r[7]&=15;
        r[8]&=252;
        r[11]&=15;
        r[12]&=252;
        r[15]&=15;

        while (n > 0) {
            for (j=0;j < 17;++j)
                c[j] = 0;
            for (j = 0;(j < 16) && (j < n);++j)
                c[j] = 0xff & m[mOff + j];
            c[j] = 1;
            mOff += j; n -= j;
            add1305(h,c);
            for (i=0;i < 17;++i){
                x[i] = 0;
                for (j=0;j < 17; ++j)
                    x[i] += h[j] * ((j <= i)? r[i - j] : 320 * r[i + 17 - j]);
            }
            for (i=0;i < 17;++i)
                h[i] = x[i];
            u = 0;
            for (j=0;j < 16;++j){
                u += h[j];
                h[j] = u & 255;
                u >>= 8;
            }
            u += h[16]; h[16] = u & 3;
            u = 5 * (u >> 2);
            for (j=0;j < 16;++j){
                u += h[j];
                h[j] = u & 255;
                u >>= 8;
            }
            u += h[16]; h[16] = u;
        }

        for (j=0;j < 17;++j)g[j] = h[j];
        add1305(h,minusp);
        s = -(h[16] >> 7);
        for (j=0;j < 17;++j)h[j] ^= s & (g[j] ^ h[j]);

        for (j=0;j < 16;++j)
            c[j] = 0xff & k[j + 16];
        c[16] = 0;
        add1305(h,c);
        for (j=0;j < 16;++j)out[outOff + j] = (byte)h[j];
    }

    private static int crypto_stream_salsa20_xor(byte[] c,byte[] m,long b,byte[] n, int nOff, byte[] k) {
        byte[] z = new byte[16],x = new byte[64];
        int u,i;
        if (b == 0) return 0;
        for (i=0;i < 16;++i)z[i] = 0;
        for (i=0;i < 8;++i)z[i] = n[nOff + i];
        int cOff = 0;
        int mOff = 0;
        while (b >= 64) {
            crypto_core_salsa20(x,z,k,sigma);
            for (i=0;i < 64; ++i) c[cOff + i] = (byte)((m != null ? m[mOff + i]:0)^ x[i]);
            u = 1;
            for (i = 8;i < 16;++i) {
                u += 0xff & z[i];
                z[i] = (byte)u;
                u >>= 8;
            }
            b -= 64;
            cOff += 64;
            if (m != null) mOff += 64;
        }
        if (b != 0) {
            crypto_core_salsa20(x,z,k,sigma);
            for (i=0;i < b; i++) c[cOff + i] = (byte)((m != null ? m[mOff + i]:0)^ x[i]);
        }
        return 0;
    }

    private static void core(byte[] out,byte[] in,byte[] k,byte[] c,int h) {
        int[] w = new int[16],x = new int[16],y = new int[16],t = new int[4];
        int i,j,m;

        for (i=0;i < 4;++i){
            x[5*i] = ld32(c,4*i);
            x[1+i] = ld32(k,4*i);
            x[6+i] = ld32(in,4*i);
            x[11+i] = ld32(k,16+4*i);
        }

        for (i=0;i < 16;++i)y[i] = x[i];

        for (i=0;i < 17;++i){
            for (j=0;j < 4;++j){
                for (m=0;m < 4;++m)t[m] = x[(5*j+4*m)%16];
                t[1] ^= L32(t[0]+t[3], 7);
                t[2] ^= L32(t[1]+t[0], 9);
                t[3] ^= L32(t[2]+t[1],13);
                t[0] ^= L32(t[3]+t[2],18);
                for (m=0;m < 4;++m)w[4*j+(j+m)%4] = t[m];
            }
            for (m=0;m < 16;++m)x[m] = w[m];
        }

        if (h != 0) {
            for (i=0;i < 16;++i)x[i] += y[i];
            for (i=0;i < 4;++i){
                x[5*i] -= ld32(c,4*i);
                x[6+i] -= ld32(in,4*i);
            }
            for (i=0;i < 4;++i){
                st32(out, 4*i,x[5*i]);
                st32(out, 16+4*i,x[6+i]);
            }
        } else
            for (i=0;i < 16;++i)st32(out, 4 * i,x[i] + y[i]);
    }

    private static int vn(byte[] x, int xOff, byte[] y) {
        int i,d = 0;
        for (i=0; i < 16; ++i)d |= 0xff & (x[xOff + i]^y[i]);
        return (1 & ((d - 1) >> 8)) - 1;
    }

    private static void add1305(int[] h,int[] c) {
        int j,u = 0;
        for (j=0;j < 17;++j){
            u += h[j] + c[j];
            h[j] = u & 255;
            u >>= 8;
        }
    }

    private static int L32(int x,int c) { return (x << c) | (x >>> (32 - c)); }

    private static void st32(byte[] x, int off, int u) {
        int i;
        for (i=0;i < 4;++i){ x[off + i] = (byte)u; u >>= 8; }
    }

    private static int ld32(byte[] x, int off) {
        int u = x[off + 3] & 0xff;
        u = (u<<8)|(x[off + 2] & 0xff);
        u = (u<<8)|(x[off + 1] & 0xff);
        return (u<<8)|(x[off] & 0xff);
    }
}
