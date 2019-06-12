package demo.jpa;

public class EmployeeInfo {
    private Integer id;
    private String name;
    private String depName;
    private String jobName;

    public EmployeeInfo(){}

    public EmployeeInfo(Integer id, String name){
        this(id, name, null, null);
    }

    public EmployeeInfo(Integer id, String name, String depName, String jobName){
        this.id = id;
        this.name = name;
        this.depName = depName;
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override public String toString() {
        return "EmployeeInfo{" + "id=" + id + ", name='" + name + '\'' + ", depName='" + depName + '\'' + '}';
    }
}
