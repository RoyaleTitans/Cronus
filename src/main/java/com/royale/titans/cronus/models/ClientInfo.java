package com.royale.titans.cronus.models;

import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.lib.HashTag;
import com.royale.titans.cronus.lib.Nonce;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;

public class ClientInfo {
    private final AsynchronousSocketChannel mSocket;
    private final String mSessionKey;
    private final Nonce mR;

    private Nonce mS;

    private HashTag mClientId;
    private String mPlayerName;

    private ArrayList<CRUtils.CardInfo> mCurrentDeck;

    public ClientInfo(AsynchronousSocketChannel socket, String sessionKey) {
        mSocket = socket;
        mSessionKey = sessionKey;
        mR = new Nonce(new byte[24]);
    }

    public void setClientId(int high, int low) {
        if (high == 0 && low == 0) {
            mClientId = HashTag.randomHashTag();
        } else {
            mClientId = new HashTag(high, low);
        }

        mPlayerName = mClientId.toString();
    }

    public void setPlayerName(String playerName) {
        mPlayerName = playerName;
    }

    public void setCurrentDeck(ArrayList<CRUtils.CardInfo> currentDeck) {
        mCurrentDeck = currentDeck;
    }

    public void setNonce(Nonce sNonce) {
        mS = sNonce;
    }

    public AsynchronousSocketChannel getSocket() {
        return mSocket;
    }

    public String getSessionKey() {
        return mSessionKey;
    }

    public String getPlayerName() {
        return mPlayerName;
    }

    public HashTag getClientId() {
        return mClientId;
    }

    public ArrayList<CRUtils.CardInfo> getCurrentDeck() {
        return mCurrentDeck;
    }

    public Nonce sNonce() {
        return mS;
    }

    public Nonce rNonce() {
        return mR;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ClientInfo) {
            return ((ClientInfo) o).mClientId.lon() == mClientId.lon();
        } else if (o instanceof PlayerInfo) {
            return ((PlayerInfo) o).getClientId().lon() == mClientId.lon();
        }

        return false;
    }
}
