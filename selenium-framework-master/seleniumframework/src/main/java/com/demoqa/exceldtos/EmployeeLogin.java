package com.demoqa.exceldtos;

public class EmployeeLogin {
  private String employeeLogin;
  private String employeedPassword;
  private Boolean isValidUser;

  public String getEmployeeLogin() {
    return employeeLogin;
  }

  public void setEmployeeLogin(final String employeeLogin) {
    this.employeeLogin = employeeLogin;
  }

  public String getEmployeedPassword() {
    return employeedPassword;
  }

  public void setEmployeedPassword(final String employeedPassword) {
    this.employeedPassword = employeedPassword;
  }

  public Boolean getIsValidUser() {
    return isValidUser;
  }

  public void setIsValidUser(final String isValidUser) {
    if (isValidUser.equalsIgnoreCase("YES"))
      this.isValidUser = true;
    else
      this.isValidUser = false;
  }

  @Override
  public String toString() {
    return "logged in emp user name " + employeeLogin;
  }
}
