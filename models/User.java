package models;

import models.enums.Enumeration.Role;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private Role role;
    private String email;

    public User(int userId, String username, String passwordHash, Role role, String email) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.email = email;
    }

    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public Role getRole() { return role; }
    public String getEmail() { return email; }

    public void setUserId(int userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setRole(Role role) { this.role = role; }
    public void setEmail(String email) { this.email = email; }
}
