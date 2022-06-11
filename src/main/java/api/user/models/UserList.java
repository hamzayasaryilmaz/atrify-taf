package api.user.models;

import api.common.models.Support;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserList {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("page")
    public Integer page;
    @JsonProperty("per_page")
    public Integer perPage;
    @JsonProperty("total")
    public Integer total;
    @JsonProperty("total_pages")
    public Integer totalPages;
    @JsonProperty("data")
    public List<UserData> data = null;
    @JsonProperty("support")
    public Support support;
}
