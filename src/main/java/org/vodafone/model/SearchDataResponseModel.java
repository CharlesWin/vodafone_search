package org.vodafone.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchDataResponseModel {
    @SerializedName("data")
    @Expose
    private List<SearchResponseModel> data;
}
