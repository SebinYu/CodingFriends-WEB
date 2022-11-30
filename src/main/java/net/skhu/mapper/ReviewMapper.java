package net.skhu.mapper;


import net.skhu.dto.Review;
import net.skhu.dto.response.ResponseParticipation;
import net.skhu.dto.response.ResponseStudygroup;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ReviewMapper {

    @Select("SELECT studentId, studygroupId, studyGroupPartner, reviewScore, reviewContents,lectureScore, objection" +
            " FROM studygroup")
    List<Review> findAll();

    @Update("UPDATE studygroup SET title = #{title}, content = #{content}, writer = #{writer}, totalNum = #{totalNum}, startDate = #{startDate}, endDate = #{endDate} " +
            "WHERE studyGroup_id = #{studyGroup_id}")
    void update(Review review);
    @Insert("INSERT review (studentId, studygroupId, studyGroupPartner, reviewScore, reviewContents,lectureScore, objection   )"
            + " VALUES (#{studentId},#{studygroupId},#{studyGroupPartner}, #{reviewScore}, #{reviewContents}, #{lectureScore}, #{objection})")
    @Options(useGeneratedKeys=true, keyProperty="review_id")
    void Insert(Review review);

    @Delete("DELETE FROM participationrate WHERE studentId = #{studentId}")
    void delete(int studentId);

}