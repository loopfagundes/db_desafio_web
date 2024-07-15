package app.netlify.bugbank.data;

public class UserData {
    public static UserModel data = new UserModel();

    public static UserModel firstUser() {
        data.setEmail("user_1@test.com");
        data.setName("User_1");
        data.setPassword("secret");
        return new UserModel(data.getEmail(), data.getName(), data.getPassword());
    }

    public static UserModel secondUser() {
        data.setEmail("user_2@test.com");
        data.setName("User_2");
        data.setPassword("secret");
        return new UserModel(data.getEmail(), data.getName(), data.getPassword());
    }
}
