package com.cjhb.pms.spm.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.springagg.web.bean.JsonModel;
import org.springagg.web.constants.Constants;
import org.springagg.web.utils.W2uiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 会话控制器
 * 
 * @author Archx<archx@foxmail.com>
 */
@Controller
@RequiresPermissions("session:*")
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private SessionDAO sessionDao;

    @RequestMapping(method = RequestMethod.GET)
    public String view() {
        return "session/list";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> list() {
        Collection<Session> sessions = sessionDao.getActiveSessions();
        return W2uiUtils.toW2uiGrid(buildSessionModel(sessions));
    }

    @RequestMapping("/{sessionId}/forceLogout")
    @ResponseBody
    public JsonModel forceLogout(@PathVariable("sessionId") String sessionId, JsonModel json) {
        try {
            Session session = sessionDao.readSession(sessionId);
            if (session != null) {
                session.setAttribute(Constants.SESSION_FORCE_LOGOUT_KEY, Boolean.TRUE);
            }
        } catch (Exception e) {/* ignore */
        }
        json.setSuccess(true);
        json.setMessage("强制退出成功");
        return json;
    }

    private List<Map<String, Object>> buildSessionModel(Collection<Session> sessions) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int index = 0;
        for (Session session : sessions) {
            PrincipalCollection principalCollection = (PrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (principalCollection == null)
                continue;

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("user", (String) principalCollection.getPrimaryPrincipal());
            map.put("recid", ++index);
            map.put("id", session.getId());
            map.put("host", session.getHost());
            map.put("last", session.getLastAccessTime());

            map.put("force", session.getAttribute(Constants.SESSION_FORCE_LOGOUT_KEY) != null);
            list.add(map);
        }
        return list;
    }
}
