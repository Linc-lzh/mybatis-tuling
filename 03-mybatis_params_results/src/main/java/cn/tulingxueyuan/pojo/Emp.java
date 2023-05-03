package cn.tulingxueyuan.pojo;


import java.time.LocalDate;
import java.util.Date;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class Emp {
    private Integer id;
    private String username;
    private LocalDate cjsj;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getCjsj() {
        return cjsj;
    }

    public void setCjsj(LocalDate cjsj) {
        this.cjsj = cjsj;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", cjsj=" + cjsj +
                '}';
    }

    public Emp(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Emp() {
    }
}
