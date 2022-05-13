package com.example.upvoluntaryo.objects;

public class Orgs {
    String orgName, orgDetails;
    int orgId;

    public Orgs(int orgId, String orgName, String orgDetails) {
        this.orgId = orgId;
        this.orgName = orgName;
        this.orgDetails = orgDetails;
    }

    public Orgs(String orgName, String orgDetails) {
        this.orgName = orgName;
        this.orgDetails = orgDetails;
    }

    public int getOrgId() {
        return orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getOrgDetails() {
        return orgDetails;
    }
}