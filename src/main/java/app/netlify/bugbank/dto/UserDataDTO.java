package app.netlify.bugbank.dto;

public class UserDataDTO {
    public static UserModelDTO dto = new UserModelDTO();

    public static UserModelDTO firstUserData() {
        dto.setEmail("user_1@test.com");
        dto.setName("User_1");
        dto.setPassword("secret");
        return new UserModelDTO(dto.getEmail(), dto.getName(), dto.getPassword());
    }

    public static UserModelDTO secondUserData() {
        dto.setEmail("user_2@test.com");
        dto.setName("User_2");
        dto.setPassword("secret");
        return new UserModelDTO(dto.getEmail(), dto.getName(), dto.getPassword());
    }
}
