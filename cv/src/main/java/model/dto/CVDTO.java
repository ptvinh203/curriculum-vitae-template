package model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model.bean.CV;
import model.dao.UserDAO;

@Getter
@Setter
@AllArgsConstructor
public class CVDTO {
    private UUID cvId;
    private UserDTO user;

    static public CVDTO fromEntity(CV cv) {
        UserDAO userDAO = UserDAO.getInstance();
        UserDTO userDTO = null;
        try {
            userDTO = UserDTO.fromEntity(userDAO.getById(cv.getUserId()).get());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return fromEntity(cv, userDTO);
    }

    static public CVDTO fromEntity(CV cv, UserDTO userDTO) {
        return new CVDTO(
            cv.getCvId(),
            userDTO
        );
    }
}
