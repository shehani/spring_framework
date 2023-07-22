package com.example.rowmappers;

import com.example.model.Idea;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdeaRowMapper implements RowMapper<Idea> {

    @Override
    public Idea mapRow(ResultSet rs, int rowNum) throws SQLException {
        Idea idea = new Idea();
        idea.setId(rs.getInt("id"));
        idea.setFirstName(rs.getString("firstName"));
        idea.setLastName(rs.getString("lastName"));
        idea.setAddress(rs.getString("address"));
        idea.setAddress2(rs.getString("address2"));
        idea.setOpinion(rs.getString("opinion"));
        idea.setCreatedDate(rs.getTimestamp("created_date").toLocalDateTime());

        if(rs.getString("modified_by") != null && rs.getTimestamp("modified_date") != null ){
            idea.setModifiedBy(rs.getString("modified_by"));
            idea.setModifiedDate(rs.getTimestamp("modified_date").toLocalDateTime());
        }
        return idea;
    }
}
