package api.user.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UserPostResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder({
            "name",
            "job",
            "id",
            "createdAt"
    })
    @JsonProperty("name")
    public String name;
    @JsonProperty("job")
    public String job;
    @JsonProperty("id")
    public String id;
    @JsonProperty("createdAt")
    public OffsetDateTime createdAt;
}
