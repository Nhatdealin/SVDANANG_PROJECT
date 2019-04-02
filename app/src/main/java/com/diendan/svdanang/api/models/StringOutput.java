package com.diendan.svdanang.api.models;

import com.diendan.svdanang.api.models.BaseOutput;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 4/13/2017.
 */

public class StringOutput extends BaseOutput {
    @SerializedName("result")
    public String result;
}
