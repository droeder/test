package demo.primeface;

import static java.util.stream.Collectors.toCollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "userBean")
@ViewScoped
public class UserBean {

    private static int CNT = 0;

    private String firstName;
    private String lastName;
    private List<Data> data = new ArrayList<>();
    private Collection<String> filterItems = new ArrayList<>();
    private List<Data> filteredData = new ArrayList<>();

    @PostConstruct
    public void init(){
        int limit = 50;
//        data = new ArrayList<>(limit);
//        filteredData = new ArrayList<>(limit);
//        filterItems = filterMethod();
//        for (int i = 0; i < 1; i++){
//            data.add(new Data("a"+i, "b"+i));
//        }
        System.out.println("INIT");
    }

    public void addRow() {
        System.out.println("addRow with CNT: " + CNT);
        CNT++;
        ArrayList<Data> tmp = new ArrayList<>();
        tmp.add(new Data("a"+CNT, "b"+CNT));
        for (int i = 0; i < 2; i++){
           tmp.add(new Data("a"+CNT+i, "b"+CNT+i));
        }
        setData(tmp);
        setFilteredData(tmp);
        setFilterItems(filterMethod());
        System.out.println("finish addRow with CNT: " + CNT);
    }

    public Collection<String> filterMethod(){
        TreeSet<String> values = data.stream().map(Data::getA).collect(toCollection(TreeSet::new));
        return values;
    }

    public List<Data> getData() {
        System.out.println("getData = " + data);
        return data;
    }

    public void setData(List<Data> data) {
        System.out.println("setData = " + data);
        this.data = data;
    }

    public List<Data> getFilteredData() {
        System.out.println("getFilteredData() = " + filteredData);
        return filteredData;
    }

    public void setFilteredData(List<Data> filteredData) {
        System.out.println("setFilteredData = " + filteredData);
        this.filteredData = filteredData;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<String> getFilterItems() {
        System.out.println("getFilterItems = " + filterItems);
        return filterItems;
    }

    public void setFilterItems(Collection<String> filterItems) {
        System.out.println("setFilterItems = " + filterItems);
        this.filterItems = filterItems;
    }
}
