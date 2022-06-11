package api.user.models;

import api.common.models.Support;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
public class SingleUser {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "data",
            "support"
    })
    @JsonProperty("data")
    public UserData data;
    @JsonProperty("support")
    public Support support;
}
