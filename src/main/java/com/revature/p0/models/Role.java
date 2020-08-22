package com.revature.p0.models;

public enum Role {
    ADMIN("Admin"), MANAGER("Manager"), ACCOUNT_HOLDER("Account Holder"), CLOSED("Closed");
    private String roleName;
    Role(String name) {
        this.roleName = name;
    }

    public static Role getByName(String name){

        for (Role role : Role.values()){
            if (role.roleName.equals(name)){
                return role;
            }
        }
        return CLOSED;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
