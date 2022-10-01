package testdb;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class Company {
    public String name;
    public int employees;
    public List<String> offices;

    public Company(String name, int employees, List<String> offices) {
        this.name = name;
        this.employees = employees;
        this.offices = offices;
    }
}

public class Main123
{
    public static void main(String[] args)
    {
        Company companies = new Company("Google", 140000,
                Arrays.asList("Mountain View", "Los Angeles", "New York"));

        String path = "C:\\Users\\User78\\.test\\example.json";

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(path), companies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
