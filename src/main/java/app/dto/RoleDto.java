package app.dto;

import app.model.Role;

import java.io.Serializable;

/**
 * Created by Светлана on 25.08.2018.
 */
public class RoleDto implements Serializable {

    private Long id;

    private String name;

    public RoleDto() {
    }

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
