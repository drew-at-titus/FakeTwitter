package com.titus.faketwitter.users;

import java.util.Objects;

public class UserCreationRequest {

  private String email;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getFirstName() {
    return firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public String getLastName() {
    return lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, firstName, lastName, password, username);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof UserCreationRequest)) {
      return false;
    }
    UserCreationRequest other = (UserCreationRequest)obj;
    return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
      && Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
      && Objects.equals(username, other.username);
  }

  @Override
  public String toString() {
    return "UserCreationRequest [email=" + email + ", username=" + username + ", firstName="
      + firstName + ", lastName=" + lastName + "]";
  }
}
