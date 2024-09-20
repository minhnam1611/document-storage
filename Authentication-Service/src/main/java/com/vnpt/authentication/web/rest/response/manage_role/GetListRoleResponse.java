package com.vnpt.authentication.web.rest.response.manage_role;



import com.vnpt.authentication.entity.RoleEntity;

import java.util.List;

public class GetListRoleResponse {

    private long recordsTotal;

    private int totalPages;

    private int sizeOfPage;

    private int recordsFiltered;

    private List<RoleEntity> data;

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSizeOfPage() {
        return sizeOfPage;
    }

    public void setSizeOfPage(int sizeOfPage) {
        this.sizeOfPage = sizeOfPage;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<RoleEntity> getData() {
        return data;
    }

    public void setData(List<RoleEntity> data) {
        this.data = data;
    }
}
