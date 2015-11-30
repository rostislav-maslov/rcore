package com.ub.vk.controllers;

import com.ub.core.base.utils.RouteUtils;
import com.ub.core.security.service.AutorizationService;
import com.ub.vk.models.AppPropertiesVkDoc;
import com.ub.vk.response.AccessTokenResponse;
import com.ub.vk.services.AppPropertiesVkService;
import com.ub.vk.services.AuthorizeVkService;
import com.ub.vk.services.VkSessionService;
import com.ub.vk.services.exception.VkNotAuthorizedException;
import com.ub.vk.statparam.AuthorizeVkStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//@Controller
public class AuthorizeVkController {
    @Autowired private AppPropertiesVkService appPropertiesVkService;
    @Autowired private AuthorizeVkService authorizeVkService;
    @Autowired private AutorizationService autorizationService;
    @Autowired private VkSessionService vkSessionService;

    //@RequestMapping(value = AuthorizeVkRoutes.AUTHORIZE, method = RequestMethod.GET)
    private String authorize(RedirectAttributes redirectAttributes) {
        autorizationService.logout();
        AppPropertiesVkDoc appPropertiesVkDoc = appPropertiesVkService.getVkProp();
        redirectAttributes.addAttribute(AuthorizeVkStatic.P_CLIENT_ID, appPropertiesVkDoc.getAPP_ID());
        redirectAttributes.addAttribute(AuthorizeVkStatic.P_REDIRECT_URL, appPropertiesVkDoc.getREDIRECT_URI());
        redirectAttributes.addAttribute(AuthorizeVkStatic.P_DISPLAY, AuthorizeVkStatic.V_DISPLAY_PAGE);
        redirectAttributes.addAttribute(AuthorizeVkStatic.P_SCOPE, appPropertiesVkDoc.getPERMISSIONS());
        redirectAttributes.addAttribute(AuthorizeVkStatic.P_RESPONSE_TYPE, appPropertiesVkDoc.getResponse_type());
        return RouteUtils.redirectTo(AuthorizeVkStatic.AUTHORIZE_URL);
    }

    //@RequestMapping(value = AuthorizeVkRoutes.AUTHORIZE_GET_CODE, method = RequestMethod.GET)
    private String authorize(@RequestParam String code, RedirectAttributes redirectAttributes) {
        try {
            autorizationService.logout();
            AccessTokenResponse accessTokenResponse = authorizeVkService.getAccessToken(code);
            vkSessionService.authorize(accessTokenResponse);
        } catch (VkNotAuthorizedException e) {
            e.printStackTrace();
        }
        return "";
    }

}
