package vn.edu.iuh.fit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "account")
public class Account {
    @Id
    @Column(name = "account_id",nullable = false,columnDefinition = "nvarchar(50)")
    private String accountId;
    @Column(name = "full_name",nullable = false,columnDefinition = "nvarchar(50)")
    private String fullName;
    @Column(name = "password",nullable = false,columnDefinition = "nvarchar(50)")
    private String password;
    @Column(name = "email",columnDefinition = "nvarchar(50)")
    private String email;
    @Column(name = "phone",columnDefinition = "nvarchar(50)")
    private String phone;
    @Column(name = "status",nullable = false,columnDefinition = "tinyint(4)")
    private int status;

    public Account() {
    }

    public Account(String accountId, String fullName, String password, String email, String phone, int status) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getStatus() {
        return status;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
