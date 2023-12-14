package model.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import model.bean.User;
import model.dao.UserDAO;
import model.dto.UserDTO;

public class UserBO {
    private UserDAO userDAO = UserDAO.getInstance();

    public List<UserDTO> getAll() {
        List<UserDTO> userDTOs = new ArrayList<>();
        try {
            List<User> users = userDAO.getAll();
            userDTOs = users.stream().map(u -> UserDTO.fromEntity(u)).toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDTOs;
    }

    public UserDTO getById(UUID id) {
        try {
            Optional<User> user = userDAO.getById(id);
            if(user.isPresent())
                return UserDTO.fromEntity(user.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO insert(UserDTO userDTO) {
        User user = new User(
            userDTO.getUsername(),
            userDTO.getEmail(), 
            userDTO.getFirstname(), 
            userDTO.getLastname(), 
            userDTO.getRole().getRoleId()
        );

        userDAO.insert(user);

        return UserDTO.fromEntity(user);
    }

    public UserDTO update(UserDTO userDTO) {
        User user = new User(
            userDTO.getUserid(),
            userDTO.getUsername(),
            userDTO.getEmail(), 
            userDTO.getFirstname(), 
            userDTO.getLastname(), 
            userDTO.getRole().getRoleId()
        );

        userDAO.update(user);

        // TODO: UPDATE USER'S CVS
        // 

        return UserDTO.fromEntity(user);
    }

    public void delete(UserDTO user) {
        userDAO.deleteById(user.getUserid());
    }

    public void deleteById(UUID id) {
        userDAO.deleteById(id);
    }
}
