package demo.primeface;

import java.io.Serializable;
import java.util.Objects;

public class Data implements Serializable {
    public String a;
    public String b;

    public Data(){}

    public Data(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Data data = (Data) o;
        return Objects.equals(a, data.a) && Objects.equals(b, data.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return "Data{" + "a='" + a + '\'' + ", b='" + b + '\'' + '}';
    }
}
