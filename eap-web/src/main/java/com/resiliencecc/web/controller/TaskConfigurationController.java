package com.resiliencecc.web.controller;

import com.resiliencecc.api.annotation.Delete;
import com.resiliencecc.api.annotation.Save;
import com.resiliencecc.api.annotation.Update;
import com.resiliencecc.api.constraint.TaskPolicyType;
import com.resiliencecc.api.exception.BusinessException;
import com.resiliencecc.api.manager.CollectionManager;
import com.resiliencecc.api.manager.PolicyConfigurationManager;
import com.resiliencecc.api.model.TaskPolicy;
import com.resiliencecc.api.model.TaskPolicy_;
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
public class TaskConfigurationController implements Serializable {

    @EJB
    private CollectionManager collectionManager;

    @EJB
    private PolicyConfigurationManager policyConfigurationManager;

    @Getter
    private List<TaskPolicy> taskPolicies;

    @Getter
    private List<TaskPolicy> statusPolicies;

    @Getter
    private List<TaskPolicy> priorityPolicies;

    @Getter
    @Setter
    private TaskPolicy taskPolicy;

    @Getter
    @Setter
    private TaskPolicy statusPolicy;

    @Getter
    @Setter
    private TaskPolicy priorityPolicy;

    private List<TaskPolicy> findAllByPolicyType(TaskPolicyType policyType) {
        return collectionManager.filter(TaskPolicy.class, Map.of(TaskPolicy_.POLICY_TYPE, policyType));
    }

    @PostConstruct
    protected void init() {
        taskPolicies = findAllByPolicyType(TaskPolicyType.TASK);
        statusPolicies = findAllByPolicyType(TaskPolicyType.STATUS);
        priorityPolicies = findAllByPolicyType(TaskPolicyType.PRIORITY);
    }

    @Save
    @WebException
    public void save(TaskPolicyType policyType) throws BusinessException {
        switch (policyType) {
            case TASK:
                policyConfigurationManager.save(taskPolicy);
                taskPolicies = findAllByPolicyType(TaskPolicyType.TASK);
                MessageUtil.success(taskPolicy.getPolicyName());
                break;

            case STATUS:
                policyConfigurationManager.save(statusPolicy);
                statusPolicies = findAllByPolicyType(TaskPolicyType.STATUS);
                MessageUtil.success(statusPolicy.getPolicyName());
                break;

            case PRIORITY:
                policyConfigurationManager.save(priorityPolicy);
                priorityPolicies = findAllByPolicyType(TaskPolicyType.PRIORITY);
                MessageUtil.success(priorityPolicy.getPolicyName());
                break;
        }

        Ajax.oncomplete(String.format("PF('%s').hide()", policyType.name()));
    }

    @Update
    @WebException
    public void update(TaskPolicyType policyType) throws BusinessException {
        switch (policyType) {
            case TASK:
                policyConfigurationManager.update(taskPolicy);
                taskPolicies = findAllByPolicyType(TaskPolicyType.TASK);
                MessageUtil.success(taskPolicy.getPolicyName());
                break;

            case STATUS:
                policyConfigurationManager.update(statusPolicy);
                statusPolicies = findAllByPolicyType(TaskPolicyType.STATUS);
                MessageUtil.success(statusPolicy.getPolicyName());
                break;

            case PRIORITY:
                policyConfigurationManager.update(priorityPolicy);
                priorityPolicies = findAllByPolicyType(TaskPolicyType.PRIORITY);
                MessageUtil.success(priorityPolicy.getPolicyName());
                break;
        }

        Ajax.oncomplete(String.format("PF('%s').hide()", policyType.name()));
    }

    @Delete
    @WebException
    public void delete(TaskPolicy taskPolicy) throws BusinessException {
        policyConfigurationManager.delete(taskPolicy);
        MessageUtil.success(taskPolicy.getPolicyName());

        switch (taskPolicy.getPolicyType()) {
            case TASK:
                taskPolicies = findAllByPolicyType(TaskPolicyType.TASK);
                break;

            case STATUS:
                statusPolicies = findAllByPolicyType(TaskPolicyType.STATUS);
                break;

            case PRIORITY:
                priorityPolicies = findAllByPolicyType(TaskPolicyType.PRIORITY);
                break;
        }
    }

    public void addPolicy(TaskPolicyType policyType) {
        switch (policyType) {
            case TASK:
                taskPolicy = new TaskPolicy();
                taskPolicy.setEnabled(true);
                taskPolicy.setPolicyType(policyType);
                break;

            case STATUS:
                statusPolicy = new TaskPolicy();
                statusPolicy.setEnabled(true);
                statusPolicy.setPolicyType(policyType);
                break;

            case PRIORITY:
                priorityPolicy = new TaskPolicy();
                priorityPolicy.setEnabled(true);
                priorityPolicy.setPolicyType(policyType);
                break;
        }
    }

}
