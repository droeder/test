package demo.jpa;

import demo.jpa.persistence.Employee;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

public class JpaMain {

    private static final String PERSISTENCE_UNIT_NAME = "JPACriteriaBuilder";
    private static EntityManager em = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();

    public static void main(String[] args) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

            CriteriaQuery<EmployeeInfo> querySpec = cb.createQuery(EmployeeInfo.class);
            Root<Employee> emp = querySpec.from(Employee.class);

            final Join<Object, Object> depJoin = emp.join("dep_id");
            final Join<Object, Object> jobJoin = emp.join("job");



            final CompoundSelection<EmployeeInfo> construct =
                    cb.construct(EmployeeInfo.class,
                            emp.get("emp_id"),
                            emp.get("emp_name"),
                            depJoin.get("dep_name"),
                            jobJoin.get("job_name")
                    );


            querySpec.select(construct);

            em.createQuery(querySpec)
                .getResultList()
                .forEach( r -> System.out.println("r = " + r));

//        doThingsThatWork(cb);
    }

    private static void doThingsThatWork(CriteriaBuilder criteriaBuilderObj) {
        CriteriaQuery<Object> queryObj = criteriaBuilderObj.createQuery();
        Root<Employee> from = queryObj.from(Employee.class);

        // Step #1 - Displaying All Records
        System.out.println("\n! Display All Records For The 'Employee' Table !\n");
        CriteriaQuery<Object> selectQuery = queryObj.select(from);
        TypedQuery<Object> typedQuery = em.createQuery(selectQuery);
        List<Object> employeeList = typedQuery.getResultList();

        if(employeeList != null && employeeList.size() > 0) {
            for(Object obj : employeeList) {
                Employee emp = (Employee)obj;
                System.out.println(emp.toString());
            }
        } else {
            System.out.println("! ALERT - No Employees Are Present In The 'Employee' Table !");
        }

        // Step #2 - Displaying All Records In An Ordered Fashion
        System.out.println("\n! Displaying All Records For The 'Employee' Table In An Asc. Order !\n");
        CriteriaQuery<Object> ascSelectQuery = queryObj.select(from);
        ascSelectQuery.orderBy(criteriaBuilderObj.asc(from.get("emp_name")));
        TypedQuery<Object> ascTypedQuery = em.createQuery(ascSelectQuery);
        List<Object> ascEmployeeList = ascTypedQuery.getResultList();

        if(ascEmployeeList != null && ascEmployeeList.size() > 0) {
            for(Object obj : ascEmployeeList) {
                Employee emp = (Employee)obj;
                System.out.println(emp.toString());
            }
        } else {
            System.out.println("! ALERT - No Employees Are Present In The 'Employee' Table !");
        }
    }
}
