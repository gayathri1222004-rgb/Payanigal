package com.payanigal.data.dto;
public class User {
    private Long id;
    private String userId;
    private String name;
    private String email;
    private String password;
    private String mobileNo;
     private Role role;
    private Status status;
    private Long createdAt;
    public enum Role   { ADMIN, PASSENGER }
    public enum Status { ACTIVE, INACTIVE }
    public User() {
    }
    public Long getId()                  { return id; }
    public void setId(Long id)           { this.id = id; }
    public String getUserId()            { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getName()              { return name; }
    public void setName(String name)     { this.name = name; }
    public String getEmail()             { return email; }
    public void setEmail(String email)   { this.email = email; }
    public String getPassword()                  { return password; }
    public void setPassword(String password)     { this.password = password; }
    public String getMobileNo()                  { return mobileNo; }
    public void setMobileNo(String mobileNo)     { this.mobileNo = mobileNo; }
    public Role getRole()                { return role; }
    public void setRole(Role role)       { this.role = role; }
    public Status getStatus()            { return status; }
    public void setStatus(Status status) { this.status = status; }
    public Long getCreatedAt()                   { return createdAt; }
    public void setCreatedAt(Long createdAt)     { this.createdAt = createdAt; }
}
