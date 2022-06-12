package api.user.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserPutResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "job",
            "updatedAt"
    })
    @JsonProperty("name")
    public String name;
    @JsonProperty("job")
    public String job;
    @JsonProperty("updatedAt")
    public OffsetDateTime updatedAt;
}
