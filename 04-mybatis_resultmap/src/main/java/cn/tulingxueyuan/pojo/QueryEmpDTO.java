package cn.tulingxueyuan.pojo;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
public class QueryEmpDTO {

    private String deptName;
    private Integer deptId;
    private Integer id;
    private String username;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

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

    @Override
    public String toString() {
        return "QueryEmpDTO{" +
                "deptName='" + deptName + '\'' +
                ", deptId=" + deptId +
                ", id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}