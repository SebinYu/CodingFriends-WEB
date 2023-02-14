package net.skhu.mapper;

import net.skhu.dto.request.RequestStudygroup;
import net.skhu.dto.response.ResponseStudygroup;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
@CacheNamespace(flushInterval=10000)
public interface StudygroupMapper {

//   조회 쿼리를 작성시, *지양__ 유지보수, 필요한 칼럼만 찾도록하는 효율성 고려 목적
    @Cacheable(value= "NoticeList")
    @Select("SELECT studyGroup_id, title, content, writer, totalNum, startDate, endDate, updateDate" +
            " FROM studygroup")
    List<ResponseStudygroup> findAll();

    @Select("SELECT apply_id" +
            " FROM apply WHERE studygroupId = #{studygroupId}")
    Integer[] findApplyer(BigInteger studygroupId);

    @Select("SELECT userid" +
            " FROM user WHERE name = #{name}")
    String findID(String name);
    @Select("SELECT writer" +
            " FROM studygroup WHERE studyGroup_id = #{studyGroup_id}")
    String findWriter(BigInteger studyGroup_id);

    @Select("SELECT studyGroup_id, title, content, writer, totalNum, startDate, endDate, updateDate " +
            "FROM studygroup where title LIKE CONCAT('%', #{keyword}, '%') AND learningMaterial_id LIKE CONCAT('%', #{learningMaterial_id}, '%')")
    List<ResponseStudygroup> findSearchedStudygroup(String keyword, String learningMaterial_id);

    @Select("SELECT studyGroup_id, title, content, writer, totalNum, startDate, endDate, updateDate " +
            "FROM studygroup where title LIKE CONCAT('%', #{keyword}, '%')")
    List<ResponseStudygroup> findSearchedStudygroupKeyword(String keyword);

    @Select("SELECT studyGroup_id, title, content, writer, totalNum, startDate, endDate, updateDate" +
            " FROM studygroup WHERE studyGroup_id = #{studyGroup_id}")
    ResponseStudygroup findOne(BigInteger studyGroup_id);

    @Select("SELECT updateDate FROM studygroup")
    LocalDateTime[] findUpdateDate();

    @Select("SELECT studyGroup_id, title, content, writer, totalNum, startDate, endDate, updateDate" +
            " FROM studygroup WHERE UpdateDate = #{UpdateDate}")
    ResponseStudygroup findUpdateDateInfo(LocalDateTime UpdateDate);

    @Select("SELECT studyGroup_id, title, content, writer, totalNum, startDate, endDate, updateDate" +
            " FROM studygroup ORDER BY startDate asc")
    List<ResponseStudygroup> findStartDate();

    @CacheEvict (value= "NoticeList", allEntries = true)
    @Insert("INSERT studygroup (title,content,writer,totalNum,startDate,endDate, learningMaterial_id, x_map, y_map)"
    		+ " VALUES (#{title},#{content},#{writer},#{totalNum},#{startDate},#{endDate},#{learningMaterial_id},#{x_map}, #{y_map})")
    @Options(useGeneratedKeys=true, keyProperty="studyGroup_id")
    void insert(ResponseStudygroup studygroup);


    @CacheEvict (value= "NoticeList", allEntries = true)
    @Update("UPDATE studygroup SET title = #{title}, content = #{content}, writer = #{writer}, totalNum = #{totalNum}, startDate = #{startDate}, endDate = #{endDate}, x_map = #{x_map}, y_map = #{y_map} " +
            "WHERE studyGroup_id = #{studyGroup_id}")
    void update(ResponseStudygroup studygroup);


    @CacheEvict (value= "NoticeList", allEntries = true)
    @Delete("DELETE FROM studygroup WHERE studyGroup_id = #{studyGroup_id}")
    void delete(BigInteger studyGroup_id);

}
