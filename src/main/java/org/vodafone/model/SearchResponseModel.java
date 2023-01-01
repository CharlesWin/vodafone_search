package org.vodafone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResponseModel {
    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("short_description")
    @Expose
    private String shortDescription;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("breadcrumbs")
    @Expose
    private String breadcrumbs;

}
