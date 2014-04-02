package com.ub.core.base.role;

public abstract class Role {

    protected final String id = this.getClass().getName();
    protected String roleTitle;
    protected String roleDescription;

//    public static List<Role> getAllRoles(){
//        Reflection reflections = new Reflection("com.mycompany");
//        Set<Class<? extends MyInterface>> classes = reflections.getSubTypesOf(MyInterface.class);
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
      //  this.id = id;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
