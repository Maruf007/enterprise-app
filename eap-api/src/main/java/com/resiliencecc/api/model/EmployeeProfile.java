package com.resiliencecc.api.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

@Data
@Audited
@DynamicUpdate
@EqualsAndHashCode(of = "employeeId")
@ToString(of = {"employeeId", "fullName"})
@Entity
@Table(name = "employeeprofile")
@SuppressWarnings("PersistenceUnitPresent")
public class EmployeeProfile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "employee_id")
    @GenericGenerator(name = "employee_id",
            strategy = "com.resiliencecc.core.identity.SequenceStringIdGenerator",
            parameters = {
                @Parameter(name = "table_name", value = "sequencestore"),
                @Parameter(name = "segment_column_name", value = "tablename"),
                @Parameter(name = "segment_value", value = "employeeprofile"),
                @Parameter(name = "value_column_name", value = "keyvalue"),
                @Parameter(name = "initial_value", value = "2"),
                @Parameter(name = "increment_size", value = "1"),
                @Parameter(name = "numberFormat", value = "%06d")
            })
    @Basic(optional = false)
    @Column(name = "employeeid", nullable = false, length = 6)
    private String employeeId;

    @NotBlank
    @Size(min = 1, max = 100)
    @Basic(optional = false)
    @Column(name = "fullname", nullable = false, length = 100)
    private String fullName;

    @NotNull
    @Basic(optional = false)
    @Column(name = "departmentid", nullable = false)
    private Integer departmentId;

    @Basic(optional = true)
    @Column(name = "supervisorid", nullable = true, length = 6)
    private String supervisorId;

    @NotAudited
    @Version
    @Basic(optional = false)
    @Column(name = "versionid", nullable = false)
    private Integer versionId;

}
