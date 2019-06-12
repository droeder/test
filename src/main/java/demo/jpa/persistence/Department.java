package demo.jpa.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="department")
public class Department {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "dep_name")
    private String dep_name;

    public Department( ) {
        super();
    }

    public Department(int dip, String dName) {
        super( );
        this.departmentId = dip;
        this.dep_name = dName;
    }

    @OneToMany()
    private Set<Employee> employees = new HashSet<>();


    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int dep_id) {
        this.departmentId = dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String emp_name) {
        this.dep_name = emp_name;
    }
}
