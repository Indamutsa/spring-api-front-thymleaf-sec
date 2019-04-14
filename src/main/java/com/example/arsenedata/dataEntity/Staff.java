package com.example.arsenedata.dataEntity;

import javax.persistence.*;

@Entity
@Table(name = "staff_member")
public class Staff
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "staff_id")
    private int staffId;

    @Embedded
    private Person member;

    public Staff()
    {

    }

    public Staff(Person member) {
        this.member = member;
    }

    public int getStaffId()
    {
        return staffId;
    }

    public void setStaffId(int staffId)
    {
        this.staffId = staffId;
    }

    public Person getMember()
    {
        return member;
    }

    public void setMember(Person member)
    {
        this.member = member;
    }

    @Override
    public String toString()
    {
        return "Staff{" +
                "staffId=" + staffId +
                ", member=" + member +
                '}';
    }
}
