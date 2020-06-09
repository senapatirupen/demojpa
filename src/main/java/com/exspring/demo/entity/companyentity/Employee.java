package com.exspring.demo.entity.companyentity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static javax.persistence.EnumType.ORDINAL;
import static javax.persistence.EnumType.STRING;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"department"})
@EqualsAndHashCode(exclude = {"department"})
@Entity
public class Employee extends AuditLog{
    public static final String LOCAL_AREA_CODE = "613";
//Id generation auto >>>>>
//    @Id @GeneratedValue(strategy=GenerationType.AUTO)

//Id generation table >>>>>
//@TableGenerator(name="Employee_Gen",
//        table="ID_GEN",
//        pkColumnName="GEN_NAME",
//        valueColumnName="GEN_VAL",
//        pkColumnValue="Emp_Gen",
//        initialValue=10000,
//        allocationSize=100)
//@Id @GeneratedValue(strategy=GenerationType.TABLE,
//        generator="Emp_Gen")

//Id Sequence Generator >>>>>
//    @SequenceGenerator(name="Emp_Gen", sequenceName="Emp_Seq")
//    @Id @GeneratedValue(generator="Emp_Gen")

//Id db identity Generation >>>>>
//@Id @GeneratedValue(strategy=GenerationType.IDENTITY)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private long salary;
    @Transient
    private String phoneNum;
    //lob mapping
    @Lob
    @Column(name = "EMP_PHOTO")
    private byte[] photo;
    //enum mapping
    @Enumerated(ORDINAL)//this is the default type
    private EmployeeType type;
    @Enumerated(STRING)
    private EmployeeType previousType;
    @Temporal(TemporalType.DATE)
    private java.util.Calendar dob;
    @Temporal(TemporalType.DATE)
    @Column(name = "S_DATE")
    private java.util.Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "E_DATE")
    private java.util.Date endDate;
    @OneToOne
    private Address address;
    //OneToOne Unidirectional >>>>>
    @OneToOne
    @JoinColumn(name = "PSPACE_ID")
    private ParkingSpace parkingSpace;
    //OneToOne Bidirectional >>>>>
    @OneToOne
    @JoinColumn(name = "EMCO_ID")
    private EmergencyContact emergencyContact;
    //OneToOne PK Mapping >>>>>
    @OneToOne(mappedBy = "employee")
    private Profile profile;
    //OneToMany Bidirectional >>>>>
    @JsonIgnore
    @ManyToOne
    private Department department;
    //OneToMany Unidirectional >>>>>
    @OneToMany
    @JoinTable(name = "EMP_PHONE",
            joinColumns = @JoinColumn(name = "EMP_ID"),
            inverseJoinColumns = @JoinColumn(name = "PHONE_ID"))
    private Collection<Phone> phones;
    //ManyToMany Bidirectional >>>>>
    @ManyToMany
    private Collection<Project> projects;
    //ManyToMany Bidirectional Join Table >>>>>
    @ManyToMany
    @JoinTable(name = "EMP_AWARD",
            joinColumns = @JoinColumn(name = "EMP_ID"),
            inverseJoinColumns = @JoinColumn(name = "AWARD_ID"))
    private Collection<Award> awards;
    //ManyToOne Join Column Unidirectional >>>>>
    @ManyToOne
    @JoinColumn(name = "OFFICE_ID")
    private OfficeLocation officeLocation;

    // In Case to override the Collection Table column names
    //    @CollectionTable(name="VACATION",
    //            joinColumns=@JoinColumn(name="EMP_ID"))
    //    @AttributeOverride(name="daysTaken",
    //            column=@Column(name="DAYS_ABS"))
    // Using a targetClass instead of generics
    @ElementCollection(targetClass=VacationEntry.class)
    private Collection vacationBookings;

    // Using generics in place of a targetClass
    @ElementCollection
    @Column(name="NICKNAME")
    private Set<String> nickNames;

    @ElementCollection
    @CollectionTable(name="EMP_PHONE")
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name="PHONE_TYPE")
    @Column(name="PHONE_NUM")
    private Map<PhoneType, String> phoneNumbers;

    //OneToMany using HashMap
    @ManyToOne
    private Client client;

    //ManyToMany using HashMap
    @ManyToMany(mappedBy="employeesBySkill")
    private Collection<Skill> skills;

    protected String getPhoneNumberForDb() {
        if (null != phoneNum && phoneNum.length() == 10)
            return phoneNum;
        else
            return LOCAL_AREA_CODE + phoneNum;
    }

    protected void setPhoneNumberForDb(String num) {
        if (num.startsWith(LOCAL_AREA_CODE))
            phoneNum = num.substring(3);
        else
            phoneNum = num;
    }
}

