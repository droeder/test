package demo.jpa.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="job")
public class Job {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "job_name")
    private String jobName;


    @OneToOne(mappedBy = "job_id")
    private Employee employee;


    public Job( ) {
        super();
    }

    public Job(int dip, String dName) {
        super( );
        this.jobId = dip;
        this.jobName = dName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int dep_id) {
        this.jobId = dep_id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String emp_name) {
        this.jobName = emp_name;
    }
}
