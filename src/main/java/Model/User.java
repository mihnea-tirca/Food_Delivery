package Model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{
    public String username;
    public String password;
    public UserType userType;

    private static final long serialVersionUID = 6529685098267757690L;

    public User(String data){
        String[] arr = data.split(" ");
        username = arr[0];
        password = arr[1];
        switch(arr[2]){
            case "Administrator":
                userType = UserType.Admin;
                break;
            case "Client":
                userType = UserType.Client;
                break;
            case "Employee":
                userType = UserType.Employee;
                break;
        }
    }

    public User(String user, String pass, UserType type){
        username = user;
        password = pass;
        userType = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && userType == user.userType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, userType);
    }
}
