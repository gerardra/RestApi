package au.com.restapi.domain.predicate;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface DomainOperations {
	default <T> List<T> filter (List<T> data, Predicate<T> predicate) {
        return data.stream().filter( predicate )
               .collect(Collectors.<T>toList());
    }
}
