package com.example.arsenedata.dataEntity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "security_role")
public class Role implements GrantedAuthority
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(name = "role_name")
    private String roleName;

    @Column(name = "description")
    private String description;


    public Role()
    {

    }

    @Override
    public String getAuthority()
    {
        return roleName;
    }

    public Role(String roleName, String description)
    {
        this.roleName = roleName;
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString()
    {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
