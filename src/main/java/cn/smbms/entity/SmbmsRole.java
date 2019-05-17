package cn.smbms.entity;


import java.util.Date;
import java.util.List;

public class SmbmsRole {

  private int id;
  private String roleCode;
  private String roleName;
  private int createdBy;
  private Date creationDate;
  private int modifyBy;
  private Date modifyDate;

  private List<SmbmsUser> userList;

  public List<SmbmsUser> getUserList() {
    return userList;
  }

  public void setUserList(List<SmbmsUser> userList) {
    this.userList = userList;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public int getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(int createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public int getModifyBy() {
    return modifyBy;
  }

  public void setModifyBy(int modifyBy) {
    this.modifyBy = modifyBy;
  }

  public Date getModifyDate() {
    return modifyDate;
  }

  public void setModifyDate(Date modifyDate) {
    this.modifyDate = modifyDate;
  }
}
