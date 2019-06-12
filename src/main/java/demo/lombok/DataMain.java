package demo.lombok;

import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public  class DataMain {

    public static void main(String... args) {

        final String format = String.format("test |%s| test", null);
        System.out.println("format = " + format);

    }
}

