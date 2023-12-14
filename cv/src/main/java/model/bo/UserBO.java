package model.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.auth0.jwt.exceptions.JWTVerificationException;

import model.bean.User;
import model.dao.UserDAO;
import model.dto.UserDTO;
import model.util.database.Query;
import model.util.database.QueryType;
import model.util.security.JwtUtil;

public class UserBO {
    static private UserBO instance;

    static public UserBO getInstance() {
        if (instance == null)
            instance = new UserBO();
        return instance;
    }

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

    public List<UserDTO> findBy(List<String> filters) {
        try {
            Query<User> query = userDAO.makeQuery(QueryType.SELECT);
            for(String filter : filters)
                query = query.where(filter);
            return query
                .query()
                .toEntityList()
                .stream()
                .map(e -> UserDTO.fromEntity(e))
                .toList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public UserDTO getById(UUID id) {
        try {
            Optional<User> user = userDAO.getById(id);
            if (user.isPresent())
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
                userDTO.getRole().getRoleId());

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
                userDTO.getRole().getRoleId());

        userDAO.update(user);
        return UserDTO.fromEntity(user);
    }

    public UserDTO getByEmail(String email) {
        try {
            User user = userDAO.makeQuery(QueryType.SELECT)
                .where(String.format("email='%s'", email))
                .query()
                .first()
                .orElse(null);
            if(user != null)
                return UserDTO.fromEntity(user);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserDTO myProfile(String token) {
        try {
            String userId = JwtUtil.extractSubject(token);
            User user = userDAO.getById(UUID.fromString(userId)).orElseThrow();
            return UserDTO.fromEntity(user);
        } catch (JWTVerificationException e) {
            System.out.println("===> JWTVerificationException: " + e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
