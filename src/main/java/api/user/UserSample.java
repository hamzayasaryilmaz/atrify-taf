package api.user;

import api.user.models.UserPost;
import com.github.javafaker.Faker;

public class UserSample {
    public static UserPost getDefaultUser() {
        Faker faker = new Faker();
        return UserPost.builder().name(faker.name().firstName()).job(faker.job().title()).build();
    }
}
