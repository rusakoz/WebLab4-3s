package org.lab4.wed.weblab4.db.entity;

import java.io.Serializable;

public interface BaseEntity<T extends Serializable> {
    void setId(T t);

    T getId();
}
