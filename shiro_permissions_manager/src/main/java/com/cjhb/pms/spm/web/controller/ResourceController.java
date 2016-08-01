package com.cjhb.pms.spm.web.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springagg.web.bean.JsonModel;
import org.springagg.web.bean.Resource;
import org.springagg.web.exception.OperationNotAllowedException;
import org.springagg.web.service.ResourceService;
import org.springagg.web.utils.W2uiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService rsv;

    @InitBinder("resource")
    public void initResourceBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("resource.");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String view() {
        return "resource/list";
    }

    @RequiresPermissions("resource:view")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list() {
        List<Resource> resources = rsv.all();
        return W2uiUtils.toW2uiGrid(resources);
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel create(Resource res, JsonModel json) {
        int ret = rsv.create(res);
        handleJsonModel(json, ret, "创建");
        return json;
    }

    @RequiresPermissions("resource:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel update(Resource res, JsonModel json) {
        int ret = rsv.update(res);
        handleJsonModel(json, ret, "更新");
        return json;
    }

    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{reid}/delete", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel delete(@PathVariable("reid") int reid, JsonModel json) {
        if (reid == 1)
            throw new OperationNotAllowedException("不允许删除根节点");
        int ret = rsv.delete(reid);
        handleJsonModel(json, ret, "删除");
        return json;
    }

    private void handleJsonModel(JsonModel json, int ret, String op) {
        json.setSuccess(ret > 0);
        json.setMessage(op + (ret > 0 ? "成功" : "失败"));
    }
}
