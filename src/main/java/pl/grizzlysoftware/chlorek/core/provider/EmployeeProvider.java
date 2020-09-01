package pl.grizzlysoftware.chlorek.core.provider;

import pl.grizzlysoftware.chlorek.core.model.Employee;
import pl.grizzlysoftware.chlorek.core.model.Shift;

import java.util.Collection;

/**
 * @author Bartosz Pawłowski, bpawlowski@grizzlysoftware.pl
 */
public interface EmployeeProvider {
    Collection<Employee> getEmployees();
    Collection<Shift> getShifts(Long branchId, int limit, int offset);
}
