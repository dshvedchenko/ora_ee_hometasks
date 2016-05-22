package task_02.config;

/**
 * @author dshvedchenko on 20.05.16.
 */
public enum AppConfig {
    Instance;

    private AppConfig() {
        this.url = "jdbc:mysql://localhost:3306/hometask_02?useEncoding=true&characterEncoding=UTF-8";
        this.username = "root";
        this.password = "1qaz2wsx";
        this.dbClassName = "com.mysql.jdbc.Driver";
        this.connPoolSize = 5;
    }

    public String url;
    public String username;
    public String password;
    public String dbClassName;
    public int connPoolSize;
}
