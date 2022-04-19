package Web.EnglishCenter.entityDTO;

import Web.EnglishCenter.entity.user.Users;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private int id;
    private String title;
    private String content;
    private Users users;
}
