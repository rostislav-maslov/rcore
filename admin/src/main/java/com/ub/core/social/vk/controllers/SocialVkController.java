package com.ub.core.social.vk.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class SocialVkController {

//    @Autowired
//    @Qualifier("prop")
//    private java.util.Properties prop;

//    @Autowired
//    private AutorizationService autorizationService;
//
//    //Properties prop = new Properties();
//    private String vkAppId;
//    private String vkAppKey;
//    private String vkAppPermissions;
//    private String vkAppRedirectUrl;
//    private String vkApiVersion;
//
//    private void readProperties(){
//        try {
//            //load a properties file
//            // prop.load(new FileInputStream("/webapp/WEB-INF/social.properties"));
//
////            //get the property value and print it out
////            vkAppId = prop.getProperty("vk_app_id");
////            vkAppKey = prop.getProperty("vk_app_key");
////            vkAppPermissions = prop.getProperty("vk_app_permissions");
////            vkAppRedirectUrl = prop.getProperty("vk_app_redirect_url");
////            vkApiVersion = prop.getProperty("vk_api_version");
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//    }
//
//
//    @RequestMapping(value="socialAuth/vk", method = RequestMethod.GET)
//    public ModelAndView fbAuthRequest(ModelMap model) {
//        readProperties();
//
//        try {
//            RedirectView redirectView = new RedirectView("https://oauth.vk.com/authorize?"  + "client_id=" + vkAppId +
//                    "&scope=" + vkAppPermissions +
//                    "&redirect_uri=" + vkAppRedirectUrl +
//                    "&display=page" +
//                    "&response_type=code&" +
//                    "&v=" + vkApiVersion
//                    , true, true, true);
//            return new ModelAndView(redirectView);
//        }
//        catch (Exception e){
//            e.getStackTrace();
//            return null;
//        }
//
//
//
//
//    }
//
//    @RequestMapping(value="socialAuth/vk/answer", method = RequestMethod.GET,
//            params = {"code"})
//    public String vkAuthGetCode(@RequestParam(value = "code", required=false) String code, HttpSession httpSession){
////        return new ModelAndView(new RedirectView("https://oauth.vk.com/access_token"  + "?client_id=" + vkAppId +
////                "&client_secret=" + vkAppKey +
////                "&code=" + code +
////                "&redirect_uri=" + vkAppRedirectUrl
////                , true, true, true));
//
//
//        if(code!=null){
//            HttpsConnectionHelper helper = new HttpsConnectionHelper();
//            String https_url = "https://oauth.vk.com/access_token"  + "?client_id=" + vkAppId +
//                    "&client_secret=" + vkAppKey +
//                    "&code=" + code +
//                    "&redirect_uri=" + vkAppRedirectUrl;
//
//            URL url;
//            try {
//
//                url = new URL(https_url);
//                HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
//
//                //dumpl all cert info
//                helper.print_https_cert(con);
//
//                //dump all the content
//                String result = helper.print_content(con);
//                Map json = (JSONObject)new JSONValue().parse(result);
//
//                autorizationService.authorizeVk(json.get("access_token").toString(),json.get("user_id").toString());
//
//
//
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//        return "com.ub.dyl.client.index";
//    }
//
//    @RequestMapping(value="socialAuth/vk/answer", method = RequestMethod.GET,
//            params = {"access_token"})
//    public ModelAndView vkAuthGetToken(@RequestParam(value = "access_token", required=false) String access_token){
//        System.out.println(access_token);
//        return null;
//    }
//
//
//    @RequestMapping(value="socialAuth/vk/answer", method = RequestMethod.GET,
//            params = {"code", "error", "error_description","access_token"})
//    public String vkAuthGetCode(@RequestParam(value = "code", required=false) String code,
//                                      @RequestParam(value = "error", required=false) String error,
//                                      @RequestParam(value = "error_description", required=false) String error_description,
//                                      @RequestParam(value = "access_token", required=false) String access_token,
//                                      ModelMap model){
//
//        if(error!= null){
//            System.out.println(error_description);
//            return null;
//        }
//        if(code!=null){
//            String url = "https://oauth.vk.com/access_token?"  + "?client_id=" + vkAppId +
//                    "&client_secret=" + vkAppKey +
//                    "&code=" + code +
//                    "&redirect_uri=" + vkAppRedirectUrl;
//
//            CallHttpApiHelper callHttpApiHelper = new CallHttpApiHelper();
//            HttpURLConnection con = null;
//
//            try {
//                con = callHttpApiHelper.getConnection(url, "", "POST");
//            } catch (Exception e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//
//
//            int responseCode = 0;
//            try {
//                responseCode = con.getResponseCode();
//            } catch (IOException e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//            }
//            if(responseCode != 200){
//
//            }
//            try {
//                Map json = (JSONObject)new JSONValue().parse(callHttpApiHelper.getResponse(con).toString());
//
//
//
//                //return response.toString();
//            } catch (Exception e) {
//                System.out.print(e.getMessage());
//            }
//
////            return new ModelAndView(new RedirectView("https://oauth.vk.com/access_token?"  + "?client_id=" + vkAppId +
////                    "&client_secret=" + vkAppKey +
////                    "&code=" + code +
////                    "&redirect_uri=" + vkAppRedirectUrl
////                    , true, true, true));
//        }
//        if(access_token!=null){
//
//        }
//
//        return "com.ub.dyl.client.index";
//
//    }
//
//    public Properties getProp() {
//        return prop;
//    }
//
//    public void setProp(Properties prop) {
//        this.prop = prop;
//    }
}
