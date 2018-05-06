package com.royale.titans.cronus.models;

import com.royale.titans.cronus.CRUtils;
import com.royale.titans.cronus.lib.HashTag;

import java.util.ArrayList;

public class PlayerInfo {

    private final ClientInfo mClientInfo;

    private float mElixir = 5.0f;

    private ArrayList<Integer> mMasksDeployed = new ArrayList<>();
    private int mUnitDeployed = 6;
    private int mSpellsDeployed = 0;
    private int mTotalDeployed = 6;

    public PlayerInfo(ClientInfo clientInfo) {
        mClientInfo = clientInfo;
    }

    public HashTag getClientId() {
        return mClientInfo.getClientId();
    }

    public ClientInfo getClientInfo() {
        return mClientInfo;
    }

    public float getAvailableElixir() {
        return mElixir;
    }

    public void incrementAvailableElixir() {
        if (mElixir < 10) {
            mElixir = mElixir + 0.25f;
        }
    }

    public void onCardDeployed(CRUtils.CardInfo cardInfo) {
        mElixir = mElixir - cardInfo.getElixirCost();

        if (cardInfo.getUnitCount() > 0) {
            mUnitDeployed = mUnitDeployed + cardInfo.getUnitCount();

            for (int i=0;i<cardInfo.getUnitCount();i++) {
                mMasksDeployed.add(cardInfo.getMaskId());
            }
        } else {
            mSpellsDeployed = mSpellsDeployed + 1;
        }

        mTotalDeployed = mUnitDeployed + mSpellsDeployed;
    }

    public int getUnitDeployed() {
        return mUnitDeployed;
    }

    public int getSpellsDeployed() {
        return mSpellsDeployed;
    }

    public int getTotalDeployed() {
        return mTotalDeployed;
    }

    public ArrayList<Integer> getMasksDeployed() {
        return mMasksDeployed;
    }
}
