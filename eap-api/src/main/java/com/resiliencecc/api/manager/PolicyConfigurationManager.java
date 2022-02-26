package com.resiliencecc.api.manager;

import com.resiliencecc.api.exception.BusinessException;
import com.resiliencecc.api.model.BookLocationPolicy;
import com.resiliencecc.api.model.BookSessionPolicy;
import com.resiliencecc.api.model.ClientPolicy;
import com.resiliencecc.api.model.GeneralPolicy;
import com.resiliencecc.api.model.InvoicePolicy;
import com.resiliencecc.api.model.ServiceChargePolicy;
import com.resiliencecc.api.model.SessionPolicy;
import com.resiliencecc.api.model.TaskPolicy;
import javax.ejb.Local;
import javax.validation.constraints.NotNull;

@Local
public interface PolicyConfigurationManager {

    public void save(@NotNull TaskPolicy taskPolicy) throws BusinessException;

    public void update(@NotNull TaskPolicy taskPolicy) throws BusinessException;

    public void delete(@NotNull TaskPolicy taskPolicy) throws BusinessException;

    public void save(@NotNull ClientPolicy clientPolicy) throws BusinessException;

    public void update(@NotNull ClientPolicy clientPolicy) throws BusinessException;

    public void delete(@NotNull ClientPolicy clientPolicy) throws BusinessException;

    public void save(@NotNull GeneralPolicy generalPolicy) throws BusinessException;

    public void update(@NotNull GeneralPolicy generalPolicy) throws BusinessException;

    public void delete(@NotNull GeneralPolicy generalPolicy) throws BusinessException;

    public void save(@NotNull ServiceChargePolicy serviceChargePolicy) throws BusinessException;

    public void update(@NotNull ServiceChargePolicy serviceChargePolicy) throws BusinessException;

    public void delete(@NotNull ServiceChargePolicy serviceChargePolicy) throws BusinessException;

    public void save(@NotNull BookSessionPolicy bookSessionPolicy) throws BusinessException;

    public void update(@NotNull BookSessionPolicy bookSessionPolicy) throws BusinessException;

    public void delete(@NotNull BookSessionPolicy bookSessionPolicy) throws BusinessException;

    public void save(@NotNull BookLocationPolicy bookLocationPolicy) throws BusinessException;

    public void update(@NotNull BookLocationPolicy bookLocationPolicy) throws BusinessException;

    public void delete(@NotNull BookLocationPolicy bookLocationPolicy) throws BusinessException;

    public void save(@NotNull SessionPolicy sessionPolicy) throws BusinessException;

    public void update(@NotNull SessionPolicy sessionPolicy) throws BusinessException;

    public void delete(@NotNull SessionPolicy sessionPolicy) throws BusinessException;

    public void save(@NotNull InvoicePolicy invoicePolicy) throws BusinessException;

    public void update(@NotNull InvoicePolicy invoicePolicy) throws BusinessException;

    public void delete(@NotNull InvoicePolicy invoicePolicy) throws BusinessException;

}
