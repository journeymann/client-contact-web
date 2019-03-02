package com.hlpp.clientcontact.security;


/**
 * describes the different application roles that exist
 */
public enum Role {

    ADMIN_ROLE("ROLE_ADMIN", 1), USER_ROLE("ROLE_USER", 0);

    private final int order;
    private final String roleName;

    private Role(final String roleName, final int order) {
        this.roleName = roleName;
        this.order = order;
    }

    public int order() {
        return order;
    }

    public String roleName() {
        return roleName;
    }

}