package com.resiliencecc.api.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "serialId")
@Embeddable
public class SessionStatus implements Serializable {

    @Basic(optional = false)
    @Column(name = "serialid", nullable = false)
    private Integer serialId;

    @NotNull
    @Basic(optional = false)
    @Column(name = "issueid", nullable = false)
    private Integer issueId;

    @Basic(optional = true)
    @Column(name = "symptomid", nullable = true)
    private Integer symptomId;

    @Basic(optional = true)
    @Column(name = "objectiveid", nullable = true)
    private Integer objectiveId;

    @Basic(optional = true)
    @Column(name = "interventionid", nullable = true)
    private Integer interventionId;

    @Basic(optional = true)
    @Column(name = "progressid", nullable = true)
    private Integer progressId;

}
