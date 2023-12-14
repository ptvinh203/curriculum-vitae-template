package model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.bean.CV;
import model.bean.User;
import model.dao.CVDAO;
import model.dao.UserRoleDAO;
import model.util.database.QueryType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private UUID userid;
    private String username;
    private String email;
    private String firstname;
    private String lastname;

    private UserRoleDTO role;

    private List<CVDTO> cvs;

    static public UserDTO fromEntity(User user) {
        UserRoleDAO roleDAO = UserRoleDAO.getInstance();
        CVDAO cvDAO = CVDAO.getInstance();

        UserRoleDTO userRoleDTO = null;
        try {
            userRoleDTO = UserRoleDTO.fromEntity(roleDAO.getById(user.getRoleid()).get());
        } catch (Exception e) {
            e.printStackTrace();
        }

        UserDTO userDTO = fromEntity(user, userRoleDTO, new ArrayList<>());

        try {
            List<CV> cvEntities = cvDAO.makeQuery(QueryType.SELECT)
                    .where(String.format("user_id='%s'", user.getUserid().toString()))
                    .query()
                    .toEntityList();
            cvEntities.forEach((cv) -> {
                userDTO.cvs.add(CVDTO.fromEntity(cv));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userDTO;
    }

    static public UserDTO fromEntity(User user, UserRoleDTO userRoleDTO, List<CVDTO> cvs) {
        return new UserDTO(
                user.getUserid(),
                user.getUsername(),
                user.getEmail(),
                user.getFirstname(),
                user.getLastname(),
                userRoleDTO,
                cvs);
    }
}
