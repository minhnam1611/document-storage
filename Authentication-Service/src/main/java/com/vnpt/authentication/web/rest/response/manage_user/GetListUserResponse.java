package com.vnpt.authentication.web.rest.response.manage_user;


import com.vnpt.authentication.entity.UserEntity;

import java.util.List;

public class GetListUserResponse {

    private long recordsTotal;

    private int totalPages;

    private int sizeOfPage;

    private int recordsFiltered;

    private List<UserEntity> data;


    public List<UserEntity> getData() {
        return data;
    }

    public void setData(List<UserEntity> listUser) {
        this.data = listUser;
    }

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
}
