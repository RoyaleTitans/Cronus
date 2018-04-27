package com.royale.titans.cronus.lib;

import com.royale.titans.cronus.ServerLogic;
import com.royale.titans.cronus.messages.server.SectorState;

public class BattleChecksumEncoder {
    private static final int[] WEED_TABLE = new int[] {
            0x431bde83, 0x3d09, 0x154ac8ce, 0xde68b267
    };

    private int mChecksum = 0;

    private int __ror4__(int x) {
        return ((x & ((int) Math.pow(2, 32) - 1)) >> 31 % 32) |
                (x << (32 - (31 % 32)) & ((int) Math.pow(2, 32) - 1));
    }

    private int sc_ror(int x, int a) {
        return ((x + __ror4__(a)) + 0x21);
    }

    private int sc_x(int x) {
        int a = (int) ((((long) WEED_TABLE[0]) * x & 0xffffffff00000000L) >> 32);
        a = (a >> 18) + (a >> 31);
        return x - ((a * WEED_TABLE[1]) << 6) + 1;
    }

    private void updateChecksum(int value) {
        mChecksum = sc_ror(mChecksum, value);
    }

    private void updateChecksumX(int value) {
        value = sc_x(value);
        updateChecksum(value);
    }

    private void updateChecksumH(int value) {
        value = value > 0 ? 13 : 7;
        mChecksum = value + __ror4__(mChecksum);
    }

    public int encode(int sequence, ServerLogic.ClientInfo clientInfo, SectorState sectorState) {
        int magic = sequence * 10;

        // start from magic
        updateChecksum(magic);
        // self update
        updateChecksum(mChecksum);
        // timestamp in minutes
        updateChecksum((int) (sectorState.getBattleInfo().getGameStartTimestamp() / 1000));
        // hardcoded
        updateChecksum(0xb);
        // magic again
        updateChecksum(magic);

        // this is shit
        mChecksum = WEED_TABLE[2] + __ror4__(mChecksum) + 0x9;
        updateChecksum(WEED_TABLE[3]);

        // todo get all values from sectorstate
        // unk
        updateChecksumX(15000029);

        updateChecksum(0);

        // arena
        updateChecksumX(54000024);

        // those 2 are dynamic.. i need to get the logic
        updateChecksum(0x40);
        updateChecksum(0x55000001);

        updateChecksum(0);

        // account id inverted
        updateChecksum(clientInfo.getClientId().low());
        updateChecksum(clientInfo.getClientId().high());

        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);

        updateChecksum(4);
        updateChecksum(1);
        updateChecksum(0);
        updateChecksum(0);

        updateChecksumX(72000007);

        updateChecksum(0);
        updateChecksum(0);

        updateChecksumH(0);
        updateChecksumH(0);
        updateChecksumH(0);
        updateChecksumH(0);
        updateChecksumH(0);
        updateChecksumH(0);
        updateChecksumH(0);

        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0);

        updateChecksum(116);
        updateChecksum(0 - 116);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(6);
        updateChecksum(0 - 6);
        updateChecksum(6);

        for (int i=0;i<4;i++) {
            updateChecksumX(35000001);
            mChecksum += 0x21;
            updateChecksum(1);
        }

        for (int i=0;i<2;i++) {
            updateChecksumX(35000000);
            mChecksum += 0x22;
            updateChecksum(0);
        }

        updateChecksum(1);
        updateChecksum(0);
        updateChecksum(1);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(1);

        for (int i=0;i<6;i++) {
            updateChecksumX(5000000 + i);
            mChecksum += 2;
            if (i == 1) {
                mChecksum += 9;
            } else if (i == 3) {
                mChecksum -= 1;
            } else if (i == 4) {
                mChecksum -= 2;
            } else if (i == 5) {
                mChecksum -= 3;
            }
            updateChecksum(i);
        }

        // towers
        updateChecksum(8);
        updateChecksum(13);
        updateChecksum(14500);
        updateChecksum(25500);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0xffffffff);
        updateChecksum(0);
        updateChecksum(0xffffffff);
        updateChecksum(500 * sequence);
        updateChecksum(0);
        updateChecksum(2);
        updateChecksum(0);
        updateChecksum(0);

        updateChecksum(8);
        updateChecksum(13);
        updateChecksum(3500);
        updateChecksum(6500);
        updateChecksum(0);
        updateChecksum(0);
        updateChecksum(0xffffffff);
        updateChecksum(0);
        updateChecksum(256);
        updateChecksum(500 * sequence);
        updateChecksum(0);
        updateChecksum(1);
        updateChecksum(0);
        updateChecksum(0);

        return mChecksum;
    }
}
