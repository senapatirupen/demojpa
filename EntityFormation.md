# Custom Table Schema
> @Entity @Table(name="EMP", schema="HR")
# Custom Column mapping
> @Entity
  public class Employee {
      @Column(name = "SAL")
      private long salary;
}
# LOB mapping
> @Entity
  public class Employee {
      @Lob @Column(name="PIC")
      private byte[] picture;
}
# Enum Mapping
> @Entity
  public class Employee {
      //this is the default type
      @Enumerated(ORDINAL)
      private EmployeeType type;
      @Enumerated(STRING)
      private EmployeeType previousType;
}
>> public enum EmployeeType {
    FULL_TIME_EMPLOYEE,
    PART_TIME_EMPLOYEE,  
    CONTRACT_EMPLOYEE 
}
# Temporal Mapping
> @Entity
  public class Employee {
      @Temporal(TemporalType.DATE)
      private java.util.Calendar dob;
      @Temporal(TemporalType.DATE)
      private java.util.Date startDate;
      @Temporal(TemporalType.TIMESTAMP)
      private java.util.Date endDate;
}
# Transient Mapping
> @Entity
  public class Employee {
      @Transient 
      private String convertedName;
}

# ID Generation
## Auto
> @Id @GeneratedValue(strategy=GenerationType.AUTO)
## Table
> @TableGenerator(name="Emp_Gen", table="ID_GEN", pkColumnName="GEN_NAME", valueColumnName="GEN_VAL")
  @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="Emp_Gen")
>> @TableGenerator(name="Address_Gen", table="ID_GEN", pkColumnName="GEN_NAME", valueColumnName="GEN_VAL",
              pkColumnValue="Addr_Gen", initialValue=10000, allocationSize=100) 
  @Id @GeneratedValue(strategy=GenerationType.TABLE, generator="Address_Gen")
## Sequence
> @SequenceGenerator(name="Emp_Gen", sequenceName="Emp_Seq")
  @Id @GeneratedValue(generator="Emp_Gen")
## By Default Identity
> @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
# ManyToOne
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @ManyToOne
      private Department department;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
}
# ManyToOne Join Column
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @ManyToOne
      @JoinColumn(name="DEPT_ID")
      private Department department;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
}
# OneToOne Unidirectional
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @OneToOne 
      @JoinColumn(name="PSPACE_ID") 
      private ParkingSpace parkingSpace;
}
>> @Entity
   public class ParkingSpace {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
}
# OneToOne Bidirectional
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @OneToOne 
      @JoinColumn(name="PSPACE_ID") 
      private ParkingSpace parkingSpace;
}
>> @Entity
   public class ParkingSpace {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
       @OneToOne(mappedBy="parkingSpace")
       private Employee employee;
}
# OneToOne PK Mapping
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @OneToOne(mappedBy="employee") 
      private ParkingSpace parkingSpace;
}
>> @Entity
   public class ParkingSpace {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
       @OneToOne
       @PrimaryKeyJoinColumn
       private Employee employee;
}
# OneToMany Unidirectional
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @OneToMany
      @JoinTable(name="EMP_PHONE", joinColumns=@JoinColumn(name="EMP_ID"),
            inverseJoinColumns=@JoinColumn(name="PHONE_ID"))
      private Collection<Phone> phones;
}
>> @Entity
   public class Phone {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
}
# OneToMany Bidirectional
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @ManyToOne
      private Department department;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
       @OneToMany(mappedBy="department")
       private Collection<Employee> employees;
}
# OneToMany using target entity
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @ManyToOne
      private Department department;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
       @OneToMany(targetEntity=Employee.class, mappedBy="department")
       private Collection<Employee> employees;
}
# OneToMany using Map
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @ManyToOne
      private Department department;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
       @OneToMany(mappedBy="department")
       @MapKeyColumn(name="CUB_ID")
       private Map<String, Employee> employeesByCubicle;
}
# OneToMany entity attribute key using Map
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @ManyToOne
      private Department department;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
       @OneToMany(mappedBy="department")
       @MapKey(name="id")
       private Map<Integer, Employee> employeesById;
}
# OneToMany embeddable as key using Map
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @ManyToOne
      private Department department;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
       @OneToMany(mappedBy="department")
       private Map<EmployeeName, Employee> employeesById;
}
>>> @Embeddable
   public class EmployeeName {
       @Column(name="F_NAME", insertable=false, updatable=false)
       private String first_Name;
       @Column(name="L_NAME", insertable=false, updatable=false)
       private String last_Name;
}
# ManyToMany embeddable as key override using Map
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private Long id;
      @Embedded
      private EmployeeName name;
      @ManyToMany(mappedBy="employeesByName")
      private Collection<Department> departments;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private Long id;
       @ManyToMany
       @JoinTable(name="DEPT_EMP",
           joinColumns=@JoinColumn(name="DEPT_ID"),
           inverseJoinColumns=@JoinColumn(name="EMP_ID"))
       @AttributeOverrides({
           @AttributeOverride(
               name="first_Name",
               column=@Column(name="EMP_FNAME")),
           @AttributeOverride(
               name="last_Name",
               column=@Column(name="EMP_LNAME"))
       })
       private Map<EmployeeName, Employee> employeesByName;
}
>>> @Embeddable
   public class EmployeeName {
       @Column(name="F_NAME", insertable=false, updatable=false)
       private String first_Name;
       @Column(name="L_NAME", insertable=false, updatable=false)
       private String last_Name;
}

# ManyToMany Bidirectional
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      @ManyToMany 
      private Collection<Project> projects;
}
>> @Entity
   public class Project {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       protected int id;
       @ManyToMany(mappedBy="projects")
       private Collection<Employee> employees;
}
# ManyToMany Join Table
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      @ManyToMany 
      @JoinTable(name="EMP_PROJ", 
            joinColumns=@JoinColumn(name="EMP_ID"),
            inverseJoinColumns=@JoinColumn(name="PROJ_ID"))
      private Collection<Project> projects;
}
>> @Entity
   public class Project {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       protected int id;
       @ManyToMany(mappedBy="projects")
       private Collection<Employee> employees;
}
# Embedded Object
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      @Embedded 
      private Address address;
}
>> @Embeddable
   public class Address {
       private String street;
}
# Sharing Embedded Object
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      @Embedded 
      private Address address;
}
>> @Embeddable
   public class Address {
       private String zip;
       private String state;
}
>>> @Entity
public class Company {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "state", column = @Column(name = "PROVINCE")),
        @AttributeOverride(name = "zip", column = @Column(name = "POSTAL_CODE"))
    })
    private Address address;
}
# Sharing Embedded Object
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      @Embedded 
      private Address address;
}
>> @Embeddable
   public class Address {
       private String zip;
       private String state;
}
>>> @Entity
public class Company {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "state", column = @Column(name = "PROVINCE")),
        @AttributeOverride(name = "zip", column = @Column(name = "POSTAL_CODE"))
    })
    private Address address;
}
# Element collection mapping
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      // Using a targetClass instead of generics
      @ElementCollection(targetClass=VacationEntry.class)
      private Collection vacationBookings;
      // Using generics in place of a targetClass
      @ElementCollection
      private Set<String> nickNames;
}
>> @Embeddable
   public class VacationEntry {
       private int daysTaken;
}
# Override element collection table column
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      // Using a targetClass instead of generics
      @ElementCollection(targetClass=VacationEntry.class)
      @CollectionTable(name="VACATION", joinColumns=@JoinColumn(name="EMP_ID"))    
      @AttributeOverride(name="daysTaken", column=@Column(name="DAYS_ABS"))
      private Collection vacationBookings;
      // Using generics in place of a targetClass
      @ElementCollection
      @Column(name="NICKNAME")
      private Set<String> nickNames;
}
>> @Embeddable
   public class VacationEntry {
       private int daysTaken;
}
# Element collection string using Map
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      @ElementCollection
      @CollectionTable(name="EMP_PHONE")
      @MapKeyColumn(name="PHONE_TYPE")
      @Column(name="PHONE_NUM")
      private Map<String, String> phoneNumbers;
}
# Element collection string and Enum using Map
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      @ElementCollection
      @CollectionTable(name="EMP_PHONE")
      @MapKeyEnumerated(EnumType.STRING)
      @MapKeyColumn(name="PHONE_TYPE")
      @Column(name="PHONE_NUM")
      private Map<PhoneType, String> phoneNumbers;
}
>> public enum PhoneType {
       Home, Mobile, Work
}
# Element collection Map entry key
> @Entity
  public class Employee {
      @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
      private int id;
      private String name;
}
>> @Entity
   public class Department {
       @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
       private int id;
       @ElementCollection
       @CollectionTable(name="EMP_SENIORITY")
       @MapKeyJoinColumn(name="EMP_ID")
       @Column(name="SENIORITY")
       private Map<Employee, Integer> seniorities;
}