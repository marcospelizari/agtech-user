package com.agtech.event.model;

import com.agtech.event.model.enums.RiskLevel;
import com.agtech.event.model.enums.Status;
import com.agtech.event.model.enums.TypeEvent;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_event")
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EVENTS")
    @SequenceGenerator(name = "SEQ_EVENTS", sequenceName = "SEQ_EVENTS", allocationSize = 1)
    private Integer id;
    private Integer idUser;
    private Integer idAddress;
    @Enumerated(EnumType.STRING)
    private TypeEvent typeEvent;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
    public Event() {}

    public Event(Integer id, Integer idUser, Integer idAddress, TypeEvent typeEvent, Status status, RiskLevel riskLevel) {
        this.id = id;
        this.idUser = idUser;
        this.idAddress = idAddress;
        this.typeEvent = typeEvent;
        this.status = status;
        this.riskLevel = riskLevel;
    }

    public Integer getId() {
        return id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Integer idAddress) {
        this.idAddress = idAddress;
    }

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
