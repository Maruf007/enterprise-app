package com.resiliencecc.web.controller;

import com.resiliencecc.api.annotation.Delete;
import com.resiliencecc.api.annotation.Save;
import com.resiliencecc.api.annotation.Update;
import com.resiliencecc.api.constraint.GeneralPolicyType;
import com.resiliencecc.api.exception.BusinessException;
import com.resiliencecc.api.manager.CollectionManager;
import com.resiliencecc.api.manager.PolicyConfigurationManager;
import com.resiliencecc.api.model.GeneralPolicy;
import com.resiliencecc.api.model.GeneralPolicy_;
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
public class GeneralPolicyController implements Serializable {

    @EJB
    private CollectionManager collectionManager;

    @EJB
    private PolicyConfigurationManager policyConfigurationManager;

    @Getter
    private List<GeneralPolicy> departmentPolicies;

    @Getter
    private List<GeneralPolicy> employmentPolicies;

    @Getter
    private List<GeneralPolicy> sourcePolicies;

    @Getter
    @Setter
    private GeneralPolicy departmentPolicy;

    @Getter
    @Setter
    private GeneralPolicy employmentPolicy;

    @Getter
    @Setter
    private GeneralPolicy sourcePolicy;

    private List<GeneralPolicy> findAllByPolicyType(GeneralPolicyType policyType) {
        return collectionManager.filter(GeneralPolicy.class, Map.of(GeneralPolicy_.POLICY_TYPE, policyType));
    }

    @PostConstruct
    protected void init() {
        departmentPolicies = findAllByPolicyType(GeneralPolicyType.DEPARTMENT);
        employmentPolicies = findAllByPolicyType(GeneralPolicyType.EMPLOYMENT);
        sourcePolicies = findAllByPolicyType(GeneralPolicyType.SOURCE);
    }

    @Save
    @WebException
    public void save(GeneralPolicyType policyType) throws BusinessException {
        switch (policyType) {
            case DEPARTMENT:
                policyConfigurationManager.save(departmentPolicy);
                departmentPolicies = findAllByPolicyType(GeneralPolicyType.DEPARTMENT);
                MessageUtil.success(departmentPolicy.getPolicyName());
                break;

            case EMPLOYMENT:
                policyConfigurationManager.save(employmentPolicy);
                employmentPolicies = findAllByPolicyType(GeneralPolicyType.EMPLOYMENT);
                MessageUtil.success(employmentPolicy.getPolicyName());
                break;

            case SOURCE:
                policyConfigurationManager.save(sourcePolicy);
                sourcePolicies = findAllByPolicyType(GeneralPolicyType.SOURCE);
                MessageUtil.success(sourcePolicy.getPolicyName());
                break;
        }

        Ajax.oncomplete(String.format("PF('%s').hide()", policyType.name()));
    }

    @Update
    @WebException
    public void update(GeneralPolicyType policyType) throws BusinessException {
        switch (policyType) {
            case DEPARTMENT:
                policyConfigurationManager.update(departmentPolicy);
                departmentPolicies = findAllByPolicyType(GeneralPolicyType.DEPARTMENT);
                MessageUtil.success(departmentPolicy.getPolicyName());
                break;

            case EMPLOYMENT:
                policyConfigurationManager.update(employmentPolicy);
                employmentPolicies = findAllByPolicyType(GeneralPolicyType.EMPLOYMENT);
                MessageUtil.success(employmentPolicy.getPolicyName());
                break;

            case SOURCE:
                policyConfigurationManager.update(sourcePolicy);
                sourcePolicies = findAllByPolicyType(GeneralPolicyType.SOURCE);
                MessageUtil.success(sourcePolicy.getPolicyName());
                break;
        }

        Ajax.oncomplete(String.format("PF('%s').hide()", policyType.name()));
    }

    @Delete
    @WebException
    public void delete(GeneralPolicy generalPolicy) throws BusinessException {
        policyConfigurationManager.delete(generalPolicy);
        MessageUtil.success(generalPolicy.getPolicyName());

        switch (generalPolicy.getPolicyType()) {
            case DEPARTMENT:
                departmentPolicies = findAllByPolicyType(GeneralPolicyType.DEPARTMENT);
                break;

            case EMPLOYMENT:
                employmentPolicies = findAllByPolicyType(GeneralPolicyType.EMPLOYMENT);
                break;

            case SOURCE:
                sourcePolicies = findAllByPolicyType(GeneralPolicyType.SOURCE);
                break;
        }
    }

    public void addPolicy(GeneralPolicyType policyType) {
        switch (policyType) {
            case DEPARTMENT:
                departmentPolicy = new GeneralPolicy();
                departmentPolicy.setEnabled(true);
                departmentPolicy.setPolicyType(policyType);
                break;

            case EMPLOYMENT:
                employmentPolicy = new GeneralPolicy();
                employmentPolicy.setEnabled(true);
                employmentPolicy.setPolicyType(policyType);
                break;

            case SOURCE:
                sourcePolicy = new GeneralPolicy();
                sourcePolicy.setEnabled(true);
                sourcePolicy.setPolicyType(policyType);
                break;
        }
    }

}
