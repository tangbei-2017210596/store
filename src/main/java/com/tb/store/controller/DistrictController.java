package com.tb.store.controller;
import com.tb.store.entity.District;
import com.tb.store.service.DistrictService;
import com.tb.store.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {
    @Resource
    private DistrictService districtService;
    @RequestMapping({"/",""})
    public JsonResult<List<District>> displayDistrictByParent(String parent){
        List<District> districts = districtService.getByParent(parent);
        return  new JsonResult<>(OK,districts);
    }
}
