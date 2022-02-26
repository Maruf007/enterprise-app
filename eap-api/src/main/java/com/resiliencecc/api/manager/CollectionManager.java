package com.resiliencecc.api.manager;

import com.resiliencecc.api.exception.DataNotFoundException;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import javax.validation.constraints.NotNull;

@Local
public interface CollectionManager {

    public <T> int count(@NotNull Class<T> entityClass,
            Map<String, Object> filterKey, Map<String, Object> filterBy);

    public <T> T find(@NotNull Class<T> entityClass, @NotNull Object id) throws DataNotFoundException;

    public <T> T findAny(@NotNull Class<T> entityClass,
            Map<String, Object> properties) throws DataNotFoundException;

    public <T> T findOne(@NotNull Class<T> entityClass,
            Map<String, Object> properties) throws DataNotFoundException;

    public <T> List<T> filter(@NotNull Class<T> entityClass, Map<String, Object> filterBy);

    public <T> List<T> search(@NotNull Class<T> entityClass,
            int first, int pageSize, String sortField, String sortOrder,
            Map<String, Object> filterKey, Map<String, Object> filterBy);

}
