package base.DIYClass;

import java.io.Serializable;


public class User implements Serializable {
    //�ֱ��Ӧ�û���������
    private String userId, password,sex,name,major,grade;
    private int id;
    //action���������ͣ����͵���������ʱ����actionΪ 1 ��Ϊ��¼����Ϊ 0 ��Ϊע��
    //status���û����ԣ���Ϊ 1 ��Ϊ����Ա��0 Ϊ��ͨ�û�
    private int action;
    private int status = 0;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
