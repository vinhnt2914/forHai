package portmanagementsystem.models;

public class Manager {
    private String username;
    private String password;
    private Port controlPort;

    public Manager(String username, String password, Port controlPort) {
        this.username = username;
        this.password = password;
        this.controlPort = controlPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Port getControlPort() {
        return controlPort;
    }

    public void setControlPort(Port controlPort) {
        this.controlPort = controlPort;
    }
}
