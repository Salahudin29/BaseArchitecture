package com.tech387.ach.data.source.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExerciseResponse implements Serializable {

    @SerializedName("id")
    @Expose
    private long mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("raw_name")
    @Expose
    private String mRawName;

    @SerializedName("hardness")
    @Expose
    private String mHardness;

    @SerializedName("thumbnail")
    @Expose
    private String mThumbnail;

    @SerializedName("muscles_involved")
    @Expose
    private String mMusclesInvolved;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRawName() {
        return mRawName;
    }

    public void setRawName(String rawName) {
        mRawName = rawName;
    }

    public String getHardness() {
        return mHardness;
    }

    public void setHardness(String hardness) {
        mHardness = hardness;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public void setThumbnail(String thumbnail) {
        mThumbnail = thumbnail;
    }

    public String getMusclesInvolved() {
        return mMusclesInvolved;
    }

    public void setMusclesInvolved(String musclesInvolved) {
        mMusclesInvolved = musclesInvolved;
    }
}
