package com.jiecheng.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jiecheng.subject.application.convert.SubjectCategoryDTOConverter;
import com.jiecheng.subject.application.convert.SubjectLabelDTOConverter;
import com.jiecheng.subject.application.dto.SubjectCategoryDTO;
import com.jiecheng.subject.application.dto.SubjectLabelDTO;
import com.jiecheng.subject.common.entity.Result;
import com.jiecheng.subject.common.util.LoginUtil;
import com.jiecheng.subject.domain.convert.SubjectLabelConverter;
import com.jiecheng.subject.domain.entity.SubjectCategoryBO;
import com.jiecheng.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * 刷题分类Controller
 * @Author: CYH
 * @Date: 2024/7/19 18:45
 */

@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Resource
    private SubjectCategoryDomainService subjectCategoryDomainService;

    /**
     * 新增分类
     * @author: CYH
     * @date: 2024/7/29 15:28
     **/
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try{
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.add.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectCategoryDTO.getCategoryName()),"分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类父级ID不能为空");
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryDTOConverter.INSTANCE.convertBoToCategory(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok(true);
        }catch (Exception e){
            log.info("SubjectCategoryController.add.error:{}",e.getMessage(),e);
            return Result.fail(e.getMessage());
        }

    }

    /**
     * 查询分类
     * @author: CYH
     * @date: 2024/7/23 20:13
     **/
    @PostMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            SubjectCategoryBO subjectCategoryBO= SubjectCategoryDTOConverter.INSTANCE.
                    convertDtoToCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.convertBoToCategoryDTOList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

    /**
     * 查询二级分类
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategoryByPrimary")
    public Result<List<SubjectCategoryDTO>> queryCategoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryCategoryByPrimary.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"分类ID不能为空");
            SubjectCategoryBO subjectCategoryBO= SubjectCategoryDTOConverter.INSTANCE.
                    convertDtoToCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategory(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDTOConverter.INSTANCE.
                    convertBoToCategoryDTOList(subjectCategoryBOList);
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }

    /**
     * 更新分类
     * @author: CYH 
     * @date: 2024/7/23 20:15
     **/
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.update.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO= SubjectCategoryDTOConverter.INSTANCE.
                    convertDtoToCategoryBo(subjectCategoryDTO);
            Boolean result  = subjectCategoryDomainService.update(subjectCategoryBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectCategoryController.update.error:{}",e.getMessage(),e);
            return Result.fail("更新分类失败");
        }
    }

    /**
     * 删除分类
     * @author: CYH
     * @date: 2024/7/23 20:15
     **/
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.delete.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            SubjectCategoryBO subjectCategoryBO= SubjectCategoryDTOConverter.INSTANCE.
                    convertDtoToCategoryBo(subjectCategoryDTO);
            Boolean result  = subjectCategoryDomainService.delete(subjectCategoryBO);
            return Result.ok(result);
        }catch (Exception e){
            log.error("SubjectCategoryController.update.error:{}",e.getMessage(),e);
            return Result.fail("删除分类失败");
        }
    }

    /**
     * 查询分类及标签一次性
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/queryCategoryAndLabel")
    public Result<List<SubjectCategoryDTO>> queryCategoryAndLabel(@RequestBody SubjectCategoryDTO subjectCategoryDTO, HttpServletRequest request){
        try {
            if (log.isInfoEnabled()){
                log.info("SubjectCategoryController.queryCategoryAndLabel.dto:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            String loginId = LoginUtil.getLoginId();
            log.info("SubjectCategoryController.queryCategoryAndLabel.loginId:{}", loginId);
            Preconditions.checkNotNull(subjectCategoryDTO.getId(),"分类ID不能为空");
            SubjectCategoryBO subjectCategoryBO= SubjectCategoryDTOConverter.INSTANCE.
                    convertDtoToCategoryBo(subjectCategoryDTO);
            List<SubjectCategoryBO> subjectCategoryBOList = subjectCategoryDomainService.queryCategoryAndLabel(subjectCategoryBO);
            List<SubjectCategoryDTO> subjectCategoryDTOList = new ArrayList<>();
            subjectCategoryBOList.forEach(bo->{
                SubjectCategoryDTO dto = SubjectCategoryDTOConverter.INSTANCE.convertBoToDTO(bo);
                List<SubjectLabelDTO> labelDTOList = SubjectLabelDTOConverter.INSTANCE.convertBoToLabelDtoList(bo.getLabelBOList());
                dto.setLabelDTOList(labelDTOList);
                subjectCategoryDTOList.add(dto);
            });
            return Result.ok(subjectCategoryDTOList);
        }catch (Exception e){
            log.error("SubjectCategoryController.queryCategoryAndLabel.error:{}",e.getMessage(),e);
            return Result.fail("查询失败");
        }
    }
}
