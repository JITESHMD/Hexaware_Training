package com.hexaware.dao;

import com.hexaware.entity.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDAO {
    void insertCompany(Company company) throws SQLException;
    List<Company> getAllCompanies() throws SQLException;
}
