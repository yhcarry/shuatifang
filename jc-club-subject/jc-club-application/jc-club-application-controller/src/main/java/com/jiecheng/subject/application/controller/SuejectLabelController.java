package com.jiecheng.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jiecheng.subject.application.convert.SubjectLabelDTOConverter;
import com.jiecheng.subject.application.dto.SubjectLabelDTO;
import com.jiecheng.subject.common.entity.Result;
import com.jiecheng.subject.domain.entity.SubjectLabelBO;
import com.jiecheng.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签Controller
 * @Author: CYH
 * @Date: 2024/7/29 15:25
 */

@RestController
@Slf4j
@RequestMapping("/subject/label")
public class SuejectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增标签
     * @author: CYH
     * @date: 2024/7/29 15:28
     **/
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if (log.isInfoEnabled()){
                log.info("SuejectLabelController.add.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkArgument(!StringUtils.isBlank(subjectLabelDTO.getLabelName()),"标签名称不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.info("SuejectLabelController.add.error:{}",e.getMessage(),e);
            return Result.fail("新增标签失败");
        }

    }

    /**
     * 更新标签
     * @author: CYH
     * @date: 2024/7/29 15:28
     **/
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if (log.isInfoEnabled()){
                log.info("SuejectLabelController.update.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.update(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.info("SuejectLabelController.update.error:{}",e.getMessage(),e);
            return Result.fail("更新标签失败");
        }

    }

    /**
     * 删除标签
     * @author: CYH
     * @date: 2024/7/29 15:28
     **/
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if (log.isInfoEnabled()){
                log.info("SuejectLabelController.delete.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(),"标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.delete(subjectLabelBO);
            return Result.ok(result);
        }catch (Exception e){
            log.info("SuejectLabelController.delete.error:{}",e.getMessage(),e);
            return Result.fail("删除标签失败");
        }

    }

    /**
     * 查询分类下标签
     * @author: CYH
     * @date: 2024/7/29 15:28
     **/
    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO){
        try{
            if (log.isInfoEnabled()){
                log.info("SuejectLabelController.queryLabelByCategoryId.dto:{}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"分类id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToLabelBO(subjectLabelDTO);
            List<SubjectLabelBO> subjectLabelBOList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> subjectLabelDTOList = SubjectLabelDTOConverter.INSTANCE.convertBoToLabelDtoList(subjectLabelBOList);
            return Result.ok(subjectLabelDTOList);
        }catch (Exception e){
            log.info("SuejectLabelController.queryLabelByCategoryId.error:{}",e.getMessage(),e);
            return Result.fail("查询分类下标签失败");
        }

    }
}
