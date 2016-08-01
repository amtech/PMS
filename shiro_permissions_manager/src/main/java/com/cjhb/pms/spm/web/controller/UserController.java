package com.cjhb.pms.spm.web.controller;

import com.cjhb.pms.domain.spm_pojo.JsonModel;
import com.cjhb.pms.domain.spm_pojo.User;
import com.cjhb.pms.service.spm.RoleService;
import com.cjhb.pms.service.spm.UserService;
import com.cjhb.pms.spm.shiro.HashUtils;
import com.cjhb.pms.spm.web.exception.OperationNotAllowedException;
import com.cjhb.pms.utils.W2uiUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService usv;

    @Autowired
    private RoleService rsv;

    @InitBinder("user")
    public void initUserBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String view(Model model) {
        model.addAttribute("roles", rsv.all());
        return "user/list";
    }

    @RequiresPermissions("user:view")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list() {
        return W2uiUtils.toW2uiGrid(usv.all());
    }

    @RequiresPermissions("user:create")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel create(User user, JsonModel json) {
        user.setPassword(HashUtils.md5(user.getPassword()));
        int ret = usv.create(user);
        handleJsonModel(json, ret, "创建");
        return json;
    }

    @RequiresPermissions("user:eidt")
    @RequestMapping(value = "/{uid}/update", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel update(User user, JsonModel json) {
        int ret = usv.update(user);
        handleJsonModel(json, ret, "更新");
        return json;
    }

    @RequiresPermissions("user:delete")
    @RequestMapping(value = "/{uid}/delete", method = RequestMethod.GET)
    @ResponseBody
    public JsonModel delete(@PathVariable("uid") int uid, JsonModel json) {
        if (uid == 1)
            throw new OperationNotAllowedException("不能删除默认管理员账户");
        int ret = usv.delete(uid);
        handleJsonModel(json, ret, "删除");
        return json;
    }

    @RequiresAuthentication
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String password(Model model) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", usv.find(username));
        return "user/password";
    }

    @RequiresAuthentication
    @RequestMapping(value = "/{uid}/password", method = RequestMethod.POST)
    @ResponseBody
    public JsonModel password(@PathVariable("uid") int uid, String newPassword, JsonModel json) {
        User user = new User();
        user.setUid(uid);
        user.setPassword(HashUtils.md5(newPassword));
        int ret = usv.update(user);
        handleJsonModel(json, ret, "修改");
        return json;
    }

    private void handleJsonModel(JsonModel json, int ret, String op) {
        json.setSuccess(ret > 0);
        json.setMessage(op + (ret > 0 ? "成功" : "失败"));
    }
}
