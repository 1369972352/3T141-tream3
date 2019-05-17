package cn.smbms.entity;


import java.util.Date;

public class SmbmsUser {

  private Integer id;
  private String userCode;
  private String userName;
  private String userPassword;
  private Integer gender;
  private Date birthday;
  private String phone;
  private String address;
  private Integer userRole;
  private Integer createdBy;
  private Date creationDate;
  private Integer modifyBy;
  private Date modifyDate;

  private int age;

  private SmbmsRole role;

  public int getAge() {
    Date currDate=new Date();
    Long time=(currDate.getTime()-birthday.getTime())/31536000000L;
    return time.intValue();
  }

  public void setAge(int age) {
    this.age = age;
  }

  public SmbmsRole getRole() {
    return role;
  }

  public void setRole(SmbmsRole role) {
    this.role = role;
  }

  public SmbmsUser() {
  }

  public SmbmsUser(Integer id, String userCode, String userName, String userPassword, Integer gender, Date birthday, String phone, String address, Integer userRole, Integer createdBy, Date creationDate, Integer modifyBy, Date modifyDate, SmbmsRole role) {
    this.id = id;
    this.userCode = userCode;
    this.userName = userName;
    this.userPassword = userPassword;
    this.gender = gender;
    this.birthday = birthday;
    this.phone = phone;
    this.address = address;
    this.userRole = userRole;
    this.createdBy = createdBy;
    this.creationDate = creationDate;
    this.modifyBy = modifyBy;
    this.modifyDate = modifyDate;
    this.role = role;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Integer getUserRole() {
    return userRole;
  }

  public void setUserRole(Integer userRole) {
    this.userRole = userRole;
  }

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public Integer getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(Integer modifyBy) {
    this.modifyBy = modifyBy;
  }

  public Date getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }

}
