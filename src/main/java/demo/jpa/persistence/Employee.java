package demo.jpa.persistence;

import javax.persistence.*;

@Entity
@Table(name="employee")

public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "emp_id")
    private int emp_id;

    @Column(name = "emp_name")
    private String emp_name;

//    @Column(name = "job_id")
//    private int jobId;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job_id;

    @ManyToOne
    @JoinColumn(name = "dep_id")//, referencedColumnName = "department_id")
    private Department dep_id;

    public Employee( ) {
        super();
    }

    public Employee(int eid, String ename) {
        super( );
        this.emp_id = eid;
        this.emp_name = ename;
    }

//    public int getJobId() {
//        return jobId;
//    }
//
//    public void setJobId(int jobId) {
//        this.jobId = jobId;
//    }

    public Job getJob_id() {
        return job_id;
    }

    public void setJob_id(Job job) {
        this.job_id = job;
    }

    public Department getDep_id() {
        return dep_id;
    }

    public void setDep_id(Department depId) {
        this.dep_id = depId;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }
}
