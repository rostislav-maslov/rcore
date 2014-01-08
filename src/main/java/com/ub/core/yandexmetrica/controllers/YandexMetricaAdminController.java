package com.ub.core.yandexmetrica.controllers;

import com.ub.core.yandexmetrica.form.YMForm;
import com.ub.core.yandexmetrica.models.YandexMetricaDoc;
import com.ub.core.yandexmetrica.services.YandexMetricaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin/yandexmetrica/")
public class YandexMetricaAdminController {

    @Autowired private YandexMetricaService yandexMetricaService;

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String update(Model model){
        YandexMetricaDoc doc = yandexMetricaService.get();
        YMForm ymForm = new YMForm();
        ymForm.setContent(doc.getContent());
        model.addAttribute("ymForm", ymForm);
        return "com.ub.core.admin.yandexmetrica.update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute @Valid YMForm ymForm, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "com.ub.core.admin.yandexmetrica.update";
        }
        yandexMetricaService.update(ymForm.getContent());
        model.addAttribute("ymForm", ymForm);
        return "com.ub.core.admin.yandexmetrica.update";
    }
}
