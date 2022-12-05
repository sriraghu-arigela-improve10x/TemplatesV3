package com.improve10x.templatesv3;

import com.google.gson.annotations.SerializedName;

public class Template {
    @SerializedName("_id")
    public String id;
    public String templateId;
    public String title;
    @SerializedName("description")
    public String message;
}
