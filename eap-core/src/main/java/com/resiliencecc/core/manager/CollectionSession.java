package com.resiliencecc.core.manager;

import com.resiliencecc.api.exception.DataNotFoundException;
import com.resiliencecc.api.manager.CollectionManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CollectionSession implements CollectionManager {

    @PersistenceContext
    private EntityManager em;

    @Override
    public <T> int count(@NotNull Class<T> entityClass,
            Map<String, Object> filterKey, Map<String, Object> filterBy) {

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        final Root<T> rt = cq.from(entityClass);
        final List<Predicate> predicates = new ArrayList<>();

        if (!(filterKey == null || filterKey.isEmpty())) {
            filterKey.keySet().forEach(key -> {
                Predicate predicate = cb.equal(rt.get(key), filterKey.get(key));
                predicates.add(predicate);
            });
        }

        if (!(filterBy == null || filterBy.isEmpty())) {
            filterBy.keySet().forEach(attr -> {
                Expression<String> expression = rt.get(attr).as(String.class);
                String pattern = "%" + filterBy.get(attr) + "%";
                Predicate predicate = cb.like(cb.lower(expression), pattern.toLowerCase());
                predicates.add(predicate);
            });
        }

        if (!predicates.isEmpty()) {
            final Predicate[] ps = predicates.stream().toArray(Predicate[]::new);
            final Predicate predicate = cb.and(ps);

            cq.where(predicate);
        }

        cq.select(cb.count(rt));
        final Query query = em.createQuery(cq);
        return ((Long) query.getSingleResult()).intValue();
    }

    @Override
    public <T> T find(@NotNull Class<T> entityClass, @NotNull Object id) throws DataNotFoundException {
        final T entity = em.find(entityClass, id);

        if (entity != null) {
            return entity;
        } else {
            throw new DataNotFoundException();
        }
    }

    @Override
    public <T> T findAny(@NotNull final Class<T> entityClass, final Map<String, Object> properties)
            throws DataNotFoundException {

        try {
            final CriteriaBuilder cb = em.getCriteriaBuilder();
            final CriteriaQuery<T> cq = cb.createQuery(entityClass);
            final Root<T> rt = cq.from(entityClass);

            if (!(properties == null || properties.isEmpty())) {
                final List<Predicate> predicates = new ArrayList<>();

                properties.keySet().forEach(key -> {
                    Object value = properties.get(key);
                    Predicate predicate = cb.equal(rt.get(key), value);

                    if (value instanceof String) {
                        predicate = cb.equal(cb.lower(rt.get(key)), ((String) value).toLowerCase());
                    }

                    predicates.add(predicate);
                });

                final Predicate[] ps = predicates.stream().toArray(Predicate[]::new);
                final Predicate predicate = cb.or(ps);

                cq.where(predicate);
            }

            cq.select(rt);
            final TypedQuery<T> query = em.createQuery(cq);
            return query.getSingleResult();
        } catch (final NoResultException ex) {
            throw new DataNotFoundException();
        }
    }

    @Override
    public <T> T findOne(@NotNull final Class<T> entityClass, final Map<String, Object> properties)
            throws DataNotFoundException {

        try {
            final CriteriaBuilder cb = em.getCriteriaBuilder();
            final CriteriaQuery<T> cq = cb.createQuery(entityClass);
            final Root<T> rt = cq.from(entityClass);

            if (!(properties == null || properties.isEmpty())) {
                final List<Predicate> predicates = new ArrayList<>();

                properties.keySet().forEach(key -> {
                    Object value = properties.get(key);
                    Predicate predicate = cb.equal(rt.get(key), value);

                    if (value instanceof String) {
                        predicate = cb.equal(cb.lower(rt.get(key)), ((String) value).toLowerCase());
                    }

                    predicates.add(predicate);
                });

                final Predicate[] ps = predicates.stream().toArray(Predicate[]::new);
                final Predicate predicate = cb.and(ps);

                cq.where(predicate);
            }

            cq.select(rt);
            final TypedQuery<T> query = em.createQuery(cq);
            return query.getSingleResult();
        } catch (final NoResultException ex) {
            throw new DataNotFoundException();
        }
    }

    @Override
    public <T> List<T> filter(@NotNull Class<T> entityClass, Map<String, Object> filterBy) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(entityClass);
        final Root<T> rt = cq.from(entityClass);

        if (!(filterBy == null || filterBy.isEmpty())) {
            final List<Predicate> predicates = new ArrayList<>();

            filterBy.keySet().forEach(key -> {
                Predicate predicate = cb.equal(rt.get(key), filterBy.get(key));
                predicates.add(predicate);
            });

            final Predicate[] ps = predicates.stream().toArray(Predicate[]::new);
            final Predicate predicate = cb.and(ps);

            cq.where(predicate);
        }

        cq.select(rt);
        final TypedQuery<T> query = em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public <T> List<T> search(@NotNull Class<T> entityClass,
            int first, int pageSize, String sortField, String sortOrder,
            Map<String, Object> filterKey, Map<String, Object> filterBy) {

        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(entityClass);
        final Root<T> rt = cq.from(entityClass);
        final List<Predicate> predicates = new ArrayList<>();

        if (!(filterKey == null || filterKey.isEmpty())) {
            filterKey.keySet().forEach(key -> {
                Predicate predicate = cb.equal(rt.get(key), filterKey.get(key));
                predicates.add(predicate);
            });
        }

        if (!(filterBy == null || filterBy.isEmpty())) {
            filterBy.keySet().forEach(attr -> {
                Expression<String> expression = rt.get(attr).as(String.class);
                String pattern = "%" + filterBy.get(attr) + "%";
                Predicate predicate = cb.like(cb.lower(expression), pattern.toLowerCase());
                predicates.add(predicate);
            });
        }

        if (!predicates.isEmpty()) {
            final Predicate[] ps = predicates.stream().toArray(Predicate[]::new);
            final Predicate predicate = cb.and(ps);

            cq.where(predicate);
        }

        cq.select(rt);
        Order order = null;

        if (sortField != null && sortOrder != null) {
            final Expression<T> expression = rt.get(sortField);

            if (sortOrder.equalsIgnoreCase("ASCENDING")) {
                order = cb.asc(expression);
            } else if (sortOrder.equalsIgnoreCase("DESCENDING")) {
                order = cb.desc(expression);
            }

            cq.orderBy(order);
        }

        final TypedQuery<T> query = em.createQuery(cq);
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

}
