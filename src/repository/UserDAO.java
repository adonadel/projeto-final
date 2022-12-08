package repository;

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
        System.out.println(users);

        UserRepository userRepository = new UserRepository();
        try{
            users = userRepository.search();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public List<User> searchWithName(String name) {
        List<User> filtredUsers = new ArrayList<>();
        for (User user : users){
            if (user.getName().contains(name)) {
                filtredUsers.add(user);
            }
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
}