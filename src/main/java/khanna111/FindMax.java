package khanna111;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

import streams.Employee;

public class FindMax {

	public static void main(String[] args) {
		List<Employee> employees = Arrays.asList(
                new Employee("Cersei",     250_000, "Lannister"),
                new Employee("Jamie",      150_000, "Lannister"),
                new Employee("Tyrion",       1_000, "Lannister"),
                new Employee("Tywin",    1_000_000, "Lannister"),
                new Employee("Jon Snow",    75_000, "Stark"),
                new Employee("Robb",       120_000, "Stark"),
                new Employee("Eddard",     125_000, "Stark"),
                new Employee("Sansa",            0, "Stark"),
                new Employee("Arya",         1_000, "Stark"));

        Employee defaultEmployee = new Employee("A man (or woman) has no name", 0, "Black and White");

        
		Optional<Employee> optionalEmp = employees.stream()
				.reduce(BinaryOperator.maxBy(Comparator.comparingInt(Employee::getSalary)));
		System.out.println("Emp with max salary 1: " + optionalEmp.orElse(defaultEmployee));
		
		optionalEmp = employees.stream()
				.reduce( new BinaryOperator<Employee>() {

					@Override
					public Employee apply(Employee t, Employee u) {

						int val = Integer.compare(t.getSalary(), u.getSalary());
						if (val > 0) {
							return t;
						}
						else {
							return u;
						}
					}
					
				});
		System.out.println("Emp with max salary 2: " + optionalEmp.orElse(defaultEmployee));

		optionalEmp = employees.stream().sorted( Comparator.comparing(Employee::getSalary).reversed()).findFirst();
		
		System.out.println("Emp with max salary 3: " + optionalEmp.orElse(defaultEmployee));
		
		optionalEmp = employees.stream().sorted((a,b) -> Comparator.comparing(Employee::getSalary).reversed().compare(a, b)).findFirst();

		System.out.println("Emp with max salary 4: " + optionalEmp.orElse(defaultEmployee));
		

		optionalEmp = employees.stream().max( Comparator.comparingInt(Employee::getSalary));

		System.out.println("Emp with max salary 5: " + optionalEmp.orElse(defaultEmployee));

	}

}