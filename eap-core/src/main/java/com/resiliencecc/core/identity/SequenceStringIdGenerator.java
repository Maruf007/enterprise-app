package com.resiliencecc.core.identity;

import java.io.Serializable;
import java.util.Properties;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;

public class SequenceStringIdGenerator extends TableGenerator {

    private String numberFormat;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Serializable sequenceId = super.generate(session, object);
        return String.format(numberFormat, sequenceId);
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        this.numberFormat = ConfigurationHelper.getString("numberFormat", params, "%032d");
        super.configure(IntegerType.INSTANCE, params, serviceRegistry);
    }

}
