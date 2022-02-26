package com.resiliencecc.web.controller;

import com.resiliencecc.api.annotation.Delete;
import com.resiliencecc.api.annotation.Save;
import com.resiliencecc.api.annotation.Update;
import com.resiliencecc.api.constraint.BookSessionPolicyType;
import com.resiliencecc.api.exception.BusinessException;
import com.resiliencecc.api.manager.CollectionManager;
import com.resiliencecc.api.manager.PolicyConfigurationManager;
import com.resiliencecc.api.model.BookSessionPolicy;
import com.resiliencecc.api.model.BookSessionPolicy_;
import com.resiliencecc.web.exception.WebException;
import com.resiliencecc.web.util.MessageUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Ajax;

@Named
@ViewScoped
public class BookSessionPolicyController implements Serializable {

    @EJB
    private CollectionManager collectionManager;

    @EJB
    private PolicyConfigurationManager policyConfigurationManager;

    @Getter
    private List<BookSessionPolicy> statusSessionPolicies;

    @Getter
    private List<BookSessionPolicy> formatSessionPolicies;

    @Getter
    @Setter
    private BookSessionPolicy statusSessionPolicy;

    @Getter
    @Setter
    private BookSessionPolicy formatSessionPolicy;

    private List<BookSessionPolicy> findAllByPolicyType(BookSessionPolicyType policyType) {
        return collectionManager.filter(BookSessionPolicy.class,
                Map.of(BookSessionPolicy_.POLICY_TYPE, policyType));
    }

    @PostConstruct
    protected void init() {
        statusSessionPolicies = findAllByPolicyType(BookSessionPolicyType.STATUS);
        formatSessionPolicies = findAllByPolicyType(BookSessionPolicyType.FORMAT);
    }

    @Save
    @WebException
    public void save(BookSessionPolicyType policyType) throws BusinessException {
        switch (policyType) {
            case STATUS:
                policyConfigurationManager.save(statusSessionPolicy);
                statusSessionPolicies = findAllByPolicyType(BookSessionPolicyType.STATUS);
                MessageUtil.success(statusSessionPolicy.getPolicyName());
                break;

            case FORMAT:
                policyConfigurationManager.save(formatSessionPolicy);
                formatSessionPolicies = findAllByPolicyType(BookSessionPolicyType.FORMAT);
                MessageUtil.success(formatSessionPolicy.getPolicyName());
                break;
        }

        Ajax.oncomplete(String.format("PF('%s').hide()", policyType.name()));
    }

    @Update
    @WebException
    public void update(BookSessionPolicyType policyType) throws BusinessException {
        switch (policyType) {
            case STATUS:
                policyConfigurationManager.update(statusSessionPolicy);
                statusSessionPolicies = findAllByPolicyType(BookSessionPolicyType.STATUS);
                MessageUtil.success(statusSessionPolicy.getPolicyName());
                break;

            case FORMAT:
                policyConfigurationManager.update(formatSessionPolicy);
                formatSessionPolicies = findAllByPolicyType(BookSessionPolicyType.FORMAT);
                MessageUtil.success(formatSessionPolicy.getPolicyName());
                break;
        }

        Ajax.oncomplete(String.format("PF('%s').hide()", policyType.name()));
    }

    @Delete
    @WebException
    public void delete(BookSessionPolicy bookSessionPolicy) throws BusinessException {
        policyConfigurationManager.delete(bookSessionPolicy);
        MessageUtil.success(bookSessionPolicy.getPolicyName());

        switch (bookSessionPolicy.getPolicyType()) {
            case STATUS:
                statusSessionPolicies = findAllByPolicyType(BookSessionPolicyType.STATUS);
                break;

            case FORMAT:
                formatSessionPolicies = findAllByPolicyType(BookSessionPolicyType.FORMAT);
                break;
        }
    }

    public void addPolicy(BookSessionPolicyType policyType) {
        switch (policyType) {
            case STATUS:
                statusSessionPolicy = new BookSessionPolicy();
                statusSessionPolicy.setEnabled(true);
                statusSessionPolicy.setPolicyType(policyType);
                break;

            case FORMAT:
                formatSessionPolicy = new BookSessionPolicy();
                formatSessionPolicy.setEnabled(true);
                formatSessionPolicy.setPolicyType(policyType);
                break;
        }
    }

}
