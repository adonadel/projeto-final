package repository;

import model.Sector;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UserDAO{

    static List<User> users = new ArrayList<>();

    public void save(User user) {
        UserRepository userRepository = new UserRepository();
        try {
            if (user.getId() != null) {
                userRepository.update(user);
            } else {
                user.setId(userRepository.nextId());
                userRepository.insert(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        users.add(user);
    }

    public void remove(User user) throws SQLException, ClassNotFoundException {
        UserRepository userRepository = new UserRepository();
        userRepository.delete(user);
    }

    public List<User> searchAll() throws SQLException, ClassNotFoundException {
        UserRepository userRepository = new UserRepository();
        try{
            users = userRepository.search();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public List<User> searchByName(String name) {
        UserRepository userRepository = new UserRepository();
        List<User> ListUsers = new ArrayList<>();

        try{
            users = userRepository.searchByName(name);

            for (User auxUser : users){
                if (auxUser.getName().equals(name)) {
                    ListUsers.add(auxUser);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ListUsers;
    }

    public List<User> searchWithName(String name) {
        UserRepository userRepository = new UserRepository();
        List<User> filtredUsers = new ArrayList<>();
        try{
            users = userRepository.searchByName(name);

            for (User user : users){
                if (user.getName().contains(name)) {
                    filtredUsers.add(user);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return filtredUsers;
    }

    public User searchById(Integer id) {
        UserRepository userRepository = new UserRepository();
        User user = new User();
        try{
            users = userRepository.searchById(id);

            for (User auxUser : users){
                if (auxUser.getId().equals(id)) {
                    user = auxUser;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<User> searchBySectorId(Integer sectorId) {
        UserRepository userRepository = new UserRepository();
        List<User> auxUsers = new ArrayList<>();
        try{
            users = userRepository.searchBySectorId(sectorId);

            for (User user : users){
                if (user.getSector().getId().equals(sectorId)) {
                    auxUsers.add(user);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return auxUsers;
    }

    public Object[] searchAllOnlyWithName() throws SQLException, ClassNotFoundException {
        UserRepository userRepository = new UserRepository();
        ArrayList<String> names = new ArrayList<>();
        try{
            users = userRepository.search();

            for (User user : users) {
                names.add(user.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return names.toArray();
    }

    public Object[] searchAllWithIdOnName() {
        UserRepository userRepository = new UserRepository();
        List<String> usersName = new ArrayList<>();

        try{
            users = userRepository.search();

            for (User user : users) {
                usersName.add(user.getId() + " - " + user.getName());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return usersName.toArray();
    }

    public User checkUserAuth(String username, String password) {
        UserRepository userRepository = new UserRepository();
        User user;
        try {
            user = userRepository.searchByUsernamePassword(username, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public Object[] searchAllReturnArray() throws SQLException, ClassNotFoundException {
        List<User> users = searchAll();
        List<String> usersNames = new ArrayList<>();

        for (User user : users) {
            usersNames.add(user.getName());
        }

        return usersNames.toArray();
    }
}