package com.resiliencecc.core.manager;

import com.resiliencecc.api.annotation.Delete;
import com.resiliencecc.api.annotation.Save;
import com.resiliencecc.api.annotation.Update;
import com.resiliencecc.api.exception.BusinessException;
import com.resiliencecc.api.exception.DataNotFoundException;
import com.resiliencecc.api.manager.CollectionManager;
import com.resiliencecc.api.model.TaskPolicy;
import com.resiliencecc.api.model.TaskPolicy_;
import com.resiliencecc.core.exception.ExceptionBuilder;
import java.lang.reflect.Method;
import java.util.Map;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import lombok.extern.jbosslog.JBossLog;

@JBossLog
public class PolicyConfigurationBusiness {

    @EJB
    private CollectionManager collectionManager;

    @AroundInvoke
    public Object validation(InvocationContext context) throws Exception {
        Method method = context.getMethod();
        Object parameter = context.getParameters()[0];

        if (method.isAnnotationPresent(Save.class)) {
            switch (method.getAnnotation(Save.class).action()) {
                case "saveTaskPolicy":
                    saveTaskPolicyValidation((TaskPolicy) parameter);
                    break;
            }
        } else if (method.isAnnotationPresent(Update.class)) {
            switch (method.getAnnotation(Update.class).action()) {
                case "updateTaskPolicy":
                    updateTaskPolicyValidation((TaskPolicy) parameter);
                    break;
            }
        } else if (method.isAnnotationPresent(Delete.class)) {
            switch (method.getAnnotation(Delete.class).action()) {
                case "deleteTaskPolicy":
                    deleteTaskPolicyValidation((TaskPolicy) parameter);
                    break;
            }
        }

        return context.proceed();
    }

    private void saveTaskPolicyValidation(TaskPolicy taskPolicy) throws BusinessException {
        try {
            collectionManager.findOne(TaskPolicy.class,
                    Map.of(TaskPolicy_.POLICY_TYPE, taskPolicy.getPolicyType(),
                            TaskPolicy_.POLICY_NAME, taskPolicy.getPolicyName()));

            throw new ExceptionBuilder().bind("ref.exists", taskPolicy.getPolicyName()).build();
        } catch (DataNotFoundException ex) {
            log.debug(ex.getMessage(), ex);
        }
    }

    private void updateTaskPolicyValidation(TaskPolicy taskPolicy) throws BusinessException {
        try {
            TaskPolicy presentTaskPolicy = collectionManager.findOne(TaskPolicy.class,
                    Map.of(TaskPolicy_.POLICY_TYPE, taskPolicy.getPolicyType(),
                            TaskPolicy_.POLICY_NAME, taskPolicy.getPolicyName()));

            if (!presentTaskPolicy.getPolicyId().equals(taskPolicy.getPolicyId())) {
                throw new ExceptionBuilder().bind("ref.exists", taskPolicy.getPolicyName()).build();
            }
        } catch (DataNotFoundException ex) {
            log.debug(ex.getMessage(), ex);
        }
    }

    private void deleteTaskPolicyValidation(TaskPolicy taskPolicy) throws BusinessException {
        throw new ExceptionBuilder().bind("ref.exists", taskPolicy.getPolicyName()).build();
    }

}
