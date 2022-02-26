package com.resiliencecc.core.manager;

import com.resiliencecc.api.annotation.Delete;
import com.resiliencecc.api.annotation.Save;
import com.resiliencecc.api.annotation.Update;
import com.resiliencecc.api.exception.BusinessException;
import com.resiliencecc.api.manager.PolicyConfigurationManager;
import com.resiliencecc.api.model.BookLocationPolicy;
import com.resiliencecc.api.model.BookSessionPolicy;
import com.resiliencecc.api.model.ClientPolicy;
import com.resiliencecc.api.model.GeneralPolicy;
import com.resiliencecc.api.model.InvoicePolicy;
import com.resiliencecc.api.model.ServiceChargePolicy;
import com.resiliencecc.api.model.SessionPolicy;
import com.resiliencecc.api.model.TaskPolicy;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Interceptors(PolicyConfigurationBusiness.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PolicyConfigurationSession implements PolicyConfigurationManager {

    @PersistenceContext
    private EntityManager em;

    @Save(action = "saveTaskPolicy")
    @Override
    public void save(TaskPolicy taskPolicy) throws BusinessException {
        em.persist(taskPolicy);
    }

    @Update(action = "updateTaskPolicy")
    @Override
    public void update(TaskPolicy taskPolicy) throws BusinessException {
        em.merge(taskPolicy);
    }

    @Delete(action = "deleteTaskPolicy")
    @Override
    public void delete(TaskPolicy taskPolicy) throws BusinessException {
        em.remove(taskPolicy);
    }

    @Save(action = "saveClientPolicy")
    @Override
    public void save(ClientPolicy clientPolicy) throws BusinessException {
        em.persist(clientPolicy);
    }

    @Update(action = "updateClientPolicy")
    @Override
    public void update(ClientPolicy clientPolicy) throws BusinessException {
        em.merge(clientPolicy);
    }

    @Delete(action = "deleteClientPolicy")
    @Override
    public void delete(ClientPolicy clientPolicy) throws BusinessException {
        em.remove(clientPolicy);
    }

    @Save(action = "saveGeneralPolicy")
    @Override
    public void save(GeneralPolicy generalPolicy) throws BusinessException {
        em.persist(generalPolicy);
    }

    @Update(action = "updateGeneralPolicy")
    @Override
    public void update(GeneralPolicy generalPolicy) throws BusinessException {
        em.merge(generalPolicy);
    }

    @Delete(action = "deleteGeneralPolicy")
    @Override
    public void delete(GeneralPolicy generalPolicy) throws BusinessException {
        em.remove(generalPolicy);
    }

    @Save(action = "saveServiceChargePolicy")
    @Override
    public void save(ServiceChargePolicy serviceChargePolicy) throws BusinessException {
        em.persist(serviceChargePolicy);
    }

    @Update(action = "updateServiceChargePolicy")
    @Override
    public void update(ServiceChargePolicy serviceChargePolicy) throws BusinessException {
        em.merge(serviceChargePolicy);
    }

    @Delete(action = "deleteServiceChargePolicy")
    @Override
    public void delete(ServiceChargePolicy serviceChargePolicy) throws BusinessException {
        em.remove(serviceChargePolicy);
    }

    @Save(action = "saveBookSessionPolicy")
    @Override
    public void save(BookSessionPolicy bookSessionPolicy) throws BusinessException {
        em.persist(bookSessionPolicy);
    }

    @Update(action = "updateBookSessionPolicy")
    @Override
    public void update(BookSessionPolicy bookSessionPolicy) throws BusinessException {
        em.merge(bookSessionPolicy);
    }

    @Delete(action = "deleteBookSessionPolicy")
    @Override
    public void delete(BookSessionPolicy bookSessionPolicy) throws BusinessException {
        em.remove(bookSessionPolicy);
    }

    @Save(action = "saveBookLocationPolicy")
    @Override
    public void save(BookLocationPolicy bookLocationPolicy) throws BusinessException {
        em.persist(bookLocationPolicy);
    }

    @Update(action = "updateBookLocationPolicy")
    @Override
    public void update(BookLocationPolicy bookLocationPolicy) throws BusinessException {
        em.merge(bookLocationPolicy);
    }

    @Delete(action = "deleteBookLocationPolicy")
    @Override
    public void delete(BookLocationPolicy bookLocationPolicy) throws BusinessException {
        em.remove(bookLocationPolicy);
    }

    @Save(action = "saveSessionPolicy")
    @Override
    public void save(SessionPolicy sessionPolicy) throws BusinessException {
        em.persist(sessionPolicy);
    }

    @Update(action = "updateSessionPolicy")
    @Override
    public void update(SessionPolicy sessionPolicy) throws BusinessException {
        em.merge(sessionPolicy);
    }

    @Delete(action = "deleteSessionPolicy")
    @Override
    public void delete(SessionPolicy sessionPolicy) throws BusinessException {
        em.remove(sessionPolicy);
    }

    @Save(action = "saveInvoicePolicy")
    @Override
    public void save(InvoicePolicy invoicePolicy) throws BusinessException {
        em.persist(invoicePolicy);
    }

    @Update(action = "updateInvoicePolicy")
    @Override
    public void update(InvoicePolicy invoicePolicy) throws BusinessException {
        em.merge(invoicePolicy);
    }

    @Delete(action = "deleteInvoicePolicy")
    @Override
    public void delete(InvoicePolicy invoicePolicy) throws BusinessException {
        em.remove(invoicePolicy);
    }

}
