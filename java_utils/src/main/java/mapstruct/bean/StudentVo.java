package mapstruct.bean;

import java.util.Date;

public class StudentVo {
    private String id;
    private String name;
    private String sSex;

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sSex='" + sSex + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

