package com.cjhb.pms.spm.web.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springagg.web.bean.JsonModel;
import org.springagg.web.bean.Role;
import org.springagg.web.service.ResourceService;
import org.springagg.web.service.RoleService;
import org.springagg.web.utils.W2uiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService rsv;

    @Autowired
    private ResourceService resv;

    @InitBinder("role")
    public void initRoleBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("role.");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("roles", rsv.all());
        model.addAttribute("resources", resv.all());
        return "role/list";
    }

    @RequiresPermissions("role:view")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list() {
        return W2uiUtils.toW2uiGrid(rsv.all());
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel create(Role role, JsonModel json) {
        int ret = rsv.create(role);
        handleJsonModel(json, ret, "创建");
        return json;
    }

    @RequiresPermissions("role:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel update(Role role, JsonModel json) {
        int ret = rsv.update(role);
        handleJsonModel(json, ret, "更新");
        return json;
    }

    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{rid}/delete", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel delete(@PathVariable("rid") int rid, JsonModel json) {
        int ret = rsv.delete(rid);
        handleJsonModel(json, ret, "删除");
        return json;
    }

    private void handleJsonModel(JsonModel json, int ret, String op) {
        json.setSuccess(ret > 0);
        json.setMessage(op + (ret > 0 ? "成功" : "失败"));
    }
}
