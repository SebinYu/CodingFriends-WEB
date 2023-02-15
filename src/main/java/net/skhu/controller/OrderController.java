package net.skhu.controller;

import net.skhu.dto.response.ResponseStudygroup;
import net.skhu.mapper.LearningMaterialMapper;
import net.skhu.mapper.StudygroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/studygroup/sequence")
public class OrderController {

    @Autowired
    StudygroupMapper studygroupMapper;

    @Autowired
    LearningMaterialMapper learningMaterialMapper;

    @GetMapping("updateDate")
    public String updateDate(Model model) throws Exception {
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());

        List<ResponseStudygroup> studygroups = studygroupMapper.findUpdateDateInfo();

        model.addAttribute("studygroups", studygroups);

        return "studygroup/sequence/updateDate";
    }

    @GetMapping("startDate")
    public String startDate(Model model) throws Exception {
        model.addAttribute("learningMaterials", learningMaterialMapper.findAll());

        List<ResponseStudygroup> studygroups = studygroupMapper.findStartDate();

        model.addAttribute("studygroups", studygroups);

        return "studygroup/sequence/startDate";
    }

}
