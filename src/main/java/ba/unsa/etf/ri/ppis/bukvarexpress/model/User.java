package ba.unsa.etf.ri.ppis.bukvarexpress.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String phone;
    private String gender;
    private String email;
    private String username;
    private String password;
    private String role;
}
